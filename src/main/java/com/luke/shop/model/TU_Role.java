package com.luke.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;

/**
 * Created by luke on 2018/4/10.
 * 系统角色
 */
@Entity
public class TU_Role extends Model {

//    @Id
//    @TableGenerator(name = "tu_role_seq",       //sequence name
//            initialValue=Static.initialValue,
//            table = "seq_table",              //sequence table
//            pkColumnName = "seq_name",        //在表中对对应的sequence name 列
//            pkColumnValue = "tu_role_seq",      //在表中对对应的sequence name 值
//            valueColumnName = "num",          //值
//            allocationSize = Static.allocationSize)
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tu_role_seq")
//    private Long id;

    @Column(length = 40)
    private String name ;

    @Transient
    private List<TU_Fun> funs ;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TU_Fun> getFuns() {
        return funs;
    }

    public void setFuns(List<TU_Fun> funs) {
        this.funs = funs;
    }
}
