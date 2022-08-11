package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class RepProdIndex extends RepProdIndexKey {
    /**
     * 母产品总资产：母产品的总资产余额，子产品为空
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pAssets;

    /**
     * 产品资产净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)    
    private BigDecimal assetsValue;

    /**
     * 产品实收资本
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal capital;

    /**
     * 产品单位净值：若产品为现管类产品，此处填写1.0000
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitValue;

    /**
     * 产品单位累计净值：若产品为现管类产品，此处填写1.0000
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitValueT;

    /**
     * 产品万份收益：现管类产品填写，非现管类产品置空
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal wYield;

    /**
     * 产品七日年化收益率：现管类产品填写，非现管类产品置空，存储的是不乘以100
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yield7;

    /**
     * 错误信息
     */
    private String errormsg;

    /**
     * 其他费用:非接口使用字段，预留给页面使用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal otherFee;

    /**
     * 取得 母产品总资产：母产品的总资产余额，子产品为空
     * @return 母产品总资产：母产品的总资产余额，子产品为空
     */
    public BigDecimal getpAssets() {
        return pAssets;
    }

    /**
     * 设置 母产品总资产：母产品的总资产余额，子产品为空
     * @param pAssets 母产品总资产：母产品的总资产余额，子产品为空
     */
    public void setpAssets(BigDecimal pAssets) {
        this.pAssets = pAssets;
    }

    /**
     * 取得 产品资产净值
     * @return 产品资产净值
     */
    public BigDecimal getAssetsValue() {
        return assetsValue;
    }

    /**
     * 设置 产品资产净值
     * @param assetsValue 产品资产净值
     */
    public void setAssetsValue(BigDecimal assetsValue) {
        this.assetsValue = assetsValue;
    }

    /**
     * 取得 产品实收资本
     * @return 产品实收资本
     */
    public BigDecimal getCapital() {
        return capital;
    }

    /**
     * 设置 产品实收资本
     * @param capital 产品实收资本
     */
    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    /**
     * 取得 产品单位净值：若产品为现管类产品，此处填写1.0000
     * @return 产品单位净值：若产品为现管类产品，此处填写1.0000
     */
    public BigDecimal getUnitValue() {
        return unitValue;
    }

    /**
     * 设置 产品单位净值：若产品为现管类产品，此处填写1.0000
     * @param unitValue 产品单位净值：若产品为现管类产品，此处填写1.0000
     */
    public void setUnitValue(BigDecimal unitValue) {
        this.unitValue = unitValue;
    }

    /**
     * 取得 产品单位累计净值：若产品为现管类产品，此处填写1.0000
     * @return 产品单位累计净值：若产品为现管类产品，此处填写1.0000
     */
    public BigDecimal getUnitValueT() {
        return unitValueT;
    }

    /**
     * 设置 产品单位累计净值：若产品为现管类产品，此处填写1.0000
     * @param unitValueT 产品单位累计净值：若产品为现管类产品，此处填写1.0000
     */
    public void setUnitValueT(BigDecimal unitValueT) {
        this.unitValueT = unitValueT;
    }

    /**
     * 取得 产品万份收益：现管类产品填写，非现管类产品置空
     * @return 产品万份收益：现管类产品填写，非现管类产品置空
     */
    public BigDecimal getwYield() {
        return wYield;
    }

    /**
     * 设置 产品万份收益：现管类产品填写，非现管类产品置空
     * @param wYield 产品万份收益：现管类产品填写，非现管类产品置空
     */
    public void setwYield(BigDecimal wYield) {
        this.wYield = wYield;
    }

    /**
     * 取得 产品七日年化收益率：现管类产品填写，非现管类产品置空，存储的是不乘以100
     * @return 产品七日年化收益率：现管类产品填写，非现管类产品置空，存储的是不乘以100
     */
    public BigDecimal getYield7() {
        return yield7;
    }

    /**
     * 设置 产品七日年化收益率：现管类产品填写，非现管类产品置空，存储的是不乘以100
     * @param yield7 产品七日年化收益率：现管类产品填写，非现管类产品置空，存储的是不乘以100
     */
    public void setYield7(BigDecimal yield7) {
        this.yield7 = yield7;
    }

    /**
     * 取得 错误信息
     * @return 错误信息
     */
    public String getErrormsg() {
        return errormsg;
    }

    /**
     * 设置 错误信息
     * @param errormsg 错误信息
     */
    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg == null ? null : errormsg.trim();
    }

    /**
     * 取得 其他费用:非接口使用字段，预留给页面使用
     * @return 其他费用:非接口使用字段，预留给页面使用
     */
    public BigDecimal getOtherFee() {
        return otherFee;
    }

    /**
     * 设置 其他费用:非接口使用字段，预留给页面使用
     * @param otherFee 其他费用:非接口使用字段，预留给页面使用
     */
    public void setOtherFee(BigDecimal otherFee) {
        this.otherFee = otherFee;
    }
}