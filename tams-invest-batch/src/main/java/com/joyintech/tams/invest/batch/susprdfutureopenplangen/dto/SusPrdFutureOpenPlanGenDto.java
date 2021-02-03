package com.joyintech.tams.invest.batch.susprdfutureopenplangen.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

/**
 * 2019年9月24日 开放现金流信息
 */
public class SusPrdFutureOpenPlanGenDto extends NeedOperateEntity {
	private String planType;//计划类型：开放计划、业绩回补计划、付息计划
	private String finprodId; // 金融产品代码
	private String calendarId; // 交易日历
	private String calcRule; // 推算规则
	private Date vdate; // 起息日
	private Date mdate;/*产品到期日*/
	private String profitType; // 收益类型
	private String cashId; // 现金流代码
	/*******************开放计划字段 start*********************/
	private Date openMdate; // 到期日
	private String openType; // 开放类型
	private Date openVdate; // 开放计划开始日
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
	private String cpriceRule; // 净值日取值规则
	private String createUser; // 创建人
	private String createDept; // 创建部门
	private Date createTime; // 创建时间
	private String updateUser; // 更新人
	private Date updateTime; // 更新时间
	private String payCycleV; // 支付频率
	/**
     * 申购确认天数
     */
    private Integer purConfDays;
    /*******************开放计划字段 end*********************/
    
    /*******************业绩回补计划字段 start*********************/
    private String  anapleroticRule; //回补方式
    private String  anapleroticType; //回补形式
    private String  anapleroticBasic;//计费基数	           
    private Date  effDate;// 生效日
    private String  anapleroticId;//回补id          
    private String  prodStyle;//适用款型   		      		    
    private String  anapleroticFrequency; //回补规则
    private String  cycleTdayRule;//营业日准则
    private Date  firstAnapleroticDate; //首次回补日
    private Date  firstAnapleroticCycleBegDate;//首次回补周期结束日
    private Date  firstAnapleroticCycleEndDate;//首次回补周期结束日
    private BigDecimal  anapleroticRatio; // 回补比例
    private Date  eAccrDate;//回补计提结束日
    
    
    /*******************业绩回补计划字段 end*********************/
    
    /*******************付息计划字段 start*********************/
    private Long cashNum; // 现金流序号，从1开始
	private Date payVdate; // 付息计划区间开始日
	private Date payMdate; // 付息区间到期日
	private Date payFPMdate; // 付息计划首次结束日
    private String payCycle;// 支付频率
    private Long payCycleVal;// 支付频率值
    private String payCycleUnit;// 付息周期单位
    private String payTdayRule;// 支付日营业日准则
    private String endTdayRule;// 截止日营业日准则
    private Date firstPayDate;// 首次支付日    
    private Date winupDate;// 清盘日
	/*******************付息计划字段 end*********************/
    
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
	public String getAnapleroticRule() {
		return anapleroticRule;
	}
	public void setAnapleroticRule(String anapleroticRule) {
		this.anapleroticRule = anapleroticRule;
	}
	public String getAnapleroticType() {
		return anapleroticType;
	}
	public void setAnapleroticType(String anapleroticType) {
		this.anapleroticType = anapleroticType;
	}
	public String getAnapleroticBasic() {
		return anapleroticBasic;
	}
	public void setAnapleroticBasic(String anapleroticBasic) {
		this.anapleroticBasic = anapleroticBasic;
	}
	public Date getEffDate() {
		return effDate;
	}
	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}
	public String getAnapleroticId() {
		return anapleroticId;
	}
	public void setAnapleroticId(String anapleroticId) {
		this.anapleroticId = anapleroticId;
	}
	public String getProdStyle() {
		return prodStyle;
	}
	public void setProdStyle(String prodStyle) {
		this.prodStyle = prodStyle;
	}
	public String getAnapleroticFrequency() {
		return anapleroticFrequency;
	}
	public void setAnapleroticFrequency(String anapleroticFrequency) {
		this.anapleroticFrequency = anapleroticFrequency;
	}
	public String getCycleTdayRule() {
		return cycleTdayRule;
	}
	public void setCycleTdayRule(String cycleTdayRule) {
		this.cycleTdayRule = cycleTdayRule;
	}
	public Date getFirstAnapleroticDate() {
		return firstAnapleroticDate;
	}
	public void setFirstAnapleroticDate(Date firstAnapleroticDate) {
		this.firstAnapleroticDate = firstAnapleroticDate;
	}
	public Date getFirstAnapleroticCycleBegDate() {
		return firstAnapleroticCycleBegDate;
	}
	public void setFirstAnapleroticCycleBegDate(Date firstAnapleroticCycleBegDate) {
		this.firstAnapleroticCycleBegDate = firstAnapleroticCycleBegDate;
	}
	public Date getFirstAnapleroticCycleEndDate() {
		return firstAnapleroticCycleEndDate;
	}
	public void setFirstAnapleroticCycleEndDate(Date firstAnapleroticCycleEndDate) {
		this.firstAnapleroticCycleEndDate = firstAnapleroticCycleEndDate;
	}
	public BigDecimal getAnapleroticRatio() {
		return anapleroticRatio;
	}
	public void setAnapleroticRatio(BigDecimal anapleroticRatio) {
		this.anapleroticRatio = anapleroticRatio;
	}
	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
	}
	public Date geteAccrDate() {
		return eAccrDate;
	}
	public void seteAccrDate(Date eAccrDate) {
		this.eAccrDate = eAccrDate;
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
	public Date getMdate() {
		return mdate;
	}
	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}
	public String getPayCycleV() {
		return payCycleV;
	}
	public void setPayCycleV(String payCycleV) {
		this.payCycleV = payCycleV;
	}
	
}
