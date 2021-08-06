package com.joyintech.tams.model.impairment.batch.eadcalculation.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dto.ModCashFlowDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 二叉树模型-reader
 */
public class BinaryTreeModelReaderEntity extends NeedOperateEntity {

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
     * 资产配置信息
     */
    private ModBintreeConfig modBintreeConfig;

    /**
     * 资产现金流计划集合
     */
    private List<ModCashFlowDto> modCashFlowList;

    /**
     * 现金流计算规则集合
     */
    private List<ModCashFlowCalRuleDto> modCashFlowCalRuleList;

    /**
     * 现金流支付规则集合
     */
    private List<ModCashFlowPayRulesDto> modCashFlowPayRulesList;

    /**
     * 股票行情集合
     */
    private List<ModStockMarketInfoDto> modStockMarketInfoList;

    /**
     * 曲线行情集合
     */
    private List<ModYieldCurveInfoDto> modYieldCurveInfoList;

    public String getFinprodId() {
        return finprodId;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public Date getVdate() {
        return vdate;
    }

    public Date getMdate() {
        return mdate;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getFinancier() {
        return financier;
    }

    public String getSponsor() {
        return sponsor;
    }

    public String getIsSus() {
        return isSus;
    }

    public String getIsRight() {
        return isRight;
    }

    public String getConFinprodId() {
        return conFinprodId;
    }

    public Date getConVdate() {
        return conVdate;
    }

    public BigDecimal getConPrice() {
        return conPrice;
    }

    public String getActualCreaditProvid() {
        return actualCreaditProvid;
    }

    public String getAssetOne() {
        return assetOne;
    }

    public String getAssetTwo() {
        return assetTwo;
    }

    public String getAssetThree() {
        return assetThree;
    }

    public String getAssetFour() {
        return assetFour;
    }

    public String getAssetFive() {
        return assetFive;
    }

    public String getIssueFinLicence() {
        return issueFinLicence;
    }

    public String getFinancierFinLicence() {
        return financierFinLicence;
    }

    public String getSponsorFinLicence() {
        return sponsorFinLicence;
    }

    public String getActualCreditFinLicence() {
        return actualCreditFinLicence;
    }

    public String getCouponSpecies() {
        return couponSpecies;
    }

    public BigDecimal getIssuePrice() {
        return issuePrice;
    }

    public String getModyorn() {
        return modyorn;
    }

    public Date getLastTradeInDate() {
        return lastTradeInDate;
    }

    public BigDecimal getLastTradeAvgPrice() {
        return lastTradeAvgPrice;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public void setFinancier(String financier) {
        this.financier = financier;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public void setIsSus(String isSus) {
        this.isSus = isSus;
    }

    public void setIsRight(String isRight) {
        this.isRight = isRight;
    }

    public void setConFinprodId(String conFinprodId) {
        this.conFinprodId = conFinprodId;
    }

    public void setConVdate(Date conVdate) {
        this.conVdate = conVdate;
    }

    public void setConPrice(BigDecimal conPrice) {
        this.conPrice = conPrice;
    }

    public void setActualCreaditProvid(String actualCreaditProvid) {
        this.actualCreaditProvid = actualCreaditProvid;
    }

    public void setAssetOne(String assetOne) {
        this.assetOne = assetOne;
    }

    public void setAssetTwo(String assetTwo) {
        this.assetTwo = assetTwo;
    }

    public void setAssetThree(String assetThree) {
        this.assetThree = assetThree;
    }

    public void setAssetFour(String assetFour) {
        this.assetFour = assetFour;
    }

    public void setAssetFive(String assetFive) {
        this.assetFive = assetFive;
    }

    public void setIssueFinLicence(String issueFinLicence) {
        this.issueFinLicence = issueFinLicence;
    }

    public void setFinancierFinLicence(String financierFinLicence) {
        this.financierFinLicence = financierFinLicence;
    }

    public void setSponsorFinLicence(String sponsorFinLicence) {
        this.sponsorFinLicence = sponsorFinLicence;
    }

    public void setActualCreditFinLicence(String actualCreditFinLicence) {
        this.actualCreditFinLicence = actualCreditFinLicence;
    }

    public void setCouponSpecies(String couponSpecies) {
        this.couponSpecies = couponSpecies;
    }

    public void setIssuePrice(BigDecimal issuePrice) {
        this.issuePrice = issuePrice;
    }

    public void setModyorn(String modyorn) {
        this.modyorn = modyorn;
    }

    public void setLastTradeInDate(Date lastTradeInDate) {
        this.lastTradeInDate = lastTradeInDate;
    }

    public void setLastTradeAvgPrice(BigDecimal lastTradeAvgPrice) {
        this.lastTradeAvgPrice = lastTradeAvgPrice;
    };

    public List<ModCashFlowDto> getModCashFlowList() {
        return modCashFlowList;
    }


    public void setModCashFlowList(List<ModCashFlowDto> modCashFlowList) {
        this.modCashFlowList = modCashFlowList;
    }

    public List<ModCashFlowCalRuleDto> getModCashFlowCalRuleList() {
        return modCashFlowCalRuleList;
    }

    public void setModCashFlowCalRuleList(List<ModCashFlowCalRuleDto> modCashFlowCalRuleList) {
        this.modCashFlowCalRuleList = modCashFlowCalRuleList;
    }

    public List<ModStockMarketInfoDto> getModStockMarketInfoList() {
        return modStockMarketInfoList;
    }

    public void setModStockMarketInfoList(List<ModStockMarketInfoDto> modStockMarketInfoList) {
        this.modStockMarketInfoList = modStockMarketInfoList;
    }

    public ModBintreeConfig getModBintreeConfig() {
        return modBintreeConfig;
    }

    public void setModBintreeConfig(ModBintreeConfig modBintreeConfig) {
        this.modBintreeConfig = modBintreeConfig;
    }

    public List<ModYieldCurveInfoDto> getModYieldCurveInfoList() {
        return modYieldCurveInfoList;
    }

    public void setModYieldCurveInfoList(List<ModYieldCurveInfoDto> modYieldCurveInfoList) {
        this.modYieldCurveInfoList = modYieldCurveInfoList;
    }

    public List<ModCashFlowPayRulesDto> getModCashFlowPayRulesList() {
        return modCashFlowPayRulesList;
    }

    public void setModCashFlowPayRulesList(List<ModCashFlowPayRulesDto> modCashFlowPayRulesList) {
        this.modCashFlowPayRulesList = modCashFlowPayRulesList;
    }
}
