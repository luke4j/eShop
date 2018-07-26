package com.luke.shop.eshop.goods.service.impl;

import com.luke.shop.eshop.goods.dao.IGoodsTreeDao;
import com.luke.shop.eshop.goods.service.IGoodsTreeService;
import com.luke.shop.eshop.goods.vo.VOGoodsAttrSetupEdit;
import com.luke.shop.eshop.goods.vo.VOGoodsTreeAdd;
import com.luke.shop.eshop.goods.vo.VOGoodsTreeEdit;
import com.luke.shop.model.TG_GoodsAttrSetup;
import com.luke.shop.model.TG_GoodsTree;
import com.luke.shop.model.TU_Com;
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
    public List<TG_GoodsTree> findNode_1(LoginTuken sessionTuken, VOIdEmpty vo) throws Exception {
        vo.setId(vo.getId()==null?0l:vo.getId());
        List<TG_GoodsTree> listGoodsTree = this.goodsTreeDao.findNode_1(sessionTuken.getComId(),vo) ;
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
}
