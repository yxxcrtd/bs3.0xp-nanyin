/*
 * 文件名：InterfStatus.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年1月20日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */
 
package com.joyintech.tams.invest.interf.dto;

import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.Date;

/**
 * 
 * 〈接口数据状态〉
 * 〈实体类〉
 * @author 王波
 * @version 1.0 2020年1月20日
 * @see InterfStatus
 * @since 1.0
 */
public class InterfStatus  {

	/**
	 * 接口ID
	 */
	private String interfId;
	
	
	/**
	 * 接口名称
	 */
	private String interfName;
	
	/**
	 * 接口类型
	 */
	private String interfType;
	
	/**
	 * 业务编号
	 */
	private String busiId;
	
	/**
	 * 状态
	 */
	private String interfSts;
	
	/**
	 * 接口流水号
	 */
	private String interfTermtrc;
	
	/**
	 * 报文
	 */
	private String msgBody;
	
	/**
	 * 发送标识 
	 */
	private String sendFlg;
	
	/**
	 * 发送次数
	 */
	private int sendCount;
	
	/**
	 * 创建人
	 */
	private String createUser;
	
	/**
	 * 创建部门
	 */
	private String createDept;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 更新人
	 */
	private String updateUser;
	
	/**
	 * 更新时间
	 */
	private Date updateTime;

	public String getInterfId() {
		return interfId;
	}

	public void setInterfId(String interfId) {
		this.interfId = interfId;
	}

	public String getInterfName() {
		return interfName;
	}

	public void setInterfName(String interfName) {
		this.interfName = interfName;
	}

	public String getInterfType() {
		return interfType;
	}

	public void setInterfType(String interfType) {
		this.interfType = interfType;
	}

	public String getBusiId() {
		return busiId;
	}

	public void setBusiId(String busiId) {
		this.busiId = busiId;
	}

	public String getInterfSts() {
		return interfSts;
	}

	public void setInterfSts(String interfSts) {
		this.interfSts = interfSts;
	}

	public String getInterfTermtrc() {
		return interfTermtrc;
	}

	public void setInterfTermtrc(String interfTermtrc) {
		this.interfTermtrc = interfTermtrc;
	}

	public String getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}

	public String getSendFlg() {
		return sendFlg;
	}

	public void setSendFlg(String sendFlg) {
		this.sendFlg = sendFlg;
	}

	public int getSendCount() {
		return sendCount;
	}

	public void setSendCount(int sendCount) {
		this.sendCount = sendCount;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateDept() {
		return createDept;
	}

	public void setCreateDept(String createDept) {
		this.createDept = createDept;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


}
