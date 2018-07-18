package com.luke.shop.model;

import javax.persistence.*;

/**
 * Created by luke on 2018/3/23.
 * 部门，站点
 */
@Entity
public class TU_Store extends Model{

//    @Id
//    @TableGenerator(name = "tu_store_seq",       //sequence name
//            initialValue=Static.initialValue,
//            table = "seq_table",              //sequence table
//            pkColumnName = "seq_name",        //在表中对对应的sequence name 列
//            pkColumnValue = "tu_store_seq",      //在表中对对应的sequence name 值
//            valueColumnName = "num",          //值
//            allocationSize = Static.allocationSize)
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tu_store_seq")
//    private Long id;

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

    @ManyToOne
    @JoinColumn(name="comId")
    private TU_Com com ;


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

    public Boolean getCenter() {
        return isCenter;
    }

    public void setCenter(Boolean center) {
        isCenter = center;
    }

    public Boolean getZhiYing() {
        return isZhiYing;
    }

    public void setZhiYing(Boolean zhiYing) {
        isZhiYing = zhiYing;
    }

    public Boolean getZhanDianJia() {
        return isZhanDianJia;
    }

    public void setZhanDianJia(Boolean zhanDianJia) {
        isZhanDianJia = zhanDianJia;
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
