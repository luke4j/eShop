package com.luke.shop.tool;

import org.apache.log4j.Logger;

public class AppMsgException extends RuntimeException{

    private static Logger log = Logger.getLogger(AppMsgException.class) ;
    private AppMsgException(){}
    private AppMsgException(String msg){ super(msg);}

    private String info ;

    public static AppMsgException create(String msg){
        log.error("\n AppMsgException == >>\n"+msg);
        AppMsgException appMsgException = new AppMsgException(msg) ;
        return appMsgException ;
    }

    public static void throwAppMsg(String msg){
         throw create(msg) ;
    }
    public static void throwAppMsg(String msg,String info){
        AppMsgException appMsgException = create(msg) ;
        appMsgException.info = info ;
        throw appMsgException ;
    }

    @Override
    public void printStackTrace() {
        if(this.info!=null)
            log.debug(this.getMessage()+"\t"+this.info);
        super.printStackTrace();
    }
}
