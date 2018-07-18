package com.luke.shop.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by luke on 2018/4/10.
 * 系统功能资源 ，js路径记录
 */
@Entity
public class TU_Fun extends Model{

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

    @ManyToMany(mappedBy = "listFun")
    List<TU_Role> listRole ;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public String getStudyPath() {
        return studyPath;
    }

    public void setStudyPath(String studyPath) {
        this.studyPath = studyPath;
    }

    public List<TU_Role> getListRole() {
        return listRole;
    }

    public void setListRole(List<TU_Role> listRole) {
        this.listRole = listRole;
    }
}
