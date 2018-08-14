package com.luke.shop.eshop.user.service;

import com.luke.shop.model.TU_Store;
import com.luke.shop.tool.LoginTuken;
import com.luke.shop.tool.vo.VOEmpty;

import java.util.List;

/**
 * Created by luke on 2018/8/14.
 */
public interface IStoreService {
    List<TU_Store> findAllStore_1(LoginTuken sessionTuken, VOEmpty vo) throws Exception;
}
