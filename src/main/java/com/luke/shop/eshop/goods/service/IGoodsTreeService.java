package com.luke.shop.eshop.goods.service;

import com.luke.shop.eshop.goods.vo.VOGoodsAttrSetupEdit;
import com.luke.shop.eshop.goods.vo.VOGoodsNode;
import com.luke.shop.eshop.goods.vo.VOGoodsTreeAdd;
import com.luke.shop.eshop.goods.vo.VOGoodsTreeEdit;
import com.luke.shop.model.TG_GoodsAttrSetup;
import com.luke.shop.model.TG_GoodsTree;
import com.luke.shop.tool.ActionResult;
import com.luke.shop.tool.LKMap;
import com.luke.shop.tool.LoginTuken;
import com.luke.shop.tool.vo.VOId;
import com.luke.shop.tool.vo.VOIdEmpty;

import java.util.List;
import java.util.Map;

/**
 * Created by luke on 2018/7/20.
 */
public interface IGoodsTreeService {
    List<VOGoodsNode> findNode_1(LoginTuken sessionTuken, VOIdEmpty vo) throws  Exception;

    TG_GoodsTree addNode_2(LoginTuken sessionTuken, VOGoodsTreeAdd vo) throws  Exception;



    TG_GoodsTree editNode_3(LoginTuken sessionTuken, VOGoodsTreeEdit vo)throws  Exception;

    /**
     *
     * @param sessionTuken
     * @param vo   goodsTree.id
     * @return
     * @throws Exception
     */
    TG_GoodsTree delNode_4(LoginTuken sessionTuken, VOId vo)throws  Exception;

    TG_GoodsAttrSetup edit_goods_attr_setup_5(LoginTuken sessionTuken, VOGoodsAttrSetupEdit vo)throws  Exception;

    /**
     *
     * @param sessionTuken
     * @param vo goodsTree.id   kindId
     * @return
     * @throws Exception
     */
    List<TG_GoodsAttrSetup> find_goods_attr_setup_6(LoginTuken sessionTuken, VOId vo) throws  Exception;


    /**
     *
     * @param sessionTuken
     * @param vo  goodsTree.id  colorId
     * @return
     * @throws Exception
     */
    List<TG_GoodsAttrSetup> find_goods_attr_setup_byColor_7(LoginTuken sessionTuken, VOId vo)throws  Exception;

    /**
     *
     * @param vo goodsTree.id colorId
     * @return
     * @throws Exception
     */
    LKMap<String,Object> find_goods_attr_setup_byColor_7_goodsTreeParent(VOId vo)throws  Exception;

    void find_goods_attrsByGoodsId_8(VOId vo, ActionResult actionResult)throws  Exception;
}
