package com.luke.shop.tool;

public class LoginTuken {

    public static final String CurrentUser = "CurrentUser" ;
    public static final String CurrentCom = "CurrentCom" ;
    public static final String CurrentStore = "CurrentStore" ;

    public enum UserType {
        Root,//系统管理员
        GSAdmin, //公司管理员
        normal  //正常用户
    }

    private Long id ;
    private Long comId ;
    private Long storeId ;
    private UserType userType = UserType.normal ;

    private String name ;
    private String loginName ;
    private String comName ;
    private String storeName ;

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

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
}
