package com.jixu.msg.dto;

import java.util.Date;
import java.util.List;

import com.jixu.common.valid.IsNumberJixu;
import com.jixu.common.valid.NotEmptyJixu;
import com.jixu.framework.msg.BaseMsgDto;

/**
 * 现金流消息Dto
 * @author Wang Zhongyuan
 */
public class CashFlowDto extends BaseMsgDto{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//现金流ID
	private String id;
	
	//利率类型(固定利率/浮动利率)
	@IsNumberJixu(keymsg="利率类型")
	private String cfType;
	
	//现金流名称
	@NotEmptyJixu(keymsg="名称")
	private String name;
	
	//类型(应收/应付)
	@IsNumberJixu(keymsg="类型")
	private String type;
	
	//起息日期
	private Date accurStartDate;
	
	//是否生效
	private String status;
	
	//利率
	private String contractRate;
	
	//利率基础
	private String dayCount;
	
	//付款频率
	private String frequency;
	
	//付款月份
	private String paymentMonth;
	
	//付款日
	private String paymentDay;
	
	//自动结转本金
	private String autoFlag;
	
	//自动结转利息
	private String autoInterestFlag;
	
	//付息计划
	private List<CashFlowPaymentsDto> payments;
	
	//浮动利率
	private CashFlowFloatDto floats;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCfType() {
		return cfType;
	}
	public void setCfType(String cfType) {
		this.cfType = cfType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getAccurStartDate() {
		return accurStartDate;
	}
	public void setAccurStartDate(Date accurStartDate) {
		this.accurStartDate = accurStartDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getContractRate() {
		return contractRate;
	}
	public void setContractRate(String contractRate) {
		this.contractRate = contractRate;
	}
	public String getDayCount() {
		return dayCount;
	}
	public void setDayCount(String dayCount) {
		this.dayCount = dayCount;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getPaymentMonth() {
		return paymentMonth;
	}
	public void setPaymentMonth(String paymentMonth) {
		this.paymentMonth = paymentMonth;
	}
	public String getPaymentDay() {
		return paymentDay;
	}
	public void setPaymentDay(String paymentDay) {
		this.paymentDay = paymentDay;
	}
	public String getAutoFlag() {
		return autoFlag;
	}
	public void setAutoFlag(String autoFlag) {
		this.autoFlag = autoFlag;
	}
	public String getAutoInterestFlag() {
		return autoInterestFlag;
	}
	public void setAutoInterestFlag(String autoInterestFlag) {
		this.autoInterestFlag = autoInterestFlag;
	}
	
	public List<CashFlowPaymentsDto> getPayments() {
		return payments;
	}
	public void setPayments(List<CashFlowPaymentsDto> payments) {
		this.payments = payments;
	}
	public CashFlowFloatDto getFloats() {
		return floats;
	}
	public void setFloats(CashFlowFloatDto floats) {
		this.floats = floats;
	}
}
