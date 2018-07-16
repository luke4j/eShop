package com.luke.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by luke on 2018/5/11.
 * 站点价格
 */
@Entity
public class TG_Price_Store extends Model{
//    @Id
//    @TableGenerator(name = "tk_price_seq",       //sequence name
//            initialValue=Static.initialValue,
//            table = "seq_table",              //sequence table
//            pkColumnName = "seq_name",        //在表中对对应的sequence name 列
//            pkColumnValue = "tk_price_seq",      //在表中对对应的sequence name 值
//            valueColumnName = "num",          //值
//            allocationSize = Static.allocationSize)
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tk_price_seq")
//    private Long id;
    @Column(nullable = false)
    private Long goodsId ;
    @Column(nullable = false)
    private Long storeId ;
    private Float sph ;
    private Float cyl ;
    private Long inPrice ;
    private Long outPrice  ;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
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

    public Long getInPrice() {
        return inPrice;
    }

    public void setInPrice(Long inPrice) {
        this.inPrice = inPrice;
    }

    public Long getOutPrice() {
        return outPrice;
    }

    public void setOutPrice(Long outPrice) {
        this.outPrice = outPrice;
    }
}
