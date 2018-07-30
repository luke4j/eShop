package com.luke.shop.model;

import javax.persistence.*;

/**
 * Created by luke on 2018/7/30.
 */
@Entity
public class TG_LensSetup extends Model {

    private Float sphMin ;
    private Float sphMax ;
    private Float sphPool ;

    private Float cylhMin ;
    private Float cylMax ;
    private Float cylPool ;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "goodsId",foreignKey = @ForeignKey(name = "fk_lensSetup_goods"))
    private TG_Goods goods ;


    public Float getSphMin() {
        return sphMin;
    }

    public void setSphMin(Float sphMin) {
        this.sphMin = sphMin;
    }

    public Float getSphMax() {
        return sphMax;
    }

    public void setSphMax(Float sphMax) {
        this.sphMax = sphMax;
    }

    public Float getSphPool() {
        return sphPool;
    }

    public void setSphPool(Float sphPool) {
        this.sphPool = sphPool;
    }

    public Float getCylhMin() {
        return cylhMin;
    }

    public void setCylhMin(Float cylhMin) {
        this.cylhMin = cylhMin;
    }

    public Float getCylMax() {
        return cylMax;
    }

    public void setCylMax(Float cylMax) {
        this.cylMax = cylMax;
    }

    public Float getCylPool() {
        return cylPool;
    }

    public void setCylPool(Float cylPool) {
        this.cylPool = cylPool;
    }

    public TG_Goods getGoods() {
        return goods;
    }

    public void setGoods(TG_Goods goods) {
        this.goods = goods;
    }
}
