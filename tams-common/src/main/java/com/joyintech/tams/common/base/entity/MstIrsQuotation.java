package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class MstIrsQuotation extends MstIrsQuotationKey {
    /**
     * 时刻，比如16:30
     */
    private String dataTime;

    /**
     * 利率行情代码
     */
    private String intRateId;

    /**
     * 曲线类型，定盘曲线、收盘曲线、行情曲线
     */
    private String curveType;

    /**
     * 曲线利率类型
     */
    private String curveIntType;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 报买
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bidPrice;

    /**
     * 报卖
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal offerPrice;

    /**
     * 均值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal avgPrice;

    /**
     * N
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal n;

    /**
     * K
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal k;

    /**
     * 录入方式，接口、手工等
     */
    private String inputType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 时刻，比如16:30
     * @return 时刻，比如16:30
     */
    public String getDataTime() {
        return dataTime;
    }

    /**
     * 设置 时刻，比如16:30
     * @param dataTime 时刻，比如16:30
     */
    public void setDataTime(String dataTime) {
        this.dataTime = dataTime == null ? null : dataTime.trim();
    }

    /**
     * 取得 利率行情代码
     * @return 利率行情代码
     */
    public String getIntRateId() {
        return intRateId;
    }

    /**
     * 设置 利率行情代码
     * @param intRateId 利率行情代码
     */
    public void setIntRateId(String intRateId) {
        this.intRateId = intRateId == null ? null : intRateId.trim();
    }

    /**
     * 取得 曲线类型，定盘曲线、收盘曲线、行情曲线
     * @return 曲线类型，定盘曲线、收盘曲线、行情曲线
     */
    public String getCurveType() {
        return curveType;
    }

    /**
     * 设置 曲线类型，定盘曲线、收盘曲线、行情曲线
     * @param curveType 曲线类型，定盘曲线、收盘曲线、行情曲线
     */
    public void setCurveType(String curveType) {
        this.curveType = curveType == null ? null : curveType.trim();
    }

    /**
     * 取得 曲线利率类型
     * @return 曲线利率类型
     */
    public String getCurveIntType() {
        return curveIntType;
    }

    /**
     * 设置 曲线利率类型
     * @param curveIntType 曲线利率类型
     */
    public void setCurveIntType(String curveIntType) {
        this.curveIntType = curveIntType == null ? null : curveIntType.trim();
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
     * 取得 报买
     * @return 报买
     */
    public BigDecimal getBidPrice() {
        return bidPrice;
    }

    /**
     * 设置 报买
     * @param bidPrice 报买
     */
    public void setBidPrice(BigDecimal bidPrice) {
        this.bidPrice = bidPrice;
    }

    /**
     * 取得 报卖
     * @return 报卖
     */
    public BigDecimal getOfferPrice() {
        return offerPrice;
    }

    /**
     * 设置 报卖
     * @param offerPrice 报卖
     */
    public void setOfferPrice(BigDecimal offerPrice) {
        this.offerPrice = offerPrice;
    }

    /**
     * 取得 均值
     * @return 均值
     */
    public BigDecimal getAvgPrice() {
        return avgPrice;
    }

    /**
     * 设置 均值
     * @param avgPrice 均值
     */
    public void setAvgPrice(BigDecimal avgPrice) {
        this.avgPrice = avgPrice;
    }

    /**
     * 取得 N
     * @return N
     */
    public BigDecimal getN() {
        return n;
    }

    /**
     * 设置 N
     * @param n N
     */
    public void setN(BigDecimal n) {
        this.n = n;
    }

    /**
     * 取得 K
     * @return K
     */
    public BigDecimal getK() {
        return k;
    }

    /**
     * 设置 K
     * @param k K
     */
    public void setK(BigDecimal k) {
        this.k = k;
    }

    /**
     * 取得 录入方式，接口、手工等
     * @return 录入方式，接口、手工等
     */
    public String getInputType() {
        return inputType;
    }

    /**
     * 设置 录入方式，接口、手工等
     * @param inputType 录入方式，接口、手工等
     */
    public void setInputType(String inputType) {
        this.inputType = inputType == null ? null : inputType.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}