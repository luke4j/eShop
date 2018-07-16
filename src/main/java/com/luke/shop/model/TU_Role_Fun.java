package com.luke.shop.model;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * Created by luke on 2018/4/10.
 */
@Entity
@Table(
        indexes = {
            @Index(
                    name = "idx_tuRoleFun_roleId",
                    columnList = "roleId",//
                    unique = false
            ),
            @Index(
                    name = "idx_tuRoleFun_funId",
                    columnList = "funId",
                    unique = false
            ),
            @Index(
                    name = "idx_tuRoleFun_roleId_funId",
                    columnList = "roleId,funId",
                    unique = true
            )
        }
)
public class TU_Role_Fun extends Model {

//    @Id
//    @TableGenerator(name = "tu_role_fun_seq",       //sequence name
//            initialValue=Static.initialValue,
//            table = "seq_table",              //sequence table
//            pkColumnName = "seq_name",        //在表中对对应的sequence name 列
//            pkColumnValue = "tu_role_fun_seq",      //在表中对对应的sequence name 值
//            valueColumnName = "num",          //值
//            allocationSize = Static.allocationSize)
//
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tu_role_fun_seq")
//    private Long id;

    private Long roleId ;
    private Long funId ;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getFunId() {
        return funId;
    }

    public void setFunId(Long funId) {
        this.funId = funId;
    }
}
