package com.luke.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by luke on 2018/5/11.
 * 商品基本信息与度数信息关系
 */
@Entity
public class TG_GoodsLensSetup extends Model{

//    @Id
//    @TableGenerator(name = "tg_goods_lens_seq",       //sequence name
//            table = "seq_table",              //sequence table
//            initialValue=Static.initialValue,
//            pkColumnName = "seq_name",        //在表中对对应的sequence name 列
//            pkColumnValue = "tg_goods_lens_seq",      //在表中对对应的sequence name 值
//            valueColumnName = "num",          //值
//            allocationSize = Static.allocationSize)
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tg_goods_lens_seq")
//    private Long id ;
    @Column(length = 50,nullable = false)
    private String name ;
    @Column(length = 25,nullable = false)
    private String py ;
    @Column(length = 180,nullable = false)
    private String pinYin ;
    @Column(length = 18,nullable = false)
    private String sphMax ;
    @Column(length = 18,nullable = false)
    private String sphMin ;
    @Column(length = 18,nullable = false)
    private String sphPool ;
    @Column(length = 18,nullable = false)
    private String cylMax ;
    @Column(length = 18,nullable = false)
    private String cylMin ;
    @Column(length = 18,nullable = false)
    private String cylPool ;
    @Column(length = 18,nullable = false,unique = true)
    private Long goodsId ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPy() {
        return py;
    }

    public void setPy(String py) {
        this.py = py;
    }

    public String getPinYin() {
        return pinYin;
    }

    public void setPinYin(String pinYin) {
        this.pinYin = pinYin;
    }

    public String getSphMax() {
        return sphMax;
    }

    public void setSphMax(String sphMax) {
        this.sphMax = sphMax;
    }

    public String getSphMin() {
        return sphMin;
    }

    public void setSphMin(String sphMin) {
        this.sphMin = sphMin;
    }

    public String getSphPool() {
        return sphPool;
    }

    public void setSphPool(String sphPool) {
        this.sphPool = sphPool;
    }

    public String getCylMax() {
        return cylMax;
    }

    public void setCylMax(String cylMax) {
        this.cylMax = cylMax;
    }

    public String getCylMin() {
        return cylMin;
    }

    public void setCylMin(String cylMin) {
        this.cylMin = cylMin;
    }

    public String getCylPool() {
        return cylPool;
    }

    public void setCylPool(String cylPool) {
        this.cylPool = cylPool;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
}
