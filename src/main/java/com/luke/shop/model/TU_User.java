package com.luke.shop.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by luke on 2018/3/23.
 *
 */
@Entity
@Table(
        indexes = {
                @Index(name = "idx_user_loginName" ,unique = true,columnList = "loginname")
        }
)
public class TU_User extends Model{

    public enum UserType {
        Root,//系统管理员
        GSAdmin, //公司管理员
        normal  //正常用户
    }
//    @Id
//    @TableGenerator(name = "tu_user_seq",       //sequence name
//            initialValue=Static.initialValue,
//            table = "seq_table",              //sequence table
//            pkColumnName = "seq_name",        //在表中对对应的sequence name 列
//            pkColumnValue = "tu_user_seq",      //在表中对对应的sequence name 值
//            valueColumnName = "num",          //值
//            allocationSize = Static.allocationSize)
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tu_user_seq")
//    private Long id;

    @Column(length = 80,nullable = false)
    private String name ;

    @Column(length = 40,nullable = false,unique = true)

    private String loginName ;

    @Column(length = 36,nullable = false)
    private String password ;

    @Column(nullable = false)
    private Long roleId = Static.LongDefNull;
    @Column(length = 20,nullable = false)
    private String roleName = Static.StrDefNull;
    @Column(nullable = false)
    private Long storeId = Static.LongDefNull;
    @Column(length = 36,nullable = false)
    private String storeName = Static.StrDefNull;
    @Column(nullable = false)
    private UserType userType = UserType.normal ;

    private Date brithday ;

    @Column(length = 20)
    private String zhi_wu ;

    @Column(length = 20)
    private String xue_li ;

    @Column(length = 20)
    private String phone ;
    @Column(length = 120)
    private String addr ;
    @Column(length = 120)
    private String photo ;



    @Transient
    private TU_Role role ;

    @Transient
    private TU_FunExt funExt ;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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

    public TU_Role getRole() {
        return role;
    }

    public void setRole(TU_Role role) {
        this.role = role;
    }

    public TU_FunExt getFunExt() {
        return funExt;
    }

    public void setFunExt(TU_FunExt funExt) {
        this.funExt = funExt;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id ;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBrithday() {
        return brithday;
    }

    public void setBrithday(Date brithday) {
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
}
