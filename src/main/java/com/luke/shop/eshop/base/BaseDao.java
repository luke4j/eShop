package com.luke.shop.eshop.base;

import com.luke.shop.model.Model;
import com.luke.shop.tool.AppMsgException;
import com.luke.shop.tool.Assertion;
import com.luke.shop.tool.LK;
import com.luke.shop.tool.Page;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class BaseDao {
    private static final Logger log = Logger.getLogger(BaseDao.class) ;
    @Resource
    private HibernateTemplate hibernateTemplate ;

    @Resource
    private JdbcTemplate jdbcTemplate ;

    @Resource
    private SessionFactory sessionFactory ;


    public Session getSession(){
        Session session = null ;
        try{
            session = this.sessionFactory.getCurrentSession() ;
            return session ;
        }catch (Exception e){

            log.error("当前线程没有HibernateSession\t"+e.getMessage());
            return sessionFactory.openSession() ;
        }
    }


    /**
     * hibernate session save ;
     * @param obj
     * @param <T>
     * @return
     */
    public <T> T save(T obj) throws Exception{
        if(obj==null) Assertion.Error("保存对象为空");
        this.getSession().save(obj) ;
        return obj ;
    }

    /**
     * hibernate ql　删除
     * @param ql
     * @param param
     * @return
     * @throws Exception
     */
    public boolean delete_ql(String ql,Object param) throws Exception{
        if(LK.StrIsEmpty(ql) ) Assertion.Error("delete_ql语句为空");
        if(param instanceof Map){
            Map<String,Object> p = (Map<String,Object>) param ;
            this.getSession().createQuery(ql).setProperties(p).executeUpdate() ;
        }else{
            this.getSession().createQuery(ql).setProperties(param).executeUpdate() ;
        }

        return true ;
    }

    /**
     * hibernate qh 更新
     * @param ql
     * @param param
     * @return
     * @throws Exception
     */
    public boolean update_ql(String ql,Object param) throws Exception{
        if(LK.StrIsEmpty(ql) ) Assertion.Error("dupdate_ql语句为空");
        if(param instanceof Map){
            Map<String,Object> p = (Map<String,Object>) param ;
            this.getSession().createQuery(ql).setProperties(p).executeUpdate() ;
        }else{
            this.getSession().createQuery(ql).setProperties(param).executeUpdate() ;
        }
        return true ;
    }




    /**
     * hibernate session 更新对象
     * @param obj
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T updateObj(T obj) throws Exception{
        if(obj instanceof Model){
            Model m = (Model) obj ;
            this.getSession().update(obj);
        }else{
            AppMsgException.throwAppMsg("删除对象不是映射对象");
        }
        return obj ;
    }

    /**
     * hibernate session get+update
     * @param clss
     * @param id
     * @param val
     * @param <T>
     * @param <C>
     * @return
     * @throws Exception
     */
    public <T,C> T updateObj(Class<T> clss ,Long id,C val) throws Exception{
        T obj = this.getSession().get(clss,id) ;
        BeanUtils.copyProperties(val,obj);
        this.getSession().update(obj);
        return obj ;
    }



    /**
     * hibernate 批量保存
     * @param list
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> List<T> saveAll(List<T> list) throws Exception{
        for(int i = 0 ;i<list.size();i++){
            if(i!=0&&i%20==0){
                this.getSession().flush();
            }
            this.save(list.get(i)) ;
        }
        this.getSession().flush();
        return list ;
    }

    /**
     * 以条件查询唯一值
     * @param ql
     * @param param
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T getUnique(String ql ,Object param) throws Exception{
        if(LK.StrIsEmpty(ql)) Assertion.Error("getUnique查询语句为空");
        if(param instanceof Map){
            Map<String,Object> p = (Map<String,Object>) param ;
            return (T)this.getSession().createQuery(ql).setProperties(p).uniqueResult() ;
        }else{
            return (T)this.getSession().createQuery(ql).setProperties(param).uniqueResult() ;
        }
    }

    /**
     * 以条件查询唯一值
     * @param ql
     * @param param
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T getUnique(String ql ,Object param,Class<T> toBean) throws Exception{
        if(LK.StrIsEmpty(ql)) Assertion.Error("getUnique查询语句为空");
        if(param instanceof Map){
            Map<String,Object> p = (Map<String,Object>) param ;
            return (T)this.getSession().createQuery(ql).setResultTransformer(Transformers.aliasToBean(toBean)).setProperties(param).uniqueResult() ;
        }else{
            return (T)this.getSession().createQuery(ql).setResultTransformer(Transformers.aliasToBean(toBean)).setProperties(param).uniqueResult() ;
        }
    }
    /**
     * hibernate session ql查询 <br>  注意，toBean与changeMap都为空时，查询出映射类的列表 toBean与changeMap不能同时使用
     * @param ql
     * @param param
     * @param page
     * @param toBean
     * @param changeMap
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> List<T> find(String ql ,Object param ,Page page,Class<T> toBean,Boolean changeMap) throws Exception{
        if(LK.StrIsEmpty(ql)) Assertion.Error("find查询语句为空");
        Query query = null ;
        if(LK.ObjIsNull(toBean)){
            query = this.getSession().createQuery(ql) ;
        }
        if(LK.ObjIsNotNull(toBean)){
            query.setResultTransformer(Transformers.aliasToBean(toBean)) ;
        }
        if(LK.ObjIsNotNull(changeMap)&&changeMap){
            query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP) ;
        }
        if(LK.ObjIsNotNull(param)){
            if(param instanceof Map){
                Map<String,Object> p = (Map<String,Object>) param ;
                query.setProperties(p) ;
            }else{
                query.setProperties(param) ;
            }

        }
        if(LK.ObjIsNotNull(page)){
            query.setMaxResults(page.getLimit()) ;
            query.setFirstResult(page.getStart()) ;
        }
        return query.list() ;
    }

    /**
     * hibernate session ql查询 <br>  注意，toBean与changeMap都为空时，查询出映射类的列表 toBean与changeMap不能同时使用
     * @param ql
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> List<T> find(String ql) throws Exception{
        return this.find(ql,null,null,null,null) ;
    }

    /**
     * hibernate session ql查询   <br>  注意 这是直接查询出映射类的列表
     * @param ql
     * @param param
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> List<T> find(String ql ,Object param ) throws Exception{
        return this.find(ql,param,null,null,null) ;
    }

    /**
     * hibernate session ql查询
     * @param ql
     * @param param
     * @param page
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> List<T> find(String ql ,Object param ,Page page) throws Exception{
        return this.find(ql,param,page,null,null) ;
    }

    public <T> T get(Class<T> clss,Long id) throws Exception {
        return this.getSession().get(clss,id) ;
    }

    public<T> T update(T obj) throws Exception {
        return this.updateObj(obj) ;
    }
    /**
     * hibernate session 软删除对象
     * @param obj
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T delObj(T obj) throws Exception{
        if(obj instanceof Model){
            Model m = (Model) obj ;
            m.setB_isDel(true);
            this.getSession().update(obj);
        }else{
            AppMsgException.throwAppMsg("删除对象不是映射对象");
        }
        return obj ;
    }
    /**
     * hibernate session 删除对象
     * @param obj
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T delObject(T obj) throws Exception{
        this.getSession().delete(obj);
        return obj ;
    }


}
