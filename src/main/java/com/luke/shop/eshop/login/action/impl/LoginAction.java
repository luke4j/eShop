package com.luke.shop.eshop.login.action.impl;


import com.luke.shop.eshop.login.action.ILoginAction;
import com.luke.shop.eshop.login.vo.VOLogin;
import com.luke.shop.tool.ActionResult;
import com.luke.shop.tool.vo.VOEmpty;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginAction implements ILoginAction {
    @Override
    public String gotoLogin_1(HttpServletRequest request, HttpServletResponse response, VOEmpty vo, BindingResult bindingResult, ActionResult actionResult) throws Exception {
        return "index";
    }

    @Override
    public String login_2(HttpServletRequest request, HttpServletResponse response, VOLogin vo, BindingResult bindingResult, ActionResult actionResult) throws Exception {
        return null;
    }

    @Override
    public String getInfo_3(HttpServletRequest request, HttpServletResponse response, VOLogin vo, BindingResult bindingResult, ActionResult actionResult) throws Exception {
        return null;
    }

    @Override
    public String logout_4(HttpServletRequest request, HttpServletResponse response, VOLogin vo, BindingResult bindingResult, ActionResult actionResult) throws Exception {
        return null;
    }
}
