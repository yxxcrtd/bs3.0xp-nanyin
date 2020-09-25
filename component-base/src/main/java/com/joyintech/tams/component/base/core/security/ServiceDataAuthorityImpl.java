///*
// * 文件名：DataAuthorityServiceImpl.java
// * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
// * 描述：理财资管管理系统V2.0
// * 修改人：何阳阳
// * 修改时间：2019年1月30日
// * 修改内容：新建
// * 系统名称：理财资管管理系统V2.0
// */
//
//package com.joyintech.tams.component.base.core.security;
//
//import java.util.List;
//
//import org.springframework.beans.factory.config.ConfigurableBeanFactory;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Service;
//
//import com.joyintech.framework.security.AbstractDataAuthorityService;
//import com.joyintech.framework.security.SecurityUtil;
//import com.joyintech.framework.security.data.GridColumnAuthInfo;
//
///**
// * @author 何阳阳
// * @version 1.0 2019年1月30日
// * @see ServiceDataAuthorityImpl
// * @since 1.0
// */
//@Service
//@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
//public class ServiceDataAuthorityImpl extends AbstractDataAuthorityService {
//
//	public static final String SESSION_DEPT_CODE = "deptCode";
//
//	public static Boolean enabled = null;
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public boolean enabled() {
//		if (null == enabled) {
//			return super.enabled();
//		}
//		return enabled.booleanValue();
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public List<String> getOrgCodeList() {
//		return null;
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<String> getDeptCodeList() {
//		if (SecurityUtil.hasAuthenticated()) {
//			return (List<String>) SecurityUtil.getFromSession(SESSION_DEPT_CODE);
//		}
//		return null;
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public List<String> ignoreMapper() {
//		List<String> ignoreList = super.defaultIgnore();
//		// 除了资金账户管理和证券账户管理，其他的都不加数据权限
//		ignoreList.add("com.joyintech.common.**");
//		ignoreList.add("com.joyintech.tams.component.base.**");
//		ignoreList.add("com.joyintech.component.business.**");
//		ignoreList.add("com.joyintech.service.interf.**");
//		ignoreList.add("com.joyintech.service.trade.**");
//		ignoreList.add("com.joyintech.service.period.**");
//		ignoreList.add("com.joyintech.service.asset.**");
//		ignoreList.add("com.joyintech.service.common.**");
//		ignoreList.add("com.joyintech.service.document.**");
//		ignoreList.add("com.joyintech.service.entrust.**");
//		ignoreList.add("com.joyintech.service.portfolio.**");
//		ignoreList.add("com.joyintech.service.product.**");
//		ignoreList.add("com.joyintech.service.report.**");
//		ignoreList.add("com.joyintech.service.risk.**");
//		ignoreList.add("com.joyintech.service.settle.**");
//		ignoreList.add("com.joyintech.service.basicdata.exchangerateinfo.**");
//		ignoreList.add("com.joyintech.service.basicdata.mstbondquotationinfo.**");
//		ignoreList.add("com.joyintech.service.basicdata.mstcalendartype.**");
//		ignoreList.add("com.joyintech.service.basicdata.mstcoinfundquotationinfo.**");
//		ignoreList.add("com.joyintech.service.basicdata.mstcustomernature.**");
//		ignoreList.add("com.joyintech.service.basicdata.mstfeevarieties.**");
//		ignoreList.add("com.joyintech.service.basicdata.mstindexquotationinfo.**");
//		ignoreList.add("com.joyintech.service.basicdata.mstinterestratequotationinfo.**");
//		ignoreList.add("com.joyintech.service.basicdata.mstmarketcalendarinfo.**");
//		ignoreList.add("com.joyintech.service.basicdata.mstmarketinstitutions.**");
//		ignoreList.add("com.joyintech.service.basicdata.mstnaturalperson.**");
//		ignoreList.add("com.joyintech.service.basicdata.mstnetassetquotationinfo.**");
//		ignoreList.add("com.joyintech.service.basicdata.mstopicsparamante.**");
//		ignoreList.add("com.joyintech.service.basicdata.mstperformancebenchmark.**");
//		ignoreList.add("com.joyintech.service.basicdata.msttradeopponent.**");
//		ignoreList.add("com.joyintech.service.basicdata.yieldcurveinfo.**");
//		return ignoreList;
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public List<GridColumnAuthInfo> getGridColumn() {
//		return null;
//	}
//}
