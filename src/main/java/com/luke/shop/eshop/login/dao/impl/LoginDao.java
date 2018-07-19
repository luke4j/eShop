package com.luke.shop.eshop.login.dao.impl;

import com.luke.shop.eshop.base.BaseDao;
import com.luke.shop.eshop.login.dao.ILoginDao;
import com.luke.shop.eshop.login.vo.VOLogin;
import com.luke.shop.eshop.login.vo.VOLoginEditPassword;
import com.luke.shop.model.TU_Com;
import com.luke.shop.model.TU_Message;
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
    public LoginTuken findlogin_2_1(VOLogin vo) throws Exception {
        vo.setPassword(vo.getPassword().toUpperCase());
        LoginTuken tuken = this.getUnique("Select u.id as id ,u.name as name,u.loginName as loginName,u.userType as userType ,c.id as comId,c.name as comName,s.id as storeId ,s.name as storeName  " +
                        "From TU_User u left join TU_Com c on u.com.id=c.id left join TU_Store s on u.store.id=s.id " +
                        "where u.loginName=:loginName and u.password=:password",
                vo,LoginTuken.class) ;
        return tuken;
    }


    @Override
    public List<TU_Message> getInfo_3_message(TU_User user) throws Exception {
        List<TU_Message> listMessage =  this.find("From TU_Message m where m.reader.id =:id",user) ;
        return listMessage;
    }


    @Override
    public void editPassword_6(VOLoginEditPassword vo,Long userId) throws Exception {
        TU_User user = this.get(TU_User.class,userId) ;
        user.setPassword(vo.getPassword().toUpperCase());
        this.update(user) ;
    }
}
