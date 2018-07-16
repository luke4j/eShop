package com.luke.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by luke on 2018/7/12.
 */
@Entity
public class TU_LoginTuken extends Model {

    @Column(length = 50,unique = true,nullable = false)
    String cookieVal ;
    Long userId ;
    Long comId ;
    Long storeId ;
    public String getCookieVal() {
        return cookieVal;
    }

    public void setCookieVal(String cookieVal) {
        this.cookieVal = cookieVal;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getComId() {
        return comId;
    }

    public void setComId(Long comId) {
        this.comId = comId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
}
