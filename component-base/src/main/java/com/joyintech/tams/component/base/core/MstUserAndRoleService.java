/*
 * 文件名：MstUserAndRoleService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年9月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.core;

import com.joyintech.tams.component.base.core.dto.MstUserAndRoleOutputParameter;

/**
 * 获取用户和角色的
 * 〈功能详细描述〉
 * @author Administrator
 * @version 1.0 2018年9月19日
 * @see MstUserAndRoleService
 * @since 1.0
 */
public interface MstUserAndRoleService {
	/**
	 * 
	 * 根据用户角色Id获取所有用户的信息
	 * <功能详细描述>
	 * @param roleId 角色Id
	 * @return 用户
	 * @see
	 * @since 1.0
	 */
	MstUserAndRoleOutputParameter getUserByRole(String roleId);
	/**
	 * 
	 * 根据部门Id获取所有的用户Id
	 * <功能详细描述>
	 * @param departmentCode 部门代码
	 * @return 用户
	 * @see
	 * @since 1.0
	 */
	MstUserAndRoleOutputParameter getUserByDepartmentCode(String departmentCode);
	
	/**
	 * 
	 * 根据机构Id获取所有的用户Id
	 * <功能详细描述>
	 * @param institutionCode
	 * @return 用户
	 * @see
	 * @since 1.0
	 */
	MstUserAndRoleOutputParameter getUserByInstitutionCode(String institutionCode);
	
	/**
     * 
     * 获取所有的用户信息
     * <功能详细描述>
     * @return 用户
     * @see
     * @since 1.0
     */
    MstUserAndRoleOutputParameter getAllUserInfo();
}
