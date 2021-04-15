package com.joyintech.tams.invest.common.position.dto;

import com.joyintech.tams.common.base.entity.EtrEntrustAdd;

import java.math.BigDecimal;

public class EtrEntrustAddDto  extends EtrEntrustAdd {
    /**
     * 指令的 到期结算金额
     * @return
     */
    public BigDecimal getExpiredTradeAmt(){
        return super.getmTradeAmt();
    }

    /**
     * 指令的 到期利息
     * @return
     */
    public BigDecimal getExpiredIntAmt(){
        return super.getmIntAmt();
    }
}
