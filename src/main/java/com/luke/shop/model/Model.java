package com.luke.shop.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by luke on 2018/3/23.
 */
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public abstract class Model  implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id ;



    @Column(nullable = false,columnDefinition = "Boolean default false")
    private Boolean b_isDel = false;
    @Column(nullable = false)
    private Date b_wtime = new Date() ;
    @Column(nullable = false,columnDefinition = "bigint default -1")
    private Long b_comId = Static.LongDefNull ;

    public Long getB_comId() {
        return b_comId;
    }

    public void setB_comId(Long b_comId) {
        this.b_comId = b_comId;
    }

    public Boolean getB_isDel() {
        return b_isDel;
    }

    public void setB_isDel(Boolean b_isDel) {
        this.b_isDel = b_isDel;
    }

    public Date getB_wtime() {
        return b_wtime;
    }

    public void setB_wtime(Date b_wtime) {
        this.b_wtime = b_wtime;
    }

//    abstract public Long getId() ;
//    abstract public void setId(Long id) ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
