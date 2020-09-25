package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class RskIndicatorParaCalcdetail extends BaseEntity {
    /**
     * 流水号
     */
    private String uuid;

    /**
     * 监督日期
     */
    private Date supDate;

    /**
     * 业务编号，包括委托编号，交易编号等
     */
    private String tradeId;

    /**
     * 业务类型，预占信息、实占信息
     */
    private String tradeType;

    /**
     * 参数代码
     */
    private String paraCode;

    /**
     * 组合或产品代码
     */
    private String portfolioId;

    /**
     * 资产代码
     */
    private String assetCode;

    /**
     * 参数粒度(预占，实占，日间，日终等)
     */
    private String paraGranularity;

    /**
     * 原值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal oldValue;

    /**
     * 新值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal newValue;

    /**
     * 变化值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal changeValue;

    /**
     * 报文时间戳
     */
    private String dotimestamp;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置 流水号
     * @param uuid 流水号
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * 取得 监督日期
     * @return 监督日期
     */
    public Date getSupDate() {
        return supDate;
    }

    /**
     * 设置 监督日期
     * @param supDate 监督日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSupDate(Date supDate) {
        this.supDate = supDate;
    }

    /**
     * 取得 业务编号，包括委托编号，交易编号等
     * @return 业务编号，包括委托编号，交易编号等
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 业务编号，包括委托编号，交易编号等
     * @param tradeId 业务编号，包括委托编号，交易编号等
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 取得 业务类型，预占信息、实占信息
     * @return 业务类型，预占信息、实占信息
     */
    public String getTradeType() {
        return tradeType;
    }

    /**
     * 设置 业务类型，预占信息、实占信息
     * @param tradeType 业务类型，预占信息、实占信息
     */
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    /**
     * 取得 参数代码
     * @return 参数代码
     */
    public String getParaCode() {
        return paraCode;
    }

    /**
     * 设置 参数代码
     * @param paraCode 参数代码
     */
    public void setParaCode(String paraCode) {
        this.paraCode = paraCode == null ? null : paraCode.trim();
    }

    /**
     * 取得 组合或产品代码
     * @return 组合或产品代码
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 组合或产品代码
     * @param portfolioId 组合或产品代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId == null ? null : portfolioId.trim();
    }

    /**
     * 取得 资产代码
     * @return 资产代码
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * 设置 资产代码
     * @param assetCode 资产代码
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    /**
     * 取得 参数粒度(预占，实占，日间，日终等)
     * @return 参数粒度(预占，实占，日间，日终等)
     */
    public String getParaGranularity() {
        return paraGranularity;
    }

    /**
     * 设置 参数粒度(预占，实占，日间，日终等)
     * @param paraGranularity 参数粒度(预占，实占，日间，日终等)
     */
    public void setParaGranularity(String paraGranularity) {
        this.paraGranularity = paraGranularity == null ? null : paraGranularity.trim();
    }

    /**
     * 取得 原值
     * @return 原值
     */
    public BigDecimal getOldValue() {
        return oldValue;
    }

    /**
     * 设置 原值
     * @param oldValue 原值
     */
    public void setOldValue(BigDecimal oldValue) {
        this.oldValue = oldValue;
    }

    /**
     * 取得 新值
     * @return 新值
     */
    public BigDecimal getNewValue() {
        return newValue;
    }

    /**
     * 设置 新值
     * @param newValue 新值
     */
    public void setNewValue(BigDecimal newValue) {
        this.newValue = newValue;
    }

    /**
     * 取得 变化值
     * @return 变化值
     */
    public BigDecimal getChangeValue() {
        return changeValue;
    }

    /**
     * 设置 变化值
     * @param changeValue 变化值
     */
    public void setChangeValue(BigDecimal changeValue) {
        this.changeValue = changeValue;
    }

    /**
     * 取得 报文时间戳
     * @return 报文时间戳
     */
    public String getDotimestamp() {
        return dotimestamp;
    }

    /**
     * 设置 报文时间戳
     * @param dotimestamp 报文时间戳
     */
    public void setDotimestamp(String dotimestamp) {
        this.dotimestamp = dotimestamp == null ? null : dotimestamp.trim();
    }
}