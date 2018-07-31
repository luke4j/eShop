package com.luke.shop.eshop.goods.dao;

import com.luke.shop.eshop.base.IBaseDao;
import com.luke.shop.eshop.goods.vo.VOGoodsAttrSetupEdit;
import com.luke.shop.eshop.goods.vo.VOGoodsNode;
import com.luke.shop.eshop.goods.vo.VOGoodsTreeEdit;
import com.luke.shop.model.TG_GoodsAttrSetup;
import com.luke.shop.model.TG_GoodsTree;
import com.luke.shop.tool.LoginTuken;
import com.luke.shop.tool.vo.VOId;
import com.luke.shop.tool.vo.VOIdEmpty;

import java.util.List;

/**
 * Created by luke on 2018/7/20.
 */
public interface IGoodsTreeDao extends IBaseDao {
    List<VOGoodsNode> findNode_1(Long comId, VOIdEmpty vo)throws  Exception ;

    TG_GoodsTree addNode_2(TG_GoodsTree node)throws  Exception ;

    /**
     * 添加品类时，一起初始化商品属性配置
     * @param node
     * @throws Exception
     */
    void addNode_2_initGoodsAttrSetup(TG_GoodsTree node)throws  Exception ;



    TG_GoodsTree editNode_3(LoginTuken sessionTuken, VOGoodsTreeEdit vo)throws  Exception ;

    TG_GoodsTree delNode_4(LoginTuken sessionTuken, VOId vo)throws  Exception ;

    TG_GoodsAttrSetup edit_goods_attr_setup_5(LoginTuken sessionTuken, VOGoodsAttrSetupEdit vo)throws  Exception ;

    /**
     * 以品类Id查询品类属性配置
     * @param vo
     * @return
     * @throws Exception
     */
    List<TG_GoodsAttrSetup> find_goods_attr_setup_6(VOId vo) throws  Exception ;
}
