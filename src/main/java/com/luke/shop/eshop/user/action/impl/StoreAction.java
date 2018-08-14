package com.luke.shop.eshop.user.action.impl;

import com.luke.shop.eshop.base.BaseAction;
import com.luke.shop.eshop.user.action.IStoreAction;
import com.luke.shop.eshop.user.service.IStoreService;
import com.luke.shop.model.TU_Store;
import com.luke.shop.tool.ActionResult;
import com.luke.shop.tool.vo.VOEmpty;
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
 * Created by luke on 2018/8/14.
 */
@Controller
public class StoreAction extends BaseAction implements IStoreAction {

    @Resource
    private IStoreService storeService ;

    @Override
    public ActionResult findAllStore_1(HttpServletRequest request, HttpServletResponse response,
                                       @ApiParam(value = findAllStore, required = true) @Valid @RequestBody
                                       VOEmpty vo,
                                       BindingResult bindingResult, ActionResult actionResult) throws Exception {
        actionResult.setDoing(findAllStore);
        List<TU_Store> listStore = this.storeService.findAllStore_1(getSessionTuken(request),vo) ;
        actionResult.setData(listStore);
        return actionResult;
    }
}
