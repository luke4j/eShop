package com.luke.shop.eshop.goods.action;

import com.luke.shop.eshop.goods.vo.VOGoodsAttrSetupEdit;
import com.luke.shop.eshop.goods.vo.VOGoodsTreeAdd;
import com.luke.shop.eshop.goods.vo.VOGoodsTreeEdit;
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
 * Created by luke on 2018/7/20.
 */
@Api(value = "/goodsTree", description = "商品属性模块")
@RequestMapping("goodsTree")
public interface IGoodsTreeAction {

    String findNode = "查询goodstree结点" ;
    String addNode = "添加goodstree结点" ;
    String editNode = "修改goodstree结点" ;
    String delNode = "删除goodstree结点" ;
    String edit_goods_attr_setup = "编辑商品属性" ;
    String find_goods_attr_setup = "查询商品属性" ;
    String find_goods_attr_setup_byColor = "以商品颜色Id查询所有商品属性" ;
    String find_goods_attrsByGoodsId = "以商品Id查询商品基本属性，扩展属性，扩展属性配置" ;


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
     * 添加goodstree结点
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



    /**
     * 修改goodstree结点
     * @param request
     * @param response
     * @param vo
     * @param bindingResult
     * @param actionResult
     * @return
     * @throws Exception
     */
    @ApiOperation(value = editNode)
    @RequestMapping(path = "editNode.act",method= RequestMethod.POST)
    @ResponseBody
    ActionResult editNode_3(HttpServletRequest request, HttpServletResponse response,
                           @ApiParam(value = editNode, required = true)  @Valid @RequestBody
                           VOGoodsTreeEdit vo, BindingResult bindingResult, ActionResult actionResult)throws Exception ;

    /**
     * 修改goodstree结点
     * @param request
     * @param response
     * @param vo
     * @param bindingResult
     * @param actionResult
     * @return
     * @throws Exception
     */
    @ApiOperation(value = delNode)
    @RequestMapping(path = "delNode.act",method= RequestMethod.POST)
    @ResponseBody
    ActionResult delNode_4(HttpServletRequest request, HttpServletResponse response,
                            @ApiParam(value = delNode, required = true)  @Valid @RequestBody
                            VOId vo, BindingResult bindingResult, ActionResult actionResult)throws Exception ;




    /**
     * 编辑商品属性
     * @param request
     * @param response
     * @param vo
     * @param bindingResult
     * @param actionResult
     * @return
     * @throws Exception
     */
    @ApiOperation(value = edit_goods_attr_setup)
    @RequestMapping(path = "edit_goods_attr_setup.act",method= RequestMethod.POST)
    @ResponseBody
    ActionResult edit_goods_attr_setup_5 (HttpServletRequest request, HttpServletResponse response,
                           @ApiParam(value = edit_goods_attr_setup, required = true)  @Valid @RequestBody
                           VOGoodsAttrSetupEdit vo,
                                          BindingResult bindingResult, ActionResult actionResult)throws Exception ;

    /**
     * 配置商品品类的商品属性时<br>
     * 以商品品类Id查询商品扩展属性
     * @param request
     * @param response
     * @param vo
     * @param bindingResult
     * @param actionResult
     * @return
     * @throws Exception
     */
    @ApiOperation(value = find_goods_attr_setup)
    @RequestMapping(path = "find_goods_attr_setup.act",method= RequestMethod.POST)
    @ResponseBody
    ActionResult find_goods_attr_setup_6 (HttpServletRequest request, HttpServletResponse response,
                                          @ApiParam(value = find_goods_attr_setup, required = true)  @Valid @RequestBody
                                          VOId vo, BindingResult bindingResult, ActionResult actionResult)throws Exception ;


    /**
     * 添加商品信息时，以颜色查询商品的所有扩展属性<br>
     *     包括 品类，品牌，型号，颜色
     * @param request
     * @param response
     * @param vo
     * @param bindingResult
     * @param actionResult
     * @return
     * @throws Exception
     */
    @ApiOperation(value = find_goods_attr_setup_byColor)
    @RequestMapping(path = "find_goods_attr_setup_byColor.act",method= RequestMethod.POST)
    @ResponseBody
    ActionResult find_goods_attr_setup_byColor_7 (HttpServletRequest request, HttpServletResponse response,
                                          @ApiParam(value = find_goods_attr_setup_byColor, required = true)  @Valid @RequestBody
                                          VOId vo, BindingResult bindingResult, ActionResult actionResult)throws Exception ;


    /**
     * 以商品id查询 商品基本信息，扩展信息与扩展信息配置
     * @param request
     * @param response
     * @param vo
     * @param bindingResult
     * @param actionResult
     * @return
     * @throws Exception
     */
    @ApiOperation(value = find_goods_attrsByGoodsId)
    @RequestMapping(path = "find_goods_attrsByGoodsId.act",method= RequestMethod.POST)
    @ResponseBody
    ActionResult find_goods_attrsByGoodsId_8 (HttpServletRequest request, HttpServletResponse response,
                                                  @ApiParam(value = find_goods_attrsByGoodsId, required = true)  @Valid @RequestBody
                                                  VOId vo, BindingResult bindingResult, ActionResult actionResult)throws Exception ;

}
