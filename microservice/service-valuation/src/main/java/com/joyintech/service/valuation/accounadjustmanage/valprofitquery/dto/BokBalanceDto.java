package com.joyintech.service.valuation.accounadjustmanage.valprofitquery.dto;

import com.joyintech.tams.common.base.entity.BokBalance;

/**
 * @author zhangwei
 * @createTime 2019-07-18 19:49
 */
public class BokBalanceDto extends BokBalance {

    /**
     * 核算方式，收益、净值、货币
     */
    private String acctMode;

    public String getAcctMode() {
        return acctMode;
    }

    public void setAcctMode(String acctMode) {
        this.acctMode = acctMode;
    }
}
