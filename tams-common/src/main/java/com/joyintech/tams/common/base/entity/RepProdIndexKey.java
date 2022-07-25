package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class RepProdIndexKey extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;
    /**
     * 日期
     */
    private Date valueDate;
    /**
     * 母产品总资产：母产品的总资产余额，子产品为空
     */
    private BigDecimal pAssets;
    /**
     * 产品资产净值
     */
    private BigDecimal assetsValue;
    /**
     * 产品实收资本
     */
    private BigDecimal capital;
    /**
     * 产品单位净值：若产品为现管类产品，此处填写1.0000
     */
    private BigDecimal unitValue;
    /**
     * 产品单位累计净值：若产品为现管类产品，此处填写1.0000
     */
    private BigDecimal unitValueT;
    /**
     * 产品万份收益：现管类产品填写，非现管类产品置空
     */
    private BigDecimal wYield;
    /**
     * 产品七日年化收益率：现管类产品填写，非现管类产品置空，存储的是不乘以100
     */
    private BigDecimal yield7;
    /**
     * 数据状态
     */
    private String status;
    /**
     * 错误信息
     */
    private String errorMsg;

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

    public BigDecimal getpAssets() {
        return pAssets;
    }

    public void setpAssets(BigDecimal pAssets) {
        this.pAssets = pAssets;
    }

    public BigDecimal getAssetsValue() {
        return assetsValue;
    }

    public void setAssetsValue(BigDecimal assetsValue) {
        this.assetsValue = assetsValue;
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
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

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
