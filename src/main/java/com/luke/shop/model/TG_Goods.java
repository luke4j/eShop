package com.luke.shop.model;

import javax.persistence.*;

/**
 * Created by luke on 2018/7/20.
 * 商品信息
 */
@Entity
public class TG_Goods extends Model {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kindId")
    private TG_GoodsTree kind ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brandId")
    private TG_GoodsTree brand ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "versionId")
    private TG_GoodsTree version ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "colorId")
    private TG_GoodsTree color ;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "attrId")
    private TG_GoodsAttr attr ;

    /**商品编码*/
    @Column(length = 40,nullable = false)
    private String c_code ;
    @Column(length = 60,nullable = false)
    private String name ;
    @Column(length = 30,nullable = false)
    private String py ;
    @Column(length = 180,nullable = false)
    private String pinYin ;

    /**0 库存，1 零订 2车房*/
    private Integer kcjb = 0 ;


    public TG_GoodsTree getKind() {
        return kind;
    }

    public void setKind(TG_GoodsTree kind) {
        this.kind = kind;
    }

    public TG_GoodsTree getBrand() {
        return brand;
    }

    public void setBrand(TG_GoodsTree brand) {
        this.brand = brand;
    }

    public TG_GoodsTree getVersion() {
        return version;
    }

    public void setVersion(TG_GoodsTree version) {
        this.version = version;
    }

    public TG_GoodsTree getColor() {
        return color;
    }

    public void setColor(TG_GoodsTree color) {
        this.color = color;
    }

    public TG_GoodsAttr getAttr() {
        return attr;
    }

    public void setAttr(TG_GoodsAttr attr) {
        this.attr = attr;
    }

    public String getC_code() {
        return c_code;
    }

    public void setC_code(String c_code) {
        this.c_code = c_code;
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

    public Integer getKcjb() {
        return kcjb;
    }

    public void setKcjb(Integer kcjb) {
        this.kcjb = kcjb;
    }
}
