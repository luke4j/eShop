package dao.user;

import com.luke.shop.eshop.user.dao.IStoreDao;
import com.luke.shop.model.TU_Com;
import com.luke.shop.model.TU_Store;
import com.luke.shop.tool.LK;
import dao.TestBaseDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestIStoreDao extends TestBaseDao {

    @Resource
    IStoreDao storeDao ;


    @Test
    public void save5Store() throws Exception{
        List<TU_Store> listStore = new ArrayList<>(10) ;
        TU_Com com = this.storeDao.get(TU_Com.class,1l) ;
        for(int i = 1 ;i<=20;i++){
            TU_Store store = new TU_Store() ;
            store.setAddr("eshop");
            if(i==0){
                store.setCenter(true);
                store.setFid(0l);
                store.setZhiYing(true);
                store.setZhanDianJia(false);
            }else{
                store.setCenter(false);
                store.setFid(1l);
                store.setZhiYing(false);
                store.setZhanDianJia(true);
            }
            store.setName("ShopStore-"+i);
            store.setPinYin(LK.NameToPingYinLong(store.getName()));
            store.setPy(LK.NameToPingYinShort(store.getName()));
            store.setCom(com);
            store.setAddr("北京shop-"+i);
            store.setPhone("18613806"+i);
            listStore.add(store) ;
        }
        this.storeDao.saveAll(listStore) ;
    }

}
