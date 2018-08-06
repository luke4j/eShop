package com.luke.shop.eshop.goods.dao.impl;

import com.luke.shop.eshop.base.BaseDao;
import com.luke.shop.eshop.goods.dao.IGoodsDao;
import com.luke.shop.eshop.goods.vo.VOGoods;
import com.luke.shop.eshop.goods.vo.VOLens;
import com.luke.shop.model.*;
import com.luke.shop.tool.Assertion;
import com.luke.shop.tool.LKMap;
import com.luke.shop.tool.LoginTuken;
import com.luke.shop.tool.vo.VOId;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luke on 2018/7/27.
 */
@Component
public class GoodsDao extends BaseDao implements IGoodsDao {

    @Override
    public void addGoods_1_kc(TG_Goods goods, VOGoods vo) throws Exception {
        TSYS_SetupCom sc = this.getUnique("From TSYS_SetupCom sc where sc.name='添加商品时初始化库存' and sc.com.id=:id ", goods.getCom()) ;
        if(Boolean.valueOf(sc.getVal())){
            TG_GoodsTree kind = goods.getKind() ;
            /**非度数商品，现库*/
            if((!Boolean.valueOf(kind.getA1()))&&goods.getKcjb().intValue()==1){
                this.addGoods_1_kc_notLens_kc(goods.getCom(), goods);
            }
            /**度数商品，现库，在保存度数时处理*/
            /**非度数商品，零订，车房 ，不保存库存*/
            /**度数商品，零订，车房，不保存库存*/
        }
    }
    private void addGoods_1_kc_notLens_kc(TU_Com com, TG_Goods goods) throws Exception {
        TSYS_SetupCom sc = this.getUnique("From TSYS_SetupCom sc where sc.name='添加商品时是否添加价格' and sc.com.id=:id ", com) ;
        if(Boolean.valueOf(sc.getVal())){
            List<TU_Store> listStore = this.find("From TU_Store s where s.com.id=:comId and s.isHasKc=true and s.b_isDel=false", new LKMap<String,Object>().putEx("comId",com.getId())) ;
            List<TK_KC> listKc = new ArrayList<TK_KC>(listStore.size()) ;
            TK_KC kc = null ;
            for(TU_Store store :listStore){
                kc = new TK_KC() ;
                kc.setStore(store);
                kc.setCom(com);
                kc.setGoods(goods);
                listKc.add(kc) ;
            }
            this.saveAll(listKc) ;
        }
    }

    @Override
    public TG_Price addGoods_1_price(TG_Goods goods, VOGoods vo) throws Exception {
        TU_Com com = goods.getCom() ;
        TSYS_SetupCom sc = this.getUnique("From TSYS_SetupCom sc where sc.name='添加商品时是否添加价格' and sc.com.id=:id ",com) ;
        if(Boolean.valueOf(sc.getVal())){
            TG_Price price = new TG_Price() ;
            BeanUtils.copyProperties(vo, price);
            price.setCom(com);
            price.setGoods(goods);
            price.setPriceType(TG_Price.PriceType.normal);
            this.save(price) ;
            return price ;
        }else{
            return null ;
        }

    }



    @Override
    public TG_LensSetup getGoodsLens_5_lensSetup(VOId vo) throws Exception {
        return this.getUnique("From TG_LensSetup ls where ls.goods.id=:id",vo);
    }

    @Override
    public List<TG_Lens> getGoodsLens_5_lens(VOId vo) throws Exception {
        return this.find("From TG_Lens l where l.goods.id=:id",vo);
    }

    @Override
    public List<TG_Lens> saveLens_6_allLens(TG_LensSetup lensSetup,TG_Goods goods, TG_Lens[] arrayGoodsLens) throws Exception {
        List<TG_Lens> listLens = new ArrayList<TG_Lens>(arrayGoodsLens.length) ;
        for(TG_Lens lens:arrayGoodsLens){
            lens.setGoods(goods);
            lens.setLensSetup(lensSetup);
            listLens.add(lens) ;
        }
        this.saveAll(listLens) ;
        return listLens;
    }

    @Override
    public void saveLens_6_delete(VOLens vo) throws Exception {
        this.delete_ql("delete from TG_Price p where p.goods.id=:goodsId",vo) ;
        this.delete_ql("delete from TK_KC k where k.goods.id=:goodsId",vo) ;
        this.delete_ql("delete from TG_Lens l where l.goods.id=:goodsId",vo) ;
        this.delete_ql("delete from TG_LensSetup s where s.goods.id=:goodsId",vo) ;
    }

    @Override
    public void saveLens_6_kc(TG_Goods goods) throws Exception {
        TU_Com com = goods.getCom() ;
        TSYS_SetupCom sc = this.getUnique("From TSYS_SetupCom sc where sc.name='添加商品时初始化库存' and sc.com.id=:id ", com) ;
        /**系统配置是否添加初始化时添加默认库存*/
        if(Boolean.valueOf(sc.getVal())) {
            List<TU_Store> listStore = this.find("From TU_Store s where s.com.id=:id ", com) ;
            List<TG_Lens> listLens = this.find("From TG_Lens l where l.goods.id=:id",goods) ;

            for(TU_Store store :listStore){
                /**是加工中心并且是有库存的站点添加默认库存*/
                if(store.getIsCenter()&&store.getIsHasKc()){
                    List<TK_KC> listKc = new ArrayList<TK_KC>(listLens.size()) ;
                    TK_KC kc = null ;
                    /**每一个度数添加库存*/
                    for(TG_Lens lens :listLens){
                        kc = new TK_KC() ;
                        kc.setGoods(goods);
                        kc.setStore(store);
                        kc.setCom(com);
                        kc.setSph(lens.getSph());
                        kc.setCyl(lens.getCyl());
                        kc.setNum_can_pin(0l);
                        kc.setNum_ci_pin(0l);
                        kc.setNum_need(0l);
                        kc.setNum_zeng_pin(0l);
                        kc.setNum_zheng_pin(0l);
                        listKc.add(kc) ;
                    }
                    this.saveAll(listKc) ;
                }
            }
        }
    }

    @Override
    public void saveLens_6_price(TG_Goods goods) throws Exception {
        TU_Com com = goods.getCom() ;
        TSYS_SetupCom sc = this.getUnique("From TSYS_SetupCom sc where sc.name='添加商品时初始化价格' and sc.com.id=:id ", com) ;
        if(Boolean.valueOf(sc.getVal())){
            List<TG_Lens> listLens = this.find("From TG_Lens l where l.goods.id=:id",goods) ;
            TG_Price price = null ;
            List<TG_Price> listPrice = new ArrayList<>(listLens.size()) ;
            for(TG_Lens lens:listLens){
                price = new TG_Price() ;
                price.setGoods(goods);
                price.setCom(com);
                price.setPin(0.0);
                price.setPout(0.0);
                price.setSph(lens.getSph());
                price.setCyl(lens.getCyl());
                price.setPriceType(TG_Price.PriceType.normal);
                listPrice.add(price) ;
            }
            this.saveAll(listPrice) ;
        }
    }
}
