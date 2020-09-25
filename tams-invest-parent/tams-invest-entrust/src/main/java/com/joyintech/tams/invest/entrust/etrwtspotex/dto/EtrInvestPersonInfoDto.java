package com.joyintech.tams.invest.entrust.etrwtspotex.dto;

import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.trade.dto.BussEtrInvestPersonInfoDto;

public class EtrInvestPersonInfoDto extends BussEtrInvestPersonInfoDto {

    /**
     * 获取交易对手
     *
     * @return 交易对手
     */
    @NotEmpty(fieldName = "EtrWtSpotex.counterId", groups = {ValidateGroupEdit.class})
    @Override
    public String getCounterId() {
        return super.getCounterId();
    }
}
