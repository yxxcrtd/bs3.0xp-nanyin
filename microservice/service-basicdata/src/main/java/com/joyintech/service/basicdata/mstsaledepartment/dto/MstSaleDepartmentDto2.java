/*
 * 文件名：MstCaptialAccountDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年8月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.mstsaledepartment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * 交易系统查询营业部接口返回实体
 */
public class MstSaleDepartmentDto2 {

	/* 所属券商 */private String brockerId;
	/* 所属券商名称 */private String brockerName;
	/* IP地址 */private String IP;
	/* 端口 */private String MAC;
	/* 备用IP地址 */private String ipBack;
	/* 备用端口 */private String macBack;
	/* 申报单位 */private String orderUnit;
	/* 是否净价回报 */private String netPriceReturn;
	/* 特殊处理业务 */private String specialBuss;
	/* 柜台接口类型 */private String agentType;
	private String serverInfo;

	public String getBrockerId() {
		return brockerId;
	}

	public void setBrockerId(String brockerId) {
		this.brockerId = brockerId;
	}

	public String getBrockerName() {
		return brockerName;
	}

	public void setBrockerName(String brockerName) {
		this.brockerName = brockerName;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public String getMAC() {
		return MAC;
	}

	public void setMAC(String mAC) {
		MAC = mAC;
	}

	public String getIpBack() {
		return ipBack;
	}

	public void setIpBack(String ipBack) {
		this.ipBack = ipBack;
	}

	public String getMacBack() {
		return macBack;
	}

	public void setMacBack(String macBack) {
		this.macBack = macBack;
	}

	public String getOrderUnit() {
		return orderUnit;
	}

	public void setOrderUnit(String orderUnit) {
		this.orderUnit = orderUnit;
	}

	public String getNetPriceReturn() {
		return netPriceReturn;
	}

	public void setNetPriceReturn(String netPriceReturn) {
		this.netPriceReturn = netPriceReturn;
	}

	public String getSpecialBuss() {
		return specialBuss;
	}

	public void setSpecialBuss(String specialBuss) {
		this.specialBuss = specialBuss;
	}

	public String getAgentType() {
		return agentType;
	}

	public void setAgentType(String agentType) {
		this.agentType = agentType;
	}

	public String getServerInfo() {
		return serverInfo;
	}

	public void setServerInfo(String serverInfo) {
		this.serverInfo = serverInfo;
	}

}
