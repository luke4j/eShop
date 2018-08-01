package com.luke.shop.eshop.test.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luke on 2018/8/1.
 */
public class VOTreeNode {

    Long id ;
    Long name ;


    List<VOTreeNode> children = new ArrayList<>(100) ;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getName() {
        return name;
    }

    public void setName(Long name) {
        this.name = name;
    }

    public List<VOTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<VOTreeNode> children) {
        this.children = children;
    }
}
