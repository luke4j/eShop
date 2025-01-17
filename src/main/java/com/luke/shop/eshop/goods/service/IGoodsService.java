package com.luke.shop.eshop.goods.service;

import com.luke.shop.eshop.goods.vo.VOGoods;
import com.luke.shop.eshop.goods.vo.VOGoodsEdit;
import com.luke.shop.eshop.goods.vo.VOLens;
import com.luke.shop.model.TG_Goods;
import com.luke.shop.tool.ActionResult;
import com.luke.shop.tool.LoginTuken;
import com.luke.shop.tool.vo.VOId;

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

    /**
     * 查询公司配置，是否添加默认库存
     * @param sessionTuken
     * @param node
     * @throws Exception
     */
    void addGoods_1_def_kc(LoginTuken sessionTuken, TG_Goods node)throws Exception;


    /**
     *
     * @param actionResult
     * @param vo
     * @throws Exception
     */
    void getGoodsLens_5(ActionResult actionResult, VOId vo) throws Exception;

    void saveLens_6(LoginTuken sessionTuken, ActionResult actionResult, VOLens vo)throws Exception;


    void saveLens_6_def_kc(LoginTuken sessionTuken, ActionResult actionResult, VOLens vo)throws Exception;

    void editGoods_2(VOGoodsEdit vo)throws Exception;

    void delGoods_3(VOId vo)throws Exception;
}
