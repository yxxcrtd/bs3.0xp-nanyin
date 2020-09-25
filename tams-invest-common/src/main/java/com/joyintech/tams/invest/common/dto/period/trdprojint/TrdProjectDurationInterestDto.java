package com.joyintech.tams.invest.common.dto.period.trdprojint;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;

/**
 * @program: component-base
 * @description:
 * @author: 强成西
 * @create: 2018-10-23 14:54
 **/
public class TrdProjectDurationInterestDto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1098479514044301025L;

    /**
     * 项目类型
     */
    private String assetType;

    /**
     * 产品代码
     */
    private String finprodId;

    /**
     * 资产代码
     */
    private String assetId;

    /**
     * 资产名称
     */
    private String assetName;

    /**
     * 借款金额
     */
    private BigDecimal amount;

    /**
     * 成交日
     */
    private String tradeDate;

    /**
     * 起息日
     */
    private String vDate;

    /**
     * 到期日
     */
    private String mDate;

    /**
     * 付息周期
     */
    private String payCycle;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 获取 finprodId
     *
     * @return finprodId
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 finprodId
     *
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 assetType
     *
     * @return assetType
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * 设置 assetType
     *
     * @param assetType assetType
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    /**
     * 获取 assetId
     *
     * @return assetId
     */
    public String getAssetId() {
        return assetId;
    }

    /**
     * 设置 assetId
     *
     * @param assetId assetId
     */
    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    /**
     * 获取 assetName
     *
     * @return assetName
     */
    public String getAssetName() {
        return assetName;
    }

    /**
     * 设置 assetName
     *
     * @param assetName assetName
     */
    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    /**
     * 获取 amount
     *
     * @return amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置 amount
     *
     * @param amount amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取 tradeDate
     *
     * @return tradeDate
     */
    public String getTradeDate() {
        return tradeDate;
    }

    /**
     * 设置 tradeDate
     *
     * @param tradeDate tradeDate
     */
    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * 获取 vDate
     *
     * @return vDate
     */
    public String getvDate() {
        return vDate;
    }

    /**
     * 设置 vDate
     *
     * @param vDate vDate
     */
    public void setvDate(String vDate) {
        this.vDate = vDate;
    }

    /**
     * 获取 mDate
     *
     * @return mDate
     */
    public String getmDate() {
        return mDate;
    }

    /**
     * 设置 mDate
     *
     * @param mDate mDate
     */
    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    /**
     * 获取 ccy
     *
     * @return ccy
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 ccy
     *
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 payCycle
     *
     * @return payCycle
     */
    public String getPayCycle() {
        return payCycle;
    }

    /**
     * 设置 payCycle
     *
     * @param payCycle payCycle
     */
    public void setPayCycle(String payCycle) {
        this.payCycle = payCycle;
    }
}
