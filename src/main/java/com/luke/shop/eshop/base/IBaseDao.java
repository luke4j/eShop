package com.luke.shop.eshop.base;

public interface IBaseDao {

    <T> T save(T obj) throws Exception ;
    <T> T get(Class<T> clss,Long id) throws Exception ;
    <T> T update(T obj) throws Exception ;
    <T> T delObj(T obj) throws Exception ;
    <T> T delObject(T obj) throws Exception ;
}
