package com.luke.shop.eshop.base.service;

import com.luke.shop.eshop.base.dao.ISaveBill;
import com.luke.shop.eshop.base.dao.ISaveBillMx;
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
     * 制单，保存单据与单据明细，使用接口来实现
     * @param saveBill
     * @param saveBillMx
     * @param zdUser
     * @param tag
     * @return
     * @throws Exception
     */
    default _YW createBill(ISaveBill saveBill,ISaveBillMx saveBillMx, TU_User zdUser, String tag) throws Exception {
        _YW yw_bill = saveBill.save() ;
        saveBillMx.save(yw_bill) ;
        yw_bill.setZdTime(new Date());
        yw_bill.setY_zd_user(zdUser);
        yw_bill.setY_bill_state(_YW.BillState.zd);
        return yw_bill;
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
    default _YW executeBill(_YW bill,TU_User zxUser,String tag,Boolean isLens) throws Exception {
        bill.setQrTime(new Date());
        bill.setY_qr_user(zxUser);
        bill.setY_bill_state(_YW.BillState.zx);
        return bill ;
    }

}
