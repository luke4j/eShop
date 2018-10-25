package com.luke.shop.eshop.base.dao;

import com.luke.shop.model._YW;

/**
 * Created by luke on 2018/10/25.
 * 保存业务单据明细接口，内部类调用外部类数据
 */
public interface ISaveBillMx {
    void save(_YW ywdj) throws Exception;

}
