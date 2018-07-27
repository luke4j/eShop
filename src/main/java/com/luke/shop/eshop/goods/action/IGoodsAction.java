package com.luke.shop.eshop.goods.action;

import com.luke.shop.eshop.goods.vo.VOGoods;
import com.luke.shop.eshop.goods.vo.VOGoodsEdit;
import com.luke.shop.tool.ActionResult;
import com.luke.shop.tool.vo.VOId;
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
 * Created by luke on 2018/7/27.
 */
@Api(value = "/goods", description = "商品")
@RequestMapping("goods")
public interface IGoodsAction {

    public final String addGoods = "添加商品信息" ;
    public final String editGoods = "修改商品信息" ;
    public final String delGoods = "软删除商品信息" ;
    public final String findGoods = "查询商品信息" ;


    /**
     * 商品属性-添加商品信息
     * @param request
     * @param response
     * @param vo
     * @param bindingResult
     * @param actionResult
     * @return
     * @throws Exception
     */
    @ApiOperation(value = addGoods)
    @RequestMapping(path = "addGoods.act",method= RequestMethod.POST)
    @ResponseBody
    ActionResult addGoods_1(HttpServletRequest request, HttpServletResponse response,
                            @ApiParam(value = addGoods, required = true)  @Valid @RequestBody
                            VOGoods vo,
                            BindingResult bindingResult, ActionResult actionResult)throws Exception ;

    @ApiOperation(value = editGoods)
    @RequestMapping(path = "editGoods.act",method= RequestMethod.POST)
    @ResponseBody
    ActionResult editGoods_2(HttpServletRequest request, HttpServletResponse response,
                            @ApiParam(value = addGoods, required = true)  @Valid @RequestBody
                            VOGoodsEdit vo,
                            BindingResult bindingResult, ActionResult actionResult)throws Exception ;

    @ApiOperation(value = delGoods)
    @RequestMapping(path = "delGoods.act",method= RequestMethod.POST)
    @ResponseBody
    ActionResult delGoods_3(HttpServletRequest request, HttpServletResponse response,
                             @ApiParam(value = delGoods, required = true)  @Valid @RequestBody
                             VOId vo,
                             BindingResult bindingResult, ActionResult actionResult)throws Exception ;

    @ApiOperation(value = findGoods)
    @RequestMapping(path = "findGoods.act",method= RequestMethod.POST)
    @ResponseBody
    ActionResult findGoods_4(HttpServletRequest request, HttpServletResponse response,
                             @ApiParam(value = findGoods, required = true)  @Valid @RequestBody
                             VOGoods vo,
                             BindingResult bindingResult, ActionResult actionResult)throws Exception ;

}
