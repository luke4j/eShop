package com.luke.shop.model;

import javax.persistence.*;

/**
 * Created by luke on 2018/8/7.
 */
@Entity
public class TK_YWLS extends Model {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ywId",foreignKey = @ForeignKey(name = "fk_ywls_yw"))
    private TK_YW yw ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kcId",foreignKey = @ForeignKey(name = "fk_ywls_kc"))
    private TK_KC kc ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "goodsId",foreignKey = @ForeignKey(name = "fk_ywls_goods"))
    private TG_Goods goods ;
    private Float sph ;
    private Float cyl ;

    @Column(length = 20,nullable = false)
    private String ywTable  ;
    private Long ywTableId ;

    /**修改值*/
    private Long eidtNum ;



    /**
     * 没有操作业务之前，库存正品
     */
    @Column(nullable = false)
    private Long num_zheng_pin = 0l ;

    /**
     * 没有操作业务之前，库存次品
     */
    @Column(nullable = false)
    private Long num_ci_pin = 0l;

    /**
     * 没有操作业务之前，库存残品
     */
    @Column(nullable = false)
    private Long num_can_pin = 0l;

    /**
     * 没有操作业务之前，库存赠品
     */
    @Column(nullable = false)
    private Long num_zeng_pin  = 0l;


    public TK_YW getYw() {
        return yw;
    }

    public void setYw(TK_YW yw) {
        this.yw = yw;
    }

    public String getYwTable() {
        return ywTable;
    }

    public void setYwTable(String ywTable) {
        this.ywTable = ywTable;
    }

    public Long getYwTableId() {
        return ywTableId;
    }

    public void setYwTableId(Long ywTableId) {
        this.ywTableId = ywTableId;
    }

    public Long getEidtNum() {
        return eidtNum;
    }

    public void setEidtNum(Long eidtNum) {
        this.eidtNum = eidtNum;
    }

    public Long getNum_zheng_pin() {
        return num_zheng_pin;
    }

    public void setNum_zheng_pin(Long num_zheng_pin) {
        this.num_zheng_pin = num_zheng_pin;
    }

    public Long getNum_ci_pin() {
        return num_ci_pin;
    }

    public void setNum_ci_pin(Long num_ci_pin) {
        this.num_ci_pin = num_ci_pin;
    }

    public Long getNum_can_pin() {
        return num_can_pin;
    }

    public void setNum_can_pin(Long num_can_pin) {
        this.num_can_pin = num_can_pin;
    }

    public Long getNum_zeng_pin() {
        return num_zeng_pin;
    }

    public void setNum_zeng_pin(Long num_zeng_pin) {
        this.num_zeng_pin = num_zeng_pin;
    }

    public TK_KC getKc() {
        return kc;
    }

    public void setKc(TK_KC kc) {
        this.kc = kc;
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
}
