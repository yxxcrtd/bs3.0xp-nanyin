package com.joyintech.tams.invest.product.feepay.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.product.feepay.validator.FinProductFeePay005Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;
/**
 * 产品费用批量支付确认
 * 
 * @author zcw
 * @version 1.0 2019年01月29日
 * @see FinProductFeePay005Dto
 * @since 1.0
 */
@PageId(value = "FinProductFeePay004")
@ValidLogic(targetClass = FinProductFeePay005Validator.class)
public class FinProductFeePay005Dto extends BaseEntity implements ProcessEntity {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -4387949980659620873L;
	
	/**
     * 操作类型
     */
    private String operateType;
    
    /**
     * 确认模式
     */
    private String confirmFlag;
    
    /**
     * 费用代码
     */
    private String feeId;
    
    /**
     * 费用名称
     */
    private String feeName;
    
    /**
     * 费用类型
     */
    private String feeType;
    
    /**
     * 交易编号
     */
    private String tradeId;
    
    /**
     * 支付日
     */
    @NotEmpty(fieldName = "FinProductFeePay004.payDate")
    private Date payDate;
    
    /**
     * 币种
     */
    private String ccy;
    
    /**
     * 按钮Id
     */
    private String elementId;
    
    /**
     * 理论金额合计
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal PayAmtSum;
    
    /**
     * 实付金额合计
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    @NotEmpty(fieldName = "FinProductFeePay004.actPayAmtSum")
    private BigDecimal ActPayAmtSum;
    
    /**
     * 调整金额合计
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal AdjustAmtSum;
    
    /**
     * 交易备注
     */
    private String remark;

    /**
     * 撤销说明
     */
    private String revokeReason;

    /**
     * 划款指令
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;

    /**
     * 附件上传
     */
    private List<UploadFile> fileArray;
    
    
    /**
     * 产品费用支付列表
     */
    private List<FinProductFeePay002Dto> finProductFeePay;
    /**
     * 产品收益类型
     */
    private String profitType;
    /**
     * 处理模式
     */
    private String dealMode;
    /**
     * 交易方向（风险接口使用）
     */
    private String ps;
    
    /**
	 * 组合剩余现金
	 */
	private BigDecimal proAvailableCash;

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getConfirmFlag() {
		return confirmFlag;
	}

	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}
	
	@PrimaryKey(Field.PK_1)
	public String getFeeId() {
		return feeId;
	}
	@PrimaryKey(Field.PK_2)
	public String getFinproduId(){
	    if(!StringUtil.isEmptyList(finProductFeePay)){
	        return finProductFeePay.get(0).getFinprodId();
	    }
        return "";
	}

	public void setFeeId(String feeId) {
		this.feeId = feeId;
	}

	public String getFeeName() {
		return feeName;
	}

	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}
	@PrimaryKey(Field.PK_3)
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

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public BigDecimal getPayAmtSum() {
		return PayAmtSum;
	}

	public void setPayAmtSum(BigDecimal payAmtSum) {
		PayAmtSum = payAmtSum;
	}

	public BigDecimal getActPayAmtSum() {
		return ActPayAmtSum;
	}

	public void setActPayAmtSum(BigDecimal actPayAmtSum) {
		ActPayAmtSum = actPayAmtSum;
	}

	public BigDecimal getAdjustAmtSum() {
		return AdjustAmtSum;
	}

	public void setAdjustAmtSum(BigDecimal adjustAmtSum) {
		AdjustAmtSum = adjustAmtSum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRevokeReason() {
		return revokeReason;
	}

	public void setRevokeReason(String revokeReason) {
		this.revokeReason = revokeReason;
	}

	public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
		return bussTrdTransferOrder;
	}

	public void setBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
		this.bussTrdTransferOrder = bussTrdTransferOrder;
	}

	public List<UploadFile> getFileArray() {
		return fileArray;
	}

	public void setFileArray(List<UploadFile> fileArray) {
		this.fileArray = fileArray;
	}

	public List<FinProductFeePay002Dto> getFinProductFeePay() {
		return finProductFeePay;
	}

	public void setFinProductFeePay(List<FinProductFeePay002Dto> finProductFeePay) {
		this.finProductFeePay = finProductFeePay;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getProfitType() {
		return profitType;
	}

	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}

	public String getDealMode() {
		return dealMode;
	}

	public void setDealMode(String dealMode) {
		this.dealMode = dealMode;
	}

	public BigDecimal getProAvailableCash() {
		return proAvailableCash;
	}

	public void setProAvailableCash(BigDecimal proAvailableCash) {
		this.proAvailableCash = proAvailableCash;
	}

    /**
     * 获取 ps
     * @return ps.
     */
    public String getPs() {
        return StringUtil.isNotEmpty(ps)?this.ps:DictCodeList.交易方向_产品付费.getValue();
    }

    /**
     * 设置 ps
     * @param ps ps
     */
    public void setPs(String ps) {
        this.ps = ps;
    }
}
