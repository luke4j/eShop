package com.luke.shop.eshop.test.action;

import com.luke.shop.tool.ActionResult;
import com.luke.shop.tool.vo.VOId;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by luke on 2018/8/1.
 */
@RequestMapping("tree")
public interface IZtreeAction  {

    /**
     * tree/getZtreeData.act
     * @param request
     * @param response
     * @param vo
     * @param bindingResult
     * @param actionResult
     * @return
     * @throws Exception
     */
    @RequestMapping(path = "getZtreeData.act",method= RequestMethod.POST)
    @ResponseBody
    ActionResult getZtreeData(HttpServletRequest request, HttpServletResponse response,
                        @RequestBody
                        VOId vo, BindingResult bindingResult, ActionResult actionResult)throws Exception ;

}
