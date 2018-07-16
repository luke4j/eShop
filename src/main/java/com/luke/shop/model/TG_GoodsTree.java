package com.luke.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * Created by luke on 2018/5/15.
 */
@Entity
@Table(indexes = {@Index(name = "idx_tg_goodstree_c_group_fid_text",columnList = "c_group,fid,text",unique = true)})
public class TG_GoodsTree extends Model {
//    @Id
//    @TableGenerator(name = "TG_GoodsTree_seq",       //sequence name
//            table = "seq_table",              //sequence table
//            initialValue=Static.initialValue,
//            pkColumnName = "seq_name",        //在表中对对应的sequence name 列
//            pkColumnValue = "TG_GoodsTree_seq",      //在表中对对应的sequence name 值
//            valueColumnName = "num",          //值
//            allocationSize = Static.allocationSize)
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TG_GoodsTree_seq")
//    private Long id ;
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

    public Integer getC_level() {
        return c_level;
    }

    public void setC_level(Integer c_level) {
        this.c_level = c_level;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
