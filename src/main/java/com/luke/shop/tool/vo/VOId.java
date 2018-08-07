package com.luke.shop.tool.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel
public class VOId implements VO{

    public VOId(){}
    public VOId(Long id ){
        this.id = id ;
    }

    @ApiModelProperty(value = "id")
    @NotNull(message = "id")
    private Long id ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
