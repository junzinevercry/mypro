package com.abs.action.base;

import java.io.InputStream;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abs.entity.file.UploadFile;
import com.abs.service.file.UploadFileService;
import com.abs.util.UploadFileProperties;

/**
 * <P><B>说明：</B>描述这个类的作用</P>
 * <P><B>日期：</B>2013-9-5 上午11:06:35</P>
 * @author zhangjun
 * @version 1.0
 */
@Controller
public class FileAction extends BaseAction {

    @Autowired
    private UploadFileService uploadFileService;

    @RequestMapping(value = "/file/gotoCreateFile.action")
    public String gotoCreateFile(HttpServletRequest request, HttpServletResponse response) {
        this.parepareForUploadFile(request);
        request.setAttribute("winName", request.getParameter("winName"));
        String fileType = request.getParameter("fileType");
        if ("undefined".equals(fileType)) {
            fileType = "";
        }
        request.setAttribute("fileType", fileType);
        return "file/createFile";
    }

    @RequestMapping(value = "/file/doCreateFile.action")
    public String doCreateFile(HttpServletRequest request, HttpServletResponse response) {
        String realName = request.getParameterValues("file1")[0];
        String fileName = request.getParameterValues("file1")[1];
        String path = request.getParameter("year") + "/" + request.getParameter("month") + "/"
                + request.getParameter("day") + "/" + fileName;
        String filePath = UploadFileProperties.getInstance().getValue("view") + path;

        UploadFile uploadFile = new UploadFile();
        uploadFile.setFileName(realName);
        uploadFile.setFilePath(filePath);
        if (StringUtils.isNotEmpty(realName)) {
            uploadFile.setFileType(realName.substring(realName.lastIndexOf(".") + 1));
        }
        uploadFileService.createUploadFile(uploadFile);
        request.setAttribute("fileId", uploadFile.getId());
        request.setAttribute("realName", realName);
        request.setAttribute("fileName", fileName);
        request.setAttribute("filePath", filePath);
        request.setAttribute("result", true);
        request.setAttribute("fileType", request.getParameter("fileType"));
        request.setAttribute("winName", request.getParameter("winName"));
        return "file/createFile";
    }

    @RequestMapping(value = "/file/doDownloadFile.action")
    public void doDownloadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String filePath = request.getParameter("filePath");
        String realName = request.getParameter("realName");
        if (StringUtils.isNotEmpty(filePath)) {
            URL url = new URL(filePath);
            java.io.InputStream in = url.openConnection().getInputStream();
            writeFile(response, in, realName);
            in.close();
        }
    }

    /**
     * 写入文件.
     * 
     * @param response
     * @param file
     *            文件对象
     * @param fileName
     *            文件名
     * @throws Exception
     */
    private void writeFile(HttpServletResponse response, InputStream in, String fileName) throws Exception {
        response.setContentType("text/html; charset=GBK");
        // 设置response的编码方式
        response.setContentType("application/x-msdownload");
        // 解决中文乱码
        response.setHeader("Content-Disposition", "attachment;filename="
                + new String(fileName.getBytes("GBK"), "iso-8859-1"));
        IOUtils.copy(in, response.getOutputStream());
    }
}
