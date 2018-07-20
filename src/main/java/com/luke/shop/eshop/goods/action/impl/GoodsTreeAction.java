package com.luke.shop.eshop.goods.action.impl;

import com.luke.shop.eshop.base.BaseAction;
import com.luke.shop.eshop.goods.action.IGoodsTreeAction;
import com.luke.shop.eshop.goods.service.IGoodsTreeService;
import com.luke.shop.eshop.goods.vo.VOGoodsTreeAdd;
import com.luke.shop.model.TG_GoodsTree;
import com.luke.shop.tool.ActionResult;
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
        List<TG_GoodsTree> listGoodsTree = this.goodsTreeService.findNode_1(getSessionTuken(request),vo) ;
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
}
