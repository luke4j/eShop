package dao.login;


import com.luke.shop.eshop.login.dao.ILoginDao;
import com.luke.shop.eshop.login.vo.VOLogin;
import com.luke.shop.tool.LK;
import dao.TestBaseDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestLoginDao extends TestBaseDao {

    @Resource
    ILoginDao loginDao ;

    @Test
    public void findlogin_2_1() throws Exception{
        VOLogin vo = new VOLogin() ;
        vo.setLoginName("cc");
        vo.setPassword(LK.MD5("cc"));
        loginDao.findlogin_2_1(vo) ;
    }
}
