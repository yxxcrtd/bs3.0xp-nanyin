package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class ValAddValueTaxKey extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 场景代码
     */
    private String tableId;

    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 发生日期
     */
    private Date happenDate;

    /**
     * 记账日期
     */
    private Date accountDate;

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 场景代码
     * @return 场景代码
     */
    public String getTableId() {
        return tableId;
    }

    /**
     * 设置 场景代码
     * @param tableId 场景代码
     */
    public void setTableId(String tableId) {
        this.tableId = tableId == null ? null : tableId.trim();
    }

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
     * 取得 发生日期
     * @return 发生日期
     */
    public Date getHappenDate() {
        return happenDate;
    }

    /**
     * 设置 发生日期
     * @param happenDate 发生日期
     */
    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
    }

    /**
     * 取得 记账日期
     * @return 记账日期
     */
    public Date getAccountDate() {
        return accountDate;
    }

    /**
     * 设置 记账日期
     * @param accountDate 记账日期
     */
    public void setAccountDate(Date accountDate) {
        this.accountDate = accountDate;
    }
}