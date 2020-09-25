/*
 * 文件名：InterfInfoList.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.core.constant;

import com.joyintech.framework.util.StringUtil;

/**
 * 接口服务代码服务场景接口ID等信息
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月23日
 * @see EsbServiceInfoList
 * @since 1.0
 */
public enum EsbServiceInfoList implements EsbServiceInfo {
	/**
	 * 接口448001-零售
	 */
	INF_448001_01(InterfConstant.SERVER_PROD_INFO, "25", "448001", "01"),
	/**
	 * 接口150006-统一TA
	 */
	INF_150006_00(InterfConstant.SERVER_PROD_INFO, "44", "150006", "00"),
	/**
	 * 接口449181-零售
	 */
	INF_449181_01(InterfConstant.SERVER_PROD_SEARCH, "42", "449181", "01"),
	/**
	 * 接口449188-零售
	 */
	INF_449188_01(InterfConstant.SERVER_PROD_INFO1, "60", "RIFMS449188", "01"),
	/**
	 * 接口449191-零售
	 */
	INF_449191_01(InterfConstant.SERVER_PROD_SEARCH, "13", "449191", "01"),
	/**
	 * 接口449194-零售
	 */
	INF_449194_01(InterfConstant.SERVER_PROD_SEARCH, "28", "449194", "01"),
	/**
	 * 接口150005-统一TA
	 */
	INF_150005_00(InterfConstant.SERVER_PROD_INFO, "39", "150005", "00"),
	/**
	 * 接口449183-零售
	 */
	INF_449183_01(InterfConstant.SERVER_TRADE_SEARCH, "20", "RIFMS449183", "01"),
	/**
	 * 接口449283-零售
	 */
	INF_449283_01(InterfConstant.SERVER_TRADE_SEARCH, "21", "RIFMS449283", "01"),
	/**
	 * 接口448108-零售
	 */
	INF_448108_01(InterfConstant.SERVER_TRADE_SEARCH, "50", "RIFMS448108", "01"),
	/**
	 * 接口449189-零售
	 */
	INF_449189_01(InterfConstant.SERVER_TRADE_SEARCH, "62", "RIFMS449189", "01"),
	/**
	 * 接口449192-零售
	 */
	INF_449192_01(InterfConstant.SERVER_PROD_SEARCH, "14", "449192", "01"),
	/**
	 * 接口449195-零售
	 */
	INF_449195_01(InterfConstant.SERVER_PROD_SEARCH, "29", "449195", "01"),
	/**
	 * 接口448103-零售
	 */
	INF_448103_01(InterfConstant.SERVER_PROD_INFO, "27", "RIFMS448103", "01"),
	/**
	 * 接口OPDL03
	 */
	INF_OPDL03("07001000002", "70", "OPDL03"),
	/**
	 * 接口G1003
	 */
	INF_G1003("01001000001", "30", "G1003"),
	/**
	 * 接口G1010
	 */
	INF_G1010("01004000001", "24", "G1010"),
	/**
	 * 接口G3023
	 */
	INF_G3023("11003040003", "37", "G3023"),
	/**
	 * 接口SMS003
	 */
	INF_SMS003("11005000002", "03", "SMS003"),
	/**
	 * 接口SMS004
	 */
	INF_SMS004("11005000002", "01", "SMS004"),
	/**
	 * 接口PFMS0001
	 */
	INF_LC0001(InterfConstant.SERVER_PROD_SEARCH, "01", "PFMS0001"),
	/**
	 * 接口PFMS0002
	 */
	INF_LC0002(InterfConstant.SERVER_PROD_SEARCH, "02", "PFMS0002"),
	/**
	 * 接口PFMS0003
	 */
	INF_LC0003("09002000004", "25", "PFMS0003"),
	/**
	 * 接口PFMS0004
	 */
	INF_LC0004(InterfConstant.SERVER_PROD_SEARCH, "08", "PFMS0004"),
	/**
	 * 接口PFMS0005
	 */
	INF_LC0005(InterfConstant.SERVER_PROD_SEARCH, "15", "PFMS0005"),
	/**
	 * 接口PFMS0006
	 */
	INF_LC0006(InterfConstant.SERVER_PROD_INFO, "04", "PFMS0006"),
	/**
	 * 接口PFMS0007
	 */
	INF_LC0007(InterfConstant.SERVER_PROD_SEARCH, "16", "PFMS0007"),
	/**
	 * 接口PFMS0008
	 */
	INF_LC0008(InterfConstant.SERVER_PROD_INFO, "18", "PFMS0008"),
	/**
	 * 接口PFMS0009
	 */
	INF_LC0009("09001000002", "19", "PFMS0009"),
	/**
	 * 接口PFMS0010
	 */
	INF_LC0010(InterfConstant.SERVER_PROD_INFO, "20", "PFMS0010"),
	/**
	 * 接口PFMS0011
	 */
	INF_TCMS001("09002000004", "73", "PFMS0011"),
	/**
	 * 接口PFMS0012
	 */
	INF_TCMS002("09003000007", "68", "PFMS0012"),
	/**
	 * 接口CUSDEP1003
	 */
	INF_CUSDEP1003("01001000001", "38", "CUSDEP1003"),
	/**
	 * 接口CUSDEP1020
	 */
	INF_CUSDEP1020("05001000006", "31", "CUSDEP1020"),
	/**
	 * 接口CUSDEP1207(交易单确认)
	 */
	INF_CUSDEP1207("11002000062", "23", "CUSDEP1207"),
	/**
	 * 接口3005（指令状态查询）
	 */
	INF_3005("11003040003", "61", "3005"),
	/**
	 * 接口LNBR001（拆借交易）
	 */
	INF_LNBR001(InterfConstant.SERVER_PROD_INFO, "74", "LNBR001"),
	/**
	 * 接口REPO001（买断式回购交易）
	 */
	INF_REPO001(InterfConstant.SERVER_PROD_INFO, "70", "REPO001"),
	/**
	 * 接口REPO002（质押式回购交易）
	 */
	INF_REPO002(InterfConstant.SERVER_PROD_INFO, "71", "REPO002"),
	/**
	 * 接口BOND001（债券交易）
	 */
	INF_BOND001("11002000059", "94", "BOND001"),
	/**
	 * 接口MOPT1001（查询交易信息导入结果）
	 */
	INF_MOPT1001("11003040003", "97", "MOPT1001"),
	/**
	 * 接口CMS003（CMS出账信息撤销）
	 */
	INF_CMS003("02002000005", "91", "CMS003"),
	/**
	 * 接口150010-资管费用信息接口
	 */
	INF_150010_00("07001000006", "35", "150010", "00"),
	/**
	 * 接口190011-净值分红信息查询接口
	 */
	INF_190011_00("03003000013", "47", "190011", "00"),
	/**
	 * 接口291001-天天成长当日销售数据统计查询
	 */
	INF_291001_00("11003000058", "51", "291001", "00"),
	/**
	 * 接口291002-净值型产品申购赎回确认数据查询
	 */
	INF_291002_00(InterfConstant.SERVER_PROD_INFO1, "76", "291002", "00");

	/**
	 * 服务代码
	 */
	private String serviceCode;

	/**
	 * 服务场景
	 */
	private String serviceScene;

	/**
	 * 交易码
	 */
	private String tranCode;

	/**
	 * 销售部门
	 */
	private String dept;

	/**
	 * @param seviceCode
	 *            服务代码
	 * @param serviceScene
	 *            服务场景
	 * @param interf
	 *            接口
	 * @param dept
	 *            销售部门
	 */
	EsbServiceInfoList(String seviceCode, String serviceScene, String interf, String dept) {
		this.serviceCode = seviceCode;
		this.serviceScene = serviceScene;
		this.tranCode = interf;
		this.dept = dept;
	}

	/**
	 * @param seviceCode
	 *            服务代码
	 * @param serviceScene
	 *            服务场景
	 * @param interf
	 *            接口
	 */
	EsbServiceInfoList(String seviceCode, String serviceScene, String interf) {
		this.serviceCode = seviceCode;
		this.serviceScene = serviceScene;
		this.tranCode = interf;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getServiceCode() {
		return serviceCode;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getServiceScene() {
		return serviceScene;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTransId() {
		return serviceCode + "-" + serviceScene;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getInterfId() {
		return StringUtil.isEmpty(dept) ? tranCode : tranCode + "-" + dept;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTranCode() {
		return tranCode;
	}
}
