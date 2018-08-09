package com.luke.shop.model;

import javax.persistence.*;

/**
 * Created by luke on 2018/8/7.
 * 库存初始化单据明细
 */
@Entity
public class TK_InitBillMX extends _YWMX {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "djId",nullable = false,foreignKey = @ForeignKey(name = "fk_yw_djId"))
    TK_InitBill dj  ;

    public TK_InitBill getDj() {
        return dj;
    }

    public void setDj(TK_InitBill dj) {
        this.dj = dj;
    }
}
