package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValFundMnfBaseInfo extends BaseEntity {
    /**
     * 资产代码
     */
    private String assetId;

    /**
     * 新增、修改、删除
     */
    private String dataActionSign;

    /**
     * 公开市场资产代码
     */
    private String marketCode;

    /**
     * 资产名称
     */
    private String assetName;

    /**
     * 货币基金
     */
    private String fundType;

    /**
     * 通道代码
     */
    private String channelId;

    /**
     * 发行日
     */
    private Date idate;

    /**
     * 管理人
     */
    private String managerId;

    /**
     * 境内外
     */
    private String blnArea;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 交易场所
     */
    private String tradeMarket;

    /**
     * 托管人
     */
    private String trustee;

    /**
     * 结转频率
     */
    private String carryoverFreq;

    /**
     * 申购确认速度
     */
    private Long purSpeed;

    /**
     * 赎回确认速度
     */
    private Long redSpeed;

    /**
     * 本金收益特征
     */
    private String capiProfFeature;

    /**
     * 是否按日复利
     */
    private String isCompoundInt;

    /**
     * 面值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal faceValue;

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
     * 取得 公开市场资产代码
     * @return 公开市场资产代码
     */
    public String getMarketCode() {
        return marketCode;
    }

    /**
     * 设置 公开市场资产代码
     * @param marketCode 公开市场资产代码
     */
    public void setMarketCode(String marketCode) {
        this.marketCode = marketCode == null ? null : marketCode.trim();
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
     * 取得 货币基金
     * @return 货币基金
     */
    public String getFundType() {
        return fundType;
    }

    /**
     * 设置 货币基金
     * @param fundType 货币基金
     */
    public void setFundType(String fundType) {
        this.fundType = fundType == null ? null : fundType.trim();
    }

    /**
     * 取得 通道代码
     * @return 通道代码
     */
    public String getChannelId() {
        return channelId;
    }

    /**
     * 设置 通道代码
     * @param channelId 通道代码
     */
    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    /**
     * 取得 发行日
     * @return 发行日
     */
    public Date getIdate() {
        return idate;
    }

    /**
     * 设置 发行日
     * @param idate 发行日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setIdate(Date idate) {
        this.idate = idate;
    }

    /**
     * 取得 管理人
     * @return 管理人
     */
    public String getManagerId() {
        return managerId;
    }

    /**
     * 设置 管理人
     * @param managerId 管理人
     */
    public void setManagerId(String managerId) {
        this.managerId = managerId == null ? null : managerId.trim();
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
     * 取得 托管人
     * @return 托管人
     */
    public String getTrustee() {
        return trustee;
    }

    /**
     * 设置 托管人
     * @param trustee 托管人
     */
    public void setTrustee(String trustee) {
        this.trustee = trustee == null ? null : trustee.trim();
    }

    /**
     * 取得 结转频率
     * @return 结转频率
     */
    public String getCarryoverFreq() {
        return carryoverFreq;
    }

    /**
     * 设置 结转频率
     * @param carryoverFreq 结转频率
     */
    public void setCarryoverFreq(String carryoverFreq) {
        this.carryoverFreq = carryoverFreq == null ? null : carryoverFreq.trim();
    }

    /**
     * 取得 申购确认速度
     * @return 申购确认速度
     */
    public Long getPurSpeed() {
        return purSpeed;
    }

    /**
     * 设置 申购确认速度
     * @param purSpeed 申购确认速度
     */
    public void setPurSpeed(Long purSpeed) {
        this.purSpeed = purSpeed;
    }

    /**
     * 取得 赎回确认速度
     * @return 赎回确认速度
     */
    public Long getRedSpeed() {
        return redSpeed;
    }

    /**
     * 设置 赎回确认速度
     * @param redSpeed 赎回确认速度
     */
    public void setRedSpeed(Long redSpeed) {
        this.redSpeed = redSpeed;
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
     * 取得 是否按日复利
     * @return 是否按日复利
     */
    public String getIsCompoundInt() {
        return isCompoundInt;
    }

    /**
     * 设置 是否按日复利
     * @param isCompoundInt 是否按日复利
     */
    public void setIsCompoundInt(String isCompoundInt) {
        this.isCompoundInt = isCompoundInt == null ? null : isCompoundInt.trim();
    }

    /**
     * 取得 面值
     * @return 面值
     */
    public BigDecimal getFaceValue() {
        return faceValue;
    }

    /**
     * 设置 面值
     * @param faceValue 面值
     */
    public void setFaceValue(BigDecimal faceValue) {
        this.faceValue = faceValue;
    }
}