package dao.user;

import com.luke.shop.eshop.user.dao.IComDao;
import com.luke.shop.model.TU_Com;
import dao.TestBaseDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestIComDao extends TestBaseDao {

    @Resource
    IComDao comDao ;


    @Test

    public void save() throws Exception{
        TU_Com com = new TU_Com() ;
        com.setAddr("北京大六环");
        com.setAdminId(1l);
        com.setAdminPassword("cc");
        com.setIsJy(false);
        com.setName("MyShop");
        com.setPhone("18613806246");

        comDao.save(com) ;
    }

}
