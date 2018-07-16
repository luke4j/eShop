package com.luke.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by luke on 2018/4/10.
 */
@Entity
public class TU_FunExt extends Model {
//    @Id
//    @TableGenerator(name = "tu_funext_seq",       //sequence name
//            initialValue=Static.initialValue,
//            table = "seq_table",              //sequence table
//            pkColumnName = "seq_name",        //在表中对对应的sequence name 列
//            pkColumnValue = "tu_funext_seq",      //在表中对对应的sequence name 值
//            valueColumnName = "num",          //值
//            allocationSize = Static.allocationSize)
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tu_funext_seq")
//    private Long id;

    private Long userId ;
    /**特殊权限*/
    @Column(length = 20)
    private String funExt ;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFunExt() {
        return funExt;
    }

    public void setFunExt(String funExt) {
        this.funExt = funExt;
    }
}
