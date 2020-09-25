package com.joyintech.tams.invest.batch.susprdfutureopenplangen.dto;

import java.util.Date;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

/**
 * 2019年9月24日 开放现金流信息
 */
public class SusPrdFutureOpenPlanGenDto extends NeedOperateEntity {

	private String finprodId; // 金融产品代码
	private Date vdate; // 起息日
	private String profitType; // 收益类型
	private Date openMdate; // 到期日
	private String cashId; // 现金流代码
	private String openType; // 开放类型
	private Date openVdate; // 开放计划开始日
	private String calendarId; // 交易日历
	private String purSpeed; // 申购确认速度
	private String isCycle; // 是否周期型
	private String closeDays; // 封闭期限
	private String dealMode; // 处理模式
	private String openDaysType; // 开放天数类型
	private String openDays; // 开放天数
	private String openMode; // 开放确认模式
	private String openCycle; // 开放确认周期
	private String openCycleUnit; // 开放确认周期单位
	private String dayType; // 开放确认日期类型
	private String dayValue; // 开放确认日期值
	private String calcMethod; // 推算方法
	private Date firstOpenDate; // 首次开放日
	private String tradedayRule; // 营业日准则
	private String hdayOpenFlag; // 节假日是否开放
	private String calcRule; // 推算规则
	private String cpriceRule; // 净值日取值规则
	private String createUser; // 创建人
	private String createDept; // 创建部门
	private Date createTime; // 创建时间
	private String updateUser; // 更新人
	private Date updateTime; // 更新时间
	/**
     * 申购确认天数
     */
    private Integer purConfDays;
    
	public String getFinprodId() {
		return finprodId;
	}
	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}
	public Date getVdate() {
		return vdate;
	}
	public void setVdate(Date vdate) {
		this.vdate = vdate;
	}
	public String getProfitType() {
		return profitType;
	}
	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}
	public Date getOpenMdate() {
		return openMdate;
	}
	public void setOpenMdate(Date openMdate) {
		this.openMdate = openMdate;
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
	public String getCalendarId() {
		return calendarId;
	}
	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}
	public String getPurSpeed() {
		return purSpeed;
	}
	public void setPurSpeed(String purSpeed) {
		this.purSpeed = purSpeed;
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
	public String getDealMode() {
		return dealMode;
	}
	public void setDealMode(String dealMode) {
		this.dealMode = dealMode;
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
	public String getOpenMode() {
		return openMode;
	}
	public void setOpenMode(String openMode) {
		this.openMode = openMode;
	}
	public String getOpenCycle() {
		return openCycle;
	}
	public void setOpenCycle(String openCycle) {
		this.openCycle = openCycle;
	}
	public String getOpenCycleUnit() {
		return openCycleUnit;
	}
	public void setOpenCycleUnit(String openCycleUnit) {
		this.openCycleUnit = openCycleUnit;
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
	public String getCalcMethod() {
		return calcMethod;
	}
	public void setCalcMethod(String calcMethod) {
		this.calcMethod = calcMethod;
	}
	public Date getFirstOpenDate() {
		return firstOpenDate;
	}
	public void setFirstOpenDate(Date firstOpenDate) {
		this.firstOpenDate = firstOpenDate;
	}
	public String getTradedayRule() {
		return tradedayRule;
	}
	public void setTradedayRule(String tradedayRule) {
		this.tradedayRule = tradedayRule;
	}
	public String getHdayOpenFlag() {
		return hdayOpenFlag;
	}
	public void setHdayOpenFlag(String hdayOpenFlag) {
		this.hdayOpenFlag = hdayOpenFlag;
	}
	public String getCalcRule() {
		return calcRule;
	}
	public void setCalcRule(String calcRule) {
		this.calcRule = calcRule;
	}
	public String getCpriceRule() {
		return cpriceRule;
	}
	public void setCpriceRule(String cpriceRule) {
		this.cpriceRule = cpriceRule;
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
	public Integer getPurConfDays() {
		return purConfDays;
	}
	public void setPurConfDays(Integer purConfDays) {
		this.purConfDays = purConfDays;
	}
	
}
