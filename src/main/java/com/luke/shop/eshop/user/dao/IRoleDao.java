package com.luke.shop.eshop.user.dao;

import com.luke.shop.eshop.base.IBaseDao;
import com.luke.shop.model.TU_Role;

public interface IRoleDao extends IBaseDao {

    TU_Role deleteRole(Long id ) throws Exception ;

}
