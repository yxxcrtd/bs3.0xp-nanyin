package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class ValRepDealAsset extends BaseEntity {
    /**
     * 回购起息的交易编号
     */
    private String tradeId;

    /**
     * 回购类型
     */
    private String tradeType;

    /**
     * 新增、修改、删除
     */
    private String dataActionSign;

    /**
     * 债券代码
     */
    private String assetId;

    /**
     * 债券当前面值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pledgeFaceValue;

    /**
     * 债券数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pledgeNumber;

    /**
     * 质押率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pledgeRatio;

    /**
     * 质押金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pledgeAmt;

    /**
     * 取得 回购起息的交易编号
     * @return 回购起息的交易编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 回购起息的交易编号
     * @param tradeId 回购起息的交易编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 取得 回购类型
     * @return 回购类型
     */
    public String getTradeType() {
        return tradeType;
    }

    /**
     * 设置 回购类型
     * @param tradeType 回购类型
     */
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
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
     * 取得 债券代码
     * @return 债券代码
     */
    public String getAssetId() {
        return assetId;
    }

    /**
     * 设置 债券代码
     * @param assetId 债券代码
     */
    public void setAssetId(String assetId) {
        this.assetId = assetId == null ? null : assetId.trim();
    }

    /**
     * 取得 债券当前面值
     * @return 债券当前面值
     */
    public BigDecimal getPledgeFaceValue() {
        return pledgeFaceValue;
    }

    /**
     * 设置 债券当前面值
     * @param pledgeFaceValue 债券当前面值
     */
    public void setPledgeFaceValue(BigDecimal pledgeFaceValue) {
        this.pledgeFaceValue = pledgeFaceValue;
    }

    /**
     * 取得 债券数量
     * @return 债券数量
     */
    public BigDecimal getPledgeNumber() {
        return pledgeNumber;
    }

    /**
     * 设置 债券数量
     * @param pledgeNumber 债券数量
     */
    public void setPledgeNumber(BigDecimal pledgeNumber) {
        this.pledgeNumber = pledgeNumber;
    }

    /**
     * 取得 质押率
     * @return 质押率
     */
    public BigDecimal getPledgeRatio() {
        return pledgeRatio;
    }

    /**
     * 设置 质押率
     * @param pledgeRatio 质押率
     */
    public void setPledgeRatio(BigDecimal pledgeRatio) {
        this.pledgeRatio = pledgeRatio;
    }

    /**
     * 取得 质押金额
     * @return 质押金额
     */
    public BigDecimal getPledgeAmt() {
        return pledgeAmt;
    }

    /**
     * 设置 质押金额
     * @param pledgeAmt 质押金额
     */
    public void setPledgeAmt(BigDecimal pledgeAmt) {
        this.pledgeAmt = pledgeAmt;
    }
}