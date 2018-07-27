package com.luke.shop.eshop.goods.dao.impl;

import com.luke.shop.eshop.base.BaseDao;
import com.luke.shop.eshop.goods.dao.IGoodsDao;
import com.luke.shop.model.TG_Goods;
import com.luke.shop.model.TK_KC;
import com.luke.shop.model.TU_Com;
import com.luke.shop.model.TU_Store;
import com.luke.shop.tool.LKMap;
import com.luke.shop.tool.LoginTuken;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luke on 2018/7/27.
 */
@Component
public class GoodsDao extends BaseDao implements IGoodsDao {
    @Override
    public void addGoods_1_add_kc(LoginTuken sessionTuken, TG_Goods goods) throws Exception {
        TU_Com com = this.get(TU_Com.class,sessionTuken.getComId()) ;
        /**没有被删除，有库存的站点*/
        List<TU_Store> listStore = this.find("From TU_Store s where s.com.id=:comId and s.isHasKc=true and s.b_isDel=false", new LKMap<String,Object>().putEx("comId",sessionTuken.getComId())) ;
        List<TK_KC> listKc = new ArrayList<TK_KC>(listStore.size()) ;
        TK_KC kc = null ;
        for(TU_Store store :listStore){
            kc = new TK_KC() ;
            kc.setStore(store);
            kc.setCom(com);
            kc.setGoods(goods);
            listKc.add(kc) ;
        }
        this.saveAll(listKc) ;
    }
}
