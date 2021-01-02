package com.joyintech.tams.invest.batch.prdopenplanupdate.dto;

import java.util.Date;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

/**
 * 批处理-市场日历接入后更新产品开放计划 dto
 */
public class PrdOpenPlanUpdateDto extends NeedOperateEntity{
	
	private String planType;//计划类型：开放计划、付息计划
	private String finprodId; // 金融产品代码
	private String profitType; // 收益类型
	private Date vdate; // 起息日
	private String calendarId; // 交易日历
	
	private String cashId; // 现金流代码
	private String calcRule; // 推算规则
	
	/*******************付息计划字段 start*********************/
    private Long cashNum; // 现金流序号，从1开始
	private Date payVdate; // 付息计划区间开始日-平台日所在的付息开始日
	private Date payMdate; // 付息区间到期日
	private Date payFPMdate; // 付息计划首次结束日-平台日所在的付息结束日
    private String payCycle;// 支付频率
    private Long payCycleVal;// 支付频率值
    private String payCycleUnit;// 付息周期单位
    private String payTdayRule;// 支付日营业日准则
    private String endTdayRule;// 截止日营业日准则
    private Date firstPayDate;// 首次支付日
    
    private Date winupDate;// 清盘日
	/*******************付息计划字段 end*********************/
	
	
	/*******************开放计划字段 start*********************/
	
	private String openType; // 开放类型
	private Date openVdate; // 开放计划区间开始日-平台日所在的开放开始日
	private Date openMdate; // 开放区间到期日
	private Date openDateEnd;// 开放计划首次结束日-平台日所在的开放结束日
	private Date firstOpenDate; // 首次开放日
	private String openMode; // 开放确认模式
	private String calcMethod; // 推算方法
	private String openCycleUnit; // 开放确认周期单位
	private String openCycle; // 开放确认周期
	private String dayType; // 开放确认日期类型
	private String dayValue; // 开放确认日期值
	private String openDaysType; // 开放天数类型
	private String openDays; // 开放天数
	private String cpriceRule; // 净值日取值规则
	private String tradedayRule; // 营业日准则
	
	private String hdayOpenFlag; // 节假日是否开放
	
	private String dealMode; // 处理模式
	private String isCycle; // 是否周期型
	private String closeDays; // 封闭期限
	private String purSpeed; // 申购确认速度
    private Integer purConfDays; //申购确认天数
    
    /*******************开放计划字段 end*********************/
    
	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
	}
	public String getFinprodId() {
		return finprodId;
	}
	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}
	public String getProfitType() {
		return profitType;
	}
	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}
	public Date getVdate() {
		return vdate;
	}
	public void setVdate(Date vdate) {
		this.vdate = vdate;
	}
	public String getCalendarId() {
		return calendarId;
	}
	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}
	public String getCashId() {
		return cashId;
	}
	public void setCashId(String cashId) {
		this.cashId = cashId;
	}
	public String getOpenType() {
		return openType;
	}
	public void setOpenType(String openType) {
		this.openType = openType;
	}
	public Date getOpenVdate() {
		return openVdate;
	}
	public void setOpenVdate(Date openVdate) {
		this.openVdate = openVdate;
	}
	public Date getOpenMdate() {
		return openMdate;
	}
	public void setOpenMdate(Date openMdate) {
		this.openMdate = openMdate;
	}
	public Date getOpenDateEnd() {
		return openDateEnd;
	}
	public void setOpenDateEnd(Date openDateEnd) {
		this.openDateEnd = openDateEnd;
	}
	public Date getFirstOpenDate() {
		return firstOpenDate;
	}
	public void setFirstOpenDate(Date firstOpenDate) {
		this.firstOpenDate = firstOpenDate;
	}
	public String getOpenMode() {
		return openMode;
	}
	public void setOpenMode(String openMode) {
		this.openMode = openMode;
	}
	public String getCalcMethod() {
		return calcMethod;
	}
	public void setCalcMethod(String calcMethod) {
		this.calcMethod = calcMethod;
	}
	public String getOpenCycleUnit() {
		return openCycleUnit;
	}
	public void setOpenCycleUnit(String openCycleUnit) {
		this.openCycleUnit = openCycleUnit;
	}
	public String getOpenCycle() {
		return openCycle;
	}
	public void setOpenCycle(String openCycle) {
		this.openCycle = openCycle;
	}
	public String getDayType() {
		return dayType;
	}
	public void setDayType(String dayType) {
		this.dayType = dayType;
	}
	public String getDayValue() {
		return dayValue;
	}
	public void setDayValue(String dayValue) {
		this.dayValue = dayValue;
	}
	public String getOpenDaysType() {
		return openDaysType;
	}
	public void setOpenDaysType(String openDaysType) {
		this.openDaysType = openDaysType;
	}
	public String getOpenDays() {
		return openDays;
	}
	public void setOpenDays(String openDays) {
		this.openDays = openDays;
	}
	public String getCpriceRule() {
		return cpriceRule;
	}
	public void setCpriceRule(String cpriceRule) {
		this.cpriceRule = cpriceRule;
	}
	public String getTradedayRule() {
		return tradedayRule;
	}
	public void setTradedayRule(String tradedayRule) {
		this.tradedayRule = tradedayRule;
	}
	public String getCalcRule() {
		return calcRule;
	}
	public void setCalcRule(String calcRule) {
		this.calcRule = calcRule;
	}
	public String getHdayOpenFlag() {
		return hdayOpenFlag;
	}
	public void setHdayOpenFlag(String hdayOpenFlag) {
		this.hdayOpenFlag = hdayOpenFlag;
	}
	public String getDealMode() {
		return dealMode;
	}
	public void setDealMode(String dealMode) {
		this.dealMode = dealMode;
	}
	public String getIsCycle() {
		return isCycle;
	}
	public void setIsCycle(String isCycle) {
		this.isCycle = isCycle;
	}
	public String getCloseDays() {
		return closeDays;
	}
	public void setCloseDays(String closeDays) {
		this.closeDays = closeDays;
	}
	public String getPurSpeed() {
		return purSpeed;
	}
	public void setPurSpeed(String purSpeed) {
		this.purSpeed = purSpeed;
	}
	public Integer getPurConfDays() {
		return purConfDays;
	}
	public void setPurConfDays(Integer purConfDays) {
		this.purConfDays = purConfDays;
	}
	public Long getCashNum() {
		return cashNum;
	}
	public void setCashNum(Long cashNum) {
		this.cashNum = cashNum;
	}
	public Date getPayVdate() {
		return payVdate;
	}
	public void setPayVdate(Date payVdate) {
		this.payVdate = payVdate;
	}
	public Date getPayMdate() {
		return payMdate;
	}
	public void setPayMdate(Date payMdate) {
		this.payMdate = payMdate;
	}
	public Date getPayFPMdate() {
		return payFPMdate;
	}
	public void setPayFPMdate(Date payFPMdate) {
		this.payFPMdate = payFPMdate;
	}
	public String getPayCycle() {
		return payCycle;
	}
	public void setPayCycle(String payCycle) {
		this.payCycle = payCycle;
	}
	public Long getPayCycleVal() {
		return payCycleVal;
	}
	public void setPayCycleVal(Long payCycleVal) {
		this.payCycleVal = payCycleVal;
	}
	public String getPayCycleUnit() {
		return payCycleUnit;
	}
	public void setPayCycleUnit(String payCycleUnit) {
		this.payCycleUnit = payCycleUnit;
	}
	public String getPayTdayRule() {
		return payTdayRule;
	}
	public void setPayTdayRule(String payTdayRule) {
		this.payTdayRule = payTdayRule;
	}
	public String getEndTdayRule() {
		return endTdayRule;
	}
	public void setEndTdayRule(String endTdayRule) {
		this.endTdayRule = endTdayRule;
	}
	public Date getFirstPayDate() {
		return firstPayDate;
	}
	public void setFirstPayDate(Date firstPayDate) {
		this.firstPayDate = firstPayDate;
	}
	public Date getWinupDate() {
		return winupDate;
	}
	public void setWinupDate(Date winupDate) {
		this.winupDate = winupDate;
	}
    
}
