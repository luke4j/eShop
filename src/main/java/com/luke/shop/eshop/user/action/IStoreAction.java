package com.luke.shop.eshop.user.action;

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

/**
 * Created by luke on 2018/8/14.
 */
@Api(value = "/store", description = "商品属性模块")
@RequestMapping("store")
public interface IStoreAction {

    String findAllStore = "查询所有站点" ;

    @ApiOperation(value = findAllStore)
    @RequestMapping(path = "findAllStore.act",method= RequestMethod.POST)
    @ResponseBody
    ActionResult findAllStore_1(HttpServletRequest request, HttpServletResponse response,
                            @ApiParam(value = findAllStore, required = true)  @Valid @RequestBody
                            VOEmpty vo,
                            BindingResult bindingResult, ActionResult actionResult)throws Exception ;

}
