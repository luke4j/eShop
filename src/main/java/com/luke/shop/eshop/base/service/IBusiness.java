package com.luke.shop.eshop.base.service;

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
    <T ,V> T createBill(V bill) throws Exception;

    /**
     * 确认单据
     * @param bill
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T  affirmBill(T bill) throws Exception;

    /**
     * 执行单据
     * @param bill
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T executeBill(T bill) throws Exception ;

}
