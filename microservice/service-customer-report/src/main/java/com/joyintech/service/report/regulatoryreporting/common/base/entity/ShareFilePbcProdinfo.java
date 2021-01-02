package com.joyintech.service.report.regulatoryreporting.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class ShareFilePbcProdinfo extends BaseEntity {
    /**
     * 明细统计uuid
     */
    private String detailuuid;

    /**
     * 报表类型,B_5
     */
    private String reporttype;

    /**
     * 分组类型
     */
    private String grouptype;

    /**
     * 属性代码
     */
    private String propertycode;

    /**
     * 主表主键
     */
    private String reportuuid;

    /**
     * 只数
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prodcounts;

    /**
     * 余额(当期募集金额)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dqmjBalance;

    /**
     * 比上月(当期募集金额)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dqmjmAmt;

    /**
     * 比年初(当期募集金额)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dqmjyAmt;

    /**
     * 同比%(当期募集金额)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dqmjtbYiled;

    /**
     * 余额(当期兑付金额)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dqdfBalance;

    /**
     * 比上月(当期兑付金额)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dqdfmAmt;

    /**
     * 比年初(当期兑付金额)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dqdfyAmt;

    /**
     * 同比%(当期兑付金额)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dqdftbYiled;

    /**
     * 余额(期末资金余额)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal qmzjBalance;

    /**
     * 比上月(期末资金余额)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal qmzjmAmt;

    /**
     * 比年初(期末资金余额)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal qmzjyAmt;

    /**
     * 同比%(期末资金余额)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal qmzjtbYiled;

    /**
     * 取得 明细统计uuid
     * @return 明细统计uuid
     */
    public String getDetailuuid() {
        return detailuuid;
    }

    /**
     * 设置 明细统计uuid
     * @param detailuuid 明细统计uuid
     */
    public void setDetailuuid(String detailuuid) {
        this.detailuuid = detailuuid == null ? null : detailuuid.trim();
    }

    /**
     * 取得 报表类型,B_5
     * @return 报表类型,B_5
     */
    public String getReporttype() {
        return reporttype;
    }

    /**
     * 设置 报表类型,B_5
     * @param reporttype 报表类型,B_5
     */
    public void setReporttype(String reporttype) {
        this.reporttype = reporttype == null ? null : reporttype.trim();
    }

    /**
     * 取得 分组类型
     * @return 分组类型
     */
    public String getGrouptype() {
        return grouptype;
    }

    /**
     * 设置 分组类型
     * @param grouptype 分组类型
     */
    public void setGrouptype(String grouptype) {
        this.grouptype = grouptype == null ? null : grouptype.trim();
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
     * 取得 主表主键
     * @return 主表主键
     */
    public String getReportuuid() {
        return reportuuid;
    }

    /**
     * 设置 主表主键
     * @param reportuuid 主表主键
     */
    public void setReportuuid(String reportuuid) {
        this.reportuuid = reportuuid == null ? null : reportuuid.trim();
    }

    /**
     * 取得 只数
     * @return 只数
     */
    public BigDecimal getProdcounts() {
        return prodcounts;
    }

    /**
     * 设置 只数
     * @param prodcounts 只数
     */
    public void setProdcounts(BigDecimal prodcounts) {
        this.prodcounts = prodcounts;
    }

    /**
     * 取得 余额(当期募集金额)
     * @return 余额(当期募集金额)
     */
    public BigDecimal getDqmjBalance() {
        return dqmjBalance;
    }

    /**
     * 设置 余额(当期募集金额)
     * @param dqmjBalance 余额(当期募集金额)
     */
    public void setDqmjBalance(BigDecimal dqmjBalance) {
        this.dqmjBalance = dqmjBalance;
    }

    /**
     * 取得 比上月(当期募集金额)
     * @return 比上月(当期募集金额)
     */
    public BigDecimal getDqmjmAmt() {
        return dqmjmAmt;
    }

    /**
     * 设置 比上月(当期募集金额)
     * @param dqmjmAmt 比上月(当期募集金额)
     */
    public void setDqmjmAmt(BigDecimal dqmjmAmt) {
        this.dqmjmAmt = dqmjmAmt;
    }

    /**
     * 取得 比年初(当期募集金额)
     * @return 比年初(当期募集金额)
     */
    public BigDecimal getDqmjyAmt() {
        return dqmjyAmt;
    }

    /**
     * 设置 比年初(当期募集金额)
     * @param dqmjyAmt 比年初(当期募集金额)
     */
    public void setDqmjyAmt(BigDecimal dqmjyAmt) {
        this.dqmjyAmt = dqmjyAmt;
    }

    /**
     * 取得 同比%(当期募集金额)
     * @return 同比%(当期募集金额)
     */
    public BigDecimal getDqmjtbYiled() {
        return dqmjtbYiled;
    }

    /**
     * 设置 同比%(当期募集金额)
     * @param dqmjtbYiled 同比%(当期募集金额)
     */
    public void setDqmjtbYiled(BigDecimal dqmjtbYiled) {
        this.dqmjtbYiled = dqmjtbYiled;
    }

    /**
     * 取得 余额(当期兑付金额)
     * @return 余额(当期兑付金额)
     */
    public BigDecimal getDqdfBalance() {
        return dqdfBalance;
    }

    /**
     * 设置 余额(当期兑付金额)
     * @param dqdfBalance 余额(当期兑付金额)
     */
    public void setDqdfBalance(BigDecimal dqdfBalance) {
        this.dqdfBalance = dqdfBalance;
    }

    /**
     * 取得 比上月(当期兑付金额)
     * @return 比上月(当期兑付金额)
     */
    public BigDecimal getDqdfmAmt() {
        return dqdfmAmt;
    }

    /**
     * 设置 比上月(当期兑付金额)
     * @param dqdfmAmt 比上月(当期兑付金额)
     */
    public void setDqdfmAmt(BigDecimal dqdfmAmt) {
        this.dqdfmAmt = dqdfmAmt;
    }

    /**
     * 取得 比年初(当期兑付金额)
     * @return 比年初(当期兑付金额)
     */
    public BigDecimal getDqdfyAmt() {
        return dqdfyAmt;
    }

    /**
     * 设置 比年初(当期兑付金额)
     * @param dqdfyAmt 比年初(当期兑付金额)
     */
    public void setDqdfyAmt(BigDecimal dqdfyAmt) {
        this.dqdfyAmt = dqdfyAmt;
    }

    /**
     * 取得 同比%(当期兑付金额)
     * @return 同比%(当期兑付金额)
     */
    public BigDecimal getDqdftbYiled() {
        return dqdftbYiled;
    }

    /**
     * 设置 同比%(当期兑付金额)
     * @param dqdftbYiled 同比%(当期兑付金额)
     */
    public void setDqdftbYiled(BigDecimal dqdftbYiled) {
        this.dqdftbYiled = dqdftbYiled;
    }

    /**
     * 取得 余额(期末资金余额)
     * @return 余额(期末资金余额)
     */
    public BigDecimal getQmzjBalance() {
        return qmzjBalance;
    }

    /**
     * 设置 余额(期末资金余额)
     * @param qmzjBalance 余额(期末资金余额)
     */
    public void setQmzjBalance(BigDecimal qmzjBalance) {
        this.qmzjBalance = qmzjBalance;
    }

    /**
     * 取得 比上月(期末资金余额)
     * @return 比上月(期末资金余额)
     */
    public BigDecimal getQmzjmAmt() {
        return qmzjmAmt;
    }

    /**
     * 设置 比上月(期末资金余额)
     * @param qmzjmAmt 比上月(期末资金余额)
     */
    public void setQmzjmAmt(BigDecimal qmzjmAmt) {
        this.qmzjmAmt = qmzjmAmt;
    }

    /**
     * 取得 比年初(期末资金余额)
     * @return 比年初(期末资金余额)
     */
    public BigDecimal getQmzjyAmt() {
        return qmzjyAmt;
    }

    /**
     * 设置 比年初(期末资金余额)
     * @param qmzjyAmt 比年初(期末资金余额)
     */
    public void setQmzjyAmt(BigDecimal qmzjyAmt) {
        this.qmzjyAmt = qmzjyAmt;
    }

    /**
     * 取得 同比%(期末资金余额)
     * @return 同比%(期末资金余额)
     */
    public BigDecimal getQmzjtbYiled() {
        return qmzjtbYiled;
    }

    /**
     * 设置 同比%(期末资金余额)
     * @param qmzjtbYiled 同比%(期末资金余额)
     */
    public void setQmzjtbYiled(BigDecimal qmzjtbYiled) {
        this.qmzjtbYiled = qmzjtbYiled;
    }
}