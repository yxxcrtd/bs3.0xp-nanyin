package com.joyintech.tams.disclosure.busicommon.financialstatements.valprofitquery.dto;

import java.math.BigDecimal;

public class DisProfitCalculationDto implements Cloneable {

	/**
	 * 项目名
	 */
	private String projName;
	
	/**
	 * 项目编码
	 */
	private String projCode;
	
	/**
	 * 项目级别
	 */
	private int projLevel;
	
	/**
	 * 科目号
	 */
	private String subjectNo;
	
	/**
	 * 期待方向
	 */
	private String crdrFlg;
	
	/**
	 * 计算公式
	 */
	private String formula;
	
	/**
	 * 是否是报表展示项
	 */
	private String showFlg;
	
	/**
	 * 报表展示序号
	 */
	private int showOrder;
	
	/**
	 * 本期金额
	 */
	private BigDecimal thisTermAmt;
	
	/**
	 * 上期金额
	 */
	private BigDecimal lastTermAmt;

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getProjCode() {
		return projCode;
	}

	public void setProjCode(String projCode) {
		this.projCode = projCode;
	}

	public int getProjLevel() {
		return projLevel;
	}

	public void setProjLevel(int projLevel) {
		this.projLevel = projLevel;
	}

	public String getSubjectNo() {
		return subjectNo;
	}

	public void setSubjectNo(String subjectNo) {
		this.subjectNo = subjectNo;
	}

	public String getCrdrFlg() {
		return crdrFlg;
	}

	public void setCrdrFlg(String crdrFlg) {
		this.crdrFlg = crdrFlg;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public BigDecimal getThisTermAmt() {
		return thisTermAmt == null ? BigDecimal.ZERO : thisTermAmt;
	}

	public void setThisTermAmt(BigDecimal thisTermAmt) {
		this.thisTermAmt = thisTermAmt;
	}

	public BigDecimal getLastTermAmt() {
		return lastTermAmt == null ? BigDecimal.ZERO : lastTermAmt;
	}

	public void setLastTermAmt(BigDecimal lastTermAmt) {
		this.lastTermAmt = lastTermAmt;
	}

	public String getShowFlg() {
		return showFlg;
	}

	public void setShowFlg(String showFlg) {
		this.showFlg = showFlg;
	}

	public int getShowOrder() {
		return showOrder;
	}

	public void setShowOrder(int showOrder) {
		this.showOrder = showOrder;
	}
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}