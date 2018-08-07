package com.luke.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by luke on 2018/8/7.
 */
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class _YW extends Model {

    public _YW(){} ;

    /**
     * 初始化时，单据状态为制单
     * @param com
     * @param yw
     * @param store
     * @param zd_user
     */
    public _YW(TU_Com com,TK_YW yw,TU_Store store,TU_User zd_user){
        this.y_bill_state = BillState.zd ;
        this.y_com = com ;
        this.y_yw = yw ;
        this.y_store = store ;
        this.y_zd_user = zd_user ;
    }

    public enum BillState{
        zd,qr,zx
    }


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "y_comId",nullable = false,foreignKey = @ForeignKey(name = "fk_byw_com"))
    private TU_Com y_com ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "y_ywId",nullable = false,foreignKey = @ForeignKey(name = "fk_byw_yw"))
    private TK_YW y_yw ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "y_storeId",nullable = false,foreignKey = @ForeignKey(name = "fk_byw_store"))
    private TU_Store y_store ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "y_zd_userId",nullable = false,foreignKey = @ForeignKey(name = "fk_byw_dz_user"))
    private TU_User y_zd_user ;
    private Date zdTime ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "y_qr_userId",foreignKey = @ForeignKey(name = "fk_byw_qr_user"))
    private TU_User y_qr_user ;
    private Date qrTime ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "y_zx_userId",foreignKey = @ForeignKey(name = "fk_byw_zx_user"))
    private TU_User y_zx_user ;
    private Date zxTime ;

    @Column(nullable = false,length = 5)
    @Enumerated(EnumType.STRING)
    private BillState y_bill_state = BillState.zd ;





    public TU_Com getY_com() {
        return y_com;
    }

    public void setY_com(TU_Com y_com) {
        this.y_com = y_com;
    }

    public TK_YW getY_yw() {
        return y_yw;
    }

    public void setY_yw(TK_YW y_yw) {
        this.y_yw = y_yw;
    }

    public TU_Store getY_store() {
        return y_store;
    }

    public void setY_store(TU_Store y_store) {
        this.y_store = y_store;
    }


    public BillState getY_bill_state() {
        return y_bill_state;
    }

    public void setY_bill_state(BillState y_bill_state) {
        this.y_bill_state = y_bill_state;
    }

    public TU_User getY_zd_user() {
        return y_zd_user;
    }

    public void setY_zd_user(TU_User y_zd_user) {
        this.y_zd_user = y_zd_user;
    }

    public Date getZdTime() {
        return zdTime;
    }

    public void setZdTime(Date zdTime) {
        this.zdTime = zdTime;
    }

    public TU_User getY_qr_user() {
        return y_qr_user;
    }

    public void setY_qr_user(TU_User y_qr_user) {
        this.y_qr_user = y_qr_user;
    }

    public Date getQrTime() {
        return qrTime;
    }

    public void setQrTime(Date qrTime) {
        this.qrTime = qrTime;
    }

    public TU_User getY_zx_user() {
        return y_zx_user;
    }

    public void setY_zx_user(TU_User y_zx_user) {
        this.y_zx_user = y_zx_user;
    }

    public Date getZxTime() {
        return zxTime;
    }

    public void setZxTime(Date zxTime) {
        this.zxTime = zxTime;
    }

    abstract  public List<? extends _YWList> getDjmx()  ;

    abstract public void setDjmx(List< ? extends _YWList> djmx) ;
}
