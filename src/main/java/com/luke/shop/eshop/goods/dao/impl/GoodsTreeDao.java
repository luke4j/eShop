package com.luke.shop.eshop.goods.dao.impl;

import com.luke.shop.eshop.base.BaseDao;
import com.luke.shop.eshop.goods.dao.IGoodsTreeDao;
import com.luke.shop.eshop.goods.vo.VOGoodsAttrSetupEdit;
import com.luke.shop.eshop.goods.vo.VOGoodsTreeEdit;
import com.luke.shop.model.TG_GoodsAttrSetup;
import com.luke.shop.model.TG_GoodsTree;
import com.luke.shop.tool.LKMap;
import com.luke.shop.tool.LoginTuken;
import com.luke.shop.tool.vo.VOId;
import com.luke.shop.tool.vo.VOIdEmpty;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luke on 2018/7/20.
 */
@Component
public class GoodsTreeDao extends BaseDao implements IGoodsTreeDao {
    @Override
    public List<TG_GoodsTree> findNode_1(Long comId, VOIdEmpty vo) throws Exception {
        LKMap<String,Object> param = new LKMap<String,Object>().putEx("comId",comId).putEx("fid",vo.getId()) ;
        List<TG_GoodsTree> listGoodsTree = this.find("From TG_GoodsTree r where r.b_isDel = false and  r.com.id=:comId and fid=:fid order by c_level ,id",param) ;
//        List<TG_GoodsTree> listGoodsTree = this.find("From TG_GoodsTree r where r.com.id=:comId  order by c_level ,id",param) ;
        return listGoodsTree;
    }

    @Override
    public TG_GoodsTree addNode_2(TG_GoodsTree node) throws Exception {
        return this.save(node);
    }

    @Override
    public void addNode_2_initGoodsAttrSetup(TG_GoodsTree node) throws Exception {
        TG_GoodsAttrSetup setup = null ;
        List<TG_GoodsAttrSetup> listSetup = new ArrayList<TG_GoodsAttrSetup>(15) ;
        for(int i = 1 ;i <= 15 ;i++ ){
            setup = new TG_GoodsAttrSetup() ;
            setup.setColumnName("a"+i);
            setup.setKind(node);
            listSetup.add(setup);
        }
        this.saveAll(listSetup) ;

    }



    @Override
    public TG_GoodsTree editNode_3(LoginTuken sessionTuken, VOGoodsTreeEdit vo) throws Exception {
        return this.updateObj(TG_GoodsTree.class, vo.getId(), vo) ;
    }

    @Override
    public TG_GoodsTree delNode_4(LoginTuken sessionTuken, VOId vo) throws Exception {
        TG_GoodsTree goodsTree = this.get(TG_GoodsTree.class, vo.getId());
        return  this.delObj(goodsTree);
    }

    @Override
    public TG_GoodsAttrSetup edit_goods_attr_setup_5(LoginTuken sessionTuken, VOGoodsAttrSetupEdit vo) throws Exception {
        return this.updateObj(TG_GoodsAttrSetup.class,vo.getId(),vo) ;
    }

    @Override
    public List<TG_GoodsAttrSetup> find_goods_attr_setup_6(VOId vo) throws Exception {
        return this.find("From TG_GoodsAttrSetup t where t.kind.id=:id",vo) ;
    }
}
