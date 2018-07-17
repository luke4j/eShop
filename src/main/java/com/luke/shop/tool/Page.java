package com.luke.shop.tool;

/**
 * Created by luke on 2018/4/11.
 */
public class Page {
    private Long count ;
    private Integer start = 0;
    private Integer limit = 10 ;


    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
