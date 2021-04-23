package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class MstSeat extends BaseEntity {
    /**
     * 席位号
     */
    private String seatId;

    /**
     * 券商代码
     */
    private String customerId;

    /**
     * 交易市场
     */
    private String tradeMarket;

    /**
     * null
     */
    private String remark;

    /**
     * 取得 席位号
     * @return 席位号
     */
    public String getSeatId() {
        return seatId;
    }

    /**
     * 设置 席位号
     * @param seatId 席位号
     */
    public void setSeatId(String seatId) {
        this.seatId = seatId == null ? null : seatId.trim();
    }

    /**
     * 取得 券商代码
     * @return 券商代码
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * 设置 券商代码
     * @param customerId 券商代码
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     * 取得 交易市场
     * @return 交易市场
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 交易市场
     * @param tradeMarket 交易市场
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket == null ? null : tradeMarket.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 null
     * @param remark null
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}