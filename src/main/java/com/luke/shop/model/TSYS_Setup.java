package com.luke.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by luke on 2018/7/27.
 */
@Entity
public class TSYS_Setup extends Model {

    @Column(length = 30,nullable = false)
    private String name ;

    @Column(length = 100,nullable = false)
    private String val ;

    @Column(length = 200,nullable = false)
    private String  note  ;





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
