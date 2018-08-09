package com.luke.shop.eshop.base.service;

import com.luke.shop.model.TU_User;

import java.util.List;

/**
 * Created by luke on 2018/7/26.
 */
public interface IBusiness {

    /**
     * 制单 ，代理会保存单据，但并不保存单据明细
     * @param bill
     * @param <T>
     * @param <V>
     * @return
     * @throws Exception
     */
    default <T, V> T createBill(V bill, TU_User zdUser, String tag) throws Exception {
        return (T)bill;
    }
    /**
     * 确认单据
     * @param bill
     * @param <T>
     * @return
     * @throws Exception
     */
    default <T> T affirmBill(T bill,TU_User qrUser ,String tag) throws Exception {
        return (T)bill ;
    }

    /**
     * 执行单据
     * @param bill
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T executeBill(T bill,TU_User zxUser,String tag) throws Exception ;

}
