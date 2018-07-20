package com.luke.shop.eshop.goods.dao.impl;

import com.luke.shop.eshop.base.BaseDao;
import com.luke.shop.eshop.goods.dao.IGoodsTreeDao;
import com.luke.shop.model.TG_GoodsTree;
import com.luke.shop.tool.LKMap;
import com.luke.shop.tool.vo.VOIdEmpty;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by luke on 2018/7/20.
 */
@Component
public class GoodsTreeDao extends BaseDao implements IGoodsTreeDao {
    @Override
    public List<TG_GoodsTree> findNode_1(Long comId, VOIdEmpty vo) throws Exception {
        LKMap<String,Object> param = new LKMap<String,Object>().putEx("comId",comId).putEx("fid",vo.getId()) ;
        List<TG_GoodsTree> listGoodsTree = this.find("From TG_GoodsTree r where r.com.id=:comId and fid=:fid",param) ;
        return listGoodsTree;
    }

    @Override
    public TG_GoodsTree addNode_2(TG_GoodsTree node) throws Exception {
        return this.save(node);
    }
}
