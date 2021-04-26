package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.io.Serializable;
import java.math.BigDecimal;

public class PrdOwnEquityChan extends BaseEntity implements Serializable {
    /**
     * 真实产品编码
     */
    private String realPrdId;

    /**
     * 指标项编号
     */
    private String subjectId;

    /**
     * 指标名称
     */
    private String subjectName;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 实收基金
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal paidFund;

    /**
     * 未分配利润
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal undistributProfits;

    /**
     * 所有者权益合计
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal totalOwnerEquity;

    /**
     * 业务日期
     */
    private String busiDate;

    /**
    
     */
    private static final long serialVersionUID = 1L;

    public PrdOwnEquityChan(String realPrdId, String subjectId, String subjectName, String ccy, BigDecimal paidFund, BigDecimal undistributProfits, BigDecimal totalOwnerEquity, String busiDate) {
        this.realPrdId = realPrdId;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.ccy = ccy;
        this.paidFund = paidFund;
        this.undistributProfits = undistributProfits;
        this.totalOwnerEquity = totalOwnerEquity;
        this.busiDate = busiDate;
    }

    public PrdOwnEquityChan() {
        super();
    }

    /**
     * 获取 真实产品编码
     */
    public String getRealPrdId() {
        return realPrdId;
    }

    /**
     * 设置 真实产品编码
     */
    public void setRealPrdId(String realPrdId) {
        this.realPrdId = realPrdId == null ? null : realPrdId.trim();
    }

    /**
     * 获取 指标项编号
     */
    public String getSubjectId() {
        return subjectId;
    }

    /**
     * 设置 指标项编号
     */
    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId == null ? null : subjectId.trim();
    }

    /**
     * 获取 指标名称
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * 设置 指标名称
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName == null ? null : subjectName.trim();
    }

    /**
     * 获取 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 获取 实收基金
     */
    public BigDecimal getPaidFund() {
        return paidFund;
    }

    /**
     * 设置 实收基金
     */
    public void setPaidFund(BigDecimal paidFund) {
        this.paidFund = paidFund;
    }

    /**
     * 获取 未分配利润
     */
    public BigDecimal getUndistributProfits() {
        return undistributProfits;
    }

    /**
     * 设置 未分配利润
     */
    public void setUndistributProfits(BigDecimal undistributProfits) {
        this.undistributProfits = undistributProfits;
    }

    /**
     * 获取 所有者权益合计
     */
    public BigDecimal getTotalOwnerEquity() {
        return totalOwnerEquity;
    }

    /**
     * 设置 所有者权益合计
     */
    public void setTotalOwnerEquity(BigDecimal totalOwnerEquity) {
        this.totalOwnerEquity = totalOwnerEquity;
    }

    /**
     * 获取 业务日期
     */
    public String getBusiDate() {
        return busiDate;
    }

    /**
     * 设置 业务日期
     */
    public void setBusiDate(String busiDate) {
        this.busiDate = busiDate == null ? null : busiDate.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PrdOwnEquityChan other = (PrdOwnEquityChan) that;
        return (this.getRealPrdId() == null ? other.getRealPrdId() == null : this.getRealPrdId().equals(other.getRealPrdId()))
            && (this.getSubjectId() == null ? other.getSubjectId() == null : this.getSubjectId().equals(other.getSubjectId()))
            && (this.getSubjectName() == null ? other.getSubjectName() == null : this.getSubjectName().equals(other.getSubjectName()))
            && (this.getCcy() == null ? other.getCcy() == null : this.getCcy().equals(other.getCcy()))
            && (this.getPaidFund() == null ? other.getPaidFund() == null : this.getPaidFund().equals(other.getPaidFund()))
            && (this.getUndistributProfits() == null ? other.getUndistributProfits() == null : this.getUndistributProfits().equals(other.getUndistributProfits()))
            && (this.getTotalOwnerEquity() == null ? other.getTotalOwnerEquity() == null : this.getTotalOwnerEquity().equals(other.getTotalOwnerEquity()))
            && (this.getBusiDate() == null ? other.getBusiDate() == null : this.getBusiDate().equals(other.getBusiDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRealPrdId() == null) ? 0 : getRealPrdId().hashCode());
        result = prime * result + ((getSubjectId() == null) ? 0 : getSubjectId().hashCode());
        result = prime * result + ((getSubjectName() == null) ? 0 : getSubjectName().hashCode());
        result = prime * result + ((getCcy() == null) ? 0 : getCcy().hashCode());
        result = prime * result + ((getPaidFund() == null) ? 0 : getPaidFund().hashCode());
        result = prime * result + ((getUndistributProfits() == null) ? 0 : getUndistributProfits().hashCode());
        result = prime * result + ((getTotalOwnerEquity() == null) ? 0 : getTotalOwnerEquity().hashCode());
        result = prime * result + ((getBusiDate() == null) ? 0 : getBusiDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", realPrdId=").append(realPrdId);
        sb.append(", subjectId=").append(subjectId);
        sb.append(", subjectName=").append(subjectName);
        sb.append(", ccy=").append(ccy);
        sb.append(", paidFund=").append(paidFund);
        sb.append(", undistributProfits=").append(undistributProfits);
        sb.append(", totalOwnerEquity=").append(totalOwnerEquity);
        sb.append(", busiDate=").append(busiDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}