package dao;


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
    @Before
    public void befor(){
        PropertyConfigurator.configure(this.getClass().getResource("/test_log4j.properties"));
    }

}
