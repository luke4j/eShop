package com.luke.shop.eshop.goods.vo;

import com.luke.shop.tool.vo.VO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by luke on 2018/7/30.
 */
@ApiModel
public class VOGoodsNode implements VO {


    private Long id ;

    @ApiModelProperty(value = "父结点ID")
    private Long fid ;
    @ApiModelProperty(value = "分组")
    private String c_group ;
    @ApiModelProperty(value = "文字")
    private String text ;
    @ApiModelProperty(value = "级别")
    private Integer c_level = 0 ;



    /**
     * {kind:'是否度数'}
     */
    private String a1 ;
    /**
     * {kind:'是否实物'}
     */
    private String a2 ;
    /**
     * {kind:"是否效期'}
     */
    private String a3 ;
    private String a4 ;
    private String a5 ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
