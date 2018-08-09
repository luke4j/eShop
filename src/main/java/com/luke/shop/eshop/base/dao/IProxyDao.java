package com.luke.shop.eshop.base.dao;

import com.luke.shop.eshop.base.IBaseDao;
import com.luke.shop.model.TK_YWLS;

import java.util.List;

/**
 * Created by luke on 2018/7/26.
 */
public interface IProxyDao extends IBaseDao{
    void saveAll_proxyDao(List<TK_YWLS> listLs) throws Exception;
    void update_proxyDao(Object obj)throws Exception;
}
