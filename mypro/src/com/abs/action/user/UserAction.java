package com.abs.action.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abs.action.base.BaseAction;
import com.abs.dto.user.UserDto;
import com.abs.entity.common.PageObject;
import com.abs.entity.user.User;
import com.abs.entity.user.UserRole;
import com.abs.service.user.UserService;
import com.abs.service.userRole.UserRoleService;
import com.abs.util.CommonUtils;
import com.abs.util.Constant;
import com.abs.util.SHAUtil;

@Controller
public class UserAction extends BaseAction {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping("/user/gotoUserList.action")
    public String gotoUserList(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("userName", request.getParameter("userName"));
        PageObject<UserDto> pageObject = this.getPageObject(request);
        this.userService.listUserForPage(queryMap, pageObject);
        request.setAttribute("queryMap", queryMap);
        request.setAttribute("pageObject", pageObject);
        this.setCurrentMenuName(request);
        return "user/userList";
    }

    @RequestMapping("/user/gotoUserListForRadio.action")
    public String gotoUserListForRadio(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("username", request.getParameter("username"));
        queryMap.put("realname", request.getParameter("realname"));
        queryMap.put("deptid", request.getParameter("deptid"));
        PageObject<UserDto> pageObject = this.getPageObject(request);
        this.userService.listUserForPage(queryMap, pageObject);
        request.setAttribute("queryMap", queryMap);
        request.setAttribute("pageObject", pageObject);
        request.setAttribute("openerName", request.getParameter("openerName"));
        return "user/userListForRadio";
    }

    @RequestMapping("/user/gotoCreateUser.action")
    public String gotoCreateUser(HttpServletRequest request, HttpServletResponse response) {
        return "user/createUser";
    }

    @RequestMapping("/user/gotoUpdateUser.action")
    public String gotoUpdateUser(HttpServletRequest request, HttpServletResponse response) {
        UserDto userDto = this.userService.findUserDtoById(request.getParameter("userId"));
        request.setAttribute("user", userDto);
        request.setAttribute("userRole", this.userRoleService.findRoleByUserId(request.getParameter("userId")));
        return "user/updateUser";
    }

    @RequestMapping("/user/doCreateUser.action")
    public void doCreateUser(HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        user.setPassword(SHAUtil.sha(Constant.DEFAULT_PASSWORD));
        user.setState(Constant.DEFAULT_STATE);
        this.fillGenericProperty(user, true);
        this.prepareForCreateOrUpdate(user, request);
        List<UserRole> userRoleList = this.getUserRoleFromRequest(request);
        String result = this.userService.createUser(user, userRoleList);
        this.ajaxReturnJSON(request, response, result);
    }

    @RequestMapping("/user/doUpdateUser.action")
    public void doUpdateUser(HttpServletRequest request, HttpServletResponse response) {
        User user = this.userService.findUserById(request.getParameter("id"));
        this.prepareForCreateOrUpdate(user, request);
        List<UserRole> userRoleList = this.getUserRoleFromRequest(request);
        String result = this.userService.updateUser(user, userRoleList);
        this.ajaxReturnJSON(request, response, result);
    }

    private List<UserRole> getUserRoleFromRequest(HttpServletRequest request) {
        List<UserRole> userRoleList = new ArrayList<UserRole>();

        return userRoleList;
    }

    private void prepareForCreateOrUpdate(User user, HttpServletRequest request) {
        user.setRealName(request.getParameter("realName"));
        user.setUserName(request.getParameter("userName"));
        user.setSex(request.getParameter("sex"));
        user.setAge(CommonUtils.stringToInteger(request.getParameter("age")));
    }

}
