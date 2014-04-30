package com.abs.dto.menu;

import java.util.ArrayList;
import java.util.List;

import com.abs.entity.menu.Menu;

/**
 * <P><B>说明：</B>描述这个类的作用</P>
 * <P><B>日期：</B>2013-6-5 下午4:42:29</P>
 * @author zhangjun
 * @version 1.0
 */
public class MenuDto {
    private String id;
    private String name;
    private String url;
    private String winName;
    private List<Menu> subMenu = new ArrayList<Menu>();

    /**
     * @return the winName
     */
    public String getWinName() {
        return winName;
    }

    /**
     * @param winName the winName to set
     */
    public void setWinName(String winName) {
        this.winName = winName;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
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
     * @return the subMenu
     */
    public List<Menu> getSubMenu() {
        return subMenu;
    }

    /**
     * @param subMenu the subMenu to set
     */
    public void setSubMenu(List<Menu> subMenu) {
        this.subMenu = subMenu;
    }

}
