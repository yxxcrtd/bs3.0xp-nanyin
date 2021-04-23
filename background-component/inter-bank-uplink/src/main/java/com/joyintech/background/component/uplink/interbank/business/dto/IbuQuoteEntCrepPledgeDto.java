package com.joyintech.background.component.uplink.interbank.business.dto;

import com.joyintech.background.component.uplink.interbank.database.entity.IbuQuoteEntCrepPledge;

/**
 * @auther zhangnian
 * @date 2021/3/3
 */
public class IbuQuoteEntCrepPledgeDto extends IbuQuoteEntCrepPledge {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -302695451253888578L;

    /**
     * 债券代码
     */
    private String bondCode;

    public String getBondCode() {
        return bondCode;
    }

    public void setBondCode(String bondCode) {
        this.bondCode = bondCode;
    }
}

