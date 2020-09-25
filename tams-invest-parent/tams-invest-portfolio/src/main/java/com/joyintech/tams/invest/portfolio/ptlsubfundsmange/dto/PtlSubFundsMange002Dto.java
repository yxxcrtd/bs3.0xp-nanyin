/*
 * 文件名：PtlSubFundsMange002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangyf
 * 修改时间：2019年8月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlsubfundsmange.dto;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.portfolio.ptlsubfundsmange.validator.PtlSubFundsMange002Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

@ValidLogic(targetClass = PtlSubFundsMange002Validator.class, groups = { ValidateGroupEdit.class,
		ValidateGroupDelete.class })
public class PtlSubFundsMange002Dto extends TrdProductDeal implements ProcessEntity{

	private static final long serialVersionUID = 1L;

	/**
	 * 有效状态
	 */
	private String statusValid;
	
	/**
	 * 画面数据状态 - DictCode.画面数据状态
	 */
	private String pageDataStatus;
	
	/**
     * 汇总交易编号
     */
	@PrimaryKey(Field.PK_1)
    private String totleTradeId;

	/**
	 * 业务类型
	 * @return
	 */
	@PrimaryKey(Field.PK_2)
	private String getPinanBusinessType() {
		return DictCodeList.共通业务类型_产品交易资金分配.getValue();
	}
	/**
     * 理财产品代码
     */
    private String prodId;
    
	/**
     * 投资组合代码
     */
    private String portfolioId;
    
//    /**
//     * 交易编号
//     */
//    private String tradeId;
	
    /**
     * 子组合代码
     */
    private String portfolioIdSub;
    
    /**
	 * 子组合名称
	 */
	private String portfolioSubName;
    
	/**
	 * 子组合资金分配明细List
	 */
	List<PtlSubFundsMange002Dto> portfolioDetailTable;
	
	/**
	 * 操作类型
	 */
	private String operateType;

	/**
	 * 页面类型
	 */
	private String pageModel;

	/**
     * 界面ID
     */
    private String elementId;
    
	/**
	 * 流水号
	 */
	private String seqNo;

	/**
	 * 交易日期
	 */
	private String tradeDateStr;

	/**
	 * 金融产品全称
	 */
	private String finprodName;

	/**
	 * 比例
	 */
	private BigDecimal disPercent;

	/**
	 * 分配金额
	 */
	private BigDecimal tradeAmtfp;

	/**
	 * 分配前剩余现金
	 */
	private BigDecimal preSurplusCash;

	/**
	 * 流水号集合
	 */
	private List<String> seqNoList;
	
	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getPageModel() {
		return pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getTradeDateStr() {
		return tradeDateStr;
	}

	public void setTradeDateStr(String tradeDateStr) {
		this.tradeDateStr = tradeDateStr;
	}

	public String getFinprodName() {
		return finprodName;
	}

	public void setFinprodName(String finprodName) {
		this.finprodName = finprodName;
	}

	public String getPortfolioSubName() {
		return portfolioSubName;
	}

	public void setPortfolioSubName(String portfolioSubName) {
		this.portfolioSubName = portfolioSubName;
	}

	public BigDecimal getDisPercent() {
		return disPercent;
	}

	public void setDisPercent(BigDecimal disPercent) {
		this.disPercent = disPercent;
	}

	public BigDecimal getTradeAmtfp() {
		return tradeAmtfp;
	}

	public void setTradeAmtfp(BigDecimal tradeAmtfp) {
		this.tradeAmtfp = tradeAmtfp;
	}

	public BigDecimal getPreSurplusCash() {
		return preSurplusCash;
	}

	public void setPreSurplusCash(BigDecimal preSurplusCash) {
		this.preSurplusCash = preSurplusCash;
	}

	public List<PtlSubFundsMange002Dto> getPortfolioDetailTable() {
		return portfolioDetailTable;
	}

	public void setPortfolioDetailTable(List<PtlSubFundsMange002Dto> portfolioDetailTable) {
		this.portfolioDetailTable = portfolioDetailTable;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String getTotleTradeId() {
		return totleTradeId;
	}

	public void setTotleTradeId(String totleTradeId) {
		this.totleTradeId = totleTradeId;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getPortfolioIdSub() {
		return portfolioIdSub;
	}

	public void setPortfolioIdSub(String portfolioIdSub) {
		this.portfolioIdSub = portfolioIdSub;
	}

//	public String getTradeId() {
//		return tradeId;
//	}
//
//	public void setTradeId(String tradeId) {
//		this.tradeId = tradeId;
//	}

	public List<String> getSeqNoList() {
		return seqNoList;
	}

	public void setSeqNoList(List<String> seqNoList) {
		this.seqNoList = seqNoList;
	}

	public String getStatusValid() {
		return statusValid;
	}

	public void setStatusValid(String statusValid) {
		this.statusValid = statusValid;
	}

	public String getPageDataStatus() {
		return pageDataStatus;
	}

	public void setPageDataStatus(String pageDataStatus) {
		this.pageDataStatus = pageDataStatus;
	}

}
