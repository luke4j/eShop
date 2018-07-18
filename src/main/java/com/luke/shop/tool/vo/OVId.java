package com.luke.shop.tool.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel
public class OVId implements VO{

    @ApiModelProperty(value = "id")
    @NotNull(message = "id")
    private Long Id ;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
