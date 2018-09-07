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


    /**
     * 对像为真断言异常
     * @param obj
     * @param msg
     */
    public static void True(Boolean obj,String msg){
        if(obj.equals(true)) AppMsgException.throwAppMsg(msg);
    }

    /**
     * 对像为假断言异常
     * @param obj
     * @param msg
     */
    public static void False(Boolean obj,String msg){
        if(obj.equals(false)) AppMsgException.throwAppMsg(msg);
    }

    /**
     * obj不为空断言异常
     * @param obj
     * @param msg
     */
    public static void Empty(Object obj,String msg){
        if(obj!=null)  throw AppMsgException.create(msg) ;
    }

    /**
     * 对象为空断言异常
     * @param obj
     * @param msg
     */
    public static void NotEmpty(Object obj,String msg){
        if(obj==null)  AppMsgException.throwAppMsg(msg);
    }

    /**
     * 两个obj1 与 obj2 不相等 断言 异常
     * @param obj1
     * @param obj2
     * @param msg
     */
    public static void Equals(Object obj1,Object obj2,String msg){
        NotEmpty(obj1, msg);
        NotEmpty(obj2, msg);
        if(!obj1.equals(obj2))
            AppMsgException.throwAppMsg(msg); ;
    }

    /**
     * obj1与obj2不相等断言异常
     * @param obj1
     * @param obj2
     * @param msg
     * @param info   可见提示
     */
    public static void Equals(Object obj1,Object obj2,String msg,String info){
        NotEmpty(obj1, msg);
        NotEmpty(obj2, msg);
        if(!obj1.equals(obj2))
            AppMsgException.throwAppMsg(msg,info); ;
    }

    /**
     * obj1与obj2相等断言异常
     * @param obj1
     * @param obj2
     * @param msg
     */
    public static void NotEquals(Object obj1,Object obj2,String msg){
        NotEmpty(obj1, "第一个比较对象为空");
        NotEmpty(obj2, "第二个比较对象为空");
        if(obj1.equals(obj2)) AppMsgException.throwAppMsg(msg);
    }

    /**
     * obj1与obj2相等断言异常 如果obj1或obj2一个为空，不做断言
     * @param obj1
     * @param obj2
     * @param msg
     */
    public static void EqualsCanNull(Object obj1,Object obj2,String msg){
        if(obj1==null) return ;
        if(obj2==null) return ;
        if(!obj1.equals(obj2)) throw AppMsgException.create(msg) ;
    }

    /**
     * obj1与obj2  不相等断言异常 如果obj1或obj2一个为空，不做断言
     * @param obj1
     * @param obj2
     * @param msg
     */
    public static void NotEqualsCanNull(Object obj1,Object obj2,String msg){
        if(obj1==null) return ;
        if(obj2==null) return ;
        if(obj1.equals(obj2)) throw AppMsgException.create(msg) ;
    }




}
