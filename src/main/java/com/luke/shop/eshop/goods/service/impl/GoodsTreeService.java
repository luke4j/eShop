package com.luke.shop.eshop.goods.service.impl;

import com.luke.shop.eshop.goods.dao.IGoodsTreeDao;
import com.luke.shop.eshop.goods.service.IGoodsTreeService;
import com.luke.shop.eshop.goods.vo.VOGoodsAttrSetupEdit;
import com.luke.shop.eshop.goods.vo.VOGoodsNode;
import com.luke.shop.eshop.goods.vo.VOGoodsTreeAdd;
import com.luke.shop.eshop.goods.vo.VOGoodsTreeEdit;
import com.luke.shop.model.*;
import com.luke.shop.tool.ActionResult;
import com.luke.shop.tool.LK;
import com.luke.shop.tool.LKMap;
import com.luke.shop.tool.LoginTuken;
import com.luke.shop.tool.vo.VOId;
import com.luke.shop.tool.vo.VOIdEmpty;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by luke on 2018/7/20.
 */
@Service
public class GoodsTreeService implements IGoodsTreeService {

    @Resource
    IGoodsTreeDao goodsTreeDao ;

    @Override
    public List<VOGoodsNode> findNode_1(LoginTuken sessionTuken, VOIdEmpty vo) throws Exception {
        vo.setId(vo.getId()==null?0l:vo.getId());
        List<VOGoodsNode> listGoodsTree = this.goodsTreeDao.findNode_1(sessionTuken.getComId(),vo) ;
        return listGoodsTree;
    }

    @Override
    public TG_GoodsTree addNode_2(LoginTuken sessionTuken, VOGoodsTreeAdd vo) throws Exception {
        TG_GoodsTree node = new TG_GoodsTree() ;
        BeanUtils.copyProperties(vo, node);
        node.setPinyi(LK.NameToPingYinLong(node.getText()));
        node.setPy(LK.NameToPingYinShort(node.getText()));
        node.setCom(this.goodsTreeDao.get(TU_Com.class,sessionTuken.getComId()));
        node = this.goodsTreeDao.addNode_2(node) ;
        if("品类".equals(node.getC_group())){
            this.goodsTreeDao.addNode_2_initGoodsAttrSetup(node) ;
        }
        return node;
    }



    @Override
    public TG_GoodsTree editNode_3(LoginTuken sessionTuken, VOGoodsTreeEdit vo) throws Exception {
        return this.goodsTreeDao.editNode_3(sessionTuken,vo) ;
    }

    @Override
    public TG_GoodsTree delNode_4(LoginTuken sessionTuken, VOId vo) throws Exception {
        return this.goodsTreeDao.delNode_4(sessionTuken, vo) ;
    }

    @Override
    public TG_GoodsAttrSetup edit_goods_attr_setup_5(LoginTuken sessionTuken, VOGoodsAttrSetupEdit vo) throws Exception {
        return this.goodsTreeDao.edit_goods_attr_setup_5(sessionTuken, vo) ;
    }

    @Override
    public List<TG_GoodsAttrSetup> find_goods_attr_setup_6(LoginTuken sessionTuken, VOId vo) throws Exception {
        List<TG_GoodsAttrSetup> listGoodsAttrSetup = this.goodsTreeDao.find_goods_attr_setup_6(vo) ;
        return listGoodsAttrSetup;
    }


    @Override
    public List<TG_GoodsAttrSetup> find_goods_attr_setup_byColor_7(LoginTuken sessionTuken, VOId vo) throws Exception {
        LKMap<String,Object> extMap = this.find_goods_attr_setup_byColor_7_goodsTreeParent(vo) ;
        TG_GoodsTree kindNode = (TG_GoodsTree)extMap.get("kind") ;
        VOId voKindId = new VOId() ;
        voKindId.setId(kindNode.getId());
        return  this.find_goods_attr_setup_6(sessionTuken,voKindId) ;
    }

    @Override
    public LKMap<String, Object> find_goods_attr_setup_byColor_7_goodsTreeParent(VOId vo) throws Exception {
        TG_GoodsTree colorNode = this.goodsTreeDao.get(TG_GoodsTree.class,vo.getId()) ;
        TG_GoodsTree versionNode = this.goodsTreeDao.get(TG_GoodsTree.class,colorNode.getFid()) ;
        TG_GoodsTree brandNode = this.goodsTreeDao.get(TG_GoodsTree.class,versionNode.getFid()) ;
        TG_GoodsTree kindNode = this.goodsTreeDao.get(TG_GoodsTree.class,brandNode.getFid()) ;
        LKMap<String, Object> ext = new LKMap<String,Object>().putEx("kind",kindNode).putEx("brand",brandNode).putEx("version",versionNode).putEx("color", colorNode) ;
        return ext;
    }

    @Override
    public void find_goods_attrsByGoodsId_8(VOId vo, ActionResult actionResult) throws Exception {
        /**商品基本信息*/
        TG_Goods goods = this.goodsTreeDao.get(TG_Goods.class, vo.getId()) ;
        VOId voColorId = new VOId() ;
        voColorId.setId(goods.getColor().getId());
        LKMap<String, Object> ext = this.find_goods_attr_setup_byColor_7_goodsTreeParent(voColorId) ;
        /**商品扩展信息*/
        TG_GoodsAttr attrs = this.goodsTreeDao.find_goods_attrsByGoodsId_8_attrs(goods) ;
        ext.put("attrs",attrs) ;
        actionResult.setExt(ext);

        /**商品扩展信息配置*/
        VOId voKindId = new VOId() ;
        TG_GoodsTree goodsNode = (TG_GoodsTree)ext.get("kind") ;
        voKindId.setId(goodsNode.getId());
        List<TG_GoodsAttrSetup> listGoodsAttr = this.find_goods_attr_setup_6(null,voKindId) ;

        actionResult.setData(listGoodsAttr);
    }
}
