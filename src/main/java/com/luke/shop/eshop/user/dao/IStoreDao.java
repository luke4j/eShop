package com.luke.shop.eshop.user.dao;

import com.luke.shop.eshop.base.IBaseDao;

import java.util.List;

public interface IStoreDao extends IBaseDao {

    <T> List<T> saveAll(List<T> list) throws Exception ;
}
