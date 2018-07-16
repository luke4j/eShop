package com.luke.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by luke on 2018/5/8.
 */
@Entity
public class TCW_ND extends Model{
//    @Id
//    @TableGenerator(name = "tu_cwnd_seq",       //sequence name
//            initialValue=Static.initialValue,
//            table = "seq_table",              //sequence table
//            pkColumnName = "seq_name",        //在表中对对应的sequence name 列
//            pkColumnValue = "tu_cwnd_seq",      //在表中对对应的sequence name 值
//            valueColumnName = "num",          //值
//            allocationSize = Static.allocationSize)
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tu_cwnd_seq")
//    private Long id;
    @Column(length = 10,nullable = false)
    private String yyyy ;
    @Column(length = 10,nullable = false)
    private String yyyymm ;


    public String getYyyy() {
        return yyyy;
    }

    public void setYyyy(String yyyy) {
        this.yyyy = yyyy;
    }

    public String getYyyymm() {
        return yyyymm;
    }

    public void setYyyymm(String yyyymm) {
        this.yyyymm = yyyymm;
    }

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public void setId(Long id) {

    }
}
