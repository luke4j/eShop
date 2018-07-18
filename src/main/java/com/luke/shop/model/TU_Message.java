package com.luke.shop.model;

import javax.persistence.*;

@Entity
public class TU_Message extends Model{

    public enum CType {
        GeRen,//个人消息
        ZhanDian, //站点消息
        JiaoSe,//角色信息
        AllCom,//全公司消息
        All    //全系统消息
    }


    @Column(length = 500,nullable = false)
    private String msg ;
    @Column(length = 50,nullable = false)
    private String title ;

    /**1:个人，2：角色，3：全公司，4：全体*/
    @Enumerated(EnumType.STRING)
    @Column(length = 12,nullable = false)
    private CType c_type = CType.GeRen ;

    @ManyToOne
    @JoinColumn(name = "readerId",nullable = false)
    private TU_User reader ;

    private Boolean isRead = false ;


    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CType getC_type() {
        return c_type;
    }

    public void setC_type(CType c_type) {
        this.c_type = c_type;
    }

    public TU_User getReader() {
        return reader;
    }

    public void setReader(TU_User reader) {
        this.reader = reader;
    }
}
