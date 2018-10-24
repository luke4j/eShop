package com.luke.shop.eshop.base.dao;

import com.luke.shop.model.TSYS_SetupCom;

/**
 * Created by luke on 2018/10/24.
 */
public interface ISystemSetupComDao {
    /**
     * 添加成品商品时添加价格
     * <br>ext1进货价，ext2销售价，val是否启用
     * @param comId
     * @return
     * @throws Exception
     */
    TSYS_SetupCom save_not_lens_add_price(Long comId) throws Exception ;

    /**
     * 添加成品商品时初始化库存
     * <br>ext1默认库存，val是否启用
     * @param comId
     * @return
     * @throws Exception
     */
    TSYS_SetupCom save_not_lens_add_kc(Long comId) throws Exception ;

    /**
     * 添加度数商品时添加添加默认价格
     * <br>ext1进货价 ext2销售价，val是否启用
     * @param comId
     * @return
     * @throws Exception
     */
    TSYS_SetupCom save_lens_add_price(Long comId) throws Exception ;

    /**
     * 添加度数商品时添加默认库存
     * <br>ext1默认库存，val是否启用
     * @param comId
     * @return
     * @throws Exception
     */
    TSYS_SetupCom save_lens_add_kc(Long comId) throws Exception ;
}
