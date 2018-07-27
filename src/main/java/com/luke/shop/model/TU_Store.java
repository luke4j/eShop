package com.luke.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by luke on 2018/3/23.
 * 部门，站点
 */
@Entity
public class TU_Store extends Model{

    @Column(length = 30,nullable = false)
    private String name ;
    @Column(length = 15,nullable = false)
    private String py ;
    @Column(length = 90,nullable = false)
    private String pinYin ;

    @Column(length = 20)
    private String phone ;

    @Column(length = 100)
    private String addr ;

    @Column(length = 100)
    private String phote ;
    /**是否加工中心*/
    private Boolean isCenter = false ;
    /**是否直营站点*/
    private Boolean isZhiYing = true ;
    /**是否使用站点价格*/
    private Boolean isZhanDianJia = false ;
    /**父级Id*/
    private Long fid = 0l ;
    /**是否有库存*/
    private Boolean isHasKc = true ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="comId")
    @JsonIgnore
    private TU_Com com ;

    public Boolean getIsHasKc() {
        return isHasKc;
    }

    public void setIsHasKc(Boolean isHasKc) {
        this.isHasKc = isHasKc;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPhote() {
        return phote;
    }

    public void setPhote(String phote) {
        this.phote = phote;
    }

    public Boolean getIsCenter() {
        return isCenter;
    }

    public void setIsCenter(Boolean isCenter) {
        this.isCenter = isCenter;
    }

    public Boolean getIsZhiYing() {
        return isZhiYing;
    }

    public void setIsZhiYing(Boolean isZhiYing) {
        this.isZhiYing = isZhiYing;
    }

    public Boolean getIsZhanDianJia() {
        return isZhanDianJia;
    }

    public void setIsZhanDianJia(Boolean isZhanDianJia) {
        this.isZhanDianJia = isZhanDianJia;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public TU_Com getCom() {
        return com;
    }

    public void setCom(TU_Com com) {
        this.com = com;
    }
}
