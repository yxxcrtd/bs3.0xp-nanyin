/**
 * 
 */
package com.joyintech.service.product.productduration.expire.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.MstFeeinfo;
import com.joyintech.framework.json.BigDecimalSerializer;


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
    private BigDecimal feeAmt;
    
    /**
     * 实付费率
     */
    private BigDecimal actFeeRate;
    
    /**
     * 实付费用
     */
    private BigDecimal actFeeAmt;
    
    /**
     * 备注
     */
    private String remark;

    
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

    
    public String getRemark() {
        return remark;
    }

    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    
}
