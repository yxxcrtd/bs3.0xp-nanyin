package com.joyintech.tams.valuation.common.interf.base.service.send.dto;

/*
 * 文件名：ValFinprodTradeQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */


import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 
 * 产品资产查询Dto
 * @author 王耕耘
 * @version 1.0 2018年5月16日
 * @see ValNetValueConfirm001Dto
 * @since 1.0
 */
public class ValNetValueConfirmDto extends BaseEntity {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 金融产品代码
     */
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
     * 帐套编号
     */
    private String bookId;
    
    /**
     * 计息基础
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal basis;
    
    /**
     * 产品类型
     */
    private String  profitType;


    private String profitTypeCode;

    /**
     * 产品总资产
     */
    private BigDecimal prdTotAssetVal;

    /**
     * 产品净资产
     */
    private BigDecimal prdAssetVal;
    
    
    public String getProfitType() {
		return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.收益类型, this.profitType))
    			?SysDataUtil.getNameFromDataDict(DictCode.收益类型, this.profitType):this.profitType;
	}

	public void setProfitType(String profitType) {
		this.profitType = profitType;
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
    	return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.金融产品类型, this.finprodType))
    			?SysDataUtil.getNameFromDataDict(DictCode.金融产品类型, this.finprodType):this.finprodType;
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
    	return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.文件类型, this.valBalFlag))
    			?SysDataUtil.getNameFromDataDict(DictCode.文件类型, this.valBalFlag):this.valBalFlag;
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
    	return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.对账状态, this.checkStatus))
    			?SysDataUtil.getNameFromDataDict(DictCode.对账状态, this.checkStatus):this.checkStatus;
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
    	return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.对账结果, this.checkResult))
    			?SysDataUtil.getNameFromDataDict(DictCode.对账结果, this.checkResult):this.checkResult;
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
    	
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.确认状态, this.confirmStatus))
        			?SysDataUtil.getNameFromDataDict(DictCode.确认状态, this.confirmStatus):this.confirmStatus;
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
    	return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.下发状态, this.noticeStatus))
    			?SysDataUtil.getNameFromDataDict(DictCode.下发状态, this.noticeStatus):this.noticeStatus;
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
	 * 获取 bookId
	 * @return bookId.
	 */
	public String getBookId() {
		return bookId;
	}

	/**
	 * 设置 bookId
	 * @param bookId bookId
	 */
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	/**
	 * 获取 basis
	 * @return basis.
	 */
	public BigDecimal getBasis() {
		return basis;
	}

	/**
	 * 设置 basis
	 * @param basis basis
	 */
	public void setBasis(BigDecimal basis) {
		this.basis = basis;
	}

    public String getProfitTypeCode() {
        return profitTypeCode;
    }

    public void setProfitTypeCode(String profitTypeCode) {
        this.profitTypeCode = profitTypeCode;
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
}
      			
      