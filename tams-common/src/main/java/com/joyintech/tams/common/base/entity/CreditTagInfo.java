package com.joyintech.tams.common.base.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

public class CreditTagInfo extends BaseEntity {
    /**
     * 授信标签代码
     */
    private String creditTagId;

    /**
     * 标签名称
     */
    private String creditTagName;

    /**
     * 授信占用方式，机构授信、资产授信、无授信
     */
    private String creditUsingType;

    /**
     * 风险系数（%）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal riskRatio;

    /**
     * 额度分类
     */
    private String creditType;

    /**
     * 扣减规则
     */
    private String deductRule;

    /**
     * 是否永续债
     */
    private String isPerpetualSec;

    /**
     * 发行方式
     */
    private String issueType;

    /**
     * 是否外汇
     */
    private String isFx;

    /**
     * 货币对
     */
    private String curPair;

    /**
     * 期限值
     */
    private Long termValue;

    /**
     * 期限单位
     */
    private String termUnit;

    /**
     * 终止期限值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal endTermValue;

    /**
     * 终止期限单位
     */
    private String endTermUnit;

    /**
     * 业务类型
     */
    private String busiType;

    /**
     * 审批编号
     */
    private String approId;

    /**
     * 机构类型
     */
    private String orgType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 授信标签代码
     * @return 授信标签代码
     */
    public String getCreditTagId() {
        return creditTagId;
    }

    /**
     * 设置 授信标签代码
     * @param creditTagId 授信标签代码
     */
    public void setCreditTagId(String creditTagId) {
        this.creditTagId = creditTagId == null ? null : creditTagId.trim();
    }

    /**
     * 取得 标签名称
     * @return 标签名称
     */
    public String getCreditTagName() {
        return creditTagName;
    }

    /**
     * 设置 标签名称
     * @param creditTagName 标签名称
     */
    public void setCreditTagName(String creditTagName) {
        this.creditTagName = creditTagName == null ? null : creditTagName.trim();
    }

    /**
     * 取得 授信占用方式，机构授信、资产授信、无授信
     * @return 授信占用方式，机构授信、资产授信、无授信
     */
    public String getCreditUsingType() {
        return creditUsingType;
    }

    /**
     * 设置 授信占用方式，机构授信、资产授信、无授信
     * @param creditUsingType 授信占用方式，机构授信、资产授信、无授信
     */
    public void setCreditUsingType(String creditUsingType) {
        this.creditUsingType = creditUsingType == null ? null : creditUsingType.trim();
    }

    /**
     * 取得 风险系数（%）
     * @return 风险系数（%）
     */
    public BigDecimal getRiskRatio() {
        return riskRatio;
    }

    /**
     * 设置 风险系数（%）
     * @param riskRatio 风险系数（%）
     */
    public void setRiskRatio(BigDecimal riskRatio) {
        this.riskRatio = riskRatio;
    }

    /**
     * 取得 额度分类
     * @return 额度分类
     */
    public String getCreditType() {
        return creditType;
    }

    /**
     * 设置 额度分类
     * @param creditType 额度分类
     */
    public void setCreditType(String creditType) {
        this.creditType = creditType == null ? null : creditType.trim();
    }

    /**
     * 取得 扣减规则
     * @return 扣减规则
     */
    public String getDeductRule() {
        return deductRule;
    }

    /**
     * 设置 扣减规则
     * @param deductRule 扣减规则
     */
    public void setDeductRule(String deductRule) {
        this.deductRule = deductRule == null ? null : deductRule.trim();
    }

    /**
     * 取得 是否永续债
     * @return 是否永续债
     */
    public String getIsPerpetualSec() {
        return isPerpetualSec;
    }

    /**
     * 设置 是否永续债
     * @param isPerpetualSec 是否永续债
     */
    public void setIsPerpetualSec(String isPerpetualSec) {
        this.isPerpetualSec = isPerpetualSec == null ? null : isPerpetualSec.trim();
    }

    /**
     * 取得 发行方式
     * @return 发行方式
     */
    public String getIssueType() {
        return issueType;
    }

    /**
     * 设置 发行方式
     * @param issueType 发行方式
     */
    public void setIssueType(String issueType) {
        this.issueType = issueType == null ? null : issueType.trim();
    }

    /**
     * 取得 是否外汇
     * @return 是否外汇
     */
    public String getIsFx() {
        return isFx;
    }

    /**
     * 设置 是否外汇
     * @param isFx 是否外汇
     */
    public void setIsFx(String isFx) {
        this.isFx = isFx == null ? null : isFx.trim();
    }

    /**
     * 取得 货币对
     * @return 货币对
     */
    public String getCurPair() {
        return curPair;
    }

    /**
     * 设置 货币对
     * @param curPair 货币对
     */
    public void setCurPair(String curPair) {
        this.curPair = curPair == null ? null : curPair.trim();
    }

    /**
     * 取得 期限值
     * @return 期限值
     */
    public Long getTermValue() {
        return termValue;
    }

    /**
     * 设置 期限值
     * @param termValue 期限值
     */
    public void setTermValue(Long termValue) {
        this.termValue = termValue;
    }

    /**
     * 取得 期限单位
     * @return 期限单位
     */
    public String getTermUnit() {
        return termUnit;
    }

    /**
     * 设置 期限单位
     * @param termUnit 期限单位
     */
    public void setTermUnit(String termUnit) {
        this.termUnit = termUnit == null ? null : termUnit.trim();
    }

    /**
     * 取得 终止期限值
     * @return 终止期限值
     */
    public BigDecimal getEndTermValue() {
        return endTermValue;
    }

    /**
     * 设置 终止期限值
     * @param endTermValue 终止期限值
     */
    public void setEndTermValue(BigDecimal endTermValue) {
        this.endTermValue = endTermValue;
    }

    /**
     * 取得 终止期限单位
     * @return 终止期限单位
     */
    public String getEndTermUnit() {
        return endTermUnit;
    }

    /**
     * 设置 终止期限单位
     * @param endTermUnit 终止期限单位
     */
    public void setEndTermUnit(String endTermUnit) {
        this.endTermUnit = endTermUnit == null ? null : endTermUnit.trim();
    }

    /**
     * 取得 业务类型
     * @return 业务类型
     */
    public String getBusiType() {
        return busiType;
    }

    /**
     * 设置 业务类型
     * @param busiType 业务类型
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType == null ? null : busiType.trim();
    }

    /**
     * 取得 审批编号
     * @return 审批编号
     */
    public String getApproId() {
        return approId;
    }

    /**
     * 设置 审批编号
     * @param approId 审批编号
     */
    public void setApproId(String approId) {
        this.approId = approId == null ? null : approId.trim();
    }

    /**
     * 取得 机构类型
     * @return 机构类型
     */
    public String getOrgType() {
        return orgType;
    }

    /**
     * 设置 机构类型
     * @param orgType 机构类型
     */
    public void setOrgType(String orgType) {
        this.orgType = orgType == null ? null : orgType.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}