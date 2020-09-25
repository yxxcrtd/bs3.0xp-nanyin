package com.joyintech.tams.component.business.evaluation.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 收益目标列表明细
 */
public class RevenueTargetListDetails {

	private Date date; // 日期
	private BigDecimal earningsTarget; // 收益目标
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public BigDecimal getEarningsTarget() {
		return earningsTarget;
	}
	public void setEarningsTarget(BigDecimal earningsTarget) {
		this.earningsTarget = earningsTarget;
	}
}
