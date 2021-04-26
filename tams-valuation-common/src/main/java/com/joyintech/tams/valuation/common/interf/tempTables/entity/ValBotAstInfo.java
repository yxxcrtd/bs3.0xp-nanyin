package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValBotAstInfo extends BaseEntity {
    /**
     * 资产代码
     */
    private String assetId;

    /**
     * 资产名称
     */
    private String assetName;

    /**
     * 新增、修改、删除
     */
    private String dataActionSign;

    /**
     * 资产发行方
     */
    private String prinAgent;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 资产类型
     */
    private String assetClass;

    /**
     * 存款类型
     */
    private String prinType;

    /**
     * 核算分类
     */
    private String valClas;

    /**
     * 本金金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal capital;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 境内外
     */
    private String inOut;

    /**
     * 交易场所
     */
    private String tradeMarket;

    /**
     * 本金收益特征
     */
    private String capiProfFeature;

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
    private BigDecimal initRate;

    /**
     * 挂钩利率
     */
    private String linkRate;

    /**
     * 系数
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ratio;

    /**
     * 利率上限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rateMax;

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
     * 取得 新增、修改、删除
     * @return 新增、修改、删除
     */
    public String getDataActionSign() {
        return dataActionSign;
    }

    /**
     * 设置 新增、修改、删除
     * @param dataActionSign 新增、修改、删除
     */
    public void setDataActionSign(String dataActionSign) {
        this.dataActionSign = dataActionSign == null ? null : dataActionSign.trim();
    }

    /**
     * 取得 资产发行方
     * @return 资产发行方
     */
    public String getPrinAgent() {
        return prinAgent;
    }

    /**
     * 设置 资产发行方
     * @param prinAgent 资产发行方
     */
    public void setPrinAgent(String prinAgent) {
        this.prinAgent = prinAgent == null ? null : prinAgent.trim();
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
     * 取得 资产类型
     * @return 资产类型
     */
    public String getAssetClass() {
        return assetClass;
    }

    /**
     * 设置 资产类型
     * @param assetClass 资产类型
     */
    public void setAssetClass(String assetClass) {
        this.assetClass = assetClass == null ? null : assetClass.trim();
    }

    /**
     * 取得 存款类型
     * @return 存款类型
     */
    public String getPrinType() {
        return prinType;
    }

    /**
     * 设置 存款类型
     * @param prinType 存款类型
     */
    public void setPrinType(String prinType) {
        this.prinType = prinType == null ? null : prinType.trim();
    }

    /**
     * 取得 核算分类
     * @return 核算分类
     */
    public String getValClas() {
        return valClas;
    }

    /**
     * 设置 核算分类
     * @param valClas 核算分类
     */
    public void setValClas(String valClas) {
        this.valClas = valClas == null ? null : valClas.trim();
    }

    /**
     * 取得 本金金额
     * @return 本金金额
     */
    public BigDecimal getCapital() {
        return capital;
    }

    /**
     * 设置 本金金额
     * @param capital 本金金额
     */
    public void setCapital(BigDecimal capital) {
        this.capital = capital;
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
     * 取得 境内外
     * @return 境内外
     */
    public String getInOut() {
        return inOut;
    }

    /**
     * 设置 境内外
     * @param inOut 境内外
     */
    public void setInOut(String inOut) {
        this.inOut = inOut == null ? null : inOut.trim();
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
    public BigDecimal getInitRate() {
        return initRate;
    }

    /**
     * 设置 初始利率
     * @param initRate 初始利率
     */
    public void setInitRate(BigDecimal initRate) {
        this.initRate = initRate;
    }

    /**
     * 取得 挂钩利率
     * @return 挂钩利率
     */
    public String getLinkRate() {
        return linkRate;
    }

    /**
     * 设置 挂钩利率
     * @param linkRate 挂钩利率
     */
    public void setLinkRate(String linkRate) {
        this.linkRate = linkRate == null ? null : linkRate.trim();
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
    
    private String belongInstitutionCode;

    public String getBelongInstitutionCode() {
        return belongInstitutionCode;
    }

    public void setBelongInstitutionCode(String belongInstitutionCode) {
        this.belongInstitutionCode = belongInstitutionCode;
    }
    
}