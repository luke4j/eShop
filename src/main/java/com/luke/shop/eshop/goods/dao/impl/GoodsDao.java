package com.luke.shop.eshop.goods.dao.impl;

import com.luke.shop.eshop.base.BaseDao;
import com.luke.shop.eshop.base.dao.ISystemSetupComDao;
import com.luke.shop.eshop.goods.dao.IGoodsDao;
import com.luke.shop.eshop.goods.vo.VOGoods;
import com.luke.shop.eshop.goods.vo.VOGoodsEdit;
import com.luke.shop.eshop.goods.vo.VOLens;
import com.luke.shop.model.*;
import com.luke.shop.tool.AppMsgException;
import com.luke.shop.tool.Assertion;
import com.luke.shop.tool.LK;
import com.luke.shop.tool.LKMap;
import com.luke.shop.tool.vo.VOId;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luke on 2018/7/27.
 */
@Component
public class GoodsDao extends BaseDao implements IGoodsDao {

    private static final Logger log = Logger.getLogger(GoodsDao.class) ;

    @Resource
    ISystemSetupComDao scdao  ;


    @Override
    public Boolean goodsIsLens(Long goodsId) throws Exception {
        TG_Goods goods = this.get(TG_Goods.class, goodsId) ;
        if(LK.ObjIsNull(goods)) Assertion.Error("goods id is "+goodsId+"查询不到商品信息");
        return Boolean.parseBoolean(goods.getKind().getA1());
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
        /**做过业务的商品数据不能再次初始化*/
        List<TK_YWLS> lstYwls = this.find ("From TK_YWLS ywls where ywls.goods.id=:goodsId and ywls.yw.bm<>:ywbm ",new LKMap<String,Object>().putEx("goodsId",vo.getGoodsId()).putEx("ywbm","0")) ;
        if(lstYwls.size()>0)
            throw  AppMsgException.create("商品已经做过业务，不能初始化，如需要重新配置度数，请再建一个同名商品") ;

        this.delete_jdbc("delete from tg_lens where goodsId=?",vo.getGoodsId()) ;
        this.delete_jdbc("delete from tg_lenssetup where goodsId=?",vo.getGoodsId()) ;

        List<TK_InitBillMX> listInitBillMX = this.find("From TK_InitBillMX il where il.l_goods.id=:goodsId", vo) ;
        if(listInitBillMX.size()>0){
            TK_InitBillMX initList = listInitBillMX.get(0) ;
            this.delete_jdbc("delete from tk_initBillMX where l_goodsId=?", vo.getGoodsId());
            this.delete_jdbc("delete from tk_initBill where id=?", initList.getDj().getId());
        }
        this.delete_jdbc("delete from tk_ywls where goodsId=?", vo.getGoodsId());
        this.delete_jdbc("delete from tk_kc where goodsId=?", vo.getGoodsId());
    }





    @Override
    public List<TK_InitBillMX> saveLensDefVal_7_dbCopy_dj(Long goodsId, Long num, Long djId) throws Exception {
        String mysqlJdbcInsertIntoSql = "insert into tk_initbillmx (b_isDel,b_wtime,l_goodsId,sph,cyl,l_num,djid)" +
                "select false,now(),l.goodsId,l.sph,l.cyl,"+num+","+djId+" from  tg_lens l where l.goodsid=?" ;
        log.info("GoodsDao.saveLensDefVal_7_dbCopy_dj copy sql is :"+mysqlJdbcInsertIntoSql);
        log.info("djid.id is "+djId);
        this.getJdbcTemplate().update(mysqlJdbcInsertIntoSql, new Object[]{goodsId}) ;
        return this.find("From TK_InitBillMX m where m.dj.id=:id",new VOId(djId)) ;
    }

    @Override
    public void updateGoods(VOGoodsEdit vo) throws Exception {
        TG_Goods goods = this.get(TG_Goods.class,vo.getId()) ;
        BeanUtils.copyProperties(vo, goods);
        this.update(goods) ;
        if(this.goodsIsLens(goods.getId())){
            /**度数价格是在保存价格中操作的，这里不涉及*/
        }else{
            if(Boolean.parseBoolean(scdao.save_not_lens_add_price(goods.getCom().getId()).getVal())){
                /**更新价格是统一价格的站点，库存中指定Id的商品价格数据*/
                String sql = "update tk_kc kc left join tu_store s on kc.storeId=s.id set kc.pin=? ,kc.pout = ? where kc.goodsId = ? and s.isZhanDianJia = false" ;
                this.jdbcTemplateUpdate(sql,new Object[]{goods.getPin(),goods.getPout(),goods.getId()}) ;
            }
        }
    }

    @Override
    public void saveOrUpdateGoods(VOGoodsEdit vo) throws Exception {
        TG_GoodsAttr goodsAttr = this.getUnique("From TG_GoodsAttr ga where ga.goods.id=:goodsId",new LKMap<String,Object>().putEx("goodsId",vo.getId())) ;
        TG_Goods goods = this.get(TG_Goods.class, vo.getId());
        if(goods!=null&&goodsAttr!=null){
            Long gaId = goodsAttr.getId() ;
            BeanUtils.copyProperties(vo,goodsAttr);
            goodsAttr.setId(gaId);
            this.update(goodsAttr) ;
        }else if(goods!=null&&goodsAttr==null){
            TG_GoodsAttr ga = new TG_GoodsAttr() ;
            ga.setGoods(goods);
            BeanUtils.copyProperties(vo,ga);
            ga.setId(null);
            this.save(ga) ;
        }
    }
}
