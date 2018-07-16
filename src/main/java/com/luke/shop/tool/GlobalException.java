package com.luke.shop.tool;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class GlobalException {

    private static Logger logger = Logger.getLogger(GlobalException.class) ;

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ActionResult handleException(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        ActionResult actionResult = ActionResult.create("程序异常,请联系管理员",request,null) ;
        actionResult.setError(e);
        actionResult.setErrorMsg(""+e.getMessage());
        this.sqlBatchUpdateException(actionResult,e) ;
        actionResult.setErrorType(e.getClass().toString());
        actionResult.setSuccess(false);
        logger.error(request.getRequestURI()+"==>>"+e.getMessage());
        return actionResult ;
    }
    private void sqlBatchUpdateException(ActionResult actionResult,Exception e){

        if(e.getCause()!=null&&e.getCause() instanceof org.hibernate.exception.ConstraintViolationException){
            if(e.getCause().getCause()!=null&&e.getCause().getCause() instanceof java.sql.BatchUpdateException) {
                Throwable throwable = e.getCause().getCause();
                if(throwable.getMessage().startsWith("Duplicate entry")){
                    actionResult.setErrorMsg(throwable.getMessage().replace("Duplicate entry","已经存在"));
                }
            }
        }
    }
}
