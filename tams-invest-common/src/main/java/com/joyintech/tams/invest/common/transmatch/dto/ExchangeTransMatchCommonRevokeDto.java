package com.joyintech.tams.invest.common.transmatch.dto;

import com.joyintech.tams.common.base.entity.SettleTransTgMapping;

public class ExchangeTransMatchCommonRevokeDto extends SettleTransTgMapping {

    /**
     * 是否汇总交易
     */
    private String isTotalDeal;

    public String getIsTotalDeal() {
        return isTotalDeal;
    }

    public void setIsTotalDeal(String isTotalDeal) {
        this.isTotalDeal = isTotalDeal;
    }
}
