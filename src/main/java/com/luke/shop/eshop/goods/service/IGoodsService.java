package com.luke.shop.eshop.goods.service;

import com.luke.shop.eshop.goods.vo.VOGoods;
import com.luke.shop.model.TG_Goods;
import com.luke.shop.tool.LoginTuken;

/**
 * Created by luke on 2018/7/27.
 */
public interface IGoodsService {
    /**
     * 商品属性-添加商品信息
     * @param sessionTuken
     * @param vo
     * @return
     * @throws Exception
     */
    TG_Goods addGoods_1(LoginTuken sessionTuken, VOGoods vo) throws Exception;
}
