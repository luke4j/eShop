package com.luke.shop.eshop.goods.action.impl;

import com.luke.shop.eshop.goods.action.IGoodsAttrSetupAction;
import com.luke.shop.eshop.goods.dao.IGoodsDao;
import com.luke.shop.eshop.goods.service.IGoodsAttrSetupService;
import com.luke.shop.model.TG_GoodsAttrSetup;
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
import java.util.List;

/**
 * Created by luke on 2018/10/25.
 */
@Controller
public class GoodsAttrSetupAction implements IGoodsAttrSetupAction {

    @Resource
    IGoodsAttrSetupService goodsAttrSetupService;

    @Override
    public ActionResult findGoodsAttrSetupByKindId(HttpServletRequest request, HttpServletResponse response , ActionResult actionResult,
                                                   @ApiParam(value = findGoodsAttrSetupByKindId, required = true) @Valid @RequestBody VOId vo,
                                                   BindingResult bindingResult) throws Exception {
        actionResult.setDoing(findGoodsAttrSetupByKindId);
        List<TG_GoodsAttrSetup> lstGoodsAttrSetup = this.goodsAttrSetupService.findGoodsAttrSetupByKindId(vo) ;
        actionResult.setData(lstGoodsAttrSetup);
        return actionResult;
    }
}
