/*
 * 文件名：ServiceAuthorizationListener.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2019年2月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.base.core.security;

import java.util.*;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.AuthorizationListener;
import com.joyintech.framework.security.AuthorizationService;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.base.core.impl.dao.MstUserAndRoleImplMapper;
import com.joyintech.tams.component.base.core.security.dao.DataAuthorityMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.systemtools.service.TamsDataAuthorityServiceImpl;
/**
 * @author 何阳阳
 * @version 1.0 2019年2月14日
 * @see ServiceAuthorization
 * @since 1.0
 */
@Component
@Primary
public class ServiceAuthorization implements AuthorizationListener, AuthorizationService {

	private static DataAuthorityMapper mapper;

	@Autowired(required = false)
	private MstUserAndRoleImplMapper mstUserAndRoleImplMapper;

	@Component
	class RefreshConfig extends TimerTask {
		@PostConstruct
		public void init() {
			Timer timer = new Timer();
			timer.scheduleAtFixedRate(this, 100, 1000 * 60 * 5);
		}

		@Override
		public void run() {
			DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
			String config = mapper.selectConfig(GlobalConstant.DATA_AUTHORITY);
            TamsDataAuthorityServiceImpl.enabled = Boolean.valueOf(StringUtil.equals(config, DictCodeList.是否_是.getValue()));
			DynamicDataSourceHolder.clearDataSource();
		}
	}

	@Override
	public void onLoginSucess(User user) {
		if (!user.isAdmin()) {
			DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
			// 用户登录成功后将用户所有有权限的部门代码存放入session中
			List<String> searchDeptList = new ArrayList<>();
			// 登录用户的用户角色的数据权限取得，没有的话，才使用默认规则
			List<Map<String, String>> authList = mapper.selectDeptCodeByUsername(user.getUserName());
			if (authList != null && authList.size() > 0) {
				// 对指定的机构部门数据有查询权限
				for (Map<String, String> auth : authList) {
					String deptCode = auth.get("DEPTCODE");
					if (StringUtil.equals(deptCode, "99") || StringUtil.isEmpty(deptCode)) {
						searchDeptList.add(auth.get("INSTITUTIONCODE"));
					} else {
						searchDeptList.add(deptCode);
					}
				}
			} else {
				// 采取默认权限：上级机构可以查询下级机构的数据，上级部门可以查询下级部门的数据。
				DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
				searchDeptList = mapper.findAllSubDeptInstitution(user.getDeptCode());
				DynamicDataSourceHolder.clearDataSource();
			}
			SecurityUtil.addSession(TamsDataAuthorityServiceImpl.SESSION_DEPT_CODE, searchDeptList);
			DynamicDataSourceHolder.clearDataSource();
		}
	}

	@Override
	public void onLogout(User user) {
	}

	@Override
	public List<String> getPermissions(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getRoles(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@DataSource(DataSourceType.OTHER)
	public com.joyintech.framework.security.User getUser(String username) {
		String caseFlag = CacheUtil.getSysParam(GlobalConstant.IGNORE_USERNAME_CASE);
		if (StringUtil.isNotEmpty(caseFlag) && StringUtil.equals(caseFlag, DictCodeList.是否_是.getValue())) {
			return mstUserAndRoleImplMapper.findByUsernameIgnoreCase(username);
		}
		return mstUserAndRoleImplMapper.findByUsername(username);
	}

	@Override
	public com.joyintech.framework.security.User getUserInfoBySSOName(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Autowired(required = false)
	public void setMapper(DataAuthorityMapper mapper) {
		ServiceAuthorization.mapper = mapper;
	}

	@Override
	public String getUserOrgType(String orgCode) {
		// TODO Auto-generated method stub
		return null;
	}
}
