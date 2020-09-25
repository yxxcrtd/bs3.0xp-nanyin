package com.joyintech.tams.common.base.entity;

public class BokTradeOrder extends BokTradeOrderKey {
    /**
     * 处理顺序
     */
    private Long psOrder;

    /**
     * 取得 处理顺序
     * @return 处理顺序
     */
    public Long getPsOrder() {
        return psOrder;
    }

    /**
     * 设置 处理顺序
     * @param psOrder 处理顺序
     */
    public void setPsOrder(Long psOrder) {
        this.psOrder = psOrder;
    }
}