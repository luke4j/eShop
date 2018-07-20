package com.luke.shop.eshop.goods.vo;

import com.luke.shop.tool.vo.VO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Created by luke on 2018/7/20.
 */
@ApiModel
public class VOGoodsTreeAdd implements VO{

    @ApiModelProperty(value = "父id")
    private Long fid ;


    /**品类，品牌，型号，颜色，分为四组*/
    @ApiModelProperty(value = "分组")
    @NotNull(message = "分组不能为空")
    @Length(min=1,message="分组不能为空")
    private String c_group ;

    @ApiModelProperty(value = "名称")
    @NotNull(message = "名称不能为空")
    @Length(min=1,message="名称不能为空")
    private String text ;

    /**品类 1，品牌 2，型号 3，颜色 4，分为个级别*/
    @ApiModelProperty(value = "级别")
    private Integer c_level = 0 ;

    @ApiModelProperty(value = "不同组表示不再的扩展信息")
    private String a1 ;
    @ApiModelProperty(value = "不同组表示不再的扩展信息")
    private String a2 ;
    @ApiModelProperty(value = "不同组表示不再的扩展信息")
    private String a3 ;
    @ApiModelProperty(value = "不同组表示不再的扩展信息")
    private String a4 ;
    @ApiModelProperty(value = "不同组表示不再的扩展信息")
    private String a5 ;

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public String getC_group() {
        return c_group;
    }

    public void setC_group(String c_group) {
        this.c_group = c_group;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getC_level() {
        return c_level;
    }

    public void setC_level(Integer c_level) {
        this.c_level = c_level;
    }

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public String getA3() {
        return a3;
    }

    public void setA3(String a3) {
        this.a3 = a3;
    }

    public String getA4() {
        return a4;
    }

    public void setA4(String a4) {
        this.a4 = a4;
    }

    public String getA5() {
        return a5;
    }

    public void setA5(String a5) {
        this.a5 = a5;
    }
}
