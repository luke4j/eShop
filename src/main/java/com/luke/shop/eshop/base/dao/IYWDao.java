package com.luke.shop.eshop.base.dao;

import com.luke.shop.model.TK_YW;

/**
 * Created by luke on 2018/10/24.
 */
public interface IYWDao {

    /**
     * 添加商品时，添加商品默认库存
     * @return
     * @throws Exception
     */
    TK_YW _0() throws Exception ;

    /**
     *盘点后，正品盘盈，添加库存
     * @return
     * @throws Exception
     */
    TK_YW _100001001() throws Exception ;
    /**
     *盘点后，次品盘盈，添加库存
     * @return
     * @throws Exception
     */
    TK_YW _100001002() throws Exception ;
    /**
     *盘点后，残品盘盈，添加库存
     * @return
     * @throws Exception
     */
    TK_YW _100001003() throws Exception ;
    /**
     *购入入库
     * @return
     * @throws Exception
     */
    TK_YW _100002001() throws Exception ;
    /**
     *正品转次
     * @return
     * @throws Exception
     */
    TK_YW _100002002() throws Exception ;
    /**
     *次品转残
     * @return
     * @throws Exception
     */
    TK_YW _100002003() throws Exception ;
    /**
     *赠品转正
     * @return
     * @throws Exception
     */
    TK_YW _100002004() throws Exception ;
    /**
     *调拨正品入库
     * @return
     * @throws Exception
     */
    TK_YW _100003001() throws Exception ;
    /**
     *调拨次品入库
     * @return
     * @throws Exception
     */
    TK_YW _100003002() throws Exception ;
    /**
     *调拨残品入库
     * @return
     * @throws Exception
     */
    TK_YW _100003003() throws Exception ;
    /**
     *结算退货
     * @return
     * @throws Exception
     */
    TK_YW _100004001() throws Exception ;
    /**
     *配送退货
     * @return
     * @throws Exception
     */
    TK_YW _100004002() throws Exception ;
    /**
     *配料退货
     * @return
     * @throws Exception
     */
    TK_YW _100004003() throws Exception ;
    /**
     *初检退货
     * @return
     * @throws Exception
     */
    TK_YW _100004004() throws Exception ;
    /**
     *加工退货-镜片
     * @return
     * @throws Exception
     */
    TK_YW _100004005() throws Exception ;
    /**
     *加工退货-镜架
     * @return
     * @throws Exception
     */
    TK_YW _100004006() throws Exception ;
    /**
     *终检退货-镜片
     * @return
     * @throws Exception
     */
    TK_YW _100004007() throws Exception ;
    /**
     *终检退货-镜架
     * @return
     * @throws Exception
     */
    TK_YW _100004008() throws Exception ;
    /**
     *取镜退货-镜片
     * @return
     * @throws Exception
     */
    TK_YW _100004009() throws Exception ;
    /**
     *取镜退货-镜架
     * @return
     * @throws Exception
     */
    TK_YW _100004010() throws Exception ;
    /**
     *完成退货-镜片
     * @return
     * @throws Exception
     */
    TK_YW _100004011() throws Exception ;
    /**
     *完成退货-镜架
     * @return
     * @throws Exception
     */
    TK_YW _100004012() throws Exception ;
    /**
     *完成退货-成品
     * @return
     * @throws Exception
     */
    TK_YW _100004013() throws Exception ;
    /**
     *盘亏正品
     * @return
     * @throws Exception
     */
    TK_YW _200001001() throws Exception ;
    /**
     *盘亏次品
     * @return
     * @throws Exception
     */
    TK_YW __200001002() throws Exception ;
    /**
     *盘亏残品
     * @return
     * @throws Exception
     */
    TK_YW __200001003() throws Exception ;
    /**
     *正品转次
     * @return
     * @throws Exception
     */
    TK_YW _200002001() throws Exception ;
    /**
     *次品转残
     * @return
     * @throws Exception
     */
    TK_YW _200002002() throws Exception ;
    /**
     *残品处理
     * @return
     * @throws Exception
     */
    TK_YW _200002003() throws Exception ;
    /**
     *正品转赠
     * @return
     * @throws Exception
     */
    TK_YW _200002004() throws Exception ;
    /**
     *调拨正品出库
     * @return
     * @throws Exception
     */
    TK_YW _200003001() throws Exception ;
    /**
     *调拨次品出库
     * @return
     * @throws Exception
     */
    TK_YW _200003002() throws Exception ;
    /**
     *调拨残品出库
     * @return
     * @throws Exception
     */
    TK_YW _200003003() throws Exception ;
    /**
     *销售正品
     * @return
     * @throws Exception
     */
    TK_YW _200004001() throws Exception ;


}
