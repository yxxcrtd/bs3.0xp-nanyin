/**
 * 
 */
package com.joyintech.tams.invest.product.productduration.expire.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.NotEmpty;


/**
 * @author zhangliang
 *
 */
public class FinProductExpireBranchInfoDto extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = -1106326967786115463L;
    
    /**
     * 分行代码
     */
    @NotEmpty(fieldName = "FinProductExpire002.branchInfo.branchName")
    private String branchCode;
    
    /**
     * 分行名称
     */
    private String branchName;
    
    /**
     * 开始日期
     */
    private String startDate;
    
    /**
     * 结束日期
     */
    private String endDate;
    
    /**
     * 本金金额
     */
    @Amount(fieldName = "FinProductExpire002.branchInfo.prinamt")
    @NotEmpty(fieldName = "FinProductExpire002.branchInfo.prinamt")
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prinAmt;
    
    /**
     * 应付收益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal profitAmt;
    
    /**
     * 实付收益
     */
    @Amount(fieldName = "FinProductExpire002.branchInfo.actProfitAmt")
    @NotEmpty(fieldName = "FinProductExpire002.branchInfo.actProfitAmt")
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal actProfitAmt;
    
    /**
     * 应付销售费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal saleFeeAmt;
    
    /**
     * 实付销售费
     */
    @Amount(fieldName = "FinProductExpire002.branchInfo.actSaleAmt")
    @NotEmpty(fieldName = "FinProductExpire002.branchInfo.actSaleAmt")
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal actSaleFeeAmt;
    
    /**
     * 应付代销费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal oSaleFeeAmt;
    
    /**
     * 实付代销费
     */
    @Amount(fieldName = "FinProductExpire002.branchInfo.actConAmt")
    @NotEmpty(fieldName = "FinProductExpire002.branchInfo.actConAmt")
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal actOSaleFeeAmt;
    
    /**
     * 产品收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prdYield;
    
    /**
     * 产品计息基础
     */
    private String prdBasis;
    
    /**
     * 销售费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal saleRate;
    
    /**
     * 销售费计息基础
     */
    private String saleBasis;
    
    /**
     * 代销费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal oSaleRate;
    
    /**
     * 代销费计息基础
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal oSaleBasis;

    
    public String getBranchCode() {
        return branchCode;
    }

    
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    
    public String getBranchName() {
        return branchName;
    }

    
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    
    
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


    public BigDecimal getPrinAmt() {
        return prinAmt;
    }

    
    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }

    
    public BigDecimal getProfitAmt() {
        return profitAmt;
    }

    
    public void setProfitAmt(BigDecimal profitAmt) {
        this.profitAmt = profitAmt;
    }

    
    public BigDecimal getActProfitAmt() {
        return actProfitAmt;
    }

    
    public void setActProfitAmt(BigDecimal actProfitAmt) {
        this.actProfitAmt = actProfitAmt;
    }

    
    public BigDecimal getSaleFeeAmt() {
        return saleFeeAmt;
    }

    
    public void setSaleFeeAmt(BigDecimal saleFeeAmt) {
        this.saleFeeAmt = saleFeeAmt;
    }

    
    public BigDecimal getActSaleFeeAmt() {
        return actSaleFeeAmt;
    }

    
    public void setActSaleFeeAmt(BigDecimal actSaleFeeAmt) {
        this.actSaleFeeAmt = actSaleFeeAmt;
    }

    
    public BigDecimal getoSaleFeeAmt() {
        return oSaleFeeAmt;
    }

    
    public void setoSaleFeeAmt(BigDecimal oSaleFeeAmt) {
        this.oSaleFeeAmt = oSaleFeeAmt;
    }

    
    public BigDecimal getActOSaleFeeAmt() {
        return actOSaleFeeAmt;
    }

    
    public void setActOSaleFeeAmt(BigDecimal actOSaleFeeAmt) {
        this.actOSaleFeeAmt = actOSaleFeeAmt;
    }

    
    public BigDecimal getPrdYield() {
        return prdYield;
    }

    
    public void setPrdYield(BigDecimal prdYield) {
        this.prdYield = prdYield;
    }

    
    public String getPrdBasis() {
        return prdBasis;
    }

    
    public void setPrdBasis(String prdBasis) {
        this.prdBasis = prdBasis;
    }

    
    public BigDecimal getSaleRate() {
        return saleRate;
    }

    
    public void setSaleRate(BigDecimal saleRate) {
        this.saleRate = saleRate;
    }

    
    public String getSaleBasis() {
        return saleBasis;
    }

    
    public void setSaleBasis(String saleBasis) {
        this.saleBasis = saleBasis;
    }

    
    public BigDecimal getoSaleRate() {
        return oSaleRate;
    }

    
    public void setoSaleRate(BigDecimal oSaleRate) {
        this.oSaleRate = oSaleRate;
    }

    
    public BigDecimal getoSaleBasis() {
        return oSaleBasis;
    }

    
    public void setoSaleBasis(BigDecimal oSaleBasis) {
        this.oSaleBasis = oSaleBasis;
    }

}
