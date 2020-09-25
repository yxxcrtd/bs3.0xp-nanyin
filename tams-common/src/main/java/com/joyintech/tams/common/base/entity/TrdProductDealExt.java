package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class TrdProductDealExt extends BaseEntity {
    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 投资组合代码
     */
    private String portfolioId;

    /**
     * 理财产品代码
     */
    private String prodId;

    /**
     * 是否有结算指令
     */
    private String hasOrder;

    /**
     * 生成方式
     */
    private String genType;

    /**
     * 主协议金融产品代码
     */
    private String mpFinprodId;

    /**
     * 主协议分支序号
     */
    private Long mpBranch;

    /**
     * 取得 交易编号
     * @return 交易编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 交易编号
     * @param tradeId 交易编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 取得 投资组合代码
     * @return 投资组合代码
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 投资组合代码
     * @param portfolioId 投资组合代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId == null ? null : portfolioId.trim();
    }

    /**
     * 取得 理财产品代码
     * @return 理财产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 理财产品代码
     * @param prodId 理财产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 是否有结算指令
     * @return 是否有结算指令
     */
    public String getHasOrder() {
        return hasOrder;
    }

    /**
     * 设置 是否有结算指令
     * @param hasOrder 是否有结算指令
     */
    public void setHasOrder(String hasOrder) {
        this.hasOrder = hasOrder == null ? null : hasOrder.trim();
    }

    /**
     * 取得 生成方式
     * @return 生成方式
     */
    public String getGenType() {
        return genType;
    }

    /**
     * 设置 生成方式
     * @param genType 生成方式
     */
    public void setGenType(String genType) {
        this.genType = genType == null ? null : genType.trim();
    }

    /**
     * 取得 主协议金融产品代码
     * @return 主协议金融产品代码
     */
    public String getMpFinprodId() {
        return mpFinprodId;
    }

    /**
     * 设置 主协议金融产品代码
     * @param mpFinprodId 主协议金融产品代码
     */
    public void setMpFinprodId(String mpFinprodId) {
        this.mpFinprodId = mpFinprodId == null ? null : mpFinprodId.trim();
    }

    /**
     * 取得 主协议分支序号
     * @return 主协议分支序号
     */
    public Long getMpBranch() {
        return mpBranch;
    }

    /**
     * 设置 主协议分支序号
     * @param mpBranch 主协议分支序号
     */
    public void setMpBranch(Long mpBranch) {
        this.mpBranch = mpBranch;
    }
}