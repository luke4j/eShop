package com.luke.shop.eshop.base.service.impl;

import com.luke.shop.eshop.base.BaseService;
import com.luke.shop.eshop.base.dao.IProxyDao;
import com.luke.shop.eshop.base.service.IBusiness;
import com.luke.shop.eshop.goods.vo.VOKCNum;
import com.luke.shop.model.TK_YWLS;
import com.luke.shop.model.TU_User;
import com.luke.shop.model._YW;
import com.luke.shop.model._YWMX;
import com.luke.shop.tool.Assertion;
import com.luke.shop.tool.LK;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Date;
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
                    _YW ywdj = null ;
                    List<_YWMX> listYwmx = null ;
                    TU_User user = null ;
                    String yw_tag = null ;
                    Boolean isLens = false ;
                    Object obj = method.invoke(target, args);

                    for(Object arg :args){
                        if(arg instanceof String){
                            yw_tag = (String)arg ;
                        }
                        if(arg instanceof List){
                            listYwmx = (List<_YWMX>)arg ;
                        }
                        if(arg instanceof TU_User){
                            user = (TU_User) arg ;
                        }
                        if(arg instanceof _YW){
                            ywdj = (_YW)arg ;
                        }
                        if(arg instanceof Boolean){
                            isLens = (Boolean)arg ;
                        }
                    }
                    /**执行相应业务*/
                    /**完成之后在执行时记录流水*/
                    if(method.getName().equals("createBill")){
                        log.info("=============createBill================");
                        log.info("=============单据已保存================");
                        log.info("=============单据明细已保存================");
                    }else if(method.getName().equals("affirmBill")){
                        log.info("=============affirmBill================");
                        log.info("=============添加确认处理人与修改单据确认状态================");
                        this.dao.update_proxyDao(ywdj);
                    }else if(method.getName().equals("executeBill")) {
                        log.info("=============executeBill================");
                        log.info("=============添加执行处理人与修改单据确认状态================");
                        this.dao.update_proxyDao(ywdj);
                        log.info("=============修改库存与保存流水================");
                        this.dao.save_ls_kc(ywdj,user,yw_tag,isLens) ;
                    }else{
                        Assertion.Error("代理方法不存在："+method.getName());
                    }
                    return obj ;
                }) ;
        return (IBusiness)proxy ;
    }


}
