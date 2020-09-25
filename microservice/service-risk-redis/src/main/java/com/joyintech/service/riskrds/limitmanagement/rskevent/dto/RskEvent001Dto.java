package com.joyintech.service.riskrds.limitmanagement.rskevent.dto;

import java.util.Date;

import com.joyintech.framework.export.paginator.domain.ITableRow;

/**
 * 风险事件列表DTO
 * 
 * @author wangzhen
 *
 */
public class RskEvent001Dto implements ITableRow {

	/**
	 * 事件代码
	 */
	private String eventCode;

	/**
	 * 事件名称
	 */
	private String eventName;

	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 更新时间
	 */
	private Date updateTime;


	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getEventCode() {
		return eventCode;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String getCreateUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCreateUser(String createUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getCreateDept() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCreateDept(String createDept) {
		// TODO Auto-generated method stub

	}

}
