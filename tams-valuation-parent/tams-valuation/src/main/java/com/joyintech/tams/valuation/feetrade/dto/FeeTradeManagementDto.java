package com.joyintech.tams.valuation.feetrade.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.feetrade.validator.FeeTradeManagementValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 费用交易管理dto
 * @version 1.0 20200120
 * @see FeeTradeManagementDto
 * @since 1.0
 */
@ValidLogic(targetClass = FeeTradeManagementValidator.class, groups = {ValidateGroupEdit.class,
	    ValidateGroupDelete.class})
public class FeeTradeManagementDto extends BaseEntity implements ProcessEntity{

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
     * 费用类型
     */
    private String feeTypeStr;
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
     * 账套代码
     */
    private String bookSetId;
    /**
     * 账套名称
     */
    private String bookSetName;
    /**
     * 子账套代码
     */
    private String subBookSetId;
    /**
     * 子账套名称
     */
    private String subBookSetName;
    /**
     * 计费截止日
     */
    private Date feeEndDate;
	/**
     * 计费截止日展示 计费截止日 - 1
     */
    private Date feeEndDateDis;
    /**
     * 理论费用
     */
    private BigDecimal shouldPayAmt;
    /**
     * 实付金额
     */
    private BigDecimal payAmt;
    /**
     * 是否结清
     */
    private String isCountOver;
    /**
     * 是否结清
     */
    private String isCountOverStr;
    /**
     * 是否待摊
     */
    private String isNeedTan;
    /**
     * 是否待摊
     */
    private String isNeedTanStr;
    
    @PrimaryKey(PrimaryKey.Field.PK_1)
    private String tradeId;
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
	public Date getFeeEndDate() {
		return feeEndDate;
	}
	public void setFeeEndDate(Date feeEndDate) {
		this.feeEndDate = feeEndDate;
	}
	public Date getFeeEndDateDis() {
		return StringUtil.isNotEmpty(feeEndDate) ? DateUtil.addDay(feeEndDate, -1) : feeEndDateDis;
	}
	public BigDecimal getShouldPayAmt() {
		return shouldPayAmt;
	}
	public void setShouldPayAmt(BigDecimal shouldPayAmt) {
		this.shouldPayAmt = shouldPayAmt;
	}
	public BigDecimal getPayAmt() {
		return payAmt;
	}
	public void setPayAmt(BigDecimal payAmt) {
		this.payAmt = payAmt;
	}
	public String getIsCountOver() {
		return isCountOver;
	}
	public void setIsCountOver(String isCountOver) {
		this.isCountOver = isCountOver;
	}
	public String getIsNeedTan() {
		return isNeedTan;
	}
	public void setIsNeedTan(String isNeedTan) {
		this.isNeedTan = isNeedTan;
	}
	public String getBookSetId() {
		return bookSetId;
	}
	public void setBookSetId(String bookSetId) {
		this.bookSetId = bookSetId;
	}
	public String getBookSetName() {
		return bookSetName;
	}
	public void setBookSetName(String bookSetName) {
		this.bookSetName = bookSetName;
	}
	public String getSubBookSetId() {
		return subBookSetId;
	}
	public void setSubBookSetId(String subBookSetId) {
		this.subBookSetId = subBookSetId;
	}
	public String getSubBookSetName() {
		return subBookSetName;
	}
	public void setSubBookSetName(String subBookSetName) {
		this.subBookSetName = subBookSetName;
	}
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public String getFeeTypeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.现金流二级类型, this.getFeeType()) != null 
				? SysDataUtil.getNameFromDataDict(DictCode.现金流二级类型, this.getFeeType()) : 
					SysDataUtil.getNameFromDataDict(DictCode.一次性费用类型, this.getFeeType());
	}
	public String getIsCountOverStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.是否, this.getIsCountOver());
	}
	public String getIsNeedTanStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.是否, this.getIsNeedTan());
	}
	
}
