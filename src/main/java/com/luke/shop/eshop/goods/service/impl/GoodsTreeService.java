package com.luke.shop.eshop.goods.service.impl;

import com.luke.shop.eshop.goods.dao.IGoodsTreeDao;
import com.luke.shop.eshop.goods.service.IGoodsTreeService;
import com.luke.shop.eshop.goods.vo.VOGoodsTreeAdd;
import com.luke.shop.model.TG_GoodsTree;
import com.luke.shop.model.TU_Com;
import com.luke.shop.tool.LK;
import com.luke.shop.tool.LoginTuken;
import com.luke.shop.tool.vo.VOIdEmpty;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
        return node;
    }
}
