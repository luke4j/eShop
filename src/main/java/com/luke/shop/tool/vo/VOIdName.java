package com.luke.shop.tool.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class VOIdName {
    @ApiModelProperty(value = "name")
    private String name ;
    @ApiModelProperty(value = "id")
    private Long id ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
