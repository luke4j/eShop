package com.luke.shop.eshop.goods.dao.impl;

import com.luke.shop.eshop.base.BaseDao;
import com.luke.shop.eshop.goods.dao.IGoodsTreeDao;
import com.luke.shop.eshop.goods.vo.VOGoodsAttrSetupEdit;
import com.luke.shop.eshop.goods.vo.VOGoodsNode;
import com.luke.shop.eshop.goods.vo.VOGoodsTreeEdit;
import com.luke.shop.model.TG_Goods;
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
    public List<VOGoodsNode> findNode_1(Long comId, VOIdEmpty vo) throws Exception {
        List<VOGoodsNode> listVoGoodsNodes = new ArrayList<>() ;

        if(vo.getId()!=null&&vo.getId().intValue()!=0){
            TG_GoodsTree treeNode = this.get(TG_GoodsTree.class, vo.getId()) ;
            if(treeNode!=null&&treeNode.getC_level().intValue()==4){
                List<TG_Goods> listGoods = this.find("From TG_Goods g where g.color.id=:id",vo) ;
                for(TG_Goods goods :listGoods){
                    VOGoodsNode node = new VOGoodsNode() ;
                    node.setText(goods.getName());
                    node.setFid(vo.getId());
                    node.setId(goods.getId());
                    node.setC_group("商品");
                    node.setC_level(5);
                    listVoGoodsNodes.add(node) ;
                }
                return listVoGoodsNodes ;
            }
        }
        return listVoGoodsNodes;
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
