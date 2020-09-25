/*
 * 文件名：BatchBussSettleOrderParameterDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.common.dto;

import com.joyintech.tams.common.base.entity.TrdProductDeal;

/**
 * 清算指令生成参数dto（批处理用）
 *
 * @author etliu
 * @version 1.0 2018年12月13日
 * @see BatchBussSettleOrderParameterDto
 * @since 1.0
 */
public class BatchBussSettleOrderParameterDto {
    /**
     * 是否汇总交易
     */
    private String isTotalTrade;
    /**
     * 拆借类型
     */
    private String bankLendingType;
    /**
     * 组合代码
     */
    private String portfolioId;
    /**
     * 金融产品交易Entity
     */
    private TrdProductDeal deal;

    /**
     * 获取 isTotalTrade
     *
     * @return isTotalTrade
     */
    public String getIsTotalTrade() {
        return isTotalTrade;
    }

    /**
     * 设置 isTotalTrade
     *
     * @param isTotalTrade isTotalTrade
     */
    public void setIsTotalTrade(String isTotalTrade) {
        this.isTotalTrade = isTotalTrade;
    }

    /**
     * 获取 bankLendingType
     *
     * @return bankLendingType
     */
    public String getBankLendingType() {
        return bankLendingType;
    }

    /**
     * 设置 bankLendingType
     *
     * @param bankLendingType bankLendingType
     */
    public void setBankLendingType(String bankLendingType) {
        this.bankLendingType = bankLendingType;
    }

    /**
     * 获取 portfolioId
     *
     * @return portfolioId
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 portfolioId
     *
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    /**
     * 获取 deal
     *
     * @return deal
     */
    public TrdProductDeal getDeal() {
        return deal;
    }

    /**
     * 设置 deal
     *
     * @param deal deal
     */
    public void setDeal(TrdProductDeal deal) {
        this.deal = deal;
    }
}
