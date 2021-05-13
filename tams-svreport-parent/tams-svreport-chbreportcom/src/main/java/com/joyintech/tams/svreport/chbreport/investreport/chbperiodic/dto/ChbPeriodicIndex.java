package com.joyintech.tams.svreport.chbreport.investreport.chbperiodic.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName ChbPeriodicIndex
 * @Description: 定期报告产品指标表
 * @Author WanShiWei
 * @Date 2021-05-07
 * @Version V1.0
 **/
public class ChbPeriodicIndex extends BaseEntity {

    private static final long serialVersionUID = 109399923067388230L;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 估值日
     */
    private Date valueDate;

    /**
     * 实收资本
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal capital;

    /**
     * 产品资产净值(按市值）
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal assetValueMarket;

    /**
     * 产品单位净值
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal unitValue;

    /**
     * 产品累计单位净值
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal unitValueT;

    /**
     * 万份收益
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal wYield;

    /**
     * 7日年化收益率%
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal yield7;

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public BigDecimal getAssetValueMarket() {
        return assetValueMarket;
    }

    public void setAssetValueMarket(BigDecimal assetValueMarket) {
        this.assetValueMarket = assetValueMarket;
    }

    public BigDecimal getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(BigDecimal unitValue) {
        this.unitValue = unitValue;
    }

    public BigDecimal getUnitValueT() {
        return unitValueT;
    }

    public void setUnitValueT(BigDecimal unitValueT) {
        this.unitValueT = unitValueT;
    }

    public BigDecimal getwYield() {
        return wYield;
    }

    public void setwYield(BigDecimal wYield) {
        this.wYield = wYield;
    }

    public BigDecimal getYield7() {
        return yield7;
    }

    public void setYield7(BigDecimal yield7) {
        this.yield7 = yield7;
    }
}
