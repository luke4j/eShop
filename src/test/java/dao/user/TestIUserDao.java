package dao.user;

import com.luke.shop.eshop.login.vo.VOLogin;
import com.luke.shop.eshop.user.dao.impl.UserDao;
import com.luke.shop.model.*;
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

        TU_Com com = userDao.get(TU_Com.class,1l);
        TU_Store store = userDao.get(TU_Store.class,1l) ;
        TU_Role role = userDao.get(TU_Role.class,1l) ;

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
        user.setCom(com);
        user.setStore(store);
        user.setRole(role);

        this.userDao.save(user) ;
    }


    @Test
    public void saveMessage() throws Exception{
        TU_User user = this.userDao.get(TU_User.class,1l) ;

        TU_Message msg = null ;
        msg = new TU_Message() ;
        msg.setC_type(TU_Message.CType.GeRen);
        msg.setTitle("个人信息");
        msg.setMsg("个人信息详细");
        msg.setReader(user);
        this.userDao.save(msg) ;

        msg = new TU_Message() ;
        msg.setC_type(TU_Message.CType.ZhanDian);
        msg.setTitle("站点信息");
        msg.setMsg("站点信息详细");
        msg.setReader(user);
        this.userDao.save(msg) ;

        msg = new TU_Message() ;
        msg.setC_type(TU_Message.CType.JiaoSe);
        msg.setTitle("角色信息");
        msg.setMsg("角色信息详细");
        msg.setReader(user);
        this.userDao.save(msg) ;
    }




}
