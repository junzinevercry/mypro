package com.abs.dao.role;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.abs.dao.base.BaseDao;
import com.abs.dto.role.RoleDto;
import com.abs.entity.common.PageObject;
import com.abs.entity.role.Role;
import com.abs.util.BeanUtil;

@Repository
public class RoleDao extends BaseDao {
    /**
     * 获得所有的角色(翻页).
     */
    public void listRoleForPage(Role queryRole, PageObject<Role> pageObject) {
        StringBuilder sql = new StringBuilder("select * from ROLE where 1=1 ");
        if (queryRole != null) {
            if (StringUtils.isNotEmpty(queryRole.getName())) {
                sql.append(" and name like '%");
                sql.append(queryRole.getName().trim());
                sql.append("%'");
            }
        }
        sql.append(" order by sort desc");
        this.listForPage(Role.class, sql.toString(), pageObject);
    }

    /**
     * 获得所有的角色(翻页).
     */
    public void listRoleForPageCheckBox(Role queryRole, PageObject<RoleDto> pageObject) {
        StringBuilder sql = new StringBuilder("select * from ROLE where 1=1 ");
        if (queryRole != null) {
            if (StringUtils.isNotEmpty(queryRole.getName())) {
                sql.append(" and name like '%");
                sql.append(queryRole.getName().trim());
                sql.append("%'");
            }
        }
        sql.append(" order by sort");
        this.listForPage(RoleDto.class, sql.toString(), pageObject);
    }

    /**
     * 根据名称获得角色.
     * 
     * @param name
     * @param id
     * @return
     */
    public Role findRoleByName(String name, String id) {
        StringBuilder sql = new StringBuilder("select * from ROLE where 1=1 ");
        if (StringUtils.isNotEmpty(name)) {
            sql.append(" and name='");
            sql.append(name);
            sql.append("'");
        }
        if (id != null) {
            sql.append(" and id <> '");
            sql.append(id);
            sql.append("'");
        }
        return BeanUtil.getAnyBean4MySQL(Role.class, this.getJdbcTemplate().queryForList(sql.toString()));
    }

    /**
     * 根据code获得角色.
     * 
     * @param code
     * @param id
     * @return
     */
    public Role findRoleByCode(String code, String id) {
        StringBuilder sql = new StringBuilder("select * from ROLE where 1=1 ");
        if (StringUtils.isNotEmpty(code)) {
            sql.append(" and code='");
            sql.append(code);
            sql.append("'");
        }
        if (id != null) {
            sql.append(" and id <> '");
            sql.append(id);
            sql.append("'");
        }
        return this.findObject(Role.class, sql.toString());
    }

    public List<Role> findCodesByUserId(String userId) {
        StringBuilder sql = new StringBuilder(
                "SELECT * FROM role as sr WHERE sr.id IN(SELECT sur.role_id from user_role as sur where sur.user_id = '");
        sql.append(userId);
        sql.append("')");
        List<Role> roleList = this.listObject(Role.class, sql.toString());
        return roleList;

    }

}
