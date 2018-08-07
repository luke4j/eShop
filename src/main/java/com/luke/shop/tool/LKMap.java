package com.luke.shop.tool;

import java.util.HashMap;

/**
 * Created by luke on 2018/4/18.
 */
public class LKMap<K,V> extends HashMap<K,V> {

    public static  LKMap create(){
        return new LKMap<>() ;
    }

    public LKMap<K,V> putEx(K k,V v){
        super.put(k, v) ;
        return this ;
    }
    public LKMap<K,V> putAllEx(HashMap<? extends K,? extends V> map){
        super.putAll(map);
        return this ;
    }


}
