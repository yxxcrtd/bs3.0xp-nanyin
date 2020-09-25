package com.joyintech.service.portfolio.ptlsubfundsmange.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author pancz
 *
 */
public class PtlSubFundsMange002Dto{
	/**
     * 交易方向，买入、卖出、收息、还本、申购、赎回等
     */
    private String ps;
    
	/**
     * 金融产品代码
     */
    private String finprodId;
	
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
    private String totleTradeId;

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
	 * 交易日期
	 */
	private Date tradeDate;
	
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
	 * 流程状态
	 */
	private String workStatus;

	/**
	 * 交易编号
	 */
	private String tradeId;

	/**
	 * 交易日
	 */
	private Date settleDate;

	/**
	 * 产品代码
	 */
	private String prodId;

	/**
	 * 产品名称
	 */
	private String prodName;

	/**
	 * 交易金额
	 */
	private BigDecimal tradeAmt;

	/**
	 * 子组合代码
	 */
	private String portfolioId;

	/**
	 * 组合代码
	 */
	private String otherPortfolioId;

	/**
	 * 子组合名称
	 */
	private String portfolioName;

	/**
	 * 分配金额
	 */
	private BigDecimal allotAmt;

	/**
	 * 币种
	 */
	private String ccy;

	/**
	 * 流水号
	 */
	private String seqNo;

	/**
	 * 流水号
	 */
	private String otherSeqNo;

	/**
	 * 流水号list
	 * 
	 * @return
	 */
	private List<String> seqNoList;

	/**
	 * 父节点
	 */
	private String parentInfo;

	/**
	 * 子节点
	 */
	private String childInfo;

	/**
	 * 是否有子节点
	 */
	private String hasChildren;

	/**
	 * 工作流代码
	 */
	private String workflowId;

	/**
     * 备注
     */
    private String remark;
    
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getTotleTradeId() {
		return totleTradeId;
	}

	public void setTotleTradeId(String totleTradeId) {
		this.totleTradeId = totleTradeId;
	}

	public String getPortfolioIdSub() {
		return portfolioIdSub;
	}

	public void setPortfolioIdSub(String portfolioIdSub) {
		this.portfolioIdSub = portfolioIdSub;
	}

	public String getPortfolioSubName() {
		return portfolioSubName;
	}

	public void setPortfolioSubName(String portfolioSubName) {
		this.portfolioSubName = portfolioSubName;
	}

	public List<PtlSubFundsMange002Dto> getPortfolioDetailTable() {
		return portfolioDetailTable;
	}

	public void setPortfolioDetailTable(List<PtlSubFundsMange002Dto> portfolioDetailTable) {
		this.portfolioDetailTable = portfolioDetailTable;
	}

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

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
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

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public Date getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(Date settleDate) {
		this.settleDate = settleDate;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public BigDecimal getTradeAmt() {
		return tradeAmt;
	}

	public void setTradeAmt(BigDecimal tradeAmt) {
		this.tradeAmt = tradeAmt;
	}

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getOtherPortfolioId() {
		return otherPortfolioId;
	}

	public void setOtherPortfolioId(String otherPortfolioId) {
		this.otherPortfolioId = otherPortfolioId;
	}

	public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	public BigDecimal getAllotAmt() {
		return allotAmt;
	}

	public void setAllotAmt(BigDecimal allotAmt) {
		this.allotAmt = allotAmt;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getOtherSeqNo() {
		return otherSeqNo;
	}

	public void setOtherSeqNo(String otherSeqNo) {
		this.otherSeqNo = otherSeqNo;
	}

	public List<String> getSeqNoList() {
		return seqNoList;
	}

	public void setSeqNoList(List<String> seqNoList) {
		this.seqNoList = seqNoList;
	}

	public String getParentInfo() {
		return parentInfo;
	}

	public void setParentInfo(String parentInfo) {
		this.parentInfo = parentInfo;
	}

	public String getChildInfo() {
		return childInfo;
	}

	public void setChildInfo(String childInfo) {
		this.childInfo = childInfo;
	}

	public String getHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(String hasChildren) {
		this.hasChildren = hasChildren;
	}

	public String getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
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

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

}
