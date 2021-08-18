package com.joyintech.tams.invest.risk.creditrisklimitmanagement.linemonitordaily.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

public class LineMonitorDailyDto extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//查询日
	private Date cdate;
	
	
	private String dailyType;

	//主体代码
	private String insitituteCode;
	
	//主体名称
	private String customerName;
	
	//主体类别
	private String customerType;
	
	//额度分项
	private String creditTypeName;
	
	//期限
	private String termStr;
	
	//失效日
	private Date vdate;
	
	//核定额度
	private BigDecimal creditAmt;
	
	//已用额度
	private BigDecimal hapCreditAmt;
	
	//剩余额度
	private BigDecimal remainderAmt;
	
	//使用比例
	private BigDecimal ratio = BigDecimal.ZERO;
	
	
	public LineMonitorDailyDto() {
		super();
	}


	public LineMonitorDailyDto(Date cdate, String dailyType) {
		super();
		this.cdate = cdate;
		this.dailyType = dailyType;
	}


	public Date getCdate() {
		return cdate;
	}


	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}


	public String getDailyType() {
		return dailyType;
	}


	public void setDailyType(String dailyType) {
		this.dailyType = dailyType;
	}


	public String getInsitituteCode() {
		return insitituteCode;
	}


	public void setInsitituteCode(String insitituteCode) {
		this.insitituteCode = insitituteCode;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getCustomerType() {
		return customerType;
	}


	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}


	public String getCreditTypeName() {
		return creditTypeName;
	}


	public void setCreditTypeName(String creditTypeName) {
		this.creditTypeName = creditTypeName;
	}


	public String getTermStr() {
		return termStr;
	}


	public void setTermStr(String termStr) {
		this.termStr = termStr;
	}


	public Date getVdate() {
		return vdate;
	}


	public void setVdate(Date vdate) {
		this.vdate = vdate;
	}


	public BigDecimal getCreditAmt() {
		return creditAmt;
	}


	public void setCreditAmt(BigDecimal creditAmt) {
		this.creditAmt = creditAmt;
	}


	public BigDecimal getHapCreditAmt() {
		return hapCreditAmt;
	}


	public void setHapCreditAmt(BigDecimal hapCreditAmt) {
		this.hapCreditAmt = hapCreditAmt;
	}


	public BigDecimal getRemainderAmt() {
		return remainderAmt;
	}


	public void setRemainderAmt(BigDecimal remainderAmt) {
		this.remainderAmt = remainderAmt;
	}


	public BigDecimal getRatio() {
		return ratio;
	}


	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
	

}
