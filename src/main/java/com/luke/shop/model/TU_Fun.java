package com.luke.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by luke on 2018/4/10.
 * 系统功能资源 ，js路径记录
 */
@Entity
public class TU_Fun extends Model{
//    @Id
//    @TableGenerator(name = "tu_fun_seq",       //sequence name
//            initialValue=Static.initialValue,
//            table = "seq_table",              //sequence table
//            pkColumnName = "seq_name",        //在表中对对应的sequence name 列
//            pkColumnValue = "tu_fun_seq",      //在表中对对应的sequence name 值
//            valueColumnName = "num",          //值
//            allocationSize = Static.allocationSize)
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tu_fun_seq")
//    private Long id;
    /**功能js实现*/
    private String viewPath ;
    /**功能页面*/
    private String htmlPath ;
    /**功能图标*/
    private String iconPath ;
    /**功能组*/
    @Column(length = 18,nullable = false)
    private String c_group ;

    @Column(length = 30,nullable = false)
    private String name ;
    @Column(nullable = false)
    private Long fid = -1l;

    @Column(length = 200)
    private String studyPath ;

    public String getStudyPath() {
        return studyPath;
    }

    public void setStudyPath(String studyPath) {
        this.studyPath = studyPath;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getViewPath() {
        return viewPath;
    }

    public void setViewPath(String viewPath) {
        this.viewPath = viewPath;
    }

    public String getHtmlPath() {
        return htmlPath;
    }

    public void setHtmlPath(String htmlPath) {
        this.htmlPath = htmlPath;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getC_group() {
        return c_group;
    }

    public void setC_group(String c_group) {
        this.c_group = c_group;
    }
}
