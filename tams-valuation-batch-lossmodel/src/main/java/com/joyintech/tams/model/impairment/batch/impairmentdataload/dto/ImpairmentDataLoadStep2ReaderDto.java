package com.joyintech.tams.model.impairment.batch.impairmentdataload.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 二叉树模型-reader
 */
public class ImpairmentDataLoadStep2ReaderDto extends NeedOperateEntity {

    /**
     * 序列号
     */
    private String seqno;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 投资组合代码（有子组合是子组合代码，没有子组合是母组合代码）
     */
    private String portfolioId;

    /**
     * 资产代码
     */
    private String assetId;

    /**
     * 资产名称
     */
    private String assetName;

    /**
     * 市场代码
     */
    private String marketId;

    /**
     * TYPE_8
     */
    private String type8;

    /**
     * TYPE_9
     */
    private String type9;

    /**
     * TYPE_10
     */
    private String type10;

    /**
     * TYPE_11
     */
    private String type11;

    /**
     * TYPE_12
     */
    private String type12;

    /**
     * 模型资产类型
     */
    private String modelAssetType;

    /**
     * 模型资产细分类
     */
    private String modelAssetDetailType;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 融资人
     */
    private String financier;

    /**
     * 评级机构
     */
    private String gradeOrgId;

    /**
     * 发行人
     */
    private String issuer;

    /**
     * 实际授信主体
     */
    private String actualCredit;

    /**
     * 担保人
     */
    private String sponsor;

    /**
     * 风险五级分类
     */
    private String riskLevelFive;

    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 交易日期
     */
    private Date tradeDate;

    /**
     * 交易方向
     */
    private String ps;

    /**
     * 交易份额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal shareAmt;

    /**
     * 到期收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yield;

    /**
     * 票面利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal paperRate;

    /**
     * 计划截止日
     */
    private Date planEndDate;

    /**
     * 是否结清
     */
    private String isClean;

    /**
     * 减值日期
     */
    private Date decDate;

    /**
     * 评级类型
     */
    private String oType;

    /**
     * 债项评级结果（最新）
     债券主体评级结果（最新）
     发行人评级结果（最新）
     融资人评级结果（最新）
     担保人评级结果（最新）
     实际授信主体评级结果（最新）
     买入时点评级结果
     最新评级结果
     债项评级结果（最新）
     */
    private String result01;

    /**
     * 债券主体评级结果（最新）
     */
    private String result02;

    /**
     * 发行人评级结果（最新）
     */
    private String resultIssuer;

    /**
     * 融资人评级结果（最新）
     */
    private String resultFinancier;

    /**
     * 担保人评级结果（最新）
     */
    private String resultSponsor;

    /**
     * 实际授信主体评级结果（最新）
     */
    private String resultActualCredit;

    /**
     * 最终使用评级类型
     */
    private String oTypeFinal;

    /**
     * 最终使用主体机构
     */
    private String orgIdFinal;

    /**
     * 主体是否持有金融牌照
     */
    private String isFinLicence;

    /**
     * 买入时点评级结果
     */
    private String resultDeal;

    /**
     * 最新评级结果
     */
    private String resultCurrent;

    /**
     * 本金逾期天数
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prinOverdueDay;

    /**
     * 利息逾期天数
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intOverdueDay;

    /**
     * 减值阶段
     */
    private String finStage;

    /**
     * 移动平均后份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal emaShareAmt;

    /**
     * EAD违约风险暴露
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ead;

    /**
     * 前瞻前PD违约概率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal preProspectivePd;

    /**
     * 前瞻后PD违约概率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prospectivePd;

    /**
     * LGD违约损失率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lgd;

    /**
     * 前瞻前ECL预期信用损失
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal preProspectiveEcl;

    /**
     * 前瞻后ECL预期信用损失
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prospectiveEcl;

    /**
     * 过程
     */
    private String process;

    /**
     * 是否含有抵质押物
     */
    private String isContainPledge;

    /**
     * 取得 序列号
     * @return 序列号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 序列号
     * @param seqno 序列号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 产品名称
     * @return 产品名称
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * 设置 产品名称
     * @param prodName 产品名称
     */
    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    /**
     * 取得 投资组合代码（有子组合是子组合代码，没有子组合是母组合代码）
     * @return 投资组合代码（有子组合是子组合代码，没有子组合是母组合代码）
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 投资组合代码（有子组合是子组合代码，没有子组合是母组合代码）
     * @param portfolioId 投资组合代码（有子组合是子组合代码，没有子组合是母组合代码）
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId == null ? null : portfolioId.trim();
    }

    /**
     * 取得 资产代码
     * @return 资产代码
     */
    public String getAssetId() {
        return assetId;
    }

    /**
     * 设置 资产代码
     * @param assetId 资产代码
     */
    public void setAssetId(String assetId) {
        this.assetId = assetId == null ? null : assetId.trim();
    }

    /**
     * 取得 资产名称
     * @return 资产名称
     */
    public String getAssetName() {
        return assetName;
    }

    /**
     * 设置 资产名称
     * @param assetName 资产名称
     */
    public void setAssetName(String assetName) {
        this.assetName = assetName == null ? null : assetName.trim();
    }

    /**
     * 取得 市场代码
     * @return 市场代码
     */
    public String getMarketId() {
        return marketId;
    }

    /**
     * 设置 市场代码
     * @param marketId 市场代码
     */
    public void setMarketId(String marketId) {
        this.marketId = marketId == null ? null : marketId.trim();
    }

    /**
     * 取得 TYPE_8
     * @return TYPE_8
     */
    public String getType8() {
        return type8;
    }

    /**
     * 设置 TYPE_8
     * @param type8 TYPE_8
     */
    public void setType8(String type8) {
        this.type8 = type8 == null ? null : type8.trim();
    }

    /**
     * 取得 TYPE_9
     * @return TYPE_9
     */
    public String getType9() {
        return type9;
    }

    /**
     * 设置 TYPE_9
     * @param type9 TYPE_9
     */
    public void setType9(String type9) {
        this.type9 = type9 == null ? null : type9.trim();
    }

    /**
     * 取得 TYPE_10
     * @return TYPE_10
     */
    public String getType10() {
        return type10;
    }

    /**
     * 设置 TYPE_10
     * @param type10 TYPE_10
     */
    public void setType10(String type10) {
        this.type10 = type10 == null ? null : type10.trim();
    }

    /**
     * 取得 TYPE_11
     * @return TYPE_11
     */
    public String getType11() {
        return type11;
    }

    /**
     * 设置 TYPE_11
     * @param type11 TYPE_11
     */
    public void setType11(String type11) {
        this.type11 = type11 == null ? null : type11.trim();
    }

    /**
     * 取得 TYPE_12
     * @return TYPE_12
     */
    public String getType12() {
        return type12;
    }

    /**
     * 设置 TYPE_12
     * @param type12 TYPE_12
     */
    public void setType12(String type12) {
        this.type12 = type12 == null ? null : type12.trim();
    }

    /**
     * 取得 模型资产类型
     * @return 模型资产类型
     */
    public String getModelAssetType() {
        return modelAssetType;
    }

    /**
     * 设置 模型资产类型
     * @param modelAssetType 模型资产类型
     */
    public void setModelAssetType(String modelAssetType) {
        this.modelAssetType = modelAssetType == null ? null : modelAssetType.trim();
    }

    /**
     * 取得 模型资产细分类
     * @return 模型资产细分类
     */
    public String getModelAssetDetailType() {
        return modelAssetDetailType;
    }

    /**
     * 设置 模型资产细分类
     * @param modelAssetDetailType 模型资产细分类
     */
    public void setModelAssetDetailType(String modelAssetDetailType) {
        this.modelAssetDetailType = modelAssetDetailType == null ? null : modelAssetDetailType.trim();
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
     * 取得 融资人
     * @return 融资人
     */
    public String getFinancier() {
        return financier;
    }

    /**
     * 设置 融资人
     * @param financier 融资人
     */
    public void setFinancier(String financier) {
        this.financier = financier == null ? null : financier.trim();
    }

    /**
     * 取得 评级机构
     * @return 评级机构
     */
    public String getGradeOrgId() {
        return gradeOrgId;
    }

    /**
     * 设置 评级机构
     * @param gradeOrgId 评级机构
     */
    public void setGradeOrgId(String gradeOrgId) {
        this.gradeOrgId = gradeOrgId == null ? null : gradeOrgId.trim();
    }

    /**
     * 取得 发行人
     * @return 发行人
     */
    public String getIssuer() {
        return issuer;
    }

    /**
     * 设置 发行人
     * @param issuer 发行人
     */
    public void setIssuer(String issuer) {
        this.issuer = issuer == null ? null : issuer.trim();
    }

    /**
     * 取得 实际授信主体
     * @return 实际授信主体
     */
    public String getActualCredit() {
        return actualCredit;
    }

    /**
     * 设置 实际授信主体
     * @param actualCredit 实际授信主体
     */
    public void setActualCredit(String actualCredit) {
        this.actualCredit = actualCredit == null ? null : actualCredit.trim();
    }

    /**
     * 取得 担保人
     * @return 担保人
     */
    public String getSponsor() {
        return sponsor;
    }

    /**
     * 设置 担保人
     * @param sponsor 担保人
     */
    public void setSponsor(String sponsor) {
        this.sponsor = sponsor == null ? null : sponsor.trim();
    }

    /**
     * 取得 风险五级分类
     * @return 风险五级分类
     */
    public String getRiskLevelFive() {
        return riskLevelFive;
    }

    /**
     * 设置 风险五级分类
     * @param riskLevelFive 风险五级分类
     */
    public void setRiskLevelFive(String riskLevelFive) {
        this.riskLevelFive = riskLevelFive == null ? null : riskLevelFive.trim();
    }

    /**
     * 取得 交易编号
     * @return 交易编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 交易编号
     * @param tradeId 交易编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 取得 交易日期
     * @return 交易日期
     */
    public Date getTradeDate() {
        return tradeDate;
    }

    /**
     * 设置 交易日期
     * @param tradeDate 交易日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * 取得 交易方向
     * @return 交易方向
     */
    public String getPs() {
        return ps;
    }

    /**
     * 设置 交易方向
     * @param ps 交易方向
     */
    public void setPs(String ps) {
        this.ps = ps == null ? null : ps.trim();
    }

    /**
     * 取得 交易份额
     * @return 交易份额
     */
    public BigDecimal getShareAmt() {
        return shareAmt;
    }

    /**
     * 设置 交易份额
     * @param shareAmt 交易份额
     */
    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }

    /**
     * 取得 到期收益率
     * @return 到期收益率
     */
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * 设置 到期收益率
     * @param yield 到期收益率
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    /**
     * 取得 票面利率
     * @return 票面利率
     */
    public BigDecimal getPaperRate() {
        return paperRate;
    }

    /**
     * 设置 票面利率
     * @param paperRate 票面利率
     */
    public void setPaperRate(BigDecimal paperRate) {
        this.paperRate = paperRate;
    }

    /**
     * 取得 计划截止日
     * @return 计划截止日
     */
    public Date getPlanEndDate() {
        return planEndDate;
    }

    /**
     * 设置 计划截止日
     * @param planEndDate 计划截止日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPlanEndDate(Date planEndDate) {
        this.planEndDate = planEndDate;
    }

    /**
     * 取得 是否结清
     * @return 是否结清
     */
    public String getIsClean() {
        return isClean;
    }

    /**
     * 设置 是否结清
     * @param isClean 是否结清
     */
    public void setIsClean(String isClean) {
        this.isClean = isClean == null ? null : isClean.trim();
    }

    /**
     * 取得 减值日期
     * @return 减值日期
     */
    public Date getDecDate() {
        return decDate;
    }

    /**
     * 设置 减值日期
     * @param decDate 减值日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setDecDate(Date decDate) {
        this.decDate = decDate;
    }

    /**
     * 取得 评级类型
     * @return 评级类型
     */
    public String getoType() {
        return oType;
    }

    /**
     * 设置 评级类型
     * @param oType 评级类型
     */
    public void setoType(String oType) {
        this.oType = oType == null ? null : oType.trim();
    }

    /**
     * 取得 债项评级结果（最新）
     债券主体评级结果（最新）
     发行人评级结果（最新）
     融资人评级结果（最新）
     担保人评级结果（最新）
     实际授信主体评级结果（最新）
     买入时点评级结果
     最新评级结果
     债项评级结果（最新）
     * @return 债项评级结果（最新）
    债券主体评级结果（最新）
    发行人评级结果（最新）
    融资人评级结果（最新）
    担保人评级结果（最新）
    实际授信主体评级结果（最新）
    买入时点评级结果
    最新评级结果
    债项评级结果（最新）
     */
    public String getResult01() {
        return result01;
    }

    /**
     * 设置 债项评级结果（最新）
     债券主体评级结果（最新）
     发行人评级结果（最新）
     融资人评级结果（最新）
     担保人评级结果（最新）
     实际授信主体评级结果（最新）
     买入时点评级结果
     最新评级结果
     债项评级结果（最新）
     * @param result01 债项评级结果（最新）
    债券主体评级结果（最新）
    发行人评级结果（最新）
    融资人评级结果（最新）
    担保人评级结果（最新）
    实际授信主体评级结果（最新）
    买入时点评级结果
    最新评级结果
    债项评级结果（最新）
     */
    public void setResult01(String result01) {
        this.result01 = result01 == null ? null : result01.trim();
    }

    /**
     * 取得 债券主体评级结果（最新）
     * @return 债券主体评级结果（最新）
     */
    public String getResult02() {
        return result02;
    }

    /**
     * 设置 债券主体评级结果（最新）
     * @param result02 债券主体评级结果（最新）
     */
    public void setResult02(String result02) {
        this.result02 = result02 == null ? null : result02.trim();
    }

    /**
     * 取得 发行人评级结果（最新）
     * @return 发行人评级结果（最新）
     */
    public String getResultIssuer() {
        return resultIssuer;
    }

    /**
     * 设置 发行人评级结果（最新）
     * @param resultIssuer 发行人评级结果（最新）
     */
    public void setResultIssuer(String resultIssuer) {
        this.resultIssuer = resultIssuer == null ? null : resultIssuer.trim();
    }

    /**
     * 取得 融资人评级结果（最新）
     * @return 融资人评级结果（最新）
     */
    public String getResultFinancier() {
        return resultFinancier;
    }

    /**
     * 设置 融资人评级结果（最新）
     * @param resultFinancier 融资人评级结果（最新）
     */
    public void setResultFinancier(String resultFinancier) {
        this.resultFinancier = resultFinancier == null ? null : resultFinancier.trim();
    }

    /**
     * 取得 担保人评级结果（最新）
     * @return 担保人评级结果（最新）
     */
    public String getResultSponsor() {
        return resultSponsor;
    }

    /**
     * 设置 担保人评级结果（最新）
     * @param resultSponsor 担保人评级结果（最新）
     */
    public void setResultSponsor(String resultSponsor) {
        this.resultSponsor = resultSponsor == null ? null : resultSponsor.trim();
    }

    /**
     * 取得 实际授信主体评级结果（最新）
     * @return 实际授信主体评级结果（最新）
     */
    public String getResultActualCredit() {
        return resultActualCredit;
    }

    /**
     * 设置 实际授信主体评级结果（最新）
     * @param resultActualCredit 实际授信主体评级结果（最新）
     */
    public void setResultActualCredit(String resultActualCredit) {
        this.resultActualCredit = resultActualCredit == null ? null : resultActualCredit.trim();
    }

    /**
     * 取得 最终使用评级类型
     * @return 最终使用评级类型
     */
    public String getoTypeFinal() {
        return oTypeFinal;
    }

    /**
     * 设置 最终使用评级类型
     * @param oTypeFinal 最终使用评级类型
     */
    public void setoTypeFinal(String oTypeFinal) {
        this.oTypeFinal = oTypeFinal == null ? null : oTypeFinal.trim();
    }

    /**
     * 取得 最终使用主体机构
     * @return 最终使用主体机构
     */
    public String getOrgIdFinal() {
        return orgIdFinal;
    }

    /**
     * 设置 最终使用主体机构
     * @param orgIdFinal 最终使用主体机构
     */
    public void setOrgIdFinal(String orgIdFinal) {
        this.orgIdFinal = orgIdFinal == null ? null : orgIdFinal.trim();
    }

    /**
     * 取得 主体是否持有金融牌照
     * @return 主体是否持有金融牌照
     */
    public String getIsFinLicence() {
        return isFinLicence;
    }

    /**
     * 设置 主体是否持有金融牌照
     * @param isFinLicence 主体是否持有金融牌照
     */
    public void setIsFinLicence(String isFinLicence) {
        this.isFinLicence = isFinLicence == null ? null : isFinLicence.trim();
    }

    /**
     * 取得 买入时点评级结果
     * @return 买入时点评级结果
     */
    public String getResultDeal() {
        return resultDeal;
    }

    /**
     * 设置 买入时点评级结果
     * @param resultDeal 买入时点评级结果
     */
    public void setResultDeal(String resultDeal) {
        this.resultDeal = resultDeal == null ? null : resultDeal.trim();
    }

    /**
     * 取得 最新评级结果
     * @return 最新评级结果
     */
    public String getResultCurrent() {
        return resultCurrent;
    }

    /**
     * 设置 最新评级结果
     * @param resultCurrent 最新评级结果
     */
    public void setResultCurrent(String resultCurrent) {
        this.resultCurrent = resultCurrent == null ? null : resultCurrent.trim();
    }

    /**
     * 取得 本金逾期天数
     * @return 本金逾期天数
     */
    public BigDecimal getPrinOverdueDay() {
        return prinOverdueDay;
    }

    /**
     * 设置 本金逾期天数
     * @param prinOverdueDay 本金逾期天数
     */
    public void setPrinOverdueDay(BigDecimal prinOverdueDay) {
        this.prinOverdueDay = prinOverdueDay;
    }

    /**
     * 取得 利息逾期天数
     * @return 利息逾期天数
     */
    public BigDecimal getIntOverdueDay() {
        return intOverdueDay;
    }

    /**
     * 设置 利息逾期天数
     * @param intOverdueDay 利息逾期天数
     */
    public void setIntOverdueDay(BigDecimal intOverdueDay) {
        this.intOverdueDay = intOverdueDay;
    }

    /**
     * 取得 减值阶段
     * @return 减值阶段
     */
    public String getFinStage() {
        return finStage;
    }

    /**
     * 设置 减值阶段
     * @param finStage 减值阶段
     */
    public void setFinStage(String finStage) {
        this.finStage = finStage == null ? null : finStage.trim();
    }

    /**
     * 取得 移动平均后份额
     * @return 移动平均后份额
     */
    public BigDecimal getEmaShareAmt() {
        return emaShareAmt;
    }

    /**
     * 设置 移动平均后份额
     * @param emaShareAmt 移动平均后份额
     */
    public void setEmaShareAmt(BigDecimal emaShareAmt) {
        this.emaShareAmt = emaShareAmt;
    }

    /**
     * 取得 EAD违约风险暴露
     * @return EAD违约风险暴露
     */
    public BigDecimal getEad() {
        return ead;
    }

    /**
     * 设置 EAD违约风险暴露
     * @param ead EAD违约风险暴露
     */
    public void setEad(BigDecimal ead) {
        this.ead = ead;
    }

    /**
     * 取得 前瞻前PD违约概率%
     * @return 前瞻前PD违约概率%
     */
    public BigDecimal getPreProspectivePd() {
        return preProspectivePd;
    }

    /**
     * 设置 前瞻前PD违约概率%
     * @param preProspectivePd 前瞻前PD违约概率%
     */
    public void setPreProspectivePd(BigDecimal preProspectivePd) {
        this.preProspectivePd = preProspectivePd;
    }

    /**
     * 取得 前瞻后PD违约概率%
     * @return 前瞻后PD违约概率%
     */
    public BigDecimal getProspectivePd() {
        return prospectivePd;
    }

    /**
     * 设置 前瞻后PD违约概率%
     * @param prospectivePd 前瞻后PD违约概率%
     */
    public void setProspectivePd(BigDecimal prospectivePd) {
        this.prospectivePd = prospectivePd;
    }

    /**
     * 取得 LGD违约损失率%
     * @return LGD违约损失率%
     */
    public BigDecimal getLgd() {
        return lgd;
    }

    /**
     * 设置 LGD违约损失率%
     * @param lgd LGD违约损失率%
     */
    public void setLgd(BigDecimal lgd) {
        this.lgd = lgd;
    }

    /**
     * 取得 前瞻前ECL预期信用损失
     * @return 前瞻前ECL预期信用损失
     */
    public BigDecimal getPreProspectiveEcl() {
        return preProspectiveEcl;
    }

    /**
     * 设置 前瞻前ECL预期信用损失
     * @param preProspectiveEcl 前瞻前ECL预期信用损失
     */
    public void setPreProspectiveEcl(BigDecimal preProspectiveEcl) {
        this.preProspectiveEcl = preProspectiveEcl;
    }

    /**
     * 取得 前瞻后ECL预期信用损失
     * @return 前瞻后ECL预期信用损失
     */
    public BigDecimal getProspectiveEcl() {
        return prospectiveEcl;
    }

    /**
     * 设置 前瞻后ECL预期信用损失
     * @param prospectiveEcl 前瞻后ECL预期信用损失
     */
    public void setProspectiveEcl(BigDecimal prospectiveEcl) {
        this.prospectiveEcl = prospectiveEcl;
    }

    /**
     * 取得 过程
     * @return 过程
     */
    public String getProcess() {
        return process;
    }

    /**
     * 设置 过程
     * @param process 过程
     */
    public void setProcess(String process) {
        this.process = process == null ? null : process.trim();
    }

    /**
     * 取得 是否含有抵质押物
     * @return 是否含有抵质押物
     */
    public String getIsContainPledge() {
        return isContainPledge;
    }

    /**
     * 设置 是否含有抵质押物
     * @param isContainPledge 是否含有抵质押物
     */
    public void setIsContainPledge(String isContainPledge) {
        this.isContainPledge = isContainPledge == null ? null : isContainPledge.trim();
    }
}
