package com.joyintech.tams.model.valuation.batch.cashflowdiscount.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.model.valuation.batch.binarytreemodel.dto.ModCashFlowCalRuleDto;
import com.joyintech.tams.model.valuation.batch.binarytreemodel.dto.ModCashFlowDto;
import com.joyintech.tams.model.valuation.batch.binarytreemodel.dto.ModCashFlowPayRulesDto;
import com.joyintech.tams.model.valuation.batch.binarytreemodel.dto.ModRateDataDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author zznn
 * @date 2021/7/6
 */
public class CashFlowDiscountReaderResult extends NeedOperateEntity {

    /**
     * 金融产品代码
     */
    private String finprodId;
    /**
     * 金融产品简称
     */
    private String finprodAbbr;
    /**
     * 市场代码
     */
    private String finprodMarketId;
    /**
     * 起息日
     */
    private Date vdate;
    /**
     * 到期日
     */
    private Date mdate;
    /**
     * 发行人
     */
    private String issuer;
    /**
     * 融资人
     */
    private String financier;

    /**
     * 担保人
     */
    private String sponsor;
    /**
     * 是否永续
     */
    private String isSus;
    /**
     * 是否含权
     */
    private String isRight;

    /**
     * 转股代码
     */
    private String conFinprodId;

    /**
     * 转股起始日
     */
    private Date conVdate;

    /**
     * 转股价格
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal conPrice;

    /**
     * 实际授信主体
     */
    private String actualCreaditProvid;
    /**
     * 资产一级分类
     */
    private String assetOne;

    /**
     * 资产二级分类
     */
    private String assetTwo;
    /**
     * 资产三级分类
     */
    private String assetThree;
    /**
     * 资产四级分类
     */
    private String assetFour;
    /**
     * 资产五级分类
     */
    private String assetFive;

    /**
     * 发行人是否持有金融牌照
     */
    private String issueFinLicence;

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
     * 息票品种
     */
    private String couponSpecies;

    /**
     * 发行价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal issuePrice;
    /**
     * 是否模型法估值
     */
    private String modyorn;

    /**
     * 最近买入交易发生日
     */
    private Date lastTradeInDate;

    /**
     * 最近买入交易日交易均价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lastTradeAvgPrice;

    /**
     * 贴现法曲线配置
     */
    private ModCurveConfig modCurveConfig;

    /**
     * 曲线代码集合
     */
    private List<String>  curveCodes;

    /**
     * 现金流计划信息
     */
    private List<ModCashFlowDto> modCashFlows;
    /**
     * 现金流计算规则
     */
    private List<ModCashFlowCalRuleDto> modCashFlowCalRules;

    /**
     * 现金流支付规则
     */
    private List<ModCashFlowPayRulesDto> modCashFlowPayRules;

    /**
     * 评级信息
     */
    private ModRateDataDto modRateData;

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
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

    public String getIsSus() {
        return isSus;
    }

    public void setIsSus(String isSus) {
        this.isSus = isSus;
    }

    public String getIsRight() {
        return isRight;
    }

    public void setIsRight(String isRight) {
        this.isRight = isRight;
    }

    public String getConFinprodId() {
        return conFinprodId;
    }

    public void setConFinprodId(String conFinprodId) {
        this.conFinprodId = conFinprodId;
    }

    public Date getConVdate() {
        return conVdate;
    }

    public void setConVdate(Date conVdate) {
        this.conVdate = conVdate;
    }

    public BigDecimal getConPrice() {
        return conPrice;
    }

    public void setConPrice(BigDecimal conPrice) {
        this.conPrice = conPrice;
    }

    public String getActualCreaditProvid() {
        return actualCreaditProvid;
    }

    public void setActualCreaditProvid(String actualCreaditProvid) {
        this.actualCreaditProvid = actualCreaditProvid;
    }

    public String getAssetOne() {
        return assetOne;
    }

    public void setAssetOne(String assetOne) {
        this.assetOne = assetOne;
    }

    public String getAssetTwo() {
        return assetTwo;
    }

    public void setAssetTwo(String assetTwo) {
        this.assetTwo = assetTwo;
    }

    public String getAssetThree() {
        return assetThree;
    }

    public void setAssetThree(String assetThree) {
        this.assetThree = assetThree;
    }

    public String getAssetFour() {
        return assetFour;
    }

    public void setAssetFour(String assetFour) {
        this.assetFour = assetFour;
    }

    public String getAssetFive() {
        return assetFive;
    }

    public void setAssetFive(String assetFive) {
        this.assetFive = assetFive;
    }

    public String getIssueFinLicence() {
        return issueFinLicence;
    }

    public void setIssueFinLicence(String issueFinLicence) {
        this.issueFinLicence = issueFinLicence;
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

    public String getModyorn() {
        return modyorn;
    }

    public void setModyorn(String modyorn) {
        this.modyorn = modyorn;
    }

    public Date getLastTradeInDate() {
        return lastTradeInDate;
    }

    public void setLastTradeInDate(Date lastTradeInDate) {
        this.lastTradeInDate = lastTradeInDate;
    }

    public BigDecimal getLastTradeAvgPrice() {
        return lastTradeAvgPrice;
    }

    public void setLastTradeAvgPrice(BigDecimal lastTradeAvgPrice) {
        this.lastTradeAvgPrice = lastTradeAvgPrice;
    }

    public ModCurveConfig getModCurveConfig() {
        return modCurveConfig;
    }

    public void setModCurveConfig(ModCurveConfig modCurveConfig) {
        this.modCurveConfig = modCurveConfig;
    }

    public List<String> getCurveCodes() {
        return curveCodes;
    }

    public void setCurveCodes(List<String> curveCodes) {
        this.curveCodes = curveCodes;
    }

    public List<ModCashFlowDto> getModCashFlows() {
        return modCashFlows;
    }

    public void setModCashFlows(List<ModCashFlowDto> modCashFlows) {
        this.modCashFlows = modCashFlows;
    }

    public List<ModCashFlowCalRuleDto> getModCashFlowCalRules() {
        return modCashFlowCalRules;
    }

    public void setModCashFlowCalRules(List<ModCashFlowCalRuleDto> modCashFlowCalRules) {
        this.modCashFlowCalRules = modCashFlowCalRules;
    }

    public List<ModCashFlowPayRulesDto> getModCashFlowPayRules() {
        return modCashFlowPayRules;
    }

    public void setModCashFlowPayRules(List<ModCashFlowPayRulesDto> modCashFlowPayRules) {
        this.modCashFlowPayRules = modCashFlowPayRules;
    }

    public ModRateDataDto getModRateData() {
        return modRateData;
    }

    public void setModRateData(ModRateDataDto modRateData) {
        this.modRateData = modRateData;
    }
}

