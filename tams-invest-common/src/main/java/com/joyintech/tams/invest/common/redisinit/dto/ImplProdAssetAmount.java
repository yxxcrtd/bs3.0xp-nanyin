package com.joyintech.tams.invest.common.redisinit.dto;

import java.math.BigDecimal;

import com.joyintech.tams.invest.common.riskinfrds.info.entity.IProdAssetAmount;

public class ImplProdAssetAmount implements IProdAssetAmount {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 8864524547957977972L;

    /**
     * 组合代码
     */
    private String portfolioCode;

    /**
     * 设置 组合代码
     * 
     * @param portfolioCode 组合代码
     */
    public void setPortfolioCode(String portfolioCode) {
        this.portfolioCode = portfolioCode;
    }

    @Override
    public String getPortfolioCode() {
        return this.portfolioCode;
    }

    /**
     * 资产代码
     */
    private String assetCode;

    /**
     * 设置 资产代码
     * 
     * @param portfolioCode 组合代码
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    @Override
    public String getAssetCode() {
        return this.assetCode;
    }

    /**
     * 资产规模-金额
     */
    private BigDecimal amount;

    /**
     * 设置 资产规模-金额
     * 
     * @param amount 资产规模-金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public BigDecimal getAmount() {
        return this.amount;
    }

    /**
     * 资产规模-数量
     */
    private BigDecimal num;

    /**
     * 设置 资产规模-数量
     * 
     * @param num 资产规模-数量
     */
    public void setNum(BigDecimal num) {
        this.num = num;
    }

    @Override
    public BigDecimal getNum() {
        return this.num;
    }

}
