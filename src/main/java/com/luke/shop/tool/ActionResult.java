package com.luke.shop.tool;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luke on 2018/3/22.
 */
public class ActionResult {

    private static Logger log = Logger.getLogger(ActionResult.class) ;


    private List<Object> params = new ArrayList<Object>(10) ;
    private HttpServletRequest request ;
    private HttpServletResponse response ;
    private ActionResult actionResult ;

    public void init(HttpServletRequest request,HttpServletResponse response,Object... objs) {
        this.request = request ;
        this.response = response ;
        this.actionResult = this ;
        Object loginTuken = this.request.getSession().getAttribute(LoginTuken.Tuken) ;
        if(LK.ObjIsNotNull(loginTuken)){
            LoginTuken tuken  = (LoginTuken)loginTuken ;
            this.userId = tuken.getId() ;
            this.userName = tuken.getName() ;
            this.loginName = tuken.getLoginName() ;
            this.setStoreid(tuken.getStoreId());
            this.setStoreName(tuken.getStoreName());
        }
        this.url = request.getRequestURI() ;
        this.success = true ;
        for(Object obj :objs){
            params.add(obj);
        }
        this.jsonParams = JSONArray.fromObject(params).toString() ;
    }
    public static ActionResult create (String doing ,HttpServletRequest request,HttpServletResponse response){
        ActionResult actionResult = new ActionResult() ;
        actionResult.actionResult = actionResult ;
        actionResult.request = request ;
        actionResult.response = response ;
        actionResult.doing = doing ;
        Object loginTuken = request.getSession().getAttribute(LoginTuken.Tuken) ;
        if(LK.ObjIsNotNull(loginTuken)){
            LoginTuken tuken = (LoginTuken)loginTuken ;
            actionResult.userId = tuken.getId() ;
            actionResult.userName = tuken.getName() ;
            actionResult.loginName = tuken.getLoginName() ;
            actionResult.setStoreid(tuken.getStoreId());
            actionResult.setStoreName(tuken.getStoreName());
        }

        actionResult.url = request.getRequestURI() ;

        JSONObject jsonObject = JSONObject.fromObject(request.getParameterMap()) ;
        actionResult.jsonParams = jsonObject.toString() ;
        actionResult.success = true ;
        return actionResult ;
    }

    public static ActionResult create(HttpServletRequest request,HttpServletResponse response){
        return create(null,request,response) ;
    }

    /**程序处理成功*/
    public void OK(Object data,String... excludes){
        List<String> exc = new ArrayList<String>(20) ;
        exc.add("request");
        exc.add("response");
        exc.add("actionResult");
        for(String ex:excludes){
            exc.add(ex);
        }
        this.data = data ;
        JsonConfig jsonConfig = new JsonConfig() ;
        jsonConfig.setExcludes(exc.toArray(new String[100])) ;
        JSONObject jsonObject = JSONObject.fromObject(this,jsonConfig) ;
        String strJson = jsonObject.toString() ;
        log.info(strJson);
        this.write(strJson);
    }
    /**程序处理成功*/
    public void OK(Object obj){
        this.OK(obj, null);
    }
    /**程序处理失败*/
    public void Fail(Throwable error){
        this.error = error ;
        this.errorType = error.getClass().toString() ;
        this.errorMsg = error.getMessage() ;
        if(LK.StrIsNotEmpty(this.errorMsg)){
            if(!error.getClass().equals(AppMsgException.class)){
                this.errorMsg = "程序异常" ;
                log.error("程序异常",error);
            }else{
                this.errorMsg = "程序未做处理异常" ;
                log.error("程序未做处理异常",error);
            }
        }
        this.OK(null);
    }

    /**以json形式写入流*/
    private void write(String json){
        this.response.setCharacterEncoding("UTF-8");
        this.response.setContentType("application/Json");
        try {
            PrintWriter pw = response.getWriter();
            pw.print(json);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**请求成功数据*/
    private Object data ;
    /**扩展信息*/
    private LKMap<String,Object> ext ;
    /**总条数*/
    private Long total ;
    /**请求错误信息*/
    private String errorMsg ;
    /**请求错误类型*/
    private String errorType ;
    /**请求目地*/
    private String doing ;
    /**请求url*/
    private String url ;
    /**请求参数*/
    private String jsonParams ;
    /**请求异常*/
    private Throwable error ;
    /**请求人id*/
    private Long userId ;
    /**请求人姓名*/
    private String userName ;
    /**请求人登录名*/
    private String loginName ;
    /**请求人所在站点*/
    private Long storeid ;
    /**请求人所在站点名*/
    private String storeName ;

    /**请求是否成功*/
    private Boolean success ;

    /**重要通知*/
    private String zytz ;

    public String getZytz() {
        return zytz;
    }

    public void setZytz(String zytz) {
        this.zytz = zytz;
    }

    public LKMap<String, Object> getExt() {
        return ext;
    }

    public void setExt(LKMap<String, Object> ext) {
        this.ext = ext;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Object total) {
        this.total = Long.parseLong(total.toString());
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getDoing() {
        return doing;
    }

    public void setDoing(String doing) {
        this.doing = doing;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getJsonParams() {
        return jsonParams;
    }

    public void setJsonParams(String jsonParams) {
        this.jsonParams = jsonParams;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }



    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStoreid() {
        return storeid;
    }

    public void setStoreid(Long storeid) {
        this.storeid = storeid;
    }
}
