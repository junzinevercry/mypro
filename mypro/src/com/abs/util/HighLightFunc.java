package com.abs.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HighLightFunc {
	/**
	 * 描　述: 截断字符串 突出查询条件 并调整高亮关键字位置
	 * 
	 * @param str1
	 * @param str2
	 * @param color
	 * @return
	 * 
	 */
	public static String setStrongInterceptString(String str1, String str2,
			String color) {
		str1 = str1.trim();
		str2 = str2.trim();
		int maxLen = 15;
		int preLen = maxLen / 2;
		int postLen = maxLen / 2;
		int subLen = str2.length();
		String tempStr = new String(str1);

		if (str1 != null && str1.length() > maxLen) {// 长度大于规定长度
			int mPos = str1.toLowerCase().indexOf(str2.toLowerCase());
			int subMidPos = mPos + subLen / 2;
			if (mPos != -1) {
				String preStr = "";
				String postStr = "";
				if (mPos == 0) {
					preStr = "";
					postStr = "...";
				} else if ((mPos + subLen) == str1.length()) {
					preStr = "...";
					postStr = "";
				} else {
					preStr = "...";
					postStr = "...";
				}
				if (subLen > maxLen) { // 关键字字符串大于规定长度
					str1 = preStr + str2.substring(0, maxLen) + "...";
					str2 = str2.substring(0, maxLen);
				} else if (subLen == maxLen) {
					str1 = preStr + str2.substring(0, maxLen) + postStr;
					str2 = str2.substring(0, maxLen);
				} else {
					if ((mPos + subLen) >= maxLen) {
						if (subMidPos + postLen >= str1.length()) { // 如果需截取的结束位置大于str1的长度则取str1的长度
							str1 = preStr
									+ str1.substring(str1.length() - maxLen,
											str1.length()) + postStr;
						} else {
							str1 = preStr
									+ str1.substring(subMidPos - preLen,
											subMidPos + postLen) + postStr;
						}
					} else {
						str1 = str1.substring(0, maxLen) + "...";
					}
				}
			} else {
				str1 = str1.substring(0, maxLen) + "...";
			}
		}
		// title每隔20个字符换行一次
		String strong = "<span title='" + tempStr.toString() + "'>";
		strong += strHelper(str1, str2, color) + "</span>";
		return strong;
	}

	/**
	 * @param str1
	 * @param str2
	 * @param color
	 * @return 截断字符串 并突出查询条件
	 */
	private static String strHelper(String str1, String str2, String color) {
		if (str2 == null || str2.equals("")) {
			return str1;
		}
		String prefix = "";
		String suffix = "";
		String strong = "<font color='" + color + "'>";
		str2 = str2.trim();
		int index = str1.toLowerCase().indexOf(str2.toLowerCase());
		if (index != -1) {
			prefix = str1.substring(0, index);
			strong += str1.substring(index, index + str2.length());
			strong += "</font>";
			suffix = str1.substring(index + str2.length(), str1.length());
			return prefix + strong + suffix;
		}
		return str1;
	}
	
	
	/**
     * 
     * <pre>
     * 创建人: 纪晨
     * 创建于: 2011-10-17
     * 描　述:
     *    返回当前日期
     * </pre>
     * 
     * @param str
     * @param enc
     * @return
     */
    public static String getTodayDate(String formatType) {
        SimpleDateFormat format = new SimpleDateFormat(formatType);
        return format.format(new Date()); 
    }
    
    
    
    
    /**
     * 描　述: 截断字符串 突出查询条件 并调整高亮关键字位置
     * 
     * @param str1
     * @param str2
     * @param color
     * @return
     * 
     */
    public static String setStrong(String str1, String str2,
            String color) {
        str1 = str1.trim();
        str2 = str2.trim();
//        int maxLen = 15;
//        int preLen = maxLen / 2;
//        int postLen = maxLen / 2;
//        int subLen = str2.length();
        String tempStr = new String(str1);

        // title每隔20个字符换行一次
        String strong = "<span title='" + tempStr.toString() + "'>";
        strong += strHelper(str1, str2, color) + "</span>";
        return strong;
    }
}
