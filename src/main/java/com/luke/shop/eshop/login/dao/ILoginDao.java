package com.luke.shop.eshop.login.dao;

import com.luke.shop.eshop.base.IBaseDao;
import com.luke.shop.eshop.login.vo.VOLogin;
import com.luke.shop.eshop.login.vo.VOLoginEditPassword;
import com.luke.shop.model.*;
import com.luke.shop.tool.LoginTuken;

import java.util.List;

public interface ILoginDao extends IBaseDao {
    List<TU_Com> findCom_5() throws Exception;


    LoginTuken findlogin_2_1(VOLogin vo)throws Exception;

    List<TU_Message> getInfo_3_message(TU_User user)throws Exception;

    void editPassword_6(VOLoginEditPassword vo,Long userId)throws Exception;

    List<TSYS_SetupCom> getInfo_3_system_setup(Long comId)throws Exception;
}
