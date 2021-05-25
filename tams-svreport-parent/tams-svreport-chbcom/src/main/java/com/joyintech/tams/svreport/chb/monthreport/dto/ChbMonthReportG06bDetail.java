package com.joyintech.tams.svreport.chb.monthreport.dto;

import com.joyintech.framework.base.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Objects;

public class ChbMonthReportG06bDetail extends BaseEntity implements Serializable {
    /**
     * 
     */
    private String detailuuid;

    /**
     * 期数
     */
    private String termid;

    /**
     * 报表类型：PROD-产品，BALANCE-资产
     */
    private String reporttype;

    /**
     * 属性代码
     */
    private String propertycode;

    /**
     * 上月月末期末余额
     */
    private BigDecimal balanceA;

    /**
     * 当月月末期末余额
     */
    private BigDecimal balanceB;

    /**
     * 上月月末期末余额-显示名称
     */
    private String balanceAFmt;

    /**
     * 当月月末期末余额-显示名称
     */
    private String balanceBFmt;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 月报主表主键
     */
    private String reportuuid;

    private static final long serialVersionUID = 1L;

    public ChbMonthReportG06bDetail(String detailuuid, String termid, String reporttype, String propertycode, BigDecimal balanceA, BigDecimal balanceB, String orgCode, String deptCode, String reportuuid) {
        this.detailuuid = detailuuid;
        this.termid = termid;
        this.reporttype = reporttype;
        this.propertycode = propertycode;
        this.balanceA = balanceA;
        this.balanceB = balanceB;
        this.orgCode = orgCode;
        this.deptCode = deptCode;
        this.reportuuid = reportuuid;
    }

    public ChbMonthReportG06bDetail() {
        super();
    }

    /**
     * 获取 
     */
    public String getDetailuuid() {
        return detailuuid;
    }

    /**
     * 设置 
     */
    public void setDetailuuid(String detailuuid) {
        this.detailuuid = detailuuid == null ? null : detailuuid.trim();
    }

    /**
     * 获取 期数
     */
    public String getTermid() {
        return termid;
    }

    /**
     * 设置 期数
     */
    public void setTermid(String termid) {
        this.termid = termid == null ? null : termid.trim();
    }

    /**
     * 获取 报表类型：PROD-产品，ASSET-资产
     */
    public String getReporttype() {
        return reporttype;
    }

    /**
     * 设置 报表类型：PROD-产品，ASSET-资产
     */
    public void setReporttype(String reporttype) {
        this.reporttype = reporttype == null ? null : reporttype.trim();
    }

    /**
     * 获取 属性代码
     */
    public String getPropertycode() {
        return propertycode;
    }

    /**
     * 设置 属性代码
     */
    public void setPropertycode(String propertycode) {
        this.propertycode = propertycode == null ? null : propertycode.trim();
    }

    /**
     * 获取 上月月末期末余额
     */
    public BigDecimal getBalanceA() {
        return balanceA;
    }

    /**
     * 设置 上月月末期末余额
     */
    public void setBalanceA(BigDecimal balanceA) {
        this.balanceA = balanceA;
    }

    /**
     * 获取 当月月末期末余额
     */
    public BigDecimal getBalanceB() {
        return balanceB;
    }

    /**
     * 设置 当月月末期末余额
     */
    public void setBalanceB(BigDecimal balanceB) {
        this.balanceB = balanceB;
    }

    public String getBalanceAFmt() {
        if (Objects.isNull(balanceA) || BigDecimal.ZERO.compareTo(balanceA) == 0) {
            return "0.00";
        }
        return new DecimalFormat("0.0000").format(balanceA);
    }

    public void setBalanceAFmt(String balanceAFmt) {
        this.balanceAFmt = balanceAFmt;
    }

    public String getBalanceBFmt() {
        if (Objects.isNull(balanceB) || BigDecimal.ZERO.compareTo(balanceB) == 0) {
            return "0.00";
        }
        return new DecimalFormat("0.00").format(balanceB);
    }

    public void setBalanceBFmt(String balanceBFmt) {
        this.balanceBFmt = balanceBFmt;
    }

    /**
     * 获取 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 获取 部门代码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置 部门代码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * 获取 月报主表主键
     */
    public String getReportuuid() {
        return reportuuid;
    }

    /**
     * 设置 月报主表主键
     */
    public void setReportuuid(String reportuuid) {
        this.reportuuid = reportuuid == null ? null : reportuuid.trim();
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
        ChbMonthReportG06bDetail other = (ChbMonthReportG06bDetail) that;
        return (this.getDetailuuid() == null ? other.getDetailuuid() == null : this.getDetailuuid().equals(other.getDetailuuid()))
            && (this.getTermid() == null ? other.getTermid() == null : this.getTermid().equals(other.getTermid()))
            && (this.getReporttype() == null ? other.getReporttype() == null : this.getReporttype().equals(other.getReporttype()))
            && (this.getPropertycode() == null ? other.getPropertycode() == null : this.getPropertycode().equals(other.getPropertycode()))
            && (this.getBalanceA() == null ? other.getBalanceA() == null : this.getBalanceA().equals(other.getBalanceA()))
            && (this.getBalanceB() == null ? other.getBalanceB() == null : this.getBalanceB().equals(other.getBalanceB()))
            && (this.getOrgCode() == null ? other.getOrgCode() == null : this.getOrgCode().equals(other.getOrgCode()))
            && (this.getDeptCode() == null ? other.getDeptCode() == null : this.getDeptCode().equals(other.getDeptCode()))
            && (this.getReportuuid() == null ? other.getReportuuid() == null : this.getReportuuid().equals(other.getReportuuid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDetailuuid() == null) ? 0 : getDetailuuid().hashCode());
        result = prime * result + ((getTermid() == null) ? 0 : getTermid().hashCode());
        result = prime * result + ((getReporttype() == null) ? 0 : getReporttype().hashCode());
        result = prime * result + ((getPropertycode() == null) ? 0 : getPropertycode().hashCode());
        result = prime * result + ((getBalanceA() == null) ? 0 : getBalanceA().hashCode());
        result = prime * result + ((getBalanceB() == null) ? 0 : getBalanceB().hashCode());
        result = prime * result + ((getOrgCode() == null) ? 0 : getOrgCode().hashCode());
        result = prime * result + ((getDeptCode() == null) ? 0 : getDeptCode().hashCode());
        result = prime * result + ((getReportuuid() == null) ? 0 : getReportuuid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", detailuuid=").append(detailuuid);
        sb.append(", termid=").append(termid);
        sb.append(", reporttype=").append(reporttype);
        sb.append(", propertycode=").append(propertycode);
        sb.append(", balanceA=").append(balanceA);
        sb.append(", balanceB=").append(balanceB);
        sb.append(", orgCode=").append(orgCode);
        sb.append(", deptCode=").append(deptCode);
        sb.append(", reportuuid=").append(reportuuid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}