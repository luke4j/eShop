package com.luke.shop.eshop.goods.vo;

import javax.persistence.Column;

/**
 * Created by luke on 2018/8/7.
 */
public class VOKCNum {

    private Long num_zheng_pin = 0l ;

    private Long num_ci_pin = 0l;

    private Long num_can_pin = 0l;

    private Long num_zeng_pin  = 0l;

    private Long num_need  = 0l;

    public Long getNum_zheng_pin() {
        return num_zheng_pin;
    }

    public void setNum_zheng_pin(Long num_zheng_pin) {
        this.num_zheng_pin = num_zheng_pin;
    }

    public Long getNum_ci_pin() {
        return num_ci_pin;
    }

    public void setNum_ci_pin(Long num_ci_pin) {
        this.num_ci_pin = num_ci_pin;
    }

    public Long getNum_can_pin() {
        return num_can_pin;
    }

    public void setNum_can_pin(Long num_can_pin) {
        this.num_can_pin = num_can_pin;
    }

    public Long getNum_zeng_pin() {
        return num_zeng_pin;
    }

    public void setNum_zeng_pin(Long num_zeng_pin) {
        this.num_zeng_pin = num_zeng_pin;
    }

    public Long getNum_need() {
        return num_need;
    }

    public void setNum_need(Long num_need) {
        this.num_need = num_need;
    }
}
