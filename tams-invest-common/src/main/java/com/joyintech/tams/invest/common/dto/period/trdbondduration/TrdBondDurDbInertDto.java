package com.joyintech.tams.invest.common.dto.period.trdbondduration;

import com.joyintech.tams.invest.common.dto.period.common.BaseTrdDurDbInertDto;

/**
 * @program: tams-invest-parent
 * @description: 数据库批量处理DTO
 * @author: 强成西
 * @create: 2018-10-16 16:43
 **/
public class TrdBondDurDbInertDto extends BaseTrdDurDbInertDto {

    /**
     * 交易编号
     */
    private String tradeId;

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }
}
