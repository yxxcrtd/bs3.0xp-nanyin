package com.joyintech.tams.invest.common.position.dto;

import com.joyintech.tams.common.base.entity.FinTradeProduct;

import java.math.BigDecimal;

public class FinTradeProductDto extends FinTradeProduct {

    /**
     * 首期交易的 到期结算金额
     * @return
     */
    public BigDecimal getExpiredTradeAmt(){
        return super.getmTradeAmt();
    }

    /**
     * 首期交易的 到期利息
     * @return
     */
    public BigDecimal getExpiredIntAmt(){
        return super.getmIntAmt();
    }

}
