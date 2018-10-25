package com.luke.shop.eshop.goods.dao.impl;

import com.luke.shop.eshop.base.BaseDao;
import com.luke.shop.eshop.goods.dao.IGoodsAttrSetupDao;
import com.luke.shop.model.TG_GoodsAttrSetup;
import com.luke.shop.tool.vo.VOId;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by luke on 2018/10/25.
 */
@Component
public class GoodsAttrSetupDao extends BaseDao implements IGoodsAttrSetupDao {

    @Override
    public List<TG_GoodsAttrSetup> findGoodsAttrSetupByKindId(VOId vo) throws Exception {
        return  this.find("From TG_GoodsAttrSetup gas where gas.kind.id=:id",vo) ;
    }
}
