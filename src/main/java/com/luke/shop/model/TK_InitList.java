package com.luke.shop.model;

import javax.persistence.*;

/**
 * Created by luke on 2018/8/7.
 * 库存初始化单据明细
 */
@Entity
public class TK_InitList extends _YWList {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "djId",foreignKey = @ForeignKey(name = "fk_yw_djId"))
    TK_Init dj  ;

    public TK_Init getDj() {
        return dj;
    }

    public void setDj(TK_Init dj) {
        this.dj = dj;
    }
}
