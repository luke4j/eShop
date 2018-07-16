package com.luke.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * Created by luke on 2018/5/10.
 * 度数商品信息扩展表
 */
@Entity
@Table(indexes = {
        @Index(name = "TG_GoodsLens_goodsId_sph_cyl" ,columnList = "goodsId,sph,cyl",unique = true)
})
public class TG_GoodsLens extends Model{
//    @Id
//    @TableGenerator(name = "tg_goodsLens_seq",       //sequence name
//            table = "seq_table",              //sequence table
//            initialValue=Static.initialValue,
//            pkColumnName = "seq_name",        //在表中对对应的sequence name 列
//            pkColumnValue = "tg_goodsLens_seq",      //在表中对对应的sequence name 值
//            valueColumnName = "num",          //值
//            allocationSize = Static.allocationSize)
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tg_goodsLens_seq")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id ;
    @Column(nullable = true)
    private Long goodsId ;
    @Column(nullable = true)
    private Float sph ;
    @Column(nullable = true)
    private Float cyl ;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Float getSph() {
        return sph;
    }

    public void setSph(Float sph) {
        this.sph = sph;
    }

    public Float getCyl() {
        return cyl;
    }

    public void setCyl(Float cyl) {
        this.cyl = cyl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
