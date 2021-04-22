package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class TrdTradeFeeDetail extends TrdTradeFeeDetailKey {
    /**
     * 是否随交易支付
     */
    private String isPayWithTrade;

    /**
     * 费用金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal feeAmt;

    /**
     * 通道代码，通道费时填该值。
     */
    private String chlFinprodId;

    /**
     * 费用代码，计提式费用存现金流代码，一次性费用存费用类型
     */
    private String feeId;

    /**
     * 费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal feeRate;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 是否作为待摊
     */
    private String isPrepaid;

    /**
     * 更新时间
     */
    private String registOrg;

    /**
     * 取得 是否随交易支付
     * @return 是否随交易支付
     */
    public String getIsPayWithTrade() {
        return isPayWithTrade;
    }

    /**
     * 设置 是否随交易支付
     * @param isPayWithTrade 是否随交易支付
     */
    public void setIsPayWithTrade(String isPayWithTrade) {
        this.isPayWithTrade = isPayWithTrade == null ? null : isPayWithTrade.trim();
    }

    /**
     * 取得 费用金额
     * @return 费用金额
     */
    public BigDecimal getFeeAmt() {
        return feeAmt;
    }

    /**
     * 设置 费用金额
     * @param feeAmt 费用金额
     */
    public void setFeeAmt(BigDecimal feeAmt) {
        this.feeAmt = feeAmt;
    }

    /**
     * 取得 通道代码，通道费时填该值。
     * @return 通道代码，通道费时填该值。
     */
    public String getChlFinprodId() {
        return chlFinprodId;
    }

    /**
     * 设置 通道代码，通道费时填该值。
     * @param chlFinprodId 通道代码，通道费时填该值。
     */
    public void setChlFinprodId(String chlFinprodId) {
        this.chlFinprodId = chlFinprodId == null ? null : chlFinprodId.trim();
    }

    /**
     * 取得 费用代码，计提式费用存现金流代码，一次性费用存费用类型
     * @return 费用代码，计提式费用存现金流代码，一次性费用存费用类型
     */
    public String getFeeId() {
        return feeId;
    }

    /**
     * 设置 费用代码，计提式费用存现金流代码，一次性费用存费用类型
     * @param feeId 费用代码，计提式费用存现金流代码，一次性费用存费用类型
     */
    public void setFeeId(String feeId) {
        this.feeId = feeId == null ? null : feeId.trim();
    }

    /**
     * 取得 费率
     * @return 费率
     */
    public BigDecimal getFeeRate() {
        return feeRate;
    }

    /**
     * 设置 费率
     * @param feeRate 费率
     */
    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
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
     * 取得 是否作为待摊
     * @return 是否作为待摊
     */
    public String getIsPrepaid() {
        return isPrepaid;
    }

    /**
     * 设置 是否作为待摊
     * @param isPrepaid 是否作为待摊
     */
    public void setIsPrepaid(String isPrepaid) {
        this.isPrepaid = isPrepaid == null ? null : isPrepaid.trim();
    }

    /**
     * 取得 更新时间
     * @return 更新时间
     */
    public String getRegistOrg() {
        return registOrg;
    }

    /**
     * 设置 更新时间
     * @param registOrg 更新时间
     */
    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg == null ? null : registOrg.trim();
    }
}