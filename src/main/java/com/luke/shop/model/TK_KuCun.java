package com.luke.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by luke on 2018/5/10.
 * 库存表
 */
@Entity
public class TK_KuCun extends Model {
//    @Id
//    @TableGenerator(name = "tk_ku_cun_seq",       //sequence name
//            initialValue=Static.initialValue,
//            table = "seq_table",              //sequence table
//            pkColumnName = "seq_name",        //在表中对对应的sequence name 列
//            pkColumnValue = "tk_ku_cun_seq",      //在表中对对应的sequence name 值
//            valueColumnName = "num",          //值
//            allocationSize = Static.allocationSize)
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tk_ku_cun_seq")
//    private Long id;

    @Column(nullable = false)
    private Long goodsId ;
    private Float sph ;
    private Float cyl ;

    @Column(nullable = false)
    private Long storeId ;

    /**
     * 正品
     */
    @Column(nullable = false)
    private Long num_zheng_pin = 0l ;

    /**
     * 次品
     */
    @Column(nullable = false)
    private Long num_ci_pin = 0l;

    /**
     * 残品
     */
    @Column(nullable = false)
    private Long num_can_pin = 0l;

    /**
     * 赠品
     */
    @Column(nullable = false)
    private Long num_zeng_pin  = 0l;

    /**
     * 需要数量
     */
    @Column(nullable = false)
    private Long num_need  = 0l;

    /**数数是否盘点*/
    private Boolean isPd = false;
    /**最后盘点日期*/
    private Date last_pd_day ;
    /**最后盘点人ID*/
    private Long last_pd_userId ;

    public Long getNum_zeng_pin() {
        return num_zeng_pin;
    }

    public void setNum_zeng_pin(Long num_zeng_pin) {
        this.num_zeng_pin = num_zeng_pin;
    }

    public Boolean getIsPd() {
        return isPd;
    }

    public void setIsPd(Boolean isPd) {
        this.isPd = isPd;
    }

    public Date getLast_pd_day() {
        return last_pd_day;
    }

    public void setLast_pd_day(Date last_pd_day) {
        this.last_pd_day = last_pd_day;
    }

    public Long getLast_pd_userId() {
        return last_pd_userId;
    }

    public void setLast_pd_userId(Long last_pd_userId) {
        this.last_pd_userId = last_pd_userId;
    }

    public Long getNum_need() {
        return num_need;
    }

    public void setNum_need(Long num_need) {
        this.num_need = num_need;
    }

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
}
