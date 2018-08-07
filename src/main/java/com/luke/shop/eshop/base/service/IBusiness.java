package com.luke.shop.eshop.base.service;

import com.luke.shop.model.TU_User;

import java.util.List;

/**
 * Created by luke on 2018/7/26.
 */
public interface IBusiness {

    /**
     * 制单
     * @param bill
     * @param <T>
     * @param <V>
     * @return
     * @throws Exception
     */
    <T ,V> T createBill(V bill,TU_User zdUser,String tag) throws Exception;

    /**
     * 确认单据
     * @param bill
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T  affirmBill(T bill,TU_User qrUser,String tag) throws Exception;

    /**
     * 执行单据
     * @param bill
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T executeBill(T bill,TU_User zxUser,String tag) throws Exception ;

}
