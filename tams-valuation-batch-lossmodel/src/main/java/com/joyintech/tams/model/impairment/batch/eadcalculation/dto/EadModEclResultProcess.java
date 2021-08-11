package com.joyintech.tams.model.impairment.batch.eadcalculation.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.valuation.common.dto.modelDto.ModEclResultProcess;

import java.math.BigDecimal;

public class EadModEclResultProcess extends ModEclResultProcess {

    /**
     * 逾期时点移动平均份额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal limitEmaShareAmt = BigDecimal.ZERO;

    public BigDecimal getLimitEmaShareAmt() {
        return limitEmaShareAmt;
    }

    public void setLimitEmaShareAmt(BigDecimal limitEmaShareAmt) {
        this.limitEmaShareAmt = limitEmaShareAmt;
    }
}
