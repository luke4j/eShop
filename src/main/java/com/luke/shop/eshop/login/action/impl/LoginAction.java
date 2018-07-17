package com.luke.shop.eshop.login.action.impl;


import com.luke.shop.eshop.base.BaseAction;
import com.luke.shop.eshop.login.action.ILoginAction;
import com.luke.shop.eshop.login.service.ILoginService;
import com.luke.shop.eshop.login.vo.VOLogin;
import com.luke.shop.model.TU_Com;
import com.luke.shop.model.TU_Store;
import com.luke.shop.model.TU_User;
import com.luke.shop.tool.ActionResult;
import com.luke.shop.tool.LKMap;
import com.luke.shop.tool.LoginTuken;
import com.luke.shop.tool.vo.VOEmpty;
import com.luke.shop.tool.vo.VOIdName;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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
    public ActionResult login_2(HttpServletRequest request, HttpServletResponse response,
                                @ApiParam(value = login, required = true) @RequestBody @Valid
                                        VOLogin vo,
                                BindingResult bindingResult, ActionResult actionResult) throws Exception {

        LoginTuken tuken = loginService.findLogin_2(vo) ;
        LoginTuken.setSessionTuken(request,response,tuken);
        actionResult.setData(tuken);
        return actionResult;
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
