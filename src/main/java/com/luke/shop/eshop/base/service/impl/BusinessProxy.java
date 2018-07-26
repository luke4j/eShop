package com.luke.shop.eshop.base.service.impl;

import com.luke.shop.eshop.base.BaseDao;
import com.luke.shop.eshop.base.dao.IProxyDao;
import com.luke.shop.eshop.base.service.IBusiness;
import com.luke.shop.tool.Assertion;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by luke on 2018/7/26.
 */
@Service
public class BusinessProxy {
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

                    if(method.getName().equals("createBill")){

                    }else if(method.getName().equals("affirmBill")){

                    }else if(method.getName().equals("executeBill")){

                    }else{
                        Assertion.Error("代理方法不存在");
                    }
                    return method.invoke(target,args);
                }) ;
        return (IBusiness)proxy ;
    }


}
