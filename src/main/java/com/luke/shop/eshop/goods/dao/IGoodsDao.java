package com.luke.shop.eshop.goods.dao;

import com.luke.shop.eshop.base.IBaseDao;
import com.luke.shop.model.TG_Goods;
import com.luke.shop.tool.LoginTuken;

/**
 * Created by luke on 2018/7/27.
 */
public interface IGoodsDao extends IBaseDao {


    /**
     * 保存所有站点
     * @param sessionTuken
     * @param goods
     * @throws Exception
     */
    void addGoods_1_add_kc(LoginTuken sessionTuken, TG_Goods goods)throws  Exception;
}
