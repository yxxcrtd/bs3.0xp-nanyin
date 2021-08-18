package com.joyintech.tams.model.valuation.batch.incomemethodmodel.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.valuation.common.dto.modelDto.ModCashFlowCalRuleDto;
import com.joyintech.tams.valuation.common.dto.modelDto.ModCashFlowDto;
import com.joyintech.tams.valuation.common.dto.modelDto.ModCashFlowPayRulesDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description:资产信息实体类
 * @auther: yuzj
 * @date: 2021/7/3
 * @param:
 * @return:
 */
public class ProductAssetsIncomeMethodReaderDto extends NeedOperateEntity {
    private static final long serialVersionUID = 1L;
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
    private String financie;

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
    private String conShareId;

    /**
     * 转股起始日
     */
    private Date convdate;

    /**
     * 转股价格
     */
    private BigDecimal pbondvalue;
    /**
     * 实际授信主体
     */
    private String actualCreditProvid;

    /**
     * 资产一级分类
     */
    private String assetType1;
    /**
     * 资产二级分类
     */
    private String assetType2;
    /**
     * 资产三级分类
     */
    private String assetType3;
    /**
     * 资产四级分类
     */
    private String assetType4;
    /**
     * 资产五级分类
     */
    private String assetType5;
    /**
     * 最近买入交易发生日
     */
    private Date latestTradeDate;

    /**
     * 最近买入交易日交易均价
     */
    private BigDecimal latestAverPrice;


    /**
     * 发行人是否持有金融牌照
     */
    private String isFinLicenceIssuer;

    /**
     * 融资人是否持有金融牌照
     */
    private String isFinLicenceFinancie;
    /**
     * 担保人是否持有金融牌照
     */
    private String isFinLicenceSponsor;
    /**
     * 实际授信主体是否持有金融牌照
     */
    private String isFinLicenceActualCredit;

    /**
     * 模型证券品种
     */
    private String secType;
    /**
     * 模型证券细分类
     */
    private String secDetailType;
    /**
     * List<现金流计划>
     */
    private List<ModCashFlowDto> FinCashPlanDtoList;

    /**
     * List<现金流计算规则>
     */
    private List<ModCashFlowCalRuleDto> FinCashCalcRuleDtoList;

    /**
     * List<现金流支付规则>
     */
    private List<ModCashFlowPayRulesDto> modCashFlowPayRulesList;

    /**
     * 发行价
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal issuePrice;

    /**
     * 息票品种
     */
    private String couponSpecies;

    /**
     * 预期股息增长率
     */
    private BigDecimal stockIntarte;

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

    public String getFinancie() {
        return financie;
    }

    public void setFinancie(String financie) {
        this.financie = financie;
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

    public String getConShareId() {
        return conShareId;
    }

    public void setConShareId(String conShareId) {
        this.conShareId = conShareId;
    }

    public Date getConvdate() {
        return convdate;
    }

    public void setConvdate(Date convdate) {
        this.convdate = convdate;
    }

    public BigDecimal getPbondvalue() {
        return pbondvalue;
    }

    public void setPbondvalue(BigDecimal pbondvalue) {
        this.pbondvalue = pbondvalue;
    }

    public String getActualCreditProvid() {
        return actualCreditProvid;
    }

    public void setActualCreditProvid(String actualCreditProvid) {
        this.actualCreditProvid = actualCreditProvid;
    }

    public String getAssetType1() {
        return assetType1;
    }

    public void setAssetType1(String assetType1) {
        this.assetType1 = assetType1;
    }

    public String getAssetType2() {
        return assetType2;
    }

    public void setAssetType2(String assetType2) {
        this.assetType2 = assetType2;
    }

    public String getAssetType3() {
        return assetType3;
    }

    public void setAssetType3(String assetType3) {
        this.assetType3 = assetType3;
    }

    public String getAssetType4() {
        return assetType4;
    }

    public void setAssetType4(String assetType4) {
        this.assetType4 = assetType4;
    }

    public String getAssetType5() {
        return assetType5;
    }

    public void setAssetType5(String assetType5) {
        this.assetType5 = assetType5;
    }

    public BigDecimal getIssuePrice() {
        return issuePrice;
    }

    public void setIssuePrice(BigDecimal issuePrice) {
        this.issuePrice = issuePrice;
    }

    public BigDecimal getLatestAverPrice() {
        return latestAverPrice;
    }

    public void setLatestAverPrice(BigDecimal latestAverPrice) {
        this.latestAverPrice = latestAverPrice;
    }

    public String getIsFinLicenceIssuer() {
        return isFinLicenceIssuer;
    }

    public void setIsFinLicenceIssuer(String isFinLicenceIssuer) {
        this.isFinLicenceIssuer = isFinLicenceIssuer;
    }

    public String getIsFinLicenceFinancie() {
        return isFinLicenceFinancie;
    }

    public void setIsFinLicenceFinancie(String isFinLicenceFinancie) {
        this.isFinLicenceFinancie = isFinLicenceFinancie;
    }

    public String getCouponSpecies() {
        return couponSpecies;
    }

    public void setCouponSpecies(String couponSpecies) {
        this.couponSpecies = couponSpecies;
    }

    public Date getLatestTradeDate() {
        return latestTradeDate;
    }

    public void setLatestTradeDate(Date latestTradeDate) {
        this.latestTradeDate = latestTradeDate;
    }

    public String getIsFinLicenceSponsor() {
        return isFinLicenceSponsor;
    }

    public void setIsFinLicenceSponsor(String isFinLicenceSponsor) {
        this.isFinLicenceSponsor = isFinLicenceSponsor;
    }

    public String getIsFinLicenceActualCredit() {
        return isFinLicenceActualCredit;
    }

    public void setIsFinLicenceActualCredit(String isFinLicenceActualCredit) {
        this.isFinLicenceActualCredit = isFinLicenceActualCredit;
    }

    public String getSecType() {
        return secType;
    }

    public void setSecType(String secType) {
        this.secType = secType;
    }

    public String getSecDetailType() {
        return secDetailType;
    }

    public void setSecDetailType(String secDetailType) {
        this.secDetailType = secDetailType;
    }

    public BigDecimal getStockIntarte() {
        return stockIntarte;
    }

    public void setStockIntarte(BigDecimal stockIntarte) {
        this.stockIntarte = stockIntarte;
    }

    public List<ModCashFlowDto> getFinCashPlanDtoList() {
        return FinCashPlanDtoList;
    }

    public void setFinCashPlanDtoList(List<ModCashFlowDto> finCashPlanDtoList) {
        FinCashPlanDtoList = finCashPlanDtoList;
    }

    public List<ModCashFlowCalRuleDto> getFinCashCalcRuleDtoList() {
        return FinCashCalcRuleDtoList;
    }

    public void setFinCashCalcRuleDtoList(List<ModCashFlowCalRuleDto> finCashCalcRuleDtoList) {
        FinCashCalcRuleDtoList = finCashCalcRuleDtoList;
    }

    public List<ModCashFlowPayRulesDto> getModCashFlowPayRulesList() {
        return modCashFlowPayRulesList;
    }

    public void setModCashFlowPayRulesList(List<ModCashFlowPayRulesDto> modCashFlowPayRulesList) {
        this.modCashFlowPayRulesList = modCashFlowPayRulesList;
    }
}
