package com.luke.shop.eshop.login.action.impl;


import com.luke.shop.eshop.base.BaseAction;
import com.luke.shop.eshop.login.action.ILoginAction;
import com.luke.shop.eshop.login.service.ILoginService;
import com.luke.shop.eshop.login.vo.VOLogin;
import com.luke.shop.model.TU_Com;
import com.luke.shop.tool.ActionResult;
import com.luke.shop.tool.vo.VOEmpty;
import com.luke.shop.tool.vo.VOIdName;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class LoginAction extends BaseAction implements ILoginAction {

    @Resource
    private ILoginService loginService ;

    @Override
    public String gotoLogin_1(HttpServletRequest request, HttpServletResponse response, VOEmpty vo, BindingResult bindingResult, ActionResult actionResult) throws Exception {
        return "index";
    }

    @Override
    public ActionResult login_2(HttpServletRequest request, HttpServletResponse response, VOLogin vo, BindingResult bindingResult, ActionResult actionResult) throws Exception {
        return null;
    }

    @Override
    public ActionResult getInfo_3(HttpServletRequest request, HttpServletResponse response, VOLogin vo, BindingResult bindingResult, ActionResult actionResult) throws Exception {
        return null;
    }

    @Override
    public ActionResult logout_4(HttpServletRequest request, HttpServletResponse response, VOLogin vo, BindingResult bindingResult, ActionResult actionResult) throws Exception {
        return null;
    }

    @Override
    public ActionResult findCom_5(HttpServletRequest request, HttpServletResponse response, VOEmpty vo, BindingResult bindingResult, ActionResult actionResult) throws Exception {
        actionResult.setDoing(findCom);
        List<VOIdName> listCom = this.loginService.findCom_5() ;
        actionResult.setData(listCom);
        return actionResult;
    }
}
