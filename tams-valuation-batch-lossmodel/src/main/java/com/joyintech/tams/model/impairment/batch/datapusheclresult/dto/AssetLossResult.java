package com.joyintech.tams.model.impairment.batch.datapusheclresult.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;

import java.math.BigDecimal;
import java.util.Date;

public class AssetLossResult extends BaseEntity {

    private AssetLossResult assetLossResult;

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
     * 投资组合代码
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
     * 资产类型
     */
    private String assetType;

    /**
     * 业务编号
     */
    private String bussNo;

    /**
     * 交易日期
     */
    private Date tradeDate;

    /**
     * 减值日期
     */
    private Date decDate;

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
     * 买入时点评级
     */
    private String purchaseTimeGrade;

    /**
     * 当前时点评级
     */
    private String currentTimeGrade;

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
     * 减值阶段
     */
    private String stage;

    /**
     * LGD违约损失率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lgd;

    /**
     * EAD违约风险暴露
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ead;

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
     * 是否允许模型覆盖
     */
    private String allowModelCover;

    /**
     * 数据来源
     */
    private String dataSource;

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
     * 取得 投资组合代码
     * @return 投资组合代码
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 投资组合代码
     * @param portfolioId 投资组合代码
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
     * 取得 资产类型
     * @return 资产类型
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * 设置 资产类型
     * @param assetType 资产类型
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType == null ? null : assetType.trim();
    }

    /**
     * 取得 业务编号
     * @return 业务编号
     */
    public String getBussNo() {
        return bussNo;
    }

    /**
     * 设置 业务编号
     * @param bussNo 业务编号
     */
    public void setBussNo(String bussNo) {
        this.bussNo = bussNo == null ? null : bussNo.trim();
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
     * 取得 买入时点评级
     * @return 买入时点评级
     */
    public String getPurchaseTimeGrade() {
        return purchaseTimeGrade;
    }

    /**
     * 设置 买入时点评级
     * @param purchaseTimeGrade 买入时点评级
     */
    public void setPurchaseTimeGrade(String purchaseTimeGrade) {
        this.purchaseTimeGrade = purchaseTimeGrade == null ? null : purchaseTimeGrade.trim();
    }

    /**
     * 取得 当前时点评级
     * @return 当前时点评级
     */
    public String getCurrentTimeGrade() {
        return currentTimeGrade;
    }

    /**
     * 设置 当前时点评级
     * @param currentTimeGrade 当前时点评级
     */
    public void setCurrentTimeGrade(String currentTimeGrade) {
        this.currentTimeGrade = currentTimeGrade == null ? null : currentTimeGrade.trim();
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
     * 取得 减值阶段
     * @return 减值阶段
     */
    public String getStage() {
        return stage;
    }

    /**
     * 设置 减值阶段
     * @param stage 减值阶段
     */
    public void setStage(String stage) {
        this.stage = stage == null ? null : stage.trim();
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
     * 取得 是否允许模型覆盖
     * @return 是否允许模型覆盖
     */
    public String getAllowModelCover() {
        return allowModelCover;
    }

    /**
     * 设置 是否允许模型覆盖
     * @param allowModelCover 是否允许模型覆盖
     */
    public void setAllowModelCover(String allowModelCover) {
        this.allowModelCover = allowModelCover == null ? null : allowModelCover.trim();
    }

    /**
     * 取得 数据来源
     * @return 数据来源
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * 设置 数据来源
     * @param dataSource 数据来源
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
    }
}