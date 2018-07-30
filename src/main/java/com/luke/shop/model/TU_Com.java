package com.luke.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

/**
 * Created by luke on 2018/3/23.
 * 公司表
 */
@Entity
public class TU_Com  extends Model{

    public enum YeuJieRQ {
        _1,
        _15,
        _30,
        _end
    }



    @Column(length = 100,nullable = false,unique = true,name = "idxu_com_name")
    private String name ;

    @Column(length = 200,nullable = false)
    private String addr ;

    @Column(length = 20,nullable = false)
    private String phone ;

    @Column(length = 50)
    private String photo ;
    /**公司管理员*/
    private Long adminId ;
    /**公司管理员未加密密码*/
    @Column(length = 36)
    private String adminPassword ;
    /**是否禁用*/
    private Boolean isJy = true ;
    /**最大可用时间*/
    private Date maxUseDay = new Date();
    /**月结日期*/
    @Enumerated(EnumType.STRING)
    private YeuJieRQ yueJieRQ = YeuJieRQ._end;
    /**本月是否月结*/
    private Boolean isYueJie = false;

    private Integer webNum = 10 ;
    private Integer webPrice = 1 ;

    private Integer padNum = 10 ;
    private Integer padPrice = 2 ;

    private Integer storeNum = 10 ;

    private Integer servicePrice = 20 ;

    /**开始使用日期*/
    private Date useDate = new Date() ;
    /**累計充值*/
    private Long cz = 0l ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public Boolean getIsJy() {
        return isJy;
    }

    public void setIsJy(Boolean isJy) {
        this.isJy = isJy;
    }

    public Date getMaxUseDay() {
        return maxUseDay;
    }

    public void setMaxUseDay(Date maxUseDay) {
        this.maxUseDay = maxUseDay;
    }

    public YeuJieRQ getYueJieRQ() {
        return yueJieRQ;
    }

    public void setYueJieRQ(YeuJieRQ yueJieRQ) {
        this.yueJieRQ = yueJieRQ;
    }

    public Boolean getIsYueJie() {
        return isYueJie;
    }

    public void setIsYueJie(Boolean isYueJie) {
        this.isYueJie = isYueJie;
    }

    public Integer getWebNum() {
        return webNum;
    }

    public void setWebNum(Integer webNum) {
        this.webNum = webNum;
    }

    public Integer getWebPrice() {
        return webPrice;
    }

    public void setWebPrice(Integer webPrice) {
        this.webPrice = webPrice;
    }

    public Integer getPadNum() {
        return padNum;
    }

    public void setPadNum(Integer padNum) {
        this.padNum = padNum;
    }

    public Integer getPadPrice() {
        return padPrice;
    }

    public void setPadPrice(Integer padPrice) {
        this.padPrice = padPrice;
    }

    public Integer getStoreNum() {
        return storeNum;
    }

    public void setStoreNum(Integer storeNum) {
        this.storeNum = storeNum;
    }

    public Integer getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(Integer servicePrice) {
        this.servicePrice = servicePrice;
    }

    public Date getUseDate() {
        return useDate;
    }

    public void setUseDate(Date useDate) {
        this.useDate = useDate;
    }

    public Long getCz() {
        return cz;
    }

    public void setCz(Long cz) {
        this.cz = cz;
    }
}
