package com.luke.shop.eshop.base.dao.impl;

import com.luke.shop.eshop.base.BaseDao;
import com.luke.shop.eshop.base.dao.ISystemSetupComDao;
import com.luke.shop.model.TSYS_SetupCom;
import com.luke.shop.tool.Assertion;
import com.luke.shop.tool.LK;
import com.luke.shop.tool.LKMap;
import org.springframework.stereotype.Component;

/**
 * Created by luke on 2018/10/24.
 */
@Component
public class SystemSetupComDao extends BaseDao implements ISystemSetupComDao {


    @Override
    public TSYS_SetupCom save_not_lens_add_price(Long comId) throws Exception {
        TSYS_SetupCom setup = this.getUnique("From TSYS_SetupCom sc where sc.id=1 and sc.com.id=:comId", new LKMap<>().putEx("comId", comId)) ;
        if(LK.ObjIsNull(setup)) Assertion.Error("请管理员配置 TSYS_SetupCom 表数据 id=1");
        return setup;
    }

    @Override
    public TSYS_SetupCom save_not_lens_add_kc(Long comId) throws Exception {
        TSYS_SetupCom setup = this.getUnique("From TSYS_SetupCom sc where sc.id=2 and sc.com.id=:comId", new LKMap<>().putEx("comId", comId)) ;
        if(LK.ObjIsNull(setup)) Assertion.Error("请管理员配置 TSYS_SetupCom 表数据  id=2");
        return setup;
    }

    @Override
    public TSYS_SetupCom save_lens_add_price(Long comId) throws Exception {
        TSYS_SetupCom setup = this.getUnique("From TSYS_SetupCom sc where sc.id=3 and sc.com.id=:comId", new LKMap<>().putEx("comId", comId)) ;
        if(LK.ObjIsNull(setup)) Assertion.Error("请管理员配置 TSYS_SetupCom 表数据 id=3");
        return setup;
    }

    @Override
    public TSYS_SetupCom save_lens_add_kc(Long comId) throws Exception {
        TSYS_SetupCom setup = this.getUnique("From TSYS_SetupCom sc where sc.id=4 and sc.com.id=:comId", new LKMap<>().putEx("comId", comId)) ;
        if(LK.ObjIsNull(setup)) Assertion.Error("请管理员配置 TSYS_SetupCom 表数据 id=4");
        return setup;
    }
}
