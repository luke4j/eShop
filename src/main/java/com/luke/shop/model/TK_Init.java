package com.luke.shop.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by luke on 2018/8/7.
 * 库存初始化单据
 */
@Entity
public class TK_Init extends _YW {


    public TK_Init(){}

    public TK_Init(TU_Com com,TK_YW yw,TU_Store store,TU_User zd_user){
        super(com,yw,store,zd_user);
        this.setZdTime(new Date());
    }


    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "djId",foreignKey = @ForeignKey(name = "fk_yw_djId"))
    private List<TK_InitList> djmx ;

    @Override
    public List<TK_InitList> getDjmx() {
        return this.djmx;
    }



    @Override
    public void setDjmx(List<? extends _YWList> djmx) {
        this.djmx = (List<TK_InitList>) djmx;
    }
}
