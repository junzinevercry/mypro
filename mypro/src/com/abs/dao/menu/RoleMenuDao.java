package com.abs.dao.menu;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.abs.dao.base.BaseDao;
import com.abs.entity.menu.RoleMenu;

/**
 * <P><B>说明：</B>描述这个类的作用</P>
 * <P><B>日期：</B>2013-6-6 上午9:08:52</P>
 * @author zhangjun
 * @version 1.0
 */
@Repository
public class RoleMenuDao extends BaseDao {
    /**
     * 根据roleId查询RoleMenu.
     * 
     * @param roleId
     * @return
     */
    public List<RoleMenu> listRoleMenuByRoleId(String roleId) {
        StringBuilder sql = new StringBuilder("select * from ROLE_MENU where 1=1 ");
        sql.append(" and ROLE_ID = '");
        sql.append(roleId);
        sql.append("'");
        return this.listObject(RoleMenu.class, sql.toString());
    }

    /**
     * 删除角色与菜单对应关系.
     * 
     * @param roleId
     */
    public void deleteRoleMenuByRoleId(String roleId) {
        StringBuilder sql = new StringBuilder("delete from ROLE_MENU where ROLE_ID = '");
        sql.append(roleId);
        sql.append("'");
        this.getJdbcTemplate().execute(sql.toString());
    }

    /**
     * 批量创建.
     * 
     * @param roleMenuList
     */
    public void createRoleMenuList(List<RoleMenu> roleMenuList) {
        for (int i = 0; i < roleMenuList.size(); i++) {
            RoleMenu roleMenu = roleMenuList.get(i);
            this.createObject(roleMenu);
        }
    }

    /**
     * 根据菜单ID删除rolemenu.
     * 
     * @param menuId
     */
    public void deleteRoleMenuByMenuId(String menuId) {
        StringBuilder sql = new StringBuilder("delete from ROLE_MENU where MENU_ID = '");
        sql.append(menuId);
        sql.append("'");
        this.getJdbcTemplate().execute(sql.toString());
    }
}
