package com.luke.shop.eshop.goods.dao.impl;

import antlr.collections.impl.LList;
import com.luke.shop.eshop.base.BaseDao;
import com.luke.shop.eshop.goods.dao.IGoodsDao;
import com.luke.shop.eshop.goods.vo.VOGoods;
import com.luke.shop.eshop.goods.vo.VOLens;
import com.luke.shop.model.*;
import com.luke.shop.tool.LKMap;
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
        TG_GoodsTree kindNode = goods.getKind() ;
        if(!Boolean.valueOf(kindNode.getA1())){
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
            }
        }
        return null ;
    }



    @Override
    public TG_LensSetup getGoodsLens_5_lensSetup(VOId vo) throws Exception {
        return this.getUnique("From TG_LensSetup ls where ls.goods.id=:id", vo);
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
        this.delete_ql("delete from TG_Price p where p.goods.id=:goodsId", vo) ;
        this.delete_ql("delete from TG_Lens l where l.goods.id=:goodsId",vo) ;
        this.delete_ql("delete from TG_LensSetup s where s.goods.id=:goodsId",vo) ;

        List<TK_Init> list = this.find("select i from TK_Init i left join TK_InitList il on i.id = il.dj.id where il.l_goods.id=:goodsId ",vo) ;
        this.delete_ql("delete from TK_InitList il where il.l_goods.id=:goodsId",vo) ;
        Long tk_initId = null ;
        if(list!=null&&list.size()>=0){
            tk_initId = list.get(0).getId() ;
        }
        this.delete_ql("delete from TK_Init i where i.id=:id",new VOId(tk_initId)) ;
        this.delete_ql("delete from TK_YWLS ls where ls.goods.id=:goodsId",vo) ;
        this.delete_ql("delete from TK_KC k where k.goods.id=:goodsId",vo) ;

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

    @Override
    public <T> T update_GoodsDao(T  t) throws Exception {
        return this.update(t) ;
    }

    @Override
    public List saveAll_GoodsDao(List listKc) throws Exception {
        return super.saveAll(listKc);
    }


}
