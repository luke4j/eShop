package com.luke.shop.eshop.goods.dao.impl;

import antlr.collections.impl.LList;
import com.luke.shop.eshop.base.BaseDao;
import com.luke.shop.eshop.goods.dao.IGoodsDao;
import com.luke.shop.eshop.goods.vo.VOGoods;
import com.luke.shop.eshop.goods.vo.VOLens;
import com.luke.shop.model.*;
import com.luke.shop.tool.Assertion;
import com.luke.shop.tool.LK;
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


    @Override
    public TG_Price addGoods_1_price(TG_Goods goods, VOGoods vo) throws Exception {
        TG_GoodsTree kindNode = goods.getKind() ;
        if(!Boolean.valueOf(kindNode.getA1())){
            TU_Com com = goods.getCom() ;
            TSYS_SetupCom sc = this.getUnique("From TSYS_SetupCom sc where sc.name='save_not_lens_add_price' and sc.com.id=:id ",com) ;
            if(Boolean.valueOf(sc.getVal())){
                TG_Price price = new TG_Price() ;
                BeanUtils.copyProperties(vo, price);
                price.setCom(com);
                price.setGoods(goods);
                price.setPriceType(TG_Price.PriceType.normal);
                if(LK.StrIsNotEmpty(sc.getExt1())) price.setPin(Double.parseDouble(sc.getExt1()));
                if(LK.StrIsNotEmpty(sc.getExt2())) price.setPout(Double.parseDouble(sc.getExt2()));
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
        Assertion.NotEmpty(vo.getGoodsId(), "商品ID为空，不能保存度数");
        this.delete_jdbc("delete from tg_price where goodsId=?", vo.getGoodsId()) ;
        this.delete_jdbc("delete from tg_lens where goodsId=?",vo.getGoodsId()) ;
        this.delete_jdbc("delete from tg_lenssetup where goodsId=?",vo.getGoodsId()) ;
        this.delete_jdbc("delete from tg_goodsattr where goodsId=?", vo.getGoodsId()) ;

        List<TK_InitList> listInitLists = this.find("From TK_InitList il where il.l_goods.id=:goodsId", vo) ;
        if(listInitLists.size()>0){
            TK_InitList initList = listInitLists.get(0) ;
            this.delete_jdbc("delete from tk_initlist where l_goodsId=?", vo.getGoodsId());
            this.delete_jdbc("delete from tk_init where id=?", initList.getDj().getId());
        }
        this.delete_jdbc("delete from tk_ywls where goodsId=?", vo.getGoodsId());
        this.delete_jdbc("delete from tk_kc where goodsId=?", vo.getGoodsId());
    }



    @Override
    public void saveLens_6_price(TG_Goods goods) throws Exception {
        /**添加度数商品时添加添加默认价格0*/
        TU_Com com = goods.getCom() ;
        TSYS_SetupCom sc = this.getUnique("From TSYS_SetupCom sc where sc.name='save_lens_add_price' and sc.com.id=:id ", com) ;
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
                if(LK.StrIsNotEmpty(sc.getExt1())) price.setPin(Double.parseDouble(sc.getExt1()));
                if(LK.StrIsNotEmpty(sc.getExt2())) price.setPout(Double.parseDouble(sc.getExt2()));
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

    @Override
    public List<TK_InitList> saveLensDefVal_7_dbCopy_dj(Long goodsId, Long num, Long djId) throws Exception {
        String mysqlJdbcInsertIntoSql = "insert into tk_initlist (b_isDel,b_wtime,l_goodsId,sph,cyl,l_num,djid)" +
                "select false,now(),l.goodsId,l.sph,l.cyl,"+num+","+djId+" from  tg_lens l where l.goodsid=?" ;

        this.getJdbcTemplate().update(mysqlJdbcInsertIntoSql, new Object[]{goodsId}) ;
        return this.find("From TK_InitList il where il.dj.id=:id",new VOId(djId)) ;
    }
}
