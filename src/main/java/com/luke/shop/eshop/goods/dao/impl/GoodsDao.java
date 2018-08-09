package com.luke.shop.eshop.goods.dao.impl;

import com.luke.shop.eshop.base.BaseDao;
import com.luke.shop.eshop.goods.dao.IGoodsDao;
import com.luke.shop.eshop.goods.vo.VOGoods;
import com.luke.shop.eshop.goods.vo.VOLens;
import com.luke.shop.model.*;
import com.luke.shop.tool.Assertion;
import com.luke.shop.tool.LK;
import com.luke.shop.tool.vo.VOId;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luke on 2018/7/27.
 */
@Component
public class GoodsDao extends BaseDao implements IGoodsDao {

    private static final Logger log = Logger.getLogger(GoodsDao.class) ;

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
                if(price.getPin()==null||price.getPin().intValue()==0){
                    Double pin = LK.ObjIsNull(sc.getExt1())?0.0:Double.parseDouble(sc.getExt1()) ;
                    price.setPin(pin);
                }
                if(price.getPout()==null||price.getPout().doubleValue()==0){
                    Double pout = LK.ObjIsNull(sc.getExt2())?0.0:Double.parseDouble(sc.getExt2()) ;
                    price.setPin(pout);
                }
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
    public void saveLens_6_price(TG_Goods goods) throws Exception {
        /**添加度数商品时添加添加默认价格0*/
        TU_Com com = goods.getCom() ;
        TSYS_SetupCom sc = this.getUnique("From TSYS_SetupCom sc where sc.name='save_lens_add_price' and sc.com.id=:id ", com) ;
        if(Boolean.valueOf(sc.getVal())){
            String pin = (String)LK.ObjIsNullDo(sc.getExt1(),"0") ;
            String pout = (String)LK.ObjIsNullDo(sc.getExt2(),"0") ;

            String mysqlJdbcInsertIntoSql = "insert into tg_price (b_isDel,b_wtime,goodsid,sph,cyl,pin,pout,priceType,comid)" +
                    "select false,now(),g.id,l.sph,l.cyl,"+pin+","+pout+",'normal',g.comId from tg_goods g left join tg_lens l on g.id = l.goodsid where g.id =?" ;
            log.info("GoodsDao.saveLens_6_price copy sql is :"+mysqlJdbcInsertIntoSql);
            log.info("goods.id is "+goods.getId());
            this.getJdbcTemplate().update(mysqlJdbcInsertIntoSql,new Object[]{goods.getId()}) ;
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
    public List<TK_InitBillMX> saveLensDefVal_7_dbCopy_dj(Long goodsId, Long num, Long djId) throws Exception {
        String mysqlJdbcInsertIntoSql = "insert into tk_initbillmx (b_isDel,b_wtime,l_goodsId,sph,cyl,l_num,djid)" +
                "select false,now(),l.goodsId,l.sph,l.cyl,"+num+","+djId+" from  tg_lens l where l.goodsid=?" ;
        log.info("GoodsDao.saveLensDefVal_7_dbCopy_dj copy sql is :"+mysqlJdbcInsertIntoSql);
        log.info("djid.id is "+djId);
        this.getJdbcTemplate().update(mysqlJdbcInsertIntoSql, new Object[]{goodsId}) ;
        return this.find("From TK_InitBillMX m where m.dj.id=:id",new VOId(djId)) ;
    }

    @Override
    public void saveDefVal_dbCopy_kc_ls(TK_InitBill initBill,String tag) throws Exception {
        /**保存库存*/
        String mysqlJdbcInsertIntoSql = "insert into tk_kc (b_isDel,b_wtime,goodsId,sph,cyl,comid,storeid,num_zheng_pin,num_can_pin,num_ci_pin,num_need,num_zeng_pin)" +
                "select false,now(),m.l_goodsid,m.sph,m.cyl,b.y_comid,y_storeId,m.l_num,0,0,0,0 from tk_initbill b left join tk_initbillmx m  on b.id = m.djid where b.id=?" ;
        log.info("GoodsDao.saveLensDefVal_dbCopy_kc copy kc sql is :"+mysqlJdbcInsertIntoSql);
        log.info("initBill.id is :"+initBill.getId());
        this.getJdbcTemplate().update(mysqlJdbcInsertIntoSql, new Object[]{initBill.getId()}) ;
        /**更新单据明细中的*/
        mysqlJdbcInsertIntoSql = "update tk_initbill b left join tk_initbillmx m  on b.id = m.djid left join tk_kc k on k.goodsId=m.l_goodsid "  ;
                if("LensInit".equals(tag)){
                    mysqlJdbcInsertIntoSql+= " and k.sph = m.sph and k.cyl = m.cyl " ;
                }
        mysqlJdbcInsertIntoSql+= "and k.storeid = b.y_storeid set m.l_kcid=k.id where b.id = ?" ;
        log.info("GoodsDao.saveLensDefVal_dbCopy_kc update initBillMX.kcid sql is :"+mysqlJdbcInsertIntoSql);
        log.info("initBill.id is :" + initBill.getId());
        this.getJdbcTemplate().update(mysqlJdbcInsertIntoSql, new Object[]{initBill.getId()}) ;
        /**批量输入流水*/
        mysqlJdbcInsertIntoSql = "insert into tk_ywls (b_isDel,b_wtime,goodsid,sph,cyl,kcid,ywid,ywtable,ywtableid,eidtnum,num_can_pin,num_ci_pin,num_zeng_pin,num_zheng_pin) " +
                "select false,now(),m.l_goodsId,m.sph,m.cyl,k.id,b.y_ywId,'TK_InitBill',"+initBill.getId()+",m.l_num,k.num_can_pin,k.num_ci_pin,k.num_zeng_pin,k.num_zheng_pin from tk_initbill b " +
                "left join tk_initbillmx m on b.id = m.djid " +
                "left join tk_kc k on k.id=m.l_kcid where b.id =?" ;
        log.info("GoodsDao.saveLensDefVal_dbCopy_ls sql is :"+mysqlJdbcInsertIntoSql);
        log.info("initBill.id is :" + initBill.getId());
        this.getJdbcTemplate().update(mysqlJdbcInsertIntoSql,new Object[]{initBill.getId()}) ;
    }

}
