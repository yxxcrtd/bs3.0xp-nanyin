package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValMngAssetInfo extends BaseEntity {
    /**
     * 资产代码
     */
    private String assetId;

    /**
     * 数据动作标志
     */
    private String dataActionSign;

    /**
     * 资产名称
     */
    private String assetName;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 计息天数
     */
    private String intDays;

    /**
     * 发行规律
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal issueAmt;

    /**
     * 境内外
     */
    private String blnArea;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 本金收益特征
     */
    private String capiProfFeature;

    /**
     * 交易场所
     */
    private String tradeMarket;

    /**
     * 资产分类
     */
    private String assetClass;

    /**
     * 是否通道
     */
    private String isChl;

    /**
     * 通道代码
     */
    private String chlId;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 利率类型
     */
    private String rateType;

    /**
     * 初始利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intRate;

    /**
     * 利率上限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rateMax;

    /**
     * 挂钩利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal linkRate;

    /**
     * 系数
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ratio;

    /**
     * 利率下限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rateMin;

    /**
     * 浮动利率编号
     */
    private String intRateId;

    /**
     * 利差
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal spreadRate;

    /**
     * 是否其他债权类资产
     * add by yanya on 20201222
     */
    private String isQtzq;

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
     * 取得 数据动作标志
     * @return 数据动作标志
     */
    public String getDataActionSign() {
        return dataActionSign;
    }

    /**
     * 设置 数据动作标志
     * @param dataActionSign 数据动作标志
     */
    public void setDataActionSign(String dataActionSign) {
        this.dataActionSign = dataActionSign == null ? null : dataActionSign.trim();
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
     * 取得 计息天数
     * @return 计息天数
     */
    public String getIntDays() {
        return intDays;
    }

    /**
     * 设置 计息天数
     * @param intDays 计息天数
     */
    public void setIntDays(String intDays) {
        this.intDays = intDays == null ? null : intDays.trim();
    }

    /**
     * 取得 发行规律
     * @return 发行规律
     */
    public BigDecimal getIssueAmt() {
        return issueAmt;
    }

    /**
     * 设置 发行规律
     * @param issueAmt 发行规律
     */
    public void setIssueAmt(BigDecimal issueAmt) {
        this.issueAmt = issueAmt;
    }

    /**
     * 取得 境内外
     * @return 境内外
     */
    public String getBlnArea() {
        return blnArea;
    }

    /**
     * 设置 境内外
     * @param blnArea 境内外
     */
    public void setBlnArea(String blnArea) {
        this.blnArea = blnArea == null ? null : blnArea.trim();
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
     * 取得 本金收益特征
     * @return 本金收益特征
     */
    public String getCapiProfFeature() {
        return capiProfFeature;
    }

    /**
     * 设置 本金收益特征
     * @param capiProfFeature 本金收益特征
     */
    public void setCapiProfFeature(String capiProfFeature) {
        this.capiProfFeature = capiProfFeature == null ? null : capiProfFeature.trim();
    }

    /**
     * 取得 交易场所
     * @return 交易场所
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 交易场所
     * @param tradeMarket 交易场所
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket == null ? null : tradeMarket.trim();
    }

    /**
     * 取得 资产分类
     * @return 资产分类
     */
    public String getAssetClass() {
        return assetClass;
    }

    /**
     * 设置 资产分类
     * @param assetClass 资产分类
     */
    public void setAssetClass(String assetClass) {
        this.assetClass = assetClass == null ? null : assetClass.trim();
    }

    /**
     * 取得 是否通道
     * @return 是否通道
     */
    public String getIsChl() {
        return isChl;
    }

    /**
     * 设置 是否通道
     * @param isChl 是否通道
     */
    public void setIsChl(String isChl) {
        this.isChl = isChl == null ? null : isChl.trim();
    }

    /**
     * 取得 通道代码
     * @return 通道代码
     */
    public String getChlId() {
        return chlId;
    }

    /**
     * 设置 通道代码
     * @param chlId 通道代码
     */
    public void setChlId(String chlId) {
        this.chlId = chlId == null ? null : chlId.trim();
    }

    /**
     * 取得 计息基础
     * @return 计息基础
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 计息基础
     * @param basis 计息基础
     */
    public void setBasis(String basis) {
        this.basis = basis == null ? null : basis.trim();
    }

    /**
     * 取得 利率类型
     * @return 利率类型
     */
    public String getRateType() {
        return rateType;
    }

    /**
     * 设置 利率类型
     * @param rateType 利率类型
     */
    public void setRateType(String rateType) {
        this.rateType = rateType == null ? null : rateType.trim();
    }

    /**
     * 取得 初始利率
     * @return 初始利率
     */
    public BigDecimal getIntRate() {
        return intRate;
    }

    /**
     * 设置 初始利率
     * @param intRate 初始利率
     */
    public void setIntRate(BigDecimal intRate) {
        this.intRate = intRate;
    }

    /**
     * 取得 利率上限
     * @return 利率上限
     */
    public BigDecimal getRateMax() {
        return rateMax;
    }

    /**
     * 设置 利率上限
     * @param rateMax 利率上限
     */
    public void setRateMax(BigDecimal rateMax) {
        this.rateMax = rateMax;
    }

    /**
     * 取得 挂钩利率
     * @return 挂钩利率
     */
    public BigDecimal getLinkRate() {
        return linkRate;
    }

    /**
     * 设置 挂钩利率
     * @param linkRate 挂钩利率
     */
    public void setLinkRate(BigDecimal linkRate) {
        this.linkRate = linkRate;
    }

    /**
     * 取得 系数
     * @return 系数
     */
    public BigDecimal getRatio() {
        return ratio;
    }

    /**
     * 设置 系数
     * @param ratio 系数
     */
    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    /**
     * 取得 利率下限
     * @return 利率下限
     */
    public BigDecimal getRateMin() {
        return rateMin;
    }

    /**
     * 设置 利率下限
     * @param rateMin 利率下限
     */
    public void setRateMin(BigDecimal rateMin) {
        this.rateMin = rateMin;
    }

    /**
     * 取得 浮动利率编号
     * @return 浮动利率编号
     */
    public String getIntRateId() {
        return intRateId;
    }

    /**
     * 设置 浮动利率编号
     * @param intRateId 浮动利率编号
     */
    public void setIntRateId(String intRateId) {
        this.intRateId = intRateId == null ? null : intRateId.trim();
    }

    /**
     * 取得 利差
     * @return 利差
     */
    public BigDecimal getSpreadRate() {
        return spreadRate;
    }

    /**
     * 设置 利差
     * @param spreadRate 利差
     */
    public void setSpreadRate(BigDecimal spreadRate) {
        this.spreadRate = spreadRate;
    }

    /**
     * 取得 是否其他债权类资产
     * @return isQtzq 是否其他债权类资产
     */
    public String getIsQtzq() {
        return isQtzq;
    }

    /**
     * 设置 是否其他债权类资产
     * @param isQtzq 是否其他债权类资产
     */
    public void setIsQtzq(String isQtzq) {
        this.isQtzq = isQtzq;
    }
    
    private String belongInstitutionCode;

    public String getBelongInstitutionCode() {
        return belongInstitutionCode;
    }

    public void setBelongInstitutionCode(String belongInstitutionCode) {
        this.belongInstitutionCode = belongInstitutionCode;
    }

}