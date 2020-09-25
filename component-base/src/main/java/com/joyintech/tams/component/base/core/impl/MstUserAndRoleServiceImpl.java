/*
 * 文件名：MstUserAndRoleServiceImpl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年9月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.base.core.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.common.base.entity.User;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.component.base.core.MstUserAndRoleService;
import com.joyintech.tams.component.base.core.dto.MstUserAndRoleOutputParameter;
import com.joyintech.tams.component.base.core.impl.dao.MstUserAndRoleImplMapper;
import org.springframework.util.StringUtils;

/**
 * 用户和角色实现类
 * 
 * @author Administrator
 * @version 1.0 2018年9月19日
 * @see MstUserAndRoleServiceImpl
 * @since 1.0
 */
@Service
public class MstUserAndRoleServiceImpl implements MstUserAndRoleService {
	/**
	 * dao
	 */
	@Autowired
	private MstUserAndRoleImplMapper mstUserAndRoleImplMapper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@DataSource(DataSourceType.OTHER)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public MstUserAndRoleOutputParameter getUserByRole(String roleId) {
		List<User> temp = mstUserAndRoleImplMapper.getUserByRole(roleId);
		MstUserAndRoleOutputParameter out = new MstUserAndRoleOutputParameter();
		out.setUserList(temp);
		return out;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@DataSource(DataSourceType.OTHER)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public MstUserAndRoleOutputParameter getUserByDepartmentCode(String deptCode) {
		Map<String, Object> map = new HashMap<>();
		map.put("deptCode", deptCode);
		return getUserByDepartmentCodeOrInstitu(map);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@DataSource(DataSourceType.OTHER)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public MstUserAndRoleOutputParameter getUserByInstitutionCode(String institutionCode) {
		Map<String, Object> map = new HashMap<>();
		map.put("institutionCode", institutionCode);
		return getUserByDepartmentCodeOrInstitu(map);
	}

	/**
	 * 根据机构或部门代码查询 <功能详细描述>
	 * 
	 * @param institutionCode
	 * @return
	 * @see
	 * @since 1.0
	 */
	private MstUserAndRoleOutputParameter getUserByDepartmentCodeOrInstitu(Map<String, Object> map) {
		if(MultilevelDataAuthorityUtil.isEnabled()){
			if(!StringUtil.equals(LoginUserUtil.getUserOrgType(),"01")){
				map.put("institutionCode",MultilevelDataAuthorityUtil.getOrganizationCode(LoginUserUtil.getUserName()));
			}
		}
		List<User> temp = mstUserAndRoleImplMapper.getUserByDepartmentCodeOrInstitu(map);
		MstUserAndRoleOutputParameter out = new MstUserAndRoleOutputParameter();
		out.setUserList(temp);
		return out;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@DataSource(DataSourceType.OTHER)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public MstUserAndRoleOutputParameter getAllUserInfo() {
		Map<String, Object> map = new HashMap<>();
		return getUserByDepartmentCodeOrInstitu(map);
	}

}
