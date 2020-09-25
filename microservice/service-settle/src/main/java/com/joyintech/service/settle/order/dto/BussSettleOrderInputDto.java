/*
 * 文件名：BussSettleOrderInputDto.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：杨智 修改时间：2018年09月20日 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.settle.order.dto;

import java.util.Map;

import com.joyintech.tams.common.base.entity.TrdProductDeal;

public class BussSettleOrderInputDto {

    /**
     * 金融产品交易Dto
     */
    private TrdProductDeal deal;

    /**
     * 清算编号
     */
    private String orderId;

    /**
     * 是否汇总交易
     */
    private String isTotalTrade;

    /**
     * 业务类型
     */
    private String finprodType2;

    /**
     * 收益类型
     */
    private String profitType;

    /**
     * 缓存map
     */
    private Map<String, String> cacheMap;

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
     * 获取 finprodType2
     *
     * @return finprodType2
     */
    public String getFinprodType2() {
        return finprodType2;
    }

    /**
     * 设置 finprodType2
     *
     * @param finprodType2 finprodType2
     */
    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }

    /**
     * 组合代码
     */
    private String portfolioId;

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

    /**
     * 获取 orderId
     *
     * @return orderId
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置 orderId
     *
     * @param orderId orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

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
     * 获取 profitType
     *
     * @return profitType
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 profitType
     *
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    /**
     * 获取 cacheMap
     *
     * @return cacheMap.
     */
    public Map<String, String> getCacheMap() {
        return cacheMap;
    }

    /**
     * 设置 cacheMap
     *
     * @param cacheMap cacheMap
     */
    public void setCacheMap(Map<String, String> cacheMap) {
        this.cacheMap = cacheMap;
    }

}
