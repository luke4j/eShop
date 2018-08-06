package com.luke.shop.eshop.goods.dao.impl;

import com.luke.shop.eshop.base.BaseDao;
import com.luke.shop.eshop.goods.dao.IGoodsTreeDao;
import com.luke.shop.eshop.goods.vo.VOGoodsAttrSetupEdit;
import com.luke.shop.eshop.goods.vo.VOGoodsNode;
import com.luke.shop.eshop.goods.vo.VOGoodsTreeEdit;
import com.luke.shop.model.TG_Goods;
import com.luke.shop.model.TG_GoodsAttr;
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
        VOGoodsNode node = null ;
        if(vo.getId().intValue()!=0){
            TG_GoodsTree treeNode = this.get(TG_GoodsTree.class, vo.getId()) ;
            /**树展开颜色*/
            if("颜色".equals(treeNode.getC_group())){
                List<TG_Goods> listGoods = this.find("From TG_Goods g where g.color.id=:id",vo) ;
                for(TG_Goods goods :listGoods){
                    node = new VOGoodsNode() ;
                    node.setText(goods.getName());
                    node.setFid(vo.getId());
                    node.setId(goods.getId());
                    node.setC_group("商品");
                    node.setC_level(5);
                    node.setIsParent(false);
                    node.setKcjb(goods.getKcjb());
                    node.setC_code(goods.getC_code());
                    listVoGoodsNodes.add(node) ;
                }
            }else{
                /**树展开品类，品牌，型号*/
                List<TG_GoodsTree> listGoodsNode = this.find("From TG_GoodsTree t where t.fid=:id",vo) ;
                for(TG_GoodsTree tmp :listGoodsNode){
                    node = new VOGoodsNode();
                    BeanUtils.copyProperties(tmp, node);
                    if("颜色".equals(tmp.getC_group())){
                        Long count = this.getUnique("select count(g.id) From TG_Goods g where g.color.id=:id", tmp) ;
                        node.setCount(count);
                    }else{
                        Long count = this.getUnique("select count(t.id) From TG_GoodsTree t where t.fid=:id", tmp) ;
                        node.setCount(count);
                    }

                    listVoGoodsNodes.add(node) ;
                }
            }
        }else{
            /**初始化树的时候没有参数，默认为0，查询所有品类*/
            List<TG_GoodsTree> listGoodsNode = this.find("From TG_GoodsTree t where t.c_group='品类'") ;
            for(TG_GoodsTree tmp :listGoodsNode){
                node = new VOGoodsNode() ;
                BeanUtils.copyProperties(tmp, node);
                Long count = this.getUnique("select count(t.id) From TG_GoodsTree t where t.fid=:id", tmp) ;
                node.setCount(count);
                listVoGoodsNodes.add(node) ;
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

    @Override
    public TG_GoodsAttr find_goods_attrsByGoodsId_8_attrs(TG_Goods goods) throws Exception {
        return this.getUnique("From TG_GoodsAttr ga where ga.goods.id=:id",goods) ;
    }
}
