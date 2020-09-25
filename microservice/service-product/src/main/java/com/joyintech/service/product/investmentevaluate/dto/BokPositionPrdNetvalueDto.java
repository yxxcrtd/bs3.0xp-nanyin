package com.joyintech.service.product.investmentevaluate.dto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class BokPositionPrdNetvalueDto extends BaseEntity {
    private String portfolioId;//组合代码
	private String portfolioName;//组合名称
	private String finprodId; // 金融产品代码
	private BigDecimal branch; // 分支序号
	private Date cdate; // 日期
	private BigDecimal tdyPrinAmt; // 当前本金
	private BigDecimal tdyShareAmt; // 当前份额
	private BigDecimal tdyUnitValue; // 当日单位净值
	private BigDecimal tdyUnitValueSum; // 当日累计净值
	private BigDecimal tdyYield; // 当日收益率
	private BigDecimal tdyUnpaidIntincexp; // 当日未分配利润
	private BigDecimal tdyAssetAmt; // 当日资产净值
	private BigDecimal yield7; // 7日年化收益率
	private String createUser; // 创建人
	private String createDept; // 创建部门
	private Date createTime; // 创建时间
	private String updateUser; // 更新人
	private Date updateTime; // 更新时间
	
	public String getFinprodId() {
		return finprodId;
	}
	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}
	public BigDecimal getBranch() {
		return branch;
	}
	public void setBranch(BigDecimal branch) {
		this.branch = branch;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public BigDecimal getTdyPrinAmt() {
		return tdyPrinAmt;
	}
	public void setTdyPrinAmt(BigDecimal tdyPrinAmt) {
		this.tdyPrinAmt = tdyPrinAmt;
	}
	public BigDecimal getTdyShareAmt() {
		return tdyShareAmt;
	}
	public void setTdyShareAmt(BigDecimal tdyShareAmt) {
		this.tdyShareAmt = tdyShareAmt;
	}
	public BigDecimal getTdyUnitValue() {
		return tdyUnitValue;
	}
	public void setTdyUnitValue(BigDecimal tdyUnitValue) {
		this.tdyUnitValue = tdyUnitValue;
	}
	public BigDecimal getTdyUnitValueSum() {
		return tdyUnitValueSum;
	}
	public void setTdyUnitValueSum(BigDecimal tdyUnitValueSum) {
		this.tdyUnitValueSum = tdyUnitValueSum;
	}
	public BigDecimal getTdyYield() {
		return tdyYield;
	}
	public void setTdyYield(BigDecimal tdyYield) {
		this.tdyYield = tdyYield;
	}
	public BigDecimal getTdyUnpaidIntincexp() {
		return tdyUnpaidIntincexp;
	}
	public void setTdyUnpaidIntincexp(BigDecimal tdyUnpaidIntincexp) {
		this.tdyUnpaidIntincexp = tdyUnpaidIntincexp;
	}
	public BigDecimal getTdyAssetAmt() {
		return tdyAssetAmt;
	}
	public void setTdyAssetAmt(BigDecimal tdyAssetAmt) {
		this.tdyAssetAmt = tdyAssetAmt;
	}
	public BigDecimal getYield7() {
		return yield7;
	}
	public void setYield7(BigDecimal yield7) {
		this.yield7 = yield7;
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

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}
}