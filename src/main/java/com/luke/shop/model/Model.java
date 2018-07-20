package com.luke.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by luke on 2018/3/23.
 */
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class Model  implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id ;



    @Column(nullable = false,columnDefinition = "Boolean default false")
    @JsonIgnore
    private Boolean b_isDel = false;

    @Column(nullable = false)
    @JsonIgnore
    private Date b_wtime = new Date() ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
