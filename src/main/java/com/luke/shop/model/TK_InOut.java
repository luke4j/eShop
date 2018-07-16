package com.luke.shop.model;

import javax.persistence.Entity;

/**
 * Created by luke on 2018/6/15.
 */
@Entity
public class TK_InOut  extends  Model{

    /**
     * 库存ID
     */
    private Long kuId ;
    /**
     * 业务ID
     */
    private Long ywId ;
    /**
     * 操作数量
     */
    private Long num ;
    /**
     * 操作后数量
     */
    private Long overNum ;
    /**
     * 操作人
     */
    private Long userId ;

    /***
     * 单据ID
     */
    private String listId ;
    /**
     * 单据存储表
     */
    private String listTable ;

    public String getListTable() {
        return listTable;
    }

    public void setListTable(String listTable) {
        this.listTable = listTable;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public Long getKuId() {
        return kuId;
    }

    public void setKuId(Long kuId) {
        this.kuId = kuId;
    }

    public Long getYwId() {
        return ywId;
    }

    public void setYwId(Long ywId) {
        this.ywId = ywId;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Long getOverNum() {
        return overNum;
    }

    public void setOverNum(Long overNum) {
        this.overNum = overNum;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
