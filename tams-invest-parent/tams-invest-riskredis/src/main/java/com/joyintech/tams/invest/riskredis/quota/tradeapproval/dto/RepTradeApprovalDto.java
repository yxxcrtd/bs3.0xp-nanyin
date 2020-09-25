package com.joyintech.tams.invest.riskredis.quota.tradeapproval.dto;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.RskOccupyInfo;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * @program: tams-invest-parent
 * @description: RepTradeApprovalDto
 * @author: liqianqian
 * @create: 2019-09-18 14:30
 **/
@PageId("RepTradeApproval002")
public class RepTradeApprovalDto extends RskOccupyInfo implements ITableRow,ProcessEntity{
    /**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 限额指标
     */
    private String indName;
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
     * 联合监控状态
     */
    private String uniteSupStat;
    
    /**
     * 交易日
     */
	private Date eventDate;
	
	/**
     * 交割日
     */
	private Date deliveryDate;
	
	/**
	 * 资产名称
	 */
	private String assetName;
	
	/**
	  * 到期收益率%
	  */
	 private String yieldMaturity;
	 /**
	  * 试算结果
	  */
	 private String conclusion;
	 /**
	  * 交易员
	  */
	 private String trader;
	 /**
	  * 投资经理
	  */
	 private String manager;
	 /**
	  * 业务编号
	  */
	 private String tradeId;
	 /**
	  * 业务类型
	  */
	 private String tradeType;
	 /**
	  * 阻挡类型
	  */
	 private String stopType;
	 /**
	  * 创建部门
	  */
	 private String createDept;
	 /**
	  * 产品代码
	  */
	 private String portfolioId;
	 /**
	  * 监督指标编号
	  */
	 private String indId;
	/**
	 * 监督日期
	 */
	private Date supDate;
	 
	/**
	 * 按钮ID - 工作流参数
	 */
	private String elementId;
	/**
	 * 画面Id - 工作流参数
	 */
	private String pageId;
	/**
    * 选择数据
    */
   private List<RepTradeApprovalDto> selectedItem;
   
   private String operateType;
    


    /**
     * 取得指标类型
     * @return 指标类型
     */
    public String getIndType() {
        return indType;
    }

    /**
     * 设置 指标类型
     * @param indType 指标类型
     */
    public void setIndType(String indType) {
        this.indType = indType;
    }

    /**
     * 取得预警线
     * @return 预警线
     */
    public String getWarnCond() {
		return warnCond;
	}

    /**
     * 设置 预警线
     * @param warnCond 预警线
     */
	public void setWarnCond(String warnCond) {
		this.warnCond = warnCond;
	}

	/**
     * 取得审批线
     * @return 审批线
     */
	public String getApproveCond() {
		return approveCond;
	}

	 /**
     * 设置 审批线
     * @param approveCond 审批线
     */
	public void setApproveCond(String approveCond) {
		this.approveCond = approveCond;
	}

	/**
     * 取得 禁止线
     * @return 禁止线
     */
	public String getFoulCond() {
		return foulCond;
	}

	/**
     * 设置 禁止线
     * @param foulCond 禁止线
     */
	public void setFoulCond(String foulCond) {
		this.foulCond = foulCond;
	}

	/**
     * 取得 当前实际值
     * @return 当前实际值
     */
	public String getSupVal() {
		return supVal;
	}

	/**
     * 设置 当前实际值
     * @param supVal 当前实际值
     */
	public void setSupVal(String supVal) {
		this.supVal = supVal;
	}

	/**
     * 取得 状态
     * @return 状态
     */
	public String getSupStat() {
		return supStat;
	}

	/**
     * 设置 状态
     * @param supStat 状态
     */
	public void setSupStat(String supStat) {
		this.supStat = supStat;
	}

	/**
     * 取得 联合监控状态
     * @return 联合监控状态
     */
	public String getUniteSupStat() {
		return uniteSupStat;
	}

	/**
     * 设置 联合监控状态
     * @param uniteSupStat 联合监控状态
     */
	public void setUniteSupStat(String uniteSupStat) {
		this.uniteSupStat = uniteSupStat;
	}
	
	/**
     * 取得 到期收益率%
     * @return 到期收益率%
     */
	public String getYieldMaturity() {
		return yieldMaturity;
	}

	/**
     * 设置 到期收益率%
     * @param yieldMaturity 到期收益率%
     */
	public void setYieldMaturity(String yieldMaturity) {
		this.yieldMaturity = yieldMaturity;
	}

	/**
     * 取得 试算结果
     * @return 试算结果
     */
	public String getConclusion() {
		return conclusion;
	}

	/**
     * 设置 利率%
     * @param conclusion 试算结果
     */
	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	/**
     * 取得 按钮ID - 工作流参数
     * @return 按钮ID - 工作流参数
     */
	public String getElementId() {
		return elementId;
	}

	/**
     * 设置 按钮ID - 工作流参数
     * @param elementId 按钮ID - 工作流参数
     */
	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	/**
     * 取得 画面Id - 工作流参数
     * @return 画面Id - 工作流参数
     */
	public String getPageId() {
		return pageId;
	}

	/**
     * 设置 画面Id - 工作流参数
     * @param pageId 画面Id - 工作流参数
     */
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}


    
	public List<RepTradeApprovalDto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<RepTradeApprovalDto> selectedItem) {
		this.selectedItem = selectedItem;
	}
	
	/**
     * 取得 交易日
     * @return 交易日
     */
	public Date getEventDate() {
		return eventDate;
	}

	/**
     * 设置 交易日
     * @param eventDate 交易日
     */
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	/**
     * 取得 交割日
     * @return 交割日
     */
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	/**
     * 设置 交割日
     * @param deliveryDate 交割日
     */
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	

	
	/**
     * 取得 资产名称
     * @return 资产名称
     */
	public String getAssetName() {
		return assetName;
	}

	/**
     * 设置 资产名称
     * @param assetName 资产名称
     */
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	
	/**
	 * 交易场所
	 */
	private String market;

	/**
	 * 取得 交易场所
	 * 
	 * @return 交易场所
	 */
	public String getMarket() {
		return market;
	}

	/**
	 * 设置 交易场所
	 * 
	 * @param market 交易场所
	 */
	public void setMarket(String market) {
		this.market = market;
	}

	/**
	 * 取得 交易员
	 * 
	 * @return 交易员
	 */
	public String getTrader() {
		return trader;
	}

	/**
	 * 设置 交易员
	 * 
	 * @param trader 交易员
	 */
	public void setTrader(String trader) {
		this.trader = trader;
	}

	/**
	 * 取得 投资经理
	 * 
	 * @return 投资经理
	 */
	public String getManager() {
		return manager;
	}

	/**
	 * 设置 投资经理
	 * 
	 * @param manager 投资经理
	 */
	public void setManager(String manager) {
		this.manager = manager;
	}
	
	/**
	 * 取得 业务编号
	 * 
	 * @return 业务编号
	 */
	public String getTradeId() {
		return tradeId;
	}

	/**
	 * 设置 业务编号
	 * 
	 * @param tradeId 业务编号
	 */
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	/**
	 * 取得 业务类型
	 * 
	 * @return 业务类型
	 */
	public String getTradeType() {
		return tradeType;
	}

	/**
	 * 设置 业务类型
	 * 
	 * @param tradeType 业务类型
	 */
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	/**
	 * 取得 阻挡类型
	 * 
	 * @return 阻挡类型
	 */
	public String getStopType() {
		return stopType;
	}

	/**
	 * 设置 阻挡类型
	 * 
	 * @param stopType 阻挡类型
	 */
	public void setStopType(String stopType) {
		this.stopType = stopType;
	}
	
	/**
	 * 取得 创建部门
	 * 
	 * @return 创建部门
	 */
	public String getCreateDept() {
		return createDept;
	}

	/**
	 * 设置 创建部门
	 * 
	 * @param createDept 创建部门
	 */
	public void setCreateDept(String createDept) {
		this.createDept = createDept;
	}
	
  @Override
  @PrimaryKey(Field.PK_1)
  public String getEventId(){
	   return super.getEventId();
  }

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getIndId() {
		return indId;
	}

	public void setIndId(String indId) {
		this.indId = indId;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getIndName() {
		return indName;
	}

	public void setIndName(String indName) {
		this.indName = indName;
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

	public Date getSupDate() {
		return supDate;
	}

	public void setSupDate(Date supDate) {
		this.supDate = supDate;
	}

}
