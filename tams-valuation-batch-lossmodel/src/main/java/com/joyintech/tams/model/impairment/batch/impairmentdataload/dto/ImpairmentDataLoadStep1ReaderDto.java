package com.joyintech.tams.model.impairment.batch.impairmentdataload.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 减值模型数据加载-reader
 */
public class ImpairmentDataLoadStep1ReaderDto extends NeedOperateEntity {

    /**
     * 金融产品代码
     */
    private String finprodId;
    /**
     * 金融产品名称
     */
    private String finprodAbbr;
    /**
     * 市场代码
     */
    private String finprodMarketId;
    /**
     * 发行人
     */
    private String issuer;
    /**
     * 发行人是否持有金融牌照
     */
    private String issueFinLicence;
    /**
     * 融资人
     */
    private String financier;
    /**
     * 担保人
     */
    private String sponsor;
    /**
     * 实际授信主体
     */
    private String actualCreditProvid;
    /**
     * 融资人是否持有金融牌照
     */
    private String financierFinLicence;

    /**
     * 担保人是否持有金融牌照
     */
    private String sponsorFinLicence;

    /**
     * 实际授信主体是否持有金融牌照
     */
    private String actualCreditFinLicence;
    /**
     * 到期日
     */
    private Date mdate;
    /**
     * 是否含有抵质押物
     */
    private String isLimits;

    /**
     * 风险五级分类结果
     */
    private String firstResult;
    /**
     * 证券品种（数据字典.证券品种_模型）
     */
    private String secTypeModel;

    /**
     * 证券细分类（数据字典.证券细分类_模型）
     */
    private String secDetailTypeModel;
    /**
     * 交易编号
     */
    private String tradeId;
    /**
     * 交割日
     */
    private Date settleDate;
    /**
     * 交易方向
     */
    private String ps;
    /**
     * 交易份额
     */
    private BigDecimal shareAmt;
    /**
     * 到期收益率
     */
    private BigDecimal yield;
    /**
     * 计划截止日
     */
    private Date planEndDate;
    /**
     * 是否结清
     */
    private String isClean;
    /**
     * 组合代码
     */
    private String portfolioId;
    /**
     * 产品代码
     */
    private String prodId;
    /**
     * 产品名称
     */
    private String prodName;
    /**
     * 评级机构（数据字典 评级机构）
     */
    private String gradeOrgId;

    /**
     * 评级类型（数据字典 评级类型_模型）
     */
    private String oType;
    /**
     * 债项评级结果
     */
    private String result01;
    /**
     * 债券主体评级结果
     */
    private String result02;
    /**
     * 发行人评级结果
     */
    private String resultIssuer;

    /**
     * 融资人评级结果
     */
    private String resultFinancier;

    /**
     * 担保人评级结果
     */
    private String resultSponsor;

    /**
     * 实际授信主体评级结果
     */
    private String resultActualCredit;
    /**
     * 最新评级结果
     */
    private String resultCurrent;
    /**
     * 最终使用评级类型
     */
    private String oTypeFinal;
    /**
     * 主体是否持有金融牌照
     */
    private String isFinLicence;
    /**
     * 最终使用主体机构
     */
    private String orgIdFinal;
    /**
     * 买入时点评级结果
     */
    private String resultDeal;
    /**
     * 交易金额
     */
    private BigDecimal tradeAmt;
    /**
     * 发行规模
     */
    private BigDecimal issueAmt;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 息票品种
     */
    private String couponSpecies;

    /**
     * 评级机构类型
     */
    private String gradeOrgType;

    /**
     * 发行价
     */
    private BigDecimal issuePrice;

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getIssueFinLicence() {
        return issueFinLicence;
    }

    public void setIssueFinLicence(String issueFinLicence) {
        this.issueFinLicence = issueFinLicence;
    }

    public String getFinancier() {
        return financier;
    }

    public void setFinancier(String financier) {
        this.financier = financier;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getFinancierFinLicence() {
        return financierFinLicence;
    }

    public void setFinancierFinLicence(String financierFinLicence) {
        this.financierFinLicence = financierFinLicence;
    }

    public String getSponsorFinLicence() {
        return sponsorFinLicence;
    }

    public void setSponsorFinLicence(String sponsorFinLicence) {
        this.sponsorFinLicence = sponsorFinLicence;
    }

    public String getActualCreditFinLicence() {
        return actualCreditFinLicence;
    }

    public void setActualCreditFinLicence(String actualCreditFinLicence) {
        this.actualCreditFinLicence = actualCreditFinLicence;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public String getIsLimits() {
        return isLimits;
    }

    public void setIsLimits(String isLimits) {
        this.isLimits = isLimits;
    }

    public String getFirstResult() {
        return firstResult;
    }

    public void setFirstResult(String firstResult) {
        this.firstResult = firstResult;
    }

    @Override
    public String getTradeId() {
        return tradeId;
    }

    @Override
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public BigDecimal getShareAmt() {
        return shareAmt;
    }

    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }

    public BigDecimal getYield() {
        return yield;
    }

    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    public Date getPlanEndDate() {
        return planEndDate;
    }

    public void setPlanEndDate(Date planEndDate) {
        this.planEndDate = planEndDate;
    }

    public String getIsClean() {
        return isClean;
    }

    public void setIsClean(String isClean) {
        this.isClean = isClean;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    @Override
    public String getProdId() {
        return prodId;
    }

    @Override
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public String getSecTypeModel() {
        return secTypeModel;
    }

    public void setSecTypeModel(String secTypeModel) {
        this.secTypeModel = secTypeModel;
    }

    public String getSecDetailTypeModel() {
        return secDetailTypeModel;
    }

    public void setSecDetailTypeModel(String secDetailTypeModel) {
        this.secDetailTypeModel = secDetailTypeModel;
    }

    public String getGradeOrgId() {
        return gradeOrgId;
    }

    public void setGradeOrgId(String gradeOrgId) {
        this.gradeOrgId = gradeOrgId;
    }

    public String getoType() {
        return oType;
    }

    public void setoType(String oType) {
        this.oType = oType;
    }

    public String getResult01() {
        return result01;
    }

    public void setResult01(String result01) {
        this.result01 = result01;
    }

    public String getResult02() {
        return result02;
    }

    public void setResult02(String result02) {
        this.result02 = result02;
    }

    public String getResultIssuer() {
        return resultIssuer;
    }

    public void setResultIssuer(String resultIssuer) {
        this.resultIssuer = resultIssuer;
    }

    public String getResultFinancier() {
        return resultFinancier;
    }

    public void setResultFinancier(String resultFinancier) {
        this.resultFinancier = resultFinancier;
    }

    public String getResultSponsor() {
        return resultSponsor;
    }

    public void setResultSponsor(String resultSponsor) {
        this.resultSponsor = resultSponsor;
    }

    public String getResultActualCredit() {
        return resultActualCredit;
    }

    public void setResultActualCredit(String resultActualCredit) {
        this.resultActualCredit = resultActualCredit;
    }

    public String getResultCurrent() {
        return resultCurrent;
    }

    public void setResultCurrent(String resultCurrent) {
        this.resultCurrent = resultCurrent;
    }

    public String getoTypeFinal() {
        return oTypeFinal;
    }

    public void setoTypeFinal(String oTypeFinal) {
        this.oTypeFinal = oTypeFinal;
    }

    public String getIsFinLicence() {
        return isFinLicence;
    }

    public void setIsFinLicence(String isFinLicence) {
        this.isFinLicence = isFinLicence;
    }

    public String getOrgIdFinal() {
        return orgIdFinal;
    }

    public void setOrgIdFinal(String orgIdFinal) {
        this.orgIdFinal = orgIdFinal;
    }

    public String getResultDeal() {
        return resultDeal;
    }

    public void setResultDeal(String resultDeal) {
        this.resultDeal = resultDeal;
    }

    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public BigDecimal getIssueAmt() {
        return issueAmt;
    }

    public void setIssueAmt(BigDecimal issueAmt) {
        this.issueAmt = issueAmt;
    }

    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public String getCouponSpecies() {
        return couponSpecies;
    }

    public void setCouponSpecies(String couponSpecies) {
        this.couponSpecies = couponSpecies;
    }

    public BigDecimal getIssuePrice() {
        return issuePrice;
    }

    public void setIssuePrice(BigDecimal issuePrice) {
        this.issuePrice = issuePrice;
    }

    public String getActualCreditProvid() {
        return actualCreditProvid;
    }

    public void setActualCreditProvid(String actualCreditProvid) {
        this.actualCreditProvid = actualCreditProvid;
    }

    /**
     * @return 评级机构类型
     */
    public String getGradeOrgType() {
        return gradeOrgType;
    }

    /**
     * 设置 评级机构类型
     *
     * @param gradeOrgType 评级机构类型
     */
    public void setGradeOrgType(String gradeOrgType) {
        this.gradeOrgType = gradeOrgType;
    }
}
