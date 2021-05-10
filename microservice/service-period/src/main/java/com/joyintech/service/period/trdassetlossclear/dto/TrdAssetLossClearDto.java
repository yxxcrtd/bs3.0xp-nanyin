/*
 * 文件名：TrdDepoDurationDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.period.trdassetlossclear.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.invest.common.dto.period.trdprojduration.TrdProjectDurationDto;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;


/**
 * 存款类存续期Dto
 * 〈功能详细描述〉
 * @author Administrator
 * @version 1.0 2018年10月11日
 * @see TrdAssetLossClearDto
 * @since 1.0
 */
public class TrdAssetLossClearDto extends TrdProjectDurationDto{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 3974078160802737259L;
    /**
     * 汇总交易编号
     */
    private String totleTradeId;
    /**
     * 金融产品代码
     */
    private String finprodId;
    /**
     * 资产名称
     */
    private String finprodName;
    /**
     * 清理日期
     */
    private Date tradeDate;
    /**
     * 持仓份额
     */
    private BigDecimal shareAmt;
    /**
     * 持仓应收利息
     */
    private BigDecimal intAmt;
    /**
     * 持仓成本
     */
    private BigDecimal prinAmt;
    /**
     * 已到期本息
     */
    private BigDecimal clearRecAmt;
    /**
     * 当前账面减值余额
     */
    private BigDecimal clearLossAmt;
    
    /**
     * 组合代码
     */
    private String portfolioId; 
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     * 证券管理账户/通道代码
     */
    private String secAcctId;
    /**
     * 证券管理账户/通道代码名称
     */
    private String secAcctIdName;
    /**
     * 投资目的
     */
    private String invAim;
    /**
     * 投资目的简称
     */
    private String invAimName;
    /**
     * 金融产品类型（估值）	
     */
    private String finprodType;
    /**
     * 金融产品类型（投管）		
     */
    private String finprodType2; 
    /**
     * 原币
     */
    private String ccy;
    /**
     * 单位面值
     */
    private BigDecimal unitValue;
    /**
     * 交易场所
     */
    private String tradeMarket;
    /**
     * 操作类型
     */
    private String operateType;
    
    /**
     * 
     */
    private String elementId;
    
    /**
     * 
     */
    private List<TrdAssetLossClearDto> selectedItem;
    
    /**
     * 
     */
    private String pageModel;
    /**
     * 
     */
    private String  workflowId;
    
    /**
     * 罚息金额
     */
    private BigDecimal penaltyIntAmt;
    
    /**
     * 分支序号
     */
    private String branch;
    /**
     * 应收款
     */
    private BigDecimal receAmt;
    /**
     * 分支类型
     */
    private String branchType;
    
    /**
     * 持仓类型，可用、实际、在途、质押、融入、融出
     */
    private String hodingType;
    
    /**
     * 分支类型
     */
    private String recType;
    
    public String getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}
     
	public String getFinprodName() {
		return finprodName;
	}
	public void setFinprodName(String finprodName) {
		this.finprodName = finprodName;
	}
	public String getTotleTradeId() {
		return totleTradeId;
	}
	public void setTotleTradeId(String totleTradeId) {
		this.totleTradeId = totleTradeId;
	}
	public String getFinprodId() {
		return finprodId;
	}
	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}
	public Date getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}
	public BigDecimal getShareAmt() {
		return shareAmt;
	}
	public void setShareAmt(BigDecimal shareAmt) {
		this.shareAmt = shareAmt;
	}
	public BigDecimal getIntAmt() {
		return intAmt;
	}
	public void setIntAmt(BigDecimal intAmt) {
		this.intAmt = intAmt;
	}
	public BigDecimal getPrinAmt() {
		return prinAmt;
	}
	public void setPrinAmt(BigDecimal prinAmt) {
		this.prinAmt = prinAmt;
	}
	public BigDecimal getClearLossAmt() {
		return clearLossAmt;
	}
	public void setClearLossAmt(BigDecimal clearLossAmt) {
		this.clearLossAmt = clearLossAmt;
	}
	public BigDecimal getClearRecAmt() {
		return clearRecAmt;
	}
	public void setClearRecAmt(BigDecimal clearRecAmt) {
		this.clearRecAmt = clearRecAmt;
	}
	public String getPortfolioId() {
		return portfolioId;
	}
	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}
	public String getPortfolioName() {
		return portfolioName;
	}
	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}
	public String getSecAcctId() {
		return secAcctId;
	}
	public void setSecAcctId(String secAcctId) {
		this.secAcctId = secAcctId;
	}
	public String getInvAim() {
		return invAim;
	}
	public void setInvAim(String invAim) {
		this.invAim = invAim;
	}
	public String getFinprodType() {
		return finprodType;
	}
	public void setFinprodType(String finprodType) {
		this.finprodType = finprodType;
	}
	public String getFinprodType2() {
		return finprodType2;
	}
	public void setFinprodType2(String finprodType2) {
		this.finprodType2 = finprodType2;
	}
	public String getCcy() {
		return ccy;
	}
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}
	public BigDecimal getUnitValue() {
		return unitValue;
	}
	public void setUnitValue(BigDecimal unitValue) {
		this.unitValue = unitValue;
	}
	public String getTradeMarket() {
		return tradeMarket;
	}
	public void setTradeMarket(String tradeMarket) {
		this.tradeMarket = tradeMarket;
	}
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	public String getElementId() {
		return elementId;
	}
	public void setElementId(String elementId) {
		this.elementId = elementId;
	}
	public List<TrdAssetLossClearDto> getSelectedItem() {
		return selectedItem;
	}
	public void setSelectedItem(List<TrdAssetLossClearDto> selectedItem) {
		this.selectedItem = selectedItem;
	}
	public String getPageModel() {
		return pageModel;
	}
	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}

	public BigDecimal getPenaltyIntAmt() {
		return penaltyIntAmt;
	}

	public void setPenaltyIntAmt(BigDecimal penaltyIntAmt) {
		this.penaltyIntAmt = penaltyIntAmt;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public BigDecimal getReceAmt() {
		return receAmt;
	}

	public void setReceAmt(BigDecimal receAmt) {
		this.receAmt = receAmt;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public String getInvAimName() {
		return invAimName;
	}

	public void setInvAimName(String invAimName) {
		this.invAimName = invAimName;
	}

	public String getSecAcctIdName() {
		return secAcctIdName;
	}

	public void setSecAcctIdName(String secAcctIdName) {
		this.secAcctIdName = secAcctIdName;
	}

	public String getRecType() {
		return recType;
	}

	public void setRecType(String recType) {
		this.recType = recType;
	}

	public String getHodingType() {
		return hodingType;
	}

	public void setHodingType(String hodingType) {
		this.hodingType = hodingType;
	}
	
}
