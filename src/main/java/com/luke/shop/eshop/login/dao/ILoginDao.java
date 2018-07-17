package com.luke.shop.eshop.login.dao;

import com.luke.shop.eshop.base.IBaseDao;
import com.luke.shop.eshop.login.vo.VOLogin;
import com.luke.shop.model.TU_Com;
import com.luke.shop.model.TU_Store;
import com.luke.shop.model.TU_User;
import com.luke.shop.tool.LoginTuken;

import java.util.List;

public interface ILoginDao extends IBaseDao {
    List<TU_Com> findCom_5() throws Exception;

    /**
     * 登录名与密码查询用户
     * @param vo
     * @return
     * @throws Exception
     */
    TU_User findLogin_2_user(VOLogin vo) throws Exception;

    /**
     * 用户公司id查询
     * @param user
     * @return
     * @throws Exception
     */
    TU_Com findLogin_2_com(TU_User user) throws Exception;

    TU_Store findLogin_2_store(TU_User user) throws Exception;

    LoginTuken findlogin_2_1(VOLogin vo)throws Exception;
}
