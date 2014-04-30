package com.abs.dao.userRole;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.abs.dao.base.BaseDao;
import com.abs.entity.role.Role;
import com.abs.entity.user.UserRole;

@Repository
public class UserRoleDao extends BaseDao {

    /**
     * 根据用户id查询所拥有的全部权限
     * 
     * @param userId
     * @return
     */
    public List<Role> findRoleByUserId(String userId) {
        StringBuilder sql = new StringBuilder(
                "select ar.* from user_role ur left join role ar on ur.role_id=ar.id where ur.user_id = '");
        sql.append(userId);
        sql.append("'");
        return this.listObject(Role.class, sql.toString());
    }

    public List<UserRole> findUserRoleByUserId(String userId) {
        StringBuilder sql = new StringBuilder("select ur.* from user_role ur  where ur.user_id = '");
        sql.append(userId);
        sql.append("'");
        return this.listObject(UserRole.class, sql.toString());
    }

    /**
     * 删除用户权限对应关系
     * 
     * @param ur
     */
    public void deleteUserRole(UserRole ur) {
        StringBuilder sql = new StringBuilder("delete from user_role where user_id = '");
        sql.append(ur.getUserId());
        sql.append("' and role_id = '");
        sql.append(ur.getRoleId());
        sql.append("'");
        this.getJdbcTemplate().execute(sql.toString());
    }

    public void deleteUserRoleByUserId(String userId) {
        StringBuilder sql = new StringBuilder("delete from user_role where user_id = '");
        sql.append(userId);
        sql.append("'");
        this.getJdbcTemplate().execute(sql.toString());
    }

    public void deleteUserRoleByRoleId(String roleId) {
        StringBuilder sql = new StringBuilder("delete from user_role where role_id = '");
        sql.append(roleId);
        sql.append("'");
        this.getJdbcTemplate().execute(sql.toString());
    }
}
