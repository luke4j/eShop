package com.luke.shop.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by luke on 2018/4/10.
 * 系统角色
 */
@Entity
public class TU_Role extends Model {

    @Column(length = 40)
    private String name ;

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name="TU_Role_Fun",
            joinColumns = {@JoinColumn(name="roleId")},
            inverseJoinColumns = {@JoinColumn(name="funId")})
    private List<TU_Fun> listFun ;

    @ManyToOne
    @JoinColumn(name = "comId")
    private TU_Com com ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TU_Fun> getListFun() {
        return listFun;
    }

    public void setListFun(List<TU_Fun> listFun) {
        this.listFun = listFun;
    }

    public TU_Com getCom() {
        return com;
    }

    public void setCom(TU_Com com) {
        this.com = com;
    }
}
