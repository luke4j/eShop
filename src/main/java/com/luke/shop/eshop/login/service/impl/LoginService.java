package com.luke.shop.eshop.login.service.impl;

import com.luke.shop.eshop.login.dao.ILoginDao;
import com.luke.shop.eshop.login.service.ILoginService;
import com.luke.shop.eshop.login.vo.VOLogin;
import com.luke.shop.model.TU_Com;
import com.luke.shop.model.TU_Message;
import com.luke.shop.model.TU_User;
import com.luke.shop.tool.Assertion;
import com.luke.shop.tool.LK;
import com.luke.shop.tool.LKMap;
import com.luke.shop.tool.LoginTuken;
import com.luke.shop.tool.vo.VOIdName;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class LoginService implements ILoginService {

    @Resource
    private ILoginDao loginDao ;

    @Override
    public List<VOIdName> findCom_5() throws Exception {
        List<TU_Com> listCom = this.loginDao.findCom_5() ;
        final List<VOIdName>  listResult = new ArrayList<>(listCom.size()) ;

        listCom.forEach((TU_Com com)->{
            VOIdName vo = new VOIdName() ;
            vo.setId(com.getId());
            vo.setName(com.getName());
            listResult.add(vo) ;
        });
        return listResult;
    }




    @Override
    public LoginTuken findLogin_2(VOLogin vo) throws Exception {
        LoginTuken tuken = this.loginDao.findlogin_2_1(vo) ;
        Assertion.NotEmpty(tuken,"exp-00002");
        if(LoginTuken.UserType.Root.equals(tuken.getUserType())){
            return tuken ;
        }
        Assertion.NotEmpty(vo.getComId(),"请选择公司");
        Assertion.Equals(vo.getComId(),tuken.getComId(),"异常：exp-00001");
        Assertion.NotEmpty(tuken.getStoreId(),"用户没有分配站点，不能登录");

        TU_Com com = this.loginDao.get(TU_Com.class,tuken.getComId()) ;
        Assertion.True(com.getJy(),"公司已被禁用");

        TU_User user = this.loginDao.get(TU_User.class,tuken.getId()) ;
        Assertion.True(user.getB_isDel(),"用户已被禁用");
        return tuken;
    }

    @Override
    public Map<String, Object> getInfo_3(LoginTuken sessionTuken) throws Exception {
        Map<String,Object> resultMap = new HashMap<>(10) ;
        TU_User user = this.loginDao.get(TU_User.class,sessionTuken.getId()) ;
        List<TU_Message> listMessage = this.loginDao.getInfo_3_message(user) ;
        resultMap.put("msgs",listMessage) ;
        resultMap.put("role",user.getRole().getListFun());
        resultMap.put("sysTime",new Date().getTime()) ;
        return resultMap;
    }
}
