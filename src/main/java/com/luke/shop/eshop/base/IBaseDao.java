package com.luke.shop.eshop.base;

import com.luke.shop.tool.Page;

import java.util.List;

public interface IBaseDao {

    <T> T save(T obj) throws Exception ;
    <T> T get(Class<T> clss,Long id) throws Exception ;

    /**
     * 以条件查询唯一值
     * @param ql
     * @param param
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T getUnique(String ql ,Object param) throws Exception ;

    /**
     * 以条件查询唯一值
     * @param ql
     * @param param
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T getUnique(String ql ,Object param,Class<T> toBean) throws Exception ;

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
    <T> List<T> find(String ql , Object param , Page page, Class<T> toBean, Boolean changeMap) throws Exception ;

    /**
     * hibernate session ql查询 <br>  注意，toBean与changeMap都为空时，查询出映射类的列表 toBean与changeMap不能同时使用
     * @param ql
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> List<T> find(String ql) throws Exception ;

    /**
     * hibernate session ql查询   <br>  注意 这是直接查询出映射类的列表
     * @param ql
     * @param param
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> List<T> find(String ql ,Object param ) throws Exception ;

    /**
     * hibernate session ql查询
     * @param ql
     * @param param
     * @param page
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> List<T> find(String ql ,Object param ,Page page) throws Exception ;
}
