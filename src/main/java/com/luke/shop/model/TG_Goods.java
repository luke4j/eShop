package com.luke.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by luke on 2018/7/20.
 * 商品信息
 */
@Entity
public class TG_Goods extends Model {

    public enum KcJb{
        xk,ld,cf
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kindId",foreignKey = @ForeignKey(name = "fk_goods_kind"))
    private TG_GoodsTree kind ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brandId",foreignKey = @ForeignKey(name = "fk_goods_brand"))
    private TG_GoodsTree brand ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "versionId",foreignKey = @ForeignKey(name = "fk_goods_version"))
    private TG_GoodsTree version ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "colorId",foreignKey = @ForeignKey(name = "fk_goods_color"))
    private TG_GoodsTree color ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comId",foreignKey = @ForeignKey(name = "fk_goods_com"))
    @JsonIgnore
    private TU_Com com ;


    /**商品编码*/
    @Column(length = 40,nullable = false)
    private String c_code ;
    @Column(length = 60,nullable = false)
    private String name ;
    @Column(length = 30,nullable = false)
    private String py ;
    @Column(length = 180,nullable = false)
    private String pinYin ;


    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private KcJb kcjb = KcJb.xk ;


    public TU_Com getCom() {
        return com;
    }

    public void setCom(TU_Com com) {
        this.com = com;
    }

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

    public KcJb getKcjb() {
        return kcjb;
    }

    public void setKcjb(KcJb kcjb) {
        this.kcjb = kcjb;
    }
}
