package com.joyintech.tams.poc.creditrisk.riskmanagement.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.NotEmpty;

/**
 * 信用风险管理Dto
 * @author QuJianZhong
 *
 */
public class CreditRiskManagement001Dto extends BaseEntity {
	
	private static final long serialVersionUID = -7680036949889436151L;
	
	// 组合代码
	@NotEmpty(fieldName = "组合代码")
	private String portfolioId;

	// 查询日期
	@NotEmpty(fieldName = "查询日期")
	private Date queryDate;
	
	// 项目名称
	private String projName;
	
	// 合计值
	private BigDecimal sumAmt;
	
	// 未逾期未减值
	private BigDecimal notOverdueAmt;
	
	// 逾期1年至2年
	private BigDecimal overdueAmt12;
	
	// 逾期2年至3年
	private BigDecimal overdueAmt23;
	
	// 逾期3年以上
	private BigDecimal overdueAmt3x;

	public Date getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(Date queryDate) {
		this.queryDate = queryDate;
	}

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public BigDecimal getSumAmt() {
		return sumAmt;
	}

	public void setSumAmt(BigDecimal sumAmt) {
		this.sumAmt = sumAmt;
	}

	public BigDecimal getNotOverdueAmt() {
		return notOverdueAmt;
	}

	public void setNotOverdueAmt(BigDecimal notOverdueAmt) {
		this.notOverdueAmt = notOverdueAmt;
	}

	public BigDecimal getOverdueAmt12() {
		return overdueAmt12;
	}

	public void setOverdueAmt12(BigDecimal overdueAmt12) {
		this.overdueAmt12 = overdueAmt12;
	}

	public BigDecimal getOverdueAmt23() {
		return overdueAmt23;
	}

	public void setOverdueAmt23(BigDecimal overdueAmt23) {
		this.overdueAmt23 = overdueAmt23;
	}

	public BigDecimal getOverdueAmt3x() {
		return overdueAmt3x;
	}

	public void setOverdueAmt3x(BigDecimal overdueAmt3x) {
		this.overdueAmt3x = overdueAmt3x;
	}

}
