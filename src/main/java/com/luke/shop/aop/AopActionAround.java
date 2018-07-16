package com.luke.shop.aop;


import com.luke.shop.tool.ActionResult;
import com.luke.shop.tool.Assertion;
import com.luke.shop.tool.L;
import com.luke.shop.tool.LoginTuken;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by luke on 2018/5/15.
 */
@Component
@Aspect
public class AopActionAround {

    private static Logger log = Logger.getLogger(AopActionAround.class);
    private static L l = L.getl(AopActionAround.class) ;

    @Pointcut("execution(* com.luke.shop.eshop.*.action.impl.*.*(..))")
    private void point() {
    }

    @Around("point()")
    public Object around(ProceedingJoinPoint jp) throws Throwable{
        ActionResult actionResult = null ;
        HttpServletRequest request = null ;
        HttpServletResponse response = null ;
        BindingResult bindingResult = null ;
        Object[] args = jp.getArgs();
        for(Object arg :args){
            if(arg instanceof ActionResult){
                actionResult = (ActionResult)arg ;
            }
            if(arg instanceof HttpServletRequest){
                request = (HttpServletRequest)arg ;
            }
            if(arg instanceof HttpServletResponse){
                response = (HttpServletResponse)arg ;
            }
            if(arg instanceof BindingResult){
                bindingResult = (BindingResult)arg ;
            }
        }

        if(bindingResult.hasErrors()){
            Assertion.Error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        if(request.getSession().getAttribute(LoginTuken.CurrentUser)!=null){
            LoginTuken tuken = (LoginTuken)request.getSession().getAttribute(LoginTuken.CurrentUser) ;
            actionResult.setLoginName(tuken.getLoginName());
            actionResult.setUserName(tuken.getName());
            actionResult.setUserId(tuken.getId());
            actionResult.setStoreName(tuken.getStoreName());
            actionResult.setStoreid(tuken.getStoreId());

        }

        actionResult.setJsonParams(JSONObject.fromObject(request.getParameterMap()).toString());
        actionResult.setUrl(request.getRequestURI());
        actionResult.setSuccess(true);

        log.info("======action===start====" + request.getRequestURI());

        Long start = new Date().getTime() ;
        Object obj = jp.proceed(args) ;
        StringBuffer info = new StringBuffer() ;
        info.append("\n").append("user is " + actionResult.getLoginName() + "\t user name is " + actionResult.getUserName() + "\tstore is " + actionResult.getStoreName() + "\tstore id is " + actionResult.getStoreid()) ;
        info.append("\n").append("page param is " + actionResult.getJsonParams()) ;
        info.append("\n").append("error is " + actionResult.getErrorMsg()) ;
        log.info(info.toString());
        Long end = new Date().getTime() ;
        log.info("======action===end====use time is " + (end - start) + "\t" + request.getRequestURI() + "\n\r\n\r");
        if((end-start)>2000){
            log.error("执行方法时间超2秒"+request.getRequestURI());
            log.error("用时："+(end-start)+"毫秒->:"+request.getRequestURI());
//            Assertion.Error("执行方法时间超2秒"+request.getRequestURI());
        }
        if(actionResult.getData()==null){
            actionResult.setData("操作成功");
        }
        return obj ;
    }
}
