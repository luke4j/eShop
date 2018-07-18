package com.luke.shop.eshop.login.service;

import com.luke.shop.eshop.login.vo.VOLogin;
import com.luke.shop.tool.LKMap;
import com.luke.shop.tool.LoginTuken;
import com.luke.shop.tool.vo.VOIdName;

import java.util.List;
import java.util.Map;

public interface ILoginService {
    /**
     * 查询公司
     * @return
     * @throws Exception
     */
    List<VOIdName> findCom_5() throws  Exception;

    /**
     * 查询登录用户信息
     * @param vo
     * @return
     * @throws Exception
     */
    LoginTuken findLogin_2(VOLogin vo) throws  Exception;

    /**
     * 获取当前用户的详细信息,系统时间，当前操作人权限 ，信息，配置
     * @param sessionTuken
     * @return
     */
    Map<String,Object> getInfo_3(LoginTuken sessionTuken)  throws Exception;
}
