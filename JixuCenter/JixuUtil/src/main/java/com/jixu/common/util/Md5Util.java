package com.jixu.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(Md5Util.class);

	public static String md5(String str){
		if(str==null){
			return "";
		}
		StringBuffer strBuffer=new StringBuffer();
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte[] hash=md.digest();
			for (int i = 0; i < hash.length; i++) {
				if((0xFF & hash[i]) < 0x10){
					strBuffer.append("0" + Integer.toHexString(0xFF & hash[i]));
				}else{
					strBuffer.append(Integer.toHexString(0xFF & hash[i]));
				}
			}
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage(),e);
		}
		return strBuffer.toString();
	}
	
	
//	public static void main(String[] args){
//		System.out.println(Md5Util.md5("jixu"));
//	}
}
