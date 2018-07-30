package com.luke.shop.model;

import javax.persistence.*;

/**
 * Created by luke on 2018/7/20.
 * 商品信息扩展信息配置
 */
@Entity
public class TG_GoodsAttrSetup extends Model {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kindId",foreignKey = @ForeignKey(name = "fk_goodsAttrSetup_goodsTree"))
    private TG_GoodsTree kind ;
    @Column(length = 40,nullable = true)
    private String columnName ;
    @Column(length = 40,nullable = true)
    private String columnValue ;
    @Column(length = 40,nullable = true)
    private String c_type = "text";
    @Column(length = 200,nullable = true)
    private String defaults = "";

    public TG_GoodsTree getKind() {
        return kind;
    }

    public void setKind(TG_GoodsTree kind) {
        this.kind = kind;
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
}
