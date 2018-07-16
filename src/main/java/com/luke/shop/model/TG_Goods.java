package com.luke.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * Created by luke on 2018/5/10.
 * 商品信息表
 */
@Entity
@Table(
        indexes = {
                @Index(name = "idx_goods_kindId",columnList = "kindId"),
                @Index(name = "idx_goods_brandId",columnList = "brandId"),
                @Index(name = "idx_goods_versionId",columnList = "versionId"),
                @Index(name = "idx_goods_colorId",columnList = "colorId" ),
                @Index(name = "idx_goods_code",columnList = "c_code",unique = true),
                @Index(name = "idx_goods_kcjb", columnList = "kcjb"),
                @Index(name = "idx_goods_name", columnList = "name")
        }
)
public class TG_Goods extends Model {

//    @Id
//    @TableGenerator(name = "tg_goods_seq",       //sequence name
//            table = "seq_table",              //sequence table
//            initialValue=Static.initialValue,
//            pkColumnName = "seq_name",        //在表中对对应的sequence name 列
//            pkColumnValue = "tg_goods_seq",      //在表中对对应的sequence name 值
//            valueColumnName = "num",          //值
//            allocationSize = Static.allocationSize)
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tg_goods_seq")
//    private Long id ;

    /**商品品类*/
    private Long kindId ;
    /**商品品牌*/
    private Long brandId ;
    /**商品颜色*/
    private Long colorId ;

    /**型号*/
    private Long versionId ;
    /**商品编码*/
    @Column(length = 40,nullable = false)
    private String c_code ;
    @Column(length = 60,nullable = false)
    private String name ;
    @Column(length = 30,nullable = false)
    private String py ;
    @Column(length = 180,nullable = false)
    private String pinYin ;

    /**0 库存，1 零订 2车房*/
    private Integer kcjb = 0 ;

    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
    }

    public Integer getKcjb() {
        return kcjb;
    }

    public void setKcjb(Integer kcjb) {
        this.kcjb = kcjb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPy() {
        return py;
    }

    public void setPy(String py) {
        this.py = py;
    }

    public String getPinYin() {
        return pinYin;
    }

    public void setPinYin(String pinYin) {
        this.pinYin = pinYin;
    }
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getKindId() {
        return kindId;
    }

    public void setKindId(Long kindId) {
        this.kindId = kindId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getColorId() {
        return colorId;
    }

    public void setColorId(Long colorId) {
        this.colorId = colorId;
    }


    public String getC_code() {
        return c_code;
    }

    public void setC_code(String c_code) {
        this.c_code = c_code;
    }


}
