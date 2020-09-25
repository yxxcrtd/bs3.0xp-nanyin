package com.joyintech.service.valuation.feetrade.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 费用交易管理dto
 * @version 1.0 20200120
 * @see FeeTradeManagementDto
 * @since 1.0
 */
public class FeeTradeManagementDto extends FeeTradeDetailDto{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -8291666227730929705L;
    /**
     * 操作类型
     */
    private String operateType;
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String elementId;
    /**
     * 费用类型
     */
    private String feeType;
    /**
     * 支付日期
     */
    private Date payDate;
    /**
     * 登记托管机构
     */
    private String registOrg;
    /**
     * 费用金额总计
     */
    private BigDecimal feeAmtTotal;
    /**
     * 理论金额合计
     */
    private BigDecimal shouldAmtTotal;
    /**
     * 费用交易明细列表
     */
    private List<FeeTradeDetailDto> feeTradeList;
    
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
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public String getRegistOrg() {
		return registOrg;
	}
	public void setRegistOrg(String registOrg) {
		this.registOrg = registOrg;
	}
	public BigDecimal getFeeAmtTotal() {
		return feeAmtTotal;
	}
	public void setFeeAmtTotal(BigDecimal feeAmtTotal) {
		this.feeAmtTotal = feeAmtTotal;
	}
	public BigDecimal getShouldAmtTotal() {
		return shouldAmtTotal;
	}
	public void setShouldAmtTotal(BigDecimal shouldAmtTotal) {
		this.shouldAmtTotal = shouldAmtTotal;
	}
	public List<FeeTradeDetailDto> getFeeTradeList() {
		return feeTradeList;
	}
	public void setFeeTradeList(List<FeeTradeDetailDto> feeTradeList) {
		this.feeTradeList = feeTradeList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
