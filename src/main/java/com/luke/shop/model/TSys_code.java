package com.luke.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by luke on 2018/4/10.
 */
@Entity
public class TSys_code extends Model{
//    @Id
//    @TableGenerator(name = "tsys_code_seq",       //sequence name
//            table = "seq_table",              //sequence table
//            initialValue=Static.initialValue,
//            pkColumnName = "seq_name",        //在表中对对应的sequence name 列
//            pkColumnValue = "tsys_code_seq",      //在表中对对应的sequence name 值
//            valueColumnName = "num",          //值
//            allocationSize = Static.allocationSize)
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tsys_code_seq")
//    private Long id ;

    @Column(length = 40,nullable = false)
    private String val ;
    @Column(length = 40,nullable = false)
    private String group_name ;
    private Long fid ;

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public void setId(Long id) {

    }
}
