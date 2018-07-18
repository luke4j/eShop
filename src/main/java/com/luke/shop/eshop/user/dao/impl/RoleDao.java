package com.luke.shop.eshop.user.dao.impl;

import com.luke.shop.eshop.base.BaseDao;
import com.luke.shop.eshop.user.dao.IRoleDao;
import com.luke.shop.model.TU_Role;
import org.springframework.stereotype.Component;

@Component
public class RoleDao extends BaseDao implements IRoleDao {

    @Override
    public TU_Role deleteRole(Long id) throws Exception {
        TU_Role role = this.get(TU_Role.class,id) ;
        this.delObject(role) ;
        return role;
    }
}
