package com.luke.shop.eshop.base.service;

import com.luke.shop.model.TU_User;
import com.luke.shop.model._YW;
import com.luke.shop.model._YWMX;

import java.util.Date;
import java.util.List;

/**
 * Created by luke on 2018/7/26.
 */
public interface IBusiness {

    /**
     * 制单 ，代理会保存单据，并保存单据明细
     * @param bill
     * @param listBillMx
     * @param zdUser
     * @param tag
     * @return
     * @throws Exception
     */
    default _YW createBill(_YW bill,List<? extends _YWMX> listBillMx, TU_User zdUser, String tag) throws Exception {
        bill.setZdTime(new Date());
        bill.setY_zd_user(zdUser);
        bill.setY_bill_state(_YW.BillState.zd);
        return bill;
    }

    /**
     * 确认单据
     * @param bill
     * @param qrUser
     * @param tag
     * @return
     * @throws Exception
     */
    default _YW affirmBill(_YW bill,TU_User qrUser ,String tag) throws Exception {
        bill.setQrTime(new Date());
        bill.setY_qr_user(qrUser);
        bill.setY_bill_state(_YW.BillState.qr);
        return bill ;
    }

    /**
     * 执行单据
     * @param bill
     * @param zxUser
     * @param tag
     * @return
     * @throws Exception
     */
    _YW executeBill(_YW bill,TU_User zxUser,String tag) throws Exception ;

}
