package com.luke.shop.eshop.base;

import com.luke.shop.tool.Assertion;
import com.luke.shop.tool.LoginTuken;

import javax.servlet.http.HttpServletRequest;

public class BaseAction {
    public LoginTuken getSessionTuken(HttpServletRequest request){
        LoginTuken tuken = LoginTuken.getSessionTuken(request) ;
        Assertion.NotEmpty(tuken,"请重新登录");
        return tuken ;
    }
}
