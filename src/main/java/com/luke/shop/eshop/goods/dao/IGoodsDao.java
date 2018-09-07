package com.luke.shop.eshop.goods.dao;

import com.luke.shop.eshop.base.IBaseDao;
import com.luke.shop.eshop.goods.vo.VOGoods;
import com.luke.shop.eshop.goods.vo.VOLens;
import com.luke.shop.model.*;
import com.luke.shop.tool.vo.VOId;

import java.util.List;

/**
 * Created by luke on 2018/7/27.
 */
public interface IGoodsDao extends IBaseDao {






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










    List<TK_InitBillMX> saveLensDefVal_7_dbCopy_dj(Long goodsId, Long num, Long djId)throws  Exception;


}
