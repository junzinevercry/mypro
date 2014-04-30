package com.abs.dao.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.abs.dao.base.BaseDao;
import com.abs.dto.user.UserDto;
import com.abs.entity.common.PageObject;
import com.abs.entity.user.User;
import com.abs.util.CommonUtils;

@Repository(value = "userDao")
public class UserDao extends BaseDao {

    public List<User> findUserByUserName(String username) {
        StringBuilder sql = new StringBuilder(" select * from user u where u.user_name= '");
        sql.append(username).append("'");
        sql.append(" and u.state='1'");
        return this.listObject(User.class, sql.toString());
    }

    public List<String> getRoleCodesByUserId(String userId) {
        StringBuilder sql = new StringBuilder(
                " select r.code from role r ,user_role ur where r.id=ur.role_id and ur.user_id= '");
        sql.append(userId);
        sql.append("'");
        List<String> list = new ArrayList<String>();
        for (Map<String, Object> m : this.getJdbcTemplate().queryForList(sql.toString())) {
            list.add(CommonUtils.toString(m.get("CODE")));
        }
        return list;
    }

    public void listUserForPage(Map<String, String> queryMap, PageObject<UserDto> pageObject) {
        StringBuilder sql = new StringBuilder("");
        this.listForPage(UserDto.class, sql.toString(), pageObject);
    }

    public UserDto findUserDtoById(String id) {
        StringBuilder sql = new StringBuilder("select u.* from user u where u.id = '");
        sql.append(id);
        sql.append("'");
        return this.findObject(UserDto.class, sql.toString());
    }

}
