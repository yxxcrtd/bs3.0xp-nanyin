package com.jixu.common.util;

import java.sql.Timestamp;
import java.util.Date;

import com.jixu.common.pojo.BasicEntity;
import com.jixu.common.tool.JixuUserUtil;

public class LoginUserRequest {
	
	public static BasicEntity getBasicEntity(){
		BasicEntity bt=null;
			
		Timestamp stamp=new Timestamp(new Date().getTime());
		
		String username=JixuUserUtil.getLoginUserName();
		bt=new BasicEntity();
		bt.setCreateUser(username);
		bt.setCreateDate(stamp);
		bt.setUpdateUser(username);
		bt.setUpdateDate(stamp);
		
		return bt;
	}
}
