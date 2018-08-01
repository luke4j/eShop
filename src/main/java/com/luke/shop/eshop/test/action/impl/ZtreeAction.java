package com.luke.shop.eshop.test.action.impl;

import com.luke.shop.eshop.test.action.IZtreeAction;
import com.luke.shop.eshop.test.vo.VOTreeNode;
import com.luke.shop.tool.ActionResult;
import com.luke.shop.tool.vo.VOId;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luke on 2018/8/1.
 */
@Controller
public class ZtreeAction implements IZtreeAction {
    @Override
    public ActionResult getZtreeData(HttpServletRequest request, HttpServletResponse response,
                               @RequestBody VOId vo,
                               BindingResult bindingResult, ActionResult actionResult) throws Exception {
        actionResult.setDoing("ztree测试");
        int n1 = 100 ,n2 = 100, n3 = 100 ;
        List<VOTreeNode> listNode = new ArrayList<>(100) ;
        for(int i = 0 ;i < n1 ; i++){
            VOTreeNode node1 = new VOTreeNode() ;
            node1.setId((long) i);
            node1.setName((long) i);
            listNode.add(node1) ;
//            for (int j = 0 ;j<n2 ;j++){
//                VOTreeNode node2 = new VOTreeNode() ;
//                node2.setId((long) j+(j*100));
//                node2.setName((long) j + (j*100));
//                node1.getChildren().add(node2) ;
//            }
        }
        actionResult.setData(listNode);
        return actionResult;
    }
}
