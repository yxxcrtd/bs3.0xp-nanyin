package com.joyintech.tams.svreport.chb.finassetliabilitystatement.dto;


import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;

/**
 * 理财产品资产负债表查询Dto
 *
 * @author WanShiwei
 * @version 2.1.6 2021年4月20日
 * @see FinAssetLiabilityStatementQuery004Dto
 * @since 2.1.6
 */
public class FinAssetLiabilityStatementQuery004Dto extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /**
     * 真实产品代码
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
     * 期末余额
     */
    private BigDecimal endingBalance;

    /**
     * 期初余额
     */
    private BigDecimal initialBalance;

    /**
     * 业务日期
     */
    private String busiDate;

    /**
     * 指标分类
     */
    private String subjectType;

    /**
     * 获取 真实产品代码
     * @return realPrdId.
     */
    public String getRealPrdId() {
        return realPrdId;
    }

    /**
     * 设置 真实产品代码
     * @param realPrdId 真实产品代码
     */
    public void setRealPrdId(String realPrdId) {
        this.realPrdId = realPrdId;
    }

    /**
     * 获取 指标项编号
     * @return subjectId.
     */
    public String getSubjectId() {
        return subjectId;
    }

    /**
     * 设置 指标项编号
     * @param subjectId 指标项编号
     */
    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * 获取 币种
     * @return ccy
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 业务日期
     * @return busiDate.
     */
    public String getBusiDate() {
        return busiDate;
    }

    /**
     * 设置 业务日期
     * @param busiDate 业务日期
     */
    public void setBusiDate(String busiDate) {
        this.busiDate = busiDate;
    }

    /**
     * 获取 指标分类
     * @return type.
     */
    public String getSubjectType() {
        return subjectType;
    }

    /**
     * 设置 指标分类
     * @param subjectType 指标分类
     */
    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    /**
     * 获取 指标名称
     * @return subjectName.
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * 设置 指标名称
     * @param subjectName 指标名称
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * 获取 期末余额
     * @return endingBalance.
     */
    public BigDecimal getEndingBalance() {
        return endingBalance;
    }

    /**
     * 设置 期末余额
     * @param endingBalance 期末余额
     */
    public void setEndingBalance(BigDecimal endingBalance) {
        this.endingBalance = endingBalance;
    }

    /**
     * 获取 期初余额
     * @return initialBalance.
     */
    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    /**
     * 设置 期初余额
     * @param initialBalance 期初余额
     */
    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

}
