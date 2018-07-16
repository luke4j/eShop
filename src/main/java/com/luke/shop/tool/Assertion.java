package com.luke.shop.tool;

/**
 * Created by luke on 2018/3/22.
 */
public class Assertion {

    public static void Error(){
        throw AppMsgException.create("还没有写代码") ;
    }

    public static void Error(String msg) {
        throw AppMsgException.create(msg) ;
    }

    public static void Empty(Object obj,String msg){
        if(obj!=null)  throw AppMsgException.create(msg) ;
    }

    public static void NotEmpty(Object obj,String msg){
        if(obj==null)  throw AppMsgException.create(msg) ;
    }

    public static void Equals(Object obj1,Object obj2,String msg){
        NotEmpty(obj1, msg);
        NotEmpty(obj2, msg);
        if(!obj1.equals(obj2)) throw AppMsgException.create(msg) ;
    }

    public static void NotEquals(Object obj1,Object obj2,String msg){
        NotEmpty(obj1, "第一个比较对象为空");
        NotEmpty(obj2, "第二个比较对象为空");
        if(obj1.equals(obj2)) throw AppMsgException.create(msg) ;
    }

    public static void EqualsCanNull(Object obj1,Object obj2,String msg){
        if(obj1==null) return ;
        if(obj2==null) return ;
        if(!obj1.equals(obj2)) throw AppMsgException.create(msg) ;
    }
    public static void NotEqualsCanNull(Object obj1,Object obj2,String msg){
        if(obj1==null) return ;
        if(obj2==null) return ;
        if(obj1.equals(obj2)) throw AppMsgException.create(msg) ;
    }



}
