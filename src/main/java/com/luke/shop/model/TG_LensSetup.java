package com.luke.shop.model;

import javax.persistence.*;

/**
 * Created by luke on 2018/7/30.
 */
@Entity
public class TG_LensSetup extends Model {

    @Column(nullable = false)
    private Float sphMin ;
    @Column(nullable = false)
    private Float sphMax ;
    @Column(nullable = false)
    private Float sphPool ;

    @Column(nullable = false)
    private Float cylMin ;
    @Column(nullable = false)
    private Float cylMax ;
    @Column(nullable = false)
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

    public Float getCylMin() {
        return cylMin;
    }

    public void setCylMin(Float cylMin) {
        this.cylMin = cylMin;
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
