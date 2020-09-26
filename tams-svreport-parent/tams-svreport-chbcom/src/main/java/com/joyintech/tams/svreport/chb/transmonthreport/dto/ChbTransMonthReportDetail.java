package com.joyintech.tams.svreport.chb.transmonthreport.dto;

import java.math.BigDecimal;

public class ChbTransMonthReportDetail {
	/**
     * 期数
     */
    private String termid;

    /**
     * 报表类型：PROD-产品，BALANCE-资产负债表，DEPOSIT-结构性存款
     */
    private String reporttype;

    /**
     * 属性代码
     */
    private String propertycode;
    /**
     * 老产品余额
     */
    private BigDecimal oldprodbalance;
    /**
     * 老产品产品价格*期末余额
     */
    private BigDecimal oldprodbalprice;
    /**
     * 老产品加权价格
     */
    private  BigDecimal oldprodjqprice;

    /**
     * 新产品余额
     */
    private BigDecimal newprodbalance;
    /**
     * 新产品产品价格*期末余额
     */
    private BigDecimal newprodbalprice;

    /**
     * 新产品加权价格
     */
    private BigDecimal newprodjqprice;
    /**
     * 老产品期末余额（穿透后）
     */
    private BigDecimal oldbottombalance;
    /**
     * 老产品期末余额*剩余期限
     */
    private BigDecimal oldbalremainterm;
    
    /**
     * 新产品期末余额（穿透后）
     */
    private BigDecimal newbottombalance;
    /**
     * 新产品期末余额*剩余期限
     */
    private BigDecimal newbalremainterm;
    /**
     * 老产品加权剩余期限
     */
    private BigDecimal  oldjqterm;
    /**
     * 新产品加权剩余期限
     */
    private BigDecimal newjqterm;
	public String getTermid() {
		return termid;
	}
	public void setTermid(String termid) {
		this.termid = termid;
	}
	public String getReporttype() {
		return reporttype;
	}
	public void setReporttype(String reporttype) {
		this.reporttype = reporttype;
	}
	public String getPropertycode() {
		return propertycode;
	}
	public void setPropertycode(String propertycode) {
		this.propertycode = propertycode;
	}
	public BigDecimal getOldprodbalance() {
		return oldprodbalance;
	}
	public void setOldprodbalance(BigDecimal oldprodbalance) {
		this.oldprodbalance = oldprodbalance;
	}
    public BigDecimal getNewprodbalance() {
		return newprodbalance;
	}
	public void setNewprodbalance(BigDecimal newprodbalance) {
		this.newprodbalance = newprodbalance;
	}
	public BigDecimal getNewprodjqprice() {
		return newprodjqprice;
	}
	public void setNewprodjqprice(BigDecimal newprodjqprice) {
		this.newprodjqprice = newprodjqprice;
	}
	public BigDecimal getOldbottombalance() {
		return oldbottombalance;
	}
	public void setOldbottombalance(BigDecimal oldbottombalance) {
		this.oldbottombalance = oldbottombalance;
	}
	public BigDecimal getNewbottombalance() {
		return newbottombalance;
	}
	public void setNewbottombalance(BigDecimal newbottombalance) {
		this.newbottombalance = newbottombalance;
	}
	public BigDecimal getOldjqterm() {
		return oldjqterm;
	}
	public void setOldjqterm(BigDecimal oldjqterm) {
		this.oldjqterm = oldjqterm;
	}
	public BigDecimal getNewjqterm() {
		return newjqterm;
	}
	public void setNewjqterm(BigDecimal newjqterm) {
		this.newjqterm = newjqterm;
	}
	public BigDecimal getOldprodbalprice() {
		return oldprodbalprice;
	}
	public void setOldprodbalprice(BigDecimal oldprodbalprice) {
		this.oldprodbalprice = oldprodbalprice;
	}
	
	public BigDecimal getOldprodjqprice() {
		return oldprodjqprice;
	}
	public void setOldprodjqprice(BigDecimal oldprodjqprice) {
		this.oldprodjqprice = oldprodjqprice;
	}
	public BigDecimal getNewprodbalprice() {
		return newprodbalprice;
	}
	public void setNewprodbalprice(BigDecimal newprodbalprice) {
		this.newprodbalprice = newprodbalprice;
	}
	public BigDecimal getOldbalremainterm() {
		return oldbalremainterm;
	}
	public void setOldbalremainterm(BigDecimal oldbalremainterm) {
		this.oldbalremainterm = oldbalremainterm;
	}
	public BigDecimal getNewbalremainterm() {
		return newbalremainterm;
	}
	public void setNewbalremainterm(BigDecimal newbalremainterm) {
		this.newbalremainterm = newbalremainterm;
	}


}
