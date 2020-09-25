package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class BokClawbackInfo extends BokClawbackInfoKey {
    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 账套日期
     */
    private Date booksetDate;

    /**
     * 回补金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal clawbackAmt;

    /**
     * 回补方式
     */
    private String clawbackType;

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 金融产品类型
     * @return 金融产品类型
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 金融产品类型
     * @param finprodType 金融产品类型
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType == null ? null : finprodType.trim();
    }

    /**
     * 取得 分支序号
     * @return 分支序号
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号
     * @param branch 分支序号
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 取得 账套日期
     * @return 账套日期
     */
    public Date getBooksetDate() {
        return booksetDate;
    }

    /**
     * 设置 账套日期
     * @param booksetDate 账套日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBooksetDate(Date booksetDate) {
        this.booksetDate = booksetDate;
    }

    /**
     * 取得 回补金额
     * @return 回补金额
     */
    public BigDecimal getClawbackAmt() {
        return clawbackAmt;
    }

    /**
     * 设置 回补金额
     * @param clawbackAmt 回补金额
     */
    public void setClawbackAmt(BigDecimal clawbackAmt) {
        this.clawbackAmt = clawbackAmt;
    }

    /**
     * 取得 回补方式
     * @return 回补方式
     */
    public String getClawbackType() {
        return clawbackType;
    }

    /**
     * 设置 回补方式
     * @param clawbackType 回补方式
     */
    public void setClawbackType(String clawbackType) {
        this.clawbackType = clawbackType == null ? null : clawbackType.trim();
    }
}