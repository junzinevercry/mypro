package com.abs.service.role;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abs.dao.menu.MenuDao;
import com.abs.dao.menu.RoleMenuDao;
import com.abs.dao.role.RoleDao;
import com.abs.dto.role.RoleDto;
import com.abs.entity.common.PageObject;
import com.abs.entity.menu.Menu;
import com.abs.entity.menu.RoleMenu;
import com.abs.entity.role.Role;
import com.abs.service.base.BaseService;

@Service
public class RoleService extends BaseService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private RoleMenuDao roleMenuDao;

    /**
     * 获得所有的角色(翻页).
     * 
     * @param queryRole
     * @param pageObject
     */
    public void listRoleForPage(Role queryRole, PageObject<Role> pageObject) {
        roleDao.listRoleForPage(queryRole, pageObject);
    }

    /**
     * 根据主键查询对象.
     * 
     * @param id
     * @return
     */
    public Role findRoleById(String id) {
        return roleDao.findObjectById(Role.class, id);
    }

    /**
     * 创建角色.
     * 
     * @param role
     * @param menuIds
     */
    @Transactional
    public String createRole(Role role, String menuIds) {
        String returnValue = findRoleHasOrNot(role, menuIds);
        if ("success".equals(returnValue)) {
            roleDao.createObject(role);
            List<RoleMenu> roleMenuList = getRoleMenuList(role, menuIds);
            roleMenuDao.createRoleMenuList(roleMenuList);
        }
        return super.getJSONResult("info", returnValue);
    }

    /**
     * 分也查询复选框
     * 
     * @param queryRole
     * @param pageObject
     * @param idJson
     */
    public void listRoleForPageCheckBox(Role queryRole, PageObject<RoleDto> pageObject, String idJson) {
        roleDao.listRoleForPageCheckBox(queryRole, pageObject);
        if (idJson != null && !"".equals(idJson.trim())) {
            JSONArray jsonArray = JSONArray.fromObject(idJson);
            List<String> isArray = new ArrayList<String>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                isArray.add(jsonObj.getString("id"));
            }
            for (RoleDto rd : pageObject.getPageList()) {
                if (isArray.contains(rd.getId())) {
                    rd.setSelectType("1");
                }
            }
        }
    }

    /**
     * 修改角色.
     * 
     * @param role
     * @param menuIds
     */
    @Transactional
    public String updateRole(Role role, String menuIds) {
        String returnValue = findRoleHasOrNot(role, menuIds);
        if ("success".equals(returnValue)) {
            List<RoleMenu> roleMenuList = getRoleMenuList(role, menuIds);
            roleDao.updateObject(role);
            roleMenuDao.deleteRoleMenuByRoleId(role.getId());
            roleMenuDao.createRoleMenuList(roleMenuList);
        }
        return super.getJSONResult("info", returnValue);
    }

    /**
     * 删除角色.
     * 
     * @param role
     */
    @Transactional
    public String deleteRole(Role role) {
        roleDao.deleteObject(role);
        roleMenuDao.deleteRoleMenuByRoleId(role.getId());
        return this.getJSONResult("info", "success");
    }

    /**
     * 校验方法.
     * 
     * @param role
     * @param menuIds
     * @return
     */
    private String findRoleHasOrNot(Role role, String menuIds) {
        String name = role.getName().trim().replace(" ", "");
        String code = role.getCode().trim().replace(" ", "");
        if (StringUtils.isEmpty(name)) {
            return "nameEmpty";
        }
        if (StringUtils.isEmpty(code)) {
            return "codeEmpty";
        }
        Role testNameRole = roleDao.findRoleByName(name, role.getId());
        if (testNameRole != null) {
            return "hasName";
        }
        Role testCodeRole = roleDao.findRoleByCode(code, role.getId());
        if (testCodeRole != null) {
            return "hasCode";
        }
        if (StringUtils.isEmpty(menuIds)) {
            return "menuEmpty";
        }
        return "success";
    }

    /**
     * 获得菜单树的json.
     * 
     * @param role
     * @return
     */
    public String getMenuJSON(Role role) {
        List<Menu> menuList = menuDao.listAllMenu();
        List<String> selectedMenuIdList = getSelectedMenuIdList(role);
        JSONArray jsonArray = new JSONArray();
        for (Menu menu : menuList) {
            JSONObject obj = new JSONObject();
            obj.put("id", menu.getId());
            obj.put("pId", menu.getpId());
            obj.put("name", menu.getName());
            obj.put("icon", "2.png");
            if (checked(role, menu, selectedMenuIdList)) {
                obj.put("checked", true);
            }
            if ("0".compareTo(menu.getpId()) == 0) {
                obj.put("open", true);
            }
            jsonArray.add(obj);
        }
        return jsonArray.toString();
    }

    private boolean checked(Role role, Menu menu, List<String> selectedMenuIdList) {
        if (role != null && selectedMenuIdList.contains(menu.getId())) {
            return true;
        }
        return false;
    }

    private List<String> getSelectedMenuIdList(Role role) {
        List<String> idList = new ArrayList<String>();
        if (role != null) {
            List<RoleMenu> roleMenuList = roleMenuDao.listRoleMenuByRoleId(role.getId());
            for (RoleMenu roleMenu : roleMenuList) {
                idList.add(roleMenu.getMenuId());
            }
        }
        return idList;
    }

    private List<RoleMenu> getRoleMenuList(Role role, String menuIds) {
        List<RoleMenu> roleMenuList = new ArrayList<RoleMenu>();
        String[] parts = menuIds.split(",");
        for (String part : parts) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(role.getId());
            roleMenu.setMenuId(part);
            roleMenuList.add(roleMenu);
        }
        return roleMenuList;
    }

}
