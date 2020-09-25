package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class PaBranchSaleDetail extends PaBranchSaleDetailKey {
    /**
     * 日期
     */
    private Date cdate;

    /**
     * 本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prinAmt;

    /**
     * 客户认购数
     */
    private Long customNum;

    /**
     * 应付收益，强减、到期用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal accIntAmt;

    /**
     * 实付收益，强减、到期用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intAmt;

    /**
     * 应付销售费，强减、到期用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal accSalefeeAmt;

    /**
     * 实付销售费，强减、到期用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal salefeeAmt;

    /**
     * 应付代销费，强减、到期用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal accSalefee2Amt;

    /**
     * 实付代销费，强减、到期用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal salefee2Amt;

    /**
     * 取得 日期
     * @return 日期
     */
    public Date getCdate() {
        return cdate;
    }

    /**
     * 设置 日期
     * @param cdate 日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    /**
     * 取得 本金
     * @return 本金
     */
    public BigDecimal getPrinAmt() {
        return prinAmt;
    }

    /**
     * 设置 本金
     * @param prinAmt 本金
     */
    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }

    /**
     * 取得 客户认购数
     * @return 客户认购数
     */
    public Long getCustomNum() {
        return customNum;
    }

    /**
     * 设置 客户认购数
     * @param customNum 客户认购数
     */
    public void setCustomNum(Long customNum) {
        this.customNum = customNum;
    }

    /**
     * 取得 应付收益，强减、到期用
     * @return 应付收益，强减、到期用
     */
    public BigDecimal getAccIntAmt() {
        return accIntAmt;
    }

    /**
     * 设置 应付收益，强减、到期用
     * @param accIntAmt 应付收益，强减、到期用
     */
    public void setAccIntAmt(BigDecimal accIntAmt) {
        this.accIntAmt = accIntAmt;
    }

    /**
     * 取得 实付收益，强减、到期用
     * @return 实付收益，强减、到期用
     */
    public BigDecimal getIntAmt() {
        return intAmt;
    }

    /**
     * 设置 实付收益，强减、到期用
     * @param intAmt 实付收益，强减、到期用
     */
    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    /**
     * 取得 应付销售费，强减、到期用
     * @return 应付销售费，强减、到期用
     */
    public BigDecimal getAccSalefeeAmt() {
        return accSalefeeAmt;
    }

    /**
     * 设置 应付销售费，强减、到期用
     * @param accSalefeeAmt 应付销售费，强减、到期用
     */
    public void setAccSalefeeAmt(BigDecimal accSalefeeAmt) {
        this.accSalefeeAmt = accSalefeeAmt;
    }

    /**
     * 取得 实付销售费，强减、到期用
     * @return 实付销售费，强减、到期用
     */
    public BigDecimal getSalefeeAmt() {
        return salefeeAmt;
    }

    /**
     * 设置 实付销售费，强减、到期用
     * @param salefeeAmt 实付销售费，强减、到期用
     */
    public void setSalefeeAmt(BigDecimal salefeeAmt) {
        this.salefeeAmt = salefeeAmt;
    }

    /**
     * 取得 应付代销费，强减、到期用
     * @return 应付代销费，强减、到期用
     */
    public BigDecimal getAccSalefee2Amt() {
        return accSalefee2Amt;
    }

    /**
     * 设置 应付代销费，强减、到期用
     * @param accSalefee2Amt 应付代销费，强减、到期用
     */
    public void setAccSalefee2Amt(BigDecimal accSalefee2Amt) {
        this.accSalefee2Amt = accSalefee2Amt;
    }

    /**
     * 取得 实付代销费，强减、到期用
     * @return 实付代销费，强减、到期用
     */
    public BigDecimal getSalefee2Amt() {
        return salefee2Amt;
    }

    /**
     * 设置 实付代销费，强减、到期用
     * @param salefee2Amt 实付代销费，强减、到期用
     */
    public void setSalefee2Amt(BigDecimal salefee2Amt) {
        this.salefee2Amt = salefee2Amt;
    }
}