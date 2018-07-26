package com.luke.shop.eshop.bs_sell.service;

import com.luke.shop.eshop.bs_sell.vo.VOSellAdd;
import com.luke.shop.tool.vo.VOId;

/**
 * Created by luke on 2018/7/26.
 */
public interface ISellService {

    /**
     * 制销售单
     * @param sellAdd
     * @throws Exception
     */
    void createSellBill(VOSellAdd sellAdd) throws Exception ;

    /**
     * 收费，确认单执行销售单
     * @param vo
     * @throws Exception
     */
    void executeSellBill(VOId vo) throws Exception ;


}
