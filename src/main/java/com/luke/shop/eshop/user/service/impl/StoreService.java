package com.luke.shop.eshop.user.service.impl;

import com.luke.shop.eshop.base.BaseService;
import com.luke.shop.eshop.user.dao.IStoreDao;
import com.luke.shop.eshop.user.service.IStoreService;
import com.luke.shop.model.TU_Store;
import com.luke.shop.tool.LoginTuken;
import com.luke.shop.tool.vo.VOEmpty;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by luke on 2018/8/14.
 */
@Service
public class StoreService extends BaseService implements IStoreService {

    @Resource
    IStoreDao storeDao ;

    @Override
    public List<TU_Store> findAllStore_1(LoginTuken sessionTuken, VOEmpty vo) throws Exception {
        return this.storeDao.find("From TU_Store s where s.com.id=:comId",sessionTuken) ;
    }
}
