package com.luke.shop.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by luke on 2018/7/27.
 */
@Entity
@Table(indexes = {
        @Index(name="idx_kc_store_goods_sph_cyl",columnList = "storeId,goodsId,sph,cyl",unique = true)
})
public class TK_KC extends Model {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "goodsId",foreignKey = @ForeignKey(name = "fk_kc_goods"))
    private TG_Goods goods ;
    private Float sph ;
    private Float cyl ;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeId",foreignKey = @ForeignKey(name = "fk_kc_store"))
    private TU_Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comId",foreignKey = @ForeignKey(name = "fk_kc_com"))
    private TU_Com com ;

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

    /**数据是否盘点*/
    private Boolean isPd = false;
    /**最后盘点日期*/
    private Date last_pd_day ;
    /**最后盘点人ID*/
    private Long last_pd_userId ;


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

    public Long getNum_zeng_pin() {
        return num_zeng_pin;
    }

    public void setNum_zeng_pin(Long num_zeng_pin) {
        this.num_zeng_pin = num_zeng_pin;
    }

    public Long getNum_need() {
        return num_need;
    }

    public void setNum_need(Long num_need) {
        this.num_need = num_need;
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

    public TG_Goods getGoods() {
        return goods;
    }

    public void setGoods(TG_Goods goods) {
        this.goods = goods;
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
