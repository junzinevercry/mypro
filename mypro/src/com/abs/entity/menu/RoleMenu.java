package com.abs.entity.menu;

import com.abs.entity.base.BaseEntity;
import com.abs.util.annotation.Columns;
import com.abs.util.annotation.Table;

/**
 * <P><B>说明：</B>描述这个类的作用</P>
 * <P><B>日期：</B>2013-6-6 上午9:10:38</P>
 * @author zhangjun
 * @version 1.0
 */
@Table(value = "ROLE_MENU")
public class RoleMenu extends BaseEntity {

    /** @Fields serialVersionUID : 描述这个变量表示什么 */
    private static final long serialVersionUID = -4998205058456675438L;
    private String roleId;
    private String menuId;

    @Columns(value = "ROLE_ID")
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Columns(value = "MENU_ID")
    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
