/*
 * 文件名：MstUserAndRoleImplMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年9月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.core.impl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.User;

/**
 * 用户与角色
 * 〈功能详细描述〉
 * @author Administrator
 * @version 1.0 2018年9月19日
 * @see MstUserAndRoleImplMapper
 * @since 1.0
 */
@Repository
public interface MstUserAndRoleImplMapper {

	/**
	 * 
	 * 根据用户角色Id获取所有用户的信息
	 * <功能详细描述>
	 * @param roleId
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<User> getUserByRole(String roleId);

	/**
	 * 
	 * 根据部门或机构Id获取所有的用户Id
	 * <功能详细描述>
	 * @param map 参数map
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<User> getUserByDepartmentCodeOrInstitu(Map<String,Object> map);
    
	/**
	 * 查询用户信息
	 * @param username 用户名
	 * @return 用户信息
	 */
	User findByUsernameIgnoreCase(String username);

    /**
     * 根据用户名查找用户
     * 
     * @param username 用户名
     * @return 用户信息
     */
	User findByUsername(String username);
}
