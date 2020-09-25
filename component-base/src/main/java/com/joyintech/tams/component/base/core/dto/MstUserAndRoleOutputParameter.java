/*
 * 文件名：MstUserAndRoleOutputParameter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年9月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.core.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.User;

/**
 * 用户和角色输出参数
 * 〈功能详细描述〉
 * @author Administrator
 * @version 1.0 2018年9月19日
 * @see MstUserAndRoleOutputParameter
 * @since 1.0
 */
public class MstUserAndRoleOutputParameter {
	
	/**
	 * 用户结果
	 */
	List<User> userList;

	/**
	 * 获取 userList
	 * @return userList.
	 */
	public List<User> getUserList() {
		return userList;
	}

	/**
	 * 设置 userList
	 * @param userList userList
	 */
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
}
