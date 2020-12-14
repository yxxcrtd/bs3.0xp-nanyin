/*
 * 文件名：ValFinprodTradeQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 
 * 产品资产查询Dto
 * @author 王耕耘
 * @version 1.0 2018年5月16日
 * @see ValNetValueConfirm001Dto
 * @since 1.0
 */
@PageId("ValNetValueConfirm001")
public class ValNetValueConfirm001Dto extends BaseEntity implements ProcessEntity {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 金融产品代码
     */
    @PrimaryKey(value=Field.PK_1)
    private String finprodId;
    /**
     * 金融产品名称
     */
    private String finprodAbbr;
    /**
     * 金融产品类型，债券、理财产品、基金
     */
    private String finprodType;
    /**
     * 金融产品类型，债券、理财产品、基金
     */
    private String finprodTypeCode;
    /**
     * 估值日期                 
     */
    @PrimaryKey(value=Field.PK_2)
    private String valDate;
    /**
     * 估值开始日期           
     */
    private String valDateBegin;
    /**
     * 估值结束日期                 
     */
    private String valDateEnd;
    /**
     * 估值表余额表标志：01：估值表；02：余额表                         
     */
    private String valBalFlag;
    /**
     * 估值表余额表标志：01：估值表；02：余额表                         
     */
    @PrimaryKey(value=Field.PK_3)
    private String valBalFlagCode;
    /**
     * 理财份额 
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal capiTal;
    /**
     * 产品总净值      
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal assetValue;
    /**
     * 单位净值/万份收益/利率     
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netUnitValue;
    /**
     * 累计净值  
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal totalNetNalue;
    /**
     * 七日年化收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sevenDayRate;
    /**
     * 应付利润
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal payProfit;
    /**
     * 当日年化收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyYield;
    /**
     * 对账状态                         
     */
    private String checkStatus;
    /**
     * 对账状态                         
     */
    private String checkStatusCode;
    /**
     * 对账结果                         
     */
    private String checkResult;
    /**
     * 对账结果                         
     */
    private String checkResultCode;
    /**
     * 确认状态                         
     */
    private String confirmStatus;
    /**
     * 确认状态                         
     */
    private String confirmStatusCode;
    /**
     * 下发状态
     */
    private String noticeStatus;
    /**
     * 下发状态
     */
    private String noticeStatusCode;
    
    /**
     * 产品类型
     */
    private String  profitType;
    
    /**
     * 产品类型Code
     */
    private String  profitTypeCode;
    
    /**
     * 当前操作为确认还是下发【确认："QR",下发："XF"】
     */
    private String confirmOrNotice;

    /**
     * 产品总资产
     */
    private BigDecimal prdTotAssetVal;

    /**
     * 产品单位净值小数位
     */
    private BigDecimal prdAssetVal;

    /**
     * 产品单位净值位数处理方式
     */
    private BigDecimal unitvaluedec;

    /**
     * 产品净资产
     */
    private String unitvaluemode;



    public String getProfitType() {
		return this.profitType;
	}

	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}

	
	
    /**
     * 获取 profitTypeCode
     * @return profitTypeCode.
     */
    public String getProfitTypeCode() {
        return profitTypeCode;
    }

    
    /**
     * 设置 profitTypeCode
     * @param profitTypeCode profitTypeCode
     */
    public void setProfitTypeCode(String profitTypeCode) {
        this.profitTypeCode = profitTypeCode;
    }

    /**
     * 获取 finprodId
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }
    
    /**
     * 设置 finprodId
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }
    
    /**
     * 获取 finprodAbbr
     * @return finprodAbbr.
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }
    
    /**
     * 设置 finprodAbbr
     * @param finprodAbbr finprodAbbr
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }
    
    /**
     * 获取 finprodType
     * @return finprodType.
     */
    public String getFinprodType() {
    	return this.finprodType;
    }
    
    /**
     * 设置 finprodType
     * @param finprodType finprodType
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }
    
    /**
     * 获取 finprodTypeCode
     * @return finprodTypeCode.
     */
    public String getFinprodTypeCode() {
        return finprodTypeCode;
    }
    
    /**
     * 设置 finprodTypeCode
     * @param finprodTypeCode finprodTypeCode
     */
    public void setFinprodTypeCode(String finprodTypeCode) {
        this.finprodTypeCode = finprodTypeCode;
    }
    
    /**
     * 获取 valDate
     * @return valDate.
     */
    public String getValDate() {
        return valDate;
    }
    
    /**
     * 设置 valDate
     * @param valDate valDate
     */
    public void setValDate(String valDate) {
        this.valDate = valDate;
    }
    
    /**
     * 获取 valDateBegin
     * @return valDateBegin.
     */
    public String getValDateBegin() {
        return valDateBegin;
    }
    
    /**
     * 设置 valDateBegin
     * @param valDateBegin valDateBegin
     */
    public void setValDateBegin(String valDateBegin) {
        this.valDateBegin = valDateBegin;
    }
    
    /**
     * 获取 valDateEnd
     * @return valDateEnd.
     */
    public String getValDateEnd() {
        return valDateEnd;
    }
    
    /**
     * 设置 valDateEnd
     * @param valDateEnd valDateEnd
     */
    public void setValDateEnd(String valDateEnd) {
        this.valDateEnd = valDateEnd;
    }
    
    /**
     * 获取 valBalFlag
     * @return valBalFlag.
     */
    public String getValBalFlag() {
    	return this.valBalFlag;
    }
    
    /**
     * 设置 valBalFlag
     * @param valBalFlag valBalFlag
     */
    public void setValBalFlag(String valBalFlag) {
        this.valBalFlag = valBalFlag;
    }
    
    /**
     * 获取 valBalFlagCode
     * @return valBalFlagCode.
     */
    public String getValBalFlagCode() {
        return valBalFlagCode;
    }
    
    /**
     * 设置 valBalFlagCode
     * @param valBalFlagCode valBalFlagCode
     */
    public void setValBalFlagCode(String valBalFlagCode) {
        this.valBalFlagCode = valBalFlagCode;
    }
    
    /**
     * 获取 capiTal
     * @return capiTal.
     */
    public BigDecimal getCapiTal() {
        return capiTal;
    }
    
    /**
     * 设置 capiTal
     * @param capiTal capiTal
     */
    public void setCapiTal(BigDecimal capiTal) {
        this.capiTal = capiTal;
    }
    
    /**
     * 获取 assetValue
     * @return assetValue.
     */
    public BigDecimal getAssetValue() {
        return assetValue;
    }
    
    /**
     * 设置 assetValue
     * @param assetValue assetValue
     */
    public void setAssetValue(BigDecimal assetValue) {
        this.assetValue = assetValue;
    }
    
    /**
     * 获取 netUnitValue
     * @return netUnitValue.
     */
    public BigDecimal getNetUnitValue() {
        return netUnitValue;
    }
    
    /**
     * 设置 netUnitValue
     * @param netUnitValue netUnitValue
     */
    public void setNetUnitValue(BigDecimal netUnitValue) {
        this.netUnitValue = netUnitValue;
    }
    
    /**
     * 获取 totalNetNalue
     * @return totalNetNalue.
     */
    public BigDecimal getTotalNetNalue() {
        return totalNetNalue;
    }
    
    /**
     * 设置 totalNetNalue
     * @param totalNetNalue totalNetNalue
     */
    public void setTotalNetNalue(BigDecimal totalNetNalue) {
        this.totalNetNalue = totalNetNalue;
    }
    
    /**
     * 获取 sevenDayRate
     * @return sevenDayRate.
     */
    public BigDecimal getSevenDayRate() {
        return sevenDayRate;
    }
    
    /**
     * 设置 sevenDayRate
     * @param sevenDayRate sevenDayRate
     */
    public void setSevenDayRate(BigDecimal sevenDayRate) {
        this.sevenDayRate = sevenDayRate;
    }
    
    /**
     * 获取 payProfit
     * @return payProfit.
     */
    public BigDecimal getPayProfit() {
        return payProfit;
    }
    
    /**
     * 设置 payProfit
     * @param payProfit payProfit
     */
    public void setPayProfit(BigDecimal payProfit) {
        this.payProfit = payProfit;
    }
    
    /**
     * 获取 checkStatus
     * @return checkStatus.
     */
    public String getCheckStatus() {
    	return this.checkStatus;
    }
    
    /**
     * 设置 checkStatus
     * @param checkStatus checkStatus
     */
    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }
    
    /**
     * 获取 checkStatusCode
     * @return checkStatusCode.
     */
    public String getCheckStatusCode() {
        return checkStatusCode;
    }
    
    /**
     * 设置 checkStatusCode
     * @param checkStatusCode checkStatusCode
     */
    public void setCheckStatusCode(String checkStatusCode) {
        this.checkStatusCode = checkStatusCode;
    }
    
    /**
     * 获取 checkResult
     * @return checkResult.
     */
    public String getCheckResult() {
    	return this.checkResult;
    }
    
    /**
     * 设置 checkResult
     * @param checkResult checkResult
     */
    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }
    
    /**
     * 获取 checkResultCode
     * @return checkResultCode.
     */
    public String getCheckResultCode() {
        return checkResultCode;
    }
    
    /**
     * 设置 checkResultCode
     * @param checkResultCode checkResultCode
     */
    public void setCheckResultCode(String checkResultCode) {
        this.checkResultCode = checkResultCode;
    }
    
    /**
     * 获取 confirmStatus
     * @return confirmStatus.
     */
    public String getConfirmStatus() {
        return this.confirmStatus;
    }
    
    /**
     * 设置 confirmStatus
     * @param confirmStatus confirmStatus
     */
    public void setConfirmStatus(String confirmStatus) {
        this.confirmStatus = confirmStatus;
    }
    
    /**
     * 获取 confirmStatusCode
     * @return confirmStatusCode.
     */
    public String getConfirmStatusCode() {
        return confirmStatusCode;
    }
    
    /**
     * 设置 confirmStatusCode
     * @param confirmStatusCode confirmStatusCode
     */
    public void setConfirmStatusCode(String confirmStatusCode) {
        this.confirmStatusCode = confirmStatusCode;
    }
    
    /**
     * 获取 noticeStatus
     * @return noticeStatus.
     */
    public String getNoticeStatus() {
    	return this.noticeStatus;
    }
    
    /**
     * 设置 noticeStatus
     * @param noticeStatus noticeStatus
     */
    public void setNoticeStatus(String noticeStatus) {
        this.noticeStatus = noticeStatus;
    }
    
    /**
     * 获取 noticeStatusCode
     * @return noticeStatusCode.
     */
    public String getNoticeStatusCode() {
        return noticeStatusCode;
    }
    
    /**
     * 设置 noticeStatusCode
     * @param noticeStatusCode noticeStatusCode
     */
    public void setNoticeStatusCode(String noticeStatusCode) {
        this.noticeStatusCode = noticeStatusCode;
    }
    
    /**
     * 获取 serialversionuid
     * @return serialversionuid.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    
    /**
     * 获取 confirmOrNotice
     * @return confirmOrNotice.
     */
    public String getConfirmOrNotice() {
        return confirmOrNotice;
    }

    public BigDecimal getPrdTotAssetVal() {
        return prdTotAssetVal;
    }

    public void setPrdTotAssetVal(BigDecimal prdTotAssetVal) {
        this.prdTotAssetVal = prdTotAssetVal;
    }

    public BigDecimal getPrdAssetVal() {
        return prdAssetVal;
    }

    public void setPrdAssetVal(BigDecimal prdAssetVal) {
        this.prdAssetVal = prdAssetVal;
    }

    /**
     * 设置 confirmOrNotice
     * @param confirmOrNotice confirmOrNotice
     */
    public void setConfirmOrNotice(String confirmOrNotice) {
        this.confirmOrNotice = confirmOrNotice;
    }

	public BigDecimal getTdyYield() {
		return tdyYield;
	}

	public void setTdyYield(BigDecimal tdyYield) {
		this.tdyYield = tdyYield;
	}

    public BigDecimal getUnitvaluedec() {
        return unitvaluedec;
    }

    public void setUnitvaluedec(BigDecimal unitvaluedec) {
        this.unitvaluedec = unitvaluedec;
    }

    public String getUnitvaluemode() {
        return unitvaluemode;
    }

    public void setUnitvaluemode(String unitvaluemode) {
        this.unitvaluemode = unitvaluemode;
    }
}
      			
      