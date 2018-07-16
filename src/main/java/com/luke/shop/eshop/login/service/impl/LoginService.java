package com.luke.shop.eshop.login.service.impl;

import com.luke.shop.eshop.login.dao.ILoginDao;
import com.luke.shop.eshop.login.service.ILoginService;
import com.luke.shop.model.TU_Com;
import com.luke.shop.tool.vo.VOIdName;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService implements ILoginService {

    @Resource
    private ILoginDao loginDao ;

    @Override
    public List<VOIdName> findCom_5() throws Exception {
        List<TU_Com> listCom = this.loginDao.findCom_5() ;
        List<VOIdName> listResult = new ArrayList<>(listCom.size()) ;

        listCom.forEach((TU_Com com)->{
            VOIdName vo = new VOIdName() ;
            vo.setId(com.getId());
            vo.setName(com.getName());
            listResult.add(vo) ;
        });
        return null;
    }
}
