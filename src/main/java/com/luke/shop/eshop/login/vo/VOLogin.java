package com.luke.shop.eshop.login.vo;

import com.luke.shop.tool.vo.VO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;


@ApiModel
public class VOLogin  implements VO {

    @ApiModelProperty(value = "登录名")
    @NotNull(message = "登录名不能为空")
    @Length(min=1,message="登录名不能为空")
    private String loginName ;

    @ApiModelProperty(value = "密码")
    @NotNull(message = "密码不能为空")
    @Length(min=1,message="密码不能为空")
    private String password ;

    @ApiModelProperty(value = "公司ID")
    private Long comId ;


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

    public Long getComId() {
        return comId;
    }

    public void setComId(Long comId) {
        this.comId = comId;
    }
}
