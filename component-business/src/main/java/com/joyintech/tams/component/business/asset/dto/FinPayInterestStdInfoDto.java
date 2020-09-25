/*
 * 文件名：FinPayInterestStdInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.asset.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.asset.validator.FinPayInterestStdInfoValidator;
import com.joyintech.tams.component.business.project.dto.BussParticipantInfoDto;
import com.joyintech.tams.invest.common.asset.dto.FinCashPlanDto;

/**
 * 付息信息
 * @author zhangnian
 * @version 1.0 2018年9月11日
 * @see FinPayInterestStdInfoDto
 * @since 1.0
 */
@ValidLogic(targetClass = FinPayInterestStdInfoValidator.class,groups = {ValidateGroupEdit.class})
public class FinPayInterestStdInfoDto extends FinCashPayRule{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 操作类型
     */
    private String operateType;
    
    private String pageModel;
    
    /**
     * 是否初始化
     */
    private String initFlag;
    
    /**
     * 是否还本计划生成计划回调付息计划
     */
    private String iscallbackPayByRepay;
    
    /**
     * 是否重新计算利息
     */
    private String isAgainCalInt;
    
    /**
     * 是否计划区间利息计算
     */
    private String isSubAmtCal;
    
    /**
     * entityName
     */
    private String entityName;
    
    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;
    
    /**
     * 存款本金
     */
    private BigDecimal issueAmt;
    
    /**
     * 交易日历
     */
    private String calendarId;
    
    /**
     * 息票品种
     */
    private String  couponSpecies;
    
    /**
     * 付息计划调整标志
     */
    private String adjPlanFlag;
    
    /**
     * 最大交易日
     */
    private Date maxTradeDate;
    
    private String type6;
    
    private String profitType;
    
    /**
     * 计息要素
     */
    @Valid
    private FinInterestFactorDto  finInterestFactorDto;
    
    /**
     * 参与方信息(通道费)
     */
    private List<BussParticipantInfoDto> bussParticipantInfoDtoList;
    
    /**
     * 付息计划
     */
    
    private List<FinCashPlanDto>  invScheduleList;
    /**
     * 还本计划
     */
    private List<FinCashPlanDto> invRepayList;
    
    /**
     * 现金流支付规则
     */
    private FinCashPayRule finCashPayRule;
    
    /**
     * 现金流支付规则List
     */
    private List<FinCashPayRule> finCashPayRuleList;

    /**
     * 现金流计划(前台带入)
     */
    private List<FinCashPlanDto> finCashPlanList;
    
    /**
     * 现金流计划(入库)
     */
    private List<FinCashPlan> finCashPlan2List;

    /**
     * 是否还本不带息场景FLag
     */
    private String isPayIntFlag;


    /**
     * 还本带息
     */
    private String isPayInt;
    /**
     * 获取 payTdayRule
     * @return payTdayRule.
     */
    /*@NotEmpty(fieldName="FinPayInterestStdInfo.payTdayRule",groups = {ValidateGroupEdit.class})*/
    public String getPayTdayRule() {
        return super.getPayTdayRule();
    }

    
    /**
     * 获取 payCycle
     * @return payCycle.
     */
    public String getPayCycle() {
        return super.getPayCycle();
    }
    
    /**
     * 获取 calcRule
     * @return calcRule.
     */
    public String getCalcRule() {
        return super.getCalcRule();
    }


    
    
    /**
     * 获取 finCashPayRule
     * @return finCashPayRule.
     */
    public FinCashPayRule getFinCashPayRule() {
        return finCashPayRule;
    }


    
    /**
     * 设置 finCashPayRule
     * @param finCashPayRule finCashPayRule
     */
    public void setFinCashPayRule(FinCashPayRule finCashPayRule) {
        this.finCashPayRule = finCashPayRule;
    }


    
    /**
     * 获取 finCashPlanList
     * @return finCashPlanList.
     */
    public List<FinCashPlanDto> getFinCashPlanList() {
        return finCashPlanList;
    }


    
    /**
     * 设置 finCashPlanList
     * @param finCashPlanList finCashPlanList
     */
    public void setFinCashPlanList(List<FinCashPlanDto> finCashPlanList) {
        this.finCashPlanList = finCashPlanList;
    }


    
    /**
     * 获取 finCashPayRuleList
     * @return finCashPayRuleList.
     */
    public List<FinCashPayRule> getFinCashPayRuleList() {
        return finCashPayRuleList;
    }


    
    /**
     * 设置 finCashPayRuleList
     * @param finCashPayRuleList finCashPayRuleList
     */
    public void setFinCashPayRuleList(List<FinCashPayRule> finCashPayRuleList) {
        this.finCashPayRuleList = finCashPayRuleList;
    }


    
    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }


    
    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }


    
    /**
     * 获取 pageModel
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }


    
    /**
     * 设置 pageModel
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }


    
    /**
     * 获取 finInterestFactorDto
     * @return finInterestFactorDto.
     */
    public FinInterestFactorDto getFinInterestFactorDto() {
        return finInterestFactorDto;
    }


    
    /**
     * 设置 finInterestFactorDto
     * @param finInterestFactorDto finInterestFactorDto
     */
    public void setFinInterestFactorDto(FinInterestFactorDto finInterestFactorDto) {
        this.finInterestFactorDto = finInterestFactorDto;
    }


    
    /**
     * 获取 couponSpecies
     * @return couponSpecies.
     */
    public String getCouponSpecies() {
        return couponSpecies;
    }


    
    /**
     * 设置 couponSpecies
     * @param couponSpecies couponSpecies
     */
    public void setCouponSpecies(String couponSpecies) {
        this.couponSpecies = couponSpecies;
    }


    
    /**
     * 获取 invRepayList
     * @return invRepayList.
     */
    public List<FinCashPlanDto> getInvRepayList() {
        return invRepayList;
    }


    
    /**
     * 设置 invRepayList
     * @param invRepayList invRepayList
     */
    public void setInvRepayList(List<FinCashPlanDto> invRepayList) {
        this.invRepayList = invRepayList;
    }


    
    /**
     * 获取 issueAmt
     * @return issueAmt.
     */
    public BigDecimal getIssueAmt() {
        return issueAmt;
    }


    
    /**
     * 设置 issueAmt
     * @param issueAmt issueAmt
     */
    public void setIssueAmt(BigDecimal issueAmt) {
        this.issueAmt = issueAmt;
    }


    
    /**
     * 获取 vdate
     * @return vdate.
     */
    public Date getVdate() {
        return vdate;
    }


    
    /**
     * 设置 vdate
     * @param vdate vdate
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }


    
    /**
     * 获取 mdate
     * @return mdate.
     */
    public Date getMdate() {
        return mdate;
    }


    
    /**
     * 设置 mdate
     * @param mdate mdate
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }


    
    /**
     * 获取 calendarId
     * @return calendarId.
     */
    public String getCalendarId() {
        return calendarId;
    }


    
    /**
     * 设置 calendarId
     * @param calendarId calendarId
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }


    
    /**
     * 获取 initFlag
     * @return initFlag.
     */
    public String getInitFlag() {
        return initFlag;
    }


    
    /**
     * 设置 initFlag
     * @param initFlag initFlag
     */
    public void setInitFlag(String initFlag) {
        this.initFlag = initFlag;
    }


    
    /**
     * 获取 entityName
     * @return entityName.
     */
    public String getEntityName() {
        return entityName;
    }


    
    /**
     * 设置 entityName
     * @param entityName entityName
     */
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    
    


    
    
    /**
     * 获取 isAgainCalInt
     * @return isAgainCalInt.
     */
    public String getIsAgainCalInt() {
        return isAgainCalInt;
    }


    
    /**
     * 设置 isAgainCalInt
     * @param isAgainCalInt isAgainCalInt
     */
    public void setIsAgainCalInt(String isAgainCalInt) {
        this.isAgainCalInt = isAgainCalInt;
    }


    /**
     * 获取 invScheduleList
     * @return invScheduleList.
     */
    public List<FinCashPlanDto> getInvScheduleList() {
        return invScheduleList;
    }


    
    /**
     * 设置 invScheduleList
     * @param invScheduleList invScheduleList
     */
    public void setInvScheduleList(List<FinCashPlanDto> invScheduleList) {
        this.invScheduleList = invScheduleList;
    }


    
    /**
     * 获取 bussParticipantInfoDtoList
     * @return bussParticipantInfoDtoList.
     */
    public List<BussParticipantInfoDto> getBussParticipantInfoDtoList() {
        return bussParticipantInfoDtoList;
    }


    
    /**
     * 设置 bussParticipantInfoDtoList
     * @param bussParticipantInfoDtoList bussParticipantInfoDtoList
     */
    public void setBussParticipantInfoDtoList(List<BussParticipantInfoDto> bussParticipantInfoDtoList) {
        this.bussParticipantInfoDtoList = bussParticipantInfoDtoList;
    }


    
    /**
     * 获取 finCashPlan2List
     * @return finCashPlan2List.
     */
    public List<FinCashPlan> getFinCashPlan2List() {
        return finCashPlan2List;
    }


    
    /**
     * 设置 finCashPlan2List
     * @param finCashPlan2List finCashPlan2List
     */
    public void setFinCashPlan2List(List<FinCashPlan> finCashPlan2List) {
        this.finCashPlan2List = finCashPlan2List;
    }


    
    /**
     * 获取 isSubAmtCal
     * @return isSubAmtCal.
     */
    public String getIsSubAmtCal() {
        return isSubAmtCal;
    }


    
    /**
     * 设置 isSubAmtCal
     * @param isSubAmtCal isSubAmtCal
     */
    public void setIsSubAmtCal(String isSubAmtCal) {
        this.isSubAmtCal = isSubAmtCal;
    }


    
    /**
     * 获取 iscallbackPayByRepay
     * @return iscallbackPayByRepay.
     */
    public String getIscallbackPayByRepay() {
        return iscallbackPayByRepay;
    }


    
    /**
     * 设置 iscallbackPayByRepay
     * @param iscallbackPayByRepay iscallbackPayByRepay
     */
    public void setIscallbackPayByRepay(String iscallbackPayByRepay) {
        this.iscallbackPayByRepay = iscallbackPayByRepay;
    }


    
    /**
     * 获取 adjPlanFlag
     * @return adjPlanFlag.
     */
    public String getAdjPlanFlag() {
        return adjPlanFlag;
    }


    
    /**
     * 设置 adjPlanFlag
     * @param adjPlanFlag adjPlanFlag
     */
    public void setAdjPlanFlag(String adjPlanFlag) {
        this.adjPlanFlag = adjPlanFlag;
    }


    
    /**
     * 获取 maxTradeDate
     * @return maxTradeDate.
     */
    public Date getMaxTradeDate() {
        return maxTradeDate;
    }


    
    /**
     * 设置 maxTradeDate
     * @param maxTradeDate maxTradeDate
     */
    public void setMaxTradeDate(Date maxTradeDate) {
        this.maxTradeDate = maxTradeDate;
    }


    
    /**
     * 获取 type6
     * @return type6.
     */
    public String getType6() {
        return type6;
    }


    
    /**
     * 设置 type6
     * @param type6 type6
     */
    public void setType6(String type6) {
        this.type6 = type6;
    }


    
    /**
     * 获取 profitType
     * @return profitType.
     */
    public String getProfitType() {
        return profitType;
    }


    
    /**
     * 设置 profitType
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    /**
     * 获取 isPayIntFlag
     *
     * @return isPayIntFlag
     */
    @Override
    public String getIsPayIntFlag() {
        return isPayIntFlag;
    }

    /**
     * 设置 isPayIntFlag
     *
     * @param isPayIntFlag isPayIntFlag
     */
    @Override
    public void setIsPayIntFlag(String isPayIntFlag) {
        this.isPayIntFlag = isPayIntFlag;
    }

    /**
     * 获取 isPayInt
     *
     * @return isPayInt
     */
    public String getIsPayInt() {
        return isPayInt;
    }

    /**
     * 设置 isPayInt
     *
     * @param isPayInt isPayInt
     */
    public void setIsPayInt(String isPayInt) {
        this.isPayInt = isPayInt;
    }
}
