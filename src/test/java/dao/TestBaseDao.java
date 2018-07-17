package dao;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = {
        "classpath:test_applicationContext.xml",
        "classpath:test_applicationContext-db.xml",
})
public class TestBaseDao {
    @Before
    public void befor(){
        PropertyConfigurator.configure(this.getClass().getResource("/test_log4j.properties"));
    }
}
