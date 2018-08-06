package com.luke.shop.eshop.goods.service.impl;

import com.luke.shop.eshop.base.BaseService;
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
import java.util.List;

/**
 * Created by luke on 2018/7/27.
 */
@Service
public class GoodsService extends BaseService implements IGoodsService {

    private static final Logger log = Logger.getLogger(GoodsService.class) ;

    @Resource
    IGoodsDao goodsDao ;

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
        this.goodsDao.addGoods_1_kc(goods,vo) ;
        this.goodsDao.addGoods_1_price(goods,vo) ;



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
        actionResult.setData(new LKMap<String,Object>().putEx("goodsLensSetup",goodsLensSetup).putEx("goodsLens",goodsLens));
    }


    @Override
    public void saveLens_6(LoginTuken sessionTuken, ActionResult actionResult, VOLens vo) throws Exception{
        JSONArray jsonGoodsLens = JSONArray.fromObject(vo.getLens()) ;
        TG_Lens[] arrayGoodsLens = (TG_Lens[]) JSONArray.toArray(jsonGoodsLens, TG_Lens.class) ;
        if(arrayGoodsLens.length>4500){
            Assertion.Error("度数配置超过4500，请分为近视，远视，混散");
        }

        TG_Goods goods = this.goodsDao.get(TG_Goods.class,vo.getGoodsId()) ;

        this.goodsDao.saveLens_6_delete(vo) ;
        /**保存度数配置*/
        TG_LensSetup lensSetup = new TG_LensSetup() ;
        BeanUtils.copyProperties(vo, lensSetup);
        lensSetup.setGoods(goods);
        this.goodsDao.save(lensSetup) ;

        List<TG_Lens> listLens = this.goodsDao.saveLens_6_allLens(lensSetup, goods, arrayGoodsLens) ;

        VOLensFindResult goodsLensFindResult = null ;
        List<VOLensFindResult> goodsLens = new ArrayList<>(listLens.size()) ;
        for(TG_Lens lens :listLens){
            goodsLensFindResult = new VOLensFindResult();
            BeanUtils.copyProperties(lens,goodsLensFindResult);
            goodsLens.add(goodsLensFindResult) ;
        }
        actionResult.setData(new LKMap<String, Object>().putEx("goodsLensSetup", lensSetup).putEx("goodsLens", goodsLens));
        /**添加默认库存 0 */
        if(goods.getKcjb().intValue()==0){
            this.goodsDao.saveLens_6_kc(goods) ;
        }
        this.goodsDao.saveLens_6_price(goods) ;


        actionResult.setZytz("修改度数配置会重置价格与库存，请重新盘点库存与设置价格");
    }
}
