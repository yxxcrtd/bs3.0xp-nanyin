/*
 * 文件名：IbuQuoteBondDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2020年5月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.background.component.uplink.interbank.business.dto;

import com.joyintech.background.component.uplink.interbank.database.entity.IbuQuoteBond;

/**
 * 现券银行间报价扩展对象DTO
 * 
 * @author Jiaoxujin
 * @version 1.0 2020年5月14日
 * @see IbuQuoteBondDto
 * @since 1.0
 */
public class IbuQuoteBondDto extends IbuQuoteBond {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 2168664244793862575L;

    /**
     * 市场代码
     */
    private String finprodMarketId;

    /**
     * 组合代码名称
     */
    private String portfolioName;

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    /**
     * 获取 portfolioName
     * 
     * @return portfolioName.
     */
    public String getPortfolioName() {
        return portfolioName;
    }

    /**
     * 设置 portfolioName
     * 
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "IbuQuoteBondDto [finprodMarketId=" + finprodMarketId + ", getQuoteId()="
               + getQuoteId() + ", getQuoteType()=" + getQuoteType() + ", getQuoteRoleType()="
               + getQuoteRoleType() + ", getVersion()=" + getVersion() + ", getExeType()="
               + getExeType() + ", getpQuoteId()=" + getpQuoteId() + ", getCfetsQuoteId()="
               + getCfetsQuoteId() + ", getFinprodId()=" + getFinprodId() + ", getFinprodType2()="
               + getFinprodType2() + ", getBranch()=" + getBranch() + ", getPortfolioId()="
               + getPortfolioId() + ", getEntrustId()=" + getEntrustId() + ", getQuoteDate()="
               + getQuoteDate() + ", getSettleDate()=" + getSettleDate() + ", getSettleSpeed()="
               + getSettleSpeed() + ", getDeliveryType()=" + getDeliveryType() + ", getSettType()="
               + getSettType() + ", getSettCcy()=" + getSettCcy() + ", getPs()=" + getPs()
               + ", getShareAmt()=" + getShareAmt() + ", getFpriceAmt()=" + getFpriceAmt()
               + ", getTradeStatus()=" + getTradeStatus() + ", getSendStatus()=" + getSendStatus()
               + ", getEffTime()=" + getEffTime() + ", getOuPartyId()=" + getOuPartyId()
               + ", getOuTraderId()=" + getOuTraderId() + ", getbVersion()=" + getbVersion()
               + ", getRemark()=" + getRemark() + ", getStatus()=" + getStatus()
               + ", getCreateUser()=" + getCreateUser() + ", getCreateDept()=" + getCreateDept()
               + ", getCreateTime()=" + getCreateTime() + ", getUpdateUser()=" + getUpdateUser()
               + ", getUpdateTime()=" + getUpdateTime() + ", getWorkflowId()=" + getWorkflowId()
               + ", getUpdateFlg()=" + getUpdateFlg() + "]";
    }
}
