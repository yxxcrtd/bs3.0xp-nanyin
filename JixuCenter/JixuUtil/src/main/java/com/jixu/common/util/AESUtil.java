package com.jixu.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * @author Wang Zhongyuan
 */
public class AESUtil {

	private static String key = "123456789012jixu";
	private static String endkey="_%^&*)wWw(*&^%";

	/**
	 * AES加密--BASE64二次加密
	 * 
	 * @param pwd 明文
	 * @param key 密匙
	 * @return String类型的密文
	 */
	public static String Encrypt(String pwd) {
		if(pwd==null)
			return null;
		try {
			byte[] raw = key.getBytes("utf-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// "算法/模式/补码方式"
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] encrypted = cipher.doFinal(pwd.getBytes("utf-8"));
	
			String newpwd=new Base64().encodeToString(encrypted);// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
			//系统刚上线时，没有用加密机制。因此加密后在末尾加上"_%^&*)wWw(*&^%"字符，以区别密码是否加密
			newpwd=newpwd+endkey;
			return newpwd;
		}catch(Exception e) {
			e.printStackTrace();
			return "Error Password";
		}
	}

	/**
	 * BASE64解密--AES解密
	 * 
	 * @param pwd 密文
	 * @param key 密匙
	 * @return String类型的明文
	 */
	public static String Decrypt(String pwd) {
		if(pwd==null)
			return null;
		//系统刚上线时，没有用加密机制。因此加密后在末尾加上"_%^&*)wWw(*&^%"字符，以区别密码是否加密
		if(!pwd.contains(endkey))
			return pwd;
		
		pwd=pwd.replace(endkey, "");
		try {
			byte[] raw = key.getBytes("utf-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] encrypted1 = new Base64().decode(pwd);// 先用base64解密
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original, "utf-8");
			return originalString;
		}catch(Exception e) {
			e.printStackTrace();
			return "Error Password";
		}
	}

	public static void main(String[] args) throws Exception {
		// 此处使用AES-128-ECB加密模式，key需要为16位。
		// 需要加密的字串
		String pwd = null;
		// 加密
		System.out.println("输入的字符串是: " + pwd);
		String enString = AESUtil.Encrypt(pwd);
		System.out.println("加密后字符串是: " + enString);

		// 解密
		String DeString = AESUtil.Decrypt(enString);
		System.out.println("解密后字符串是: " + DeString);
	}
}
