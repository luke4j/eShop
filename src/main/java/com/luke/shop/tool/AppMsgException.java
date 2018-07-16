package com.luke.shop.tool;

import org.apache.log4j.Logger;

public class AppMsgException extends RuntimeException{

    private static Logger logger = Logger.getLogger(AppMsgException.class) ;
    private AppMsgException(){}
    private AppMsgException(String msg){ super(msg);}

    public static AppMsgException create(String msg){
        logger.error("\n AppMsgException == >>\n"+msg);
        AppMsgException appMsgException = new AppMsgException(msg) ;
        return appMsgException ;
    }

    public static void throwAppMsg(String msg){
         throw create(msg) ;
    }

}
