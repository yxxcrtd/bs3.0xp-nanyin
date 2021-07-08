/*
 * 文件名：BussCombDistribtDto.java
 * 版权：Copyright 2020-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2021年01月25日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.tams.invest.entrust.etribrepo.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;

/**
 * 组合分配DTO
 * @author 王波
 * @date 2021年01月25日
 * @see BussCombDistribtDto
 * @since 1.0
 */
public class BussCombDistribtDto {

    /**
     * 组合ID
     */
    private String portfolioId;

    private String entrustId;
    
    /**
     * 质押券库
     */
    private String zyqk;
    
    public String getZyqk() {
		return zyqk;
	}

	public void setZyqk(String zyqk) {
		this.zyqk = zyqk;
	}

    /**
     * 券面总额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal entrustAmt;

    /**
     * 组合T日可用现金
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal proAvailableCash;

    /**
     * 组合T+1日可用现金
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal proAvailableCash1;


    /**
     * 托管户T日可用现金
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal secManageAvailablePosition;

    /**
     * 托管户T+1日可用现金
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal secManageAvailablePosition1;

    /**
     * 通道T日可用现金
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal channelAvailableCash;

    /**
     * 通道T+1日可用现金
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal channelAvailableCash1;

    /**
     *  资产持仓券面总额（万元）
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal assetPositionEntrustAmt;

    /**
     * 资产可用券面总额（万元）
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal assetAvailableEntrustAmt;

    public String getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public BigDecimal getEntrustAmt() {
        return entrustAmt;
    }

    public void setEntrustAmt(BigDecimal entrustAmt) {
        this.entrustAmt = entrustAmt;
    }

    public BigDecimal getProAvailableCash() {
        return proAvailableCash;
    }

    public void setProAvailableCash(BigDecimal proAvailableCash) {
        this.proAvailableCash = proAvailableCash;
    }

    public BigDecimal getProAvailableCash1() {
        return proAvailableCash1;
    }

    public void setProAvailableCash1(BigDecimal proAvailableCash1) {
        this.proAvailableCash1 = proAvailableCash1;
    }

    public BigDecimal getSecManageAvailablePosition() {
        return secManageAvailablePosition;
    }

    public void setSecManageAvailablePosition(BigDecimal secManageAvailablePosition) {
        this.secManageAvailablePosition = secManageAvailablePosition;
    }

    public BigDecimal getSecManageAvailablePosition1() {
        return secManageAvailablePosition1;
    }

    public void setSecManageAvailablePosition1(BigDecimal secManageAvailablePosition1) {
        this.secManageAvailablePosition1 = secManageAvailablePosition1;
    }

    public BigDecimal getChannelAvailableCash() {
        return channelAvailableCash;
    }

    public void setChannelAvailableCash(BigDecimal channelAvailableCash) {
        this.channelAvailableCash = channelAvailableCash;
    }

    public BigDecimal getChannelAvailableCash1() {
        return channelAvailableCash1;
    }

    public void setChannelAvailableCash1(BigDecimal channelAvailableCash1) {
        this.channelAvailableCash1 = channelAvailableCash1;
    }

    public BigDecimal getAssetPositionEntrustAmt() {
        return assetPositionEntrustAmt;
    }

    public void setAssetPositionEntrustAmt(BigDecimal assetPositionEntrustAmt) {
        this.assetPositionEntrustAmt = assetPositionEntrustAmt;
    }

    public BigDecimal getAssetAvailableEntrustAmt() {
        return assetAvailableEntrustAmt;
    }

    public void setAssetAvailableEntrustAmt(BigDecimal assetAvailableEntrustAmt) {
        this.assetAvailableEntrustAmt = assetAvailableEntrustAmt;
    }
    private String parentPortfolioId;


    public String getParentPortfolioId() {
        return parentPortfolioId;
    }

    public void setParentPortfolioId(String parentPortfolioId) {
        this.parentPortfolioId = parentPortfolioId;
    }
}
