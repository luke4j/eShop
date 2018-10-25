package com.luke.shop.eshop.goods.service;

import com.luke.shop.model.TG_GoodsAttrSetup;
import com.luke.shop.tool.vo.VOId;

import java.util.List;

/**
 * Created by luke on 2018/10/25.
 */
public interface IGoodsAttrSetupService {

    /**
     * 以品类Id查询商品自定义属性配置
     * @param vo
     * @return
     */
    List<TG_GoodsAttrSetup> findGoodsAttrSetupByKindId(VOId vo) throws Exception;
}
