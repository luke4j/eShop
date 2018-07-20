package com.luke.shop.eshop.goods.action;

import com.luke.shop.eshop.goods.vo.VOGoodsTreeAdd;
import com.luke.shop.tool.ActionResult;
import com.luke.shop.tool.vo.VOIdEmpty;
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
 * Created by luke on 2018/7/20.
 */
@Api(value = "/goodsTree", description = "程序登录")
@RequestMapping("goodsTree")
public interface IGoodsTreeAction {

    String findNode = "查询goodstree结点" ;
    String addNode = "添加goodstree结点" ;

    /**
     * 查询goodstree结点
     * @param request
     * @param response
     * @param vo
     * @param bindingResult
     * @param actionResult
     * @return
     * @throws Exception
     */
    @ApiOperation(value = findNode)
    @RequestMapping(path = "findNode.act",method= RequestMethod.POST)
    @ResponseBody
    ActionResult findNode_1(HttpServletRequest request, HttpServletResponse response,
                           @ApiParam(value = findNode, required = true)  @Valid @RequestBody
                           VOIdEmpty vo,
                            BindingResult bindingResult, ActionResult actionResult)throws Exception ;

    /**
     * 查询goodstree结点
     * @param request
     * @param response
     * @param vo
     * @param bindingResult
     * @param actionResult
     * @return
     * @throws Exception
     */
    @ApiOperation(value = addNode)
    @RequestMapping(path = "addNode.act",method= RequestMethod.POST)
    @ResponseBody
    ActionResult addNode_2(HttpServletRequest request, HttpServletResponse response,
                            @ApiParam(value = addNode, required = true)  @Valid @RequestBody
                            VOGoodsTreeAdd vo, BindingResult bindingResult, ActionResult actionResult)throws Exception ;

}
