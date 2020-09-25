/*
 * 文件名：Rsk008AssetEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.event.rsk008.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * RSK008输入-委托资产信息
 * 
 * @author zhangrui1
 * @version 1.0 2019年10月10日
 * @see Rsk008AssetEntity
 * @since 1.0
 */
public class Rsk008AssetEntity implements Serializable {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 50771172170360868L;

    /**
     * 资产代码
     */
    private String assetCode;

    /**
     * 资产名称
     */
    private String assetName;

    /**
     * 交易场所
     */
    private String market;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 价格
     */
    private BigDecimal priceAmt;

    /**
     * 数量
     */
    private BigDecimal tradeNum;

    /**
     * 取得 资产代码
     * 
     * @return 资产代码
     */
    public String getAssetCode() {
        return this.assetCode;
    }

    /**
     * 设置 资产代码
     * 
     * @param assetCode 资产代码
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    /**
     * 取得 资产名称
     * 
     * @return 资产名称
     */
    public String getAssetName() {
        return this.assetName;
    }

    /**
     * 设置 资产名称
     * 
     * @param assetName 资产名称
     */
    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    /**
     * 取得 交易场所
     * 
     * @return 交易场所
     */
    public String getMarket() {
        return this.market;
    }

    /**
     * 设置 交易场所
     * 
     * @param market 交易场所
     */
    public void setMarket(String market) {
        this.market = market;
    }

    /**
     * 取得 金额
     * 
     * @return 金额
     */
    public BigDecimal getAmount() {
        return this.amount;
    }

    /**
     * 设置 金额
     * 
     * @param amount 金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 取得 价格
     * 
     * @return 价格
     */
    public BigDecimal getPriceAmt() {
        return this.priceAmt;
    }

    /**
     * 设置 价格
     * 
     * @param priceAmt 价格
     */
    public void setPriceAmt(BigDecimal priceAmt) {
        this.priceAmt = priceAmt;
    }

    /**
     * 取得 数量
     * 
     * @return 数量
     */
    public BigDecimal getTradeNum() {
        return this.tradeNum;
    }

    /**
     * 设置 数量
     * 
     * @param tradeNum 数量
     */
    public void setTradeNum(BigDecimal tradeNum) {
        this.tradeNum = tradeNum;
    }
}
