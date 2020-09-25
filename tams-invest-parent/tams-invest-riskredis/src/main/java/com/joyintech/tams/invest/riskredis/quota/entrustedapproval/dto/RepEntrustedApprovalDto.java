package com.joyintech.tams.invest.riskredis.quota.entrustedapproval.dto;


import java.util.Date;
import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.RskDimEntrustInfo;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 委托审批
 * @author zhengjunping
 * @version 1.0 2019年9月9日
 * @see RepEntrustedApprovalDto
 * @since 1.0  
 */
@PageId("RepEntrustedApproval002")
public class RepEntrustedApprovalDto  extends RskDimEntrustInfo implements ITableRow,ProcessEntity{

	public String getTradeType() {
		return tradeType;
	}

	public Date getSupDate() {
		return supDate;
	}

	public void setSupDate(Date supDate) {
		this.supDate = supDate;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getIndName() {
		return indName;
	}

	public void setIndName(String indName) {
		this.indName = indName;
	}

	public String getExpr() {
		return expr;
	}

	public void setExpr(String expr) {
		this.expr = expr;
	}

	public String getSupType() {
		return supType;
	}

	public void setSupType(String supType) {
		this.supType = supType;
	}

	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}
	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 指标类型
	 */
	private String indType;
	/**
	 * 预警线
	 */
	private String warnCond;
	/**
	 * 审批线
	 */
	private String approveCond;
	/**
	 * 禁止线
	 */
	private String foulCond;
	/**
	 * 当前实际值
	 */
	private String supVal;
	/**
	 * 状态
	 */
	private String supStat;
	/**
	 * 联合监督状态
	 */
	private String uniteSupStat;
	/**
	 * 资产名称
	 */
	private String finprodName;
	/**
	 * 到期收益率
	 */
	private String yieldMaturity;
	/**
	 * 试算结论
	 */
	private String conclusion;
	/**
	 * 业务编号
	 */
	private String tradeId;
	/**
	 * 产品代码
	 */
	private String portfolioId;
	/**
	 * 监督日期
	 */
	private Date supDate;
	/**
	 * 业务类型
	 */
	private String tradeType;
	/**
	 * 指标名称
	 */
	private String indName;
	/**
	 * 表达式
	 */
	private String expr;
	/**
	 * 监督类型
	 */
	private String supType;
	/**
	 * 是否启用
	 */
	private String isEnabled;
	/**
	 * 指标id
	 */
	private String indId;
	/**
	 * 事件类型
	 */
	private String eventType;
	/**
	 * 当前流程状态
	 */
	private String wflStatus;
	
	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getWflStatus() {
		return wflStatus;
	}

	public void setWflStatus(String wflStatus) {
		this.wflStatus = wflStatus;
	}

	public String getIndId() {
		return indId;
	}

	public void setIndId(String indId) {
		this.indId = indId;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	public String getYieldMaturity() {
		return yieldMaturity;
	}

	public void setYieldMaturity(String yieldMaturity) {
		this.yieldMaturity = yieldMaturity;
	}

	public String getFinprodName() {
		return finprodName;
	}

	public void setFinprodName(String finprodName) {
		this.finprodName = finprodName;
	}

	public String getIndType() {
		return indType;
	}

	public void setIndType(String indType) {
		this.indType = indType;
	}

	public String getWarnCond() {
		return warnCond;
	}

	public void setWarnCond(String warnCond) {
		this.warnCond = warnCond;
	}

	public String getApproveCond() {
		return approveCond;
	}

	public void setApproveCond(String approveCond) {
		this.approveCond = approveCond;
	}

	public String getFoulCond() {
		return foulCond;
	}

	public void setFoulCond(String foulCond) {
		this.foulCond = foulCond;
	}

	public String getSupVal() {
		return supVal;
	}

	public void setSupVal(String supVal) {
		this.supVal = supVal;
	}

	public String getSupStat() {
		return supStat;
	}

	public void setSupStat(String supStat) {
		this.supStat = supStat;
	}

	public String getUniteSupStat() {
		return uniteSupStat;
	}

	public void setUniteSupStat(String uniteSupStat) {
		this.uniteSupStat = uniteSupStat;
	}

	/**
     * 将监控状态对应的数据字典代码转换为数据字典值
     * @return 数据字典值
     */
    public String getSupStatAbbr(){
        return SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.监督状态, this.getSupStat());
    }
    
    /**
     * 将联合监控状态对应的数据字典代码转换为数据字典值
     * @return 数据字典值
     */
    public String getUniteSupStatAbbr(){
        return SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.监督状态, this.getUniteSupStat());
    }
    /**
     * 将交易方向对应的数据字典代码转换为数据字典值
     * @return 数据字典值
     */
    public String getPsAbbr(){
        return SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.交易方向, this.getPs());
    }
    
    /**
     * 将指标对应的数据字典代码转换为数据字典值
     * @return 数据字典值
     */
    public String getIndTypeAbbr(){
        return SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.指标类型, this.getIndType());
    }
    
    /**
     * 将交易场所对应的数据字典代码转换为数据字典值
     * @return 数据字典值
     */
    public String getMarketAbbr(){
        return SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.交易流通场所, this.getMarket());
    }
    /**
     * 选择数据
     */
    private List<RepEntrustedApprovalDto> selectedItem;
    
    public List<RepEntrustedApprovalDto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<RepEntrustedApprovalDto> selectedItem) {
		this.selectedItem = selectedItem;
	}
    
	
	@Override
	@PrimaryKey(Field.PK_1)
	public String getEventId() {
		return super.getEventId();
	}

	public void setEventId(String eventId) {
		super.setEventId(eventId);
	}
	/**
     * 按钮ID - 工作流参数
     */
    private String elementId;
    public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	/**
     * 画面Id - 工作流参数
     */
    private String pageId;
	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	
}
