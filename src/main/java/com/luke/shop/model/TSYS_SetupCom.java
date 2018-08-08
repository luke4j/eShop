package com.luke.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by luke on 2018/7/27.
 */
@Entity
public class TSYS_SetupCom extends Model{

    @Column(length = 30,nullable = false)
    private String name ;

    @Column(length = 100,nullable = false)
    private String val ;

    @Column(length = 100)
    private String ext1 ;

    @Column(length = 100)
    private String ext2 ;
    @Column(length = 100)
    private String ext3 ;
    @Column(length = 100)
    private String ext4 ;
    @Column(length = 100)
    private String ext5 ;

    @Column(length = 200,nullable = false)
    private String  note  ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comId",foreignKey = @ForeignKey(name = "fk_setupCom_com"))
    @JsonIgnore
    private TU_Com com  ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public TU_Com getCom() {
        return com;
    }

    public void setCom(TU_Com com) {
        this.com = com;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }

    public String getExt4() {
        return ext4;
    }

    public void setExt4(String ext4) {
        this.ext4 = ext4;
    }

    public String getExt5() {
        return ext5;
    }

    public void setExt5(String ext5) {
        this.ext5 = ext5;
    }
}
