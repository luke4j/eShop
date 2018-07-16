package com.luke.shop.eshop.login.action;

import com.luke.shop.eshop.login.vo.VOLogin;
import com.luke.shop.tool.ActionResult;
import com.luke.shop.tool.vo.VOEmpty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Api(value = "/login", description = "程序登录")
@RequestMapping("login")
public interface ILoginAction {

    String gotoLogin = "去登录页面" ;
    String login = "用户登录" ;
    String getInfo = "获取登录信息" ;
    String logout = "用户登出" ;


    /**
     * gotoLogin.act<br>
     * 欢迎页面跳转到登录页面使用
     * @param request
     * @param response
     * @param vo
     * @param bindingResult
     * @param actionResult
     * @return
     * @throws Exception
     */
    @ApiOperation(value = gotoLogin)
    @RequestMapping(path = "gotoLogin.act",method= RequestMethod.GET)
    String gotoLogin_1(HttpServletRequest request, HttpServletResponse response,
                     @ApiParam(value = gotoLogin, required = true)  @Valid
                             VOEmpty vo, BindingResult bindingResult, ActionResult actionResult)throws Exception ;

    /**
     * 登录使用
     * @param request
     * @param response
     * @param vo
     * @param bindingResult
     * @param actionResult
     * @return
     * @throws Exception
     */
    @ApiOperation(value = login)
    @RequestMapping(path = "login.act",method= RequestMethod.GET)
    String login_2(HttpServletRequest request, HttpServletResponse response,
                   @ApiParam(value = login, required = true)  @Valid
                           VOLogin vo, BindingResult bindingResult, ActionResult actionResult)throws Exception ;

    @ApiOperation(value = getInfo)
    @RequestMapping(path = "getInfo.act",method= RequestMethod.GET)
    String getInfo_3(HttpServletRequest request, HttpServletResponse response,
                   @ApiParam(value = getInfo, required = true)  @Valid
                           VOLogin vo, BindingResult bindingResult, ActionResult actionResult)throws Exception ;


//    logout_4
    @ApiOperation(value = logout)
    @RequestMapping(path = "logout.act",method= RequestMethod.GET)
    String logout_4(HttpServletRequest request, HttpServletResponse response,
                     @ApiParam(value = logout, required = true)  @Valid
                             VOLogin vo, BindingResult bindingResult, ActionResult actionResult)throws Exception ;
}
