/*
 * 文件名：TrdLendDuration001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dingzhipeng
 * 修改时间：2018年9月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.period.trdlendduration.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.tams.invest.common.dto.period.trdlendduration.TrdLendDurationCommonDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2018年9月14日
 * @see TrdLendDurationDto
 * @since 1.0
 */
public class TrdLendDurationDto extends TrdLendDurationCommonDto{
    /**
     * 拆借类型
     */
    private String bankLendingType;
	/**
	 * 平安业务类型
	 */
	private String paBussType;
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 8496738071465391531L;
    /**
     * 画面数据状态
     */
    private String statusValid;
    
    /**
     * 操作类型
     */
    private String operateType;
    
    /**
     * 本金收益特征
     */
    private String capiIncomeFeature;
    /**
     * 画面类型
     */
    private String pageModel;
	/**
     * 业务代码
     */
    private String busiCode;
    /**
     * 组合代码
     */
    private String portfolioId;
    /**
     * 实体组合代码
     */
    private String actPortfolioId;

    /**
     * 组合名称
     */
    private String portfolioName;
    
    /**
     * 计息基础
     */
    private String basis;
    
    /**
     * 本金金额
     */
    private BigDecimal theoryPrin;
    
    /**
     * 资金到账日
     */
    private Date settleDate;
    /**
     * 发行机构代码
     */
    private String repIssuePartyId;
    /**
     * 应计利息
     */
    private BigDecimal intAmt;
    /**
     * 到期本息和
     */
    private BigDecimal dueInterest;
    /**
     * 到期本息和
     */
    private BigDecimal theoryAmt;
    
    /**
     * 实收本息和
     */
    private BigDecimal actRecPrincipa;
    
    /**
     * 利率% 
     */
    private BigDecimal intRate;
    /**
     * 撤销说明
     */
    private String cancelRemark;
    
	
    /**
     * 获取 cancelRemark
     * @return cancelRemark.
     */
    public String getCancelRemark() {
        return cancelRemark;
    }

    
    /**
     * 设置 cancelRemark
     * @param cancelRemark cancelRemark
     */
    public void setCancelRemark(String cancelRemark) {
        this.cancelRemark = cancelRemark;
    }

    /**
	 * 获取 statusValid
	 * @return statusValid.
	 */
	public String getStatusValid() {
		return statusValid;
	}

	/**
	 * 设置 statusValid
	 * @param statusValid statusValid
	 */
	public void setStatusValid(String statusValid) {
		this.statusValid = statusValid;
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
	 * 获取 capiIncomeFeature
	 * @return capiIncomeFeature.
	 */
	public String getCapiIncomeFeature() {
		return capiIncomeFeature;
	}

	/**
	 * 设置 capiIncomeFeature
	 * @param capiIncomeFeature capiIncomeFeature
	 */
	public void setCapiIncomeFeature(String capiIncomeFeature) {
		this.capiIncomeFeature = capiIncomeFeature;
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
	 * 获取 busiCode
	 * @return busiCode.
	 */
	public String getBusiCode() {
		return busiCode;
	}

	/**
	 * 设置 busiCode
	 * @param busiCode busiCode
	 */
	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}

	/**
	 * 获取 portfolioId
	 * @return portfolioId.
	 */
	public String getPortfolioId() {
		return portfolioId;
	}

	/**
	 * 设置 portfolioId
	 * @param portfolioId portfolioId
	 */
	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	/**
	 * 获取 portfolioName
	 * @return portfolioName.
	 */
	public String getPortfolioName() {
		return portfolioName;
	}

	/**
	 * 设置 portfolioName
	 * @param portfolioName portfolioName
	 */
	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	/**
	 * 获取 basis
	 * @return basis.
	 */
	public String getBasis() {
		return basis;
	}

	/**
	 * 设置 basis
	 * @param basis basis
	 */
	public void setBasis(String basis) {
		this.basis = basis;
	}

	/**
	 * 获取 theoryPrin
	 * @return theoryPrin.
	 */
	public BigDecimal getTheoryPrin() {
		return theoryPrin;
	}

	/**
	 * 设置 theoryPrin
	 * @param theoryPrin theoryPrin
	 */
	public void setTheoryPrin(BigDecimal theoryPrin) {
		this.theoryPrin = theoryPrin;
	}

	/**
	 * 获取 settleDate
	 * @return settleDate.
	 */
	public Date getSettleDate() {
		return settleDate;
	}

	/**
	 * 设置 settleDate
	 * @param settleDate settleDate
	 */
	public void setSettleDate(Date settleDate) {
		this.settleDate = settleDate;
	}

	/**
	 * 获取 intAmt
	 * @return intAmt.
	 */
	public BigDecimal getIntAmt() {
		return intAmt;
	}

	/**
	 * 设置 intAmt
	 * @param intAmt intAmt
	 */
	public void setIntAmt(BigDecimal intAmt) {
		this.intAmt = intAmt;
	}

	/**
	 * 获取 dueInterest
	 * @return dueInterest.
	 */
	public BigDecimal getDueInterest() {
		return dueInterest;
	}

	/**
	 * 设置 dueInterest
	 * @param dueInterest dueInterest
	 */
	public void setDueInterest(BigDecimal dueInterest) {
		this.dueInterest = dueInterest;
	}

	/**
	 * 获取 actRecPrincipa
	 * @return actRecPrincipa.
	 */
	public BigDecimal getActRecPrincipa() {
		return actRecPrincipa;
	}

	/**
	 * 设置 actRecPrincipa
	 * @param actRecPrincipa actRecPrincipa
	 */
	public void setActRecPrincipa(BigDecimal actRecPrincipa) {
		this.actRecPrincipa = actRecPrincipa;
	}

	/**
	 * 获取 intRate
	 * @return intRate.
	 */
	public BigDecimal getIntRate() {
		return intRate;
	}

	/**
	 * 设置 intRate
	 * @param intRate intRate
	 */
	public void setIntRate(BigDecimal intRate) {
		this.intRate = intRate;
	}

	/**
	 * 获取 paBussType
	 * @return paBussType.
	 */
	public String getPaBussType() {
		return paBussType;
	}

	/**
	 * 设置 paBussType
	 * @param paBussType paBussType
	 */
	public void setPaBussType(String paBussType) {
		this.paBussType = paBussType;
	}

	/**
	 * 获取 repIssuePartyId
	 * @return repIssuePartyId.
	 */
	public String getRepIssuePartyId() {
		return repIssuePartyId;
	}

	/**
	 * 设置 repIssuePartyId
	 * @param repIssuePartyId repIssuePartyId
	 */
	public void setRepIssuePartyId(String repIssuePartyId) {
		this.repIssuePartyId = repIssuePartyId;
	}

	/**
	 * 获取 theoryAmt
	 * @return theoryAmt.
	 */
	public BigDecimal getTheoryAmt() {
		return theoryAmt;
	}

	/**
	 * 设置 theoryAmt
	 * @param theoryAmt theoryAmt
	 */
	public void setTheoryAmt(BigDecimal theoryAmt) {
		this.theoryAmt = theoryAmt;
	}

	/**
	 * 获取 bankLendingType
	 * @return bankLendingType.
	 */
	public String getBankLendingType() {
		return bankLendingType;
	}

	/**
	 * 设置 bankLendingType
	 * @param bankLendingType bankLendingType
	 */
	public void setBankLendingType(String bankLendingType) {
		this.bankLendingType = bankLendingType;
	}

	/**
	 * 获取 actPortfolioId
	 *
	 * @return actPortfolioId
	 */
	@Override
	public String getActPortfolioId() {
		return actPortfolioId;
	}

	/**
	 * 设置 actPortfolioId
	 *
	 * @param actPortfolioId actPortfolioId
	 */
	@Override
	public void setActPortfolioId(String actPortfolioId) {
		this.actPortfolioId = actPortfolioId;
	}
}
