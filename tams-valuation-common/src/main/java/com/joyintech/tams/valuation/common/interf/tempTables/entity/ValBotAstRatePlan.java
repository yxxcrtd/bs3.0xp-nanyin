package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValBotAstRatePlan extends BaseEntity {
    /**
     * 资产代码
     */
    private String assetId;

    /**
     * 现金流序号
     */
    private Long cashNum;

    /**
     * 计息开始日
     */
    private Date vdate;

    /**
     * 计息结束日
     */
    private Date mdate;

    /**
     * 支付日
     */
    private Date payDate;

    /**
     * 应计利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitInt;

    /**
     * 新增、撤销
     */
    private String dataActionSign;

    /**
     * 计息本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intCap;

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
     * 取得 现金流序号
     * @return 现金流序号
     */
    public Long getCashNum() {
        return cashNum;
    }

    /**
     * 设置 现金流序号
     * @param cashNum 现金流序号
     */
    public void setCashNum(Long cashNum) {
        this.cashNum = cashNum;
    }

    /**
     * 取得 计息开始日
     * @return 计息开始日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 计息开始日
     * @param vdate 计息开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 计息结束日
     * @return 计息结束日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 计息结束日
     * @param mdate 计息结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 支付日
     * @return 支付日
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     * 设置 支付日
     * @param payDate 支付日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * 取得 应计利息
     * @return 应计利息
     */
    public BigDecimal getUnitInt() {
        return unitInt;
    }

    /**
     * 设置 应计利息
     * @param unitInt 应计利息
     */
    public void setUnitInt(BigDecimal unitInt) {
        this.unitInt = unitInt;
    }

    /**
     * 取得 新增、撤销
     * @return 新增、撤销
     */
    public String getDataActionSign() {
        return dataActionSign;
    }

    /**
     * 设置 新增、撤销
     * @param dataActionSign 新增、撤销
     */
    public void setDataActionSign(String dataActionSign) {
        this.dataActionSign = dataActionSign == null ? null : dataActionSign.trim();
    }

    /**
     * 取得 计息本金
     * @return 计息本金
     */
    public BigDecimal getIntCap() {
        return intCap;
    }

    /**
     * 设置 计息本金
     * @param intCap 计息本金
     */
    public void setIntCap(BigDecimal intCap) {
        this.intCap = intCap;
    }
}