package com.abs.action.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abs.util.Uploader;

/**
 * <P><B>说明：</B>描述这个类的作用</P>
 * <P><B>日期：</B>2013-7-25 下午2:25:47</P>
 * @author zhangjun
 * @version 1.0
 */
@Controller
public class UeditorAction extends BaseAction {
    /**
     * 上传图片
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/ueditor/doImageUpload.action", headers = "")
    public void gotoLinkList(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            Uploader up = new Uploader(request);
            up.setSavePath("upload");
            String[] fileType = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };
            up.setAllowFiles(fileType);
            up.setMaxSize(10000); //单位KB
            up.upload();
            response.getWriter().print(
                    "{'original':'" + up.getOriginalName() + "','url':'" + up.getUrl() + "','title':'" + up.getTitle()
                            + "','state':'" + up.getState() + "'}");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return "ueditor/imageUp";
    }
}
