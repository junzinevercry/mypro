package com.abs.action.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abs.action.base.BaseAction;
import com.abs.dto.menu.MenuDto;
import com.abs.dto.user.UserDto;
import com.abs.entity.user.User;
import com.abs.service.menu.MenuService;
import com.abs.service.user.UserService;
import com.abs.util.Constant;

/**
 * <P>
 * <B>说明：</B>描述这个类的作用
 * </P>
 * <P>
 * <B>日期：</B>2013-6-4 下午3:56:06
 * </P>
 * 
 * @author zhangjun
 * @version 1.0
 */
@Controller
public class LoginAction extends BaseAction {
    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    /**
     * 系统登录页面.
     * 
     * @param request
     * @return
     */
    @RequestMapping("/login.action")
    public String login(HttpServletRequest request) {
        request.getSession().setAttribute(Constant.SYS_GLOBAL_LOGIN_SESSION_USER, null);
        return "login";
    }

    /**
     * 系统登录超时页面.
     * 
     * @param request
     * @return
     */
    @RequestMapping("/loginout.action")
    public String loginout(HttpServletRequest request) {
        return "loginTimeOut";
    }

    @RequestMapping("/logout.action")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute(Constant.SYS_GLOBAL_LOGIN_SESSION_USER);
        return "redirect:/login.action";
    }

    /**
     * <P>
     * <B>说明：</B>描述这个方法的作用
     * </P>
     * <P>
     * <B>日期：</B>2013-6-5 下午2:39:56
     * </P>
     * <P>
     * <B>作者：</B>zhangjun
     * </P>
     * 
     * @param request
     * @return
     */
    @RequestMapping("/index.action")
    public String index(HttpServletRequest request) {
        request.getSession().setAttribute(Constant.SYS_GLOBAL_LOGIN_SESSION_USER, this.getLoginUser());
        User user = this.userService.findUserById(this.getUserId());
        request.setAttribute("realName", user.getRealName());
        return "index";
    }

    /**
     * <P>
     * <B>说明：</B>描述这个方法的作用
     * </P>
     * <P>
     * <B>日期：</B>2013-6-5 下午2:39:56
     * </P>
     * <P>
     * <B>作者：</B>zhangjun
     * </P>
     * 
     * @param request
     * @return
     */
    @RequestMapping("/home.action")
    public String home(HttpServletRequest request) {
        return "home";
    }

    /**
     * <P>
     * <B>说明：</B>描述这个方法的作用
     * </P>
     * <P>
     * <B>日期：</B>2013-6-14 上午10:53:55
     * </P>
     * <P>
     * <B>作者：</B>zhangjun
     * </P>
     * 
     * @param request
     * @return
     */
    @RequestMapping("/left.action")
    public String left(HttpServletRequest request) {
        UserDto loginUser = userService.findUserDtoById(this.getUserId());
        request.setAttribute("loginUser", loginUser);
        Map<String, String> map = new HashMap<String, String>();
        List<MenuDto> menuList = menuService.listMenuForHomePage(this.getUserId());
        map.put("num", this.setLeftMenuNum("topic", menuList).get("winNum"));
        request.setAttribute("num", map);
        return "left";
    }

    private Map<String, String> setLeftMenuNum(String winName, List<MenuDto> menuList) {
        Map<String, String> result = new HashMap<String, String>();
        int i = 0;
        for (MenuDto menuDto : menuList) {
            if (winName.equals(menuDto.getWinName())) {
                result.put("winNum", i + "");
                return result;
            }
            i += 1;
        }
        result.put("num", i + "");
        result.put("url", "");
        result.put("winName", "");
        return result;
    }

    @RequestMapping("/updateLeft.action")
    public String left(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> map = new HashMap<String, String>();
        this.ajaxReturnJSON(request, response, JSONObject.fromObject(map).toString());
        return null;
    }

    @RequestMapping("/tools.action")
    public String tools(HttpServletRequest request) {
        return "/tools/tools";
    }

    @RequestMapping("/calendar.action")
    public String calendar(HttpServletRequest request) {
        return "/tools/calendar";
    }

    @RequestMapping("/calculator.action")
    public String calculator(HttpServletRequest request) {
        return "/tools/calculator";
    }

    @RequestMapping("/calculator/common.action")
    public String common(HttpServletRequest request) {
        return "/tools/common";
    }

    @RequestMapping("/calculator/science.action")
    public String science(HttpServletRequest request) {
        return "/tools/science";
    }

}
