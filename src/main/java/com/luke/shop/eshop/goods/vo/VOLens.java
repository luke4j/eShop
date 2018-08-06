package com.luke.shop.eshop.goods.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by luke on 2018/6/4.
 */
@ApiModel
public class VOLens {

    @ApiModelProperty(value = "json度数集合")
    @NotNull(message = "json度数集合不能为空")
    String lens ;

    @ApiModelProperty(value = "商品ID")
    @NotNull(message = "商品ID不能为空")
    Long goodsId ;

    @ApiModelProperty(value = "最大柱镜")
    @NotNull(message = "最大柱镜不能为空")
    Float cylMax ;
    @ApiModelProperty(value = "最小柱镜")
    @NotNull(message = "最小柱镜不能为空")
    Float cylMin ;
    @ApiModelProperty(value = "柱镜级差")
    @NotNull(message = "柱镜级差不能为空")
    Float cylPool ;



    @ApiModelProperty(value = "最大球镜")
    @NotNull(message = "最大球镜不能为空")
    Float sphMax ;
    @ApiModelProperty(value = "最小球镜")
    @NotNull(message = "最小球镜不能为空")
    Float sphMin ;
    @ApiModelProperty(value = "最大柱镜")
    @NotNull(message = "球镜级差不能为空")
    Float sphPool ;

    public Float getCylMax() {
        return cylMax;
    }

    public void setCylMax(Float cylMax) {
        this.cylMax = cylMax;
    }

    public Float getCylMin() {
        return cylMin;
    }

    public void setCylMin(Float cylMin) {
        this.cylMin = cylMin;
    }

    public Float getCylPool() {
        return cylPool;
    }

    public void setCylPool(Float cylPool) {
        this.cylPool = cylPool;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getLens() {
        return lens;
    }

    public void setLens(String lens) {
        this.lens = lens;
    }

    public Float getSphMax() {
        return sphMax;
    }

    public void setSphMax(Float sphMax) {
        this.sphMax = sphMax;
    }

    public Float getSphMin() {
        return sphMin;
    }

    public void setSphMin(Float sphMin) {
        this.sphMin = sphMin;
    }

    public Float getSphPool() {
        return sphPool;
    }

    public void setSphPool(Float sphPool) {
        this.sphPool = sphPool;
    }
}
