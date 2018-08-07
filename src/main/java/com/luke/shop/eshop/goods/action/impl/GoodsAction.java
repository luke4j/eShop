package com.luke.shop.eshop.goods.action.impl;

import com.luke.shop.eshop.base.BaseAction;
import com.luke.shop.eshop.goods.action.IGoodsAction;
import com.luke.shop.eshop.goods.service.IGoodsService;
import com.luke.shop.eshop.goods.vo.VOGoods;
import com.luke.shop.eshop.goods.vo.VOGoodsEdit;
import com.luke.shop.eshop.goods.vo.VOLens;
import com.luke.shop.model.TG_Goods;
import com.luke.shop.tool.ActionResult;
import com.luke.shop.tool.vo.VOId;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by luke on 2018/7/27.
 */
@Controller
public class GoodsAction extends BaseAction implements IGoodsAction {

    @Resource
    IGoodsService goodsService ;

    @Override
    public ActionResult addGoods_1(HttpServletRequest request, HttpServletResponse response,
                                   @ApiParam(value = addGoods, required = true) @Valid @RequestBody VOGoods vo,
                                   BindingResult bindingResult, ActionResult actionResult) throws Exception {
        actionResult.setDoing(addGoods);
        TG_Goods node = this.goodsService.addGoods_1(getSessionTuken(request), vo) ;
        actionResult.setData(node);
        return actionResult;
    }

    @Override
    public ActionResult editGoods_2(HttpServletRequest request, HttpServletResponse response,
                                    @ApiParam(value = addGoods, required = true) @Valid @RequestBody VOGoodsEdit vo,
                                    BindingResult bindingResult, ActionResult actionResult) throws Exception {
        return null;
    }

    @Override
    public ActionResult delGoods_3(HttpServletRequest request, HttpServletResponse response,
                                   @ApiParam(value = delGoods, required = true) @Valid @RequestBody VOId vo,
                                   BindingResult bindingResult, ActionResult actionResult) throws Exception {
        return null;
    }

    @Override
    public ActionResult findGoods_4(HttpServletRequest request, HttpServletResponse response,
                                    @ApiParam(value = findGoods, required = true) @Valid @RequestBody VOGoods vo,
                                    BindingResult bindingResult, ActionResult actionResult) throws Exception {
        return null;
    }

    @Override
    public ActionResult getGoodsLens_5(HttpServletRequest request, HttpServletResponse response,
                                     @ApiParam(value = getGoodsLens, required = true) @Valid @RequestBody VOId vo,
                                     BindingResult bindingResult, ActionResult actionResult) throws Exception {
        actionResult.setDoing(getGoodsLens);
        this.goodsService.getGoodsLens_5(actionResult,vo) ;
        return actionResult;
    }

    @Override
    public ActionResult saveLens_6(HttpServletRequest request, HttpServletResponse response,
                                   @ApiParam(value = saveLens, required = true) @Valid @RequestBody VOLens vo,
                                   BindingResult bindingResult, ActionResult actionResult) throws Exception {
        actionResult.setData(saveLens);
        this.goodsService.saveLens_6(getSessionTuken(request),actionResult,vo) ;
        return actionResult;
    }
}
