package com.luke.shop.eshop.goods.action.impl;

import com.luke.shop.eshop.base.BaseAction;
import com.luke.shop.eshop.goods.action.IGoodsTreeAction;
import com.luke.shop.eshop.goods.service.IGoodsTreeService;
import com.luke.shop.eshop.goods.vo.VOGoodsAttrSetupEdit;
import com.luke.shop.eshop.goods.vo.VOGoodsNode;
import com.luke.shop.eshop.goods.vo.VOGoodsTreeAdd;
import com.luke.shop.eshop.goods.vo.VOGoodsTreeEdit;
import com.luke.shop.model.TG_GoodsAttrSetup;
import com.luke.shop.model.TG_GoodsTree;
import com.luke.shop.tool.ActionResult;
import com.luke.shop.tool.LKMap;
import com.luke.shop.tool.vo.VOId;
import com.luke.shop.tool.vo.VOIdEmpty;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by luke on 2018/7/20.
 */
@Controller
public class GoodsTreeAction extends BaseAction implements IGoodsTreeAction {

    @Resource
    private IGoodsTreeService goodsTreeService ;


    @Override
    public ActionResult findNode_1(HttpServletRequest request, HttpServletResponse response,
                                   @ApiParam(value = findNode, required = true) @Valid @RequestBody
                                   VOIdEmpty vo,
                                   BindingResult bindingResult, ActionResult actionResult) throws Exception {
        actionResult.setDoing(findNode);
        List<VOGoodsNode> listGoodsTree = this.goodsTreeService.findNode_1(getSessionTuken(request),vo) ;
        actionResult.setData(listGoodsTree);
        return actionResult;
    }


    @Override
    public ActionResult addNode_2(HttpServletRequest request, HttpServletResponse response,
                                  @ApiParam(value = addNode, required = true) @Valid @RequestBody
                                  VOGoodsTreeAdd vo,
                                  BindingResult bindingResult, ActionResult actionResult) throws Exception {
        actionResult.setDoing(addNode);
        TG_GoodsTree node = this.goodsTreeService.addNode_2(getSessionTuken(request),vo) ;
        actionResult.setData(node);
        return actionResult;
    }

    @Override
    public ActionResult editNode_3(HttpServletRequest request, HttpServletResponse response,
                                   @ApiParam(value = editNode, required = true) @Valid @RequestBody VOGoodsTreeEdit vo,
                                   BindingResult bindingResult, ActionResult actionResult) throws Exception {

        actionResult.setDoing(editNode);
        TG_GoodsTree node = this.goodsTreeService.editNode_3(getSessionTuken(request), vo) ;
        actionResult.setData(node);
        return actionResult;
    }

    @Override
    public ActionResult delNode_4(HttpServletRequest request, HttpServletResponse response,
                                  @ApiParam(value = delNode, required = true) @Valid @RequestBody VOId vo,
                                  BindingResult bindingResult, ActionResult actionResult) throws Exception {
        actionResult.setDoing(delNode);
        TG_GoodsTree node = this.goodsTreeService.delNode_4(getSessionTuken(request), vo) ;
        actionResult.setData(node);
        return actionResult;
    }

    @Override
    public ActionResult edit_goods_attr_setup_5(HttpServletRequest request, HttpServletResponse response,
                                                @ApiParam(value = edit_goods_attr_setup, required = true) @Valid @RequestBody VOGoodsAttrSetupEdit vo,
                                                BindingResult bindingResult, ActionResult actionResult) throws Exception {
        actionResult.setDoing(edit_goods_attr_setup);
        TG_GoodsAttrSetup goodsAttrSetup = this.goodsTreeService.edit_goods_attr_setup_5(getSessionTuken(request), vo) ;
        actionResult.setData(goodsAttrSetup);
        return actionResult;
    }

    @Override
    public ActionResult find_goods_attr_setup_6(HttpServletRequest request, HttpServletResponse response,
                                                @ApiParam(value = find_goods_attr_setup, required = true) @Valid @RequestBody VOId vo,
                                                BindingResult bindingResult, ActionResult actionResult) throws Exception {
        actionResult.setDoing(find_goods_attr_setup);
        List<TG_GoodsAttrSetup> listGoodsAttrSetup =  this.goodsTreeService.find_goods_attr_setup_6(getSessionTuken(request), vo) ;
        actionResult.setData(listGoodsAttrSetup);
        return actionResult;
    }

    @Override
    public ActionResult find_goods_attr_setup_byColor_7(HttpServletRequest request, HttpServletResponse response,
                                                        @ApiParam(value = find_goods_attr_setup_byColor, required = true) @Valid @RequestBody VOId vo,
                                                        BindingResult bindingResult, ActionResult actionResult) throws Exception {
        actionResult.setDoing(find_goods_attr_setup_byColor);
        List<TG_GoodsAttrSetup> listGoodsAttrSetup =  this.goodsTreeService.find_goods_attr_setup_byColor_7(getSessionTuken(request), vo) ;
        LKMap<String,Object> ext = this.goodsTreeService.find_goods_attr_setup_byColor_7_goodsTreeParent(vo) ;
        actionResult.setData(listGoodsAttrSetup);
        actionResult.setExt(ext);
        return actionResult;
    }

    @Override
    public ActionResult find_goods_attrsByGoodsId_8(HttpServletRequest request, HttpServletResponse response,
                                                          @ApiParam(value = find_goods_attrsByGoodsId, required = true) @Valid @RequestBody VOId vo,
                                                          BindingResult bindingResult, ActionResult actionResult) throws Exception {
        actionResult.setDoing(find_goods_attrsByGoodsId);
        this.goodsTreeService.find_goods_attrsByGoodsId_8(vo, actionResult) ;
        return actionResult;
    }
}
