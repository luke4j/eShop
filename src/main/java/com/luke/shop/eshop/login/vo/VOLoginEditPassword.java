package com.luke.shop.eshop.login.vo;

import com.luke.shop.tool.vo.VO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@ApiModel
public class VOLoginEditPassword implements VO {

    /**密码是页面加载后专来的*/
    @ApiModelProperty(value = "密码")
    @NotNull(message = "密码不能为空")
    @Length(min=1,message="密码不能为空")
    String password ;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
