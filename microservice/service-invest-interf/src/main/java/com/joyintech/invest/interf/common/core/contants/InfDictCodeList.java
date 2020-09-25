/*
 * 文件名：InfDictCodeList.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.invest.interf.common.core.contants;

import com.joyintech.framework.base.BaseDictCodeList;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.util.StringUtil;

/**
 * 接口数据字典明细
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月8日
 * @see InfDictCodeList
 * @since 1.0
 */
public enum InfDictCodeList implements BaseDictCodeList {

	/**
	 * 信息码_提示
	 */
	信息码_提示(InfDictCode.信息码, "P", "提示"),
	/**
	 * 信息码_警告
	 */
	信息码_警告(InfDictCode.信息码, "W", "警告"),
	/**
	 * 信息码_拒绝
	 */
	信息码_拒绝(InfDictCode.信息码, "R", "拒绝"),

	/**
	 * 信息类型_正常
	 */
	信息类型_正常(InfDictCode.信息类型, "N", "正常"),
	/**
	 * 信息类型_错误
	 */
	信息类型_错误(InfDictCode.信息类型, "E", "错误"),
	/**
	 * 发送标识_发送
	 */
	发送标识_发送(InfDictCode.发送标识, "01", "发送"),
	/**
	 * 发送标识_接收
	 */
	发送标识_接收(InfDictCode.发送标识, "02", "接收"),
	/**
	 * 发送标识_其他
	 */
	发送标识_其他(InfDictCode.发送标识, "03", "其他"),
	/**
	 * 发送状态_已发送
	 */
	发送状态_已发送(InfDictCode.发送状态, "01", "已发送"),
	/**
	 * 发送状态_发送中
	 */
	发送状态_发送中(InfDictCode.发送状态, "02", "发送中"),
	/**
	 * 发送状态_发送失败
	 */
	发送状态_发送失败(InfDictCode.发送状态, "03", "发送失败"),
	/**
	 * 参与人角色_本方交易员
	 */
	参与人角色_本方交易员(InfDictCode.参与人角色, "01", "本方交易员"),
	/**
	 * 参与人角色_对手方交易员
	 */
	参与人角色_对手方交易员(InfDictCode.参与人角色, "02", "对手方交易员"),
	/**
	 * 收付方向_收取
	 */
	收付方向_收取(InfDictCode.收付方向, "01", "收取"),
	/**
	 * 收付方向_支付
	 */
	收付方向_支付(InfDictCode.收付方向, "02", "支付"), 
	/**
	 * 标的账户类型_本方账户
	 */
	标的账户类型_本方账户(InfDictCode.标的账户类型, "01", "本方账户"),
	/**
	 * 标的账户类型_对手方账户
	 */
	标的账户类型_对手方账户(InfDictCode.标的账户类型, "02", "对手方账户"),
	
	利率类型_固定利率(InfDictCode.利率类型,"01","固定利率"),
	利率类型_浮动利率(InfDictCode.利率类型,"02","浮动利率"),

	接口名称_债券要素信息(InfDictCode.接口名称,"S001","债券要素信息"),
	接口名称_债券利率信息(InfDictCode.接口名称,"S002","债券利率信息"),
	接口名称_债券现金流计划(InfDictCode.接口名称,"S003","债券现金流计划"),
	接口名称_债券行权计划(InfDictCode.接口名称,"S004","债券行权计划"),
	接口名称_债券交易(InfDictCode.接口名称,"S005","债券交易"),
	接口名称_债券存续期(InfDictCode.接口名称,"S006","债券存续期"),
	接口名称_基金要素信息(InfDictCode.接口名称,"S007","基金要素信息"),
	接口名称_非货币基金交易(InfDictCode.接口名称,"S008","非货币基金交易"),
	接口名称_非货币基金存续期(InfDictCode.接口名称,"S009","非货币基金存续期"),
	接口名称_货币基金交易(InfDictCode.接口名称,"S010","货币基金交易"),
	接口名称_货币基金存续期(InfDictCode.接口名称,"S011","货币基金存续期"),
	接口名称_项目要素信息(InfDictCode.接口名称,"S012","项目要素信息"),
	接口名称_项目利率信息(InfDictCode.接口名称,"S013","项目利率信息"),
	接口名称_项目现金流计划(InfDictCode.接口名称,"S014","项目现金流计划"),
	接口名称_项目类资产交易(InfDictCode.接口名称,"S015","项目类资产交易"),
	接口名称_项目类资产存续期(InfDictCode.接口名称,"S016","项目类资产存续期"),
	接口名称_计划类资产要素信息(InfDictCode.接口名称,"S017","计划类资产要素信息"),
	接口名称_计划类资产利率信息(InfDictCode.接口名称,"S018","计划类资产利率信息"),
	接口名称_计划类资产现金流计划(InfDictCode.接口名称,"S019","计划类资产现金流计划"),
	接口名称_计划类利率型资产交易(InfDictCode.接口名称,"S020","计划类利率型资产交易"),
	接口名称_计划类利率型资产存续期(InfDictCode.接口名称,"S021","计划类利率型资产存续期"),
	接口名称_计划类净值型资产交易(InfDictCode.接口名称,"S022","计划类净值型资产交易"),
	接口名称_计划类净值型资产存续期(InfDictCode.接口名称,"S023","计划类净值型资产存续期"),
	接口名称_计划类货币型资产交易(InfDictCode.接口名称,"S024","计划类货币型资产交易"),
	接口名称_计划类货币型资产存续期(InfDictCode.接口名称,"S025","计划类货币型资产存续期"),
	接口名称_存款类资产要素信息(InfDictCode.接口名称,"S026","存款类资产要素信息"),
	接口名称_存款类资产利率信息(InfDictCode.接口名称,"S027","存款类资产利率信息"),
	接口名称_存款类资产现金流计划(InfDictCode.接口名称,"S028","存款类资产现金流计划"),
	接口名称_存款类资产交易(InfDictCode.接口名称,"S029","存款类资产交易"),
	接口名称_存款类资产存续期(InfDictCode.接口名称,"S030","存款类资产存续期"),
	接口名称_股票要素信息(InfDictCode.接口名称,"S031","股票要素信息"),
	接口名称_股票交易(InfDictCode.接口名称,"S032","股票交易"),
	接口名称_股票存续期(InfDictCode.接口名称,"S033","股票存续期"),
	接口名称_债券质押式回购交易(InfDictCode.接口名称,"S034","债券质押式回购交易"),
	接口名称_债券质押式回购存续期(InfDictCode.接口名称,"S035","债券质押式回购存续期"),
	接口名称_债券买断式回购交易(InfDictCode.接口名称,"S036","债券买断式回购交易"),
	接口名称_债券买断式回购存续期(InfDictCode.接口名称,"S037","债券买断式回购存续期"),
	接口名称_拆借交易(InfDictCode.接口名称,"S038","拆借交易"),
	接口名称_拆借存续期(InfDictCode.接口名称,"S039","拆借存续期"),
	接口名称_债券借贷交易(InfDictCode.接口名称,"S040","债券借贷交易"),
	接口名称_债券借贷存续期(InfDictCode.接口名称,"S041","债券借贷存续期"),
	接口名称_期权交易(InfDictCode.接口名称,"S042","期权交易"),
	接口名称_期权存续期(InfDictCode.接口名称,"S043","期权存续期"),
	接口名称_利率互换交易(InfDictCode.接口名称,"S044","利率互换交易"),
	接口名称_利率互换浮动利率定盘信息(InfDictCode.接口名称,"S045","利率互换浮动利率定盘信息"),
	接口名称_利率互换存续期(InfDictCode.接口名称,"S046","利率互换存续期"),
	接口名称_外汇即期交易(InfDictCode.接口名称,"S047","外汇即期交易"),
	接口名称_外汇远期交易(InfDictCode.接口名称,"S048","外汇远期交易"),
	接口名称_外汇远期存续期(InfDictCode.接口名称,"S049","外汇远期存续期"),
	接口名称_外汇掉期交易(InfDictCode.接口名称,"S050","外汇掉期交易"),
	接口名称_外汇掉期存续期(InfDictCode.接口名称,"S051","外汇掉期存续期"),
	接口名称_费用支付交易(InfDictCode.接口名称,"S052","费用支付交易"),
	接口名称_资金划拨交易(InfDictCode.接口名称,"S053","资金划拨交易"),
	接口名称_交易对手信息(InfDictCode.接口名称,"S054","交易对手信息"),
	接口名称_市场机构信息(InfDictCode.接口名称,"S055","市场机构信息"),
	接口名称_通道信息(InfDictCode.接口名称,"S056","通道信息"),
	接口名称_应收款转让交易(InfDictCode.接口名称,"S057","应收款转让交易"),
	接口名称_资产行情信息(InfDictCode.接口名称,"S058","资产行情信息"),
	接口名称_债券估值信息(InfDictCode.接口名称,"S059","债券估值信息"),
	接口名称_底层资产估值信息(InfDictCode.接口名称,"S060","底层资产估值信息"),
	接口名称_自定义现金流(InfDictCode.接口名称,"S061","自定义现金流"),

	接口名称_其他现金流交易信息(InfDictCode.接口名称,"R001","其他现金流交易信息"),
	接口名称_交易清算状态(InfDictCode.接口名称,"R002","交易清算状态"),
	接口名称_SPPI测试结果(InfDictCode.接口名称,"R003","SPPI测试结果"),
	接口名称_到账确认信息(InfDictCode.接口名称,"R004","到账确认信息"),
	接口名称_组合信息(InfDictCode.接口名称,"R005","组合信息"),
	接口名称_组合资产估值信息(InfDictCode.接口名称,"R008","组合资产估值信息"),
	接口名称_组合估值信息(InfDictCode.接口名称,"R009","组合估值信息"),
	接口名称_组合费用估值信息(InfDictCode.接口名称,"R010","组合费用估值信息"),
	接口名称_组合证券账户信息(InfDictCode.接口名称,"R012","组合证券账户信息"),
	接口名称_组合资金账户信息(InfDictCode.接口名称,"R013","组合资金账户信息"),
	接口名称_组合业绩基准信息(InfDictCode.接口名称,"R014","组合业绩基准信息"),
	;

	private IDictCode dictCode;

	private String value;

	private String codeName;

	private InfDictCodeList(IDictCode dictCode, String value, String codeName) {
		this.dictCode = dictCode;
		this.value = value;
		this.codeName = codeName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IDictCode getDictCode() {
		return dictCode;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getValue() {
		return value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCodeName() {
		return codeName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public static final boolean isType(String type, InfDictCodeList dict) {
		return StringUtil.equals(type, dict.getValue());
	}
}
