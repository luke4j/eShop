package com.luke.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by luke on 2018/7/30.
 */
@Entity
@Table(indexes = {
        @Index(name="idx_price_goods_store_sph_cyl",columnList = "goodsId,storeId,sph,cyl",unique = true)
})
public class TG_Price extends Model {

    public enum PriceType {
        normal,//正常价格
        store,//站点价格
    }

    @Column(nullable = false,length = 10)
    @Enumerated(EnumType.STRING)
    private PriceType priceType = PriceType.normal ;

    @ManyToOne
    @JoinColumn(name = "goodsId",nullable = false,foreignKey = @ForeignKey(name = "fk_price_goods"))
    private TG_Goods goods ;
    private Float sph ;
    private Float cyl ;

    private Double pin = 0.0;
    private Double pout  = 0.0 ;

    @ManyToOne
    @JoinColumn(name = "storeId",foreignKey = @ForeignKey(name = "fk_price_store"))
    private TU_Store store ;

    @ManyToOne
    @JoinColumn(name = "comId",foreignKey =@ForeignKey(name="fk_price_com") )
    @JsonIgnore
    private TU_Com com ;


    public PriceType getPriceType() {
        return priceType;
    }

    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

    public TG_Goods getGoods() {
        return goods;
    }

    public void setGoods(TG_Goods goods) {
        this.goods = goods;
    }

    public Float getSph() {
        return sph;
    }

    public void setSph(Float sph) {
        this.sph = sph;
    }

    public Float getCyl() {
        return cyl;
    }

    public void setCyl(Float cyl) {
        this.cyl = cyl;
    }

    public Double getPin() {
        return pin;
    }

    public void setPin(Double pin) {
        this.pin = pin;
    }

    public Double getPout() {
        return pout;
    }

    public void setPout(Double pout) {
        this.pout = pout;
    }

    public TU_Store getStore() {
        return store;
    }

    public void setStore(TU_Store store) {
        this.store = store;
    }

    public TU_Com getCom() {
        return com;
    }

    public void setCom(TU_Com com) {
        this.com = com;
    }
}
