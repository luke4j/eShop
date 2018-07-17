package dao.user;

import com.luke.shop.eshop.login.vo.VOLogin;
import com.luke.shop.eshop.user.dao.impl.UserDao;
import com.luke.shop.model.TU_User;
import com.luke.shop.tool.LK;
import com.luke.shop.tool.LoginTuken;
import dao.TestBaseDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;



@RunWith(SpringJUnit4ClassRunner.class)
public class TestIUserDao extends TestBaseDao {


    @Resource
    UserDao userDao ;

    @Test
    public void saveUser() throws Exception{
        TU_User user = new TU_User() ;
        user.setBrithday(LK.StrToDate_YMD("2001-01-01"));
        user.setLoginName("cc");
        user.setName("cc");
        user.setPassword(LK.MD5("cc"));
        user.setAddr("北京");
        user.setPhone("18613806246");
        user.setXue_li("小学");
        user.setZhi_wu("CEO");
        user.setUserType(LoginTuken.UserType.normal);

        this.userDao.save(user) ;
    }



}
