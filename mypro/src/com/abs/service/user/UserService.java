package com.abs.service.user;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abs.dao.user.UserDao;
import com.abs.dao.userRole.UserRoleDao;
import com.abs.dto.user.UserDto;
import com.abs.entity.common.PageObject;
import com.abs.entity.user.User;
import com.abs.entity.user.UserRole;

/**
 * <P><B>说明：</B>描述这个类的作用</P>
 * <P><B>日期：</B>2013-6-4 下午4:21:19</P>
 * @author zhangjun
 * @version 1.0
 */
/**
 * <P>
 * <B>说明：</B>描述这个类的作用
 * </P>
 * <P>
 * <B>日期：</B>2013-12-4 下午2:03:56
 * </P>
 * 
 * @author ytx
 * @version 1.0
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;

    /**
     * <P>
     * <B>说明：</B>根据id查询user
     * </P>
     * <P>
     * <B>日期：</B>2013-6-4 下午4:21:40
     * </P>
     * <P>
     * <B>作者：</B>zhangjun
     * </P>
     * 
     * @param userId
     * @return
     */
    public User findUserById(String id) {
        return this.userDao.findObjectById(User.class, id);
    }

    private JSONObject getSuccessJson() {
        JSONObject json = new JSONObject();
        json.put("type", "success");
        return json;
    }

    public String createUser(User user, List<UserRole> userRoleList) {
        this.userDao.createObject(user);
        this.createUserRoleList(user, userRoleList);
        return this.getSuccessJson().toString();
    }

    public String updateUser(User user, List<UserRole> userRoleList) {
        this.userDao.updateObject(user);
        userRoleDao.deleteUserRoleByUserId(user.getId());
        this.createUserRoleList(user, userRoleList);
        return this.getSuccessJson().toString();
    }

    public void listUserForPage(Map<String, String> queryMap, PageObject<UserDto> pageObject) {
        this.userDao.listUserForPage(queryMap, pageObject);
    }

    public UserDto findUserDtoById(String id) {
        return this.userDao.findUserDtoById(id);
    }

    private void createUserRoleList(User user, List<UserRole> userRoleList) {
        for (UserRole userRole : userRoleList) {
            userRole.setUserId(user.getId());
            this.userRoleDao.createObject(userRole);
        }
    }

    public String updateUserState(String userId, String state) {
        // TODO Auto-generated method stub
        return null;
    }
}
