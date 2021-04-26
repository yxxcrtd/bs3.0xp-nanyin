package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;

public class PrdProfit extends BaseEntity implements Serializable {
    /**
     * 真实产品编码
     */
    private Integer realPrdId;

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
     * 本期金额
     */
    private BigDecimal currentAmt;

    /**
     * 本年累计金额
     */
    private BigDecimal currentYearAccumuAmt;

    /**
     * 业务日期
     */
    private String busiDate;

    /**
     * tianxincode@163.com
	 * 2021-04-25T10:45:52.990
     */
    private static final long serialVersionUID = 1L;

    public PrdProfit(Integer realPrdId, String subjectId, String subjectName, String ccy, BigDecimal currentAmt, BigDecimal currentYearAccumuAmt, String busiDate) {
        this.realPrdId = realPrdId;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.ccy = ccy;
        this.currentAmt = currentAmt;
        this.currentYearAccumuAmt = currentYearAccumuAmt;
        this.busiDate = busiDate;
    }

    public PrdProfit() {
        super();
    }

    /**
     * 获取 真实产品编码
     */
    public Integer getRealPrdId() {
        return realPrdId;
    }

    /**
     * 设置 真实产品编码
     */
    public void setRealPrdId(Integer realPrdId) {
        this.realPrdId = realPrdId;
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
     * 获取 本期金额
     */
    public BigDecimal getCurrentAmt() {
        return currentAmt;
    }

    /**
     * 设置 本期金额
     */
    public void setCurrentAmt(BigDecimal currentAmt) {
        this.currentAmt = currentAmt;
    }

    /**
     * 获取 本年累计金额
     */
    public BigDecimal getCurrentYearAccumuAmt() {
        return currentYearAccumuAmt;
    }

    /**
     * 设置 本年累计金额
     */
    public void setCurrentYearAccumuAmt(BigDecimal currentYearAccumuAmt) {
        this.currentYearAccumuAmt = currentYearAccumuAmt;
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
        this.busiDate = busiDate;
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
        PrdProfit other = (PrdProfit) that;
        return (this.getRealPrdId() == null ? other.getRealPrdId() == null : this.getRealPrdId().equals(other.getRealPrdId()))
            && (this.getSubjectId() == null ? other.getSubjectId() == null : this.getSubjectId().equals(other.getSubjectId()))
            && (this.getSubjectName() == null ? other.getSubjectName() == null : this.getSubjectName().equals(other.getSubjectName()))
            && (this.getCcy() == null ? other.getCcy() == null : this.getCcy().equals(other.getCcy()))
            && (this.getCurrentAmt() == null ? other.getCurrentAmt() == null : this.getCurrentAmt().equals(other.getCurrentAmt()))
            && (this.getCurrentYearAccumuAmt() == null ? other.getCurrentYearAccumuAmt() == null : this.getCurrentYearAccumuAmt().equals(other.getCurrentYearAccumuAmt()))
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
        result = prime * result + ((getCurrentAmt() == null) ? 0 : getCurrentAmt().hashCode());
        result = prime * result + ((getCurrentYearAccumuAmt() == null) ? 0 : getCurrentYearAccumuAmt().hashCode());
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
        sb.append(", currentAmt=").append(currentAmt);
        sb.append(", currentYearAccumuAmt=").append(currentYearAccumuAmt);
        sb.append(", busiDate=").append(busiDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}