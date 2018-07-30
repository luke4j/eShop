package com.luke.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by luke on 2018/7/20.
 * 商品 品类，品牌，型号，颜色组成的树
 */
@Entity
public class TG_GoodsTree extends Model{

    @Column(nullable = false)
    private Long fid ;
    @Column(length = 30,nullable = false)
    private String c_group ;
    @Column(length = 40,nullable = false)
    private String text ;
    private Integer c_level = 0 ;
    @Column(length = 120)
    private String pinyi ;
    @Column(length = 20)
    private String py ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comId",foreignKey = @ForeignKey(name = "fk_goodsTree_com"))
    @JsonIgnore
    private TU_Com com ;



    /**
     * {kind:'是否度数'}
     */
    @Column(length = 30)
    private String a1 ;
    /**
     * {kind:'是否实物'}
     */
    @Column(length = 30)
    private String a2 ;
    /**
     * {kind:"是否效期'}
     */
    @Column(length = 30)
    private String a3 ;
    @Column(length = 30)
    private String a4 ;
    @Column(length = 30)
    private String a5 ;


    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public String getC_group() {
        return c_group;
    }

    public void setC_group(String c_group) {
        this.c_group = c_group;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getC_level() {
        return c_level;
    }

    public void setC_level(Integer c_level) {
        this.c_level = c_level;
    }

    public String getPinyi() {
        return pinyi;
    }

    public void setPinyi(String pinyi) {
        this.pinyi = pinyi;
    }

    public String getPy() {
        return py;
    }

    public void setPy(String py) {
        this.py = py;
    }

    public TU_Com getCom() {
        return com;
    }

    public void setCom(TU_Com com) {
        this.com = com;
    }

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public String getA3() {
        return a3;
    }

    public void setA3(String a3) {
        this.a3 = a3;
    }

    public String getA4() {
        return a4;
    }

    public void setA4(String a4) {
        this.a4 = a4;
    }

    public String getA5() {
        return a5;
    }

    public void setA5(String a5) {
        this.a5 = a5;
    }
}
