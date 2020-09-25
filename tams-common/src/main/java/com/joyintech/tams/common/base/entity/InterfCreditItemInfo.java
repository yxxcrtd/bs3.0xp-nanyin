package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class InterfCreditItemInfo extends BaseEntity {
    /**
     * 出账编号
     */
    private String cashOutNo;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 关联合同编号
     */
    private String relContractNo;

    /**
     * 是否政府融资平台
     */
    private String isGov;

    /**
     * 是否两高一剩行业
     */
    private String isHighPollution;

    /**
     * 是否国家限制行业
     */
    private String isGovtLimit;

    /**
     * 是否战略新兴产业
     */
    private String isGovtNewStrategy;

    /**
     * 资金用途
     */
    private String fundUseType;

    /**
     * 还款来源
     */
    private String repaymentType;

    /**
     * 业务合同对应批复产品
     */
    private String busiContractProd;

    /**
     * 是否低风险业务
     */
    private String isLowRisk;

    /**
     * 批复文号
     */
    private String approveNo;

    /**
     * 信贷分类
     */
    private String creditType;

    /**
     * 额度批复金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal aproveAmount;

    /**
     * 批复期限
     */
    private Integer aproveLimit;

    /**
     * 终审机构
     */
    private String lastAproveIns;

    /**
     * 已使用额度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal usedAmount;

    /**
     * 签发日期
     */
    private Date signDate;

    /**
     * 风险分类
     */
    private String riskType;

    /**
     * 担保方式
     */
    private String assurePattern;

    /**
     * 担保情况说明
     */
    private String assureMark;

    /**
     * 分行核心机构号
     */
    private String branchInsNo;

    /**
     * BECIF号
     */
    private String becif;

    /**
     * 信贷客户编号
     */
    private String creditCustomerNo;

    /**
     * 客户名称
     */
    private String creditCustomerName;

    /**
     * 信贷业务品种编号
     */
    private String creditBusinessNo;

    /**
     * 信贷业务品种中文
     */
    private String creditBusinessName;

    /**
     * 业务合同编号
     */
    private String busiContractNo;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 执行利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yield;

    /**
     * 放款指令总金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal paymentAmount;

    /**
     * 剩余可用金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal remainAvailAmt;

    /**
     * 撤销原因
     */
    private String reason;

    /**
     * 是否已撤销
     */
    private String isRevoke;

    /**
     * 取得 出账编号
     * @return 出账编号
     */
    public String getCashOutNo() {
        return cashOutNo;
    }

    /**
     * 设置 出账编号
     * @param cashOutNo 出账编号
     */
    public void setCashOutNo(String cashOutNo) {
        this.cashOutNo = cashOutNo == null ? null : cashOutNo.trim();
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 关联合同编号
     * @return 关联合同编号
     */
    public String getRelContractNo() {
        return relContractNo;
    }

    /**
     * 设置 关联合同编号
     * @param relContractNo 关联合同编号
     */
    public void setRelContractNo(String relContractNo) {
        this.relContractNo = relContractNo == null ? null : relContractNo.trim();
    }

    /**
     * 取得 是否政府融资平台
     * @return 是否政府融资平台
     */
    public String getIsGov() {
        return isGov;
    }

    /**
     * 设置 是否政府融资平台
     * @param isGov 是否政府融资平台
     */
    public void setIsGov(String isGov) {
        this.isGov = isGov == null ? null : isGov.trim();
    }

    /**
     * 取得 是否两高一剩行业
     * @return 是否两高一剩行业
     */
    public String getIsHighPollution() {
        return isHighPollution;
    }

    /**
     * 设置 是否两高一剩行业
     * @param isHighPollution 是否两高一剩行业
     */
    public void setIsHighPollution(String isHighPollution) {
        this.isHighPollution = isHighPollution == null ? null : isHighPollution.trim();
    }

    /**
     * 取得 是否国家限制行业
     * @return 是否国家限制行业
     */
    public String getIsGovtLimit() {
        return isGovtLimit;
    }

    /**
     * 设置 是否国家限制行业
     * @param isGovtLimit 是否国家限制行业
     */
    public void setIsGovtLimit(String isGovtLimit) {
        this.isGovtLimit = isGovtLimit == null ? null : isGovtLimit.trim();
    }

    /**
     * 取得 是否战略新兴产业
     * @return 是否战略新兴产业
     */
    public String getIsGovtNewStrategy() {
        return isGovtNewStrategy;
    }

    /**
     * 设置 是否战略新兴产业
     * @param isGovtNewStrategy 是否战略新兴产业
     */
    public void setIsGovtNewStrategy(String isGovtNewStrategy) {
        this.isGovtNewStrategy = isGovtNewStrategy == null ? null : isGovtNewStrategy.trim();
    }

    /**
     * 取得 资金用途
     * @return 资金用途
     */
    public String getFundUseType() {
        return fundUseType;
    }

    /**
     * 设置 资金用途
     * @param fundUseType 资金用途
     */
    public void setFundUseType(String fundUseType) {
        this.fundUseType = fundUseType == null ? null : fundUseType.trim();
    }

    /**
     * 取得 还款来源
     * @return 还款来源
     */
    public String getRepaymentType() {
        return repaymentType;
    }

    /**
     * 设置 还款来源
     * @param repaymentType 还款来源
     */
    public void setRepaymentType(String repaymentType) {
        this.repaymentType = repaymentType == null ? null : repaymentType.trim();
    }

    /**
     * 取得 业务合同对应批复产品
     * @return 业务合同对应批复产品
     */
    public String getBusiContractProd() {
        return busiContractProd;
    }

    /**
     * 设置 业务合同对应批复产品
     * @param busiContractProd 业务合同对应批复产品
     */
    public void setBusiContractProd(String busiContractProd) {
        this.busiContractProd = busiContractProd == null ? null : busiContractProd.trim();
    }

    /**
     * 取得 是否低风险业务
     * @return 是否低风险业务
     */
    public String getIsLowRisk() {
        return isLowRisk;
    }

    /**
     * 设置 是否低风险业务
     * @param isLowRisk 是否低风险业务
     */
    public void setIsLowRisk(String isLowRisk) {
        this.isLowRisk = isLowRisk == null ? null : isLowRisk.trim();
    }

    /**
     * 取得 批复文号
     * @return 批复文号
     */
    public String getApproveNo() {
        return approveNo;
    }

    /**
     * 设置 批复文号
     * @param approveNo 批复文号
     */
    public void setApproveNo(String approveNo) {
        this.approveNo = approveNo == null ? null : approveNo.trim();
    }

    /**
     * 取得 信贷分类
     * @return 信贷分类
     */
    public String getCreditType() {
        return creditType;
    }

    /**
     * 设置 信贷分类
     * @param creditType 信贷分类
     */
    public void setCreditType(String creditType) {
        this.creditType = creditType == null ? null : creditType.trim();
    }

    /**
     * 取得 额度批复金额
     * @return 额度批复金额
     */
    public BigDecimal getAproveAmount() {
        return aproveAmount;
    }

    /**
     * 设置 额度批复金额
     * @param aproveAmount 额度批复金额
     */
    public void setAproveAmount(BigDecimal aproveAmount) {
        this.aproveAmount = aproveAmount;
    }

    /**
     * 取得 批复期限
     * @return 批复期限
     */
    public Integer getAproveLimit() {
        return aproveLimit;
    }

    /**
     * 设置 批复期限
     * @param aproveLimit 批复期限
     */
    public void setAproveLimit(Integer aproveLimit) {
        this.aproveLimit = aproveLimit;
    }

    /**
     * 取得 终审机构
     * @return 终审机构
     */
    public String getLastAproveIns() {
        return lastAproveIns;
    }

    /**
     * 设置 终审机构
     * @param lastAproveIns 终审机构
     */
    public void setLastAproveIns(String lastAproveIns) {
        this.lastAproveIns = lastAproveIns == null ? null : lastAproveIns.trim();
    }

    /**
     * 取得 已使用额度
     * @return 已使用额度
     */
    public BigDecimal getUsedAmount() {
        return usedAmount;
    }

    /**
     * 设置 已使用额度
     * @param usedAmount 已使用额度
     */
    public void setUsedAmount(BigDecimal usedAmount) {
        this.usedAmount = usedAmount;
    }

    /**
     * 取得 签发日期
     * @return 签发日期
     */
    public Date getSignDate() {
        return signDate;
    }

    /**
     * 设置 签发日期
     * @param signDate 签发日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    /**
     * 取得 风险分类
     * @return 风险分类
     */
    public String getRiskType() {
        return riskType;
    }

    /**
     * 设置 风险分类
     * @param riskType 风险分类
     */
    public void setRiskType(String riskType) {
        this.riskType = riskType == null ? null : riskType.trim();
    }

    /**
     * 取得 担保方式
     * @return 担保方式
     */
    public String getAssurePattern() {
        return assurePattern;
    }

    /**
     * 设置 担保方式
     * @param assurePattern 担保方式
     */
    public void setAssurePattern(String assurePattern) {
        this.assurePattern = assurePattern == null ? null : assurePattern.trim();
    }

    /**
     * 取得 担保情况说明
     * @return 担保情况说明
     */
    public String getAssureMark() {
        return assureMark;
    }

    /**
     * 设置 担保情况说明
     * @param assureMark 担保情况说明
     */
    public void setAssureMark(String assureMark) {
        this.assureMark = assureMark == null ? null : assureMark.trim();
    }

    /**
     * 取得 分行核心机构号
     * @return 分行核心机构号
     */
    public String getBranchInsNo() {
        return branchInsNo;
    }

    /**
     * 设置 分行核心机构号
     * @param branchInsNo 分行核心机构号
     */
    public void setBranchInsNo(String branchInsNo) {
        this.branchInsNo = branchInsNo == null ? null : branchInsNo.trim();
    }

    /**
     * 取得 BECIF号
     * @return BECIF号
     */
    public String getBecif() {
        return becif;
    }

    /**
     * 设置 BECIF号
     * @param becif BECIF号
     */
    public void setBecif(String becif) {
        this.becif = becif == null ? null : becif.trim();
    }

    /**
     * 取得 信贷客户编号
     * @return 信贷客户编号
     */
    public String getCreditCustomerNo() {
        return creditCustomerNo;
    }

    /**
     * 设置 信贷客户编号
     * @param creditCustomerNo 信贷客户编号
     */
    public void setCreditCustomerNo(String creditCustomerNo) {
        this.creditCustomerNo = creditCustomerNo == null ? null : creditCustomerNo.trim();
    }

    /**
     * 取得 客户名称
     * @return 客户名称
     */
    public String getCreditCustomerName() {
        return creditCustomerName;
    }

    /**
     * 设置 客户名称
     * @param creditCustomerName 客户名称
     */
    public void setCreditCustomerName(String creditCustomerName) {
        this.creditCustomerName = creditCustomerName == null ? null : creditCustomerName.trim();
    }

    /**
     * 取得 信贷业务品种编号
     * @return 信贷业务品种编号
     */
    public String getCreditBusinessNo() {
        return creditBusinessNo;
    }

    /**
     * 设置 信贷业务品种编号
     * @param creditBusinessNo 信贷业务品种编号
     */
    public void setCreditBusinessNo(String creditBusinessNo) {
        this.creditBusinessNo = creditBusinessNo == null ? null : creditBusinessNo.trim();
    }

    /**
     * 取得 信贷业务品种中文
     * @return 信贷业务品种中文
     */
    public String getCreditBusinessName() {
        return creditBusinessName;
    }

    /**
     * 设置 信贷业务品种中文
     * @param creditBusinessName 信贷业务品种中文
     */
    public void setCreditBusinessName(String creditBusinessName) {
        this.creditBusinessName = creditBusinessName == null ? null : creditBusinessName.trim();
    }

    /**
     * 取得 业务合同编号
     * @return 业务合同编号
     */
    public String getBusiContractNo() {
        return busiContractNo;
    }

    /**
     * 设置 业务合同编号
     * @param busiContractNo 业务合同编号
     */
    public void setBusiContractNo(String busiContractNo) {
        this.busiContractNo = busiContractNo == null ? null : busiContractNo.trim();
    }

    /**
     * 取得 起息日
     * @return 起息日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 起息日
     * @param vdate 起息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 到期日
     * @return 到期日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 到期日
     * @param mdate 到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 执行利率
     * @return 执行利率
     */
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * 设置 执行利率
     * @param yield 执行利率
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    /**
     * 取得 放款指令总金额
     * @return 放款指令总金额
     */
    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * 设置 放款指令总金额
     * @param paymentAmount 放款指令总金额
     */
    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    /**
     * 取得 剩余可用金额
     * @return 剩余可用金额
     */
    public BigDecimal getRemainAvailAmt() {
        return remainAvailAmt;
    }

    /**
     * 设置 剩余可用金额
     * @param remainAvailAmt 剩余可用金额
     */
    public void setRemainAvailAmt(BigDecimal remainAvailAmt) {
        this.remainAvailAmt = remainAvailAmt;
    }

    /**
     * 取得 撤销原因
     * @return 撤销原因
     */
    public String getReason() {
        return reason;
    }

    /**
     * 设置 撤销原因
     * @param reason 撤销原因
     */
    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    /**
     * 取得 是否已撤销
     * @return 是否已撤销
     */
    public String getIsRevoke() {
        return isRevoke;
    }

    /**
     * 设置 是否已撤销
     * @param isRevoke 是否已撤销
     */
    public void setIsRevoke(String isRevoke) {
        this.isRevoke = isRevoke == null ? null : isRevoke.trim();
    }
}