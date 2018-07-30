package com.luke.shop.eshop.goods.service.impl;

import com.luke.shop.eshop.base.BaseService;
import com.luke.shop.eshop.goods.dao.IGoodsDao;
import com.luke.shop.eshop.goods.service.IGoodsService;
import com.luke.shop.eshop.goods.vo.VOGoods;
import com.luke.shop.model.*;
import com.luke.shop.tool.LK;
import com.luke.shop.tool.LKMap;
import com.luke.shop.tool.LoginTuken;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by luke on 2018/7/27.
 */
@Service
public class GoodsService extends BaseService implements IGoodsService {

    private static final Logger log = Logger.getLogger(GoodsService.class) ;

    @Resource
    IGoodsDao goodsDao ;

    @Override
    public TG_Goods addGoods_1(LoginTuken sessionTuken, VOGoods vo) throws Exception {

        TU_Com com = this.goodsDao.get(TU_Com.class,sessionTuken.getComId()) ;

        /**保存商品信息*/
        TG_Goods goods = new TG_Goods() ;
        BeanUtils.copyProperties(vo, goods);
        goods.setCom(com);
        goods.setPinYin(LK.NameToPingYinLong(goods.getName()));
        goods.setPy(LK.NameToPingYinShort(goods.getName()));

        TG_GoodsTree color = this.goodsDao.get(TG_GoodsTree.class,vo.getColorId()) ;
        TG_GoodsTree version = this.goodsDao.get(TG_GoodsTree.class,color.getFid()) ;
        TG_GoodsTree brand = this.goodsDao.get(TG_GoodsTree.class,version.getFid()) ;
        TG_GoodsTree kind = this.goodsDao.get(TG_GoodsTree.class,brand.getFid()) ;

        goods.setKind(kind);
        goods.setBrand(brand);
        goods.setVersion(version);
        goods.setColor(color);

        goods = this.goodsDao.save(goods);

                /**保存扩展属性*/
        TG_GoodsAttr attr = new TG_GoodsAttr() ;
        BeanUtils.copyProperties(vo,attr);
        attr.setGoods(goods);
        this.goodsDao.save(attr) ;

        //向下的逻辑需要分两种情况处理，度数商品，和非度数商品，

        /**添加非度数商品库存现库级别商品写入库存*/
        if(goods.getKcjb().intValue()==1&&"false".equals(kind.getA1())){//非度数商品，库存现货
            this.goodsDao.addGoods_1_notLens_kc(sessionTuken, goods) ;
        }else if(goods.getKcjb().intValue()>1&&"false".equals(kind.getA1())){//非度数商品，零订
            //不写库存
        }else if(goods.getKcjb().intValue()==1&&"true".equals(kind.getA1())){//度数商品，库存现货
            //不在此处处理
        }else if(goods.getKcjb().intValue()>1&&"true".equals(kind.getA1())){//度数商品，零订
            //不写库存
        }
        TSYS_SetupCom setup = this.goodsDao.getUnique("From TSYS_SetupCom sc where sc.name=:name and sc.com.id=:comId "
                ,new LKMap<String,Object>().putEx("name","添加商品时是否添加价格").putEx("comId",sessionTuken.getComId())) ;
        if(setup!=null&&"true".equals(setup.getVal())){
            TG_Price price = new TG_Price() ;
            BeanUtils.copyProperties(vo,price);
            price.setCom(com);
            price.setGoods(goods);
            this.goodsDao.save(price) ;
        }
        return goods ;
    }
}
