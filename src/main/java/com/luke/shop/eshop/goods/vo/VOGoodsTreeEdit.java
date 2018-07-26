package com.luke.shop.eshop.goods.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by luke on 2018/7/25.
 */
@ApiModel
public class VOGoodsTreeEdit extends VOGoodsTreeAdd {

    @ApiModelProperty(value = "ID")
    private Long Id ;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
