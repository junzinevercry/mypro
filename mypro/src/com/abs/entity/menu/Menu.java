package com.abs.entity.menu;

import com.abs.entity.base.GenericEntity;
import com.abs.util.annotation.Columns;
import com.abs.util.annotation.Table;

/**
 * <P><B>说明：</B>描述这个类的作用</P>
 * <P><B>日期：</B>2013-6-4 下午3:45:39</P>
 * @author zhangjun
 * @version 1.0
 */
@Table(value = "MENU")
public class Menu extends GenericEntity {
    /**
     * 
     */
    private static final long serialVersionUID = 1922238010685205585L;

    /** @Fields pId : 父节点ID*/
    private String pId;

    /** @Fields name : 菜单名称*/
    private String name;

    /** @Fields url : 菜单链接*/
    private String url;

    /** @Fields nLevel : 级别*/
    private Integer nLevel;

    /**
     * 窗口名称.
     */
    private String winName;

    @Columns(value = "WIN_NAME")
    public String getWinName() {
        return winName;
    }

    public void setWinName(String winName) {
        this.winName = winName;
    }

    /**
     * @return the pId
     */
    @Columns(value = "PID")
    public String getpId() {
        return pId;
    }

    /**
     * @param pId the pId to set
     */
    public void setpId(String pId) {
        this.pId = pId;
    }

    /**
     * @return the name
     */
    @Columns(value = "NAME")
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the url
     */
    @Columns(value = "URL")
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the nLevel
     */

    @Columns(value = "NLEVEL")
    public Integer getnLevel() {
        return nLevel;
    }

    /**
     * @param nLevel the nLevel to set
     */
    public void setnLevel(Integer nLevel) {
        this.nLevel = nLevel;
    }

}
