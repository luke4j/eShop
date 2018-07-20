package com.luke.shop.eshop.goods.dao;

import com.luke.shop.eshop.base.IBaseDao;
import com.luke.shop.model.TG_GoodsTree;
import com.luke.shop.tool.vo.VOIdEmpty;

import java.util.List;

/**
 * Created by luke on 2018/7/20.
 */
public interface IGoodsTreeDao extends IBaseDao {
    List<TG_GoodsTree> findNode_1(Long comId, VOIdEmpty vo)throws  Exception ;

    TG_GoodsTree addNode_2(TG_GoodsTree node)throws  Exception ;
}
