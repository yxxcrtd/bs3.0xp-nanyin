package com.joyintech.service.product.messageadjust.maintenance.dto;

import java.util.Date;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 开放规则调整dto
 * @author yxf
 * @version 1.0 2019年12月2日
 */
@PageId("FinOpenPlanMaintenance002")
// @ValidLogic(targetClass = FinOpenPlanMaintenance002Validator.class)
public class FinOpenPlanMaintenance002Dto extends BaseEntity implements ProcessEntity {

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = -8543840737635969481L;

	@PrimaryKey(value=Field.PK_1)
	private String finprodId;
	
	@PrimaryKey(value=Field.PK_2)
	private String pageId;
	
	private String operationType;
	private String prodStyle;
	private String investmentCycle;
	private String dealMode;
	private String calendarId;
	private Date vdate;
	private Date mdate;
	private String profitType;
	private String pFinprodId;
	private Date effDate;
	private Date openNdateStart;
	private Date openNdateEnd;
	private String purSpeed;
	private String redSpeed;
	private String finprodAbbr;
	private String openMode;
	private String openDaysType;
	private Long openDays;
	private Long openCycle;
	private String openCycleUnit;
	private String calcMethod;
	private String tradedayRule;
	private String calcRule;
	private String openType;
	private String cpriceRule;
	private String elementId;
	private String operateType;
	private String cashId;
	private String branch;
	private Date nextOpenDay;
	private String dayValue;
	private Date firstOpenDate;
	private String hdayOpenFlag;
	private String termType;
	
	private BussFinProductOpenPlanDto2 bussFinProductOpenPlanDto;
	private BussFinProductOpenPlanDto2 bussFinProductOpenPlanRedDto;

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}

	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getProdStyle() {
		return prodStyle;
	}

	public void setProdStyle(String prodStyle) {
		this.prodStyle = prodStyle;
	}

	public String getInvestmentCycle() {
		return investmentCycle;
	}

	public void setInvestmentCycle(String investmentCycle) {
		this.investmentCycle = investmentCycle;
	}

	public String getDealMode() {
		return dealMode;
	}

	public void setDealMode(String dealMode) {
		this.dealMode = dealMode;
	}

	public String getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	public Date getVdate() {
		return vdate;
	}

	public void setVdate(Date vdate) {
		this.vdate = vdate;
	}

	public Date getMdate() {
		return mdate;
	}

	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}

	public String getProfitType() {
		return profitType;
	}

	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}

	public String getpFinprodId() {
		return pFinprodId;
	}

	public void setpFinprodId(String pFinprodId) {
		this.pFinprodId = pFinprodId;
	}

	public Date getEffDate() {
		return effDate;
	}

	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}

	public Date getOpenNdateStart() {
		return openNdateStart;
	}

	public void setOpenNdateStart(Date openNdateStart) {
		this.openNdateStart = openNdateStart;
	}

	public Date getOpenNdateEnd() {
		return openNdateEnd;
	}

	public void setOpenNdateEnd(Date openNdateEnd) {
		this.openNdateEnd = openNdateEnd;
	}

	public String getPurSpeed() {
		return purSpeed;
	}

	public void setPurSpeed(String purSpeed) {
		this.purSpeed = purSpeed;
	}

	public String getRedSpeed() {
		return redSpeed;
	}

	public void setRedSpeed(String redSpeed) {
		this.redSpeed = redSpeed;
	}

	public String getFinprodAbbr() {
		return finprodAbbr;
	}

	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}

	public String getOpenMode() {
		return openMode;
	}

	public void setOpenMode(String openMode) {
		this.openMode = openMode;
	}

	public String getOpenCycleUnit() {
		return openCycleUnit;
	}

	public void setOpenCycleUnit(String openCycleUnit) {
		this.openCycleUnit = openCycleUnit;
	}

	public String getCalcMethod() {
		return calcMethod;
	}

	public void setCalcMethod(String calcMethod) {
		this.calcMethod = calcMethod;
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

	public String getOpenType() {
		return openType;
	}

	public void setOpenType(String openType) {
		this.openType = openType;
	}

	public String getCpriceRule() {
		return cpriceRule;
	}

	public void setCpriceRule(String cpriceRule) {
		this.cpriceRule = cpriceRule;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public BussFinProductOpenPlanDto2 getBussFinProductOpenPlanDto() {
		return bussFinProductOpenPlanDto;
	}

	public void setBussFinProductOpenPlanDto(BussFinProductOpenPlanDto2 bussFinProductOpenPlanDto) {
		this.bussFinProductOpenPlanDto = bussFinProductOpenPlanDto;
	}

	public String getCashId() {
		return cashId;
	}

	public void setCashId(String cashId) {
		this.cashId = cashId;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Date getNextOpenDay() {
		return nextOpenDay;
	}

	public void setNextOpenDay(Date nextOpenDay) {
		this.nextOpenDay = nextOpenDay;
	}

	public Long getOpenDays() {
		return openDays;
	}

	public void setOpenDays(Long openDays) {
		this.openDays = openDays;
	}

	public Long getOpenCycle() {
		return openCycle;
	}

	public void setOpenCycle(Long openCycle) {
		this.openCycle = openCycle;
	}

	public String getOpenDaysType() {
		return openDaysType;
	}

	public void setOpenDaysType(String openDaysType) {
		this.openDaysType = openDaysType;
	}

	public String getDayValue() {
		return dayValue;
	}

	public void setDayValue(String dayValue) {
		this.dayValue = dayValue;
	}

	public Date getFirstOpenDate() {
		return firstOpenDate;
	}

	public void setFirstOpenDate(Date firstOpenDate) {
		this.firstOpenDate = firstOpenDate;
	}

	public String getHdayOpenFlag() {
		return hdayOpenFlag;
	}

	public void setHdayOpenFlag(String hdayOpenFlag) {
		this.hdayOpenFlag = hdayOpenFlag;
	}

	public BussFinProductOpenPlanDto2 getBussFinProductOpenPlanRedDto() {
		return bussFinProductOpenPlanRedDto;
	}

	public void setBussFinProductOpenPlanRedDto(BussFinProductOpenPlanDto2 bussFinProductOpenPlanRedDto) {
		this.bussFinProductOpenPlanRedDto = bussFinProductOpenPlanRedDto;
	}
}
