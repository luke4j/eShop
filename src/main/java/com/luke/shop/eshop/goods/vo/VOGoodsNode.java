package com.luke.shop.eshop.goods.vo;

import com.luke.shop.tool.vo.VO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luke on 2018/7/30.
 */
@ApiModel
public class VOGoodsNode implements VO {


    private Boolean isParent = true;

    private Long count ;

    private Long id ;

    @ApiModelProperty(value = "父结点ID")
    private Long fid ;
    @ApiModelProperty(value = "分组")
    private String c_group ;
    @ApiModelProperty(value = "文字")
    private String text ;
    @ApiModelProperty(value = "节点级别")
    private Integer c_level = 0 ;

    /**0 库存，1 零订 2车房*/
    private Integer kcjb = 0 ;

    /**商品级别*/
    private String c_code ;

    private Double pin = 0.0;
    private Double pout  = 0.0 ;

    private List<VOGoodsNode> children = new ArrayList<VOGoodsNode>(100) ;


    public Double getPin() {
        return pin;
    }

    public void setPin(Double pin) {
        this.pin = pin;
    }

    public Double getPout() {
        return pout;
    }

    public void setPout(Double pout) {
        this.pout = pout;
    }

    public Integer getKcjb() {
        return kcjb;
    }

    public void setKcjb(Integer kcjb) {
        this.kcjb = kcjb;
    }

    public String getC_code() {
        return c_code;
    }

    public void setC_code(String c_code) {
        this.c_code = c_code;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

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


    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }

    public List<VOGoodsNode> getChildren() {
        return children;
    }

    public void setChildren(List<VOGoodsNode> children) {
        this.children = children;
    }

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

    public String getName() {
        return text;
    }

    public void setName(String text) {
        this.text = text;
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
