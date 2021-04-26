package com.joyintech.tams.settle.batch.datagather.dto;

import com.joyintech.tams.common.base.entity.SettleTransData;

/**
 * @author 何阳阳
 * @date 2020/7/17
 * @see SettleTransDataDto
 * @since 1.0
 */
public class SettleTransDataDto extends SettleTransData {
    /**
     * 指令编号
     */
    private String orderId;

    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 回购类型
     */
    private String repoType;

    /**
     * 投管交易编号
     */
    private String tradeIdTg;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getInvAim() {
        return invAim;
    }

    public void setInvAim(String invAim) {
        this.invAim = invAim;
    }

    public String getRepoType() {
        return repoType;
    }

    public void setRepoType(String repoType) {
        this.repoType = repoType;
    }

    public String getTradeIdTg() {
        return tradeIdTg;
    }

    public void setTradeIdTg(String tradeIdTg) {
        this.tradeIdTg = tradeIdTg;
    }
}
