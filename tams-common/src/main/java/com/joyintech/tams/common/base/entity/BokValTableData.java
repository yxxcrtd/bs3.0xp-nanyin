package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class BokValTableData extends BaseEntity {
    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 账套名称
     */
    private String booksetName;

    /**
     * 收益类型
     */
    private String profitType;

    /**
     * 估值日期
     */
    private Date valDate;

    /**
     * 明细区分
     */
    private String detailDist;

    /**
     * 分层代码
     */
    private String layeringId;

    /**
     * 科目号
     */
    private String subjectNo;

    /**
     * 科目名称
     */
    private String subjectName;

    /**
     * 上级科目号
     */
    private String fsubjectId;

    /**
     * 数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal numAmt;

    /**
     * 单位成本
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitCost;

    /**
     * 本币成本
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cost;

    /**
     * 本币成本占净值比
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal costPercent;

    /**
     * 行情收市价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal closePrice;

    /**
     * 本币估值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal marketValue;

    /**
     * 本币估值占净值比
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal valuePercent;

    /**
     * 本币估值增值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal valueIncrement;

    /**
     * 余额方向
     */
    private String balFlag;

    /**
     * 影子价格估值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal shadowPriceValue;

    /**
     * 行情日期
     */
    private Date marketValDate;

    /**
     * 原币币种
     */
    private String oCcy;

    /**
     * 汇率(原币对本币)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal exchangeRate;

    /**
     * 原币成本
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal oCost;

    /**
     * 原币估值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal oMarketValue;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 流水号
     * @param seqNo 流水号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 账套代码
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId == null ? null : booksetId.trim();
    }

    /**
     * 取得 账套名称
     * @return 账套名称
     */
    public String getBooksetName() {
        return booksetName;
    }

    /**
     * 设置 账套名称
     * @param booksetName 账套名称
     */
    public void setBooksetName(String booksetName) {
        this.booksetName = booksetName == null ? null : booksetName.trim();
    }

    /**
     * 取得 收益类型
     * @return 收益类型
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 收益类型
     * @param profitType 收益类型
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType == null ? null : profitType.trim();
    }

    /**
     * 取得 估值日期
     * @return 估值日期
     */
    public Date getValDate() {
        return valDate;
    }

    /**
     * 设置 估值日期
     * @param valDate 估值日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setValDate(Date valDate) {
        this.valDate = valDate;
    }

    /**
     * 取得 明细区分
     * @return 明细区分
     */
    public String getDetailDist() {
        return detailDist;
    }

    /**
     * 设置 明细区分
     * @param detailDist 明细区分
     */
    public void setDetailDist(String detailDist) {
        this.detailDist = detailDist == null ? null : detailDist.trim();
    }

    /**
     * 取得 分层代码
     * @return 分层代码
     */
    public String getLayeringId() {
        return layeringId;
    }

    /**
     * 设置 分层代码
     * @param layeringId 分层代码
     */
    public void setLayeringId(String layeringId) {
        this.layeringId = layeringId == null ? null : layeringId.trim();
    }

    /**
     * 取得 科目号
     * @return 科目号
     */
    public String getSubjectNo() {
        return subjectNo;
    }

    /**
     * 设置 科目号
     * @param subjectNo 科目号
     */
    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo == null ? null : subjectNo.trim();
    }

    /**
     * 取得 科目名称
     * @return 科目名称
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * 设置 科目名称
     * @param subjectName 科目名称
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName == null ? null : subjectName.trim();
    }

    /**
     * 取得 上级科目号
     * @return 上级科目号
     */
    public String getFsubjectId() {
        return fsubjectId;
    }

    /**
     * 设置 上级科目号
     * @param fsubjectId 上级科目号
     */
    public void setFsubjectId(String fsubjectId) {
        this.fsubjectId = fsubjectId == null ? null : fsubjectId.trim();
    }

    /**
     * 取得 数量
     * @return 数量
     */
    public BigDecimal getNumAmt() {
        return numAmt;
    }

    /**
     * 设置 数量
     * @param numAmt 数量
     */
    public void setNumAmt(BigDecimal numAmt) {
        this.numAmt = numAmt;
    }

    /**
     * 取得 单位成本
     * @return 单位成本
     */
    public BigDecimal getUnitCost() {
        return unitCost;
    }

    /**
     * 设置 单位成本
     * @param unitCost 单位成本
     */
    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    /**
     * 取得 本币成本
     * @return 本币成本
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * 设置 本币成本
     * @param cost 本币成本
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    /**
     * 取得 本币成本占净值比
     * @return 本币成本占净值比
     */
    public BigDecimal getCostPercent() {
        return costPercent;
    }

    /**
     * 设置 本币成本占净值比
     * @param costPercent 本币成本占净值比
     */
    public void setCostPercent(BigDecimal costPercent) {
        this.costPercent = costPercent;
    }

    /**
     * 取得 行情收市价
     * @return 行情收市价
     */
    public BigDecimal getClosePrice() {
        return closePrice;
    }

    /**
     * 设置 行情收市价
     * @param closePrice 行情收市价
     */
    public void setClosePrice(BigDecimal closePrice) {
        this.closePrice = closePrice;
    }

    /**
     * 取得 本币估值
     * @return 本币估值
     */
    public BigDecimal getMarketValue() {
        return marketValue;
    }

    /**
     * 设置 本币估值
     * @param marketValue 本币估值
     */
    public void setMarketValue(BigDecimal marketValue) {
        this.marketValue = marketValue;
    }

    /**
     * 取得 本币估值占净值比
     * @return 本币估值占净值比
     */
    public BigDecimal getValuePercent() {
        return valuePercent;
    }

    /**
     * 设置 本币估值占净值比
     * @param valuePercent 本币估值占净值比
     */
    public void setValuePercent(BigDecimal valuePercent) {
        this.valuePercent = valuePercent;
    }

    /**
     * 取得 本币估值增值
     * @return 本币估值增值
     */
    public BigDecimal getValueIncrement() {
        return valueIncrement;
    }

    /**
     * 设置 本币估值增值
     * @param valueIncrement 本币估值增值
     */
    public void setValueIncrement(BigDecimal valueIncrement) {
        this.valueIncrement = valueIncrement;
    }

    /**
     * 取得 余额方向
     * @return 余额方向
     */
    public String getBalFlag() {
        return balFlag;
    }

    /**
     * 设置 余额方向
     * @param balFlag 余额方向
     */
    public void setBalFlag(String balFlag) {
        this.balFlag = balFlag == null ? null : balFlag.trim();
    }

    /**
     * 取得 影子价格估值
     * @return 影子价格估值
     */
    public BigDecimal getShadowPriceValue() {
        return shadowPriceValue;
    }

    /**
     * 设置 影子价格估值
     * @param shadowPriceValue 影子价格估值
     */
    public void setShadowPriceValue(BigDecimal shadowPriceValue) {
        this.shadowPriceValue = shadowPriceValue;
    }

    /**
     * 取得 行情日期
     * @return 行情日期
     */
    public Date getMarketValDate() {
        return marketValDate;
    }

    /**
     * 设置 行情日期
     * @param marketValDate 行情日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMarketValDate(Date marketValDate) {
        this.marketValDate = marketValDate;
    }

    /**
     * 取得 原币币种
     * @return 原币币种
     */
    public String getoCcy() {
        return oCcy;
    }

    /**
     * 设置 原币币种
     * @param oCcy 原币币种
     */
    public void setoCcy(String oCcy) {
        this.oCcy = oCcy == null ? null : oCcy.trim();
    }

    /**
     * 取得 汇率(原币对本币)
     * @return 汇率(原币对本币)
     */
    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    /**
     * 设置 汇率(原币对本币)
     * @param exchangeRate 汇率(原币对本币)
     */
    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    /**
     * 取得 原币成本
     * @return 原币成本
     */
    public BigDecimal getoCost() {
        return oCost;
    }

    /**
     * 设置 原币成本
     * @param oCost 原币成本
     */
    public void setoCost(BigDecimal oCost) {
        this.oCost = oCost;
    }

    /**
     * 取得 原币估值
     * @return 原币估值
     */
    public BigDecimal getoMarketValue() {
        return oMarketValue;
    }

    /**
     * 设置 原币估值
     * @param oMarketValue 原币估值
     */
    public void setoMarketValue(BigDecimal oMarketValue) {
        this.oMarketValue = oMarketValue;
    }
}