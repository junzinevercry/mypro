package com.abs.service.menu;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abs.dao.menu.MenuDao;
import com.abs.dao.menu.RoleMenuDao;
import com.abs.dto.menu.MenuDto;
import com.abs.entity.common.PageObject;
import com.abs.entity.menu.Menu;
import com.abs.service.base.BaseService;

/**
 * <P><B>说明：</B>描述这个类的作用</P>
 * <P><B>日期：</B>2013-6-5 下午3:34:55</P>
 * @author zhangjun
 * @version 1.0
 */
@Service
public class MenuService extends BaseService{
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private RoleMenuDao roleMenuDao;

    /**
     * <P><B>说明：</B>获得menu列表</P>
     * <P><B>日期：</B>2013-6-6 上午9:05:48</P>
     * <P><B>作者：</B>zhangjun</P>
     * @param queryMenu
     * @param pageObject
     * @param userId
     */
    public void listMenuForPage(Menu queryMenu, PageObject<Menu> pageObject, String userId) {
        menuDao.listMenuForPage(queryMenu, pageObject, userId);
    }

    /**
     * <P><B>说明：</B>描述这个方法的作用</P>
     * <P><B>日期：</B>2013-6-5 下午3:36:03</P>
     * <P><B>作者：</B>zhangjun</P>
     * @param userId
     * @return
     */
    public List<MenuDto> listMenuForHomePage(String userId) {
        List<MenuDto> result = new ArrayList<MenuDto>();
        List<Menu> menuList = menuDao.listMenuForHomePage(userId);
        for (Menu menu : menuList) {
            MenuDto menuDto = new MenuDto();
            menuDto.setId(menu.getId());
            menuDto.setName(menu.getName());
            menuDto.setWinName(menu.getWinName());
            menuDto.setUrl(menu.getUrl());
            List<Menu> subMenu = menuDao.listSubMenuForHomePage(userId, menu.getId());
            menuDto.setSubMenu(subMenu);
            result.add(menuDto);
        }
        return result;
    }

    /**
     * <P><B>说明：</B>获得子菜单</P>
     * <P><B>日期：</B>2013-6-5 下午4:59:20</P>
     * <P><B>作者：</B>zhangjun</P>
     * @param parentWinName
     * @param userId
     * @return
     */
    public List<Menu> listSubMenu(String parentWinName, String userId) {
        Menu menu = menuDao.findMenuByWinNameAndUserId(parentWinName, userId);
        if (menu == null) {
            return null;
        }
        if ("0".equals(menu.getpId())) {
            return menuDao.listSubMenuForHomePage(userId, menu.getId());
        }
        return menuDao.listSubMenuForHomePage(userId, menu.getpId());
    }

    /**
     * <P><B>说明：</B>获得父菜单</P>
     * <P><B>日期：</B>2013-6-5 下午4:59:36</P>
     * <P><B>作者：</B>zhangjun</P>
     * @param currentMenuName
     * @param userId
     * @return
     */
    public Menu findParentMenu(String currentMenuName, String userId) {
        Menu menu = menuDao.findMenuByWinNameAndUserId(currentMenuName, userId);
        if (menu == null) {
            return menuDao.findMenuByWinNameAndUserId(currentMenuName, null);
        }
        if ("0".equals(menu.getpId())) {
            return menu;
        }
        return menuDao.findObjectById(Menu.class, menu.getpId());
    }

    /**
     * <P><B>说明：</B>获得当前菜单</P>
     * <P><B>日期：</B>2013-6-5 下午4:59:47</P>
     * <P><B>作者：</B>zhangjun</P>
     * @param currentMenuName
     * @param userId
     * @return
     */
    public Menu findCurrentMenu(String currentMenuName, String userId) {
        Menu menu = menuDao.findMenuByWinNameAndUserId(currentMenuName, userId);
        if (menu == null) {
            menu = menuDao.findMenuByWinNameAndUserId(currentMenuName, null);
        }
        return menu;
    }

    /**
     * <P><B>说明：</B>根据ID查询菜单</P>
     * <P><B>日期：</B>2013-6-6 上午9:15:17</P>
     * <P><B>作者：</B>zhangjun</P>
     * @param id
     * @return
     */
    public Menu findMenuById(String id) {
        return menuDao.findObjectById(Menu.class, id);
    }

    /**
     * <P><B>说明：</B>创建菜单</P>
     * <P><B>日期：</B>2013-6-6 上午9:15:36</P>
     * <P><B>作者：</B>zhangjun</P>
     * @param menu
     * @return
     */
    @Transactional
    public String doCreateMenu(Menu menu) {
        String returnValue = checkBefore(menu);
        if ("success".equals(returnValue)) {
            menuDao.createObject(menu);
        }
        return this.getJSONResult("info", "success");
    }

    /**
     * <P><B>说明：</B>删除菜单</P>
     * <P><B>日期：</B>2013-6-6 上午9:14:11</P>
     * <P><B>作者：</B>zhangjun</P>
     * @param menu
     */
    @Transactional
    public String doDeleteMenu(Menu menu) {
        menuDao.deleteMenu(menu);
        roleMenuDao.deleteRoleMenuByMenuId(menu.getId());
        return this.getJSONResult("info", "success");
    }

    /**
     * <P><B>说明：</B>修改菜单</P>
     * <P><B>日期：</B>2013-6-6 上午9:15:46</P>
     * <P><B>作者：</B>zhangjun</P>
     * @param menu
     * @return
     */
    @Transactional
    public String doUpdateMenu(Menu menu) {
        String returnValue = checkBefore(menu);
        if ("success".equals(returnValue)) {
            menuDao.updateObject(menu);
        }
        return this.getJSONResult("info", "success");
    }

    private String checkBefore(Menu menu) {
        String name = menu.getName().trim().replace(" ", "");
        String url = menu.getUrl().trim().replace(" ", "");
        String winName = menu.getWinName().trim().replace(" ", "");
        if (StringUtils.isEmpty(name)) {
            return "nameEmpty";
        }
        if (StringUtils.isEmpty(url)) {
            return "urlEmpty";
        }
        if (StringUtils.isEmpty(winName)) {
            return "winNameEmpty";
        }
        Menu testNameMenu = menuDao.findMenuByName(name, menu.getId());
        if (testNameMenu != null) {
            return "hasName";
        }
        Menu testCodeMenu = menuDao.findMenuByUrl(url, menu.getId());
        if (testCodeMenu != null) {
            return "hasUrl";
        }
        Menu testWinNameMenu = menuDao.findMenuByWinName(winName, menu.getId());
        if (testWinNameMenu != null) {
            return "hasWinName";
        }
        return "success";
    }

    /**
     * <P><B>说明：</B>获得menu列表</P>
     * <P><B>日期：</B>2013-6-6 上午9:15:56</P>
     * <P><B>作者：</B>zhangjun</P>
     * @param userId
     * @return
     */
    public List<Menu> listParentMenu(String userId) {
        return menuDao.listParentMenu(userId);
    }

}
