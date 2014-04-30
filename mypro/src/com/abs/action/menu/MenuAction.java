package com.abs.action.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abs.action.base.BaseAction;
import com.abs.entity.common.PageObject;
import com.abs.entity.menu.Menu;
import com.abs.service.menu.MenuService;

/**
 * <P><B>说明：</B>描述这个类的作用</P>
 * <P><B>日期：</B>2013-6-6 上午8:57:33</P>
 * @author zhangjun
 * @version 1.0
 */
@Controller
public class MenuAction extends BaseAction {
    @Autowired
    private MenuService menuService;

    /**
     * 跳转到菜单列表.
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/menu/gotoMenuList.action")
    public String gotoMenuList(HttpServletRequest request, HttpServletResponse response) {
        Menu queryMenu = new Menu();
        queryMenu.setName(request.getParameter("name"));
        PageObject<Menu> pageObject = this.getPageObject(request);
        menuService.listMenuForPage(queryMenu, pageObject, this.getUserId());
        request.setAttribute("queryMenu", queryMenu);
        request.setAttribute("pageObject", pageObject);
        prepareForCreateOrUpdate(request);
        this.setCurrentMenuName(request);
        return "menu/menuList";
    }

    /**
     * 跳转到创建菜单.
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/menu/gotoCreateMenu.action")
    public String gotoCreateMenu(HttpServletRequest request, HttpServletResponse response) {
        prepareForCreateOrUpdate(request);
        return "menu/createMenu";
    }

    /**
     * 跳转到修改菜单.
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/menu/gotoUpdateMenu.action")
    public String gotoUpdateMenu(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        Menu menu = menuService.findMenuById(id);
        request.setAttribute("menu", menu);
        prepareForCreateOrUpdate(request);
        return "menu/updateMenu";
    }

    /**
     * 执行创建菜单.
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/menu/doCreateMenu.action")
    public void doCreateMenu(HttpServletRequest request, HttpServletResponse response) {
        Menu menu = new Menu();
        super.fillGenericProperty(menu, true);
        getMenuFromReq(request, menu);
        String returnValue = menuService.createMenu(menu);
        ajaxReturnJSON(request, response, returnValue);
    }

    /**
     * 执行修改菜单.
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/menu/doUpdateMenu.action")
    public void doUpdateMenu(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        Menu menu = menuService.findMenuById(id);
        super.fillGenericProperty(menu, false);
        getMenuFromReq(request, menu);
        String returnValue = menuService.updateMenu(menu);
        ajaxReturnJSON(request, response, returnValue);
    }

    /**
     * 执行删除菜单.
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/menu/doDeleteMenu.action")
    public void doDeleteMenu(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        request.setAttribute("currentPage", request.getParameter("currentPage"));
        Menu menu = new Menu();
        menu.setId(id);
        menuService.deleteMenu(menu);
        ajaxReturnJSON(request, response, "true");
    }

    /**
     * 获得页面上的值.
     * 
     * @param request
     * @param menu
     */
    private void getMenuFromReq(HttpServletRequest request, Menu menu) {
        menu.setName(request.getParameter("name"));
        menu.setUrl(request.getParameter("url"));
        menu.setWinName(request.getParameter("winName"));
        menu.setpId(request.getParameter("pId"));
        if ("0".equals(menu.getpId())) {
            menu.setnLevel(0);
        } else {
            menu.setnLevel(0);
        }
    }

    /**
     * 为创建和修改页面提供参数.
     * 
     * @param request
     */
    private void prepareForCreateOrUpdate(HttpServletRequest request) {
        request.setAttribute("menuList", menuService.listParentMenu(this.getUserId()));
    }

}
