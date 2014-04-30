package com.abs.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 * 附件工具类.
 * 
 * @author Zhangjun
 * 
 */
public class FileUtil {
    /** 属性文件. */
    /*private static Properties prop = new Properties();
    static {
    	try {
    		prop.load(FileUtil.class.getResourceAsStream("/file.properties"));
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }

    *//**
      * 得到某一属性的值.
      * 
      * @param key
      *            取得其值的键
      * @return key的值
      */
    /*
    public static String getValue(String key) {
    if (prop.containsKey(key)) {
    	// 得到某一属性的值
    	String value = prop.getProperty(key);
    	return value;
    } else
    	return "";
    }*/

    /**
     * 删除目录（文件夹）以及目录下的文件
     * 
     * @param dir
     *            被删除目录的文件路径
     * @return 目录删除成功返回true,否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator)) {
            dir = dir + File.separator;
        }
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            System.out.println("删除目录失败" + dir + "目录不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
            // 删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }
        if (!flag) {
            System.out.println("删除目录失败");
            return false;
        }

        // 删除当前目录
        if (dirFile.delete()) {
            System.out.println("删除目录" + dir + "成功！");
            return true;
        } else {
            System.out.println("删除目录" + dir + "失败！");
            return false;
        }
    }

    /**
     * 删除单个文件
     * 
     * @param fileName
     *            被删除文件的文件名
     * @return 单个文件删除成功返回true,否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.isFile() && file.exists()) {
            file.delete();
            System.out.println("删除单个文件" + fileName + "成功！");
            return true;
        } else {
            System.out.println("删除单个文件" + fileName + "失败！");
            return false;
        }
    }

    /**
     * 写入一个文件.
     * 
     * @param path
     *            文件名
     * @param content
     *            文件内容
     */
    public static void write(String path, String content) {
        BufferedWriter output = null;
        try {
            File f = new File(path);
            if (f.exists()) {
                System.out.println("文件存在");
            } else {
                System.out.println("文件不存在，正在创建...");
                if (f.createNewFile()) {
                    System.out.println("文件创建成功!");
                } else {
                    System.out.println("文件创建失败!");
                }
            }
            output = new BufferedWriter(new FileWriter(f));
            output.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                throw new RuntimeException("写入文件时出错", e);
            }
        }
    }

    /**
     * 移动文件from到to.
     * 
     * @param from
     *            源文件
     * @param to
     *            目标文件
     * @throws IOException
     */
    public static void moveFile(File from, File to) {
        try {
            FileUtils.copyFile(from, to);
        } catch (IOException e) {
            throw new RuntimeException("移动文件时出错", e);
        }
    }

    private static boolean isMultipart(HttpServletRequest request) {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);//判断提交的表单时否文件上传
        return isMultipart;
    }

    @SuppressWarnings("deprecation")
    private static String getAbsolutePath(HttpServletRequest request) {
        String realPath = request.getRealPath("/");
        String path = realPath + getOppositePath();
        return path;
    }
    public static String getOppositePath(){
        return "/resource/images/news/";
    }

    private static void processFormField(FileItem i,Map<String,String> parameterMaps) {
        parameterMaps.put(i.getFieldName(), i.getString());
    }

    private static void processFile(FileItem i, String absoultPath,Map<String,String> parameterMaps) throws IOException {
        InputStream in = null;
        OutputStream out = null;
        try {
            in  = i.getInputStream();
            String fileName = i.getName();
            fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
            if(fileName.equals("")){
                return;
            }
            fileName = System.currentTimeMillis()+fileName.substring(fileName.lastIndexOf("."));
            File dir = new File(absoultPath);
            if (!dir.exists())
                dir.mkdir();
            File f = new File(absoultPath + fileName);
            if (!f.exists())
                f.createNewFile();
            out = new FileOutputStream(new File(absoultPath + fileName));
            IOUtils.copy(in, out);
            parameterMaps.put(i.getFieldName(),fileName);
            WinPicture myWin=new WinPicture(); 
         // compressPic(大图片路径,生成小图片路径,大图片文件名,生成小图片文名,生成小图片宽度,生成小图片高度,是否等比缩放(默认为true))
            System.out.println("输入的图片大小：" + myWin.getPicSize(absoultPath+fileName)/1024 + "KB"); 
            myWin.compressPic(absoultPath,absoultPath, fileName, "s"+fileName, 300, 220, true); 
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }
    }
    private static class MyHttpServletRequest extends HttpServletRequestWrapper{
        private Map<String,String> parameterMaps;
        public MyHttpServletRequest(HttpServletRequest request,Map<String,String> parameterMaps) {
            super(request);
            this.parameterMaps = parameterMaps;
        }
        @Override
        public String getParameter(String name) {
            return parameterMaps.get(name);
        }
        @SuppressWarnings("rawtypes")
        @Override
        public Map getParameterMap() {
            return parameterMaps;
        }
    }
    @SuppressWarnings("unchecked")
    public static HttpServletRequest upload(HttpServletRequest request) throws FileUploadException, IOException {
         Map<String,String> parameterMaps = new HashMap<String,String>();
        if (isMultipart(request)) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> items = upload.parseRequest(request);
            for (FileItem i : items) {
                if (i.isFormField()) {
                    processFormField(i,parameterMaps);
                } else {
                    processFile(i, getAbsolutePath(request),parameterMaps);
                }
            }
        }
        return new MyHttpServletRequest(request, parameterMaps);
    }
}
