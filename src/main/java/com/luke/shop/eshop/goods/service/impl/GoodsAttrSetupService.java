package com.luke.shop.eshop.goods.service.impl;

import com.luke.shop.eshop.goods.dao.IGoodsAttrSetupDao;
import com.luke.shop.eshop.goods.service.IGoodsAttrSetupService;
import com.luke.shop.model.TG_GoodsAttrSetup;
import com.luke.shop.tool.vo.VOId;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by luke on 2018/10/25.
 */
@Service
public class GoodsAttrSetupService implements IGoodsAttrSetupService {

    @Resource
    IGoodsAttrSetupDao goodsAttrSetupDao ;

    @Override
    public List<TG_GoodsAttrSetup> findGoodsAttrSetupByKindId(VOId vo) throws Exception {
        return goodsAttrSetupDao.findGoodsAttrSetupByKindId(vo) ;
    }
}
