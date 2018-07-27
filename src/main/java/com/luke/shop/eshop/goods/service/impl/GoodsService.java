package com.luke.shop.eshop.goods.service.impl;

import com.luke.shop.eshop.base.BaseService;
import com.luke.shop.eshop.goods.dao.IGoodsDao;
import com.luke.shop.eshop.goods.service.IGoodsService;
import com.luke.shop.eshop.goods.vo.VOGoods;
import com.luke.shop.model.TG_Goods;
import com.luke.shop.model.TG_GoodsAttr;
import com.luke.shop.model.TG_GoodsTree;
import com.luke.shop.model.TSYS_SetupCom;
import com.luke.shop.tool.LK;
import com.luke.shop.tool.LKMap;
import com.luke.shop.tool.LoginTuken;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.*;

/**
 * Created by luke on 2018/7/27.
 */
@Service
public class GoodsService extends BaseService implements IGoodsService {

    @Resource
    IGoodsDao goodsDao ;

    @Override
    public TG_Goods addGoods_1(LoginTuken sessionTuken, VOGoods vo) throws Exception {
        /**保存扩展属性*/
        TG_GoodsAttr attr = new TG_GoodsAttr() ;
        BeanUtils.copyProperties(vo,attr);
        this.goodsDao.save(attr) ;

        /**保存商品信息*/
        TG_Goods goods = new TG_Goods() ;
        BeanUtils.copyProperties(vo, goods);
        goods.setPinYin(LK.NameToPingYinLong(goods.getName()));
        goods.setPy(LK.NameToPingYinShort(goods.getName()));
        goods.setAttr(attr);

        TG_GoodsTree color = this.goodsDao.get(TG_GoodsTree.class,vo.getColorId()) ;
        TG_GoodsTree version = this.goodsDao.get(TG_GoodsTree.class,color.getFid()) ;
        TG_GoodsTree brand = this.goodsDao.get(TG_GoodsTree.class,version.getFid()) ;
        TG_GoodsTree kind = this.goodsDao.get(TG_GoodsTree.class,brand.getFid()) ;

        goods.setKind(kind);
        goods.setBrand(brand);
        goods.setVersion(version);
        goods.setColor(color);

        goods = this.goodsDao.save(goods);

        /**添加非度数商品库存现库级别商品写入库存0*/
        if(goods.getKcjb().intValue()==0&&"false".equals(kind.getA1())){
            this.goodsDao.addGoods_1_add_kc( sessionTuken, goods) ;
        }
        TSYS_SetupCom setup = this.goodsDao.getUnique("From TSYS_SetupCom sc where sc.name=:name and sc.com.id=:comId "
                ,new LKMap<String,Object>().putEx("name","添加商品时是否添加价格").putEx("comId",sessionTuken.getComId())) ;
        if(setup!=null&&"true".equals(setup.getVal())){
            //TODO 写非度数商品价格，度数商品价格在写度数时添加
        }
        return goods ;
    }
}
