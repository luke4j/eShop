package com.luke.shop.eshop.goods.vo;

import com.luke.shop.tool.vo.VO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by luke on 2018/5/31.
 */
@ApiModel
public class VOGoodsEdit extends VOGoods implements VO {
    @ApiModelProperty(value = "ID")
    @NotNull(message = "商品ID不能为空")
    Long id ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
