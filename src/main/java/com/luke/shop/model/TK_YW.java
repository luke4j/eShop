package com.luke.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by luke on 2018/6/15.
 */
@Entity
public class TK_YW extends Model {
    /**业务名称*/
    @Column(length = 40,nullable = false)
    private String name ;
    /**业务编码*/
    @Column(length = 40,nullable = false)
    private String bm ;
    /**业务信息*/
    @Column(length = 100,nullable = false)
    private String info ;
    /**操作方式*/
    @Column(length = 6,nullable = false)
    private String opt ;
    /**操作类型  正品，次品，残品*/
    @Column(length = 6)
    private String kind ;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBm() {
        return bm;
    }

    public void setBm(String bm) {
        this.bm = bm;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
