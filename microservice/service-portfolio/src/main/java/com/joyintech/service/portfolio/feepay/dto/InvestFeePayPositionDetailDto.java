/*
 * 文件名：InvestFeePayPositionDetailDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：malinlin
 * 修改时间：2019年12月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.portfolio.feepay.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.NotEmpty;

/**
 * 费用明细查看列表
 * 
 * @author malinlin
 * @version 1.0 2019年12月13日
 * @see InvestFeePayPositionDetailDto
 * @since 1.0
 */
public class InvestFeePayPositionDetailDto extends BaseEntity {

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
    private static final long serialVersionUID = 1L;

	/**
	 * 组合代码
	 */
    private String ptlId;

	/**
	 * 组合名称
	 */
    private String portFolioName;

	/**
	 * 交易品种
	 */
    private String tradeType;

	/**
	 * 交易方向
	 */
    private String ps;

	/**
	 * 资产代码
	 */
    private String assetCode;
	
	/**
     * 资产名称
     */
    private String assetName;
    
    /**
     * 交易金额
     */
    private BigDecimal tradeAmt;
    
    /**
     * 交易日期
     */
    private String tradeDate;

	/**
	 * 费用类型
	 */
    private String feeType;

	/**
	 * 理论金额
	 */
    private BigDecimal payAmt;
    
    /**
     * 实付金额
     */
    private BigDecimal actPayAmt;
    
    /**
     * 调整金额
     */
    private BigDecimal adjustAmt;

    /**
     * 交易编号
     */
    private String tradeId;
    
    /**
     * 证券账户
     */
    private String secAcctId;

    /**
     * 计提开始日
     */
    private Date calcStartDate;

    /**
     * 计提结束日
     */
    private Date calcEndDate;
    
    /**
     * 是否结清
     */
    private String isClean;
    
    /**
     * 获取 ptlId
     * @return ptlId.
     */
    public String getPtlId() {
        return ptlId;
    }

    
    /**
     * 设置 ptlId
     * @param ptlId ptlId
     */
    public void setPtlId(String ptlId) {
        this.ptlId = ptlId;
    }

    
    /**
     * 获取 portFolioName
     * @return portFolioName.
     */
    public String getPortFolioName() {
        return portFolioName;
    }

    
    /**
     * 设置 portFolioName
     * @param portFolioName portFolioName
     */
    public void setPortFolioName(String portFolioName) {
        this.portFolioName = portFolioName;
    }

    
    /**
     * 获取 tradeType
     * @return tradeType.
     */
    public String getTradeType() {
        return tradeType;
    }

    
    /**
     * 设置 tradeType
     * @param tradeType tradeType
     */
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    
    /**
     * 获取 ps
     * @return ps.
     */
    public String getPs() {
        return ps;
    }

    
    /**
     * 设置 ps
     * @param ps ps
     */
    public void setPs(String ps) {
        this.ps = ps;
    }

    
    /**
     * 获取 assetCode
     * @return assetCode.
     */
    public String getAssetCode() {
        return assetCode;
    }

    
    /**
     * 设置 assetCode
     * @param assetCode assetCode
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    
    /**
     * 获取 assetName
     * @return assetName.
     */
    public String getAssetName() {
        return assetName;
    }

    
    /**
     * 设置 assetName
     * @param assetName assetName
     */
    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    
    /**
     * 获取 tradeAmt
     * @return tradeAmt.
     */
    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    
    /**
     * 设置 tradeAmt
     * @param tradeAmt tradeAmt
     */
    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    
    /**
     * 获取 tradeDate
     * @return tradeDate.
     */
    public String getTradeDate() {
        return tradeDate;
    }

    
    /**
     * 设置 tradeDate
     * @param tradeDate tradeDate
     */
    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    
    /**
     * 获取 feeType
     * @return feeType.
     */
    public String getFeeType() {
        return feeType;
    }

    
    /**
     * 设置 feeType
     * @param feeType feeType
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    
    /**
     * 获取 payAmt
     * @return payAmt.
     */
    public BigDecimal getPayAmt() {
        return payAmt;
    }

    
    /**
     * 设置 payAmt
     * @param payAmt payAmt
     */
    public void setPayAmt(BigDecimal payAmt) {
        this.payAmt = payAmt;
    }
    
    
    /**
     * 获取 actPayAmt
     * @return actPayAmt.
     */
    public BigDecimal getActPayAmt() {
        return actPayAmt;
    }


    /**
     * 设置 actPayAmt
     * @param actPayAmt actPayAmt
     */
    public void setActPayAmt(BigDecimal actPayAmt) {
        this.actPayAmt = actPayAmt;
    }


    /**
     * 获取 adjustAmt
     * @return adjustAmt.
     */
    public BigDecimal getAdjustAmt() {
        return adjustAmt;
    }

    
    /**
     * 设置 adjustAmt
     * @param adjustAmt adjustAmt
     */
    public void setAdjustAmt(BigDecimal adjustAmt) {
        this.adjustAmt = adjustAmt;
    }


    /**
     * 获取 tradeId
     * @return tradeId.
     */
    public String getTradeId() {
        return tradeId;
    }

    
    /**
     * 设置 tradeId
     * @param tradeId tradeId
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    
    /**
     * 获取 secAcctId
     * @return secAcctId.
     */
    public String getSecAcctId() {
        return secAcctId;
    }

    
    /**
     * 设置 secAcctId
     * @param secAcctId secAcctId
     */
    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    
    /**
     * 获取 calcStartDate
     * @return calcStartDate.
     */
    public Date getCalcStartDate() {
        return calcStartDate;
    }

    
    /**
     * 设置 calcStartDate
     * @param calcStartDate calcStartDate
     */
    public void setCalcStartDate(Date calcStartDate) {
        this.calcStartDate = calcStartDate;
    }

    
    /**
     * 获取 calcEndDate
     * @return calcEndDate.
     */
    public Date getCalcEndDate() {
        return calcEndDate;
    }

    
    /**
     * 设置 calcEndDate
     * @param calcEndDate calcEndDate
     */
    public void setCalcEndDate(Date calcEndDate) {
        this.calcEndDate = calcEndDate;
    }

    
    /**
     * 获取 isClean
     * @return isClean.
     */
    public String getIsClean() {
        return isClean;
    }

    
    /**
     * 设置 isClean
     * @param isClean isClean
     */
    public void setIsClean(String isClean) {
        this.isClean = isClean;
    }


}
