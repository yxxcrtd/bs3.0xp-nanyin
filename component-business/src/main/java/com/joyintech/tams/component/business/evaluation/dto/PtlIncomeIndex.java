package com.joyintech.tams.component.business.evaluation.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author yangxiaofeng
 * 2019年10月8日
 * 收益指标目标实体类
 *
 */
public class PtlIncomeIndex {

	private String indexId; // 指标代码
	private String incomeType; // 收益目标类型
	private BigDecimal incomeTarget; // 收益目标
	private BigDecimal maxIncomeTarget; // 最高收益目标
	private BigDecimal minIncomeTarget; // 最低收益目标
	private String createUser; // 创建人
	private String createDept; // 创建部门
	private Date createTime; // 创建时间
	private String updateUser; // 更新人
	private Date updateTime; // 更新时间
	
	public String getIndexId() {
		return indexId;
	}
	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}
	public String getIncomeType() {
		return incomeType;
	}
	public void setIncomeType(String incomeType) {
		this.incomeType = incomeType;
	}
	public BigDecimal getIncomeTarget() {
		return incomeTarget;
	}
	public void setIncomeTarget(BigDecimal incomeTarget) {
		this.incomeTarget = incomeTarget;
	}
	public BigDecimal getMaxIncomeTarget() {
		return maxIncomeTarget;
	}
	public void setMaxIncomeTarget(BigDecimal maxIncomeTarget) {
		this.maxIncomeTarget = maxIncomeTarget;
	}
	public BigDecimal getMinIncomeTarget() {
		return minIncomeTarget;
	}
	public void setMinIncomeTarget(BigDecimal minIncomeTarget) {
		this.minIncomeTarget = minIncomeTarget;
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
