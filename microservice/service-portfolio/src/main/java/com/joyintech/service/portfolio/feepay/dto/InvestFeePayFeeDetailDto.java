/*
 * 文件名：FinProductFeePayFeeDetailDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：malinlin
 * 修改时间：2019年12月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.portfolio.feepay.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;


/**
 * 费用明细列表
 * 
 * @author malinlin
 * @version 1.0 2019年12月6日
 * @see InvestFeePayFeeDetailDto
 * @since 1.0
 */
public class InvestFeePayFeeDetailDto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 178566433294580403L;

    /**
     * 金融产品代码
     */
    private String finprodId;
    
    /**
     * 分支序号
     */
    private String branch;
    
    /**
     * 是否结清
     */
    private String isClean;
    
    /**
     * 产品代码
     */
    private String finprodMarketId;
    
    /**
     * 费用类型
     */
    private String feeType;
    
    /**
     * 费用名称
     */
    private String feeName;
    
    /**
     * 费用代码
     */
    private String feeId;
    
    /**
     * 计费基数
     */
    private String basicBill;
    
    /**
     * 计息基础
     */
    private String basis;
    
    /**
     * 计提开始日
     */
    private Date calcStartDate;
    
    /**
     * 计提结束日
     */
    private Date calcEndDate;
    
    /**
     * 本金
     */
    private BigDecimal prinAmt;
    
    /**
     * 费率%
     */
    private BigDecimal feeRate;
    
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
     * 子产品款型
     */
    private String sFinprodId;

    /**
     * 组合id
     */
    private String ptlId;
    
    /**
     * 组合名称
     */
    private String portFolioName;
    
    public String getsFinprodId() {
		return sFinprodId;
	}


	public void setsFinprodId(String sFinprodId) {
		this.sFinprodId = sFinprodId;
	}

    
    /**
     * 获取 finprodMarketId
     * @return finprodMarketId.
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    
    /**
     * 设置 finprodMarketId
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    
    /**
     * 获取 feeName
     * @return feeName.
     */
    public String getFeeName() {
        return feeName;
    }

    
    /**
     * 设置 feeName
     * @param feeName feeName
     */
    public void setFeeName(String feeName) {
        this.feeName = feeName;
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
     * 获取 prinAmt
     * @return prinAmt.
     */
    public BigDecimal getPrinAmt() {
        return prinAmt;
    }

    
    /**
     * 设置 prinAmt
     * @param prinAmt prinAmt
     */
    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }

    
    /**
     * 获取 feeRate
     * @return feeRate.
     */
    public BigDecimal getFeeRate() {
        return feeRate;
    }

    
    /**
     * 设置 feeRate
     * @param feeRate feeRate
     */
    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
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


    
    public String getFinprodId() {
        return finprodId;
    }


    
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }


    
    public String getFeeType() {
        return feeType;
    }


    
    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }


    
    public String getBasis() {
        return basis;
    }


    
    public void setBasis(String basis) {
        this.basis = basis;
    }


    
    public String getFeeId() {
        return feeId;
    }


    
    public void setFeeId(String feeId) {
        this.feeId = feeId;
    }


    
    public String getBranch() {
        return branch;
    }


    
    public void setBranch(String branch) {
        this.branch = branch;
    }


	public String getIsClean() {
		return isClean;
	}


	public void setIsClean(String isClean) {
		this.isClean = isClean;
	}


	public String getBasicBill() {
		return basicBill;
	}


	public void setBasicBill(String basicBill) {
		this.basicBill = basicBill;
	}

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
     * 证券账户
     */
    private String secAcctId;

    public String getSecAcctId() {
        return secAcctId;
    }

    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }
}
