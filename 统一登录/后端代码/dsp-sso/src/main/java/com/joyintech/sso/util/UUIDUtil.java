package com.joyintech.sso.util;


import java.util.UUID;

public class UUIDUtil {

	public static String generate() {
		String uuid = UUID.randomUUID().toString().replaceAll("-","");
		return uuid;
	}
}
