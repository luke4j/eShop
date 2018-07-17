package tool;

import com.luke.shop.tool.L;
import com.luke.shop.tool.LK;
import org.junit.Test;

public class TestLK {
    private L l = L.getl(TestLK.class) ;


    @Test
    public void test_mk5(){
        l.i(LK.MD5("sj") ) ;
    }

}
