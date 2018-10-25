package com.luke.shop.eshop.goods.action;

import com.luke.shop.tool.ActionResult;
import com.luke.shop.tool.vo.VOId;
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
 * Created by luke on 2018/10/25.
 */
@Api(value = "/goodsAttrSetup", description = "商品自定义属性")
@RequestMapping("goodsAttrSetup")
public interface IGoodsAttrSetupAction {
    String findGoodsAttrSetupByKindId = "以品类Id查询商品自定义属性配置" ;

    /**
     * 以品类Id查询商品自定义属性配置
     * <br>findGoodsAttrSetupByKindId.act
     * @param request
     * @param response
     * @param actionResult
     * @param vo
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @ApiOperation(value = findGoodsAttrSetupByKindId)
    @RequestMapping(path = "findGoodsAttrSetupByKindId.act",method= RequestMethod.POST)
    @ResponseBody
    ActionResult findGoodsAttrSetupByKindId(HttpServletRequest request, HttpServletResponse response, ActionResult actionResult,
                            @ApiParam(value = findGoodsAttrSetupByKindId, required = true)  @Valid @RequestBody
                            VOId vo,
                            BindingResult bindingResult)throws Exception ;
}
