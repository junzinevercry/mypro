package com.abs.entity.user;

import com.abs.entity.base.BaseEntity;
import com.abs.util.annotation.Columns;
import com.abs.util.annotation.Table;

@Table(value = "user_role")
public class UserRole extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 1390075163313131078L;
    private String userId;
    private String roleId;

    @Columns(value = "USER_ID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Columns(value = "ROLE_ID")
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

}
