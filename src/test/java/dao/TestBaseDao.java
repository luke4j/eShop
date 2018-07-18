package dao;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = {
        "classpath:test_applicationContext.xml",
        "classpath:test_applicationContext-db.xml",
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback(false)
public class TestBaseDao {
        private static Logger log  = Logger.getLogger(TestBaseDao.class) ;
    /**
     * 注意，dao是空数据库测试
     */
    @Before
    public void befor(){
        log.warn("注意，dao是空数据库测试");
        PropertyConfigurator.configure(this.getClass().getResource("/test_log4j.properties"));
    }

}
