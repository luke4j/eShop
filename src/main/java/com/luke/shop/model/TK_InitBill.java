package com.luke.shop.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by luke on 2018/8/7.
 * 库存初始化单据
 */
@Entity
public class TK_InitBill extends _YW {


    public TK_InitBill(){}

    public TK_InitBill(TU_Com com, TK_YW yw, TU_Store store, TU_User zd_user){
        super(com,yw,store,zd_user);
        this.setZdTime(new Date());
    }


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "djId",foreignKey = @ForeignKey(name = "fk_yw_djId"))
    private List<TK_InitBillMX> djmx ;

    @Override
    public List<TK_InitBillMX> getDjmx() {
        return this.djmx;
    }



    @Override
    public void setDjmx(List<? extends _YWMX> djmx) {
        this.djmx = (List<TK_InitBillMX>) djmx;
    }
}
