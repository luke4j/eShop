package com.luke.shop.tool;

import com.luke.shop.model.Model;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by luke on 2018/3/22.
 * 工具类
 */
public class LK {

    /**
     * hibernate 延时加载这个设置很烦人，所以才有这个方法 <br>
     *     listT 为 null返回null
     * @param listT
     * @param <T>
     * @return
     */
    public static <T> List<T> getModelId(List<T> listT){

        if(listT==null)
            return null ;
        listT.forEach((T t)->{
            if(t instanceof Model){
                Model m = (Model)t ;
                m.getId() ;
            }
        });
        return listT ;
    }

    /**
     *  hibernate 延时加载这个设置很烦人，所以才有这个方法<br>
     * obj为null返回null
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> T getModelId(T obj){
        if(obj==null) return null ;
        if(obj instanceof Model){
            Model m = (Model)obj ;
            m.getId() ;
        }
        return obj ;
    }

    public static String uuid(){
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    /**
     * 字符串是空
     * @param str
     * @return
     */
    public static Boolean StrIsEmpty(String str){
        return (str==null||str.trim().equals(""))?true:false ;
    }

    /**
     * 字符串不是空
     * @param str
     * @return
     */
    public static Boolean StrIsNotEmpty(String str){
        return !StrIsEmpty(str) ;
    }

    /**
     * 字符串为空返回默认值，不为空返回本身的trim
     * @param str
     * @param def
     * @return
     */
    public static String StrIsEmptyDo(String str,String def){
        return StrIsEmpty(str)?def:str.trim() ;
    }
    /**
     * 字符串是数字
     * @param str
     * @return
     */
    public static Boolean StrIsNum(String str){
        if(StrIsEmpty(str)) return false ;
        str = str.trim() ;
        if(str.startsWith(".")) return false ;
        Pattern pattern = Pattern.compile("^[\\+\\-]{0,1}[\\d]*[\\.]{0,1}[\\d]*$");
        Matcher matcher = pattern.matcher(str) ;
        return matcher.matches() ;
    }

    /**
     * 字符串是时间
     * @param str
     * @param format
     * @return
     */
    public static Boolean StrIsDate(String str,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            sdf.parse(str);
            return true ;
        } catch (Exception e) {
            return false ;
        }
    }

    /**
     * 字符串转时间
     * @param str
     * @param format
     * @return
     */
    public static Date StrToDate(String str,String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(str);
        } catch (Exception e) {
            AppMsgException.throwAppMsg("请判断是字符串是否时间") ;
        }
        return null ;
    }

    /**
     * 字符串转yyyy-MM-dd
     * @param str
     * @return
     */
    public static Date StrToDate_YMD(String str) {
        if(StrIsDate(str,"yyyy-MM-dd")){
            return StrToDate(str,"yyyy-MM-dd") ;
        }else{
            AppMsgException.throwAppMsg("请判断是字符串是否年--yyyy-MM-dd");
        }
        return null ;
    }

    /**
     * 字符串转yyyy-MM-dd hh:mm:ss
     * @param str
     * @return
     */
    public static Date StrToDate_YMDHMS(String str) {
        if(StrIsDate(str,"yyyy-MM-dd hh:mm:ss")){
            return StrToDate(str,"yyyy-MM-dd hh:mm:ss") ;
        }else{
            AppMsgException.throwAppMsg("请判断是字符串是否年月");
        }
        return null ;
    }





    /**
     * 时间转字符串
     * @param date
     * @param format
     * @return
     */
    public static String DateToStr(Date date ,String format){
        if(date == null)
            return null ;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 时间转年
     * @param date
     * @return
     */
    public static String DateToStr_Y(Date date){
        if(date == null)
            return null ;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return sdf.format(date);
    }

    /**
     * 时间转年月
     * @param date
     * @return
     */
    public static String DateToStr_YM(Date date){
        if(date == null)
            return null ;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        return sdf.format(date);
    }

    /**
     * 时间转年月日
     * @param date
     * @return
     */
    public static String DateToStr_YMD(Date date){
        if(date == null)
            return null ;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }



    /**
     * 字符转度数格式
     * @param str
     * @return
     */
    public static String Lens(String str){
        if(StrIsNum(str)){
            if(Float.parseFloat(str)==0)
                return "0" ;
            DecimalFormat df = new DecimalFormat("0.00");
            Float data = Float.parseFloat(str) ;
            if(data<=0){
                return df.format(data) ;
            }else{
                return "+"+df.format(data) ;
            }
        }else{
            throw AppMsgException.create("请正确填写数字") ;
        }
    }

    /**
     * 浮点转度数格式
     * @param data
     * @return
     */
    public static String toLensFormat(Float data){
        String str = data+"" ;
        return Lens(str) ;
    }

    /**
     * 名字转拼音 全拼
     *
     * @param name
     * @return String
     * @author llg
     */
    public static String NameToPingYinLong(String name) {
        if (name == null || name.trim().equals(""))
            return "";
        String rt = "";
        HanyuPinyinOutputFormat hypyFormate = new HanyuPinyinOutputFormat();
        hypyFormate.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        hypyFormate.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        hypyFormate.setVCharType(HanyuPinyinVCharType.WITH_V);
        try {
            for (int i = 0; i < name.length(); i++) {
                String[] ss = PinyinHelper.toHanyuPinyinStringArray(name.charAt(i), hypyFormate);
                if (name.charAt(i)<128||ss == null || ss[0].length() <= 0) {
                    rt += name.charAt(i);
                    continue;
                }
                rt += ss[0].substring(0, 1).toUpperCase() + ss[0].substring(1, ss[0].length());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rt;
    }

    /**
     * 名字转拼音 简拼（首字母）
     *
     * @param name
     * @return String
     * @author llg
     */
    public static String NameToPingYinShort(String name) {
        if (name == null || name.trim().equals(""))
            return "";
        String rt = "";
        for (int i = 0; i < name.length(); i++) {
            String[] ss = PinyinHelper.toHanyuPinyinStringArray(name.charAt(i));
            if (ss == null || ss[0].length() <= 0) {
                rt += name.charAt(i);
                continue;
            }

            rt += ss[0].substring(0, 1).toUpperCase();
        }
        if (rt.length() >= 36) {
            rt = rt.substring(0, 35);
        }

        return rt;
    }

    /**
     * md5加密（网上找的，应该靠谱）
     *
     * @param s
     * @return String
     * @author llg
     */
    public final static String MD5(String s) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 生成 json字符串，可以添加其它字符串类型属性
     * @param o
     * @param m
     * @param cs
     * @return String
     */
    public static String ObjToJsonStr(Object o, Map<String, String> m, String... cs) {
        JsonConfig jc = new JsonConfig();
        jc.setExcludes(cs);
        JSONObject json = JSONObject.fromObject(o, jc);
        for (Map.Entry<String, String> en : m.entrySet()) {
            json.put(en.getKey(), en.getValue());
        }
        return json.toString();
    }

    /**
     * 时间转年龄
     *
     * @param borthDate
     * @return int
     * @author llg
     */
    public static int BirthdayToAge(Date borthDate) {
        String now = DateToStr_Y(new Date());
        String borth = DateToStr_Y(borthDate);
        int rt = Integer.parseInt(now) - Integer.parseInt(borth);
        return rt;
    }

    /**
     * 时间转年龄
     *
     * @param borthDate
     * @return int
     * @author llg
     */
    public static int DateToAge(String borthDate) {
        return BirthdayToAge(StrToDate_YMD(borthDate));
    }


    /**
     * 对象是空
     * @param obj
     * @return
     */
    public static Boolean ObjIsNull(Object obj){
        return obj == null?true :false ;
    }

    public static Object ObjIsNullDo(Object obj ,String def){
        if(obj==null)
            return def ;
        else
            return obj ;
    }

    /**
     * 对象不是空
     * @param obj
     * @return
     */
    public static boolean ObjIsNotNull(Object obj){
        return !ObjIsNull(obj) ;
    }

    /**
     *
     * @param list
     * @param discard       丢弃属性
     * @return
     */
    public static List<Map<String,Object>> ListObjToListMap(List<?> list,Map<String,String> discard){
        List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>(list.size()) ;
        Map<String,Object> map = null ;
        for(Object obj :list){
            map = ObjToMap(obj,discard) ;
            listMap.add(map) ;
        }
        return listMap ;
    }

    /**
     * 对象转为map
     * @param obj
     * @param discard   丢弃属性
     * @return
     */
    public static Map<String, Object> ObjToMap(Object obj,Map<String,String> discard ) {
        Field[] fields = obj.getClass().getDeclaredFields();
        Map<String, Object> map = new HashMap<String, Object>(fields.length);
        boolean hasDiscard = discard!=null ;
        try {
            for (Field f : fields) {
                /**判断属性是否被丢弃*/
                if(hasDiscard){
                    if(discard.get(f.getName())!=null)
                        continue;
                }
                f.setAccessible(true);
                map.put(f.getName(), f.get(obj));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        ObjSuperToMap(map,obj.getClass().getSuperclass(),obj,discard);
        return map;
    }
    private static void ObjSuperToMap(Map<String,Object> map,Class clazz,Object obj,Map<String,String> discard){
        if(clazz.equals(Object.class))
            return ;
        else{
            Field[] fields = clazz.getDeclaredFields() ;
            try{
                boolean hasDiscard = discard!=null ;
                for(Field f: fields){
                    /**判断属性是否被丢弃*/
                    if(hasDiscard){
                        if(discard.get(f.getName())!=null)
                            continue;
                    }
                    f.setAccessible(true);
                    map.put(f.getName(),f.get(obj)) ;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            ObjSuperToMap(map, clazz.getSuperclass(), obj,discard);
        }
    }


    /**
     * 为指定的时间添加天数，可以是负数，就是前几天
     * @param time
     * @param num
     * @return
     */
    public static Date AddDay(Date time,int num){
        Calendar calendar = Calendar.getInstance() ;
        calendar.setTime(time);
        calendar.add(Calendar.DATE,num);
        return calendar.getTime() ;
    }

}
