package com.luke.shop.eshop.base.service.impl;

import com.luke.shop.eshop.base.dao.IProxyDao;
import com.luke.shop.eshop.base.service.IBusiness;
import com.luke.shop.eshop.goods.vo.VOKCNum;
import com.luke.shop.model.TK_YWLS;
import com.luke.shop.model._YW;
import com.luke.shop.model._YWList;
import com.luke.shop.tool.Assertion;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luke on 2018/7/26.
 */
@Service
public class BusinessProxy {

    private static final Logger log = Logger.getLogger(BusinessProxy.class) ;

    @Resource
    IProxyDao dao  ;

    /**
     * 单据包括 进货单，配送单，销售单，报残单，退货单，返厂单，盘亏单，盘盈单，次转正单，正转次单，次转残单，残转次单<br>
     * 制单是数据库中有了单据数据 ，未对库存修改<br>
     * 单据确认是操作人签字确认 ,未对库存修改<br>
     * 单据执行是库存产生变化<br>
     * */
    public IBusiness getInstance(IBusiness target){

        Assertion.NotEmpty(target,"代理对象为空");
        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                (proxy1, method, args) -> {
                    /**执行相应业务*/
                    Object obj = method.invoke(target,args);
                    /**完成之后在执行时记录流水*/
                    if(method.getName().equals("createBill")){
                        log.info("=============createBill================");
                    }else if(method.getName().equals("affirmBill")){
                        log.info("=============affirmBill================");
                    }else if(method.getName().equals("executeBill")){
                        log.info("=============executeBill================");
                        log.info("=============业务真正执行后流水记录================");
                        for(Object arg :args){
                            if(arg instanceof _YW){
                                _YW yw = (_YW)arg ;
                                TK_YWLS ywls = null ;List<TK_YWLS> listLs = new ArrayList<TK_YWLS>(yw.getDjmx().size()) ;
                                for(_YWList mx :yw.getDjmx()){
                                    ywls = new TK_YWLS() ;
                                    ywls.setYw(yw.getY_yw());
                                    ywls.setYwTable(yw.getClass().getSimpleName());
                                    ywls.setYwTableId(yw.getId());
                                    ywls.setKc(mx.getL_kc());
                                    ywls.setGoods(mx.getL_goods());
                                    ywls.setSph(mx.getSph());
                                    ywls.setCyl(mx.getCyl());
                                    VOKCNum kcNum = new VOKCNum() ;
                                    BeanUtils.copyProperties(mx.getL_kc(),kcNum);
                                    BeanUtils.copyProperties(kcNum, ywls);
                                    ywls.setEidtNum(mx.getL_num());
                                    listLs.add(ywls) ;
                                }
                                this.dao.saveAll_proxyDao(listLs) ;
                                break ;
                            }
                        }
                    }else{
                        Assertion.Error("代理方法不存在："+method.getName());
                    }


                    return obj ;
                }) ;
        return (IBusiness)proxy ;
    }


}
