package com.luke.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by luke on 2018/4/10.
 */
@Entity
public class T_Message extends Model {

    public enum CType {
        GeRen,//个人消息
        ZhanDian, //站点消息
        JiaoSe,//角色信息
        AllCom,//全公司消息
        All    //全系统消息
    }

//    @Id
//    @TableGenerator(name = "t_message_seq",       //sequence name
//            initialValue=Static.initialValue,
//            table = "seq_table",              //sequence table
//            pkColumnName = "seq_name",        //在表中对对应的sequence name 列
//            pkColumnValue = "t_message_seq",      //在表中对对应的sequence name 值
//            valueColumnName = "num",          //值
//            allocationSize = Static.allocationSize)
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "t_message_seq")
//    private Long id ;

    @Column(length = 500,nullable = false)
    private String msg ;
    @Column(length = 50,nullable = false)
    private String title ;

    /**1:个人，2：角色，3：全公司，4：全体*/
    private CType c_type ;
    private Long readerId ;

    /**有效期*/
    private Date xiao_qi ;
    private Boolean isRead = false;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Date getXiao_qi() {
        return xiao_qi;
    }

    public void setXiao_qi(Date xiao_qi) {
        this.xiao_qi = xiao_qi;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CType getC_type() {
        return c_type;
    }

    public void setC_type(CType c_type) {
        this.c_type = c_type;
    }

    public Long getReaderId() {
        return readerId;
    }

    public void setReaderId(Long readerId) {
        this.readerId = readerId;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }
}
