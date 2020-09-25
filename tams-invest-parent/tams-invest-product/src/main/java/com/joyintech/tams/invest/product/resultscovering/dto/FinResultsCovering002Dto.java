package com.joyintech.tams.invest.product.resultscovering.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.invest.product.resultscovering.validator.FinResultsCovering002Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

@PageId(value = "FinResultsCovering002")
@ValidLogic(targetClass = FinResultsCovering002Validator.class)
public class FinResultsCovering002Dto extends BaseEntity implements ProcessEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String busiCode;
	
	/**
     * 元素Id
     */
    private String elementId;
	/**
	 * 回补日
	 */
	private Date coveringDate;
	/**
	 * 产品代码
	 */
	private String prodId;
	
    /**
     * 产品款型
     */
    private String prodStyle;
    
    /**
     * 分层子产品代码
     */
    private String layFinprodId;
    
    /**
     * 组合代码
     */
    private String portfolioId;
    
    /**
     * 是否分层
     */
    private String isLay;
    
	/**
	 * 产品简称
	 */
	private String prodAbbr;
	/**
	 * 回补方式
	 */
	private String coveringWay;
	/**
	 * 回补形式
	 */
	private String coveringForm;
	/**
	 * 有效状态
	 */
	private String effectStatus;
	/**
	 * 实际回补金额
	 */
	private BigDecimal actualAmount;
	/**
	 * 应回补金额
	 */
	private BigDecimal theoryAmount;
	/**
	 * 回补周期开始日
	 */
	private Date covCycleStartDate;
	/**
	 * 回补周期结束日
	 */
	private Date covCycleEndDate;
	/**
	 * 固定管理费回补金额
	 */
	private BigDecimal afterCovFixFee;
	/**
	 * 浮动管理费回补金额
	 */
	private BigDecimal afterCovFloatFee;
	/**
	 * 总实际收入
	 * @return
	 */
	private BigDecimal sumAmt;
	/**
	 * 交易编号
	 * @return
	 */
	private String tradeId;
	/**
	 * 工作流
	 * @return
	 */
	private String workflowId;
	/**
	 * 币种
	 * @return
	 */
	private String ccy;
	/**
     * 操作类型
     */
    private String operateType;
	
	@PrimaryKey(Field.PK_1)
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public String getBusiCode() {
		return busiCode;
	}
	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}
	public Date getCoveringDate() {
		return coveringDate;
	}
	public void setCoveringDate(Date coveringDate) {
		this.coveringDate = coveringDate;
	}
	@PrimaryKey(Field.PK_2)
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getProdAbbr() {
		return prodAbbr;
	}
	public void setProdAbbr(String prodAbbr) {
		this.prodAbbr = prodAbbr;
	}
	public String getCoveringWay() {
		return coveringWay;
	}
	public void setCoveringWay(String coveringWay) {
		this.coveringWay = coveringWay;
	}
	public String getCoveringForm() {
		return coveringForm;
	}
	public void setCoveringForm(String coveringForm) {
		this.coveringForm = coveringForm;
	}
	public String getEffectStatus() {
		return effectStatus;
	}
	public void setEffectStatus(String effectStatus) {
		this.effectStatus = effectStatus;
	}
	public BigDecimal getActualAmount() {
		return actualAmount;
	}
	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}
	public BigDecimal getTheoryAmount() {
		return theoryAmount;
	}
	public void setTheoryAmount(BigDecimal theoryAmount) {
		this.theoryAmount = theoryAmount;
	}
	public Date getCovCycleStartDate() {
		return covCycleStartDate;
	}
	public void setCovCycleStartDate(Date covCycleStartDate) {
		this.covCycleStartDate = covCycleStartDate;
	}
	public Date getCovCycleEndDate() {
		return covCycleEndDate;
	}
	public void setCovCycleEndDate(Date covCycleEndDate) {
		this.covCycleEndDate = covCycleEndDate;
	}
	public BigDecimal getAfterCovFixFee() {
		return afterCovFixFee;
	}
	public void setAfterCovFixFee(BigDecimal afterCovFixFee) {
		this.afterCovFixFee = afterCovFixFee;
	}
	public BigDecimal getAfterCovFloatFee() {
		return afterCovFloatFee;
	}
	public void setAfterCovFloatFee(BigDecimal afterCovFloatFee) {
		this.afterCovFloatFee = afterCovFloatFee;
	}
	public String getElementId() {
		return elementId;
	}
	public void setElementId(String elementId) {
		this.elementId = elementId;
	}
	public BigDecimal getSumAmt() {
		return sumAmt;
	}
	public void setSumAmt(BigDecimal sumAmt) {
		this.sumAmt = sumAmt;
	}
	public String getWorkflowId() {
		return workflowId;
	}
	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}
	public String getCcy() {
		return ccy;
	}
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
    /**
     * 获取 prodStyle
     * @return prodStyle.
     */
    public String getProdStyle() {
        return prodStyle;
    }
    /**
     * 设置 prodStyle
     * @param prodStyle prodStyle
     */
    public void setProdStyle(String prodStyle) {
        this.prodStyle = prodStyle;
    }
    /**
     * 获取 isLay
     * @return isLay.
     */
    public String getIsLay() {
        return isLay;
    }
    /**
     * 设置 isLay
     * @param isLay isLay
     */
    public void setIsLay(String isLay) {
        this.isLay = isLay;
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
     * 获取 layFinprodId
     * @return layFinprodId.
     */
    public String getLayFinprodId() {
        return layFinprodId;
    }
    /**
     * 设置 layFinprodId
     * @param layFinprodId layFinprodId
     */
    public void setLayFinprodId(String layFinprodId) {
        this.layFinprodId = layFinprodId;
    }
	
}
