package com.luke.shop.eshop.goods.service.impl;

import com.luke.shop.eshop.base.BaseService;
import com.luke.shop.eshop.base.dao.ISystemSetupComDao;
import com.luke.shop.eshop.base.dao.IYWDao;
import com.luke.shop.eshop.base.service.IBusiness;
import com.luke.shop.eshop.base.service.impl.BusinessProxy;
import com.luke.shop.eshop.goods.dao.IGoodsDao;
import com.luke.shop.eshop.goods.service.IGoodsService;
import com.luke.shop.eshop.goods.vo.VOGoods;
import com.luke.shop.eshop.goods.vo.VOGoodsEdit;
import com.luke.shop.eshop.goods.vo.VOLens;
import com.luke.shop.eshop.goods.vo.VOLensFindResult;
import com.luke.shop.model.*;
import com.luke.shop.tool.*;
import com.luke.shop.tool.vo.VOId;
import net.sf.json.JSONArray;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luke on 2018/7/27.
 */
@Service
public class GoodsService extends BaseService implements IGoodsService,IBusiness {

    private static final Logger log = Logger.getLogger(GoodsService.class) ;

    @Resource
    IGoodsDao goodsDao ;
    @Resource
    BusinessProxy proxy  ;

    @Resource
    ISystemSetupComDao scDao ;
    @Resource
    IYWDao ywDao ;


    @Override
    public TG_Goods addGoods_1(LoginTuken sessionTuken, VOGoods vo) throws Exception {

        TU_Com com = this.goodsDao.get(TU_Com.class,sessionTuken.getComId()) ;

        /**保存商品信息*/
        TG_Goods goods = new TG_Goods() ;
        BeanUtils.copyProperties(vo, goods);
        goods.setCom(com);
        goods.setPinYin(LK.NameToPingYinLong(goods.getName()));
        goods.setPy(LK.NameToPingYinShort(goods.getName()));

        TG_GoodsTree color = this.goodsDao.get(TG_GoodsTree.class,vo.getColorId()) ;
        TG_GoodsTree version = this.goodsDao.get(TG_GoodsTree.class,color.getFid()) ;
        TG_GoodsTree brand = this.goodsDao.get(TG_GoodsTree.class,version.getFid()) ;
        TG_GoodsTree kind = this.goodsDao.get(TG_GoodsTree.class,brand.getFid()) ;

        goods.setKind(kind);
        goods.setBrand(brand);
        goods.setVersion(version);
        goods.setColor(color);

        goods = this.goodsDao.save(goods);

        /**保存扩展属性*/
        TG_GoodsAttr attr = new TG_GoodsAttr() ;
        BeanUtils.copyProperties(vo, attr);
        attr.setGoods(goods);
        this.goodsDao.save(attr) ;

        return goods ;
    }

    @Override
    public void addGoods_1_def_kc(LoginTuken sessionTuken, TG_Goods goods) throws Exception {
        /**添加商品时初始化库存*/
        TU_Com com = goods.getCom() ;
        TSYS_SetupCom sc = scDao.save_not_lens_add_kc(com.getId()) ;
        /**系统配置了添加商品时初始化库存才执行*/
        if(Boolean.parseBoolean(sc.getVal())){
            Long num = 0l ;
            if(LK.StrIsNotEmpty(sc.getExt1())){
                num = Long.parseLong(sc.getExt1()) ;
            }

            TG_GoodsTree kindNode = goods.getKind() ;
            /**初始化库存业务*/
            TK_YW yw = this.ywDao.yw0() ;
            TU_User user = this.goodsDao.get(TU_User.class, sessionTuken.getId()) ;
            List<TU_Store> listStore = this.goodsDao.find("From TU_Store s where s.com.id=:id  and s.isHasKc=true ", com) ;

            List<_YWMX> listInitBillMx = new ArrayList<>(1) ;
            /**每个需要有库存的站点执行*/
            for(TU_Store store : listStore){
                final Long finalNum = num;

                TK_InitBill bill = (TK_InitBill)proxy.getInstance(this).createBill(()->{
                    /**保存单据*/
                    TK_InitBill initBill = new TK_InitBill(goods.getCom(),yw,store,user) ;
                    this.goodsDao.save(initBill) ;
                    return initBill ;
                },(ywdj)->{
                    /**保存明细*/
                    TK_InitBillMX initBillMX = new TK_InitBillMX() ;
                    initBillMX.setDj((TK_InitBill)ywdj);
                    initBillMX.setL_goods(goods);
                    initBillMX.setL_num(finalNum);
                    this.goodsDao.save(initBillMX) ;
                }, user,TK_InitBill.class.getSimpleName());
                proxy.getInstance(this).affirmBill(bill,user, TK_InitBill.class.getSimpleName()) ;
                proxy.getInstance(this).executeBill(bill, user,TK_InitBill.class.getSimpleName(),false) ;
            }
        }
    }

    @Override
    public void getGoodsLens_5(ActionResult actionResult, VOId vo) throws Exception {
        TG_LensSetup goodsLensSetup = this.goodsDao.getGoodsLens_5_lensSetup(vo) ;
        List<TG_Lens> listLens = this.goodsDao.getGoodsLens_5_lens(vo) ;
        VOLensFindResult goodsLensFindResult = null ;
        List<VOLensFindResult> goodsLens = new ArrayList<>(listLens.size()) ;
        for(TG_Lens lens :listLens){
            goodsLensFindResult = new VOLensFindResult();
            BeanUtils.copyProperties(lens,goodsLensFindResult);
            goodsLens.add(goodsLensFindResult) ;
        }
        actionResult.setData(new LKMap<String, Object>().putEx("goodsLensSetup", goodsLensSetup).putEx("goodsLens", goodsLens));
    }


    @Override
    public void saveLens_6(LoginTuken sessionTuken, ActionResult actionResult, VOLens vo) throws Exception{
        JSONArray jsonGoodsLens = JSONArray.fromObject(vo.getLens()) ;
        TG_Lens[] arrayGoodsLens = (TG_Lens[]) JSONArray.toArray(jsonGoodsLens, TG_Lens.class) ;
        if(arrayGoodsLens.length>6500){
            Assertion.Error("度数配置超过6500，请分为近视，远视，混散");
        }

        TG_Goods goods = this.goodsDao.get(TG_Goods.class,vo.getGoodsId()) ;

        this.goodsDao.saveLens_6_delete(vo) ;
        /**保存度数配置*/
        TG_LensSetup lensSetup = new TG_LensSetup() ;
        BeanUtils.copyProperties(vo, lensSetup);
        lensSetup.setGoods(goods);
        this.goodsDao.save(lensSetup) ;
        /**保存所有度数*/
        List<TG_Lens> listLens = this.goodsDao.saveLens_6_allLens(lensSetup, goods, arrayGoodsLens) ;

        /**处理返回值 */
        VOLensFindResult goodsLensFindResult = null ;
        List<VOLensFindResult> goodsLens = new ArrayList<>(listLens.size()) ;
        for(TG_Lens lens :listLens){
            goodsLensFindResult = new VOLensFindResult();
            BeanUtils.copyProperties(lens,goodsLensFindResult);
            goodsLens.add(goodsLensFindResult) ;
        }
        actionResult.setData(new LKMap<String, Object>().putEx("goodsLensSetup", lensSetup).putEx("goodsLens", goodsLens));

        actionResult.setZytz("修改度数配置会重置价格与库存，请重新盘点库存与设置价格");
    }



    @Override
    public void saveLens_6_def_kc(LoginTuken sessionTuken, ActionResult actionResult, VOLens vo) throws Exception {
        TG_Goods goods = this.goodsDao.get(TG_Goods.class, vo.getGoodsId()) ;
        Boolean isGlass = false ;
        if("品类".equals(goods.getKind().getC_group())&&"镜片".equals(goods.getKind().getText())){
            isGlass = true ;
        }
        TU_Com com = goods.getCom() ;
        TSYS_SetupCom sc = scDao.save_lens_add_kc(com.getId()) ;
        Long num = 0l ;

        if(Boolean.parseBoolean(sc.getVal())){
            if(LK.StrIsNotEmpty(sc.getExt1())){
                num = Long.parseLong(sc.getExt1()) ;
            }else{
                Assertion.Error("请正确配置 TSYS_SetupCom表save_lens_add_kc");
            }

            TK_YW yw = this.ywDao.yw0() ;
            TU_User user = this.goodsDao.get(TU_User.class, sessionTuken.getId()) ;
            /**公司下面有镜片库存的站点*/
            List<TU_Store> lstStore = null ;
            if(isGlass){
                lstStore =  this.goodsDao.find("From TU_Store s where s.com.id=:id and s.isHasLensKc=true ", com) ;
            }else{
                lstStore =  this.goodsDao.find("From TU_Store s where s.com.id=:id and s.isHasKc=true ", com) ;
            }


            /**是加工中心并且有库存的站点*/
            for(TU_Store store :lstStore){

                /**以商品id，商品对应的度数数据,系统配置的初始化库存数据，批量生成单据明细信息*/


                final Long finalNum = num;
                TK_InitBill bill = (TK_InitBill) proxy.getInstance(this).createBill(()->{
                    TK_InitBill initBill = new TK_InitBill(goods.getCom(),yw,store,user) ;
                    this.goodsDao.save(initBill) ;
                    return initBill ;
                },(ywdj)->{
                     this.goodsDao.saveLensDefVal_7_dbCopy_dj(goods.getId(), finalNum,ywdj.getId()) ;
                }, user,  TK_InitBill.class.getSimpleName());
                proxy.getInstance(this).affirmBill(bill,user,TK_InitBill.class.getSimpleName()) ;
                proxy.getInstance(this).executeBill(bill,user, TK_InitBill.class.getSimpleName(),true) ;
            }
        }

    }

    @Override
    public void editGoods_2(VOGoodsEdit vo) throws Exception {
        this.goodsDao.updateGoods(vo) ;
        this.goodsDao.saveOrUpdateGoods(vo) ;
    }

    @Override
    public void delGoods_3(VOId vo) throws Exception {
        this.goodsDao.delGoods_3(vo) ;
    }
}
