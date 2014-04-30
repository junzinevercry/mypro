package com.abs.action.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ServletContextAware;

import com.abs.entity.base.GenericEntity;
import com.abs.entity.common.PageObject;
import com.abs.service.menu.MenuService;
import com.abs.service.security.OperatorDetails;
import com.abs.service.security.UserContext;
import com.abs.util.CommonUtils;
import com.abs.util.UploadFileProperties;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
public class BaseAction implements ServletContextAware {

    @Autowired
    private MenuService menuService;

    public static final String PROJECT_PATH = BaseAction.class.getResource("/").toString().replaceAll("^file:", "")
            .replaceAll("WEB-INF/classes", "");

    private final void setCurrentMenuName(HttpServletRequest request, String currentMenuName) {
        request.setAttribute("subMenuList", menuService.listSubMenu(currentMenuName, this.getUserId()));
        request.setAttribute("parentMenu", menuService.findParentMenu(currentMenuName, this.getUserId()));
        request.setAttribute("currentMenu", menuService.findCurrentMenu(currentMenuName, this.getUserId()));
    }

    protected final void setCurrentMenuName(HttpServletRequest request) {
        setCurrentMenuName(request, request.getParameter("winName"));
        request.setAttribute("winName", request.getParameter("winName"));
    }

    protected <T extends GenericEntity> void fillGenericProperty(T entity, boolean isCreate) {
        Date currentDate = new Date();
        if (isCreate) {
            entity.setCreateDate(currentDate);
            entity.setCreateBy(this.getUserId());
        }
        entity.setUpdateDate(currentDate);
        entity.setUpdateBy(this.getUserId());
    }

    /** 分页对象 */
    protected PageObject pageObject;

    protected ServletContext servletContext;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public final <T> PageObject<T> getPageObject(HttpServletRequest request) {
        int currentPage = 1;
        Integer pageFrom = CommonUtils.stringToInteger(request.getParameter("currentPage"));
        if (pageFrom != null) {
            currentPage = pageFrom;
        }
        pageObject = new PageObject<T>();
        pageObject.setCurrentPage(currentPage);
        return pageObject;
    }

    public void setPageObject(PageObject pageObject) {
        this.pageObject = pageObject;
    }

    /**
     * ajax返回json
     * 
     * @param request
     * @param response
     * @param resStr
     * @return
     * @throws IOException
     */
    protected String ajaxReturnJSON(HttpServletRequest request, HttpServletResponse response, String resStr) {
        return this.ajaxReturn(request, response, resStr, false);
    }

    /**
     * ajax返回xml
     * 
     * @param request
     * @param response
     * @param resStr
     * @return
     * @throws IOException
     */
    protected String ajaxReturnXML(HttpServletRequest request, HttpServletResponse response, String resStr) {
        return this.ajaxReturn(request, response, resStr, true);
    }

    /**
     * ajax返回基础方法
     * 
     * @param request
     * @param response
     * @param resStr
     * @param isXml
     * @return
     */
    private String ajaxReturn(HttpServletRequest request, HttpServletResponse response, String resStr, boolean isXml) {
        response.setCharacterEncoding("UTF-8");
        if (isXml) {
            response.setContentType("text/xml");
        }
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(resStr);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将request的内容重新放到requset中.
     * 
     * @param request
     */
    protected final void resetRequest(HttpServletRequest request) {
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            request.setAttribute(parameterName, request.getParameter(parameterName));
        }
    }

    protected final String getUserId() {
        return this.getLoginUser().getUserid();
    }

    /**
     * 
     * getLoginUser:获取当前登录的User对象(OperatorDetails),由SpringSecurity维护
     * 
     * @param
     * @param request
     * @param
     * @return 设定文件
     * @return TUser DOM对象
     * @throws
     * @since CodingExample Ver 1.1
     */
    protected OperatorDetails getLoginUser() {
        return UserContext.getUser();
    }

    /**
     * <P><B>说明：</B>描述这个方法的作用</P>
     * <P><B>日期：</B>2013-6-6 下午4:55:40</P>
     * <P><B>作者：</B>zhangjun</P>
     * @param request
     */
    protected void parepareForUploadFile(HttpServletRequest request) {
        request.setAttribute("uploadURL", UploadFileProperties.getInstance().getValue("uploadURL"));
        request.setAttribute("sessionId", request.getSession().getId());
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
        request.setAttribute("basePath", basePath);
        request.setAttribute("year", Calendar.getInstance().get(Calendar.YEAR));
        request.setAttribute("month", Calendar.getInstance().get(Calendar.MONTH) + 1);
        request.setAttribute("day", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
    }
}
