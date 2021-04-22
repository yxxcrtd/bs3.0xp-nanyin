package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class TrdAssetLoss extends BaseEntity {
    /**
     * 交易代码
     */
    private String tradeId;

    /**
     * 市场代码
     */
    private String secId;

    /**
     * 减值阶段
     */
    private String stage;

    /**
     * 代偿期
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal peroid;

    /**
     * 行情日
     */
    private Date valDate;

    /**
     * 区间开始日
     */
    private Date strDate;

    /**
     * 区间结束日
     */
    private Date endDate;

    /**
     * 减值测算方式
     */
    private String lossTestType;

    /**
     * 减值录入方式
     */
    private String lossInputType;

    /**
     * 减值比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lossRatio;

    /**
     * PD违约概率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pdVaule;

    /**
     * LGD违约损失率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lgdVaule;

    /**
     * ECL预期信用损失比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal eclVaule;

    /**
     * 取得 交易代码
     * @return 交易代码
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 交易代码
     * @param tradeId 交易代码
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 取得 市场代码
     * @return 市场代码
     */
    public String getSecId() {
        return secId;
    }

    /**
     * 设置 市场代码
     * @param secId 市场代码
     */
    public void setSecId(String secId) {
        this.secId = secId == null ? null : secId.trim();
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
     * 取得 代偿期
     * @return 代偿期
     */
    public BigDecimal getPeroid() {
        return peroid;
    }

    /**
     * 设置 代偿期
     * @param peroid 代偿期
     */
    public void setPeroid(BigDecimal peroid) {
        this.peroid = peroid;
    }

    /**
     * 取得 行情日
     * @return 行情日
     */
    public Date getValDate() {
        return valDate;
    }

    /**
     * 设置 行情日
     * @param valDate 行情日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setValDate(Date valDate) {
        this.valDate = valDate;
    }

    /**
     * 取得 区间开始日
     * @return 区间开始日
     */
    public Date getStrDate() {
        return strDate;
    }

    /**
     * 设置 区间开始日
     * @param strDate 区间开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setStrDate(Date strDate) {
        this.strDate = strDate;
    }

    /**
     * 取得 区间结束日
     * @return 区间结束日
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置 区间结束日
     * @param endDate 区间结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 取得 减值测算方式
     * @return 减值测算方式
     */
    public String getLossTestType() {
        return lossTestType;
    }

    /**
     * 设置 减值测算方式
     * @param lossTestType 减值测算方式
     */
    public void setLossTestType(String lossTestType) {
        this.lossTestType = lossTestType == null ? null : lossTestType.trim();
    }

    /**
     * 取得 减值录入方式
     * @return 减值录入方式
     */
    public String getLossInputType() {
        return lossInputType;
    }

    /**
     * 设置 减值录入方式
     * @param lossInputType 减值录入方式
     */
    public void setLossInputType(String lossInputType) {
        this.lossInputType = lossInputType == null ? null : lossInputType.trim();
    }

    /**
     * 取得 减值比例
     * @return 减值比例
     */
    public BigDecimal getLossRatio() {
        return lossRatio;
    }

    /**
     * 设置 减值比例
     * @param lossRatio 减值比例
     */
    public void setLossRatio(BigDecimal lossRatio) {
        this.lossRatio = lossRatio;
    }

    /**
     * 取得 PD违约概率
     * @return PD违约概率
     */
    public BigDecimal getPdVaule() {
        return pdVaule;
    }

    /**
     * 设置 PD违约概率
     * @param pdVaule PD违约概率
     */
    public void setPdVaule(BigDecimal pdVaule) {
        this.pdVaule = pdVaule;
    }

    /**
     * 取得 LGD违约损失率
     * @return LGD违约损失率
     */
    public BigDecimal getLgdVaule() {
        return lgdVaule;
    }

    /**
     * 设置 LGD违约损失率
     * @param lgdVaule LGD违约损失率
     */
    public void setLgdVaule(BigDecimal lgdVaule) {
        this.lgdVaule = lgdVaule;
    }

    /**
     * 取得 ECL预期信用损失比例
     * @return ECL预期信用损失比例
     */
    public BigDecimal getEclVaule() {
        return eclVaule;
    }

    /**
     * 设置 ECL预期信用损失比例
     * @param eclVaule ECL预期信用损失比例
     */
    public void setEclVaule(BigDecimal eclVaule) {
        this.eclVaule = eclVaule;
    }
}