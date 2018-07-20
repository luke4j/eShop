package com.luke.shop.eshop.login.action;

import com.luke.shop.eshop.login.vo.VOLogin;
import com.luke.shop.eshop.login.vo.VOLoginEditPassword;
import com.luke.shop.tool.ActionResult;
import com.luke.shop.tool.vo.VOEmpty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Api(value = "/login", description = "程序登录")
@RequestMapping("login")
public interface ILoginAction {

    String gotoLogin = "去登录页面" ;
    String login = "用户登录" ;
    String getInfo = "获取登录信息" ;
    String logout = "主界面导航条->用户登出" ;
    String findCom = "查询所有公司，为登录页面公司下拉组件提供数据" ;
    String editPassword = "主界面导航条->修改密码" ;
    String getUserInfo =  "主界面导航条->用户信息" ;


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
    @RequestMapping(path = "login.act",method= RequestMethod.POST)
    @ResponseBody
    ActionResult login_2(HttpServletRequest request, HttpServletResponse response,
                   @ApiParam(value = login, required = true) @RequestBody @Valid
                           VOLogin vo, BindingResult bindingResult, ActionResult actionResult)throws Exception ;

    /**
     * 获取当前用户的详细信息,系统时间，当前操作人权限 ，信息，配置
     * @param request
     * @param response
     * @param vo
     * @param bindingResult
     * @param actionResult
     * @return
     * @throws Exception
     */
    @ApiOperation(value = getInfo)
    @RequestMapping(path = "getInfo.act",method= RequestMethod.POST)
    @ResponseBody
    ActionResult getInfo_3(HttpServletRequest request, HttpServletResponse response,
                   @ApiParam(value = getInfo, required = true)  @Valid
                           VOEmpty vo, BindingResult bindingResult, ActionResult actionResult)throws Exception ;


    /**
     * 用户登出
     * @param request
     * @param response
     * @param vo
     * @param bindingResult
     * @param actionResult
     * @return
     * @throws Exception
     */
    @ApiOperation(value = logout)
    @RequestMapping(path = "logout.act",method= RequestMethod.POST)
    @ResponseBody
    ActionResult logout_4(HttpServletRequest request, HttpServletResponse response,
                     @ApiParam(value = logout, required = true)  @Valid
                             VOEmpty vo, BindingResult bindingResult, ActionResult actionResult)throws Exception ;

    /**
     * 登录查询所有公司
     * @param request
     * @param response
     * @param vo
     * @param bindingResult
     * @param actionResult
     * @return
     * @throws Exception
     */
    @ApiOperation(value = findCom)
    @RequestMapping(path = "findCom.act",method= RequestMethod.POST)
    @ResponseBody
    ActionResult findCom_5(HttpServletRequest request, HttpServletResponse response,
                    @ApiParam(value = findCom, required = true)  @Valid
                            VOEmpty vo, BindingResult bindingResult, ActionResult actionResult)throws Exception ;


    /**
     * 导航条修改密码
     * @param request
     * @param response
     * @param vo
     * @param bindingResult
     * @param actionResult
     * @return
     * @throws Exception
     */
    @ApiOperation(value = editPassword)
    @RequestMapping(path = "editPassword.act",method= RequestMethod.POST)
    @ResponseBody
    ActionResult editPassword_6(HttpServletRequest request, HttpServletResponse response,
                                @ApiParam(value = editPassword, required = true)  @Valid @RequestBody
                                        VOLoginEditPassword vo, BindingResult bindingResult, ActionResult actionResult)throws Exception ;

    /**
     * 导航条用户信息显示
     * @param request
     * @param response
     * @param vo
     * @param bindingResult
     * @param actionResult
     * @return
     * @throws Exception
     */
    @ApiOperation(value = getUserInfo)
    @RequestMapping(path = "getUserInfo.act",method= RequestMethod.POST)
    @ResponseBody
    ActionResult getUserInfo_7(HttpServletRequest request, HttpServletResponse response,
                                @ApiParam(value = getUserInfo, required = true)  @Valid
                                VOEmpty vo, BindingResult bindingResult, ActionResult actionResult)throws Exception ;
}
