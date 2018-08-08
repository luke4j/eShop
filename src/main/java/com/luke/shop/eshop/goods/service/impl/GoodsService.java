package com.luke.shop.eshop.goods.service.impl;

import com.luke.shop.eshop.base.BaseService;
import com.luke.shop.eshop.base.service.IBusiness;
import com.luke.shop.eshop.base.service.impl.BusinessProxy;
import com.luke.shop.eshop.goods.dao.IGoodsDao;
import com.luke.shop.eshop.goods.service.IGoodsService;
import com.luke.shop.eshop.goods.vo.VOGoods;
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
import java.util.Date;
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

    @Override
    public <T, V> T createBill(V bill,TU_User zdUser,String tag) throws Exception {
        return null;
    }

    @Override
    public <T> T affirmBill(T bill,TU_User qrUser ,String tag) throws Exception {
        /**初始化库存单据确认*/
        if("LensInit".equals(tag)){
            TK_Init init = (TK_Init)bill ;
            init.setY_qr_user(qrUser);
            init.setQrTime(new Date());
            init.setY_bill_state(_YW.BillState.qr);
            return (T)this.goodsDao.update_GoodsDao(init) ;
        }else if("NotLensInit".equals(tag)){
            TK_Init init = (TK_Init)bill ;
            init.setY_qr_user(qrUser);
            init.setQrTime(new Date());
            init.setY_bill_state(_YW.BillState.qr);
            return (T)this.goodsDao.update_GoodsDao(init) ;
        }else{
            Assertion.Error("GoodsService.affirmBill param [tag] is not handler");
        }
        return null ;
    }

    @Override
    public <T> T executeBill(T bill,TU_User zxUser,String tag) throws Exception {
        if("LensInit".equals(tag)){
            TK_Init init = (TK_Init)bill ;
            /**设置单据状态与执行操作人*/
            init.setY_zx_user(zxUser);
            init.setZxTime(new Date());
            init.setY_bill_state(_YW.BillState.qr);
            this.goodsDao.update_GoodsDao(init) ;

            List<TK_KC> listKc = new ArrayList<>(init.getDjmx().size()) ;
            TK_KC kc = null ;TG_Goods goods = null;
            /**每一条初始化单据的明细都有一个库存记录*/

            this.goodsDao.saveAll_GoodsDao(listKc) ;



            /**更新初始化单据中的库存数据 */
            for(_YWList ywlist:init.getDjmx()){
                TK_InitList iniList = (TK_InitList )ywlist ;
                iniList.setDj(init);
               for(int i = 0 ;i<listKc.size() ;i++){
                   if(listKc.get(i).getGoods().getId().longValue()==iniList.getL_goods().getId().longValue()
                           &&listKc.get(i).getSph().floatValue()==iniList.getSph().floatValue()
                           &&listKc.get(i).getCyl().floatValue()==iniList.getCyl().floatValue()
                           &&listKc.get(i).getStore().getId().longValue()==init.getY_store().getId().longValue()){
                       iniList.setL_kc(listKc.get(i));
                       break ;
                   }
               }
            }
            return (T)init ;
        }else if("NotLensInit".equals(tag)){
            TK_Init init = (TK_Init)bill ;
            List<TK_KC> listKc = new ArrayList<>(init.getDjmx().size()) ;
            TK_KC kc = null ;TG_Goods goods = null;
            /**每一条初始化单据的明细都有一个库存记录，非度数商品添加一次，就只有一个明细*/
            for(_YWList ywlist :init.getDjmx()){
                TK_InitList initList = (TK_InitList)ywlist ;
                if(goods==null) goods = initList.getL_goods() ;
                kc = new TK_KC() ;
                kc.setGoods(goods);
                kc.setCyl(initList.getCyl());
                kc.setSph(initList.getSph());
                kc.setCom(init.getY_com());
                kc.setStore(init.getY_store());
                listKc.add(kc) ;
            }
            this.goodsDao.saveAll_GoodsDao(listKc) ;

            /**设置单据状态与执行操作人*/
            init.setY_zx_user(zxUser);
            init.setZxTime(new Date());
            init.setY_bill_state(_YW.BillState.zx);
            this.goodsDao.update_GoodsDao(init) ;

            /**更新初始化单据中的库存数据 */
            init.getDjmx().get(0).setL_kc(listKc.get(0));
            this.goodsDao.update_GoodsDao(init.getDjmx().get(0)) ;
            return (T)init ;
        }else{
            Assertion.Error("GoodsService.executeBill param [tag] is not handler");
        }
        return null ;
    }



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

        //向下的逻辑需要分两种情况处理，度数商品，和非度数商品，
        this.addGoods_1_init_bill(goods, sessionTuken) ;
        this.goodsDao.addGoods_1_price(goods, vo) ;

        return goods ;
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

        /**添加默认入库单，确认入库单，入默认库存0 */
        //TODO 添加完成之后再做处理 用数据库批量处理
//        this.saveLens_6_init_lens_bill(goods, listLens, sessionTuken) ;
        /**添加默认价格*/
        this.goodsDao.saveLens_6_price(goods) ;

        actionResult.setZytz("修改度数配置会重置价格与库存，请重新盘点库存与设置价格");
    }

    /**
     * 默认非度数库存处理
     * @param goods
     * @param sessionTuken
     */
    private void addGoods_1_init_bill(TG_Goods goods, LoginTuken sessionTuken) throws Exception {
        /**添加商品时初始化库存*/
        TU_Com com = goods.getCom() ;
        TSYS_SetupCom sc = this.goodsDao.getUnique("From TSYS_SetupCom sc where sc.name='save_not_lens_add_kc' and sc.com.id=:id ", com) ;
        Long num = 0l ;
        if(LK.StrIsNotEmpty(sc.getExt1())){
            num = Long.parseLong(sc.getExt1()) ;
        }
        /**系统配置了添加商品时初始化库存才执行*/
        if(Boolean.parseBoolean(sc.getVal())){
            TG_GoodsTree kindNode = goods.getKind() ;
            /**只对现库级别并且非度数商品有效*/
            if(TG_Goods.KcJb.xk.ordinal()==goods.getKcjb().ordinal()&&!Boolean.valueOf(kindNode.getA1())){
                TK_YW yw = this.goodsDao.getUnique("From TK_YW yw where yw.bm=:bm", LKMap.create().putEx("bm", "0")) ;
                TU_User user = this.goodsDao.get(TU_User.class, sessionTuken.getId()) ;
                List<TU_Store> listStore = this.goodsDao.find("From TU_Store s where s.com.id=:id  and s.isHasKc=true ", com) ;
                TK_Init init = null ;List<_YWList> initLists = null ; TK_InitList initList = null ;
                /**每个需要有库存的站点执行*/
                for(TU_Store store :listStore){
                    init = new TK_Init(goods.getCom(),yw,store,user) ;
                    init.setY_zd_user(user);
                    this.goodsDao.save(init) ;

                    initLists = new ArrayList<>() ;
                    initList = new TK_InitList() ;
                    initList.setL_goods(goods);
                    initList.setL_num(num);
                    initLists.add(initList) ;
                    this.goodsDao.saveAll_GoodsDao(initLists) ;

                    init.setDjmx(initLists);

                    proxy.getInstance(this).createBill(init, user, "NotLensInit") ;
                    proxy.getInstance(this).affirmBill(init,user, "NotLensInit") ;
                    proxy.getInstance(this).executeBill(init,user, "NotLensInit") ;
                }
            }
        }
    }
    /**
     * 默认度数库存处理
     * @param goods
     * @param listLens
     * @param sessionTuken
     */
    private void saveLens_6_init_lens_bill(TG_Goods goods, List<TG_Lens> listLens, LoginTuken sessionTuken) throws Exception{
        /**添加度数商品时添加默认库存0*/
        TU_Com com = goods.getCom() ;
        TSYS_SetupCom sc = this.goodsDao.getUnique("From TSYS_SetupCom sc where sc.name='save_lens_add_kc' and sc.com.id=:id ", com) ;
        Long num = 0l ;
        if(LK.StrIsNotEmpty(sc.getExt1()))
            num = Long.parseLong(sc.getExt1()) ;

        if(Boolean.parseBoolean(sc.getVal())){
            /**只处理现库级别*/
            if(TG_Goods.KcJb.xk.ordinal()==goods.getKcjb().ordinal()){
                TK_YW yw = this.goodsDao.getUnique("From TK_YW yw where yw.bm=:bm", LKMap.create().putEx("bm", "0")) ;
                TU_User user = this.goodsDao.get(TU_User.class, sessionTuken.getId()) ;
                List<TU_Store> listStore = this.goodsDao.find("From TU_Store s where s.com.id=:id and s.isCenter=true and s.isHasKc=true ", com) ;
                TK_Init init = null ;List<_YWList> initLists = null ; TK_InitList initList = null ;
                /**是加工中心并且有库存的站点*/
                for(TU_Store store :listStore){
                    init = new TK_Init(goods.getCom(),yw,store,user) ;
                    init.setY_zd_user(user);
                    this.goodsDao.save(init) ;
                    initLists = new ArrayList<>(listLens.size()) ;
                    /**每一个度数都是一个库存记录*/
                    for(TG_Lens lens :listLens){
                        initList = new TK_InitList() ;
                        initList.setL_goods(goods);
                        initList.setL_num(num);
                        initList.setSph(lens.getSph());
                        initList.setCyl(lens.getCyl());
                        initList.setDj(init);
                        initLists.add(initList) ;
                    }
                    this.goodsDao.saveAll_GoodsDao(initLists) ;
                    init.setDjmx(initLists);

                    proxy.getInstance(this).createBill(init,user,"LensInit") ;
                    proxy.getInstance(this).affirmBill(init,user, "LensInit") ;
                    proxy.getInstance(this).executeBill(init,user, "LensInit") ;
                }
            }
        }
    }

    @Override
    public void saveLensDefVal_7(LoginTuken sessionTuken, ActionResult actionResult, VOId vo) throws Exception {
        TG_Goods goods = this.goodsDao.get(TG_Goods.class,vo.getId()) ;
        /**添加度数商品时添加默认库存0*/
        TU_Com com = goods.getCom() ;
        TSYS_SetupCom sc = this.goodsDao.getUnique("From TSYS_SetupCom sc where sc.name='save_lens_add_kc' and sc.com.id=:id ", com) ;
        Long num = 0l ;
        if(LK.StrIsNotEmpty(sc.getExt1()))
            num = Long.parseLong(sc.getExt1()) ;

        if(Boolean.parseBoolean(sc.getVal())){
            /**只处理现库级别*/
            if(TG_Goods.KcJb.xk.ordinal()==goods.getKcjb().ordinal()){
                TK_YW yw = this.goodsDao.getUnique("From TK_YW yw where yw.bm=:bm", LKMap.create().putEx("bm", "0")) ;
                TU_User user = this.goodsDao.get(TU_User.class, sessionTuken.getId()) ;
                List<TU_Store> listStore = this.goodsDao.find("From TU_Store s where s.com.id=:id and s.isCenter=true and s.isHasKc=true ", com) ;
                TK_Init init = null ;List<_YWList> initLists = null ; TK_InitList initList = null ;
                /**是加工中心并且有库存的站点*/
                for(TU_Store store :listStore){
                    init = new TK_Init(goods.getCom(),yw,store,user) ;
                    init.setY_zd_user(user);
                    this.goodsDao.save(init) ;
                    List<TK_InitList> listInitLists = this.goodsDao.saveLensDefVal_7_dbCopy_dj(goods.getId(), num, init.getId()) ;
                    init.setDjmx(listInitLists);
                    proxy.getInstance(this).createBill(init,user,"LensInit") ;
                    proxy.getInstance(this).affirmBill(init,user, "LensInit") ;
                    proxy.getInstance(this).executeBill(init,user, "LensInit") ;
                }
            }
        }
    }
}
