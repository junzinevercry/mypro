package com.abs.core.util;

import java.util.ResourceBundle;

public class ReadFileUtils {
	
	/**
	 * 根据文件路劲获取资源文件的参数值
	 * @param fileKey
	 * @return
	 */
	public static String getFilePath(String fileKey,String filePath) {
		ResourceBundle rb = ResourceBundle.getBundle(filePath);
		return rb.getString(fileKey);
	}
	
	public static void main(String[] args) {
		String var = ReadFileUtils.getFilePath("IS_UPDATE_START_MEETING", "public");
		System.out.println(var);
	}

}
