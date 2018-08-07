package com.luke.shop.eshop.base.dao.impl;

import com.luke.shop.eshop.base.BaseDao;
import com.luke.shop.eshop.base.dao.IProxyDao;
import com.luke.shop.model.TK_YWLS;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by luke on 2018/7/26.
 */
@Component
public class ProxyDao extends BaseDao implements IProxyDao {


    @Override
    public void saveAll_proxyDao(List<TK_YWLS> listLs) throws Exception {
        super.saveAll(listLs) ;
    }
}
