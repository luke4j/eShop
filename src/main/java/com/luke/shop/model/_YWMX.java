package com.luke.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * Created by luke on 2018/8/7.
 * 业务明细基类
 */
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class _YWMX extends Model {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "l_goodsId",nullable = false,foreignKey = @ForeignKey(name = "fk_bywl_goods"))
    private TG_Goods l_goods ;

    private Float sph ;
    private Float cyl ;

    private Long l_num ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "l_kcId",foreignKey = @ForeignKey(name = "fk_bywl_kc"))
    private TK_KC l_kc ;

    public Float getCyl() {
        return cyl;
    }

    public void setCyl(Float cyl) {
        this.cyl = cyl;
    }

    public Float getSph() {
        return sph;
    }

    public void setSph(Float sph) {
        this.sph = sph;
    }

    public TG_Goods getL_goods() {
        return l_goods;
    }

    public void setL_goods(TG_Goods l_goods) {
        this.l_goods = l_goods;
    }

    public Long getL_num() {
        return l_num;
    }

    public void setL_num(Long l_num) {
        this.l_num = l_num;
    }

    public TK_KC getL_kc() {
        return l_kc;
    }

    public void setL_kc(TK_KC l_kc) {
        this.l_kc = l_kc;
    }
}
