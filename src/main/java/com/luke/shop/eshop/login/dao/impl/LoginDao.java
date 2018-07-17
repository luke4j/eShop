package com.luke.shop.eshop.login.dao.impl;

import com.luke.shop.eshop.base.BaseDao;
import com.luke.shop.eshop.login.dao.ILoginDao;
import com.luke.shop.eshop.login.vo.VOLogin;
import com.luke.shop.model.TU_Com;
import com.luke.shop.model.TU_Store;
import com.luke.shop.model.TU_User;
import com.luke.shop.tool.Assertion;
import com.luke.shop.tool.LoginTuken;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoginDao extends BaseDao implements ILoginDao {

    @Override
    public List<TU_Com> findCom_5() throws Exception {
        return this.find("From TU_Com c") ;
    }

    @Override
    public TU_User findLogin_2_user(VOLogin vo) throws Exception {
        vo.setPassword(vo.getPassword().toUpperCase());
        TU_User user = this.getUnique("From TU_User u where u.loginName=:loginName and u.password=:password ",vo) ;
        Assertion.NotEmpty(user,"异常：exp-00002");
        return user;
    }

    @Override
    public TU_Com findLogin_2_com(TU_User user) throws Exception {
        return null;
    }

    @Override
    public TU_Store findLogin_2_store(TU_User user) throws Exception {
        return null;
    }

    @Override
    public LoginTuken findlogin_2_1(VOLogin vo) throws Exception {
        vo.setPassword(vo.getPassword().toUpperCase());
        LoginTuken tuken = this.getUnique("Select u.id as id ,u.name as name,u.userType as userType ,c.id as comId,c.name as comName,s.id as storeId ,s.name as storeName  " +
                        "From TU_User u left join TU_Com c on u.com.id=c.id left join TU_Store s on u.store.id=s.id " +
                        "where u.loginName=:loginName and u.password=:password",
                vo,LoginTuken.class) ;
        return tuken;
    }
}
