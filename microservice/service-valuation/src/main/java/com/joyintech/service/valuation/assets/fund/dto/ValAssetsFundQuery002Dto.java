/*
 * 文件名：ValAssetFundQueryDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年7月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.assets.fund.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.service.valuation.assets.fund.validator.ValAssetsFundQuery002Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 基金类资产查询详情 DTO
 * 
 * @author zhangwei
 * @version 1.0 2018年7月19日
 * @see ValAssetFundQueryDto
 * @since 1.0
 */
@ValidLogic(targetClass = ValAssetsFundQuery002Validator.class)
public class ValAssetsFundQuery002Dto extends BaseEntity implements ProcessEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 资产代码
     */
    @PrimaryKey(value=Field.PK_1)
    @NotEmpty(fieldName = "ValAssetsFund.finprodId")
    private String finprodId;

    /**
     * 资产名称
     */
    @NotEmpty(fieldName = "ValAssetsFund.finprodAbbr")
    private String finprodAbbr;

    /**
     * 市场代码
     */
    private String finprodMarketId;

    /**
     * 基金类型
     */
    @NotEmpty(fieldName = "ValAssetsFund.fundType")
    private String fundType;

    /**
     * 收益类型
     */
    private String profitType;

    /**
     * 管理人
     */
    @NotEmpty(fieldName = "ValAssetsFund.issuer")
    private String issuer;

    /**
     * 币种
     */
    @NotEmpty(fieldName = "ValAssetsFund.ccy")
    private String ccy;

    /**
     * 发行日
     */
    @NotEmpty(fieldName = "ValAssetsFund.idate")
    private Date idate;

    /**
     * 交易日历
     */
    @NotEmpty(fieldName = "ValAssetsFund.calendarId")
    private String calendarId;

    /**
     * 交易日历名称
     */
    private String calendarName;

    /**
     * 境内外
     */
    @NotEmpty(fieldName = "ValAssetsFund.blnArea")
    private String blnArea;

    /**
     * 交易场所
     */
    @NotEmpty(fieldName = "ValAssetsFund.tradeMarket")
    private String tradeMarket;

    /**
     * 托管人
     */
    // @NotEmpty(fieldName = "ValAssetsFund.custodian")
    private String custodian;

    /**
     * 申购确认速度
     */
    @NotEmpty(fieldName = "ValAssetsFund.purSpeed")
    private Long purSpeed;

    /**
     * 赎回确认速度
     */
    @NotEmpty(fieldName = "ValAssetsFund.redSpeed")
    private Long redSpeed;

    /**
     * 本金收益特征
     */
    @NotEmpty(fieldName = "ValAssetsFund.capiIncomeFeature")
    private String capiIncomeFeature;

    /**
     * 是否按日复利
     */
    private String isCompoundInt;

    /**
     * 分红频率
     */
    private String payCycle;

    /**
     * 分红日
     */
    private Date sdate;

//    /**
//     * 营业日准则
//     */
//    private String tdayRule;

    /**
     * 截止日营业日准则
     */
    private String endTdayRule;

    /**
     * 支付日营业日准则
     */
    private String payTdayRule;
    
    /**
     * 推算方式
     */
    private String calcRule;

    /** 计息开始日 */
    private Date vdate;

    /** 计息结束日 */
    private Date mdate;

    /** 付息日 */
    private Date payDate;

    // /**
    // * 是新建还是修改操作 Y：修改 N：新建
    // */
    // private String isEdit;

    /**
     * 操作类型：新建new、修改edit、双击查看view
     */
    private String operateType;

    /**
     * 分红计划
     */
    private List<ValCashPlanDto> planList;

    /**
     * 获取 finprodId
     * 
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 finprodId
     * 
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 finprodAbbr
     * 
     * @return finprodAbbr.
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    /**
     * 设置 finprodAbbr
     * 
     * @param finprodAbbr finprodAbbr
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    /**
     * 获取 finprodMarketId
     * 
     * @return finprodMarketId.
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    /**
     * 设置 finprodMarketId
     * 
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    /**
     * 获取 fundType
     * 
     * @return fundType.
     */
    public String getFundType() {
        return fundType;
    }

    /**
     * 设置 fundType
     * 
     * @param fundType fundType
     */
    public void setFundType(String fundType) {
        this.fundType = fundType;
    }

    public String getProfitType() {
		return profitType;
	}

	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}

	/**
     * 获取 issuer
     * 
     * @return issuer.
     */
    public String getIssuer() {
        return issuer;
    }

    /**
     * 设置 issuer
     * 
     * @param issuer issuer
     */
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    /**
     * 获取 ccy
     * 
     * @return ccy.
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 ccy
     * 
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 idate
     * 
     * @return idate.
     */
    public Date getIdate() {
        return idate;
    }

    /**
     * 设置 idate
     * 
     * @param idate idate
     */
    public void setIdate(Date idate) {
        this.idate = idate;
    }

    /**
     * 获取 calendarId
     * 
     * @return calendarId.
     */
    public String getCalendarId() {
        return calendarId;
    }

    /**
     * 设置 calendarId
     * 
     * @param calendarId calendarId
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    /**
     * 获取 calendarName
     * 
     * @return calendarName.
     */
    public String getCalendarName() {
        return calendarName;
    }

    /**
     * 设置 calendarName
     * 
     * @param calendarName calendarName
     */
    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }

    /**
     * 获取 blnArea
     * 
     * @return blnArea.
     */
    public String getBlnArea() {
        return blnArea;
    }

    /**
     * 设置 blnArea
     * 
     * @param blnArea blnArea
     */
    public void setBlnArea(String blnArea) {
        this.blnArea = blnArea;
    }

    /**
     * 获取 tradeMarket
     * 
     * @return tradeMarket.
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 tradeMarket
     * 
     * @param tradeMarket tradeMarket
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    /**
     * 获取 custodian
     * 
     * @return custodian.
     */
    public String getCustodian() {
        return custodian;
    }

    /**
     * 设置 custodian
     * 
     * @param custodian custodian
     */
    public void setCustodian(String custodian) {
        this.custodian = custodian;
    }

    /**
     * 获取 purSpeed
     * 
     * @return purSpeed.
     */
    public Long getPurSpeed() {
        return purSpeed;
    }

    /**
     * 设置 purSpeed
     * 
     * @param purSpeed purSpeed
     */
    public void setPurSpeed(Long purSpeed) {
        this.purSpeed = purSpeed;
    }

    /**
     * 获取 redSpeed
     * 
     * @return redSpeed.
     */
    public Long getRedSpeed() {
        return redSpeed;
    }

    /**
     * 设置 redSpeed
     * 
     * @param redSpeed redSpeed
     */
    public void setRedSpeed(Long redSpeed) {
        this.redSpeed = redSpeed;
    }

    /**
     * 获取 capiIncomeFeature
     * 
     * @return capiIncomeFeature.
     */
    public String getCapiIncomeFeature() {
        return capiIncomeFeature;
    }

    /**
     * 设置 capiIncomeFeature
     * 
     * @param capiIncomeFeature capiIncomeFeature
     */
    public void setCapiIncomeFeature(String capiIncomeFeature) {
        this.capiIncomeFeature = capiIncomeFeature;
    }

    /**
     * 获取 isCompoundInt
     * 
     * @return isCompoundInt.
     */
    public String getIsCompoundInt() {
        return isCompoundInt;
    }

    /**
     * 设置 isCompoundInt
     * 
     * @param isCompoundInt isCompoundInt
     */
    public void setIsCompoundInt(String isCompoundInt) {
        this.isCompoundInt = isCompoundInt;
    }

    /**
     * 获取 payCycle
     * 
     * @return payCycle.
     */
    public String getPayCycle() {
        return payCycle;
    }

    /**
     * 设置 payCycle
     * 
     * @param payCycle payCycle
     */
    public void setPayCycle(String payCycle) {
        this.payCycle = payCycle;
    }

    /**
     * 获取 sdate
     * 
     * @return sdate.
     */
    public Date getSdate() {
        return sdate;
    }

    /**
     * 设置 sdate
     * 
     * @param sdate sdate
     */
    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

//    /**
//     * 获取 tdayRule
//     * 
//     * @return tdayRule.
//     */
//    public String getTdayRule() {
//        return tdayRule;
//    }
//
//    /**
//     * 设置 tdayRule
//     * 
//     * @param tdayRule tdayRule
//     */
//    public void setTdayRule(String tdayRule) {
//        this.tdayRule = tdayRule;
//    }
    

    /**
     * 获取 calcRule
     * 
     * @return calcRule.
     */
    public String getCalcRule() {
        return calcRule;
    }

    public String getEndTdayRule() {
		return endTdayRule;
	}

	public void setEndTdayRule(String endTdayRule) {
		this.endTdayRule = endTdayRule;
	}

	public String getPayTdayRule() {
		return payTdayRule;
	}

	public void setPayTdayRule(String payTdayRule) {
		this.payTdayRule = payTdayRule;
	}

	/**
     * 设置 calcRule
     * 
     * @param calcRule calcRule
     */
    public void setCalcRule(String calcRule) {
        this.calcRule = calcRule;
    }

    // /**
    // * 获取 isEdit
    // *
    // * @return isEdit.
    // */
    // public String getIsEdit() {
    // return isEdit;
    // }
    //
    // /**
    // * 设置 isEdit
    // *
    // * @param isEdit isEdit
    // */
    // public void setIsEdit(String isEdit) {
    // this.isEdit = isEdit;
    // }

    /**
     * 获取 vdate
     * 
     * @return vdate.
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 vdate
     * 
     * @param vdate vdate
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 获取 mdate
     * 
     * @return mdate.
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 mdate
     * 
     * @param mdate mdate
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 获取 payDate
     * 
     * @return payDate.
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     * 设置 payDate
     * 
     * @param payDate payDate
     */
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * 获取 operateType
     *
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operateType
     *
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 planList
     * 
     * @return planList.
     */
    public List<ValCashPlanDto> getPlanList() {
        return planList;
    }

    /**
     * 设置 planList
     * 
     * @param planList planList
     */
    public void setPlanList(List<ValCashPlanDto> planList) {
        this.planList = planList;
    }

}
