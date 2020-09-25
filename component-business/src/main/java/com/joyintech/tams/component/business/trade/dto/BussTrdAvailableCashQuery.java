/*
 * 文件名：BussTrdAvailableCashQuery.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年12月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.trade.dto;

import java.math.BigDecimal;

/**
 * 组合可用持仓和组合可用现金实体类
 * @author fanshuanghu
 * @version 1.0 2018年12月1日
 * @see BussTrdAvailableCashQuery
 * @since 1.0
 */
public class BussTrdAvailableCashQuery {
    /**
     * 组合T日可用现金
     */
    public BigDecimal avalibleCash;
    /**
     * 组合T+1日可用现金
     */
    public BigDecimal avalibleCashT1;
    /**
     * 托管户剩余现金
     */
    public BigDecimal secAcctBalance;
    /**
     * 组合可用持仓
     */
    public BigDecimal assetPosition;
    /**
     * 通道户剩余现金
     */
    public BigDecimal channelAvailableCash;

    /**
     * 获取 channelAvailableCash
     *
     * @return channelAvailableCash
     */
    public BigDecimal getChannelAvailableCash() {
        return channelAvailableCash;
    }

    /**
     * 设置 channelAvailableCash
     *
     * @param channelAvailableCash channelAvailableCash
     */
    public void setChannelAvailableCash(BigDecimal channelAvailableCash) {
        this.channelAvailableCash = channelAvailableCash;
    }

    /**
     * 获取 avalibleCash
     * @return avalibleCash.
     */
    public BigDecimal getAvalibleCash() {
        return avalibleCash;
    }
    
    /**
     * 设置 avalibleCash
     * @param avalibleCash avalibleCash
     */
    public void setAvalibleCash(BigDecimal avalibleCash) {
        this.avalibleCash = avalibleCash;
    }
    
    /**
     * 获取 avalibleCashT1
     * @return avalibleCashT1.
     */
    public BigDecimal getAvalibleCashT1() {
        return avalibleCashT1;
    }
    
    /**
     * 设置 avalibleCashT1
     * @param avalibleCashT1 avalibleCashT1
     */
    public void setAvalibleCashT1(BigDecimal avalibleCashT1) {
        this.avalibleCashT1 = avalibleCashT1;
    }
    
    /**
     * 获取 secAcctBalance
     * @return secAcctBalance.
     */
    public BigDecimal getSecAcctBalance() {
        return secAcctBalance;
    }
    
    /**
     * 设置 secAcctBalance
     * @param secAcctBalance secAcctBalance
     */
    public void setSecAcctBalance(BigDecimal secAcctBalance) {
        this.secAcctBalance = secAcctBalance;
    }
    
    /**
     * 获取 assetPosition
     * @return assetPosition.
     */
    public BigDecimal getAssetPosition() {
        return assetPosition;
    }
    
    /**
     * 设置 assetPosition
     * @param assetPosition assetPosition
     */
    public void setAssetPosition(BigDecimal assetPosition) {
        this.assetPosition = assetPosition;
    }
    
}
