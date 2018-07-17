package com.luke.shop.tool;

import com.luke.shop.model.TU_Com;
import com.luke.shop.model.TU_Store;
import com.luke.shop.model.TU_User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginTuken {

    public static LoginTuken createTuken(TU_User user, TU_Com com , TU_Store store){
        Assertion.NotEmpty(user,"createTuken方法参数user 为 null 异常");

        LoginTuken tuken = new LoginTuken() ;
        tuken.id = user.getId();
        tuken.name = user.getName() ;
        tuken.userType = user.getUserType() ;

        if(LK.ObjIsNotNull(com)){
            tuken.comId = com.getId() ;
            tuken.comName = com.getName() ;

        }
        if(LK.ObjIsNotNull(store)){
            tuken.storeId = store.getId() ;
            tuken.storeName = store.getName() ;
        }
        return tuken ;
    }

    public static LoginTuken createTuken(Long userId,Long comId,Long storeId,String userName,String comName,String storeName,UserType userType){
        LoginTuken tuken = new LoginTuken() ;
        tuken.id = userId;
        tuken.comId = comId ;
        tuken.storeId = storeId ;
        tuken.name = userName ;
        tuken.comName = comName ;
        tuken.storeName = storeName ;
        tuken.userType = userType ;
        return tuken ;
    }

    public static final String Tuken = "Tuken" ;

    public static  void setSessionTuken(HttpServletRequest request,HttpServletResponse response,LoginTuken tuken){
        request.setAttribute(Tuken,tuken);
    }

    public LoginTuken getSessionTuken(HttpServletRequest request){
        return (LoginTuken)request.getSession().getAttribute(Tuken);
    }






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
