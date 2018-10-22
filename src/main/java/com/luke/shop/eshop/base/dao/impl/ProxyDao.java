package com.luke.shop.eshop.base.dao.impl;

import com.luke.shop.eshop.base.BaseDao;
import com.luke.shop.eshop.base.dao.IProxyDao;
import com.luke.shop.model.*;
import com.luke.shop.tool.Assertion;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by luke on 2018/7/26.
 */
@Component
public class ProxyDao extends BaseDao implements IProxyDao {

    private static final Logger log = Logger.getLogger(ProxyDao.class) ;

    @Override
    public void saveAll_proxyDao(List listLs) throws Exception {
        super.saveAll(listLs) ;
    }

    @Override
    public void update_proxyDao(Object obj) throws Exception {
        super.updateObj(obj) ;
    }

    @Override
    public String save_ls_kc(_YW yw, TU_User user, String yw_tag,Boolean isLens)throws Exception {
        log.info("业务标志：" + yw_tag);
        switch (yw_tag){
            /**因初始化时数据与sql语句不能使用通用模板，所以只能写*/
            case "TK_InitBill" :
                if(yw instanceof TK_InitBill ){
                    TK_InitBill bill = (TK_InitBill)yw ;

                    /**保存库存*/
                    String mysqlJdbcInsertIntoSql = "insert into tk_kc (b_isDel,b_wtime,goodsId,sph,cyl,comid,storeid,num_zheng_pin,num_can_pin,num_ci_pin,num_need,num_zeng_pin,pin,pout)" ;
                    if(isLens){
                        String priceInSql = "(select t.ext1 from tsys_setupcom t where t.name = 'save_lens_add_price' and t.comId=?)" ;
                        String priceOutSql = "(select t.ext2 from tsys_setupcom t where t.name = 'save_lens_add_price' and t.comId=?)" ;
                        mysqlJdbcInsertIntoSql+= "select false,now(),m.l_goodsid,m.sph,m.cyl,b.y_comid,y_storeId,m.l_num,0,0,0,0, isnull(lens.pin,"+priceInSql+",0),isnull(lens.pout,"+priceOutSql+",0) from tk_initbill b left join tk_initbillmx m  on b.id = m.djid  left join tg_lens lens on lens.goodsId=m.l_goodsId and lens.sph=m.sph and lens.cyl=m.cyl  where b.id=?" ;
                    }else{
                        String priceInSql = "(select t.ext1 from tsys_setupcom t where t.name = 'save_not_lens_add_price' and t.comId=?)" ;
                        String priceOutSql = "(select t.ext2 from tsys_setupcom t where t.name = 'save_not_lens_add_price' and t.comId=?)" ;
                        mysqlJdbcInsertIntoSql+= "select false,now(),m.l_goodsid,m.sph,m.cyl,b.y_comid,y_storeId,m.l_num,0,0,0,0, isnull(g.pin,"+priceInSql+",0),isnull(g.out,"+priceOutSql+",0) from tk_initbill b left join tk_initbillmx m  on b.id = m.djid left join tg_goods g on g.id = m.l_goodsId where b.id=?" ;
                    }
                    this.getJdbcTemplate().update(mysqlJdbcInsertIntoSql, new Object[]{user.getCom().getId(),user.getCom().getId(),bill.getId()}) ;

                    String sphAndCyl = " and k.sph = m.sph and k.cyl = m.cyl " ;
                    /**由库存再更新单据明细所关联的库存id*/
                    mysqlJdbcInsertIntoSql = "update tk_initbill b left join tk_initbillmx m  on b.id = m.djid left join tk_kc k on k.goodsId=m.l_goodsid "+(isLens?sphAndCyl:"")+" and k.storeid = b.y_storeid set m.l_kcid=k.id where b.id = ?"  ;
                    this.jdbcTemplateUpdate(mysqlJdbcInsertIntoSql,new Object[]{bill.getId()}) ;
                    /**写流水*/
                    mysqlJdbcInsertIntoSql = "insert into tk_ywls (b_isDel,b_wtime,goodsid,sph,cyl,kcid,ywid,ywtable,ywtableid,eidtnum,num_can_pin,num_ci_pin,num_zeng_pin,num_zheng_pin) " +
                            "select false,now(),m.l_goodsId,m.sph,m.cyl,k.id,b.y_ywId,'"+TK_InitBill.class.getSimpleName()+"',"+bill.getId()+",m.l_num,k.num_can_pin,k.num_ci_pin,k.num_zeng_pin,k.num_zheng_pin from "+TK_InitBill.class.getSimpleName()+" b " +
                            "left join "+TK_InitBillMX.class.getSimpleName()+" m on b.id = m.djid " +
                            "left join tk_kc k on k.id=m.l_kcid where b.id =?" ;
                    this.jdbcTemplateUpdate(mysqlJdbcInsertIntoSql, new Object[]{bill.getId()}) ;
                }else{
                    Assertion.Error("开发逻辑异常：ProxyDao.save_ls_kc type " + yw.getClass().getSimpleName());
                }
                break;


            default:
                Assertion.Error("未确认业务："+yw_tag);
                break ;
        }
        return null;
    }
    private String save_ls_kc_template(String yw_table,String ywmx_table ,Long yw_id,TK_YW yw,boolean isLens)throws Exception{

        String numName = null ;
        switch (yw.getKind()){
            case "正品":
                numName = " num_zheng_pin " ;
                break ;
            case "次品":
                numName = " num_ci_pin " ;
                break ;
            case "残品":
                numName = " num_can_pin " ;
                break ;
            case "赠品":
                numName = " num_zeng_pin " ;
                break ;
            default:
                Assertion.Error("数据库tk_yw.kind字段值异常："+yw.getKind());
                break ;
        }

        String sphAndCyl = " and k.sph = m.sph and k.cyl = m.cyl " ;
        log.info("业务更新库存：");
        String mysqlJdbcInsertIntoSql = "update "+yw_table+" b left join "+ywmx_table+" m on b.id = m.djid left join  tk_kc k on k.goodsid = m.l_goodsid "+(isLens?sphAndCyl:"")+" and k.storeid = b.y_storeid " +
                "set k."+numName+"=k."+numName+" "+yw.getOpt()+" m.l_num,m.l_kcid=k.id where b.id=?" ;
        this.jdbcTemplateUpdate(mysqlJdbcInsertIntoSql, new Object[]{yw_id}) ;

        mysqlJdbcInsertIntoSql = "insert into tk_ywls (b_isDel,b_wtime,goodsid,sph,cyl,kcid,ywid,ywtable,ywtableid,eidtnum,num_can_pin,num_ci_pin,num_zeng_pin,num_zheng_pin) " +
                "select false,now(),m.l_goodsId,m.sph,m.cyl,k.id,b.y_ywId,'"+yw_table+"',"+yw_id+",m.l_num,k.num_can_pin,k.num_ci_pin,k.num_zeng_pin,k.num_zheng_pin from "+yw_table+" b " +
                "left join "+ywmx_table+" m on b.id = m.djid " +
                "left join tk_kc k on k.id=m.l_kcid where b.id =?" ;
        this.jdbcTemplateUpdate(mysqlJdbcInsertIntoSql,new Object[]{yw_id}) ;

        return "success" ;
    }


}
