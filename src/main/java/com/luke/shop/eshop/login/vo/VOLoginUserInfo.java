package com.luke.shop.eshop.login.vo;

import com.luke.shop.tool.vo.VO;
import io.swagger.annotations.ApiModel;

/**
 * Created by luke on 2018/7/19.
 */
@ApiModel
public class VOLoginUserInfo implements VO {
    String name ;
    String loginName ;
    String brithday ;
    String zhi_wu ;
    String xue_li ;
    String storeName ;
    String phone ;
    String addr ;
    String photo ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getBrithday() {
        return brithday;
    }

    public void setBrithday(String brithday) {
        this.brithday = brithday;
    }

    public String getZhi_wu() {
        return zhi_wu;
    }

    public void setZhi_wu(String zhi_wu) {
        this.zhi_wu = zhi_wu;
    }

    public String getXue_li() {
        return xue_li;
    }

    public void setXue_li(String xue_li) {
        this.xue_li = xue_li;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
