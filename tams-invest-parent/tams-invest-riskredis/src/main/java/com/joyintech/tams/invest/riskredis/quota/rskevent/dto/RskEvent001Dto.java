package com.joyintech.tams.invest.riskredis.quota.rskevent.dto;

import java.util.Date;

import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.tams.common.base.entity.RskEvent;

/**
 * 风险事件列表DTO
 * @author wangzhen
 *
 */
public class RskEvent001Dto extends RskEvent implements ITableRow {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1504491602917193262L;

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

}
