package dao.user;

import com.luke.shop.eshop.user.dao.IRoleDao;
import com.luke.shop.model.TU_Com;
import com.luke.shop.model.TU_Fun;
import com.luke.shop.model.TU_Role;
import com.luke.shop.tool.LKMap;
import dao.TestBaseDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestIRoleDao extends TestBaseDao {

    @Resource
    IRoleDao roleDao ;

    @Test
    public void saveRole()throws Exception{
        List<TU_Fun> listFun = roleDao.find("From TU_Fun f") ;
        TU_Com com = roleDao.get(TU_Com.class,1l) ;
        TU_Role role = new TU_Role() ;
        role.setName("Root");
        role.setListFun(listFun);
        role.setCom(com);
        this.roleDao.save(role) ;
    }


    @Test
    public void deleteRole() throws Exception{
        TU_Role role = this.roleDao.getUnique("from TU_Role r where r.name=:name",new LKMap<String,Object>().putEx("name","Root")) ;
        this.roleDao.deleteRole(role.getId()) ;
    }




}
