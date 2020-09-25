/**
 * 
 */
package com.joyintech.tams.invest.product.productduration.expire.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.MstFeeinfo;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.tams.constant.DictCode;


/**
 * 费用信息
 * @author zhangliang
 *
 */
public class FinProductExpireFeeInfoDto extends MstFeeinfo {

    /**
     * 
     */
    private static final long serialVersionUID = -4383246639189522862L;

    /**
     * 开始日
     */
    private String startDate;
    
    /**
     * 结束日
     */
    private String endDate;
    
    /**
     * 本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prinamt;
    
    /**
     * 计息基础
     */
    private String basis;
    
    /**
     * 费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal feeRate;
    
    /**
     * 费用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal feeAmt;
    
    /**
     * 实付费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal actFeeRate;
    
    /**
     * 实付费用
     */
    @Amount(fieldName = "FinProductExpire002.feeInfo.actFeeAmt")
    @NotEmpty(fieldName = "FinProductExpire002.feeInfo.actFeeAmt")
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal actFeeAmt;

    
    public String getStartDate() {
        return startDate;
    }

    
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    
    public String getEndDate() {
        return endDate;
    }

    
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    
    public BigDecimal getPrinamt() {
        return prinamt;
    }

    
    public void setPrinamt(BigDecimal prinamt) {
        this.prinamt = prinamt;
    }

    
    public String getBasis() {
        return basis;
    }

    
    public void setBasis(String basis) {
        this.basis = basis;
    }

    
    public BigDecimal getFeeRate() {
        return feeRate;
    }

    
    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }

    
    public BigDecimal getFeeAmt() {
        return feeAmt;
    }

    
    public void setFeeAmt(BigDecimal feeAmt) {
        this.feeAmt = feeAmt;
    }

    
    public BigDecimal getActFeeRate() {
        return actFeeRate;
    }

    
    public void setActFeeRate(BigDecimal actFeeRate) {
        this.actFeeRate = actFeeRate;
    }

    
    public BigDecimal getActFeeAmt() {
        return actFeeAmt;
    }

    
    public void setActFeeAmt(BigDecimal actFeeAmt) {
        this.actFeeAmt = actFeeAmt;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Length(max = 200)
    public String getRemark() {
        return super.getRemark();
    }
    
    /**
     * {@inheritDoc}
     */
    public String getFeeTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.费用品种类型, super.getFeeType());
    }
}
