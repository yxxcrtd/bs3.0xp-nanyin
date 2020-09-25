package com.joyintech.tams.invest.product.resultscovering.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.product.resultscovering.validator.FinResultsCovering002Validator;
import com.joyintech.workflow.engine.dto.ProcessEntity;
@PageId(value = "FinResultsCovering001")
@ValidLogic(targetClass = FinResultsCovering002Validator.class)
public class FinResultsCovering001Dto extends BaseEntity implements ProcessEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	 * 交易编号
	 * @return
	 */
	private String tradeId;
	
	public Date getCoveringDate() {
		return coveringDate;
	}
	public void setCoveringDate(Date coveringDate) {
		this.coveringDate = coveringDate;
	}
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
	public String getCoveringWayStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.回补规则, coveringWay);
	}
	public void setCoveringWay(String coveringWay) {
		this.coveringWay = coveringWay;
	}
	public String getCoveringForm() {
		return coveringForm;
	}
	public String getCoveringFormStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.回补方式, coveringForm);
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
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	/**
	 * 回补周期开始日
	 */
	private Date covCycleStartDate;
	/**
	 * 回补周期结束日
	 */
	private Date covCycleEndDate;
	
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
    /**交易状态*/
    private String tradeStatus;
	public String getTradeStatus() {
		return tradeStatus;
	}
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	public String getTradeStatusStr(){
		String TradeStatusStr = "";
		if(StringUtil.isNotBlank(this.getTradeStatus())){
			TradeStatusStr= SysDataUtil.getNameFromDataDict(BussinessDictCode.有效状态, this.getTradeStatus());
		}
        return TradeStatusStr;
    }
	
	/** 交易币种 */
	private String ccy;
	public String getCcy() {
		return ccy;
	}
	public String getCcyStr() {
		return SysDataUtil.getNameFromDataDict(BussinessDictCode.币种,  this.getCcy());
	}
	public void setCcy(String ccy) {
		this.ccy = ccy;
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
  
}
