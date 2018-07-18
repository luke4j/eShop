package com.luke.shop.eshop.base;

import com.luke.shop.tool.LoginTuken;

import javax.servlet.http.HttpServletRequest;

public class BaseAction {
    public LoginTuken getSessionTuken(HttpServletRequest request){
        return LoginTuken.getSessionTuken(request) ;
    }
}
