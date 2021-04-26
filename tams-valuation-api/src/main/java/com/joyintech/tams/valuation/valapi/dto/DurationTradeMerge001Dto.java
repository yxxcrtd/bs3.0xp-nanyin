package com.joyintech.tams.valuation.valapi.dto;

import com.joyintech.tams.valuation.valapi.annotations.ValInterfApiField;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName DurationTradeMerge001Dto
 * @Description: 存续期交易合并dto
 * @Author yanya
 * @Date 2021/3/1 0001 
 * @Version V1.0
 **/
public class DurationTradeMerge001Dto implements Serializable {

    private static final long serialVersionUID = -9025638360726571350L;

    /**产品编码**/
    @ValInterfApiField
    private String prodId;
    /**产品类型**/
    @ValInterfApiField(fieldVal="F16,F22,F18")
    private String prodFinprodType;
    /**分支序号**/
    private Long branch;
    /**投管交易方向**/
    private String psType;
    /**费用类型**/
    private String feeType;
    /**是否随交易支付**/
    private String isPayWithTrade;
    /**费用金额**/
    private BigDecimal feeAmt;

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdFinprodType() {
        return prodFinprodType;
    }

    public void setProdFinprodType(String prodFinprodType) {
        this.prodFinprodType = prodFinprodType;
    }

    public Long getBranch() {
        return branch;
    }

    public void setBranch(Long branch) {
        this.branch = branch;
    }

    public String getPsType() {
        return psType;
    }

    public void setPsType(String psType) {
        this.psType = psType;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getIsPayWithTrade() {
        return isPayWithTrade;
    }

    public void setIsPayWithTrade(String isPayWithTrade) {
        this.isPayWithTrade = isPayWithTrade;
    }

    public BigDecimal getFeeAmt() {
        return feeAmt;
    }

    public void setFeeAmt(BigDecimal feeAmt) {
        this.feeAmt = feeAmt;
    }

    @Override
    public String toString() {
        return "DurationTradeMerge001Dto{" +
                "prodId='" + prodId + '\'' +
                ", prodFinprodType='" + prodFinprodType + '\'' +
                ", branch=" + branch +
                ", psType='" + psType + '\'' +
                ", feeType='" + feeType + '\'' +
                ", isPayWithTrade='" + isPayWithTrade + '\'' +
                ", feeAmt=" + feeAmt +
                '}';
    }
}
