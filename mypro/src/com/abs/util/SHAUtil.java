package com.abs.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAUtil {
	
	public static String sha(String originalStr) {
		if ("".equals(originalStr) && originalStr == null) {
			return "";
		}
		String digestStr = null;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException exc) {
			System.err.println("This version of Java does not support SHA-1.");
			return null;
		}
		md.reset();
		md.update(originalStr.getBytes());
		byte[] rawDigest = md.digest();

		StringBuffer str = new StringBuffer();
		for (int i = 0; i < rawDigest.length; i++) {
			String bStr = Integer.toHexString((int) rawDigest[i] & 0xff);
			if (bStr.length() == 1) {
				str.append("0" + bStr);
			} else {
				str.append(bStr);
			}
		}
		digestStr = str.toString();

		return digestStr;
	}

	public static void main(String[] args) {
		String str = SHAUtil.sha("1");
		System.out.print(str + ":" + str.length());
	}

}
