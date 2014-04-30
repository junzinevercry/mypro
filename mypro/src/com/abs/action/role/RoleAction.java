package com.abs.action.role;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abs.action.base.BaseAction;
import com.abs.dto.role.RoleDto;
import com.abs.entity.common.PageObject;
import com.abs.entity.role.Role;
import com.abs.service.role.RoleService;

@Controller
public class RoleAction extends BaseAction {
    @Autowired
    private RoleService roleService;

    /**
     * 跳转到角色列表.
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/role/gotoRoleList.action")
    public String gotoRoleList(HttpServletRequest request, HttpServletResponse response) {
        Role queryRole = new Role();
        queryRole.setName(request.getParameter("name"));
        PageObject<Role> pageObject = this.getPageObject(request);
        roleService.listRoleForPage(queryRole, pageObject);
        request.setAttribute("queryRole", queryRole);
        request.setAttribute("pageObject", pageObject);
        this.setCurrentMenuName(request);
        return "role/roleList";
    }

    /**
     * 分也查询角色（复选框）
     * 
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/role/gotoRoleListForCheckBox.action")
    public String gotoRoleListForCheckBox(HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {
        Role queryRole = new Role();
        queryRole.setName(request.getParameter("name"));
        PageObject<RoleDto> pageObject = this.getPageObject(request);
        request.setCharacterEncoding("GBK");
        String idJson = java.net.URLDecoder.decode(java.net.URLDecoder.decode(request.getParameter("idJson"), "UTF-8"),
                "UTF-8");
        roleService.listRoleForPageCheckBox(queryRole, pageObject, idJson);
        request.setAttribute("openerName", request.getParameter("openerName"));
        request.setAttribute("queryRole", queryRole);
        request.setAttribute("pageObject", pageObject);
        request.setAttribute("idJson", idJson);
        return "role/roleListForCheckBox";
    }

    /**
     * 跳转到创建角色.
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/role/gotoCreateRole.action")
    public String gotoCreateRole(HttpServletRequest request, HttpServletResponse response) {
        prepareForCreateOrUpdate(request, null);
        return "role/createRole";
    }

    /**
     * 跳转到修改角色.
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/role/gotoUpdateRole.action")
    public String gotoUpdateRole(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        Role role = roleService.findRoleById(id);
        request.setAttribute("role", role);
        prepareForCreateOrUpdate(request, role);
        return "role/updateRole";
    }

    /**
     * 执行创建角色.
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/role/doCreateRole.action")
    public void doCreateRole(HttpServletRequest request, HttpServletResponse response) {
        Role role = new Role();
        getRoleFromReq(request, role);
        String menuIds = request.getParameter("menuIds");
        String returnValue = roleService.createRole(role, menuIds);
        ajaxReturnJSON(request, response, returnValue);
    }

    /**
     * 执行修改角色.
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/role/doUpdateRole.action")
    public void doUpdateRole(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        Role role = roleService.findRoleById(id);
        getRoleFromReq(request, role);
        String menuIds = request.getParameter("menuIds");
        String returnValue = roleService.updateRole(role, menuIds);
        ajaxReturnJSON(request, response, returnValue);
    }

    /**
     * 执行删除角色.
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/role/doDeleteRole.action")
    public void doDeleteRole(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        request.setAttribute("currentPage", request.getParameter("currentPage"));
        Role role = new Role();
        role.setId(id);
        ajaxReturnJSON(request, response, roleService.deleteRole(role));
    }

    /**
     * 获得页面上的值.
     * 
     * @param request
     * @param menu
     */
    private void getRoleFromReq(HttpServletRequest request, Role role) {
        role.setName(request.getParameter("name"));
        role.setCode(request.getParameter("code"));
    }

    /**
     * 为创建和修改页面提供参数.
     * 
     * @param request
     */
    private void prepareForCreateOrUpdate(HttpServletRequest request, Role role) {
        request.setAttribute("menuJSON", roleService.getMenuJSON(role));
    }

}
