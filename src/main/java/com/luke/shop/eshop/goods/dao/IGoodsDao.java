package com.luke.shop.eshop.goods.dao;

import com.luke.shop.eshop.base.IBaseDao;
import com.luke.shop.eshop.goods.vo.VOGoods;
import com.luke.shop.eshop.goods.vo.VOLens;
import com.luke.shop.model.*;
import com.luke.shop.tool.LoginTuken;
import com.luke.shop.tool.vo.VOId;

import java.util.List;

/**
 * Created by luke on 2018/7/27.
 */
public interface IGoodsDao extends IBaseDao {

    /**
     * 添加商品时添加默认库存
     * @param goods
     * @return
     * @throws Exception
     */
    void addGoods_1_kc(TG_Goods goods, VOGoods vo)throws  Exception;

    /**
     * 添加商品时，添加默认价格
     * @param goods
     * @return
     * @throws Exception
     */
    TG_Price addGoods_1_price(TG_Goods goods, VOGoods vo)throws  Exception;


    /**
     * 查询度数配置
     * @param vo
     * @return
     * @throws Exception
     */
    TG_LensSetup getGoodsLens_5_lensSetup(VOId vo)throws  Exception;

    /**
     * 查询度数
     * @param vo
     * @return
     * @throws Exception
     */
    List<TG_Lens> getGoodsLens_5_lens(VOId vo)throws  Exception;

    List<TG_Lens> saveLens_6_allLens(TG_LensSetup lensSetup,TG_Goods goods, TG_Lens[] arrayGoodsLens)throws  Exception;

    /**
     * 删除所有包括库存，价格，度数配置，所有度数
     * @param vo
     * @throws Exception
     */
    void saveLens_6_delete(VOLens vo)throws  Exception;

    /**是加工中心并且有库存的站点添加默认库存*/
    void saveLens_6_kc(TG_Goods goods)throws  Exception;

    void saveLens_6_price(TG_Goods goods)throws  Exception;


}
