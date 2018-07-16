package com.luke.shop.tool;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by luke on 2018/4/19.
 */
public class L {
    private L(){} ;
    private String tag = null ;

    private static String t(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS") ;
        Date now = new Date() ;
        return df.format(now) ;
    }

    public static L getl(String tag){
        L l = new L() ;
        l.tag = tag ;
        return l ;
    }
    public static L getl(Class clazz){
        L l = new L() ;
        l.tag = clazz.getName() ;
        return l ;
    }

    public void i(String msg){
        this.i(this.tag,msg);
    }
    public void d(String msg){
        this.d(this.tag, msg);
    }
    public void e(String msg){
        this.e(this.tag,msg);
    }


    public void i(String tag,String msg){
        System.out.println("[LK INFO]\t"+t()+"\t"+tag+"\t"+msg );
    }
    public void e(String tag,String msg){
        System.err.println("[LK ERROR]\t" + t() + "\t" + tag + "\t" + msg);
    }
    public void d(String tag,String msg){
        System.out.println("[LK DEBUG]\t"+t()+"\t"+tag+"\t"+msg );
    }
}
