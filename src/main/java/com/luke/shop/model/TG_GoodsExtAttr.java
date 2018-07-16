package com.luke.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by luke on 2018/5/11.
 * 商品扩展信息字段意义
 */
@Entity
public class TG_GoodsExtAttr extends Model{
//    @Id
//    @TableGenerator(name = "tg_goodsExtAttr_seq",       //sequence name
//            table = "seq_table",              //sequence table
//            initialValue=Static.initialValue,
//            pkColumnName = "seq_name",        //在表中对对应的sequence name 列
//            pkColumnValue = "tg_goodsExtAttr_seq",      //在表中对对应的sequence name 值
//            valueColumnName = "num",          //值
//            allocationSize = Static.allocationSize)
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tg_goodsExtAttr_seq")
//    private Long id ;
    @Column(nullable = false)
    private Long kindId ;
    @Column(length = 40,nullable = true)
    private String columnName ;
    @Column(length = 40,nullable = true)
    private String columnValue ;
    @Column(length = 40,nullable = true)
    private String c_type = "text";
    @Column(length = 200,nullable = true)
    private String defaults = "";

    public String getC_type() {
        return c_type;
    }

    public void setC_type(String c_type) {
        this.c_type = c_type;
    }

    public String getDefaults() {
        return defaults;
    }

    public void setDefaults(String defaults) {
        this.defaults = defaults;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKindId() {
        return kindId;
    }

    public void setKindId(Long kindId) {
        this.kindId = kindId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnValue() {
        return columnValue;
    }

    public void setColumnValue(String columnValue) {
        this.columnValue = columnValue;
    }
}
