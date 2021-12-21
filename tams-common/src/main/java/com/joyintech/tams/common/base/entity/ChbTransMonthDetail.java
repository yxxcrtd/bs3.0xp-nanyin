package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class ChbTransMonthDetail extends BaseEntity {
    /**
     * null
     */
    private String detailuuid;

    /**
     * 期数
     */
    private String termid;

    /**
     * 报表类型：PROD-产品，BALANCE-资产负债表，DEPOSIT-结构性存款
     */
    private String reporttype;

    /**
     * 属性代码
     */
    private String propertycode;

    /**
     * 新产品余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal newBalance;

    /**
     * 新产品加权价格/剩余期限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal newTerms;

    /**
     * 老产品余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal oldBalance;

    /**
     * 老产品加权价格/剩余期限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal oldTerms;

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

    /**
     * 取得 null
     * @return null
     */
    public String getDetailuuid() {
        return detailuuid;
    }

    /**
     * 设置 null
     * @param detailuuid null
     */
    public void setDetailuuid(String detailuuid) {
        this.detailuuid = detailuuid == null ? null : detailuuid.trim();
    }

    /**
     * 取得 期数
     * @return 期数
     */
    public String getTermid() {
        return termid;
    }

    /**
     * 设置 期数
     * @param termid 期数
     */
    public void setTermid(String termid) {
        this.termid = termid == null ? null : termid.trim();
    }

    /**
     * 取得 报表类型：PROD-产品，BALANCE-资产负债表，DEPOSIT-结构性存款
     * @return 报表类型：PROD-产品，BALANCE-资产负债表，DEPOSIT-结构性存款
     */
    public String getReporttype() {
        return reporttype;
    }

    /**
     * 设置 报表类型：PROD-产品，BALANCE-资产负债表，DEPOSIT-结构性存款
     * @param reporttype 报表类型：PROD-产品，BALANCE-资产负债表，DEPOSIT-结构性存款
     */
    public void setReporttype(String reporttype) {
        this.reporttype = reporttype == null ? null : reporttype.trim();
    }

    /**
     * 取得 属性代码
     * @return 属性代码
     */
    public String getPropertycode() {
        return propertycode;
    }

    /**
     * 设置 属性代码
     * @param propertycode 属性代码
     */
    public void setPropertycode(String propertycode) {
        this.propertycode = propertycode == null ? null : propertycode.trim();
    }

    /**
     * 取得 新产品余额
     * @return 新产品余额
     */
    public BigDecimal getNewBalance() {
        return newBalance;
    }

    /**
     * 设置 新产品余额
     * @param newBalance 新产品余额
     */
    public void setNewBalance(BigDecimal newBalance) {
        this.newBalance = newBalance;
    }

    /**
     * 取得 新产品加权价格/剩余期限
     * @return 新产品加权价格/剩余期限
     */
    public BigDecimal getNewTerms() {
        return newTerms;
    }

    /**
     * 设置 新产品加权价格/剩余期限
     * @param newTerms 新产品加权价格/剩余期限
     */
    public void setNewTerms(BigDecimal newTerms) {
        this.newTerms = newTerms;
    }

    /**
     * 取得 老产品余额
     * @return 老产品余额
     */
    public BigDecimal getOldBalance() {
        return oldBalance;
    }

    /**
     * 设置 老产品余额
     * @param oldBalance 老产品余额
     */
    public void setOldBalance(BigDecimal oldBalance) {
        this.oldBalance = oldBalance;
    }

    /**
     * 取得 老产品加权价格/剩余期限
     * @return 老产品加权价格/剩余期限
     */
    public BigDecimal getOldTerms() {
        return oldTerms;
    }

    /**
     * 设置 老产品加权价格/剩余期限
     * @param oldTerms 老产品加权价格/剩余期限
     */
    public void setOldTerms(BigDecimal oldTerms) {
        this.oldTerms = oldTerms;
    }

    /**
     * 取得 机构代码
     * @return 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置 机构代码
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 取得 部门代码
     * @return 部门代码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置 部门代码
     * @param deptCode 部门代码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * 取得 月报主表主键
     * @return 月报主表主键
     */
    public String getReportuuid() {
        return reportuuid;
    }

    /**
     * 设置 月报主表主键
     * @param reportuuid 月报主表主键
     */
    public void setReportuuid(String reportuuid) {
        this.reportuuid = reportuuid == null ? null : reportuuid.trim();
    }
}