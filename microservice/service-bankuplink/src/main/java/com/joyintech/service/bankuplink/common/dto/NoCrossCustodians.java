package com.joyintech.service.bankuplink.common.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;

/**
 * 跨托管
 * @auther zhangnian
 * @date 2021/3/24
 */
public class NoCrossCustodians {

    /**
     * 托管机构名称
     */
    private String crossCustodianInstitutionName;

    /**
     * 托管交易金额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal crossCustodianTradeAmt;

    public String getCrossCustodianInstitutionName() {
        return crossCustodianInstitutionName;
    }

    public void setCrossCustodianInstitutionName(String crossCustodianInstitutionName) {
        this.crossCustodianInstitutionName = crossCustodianInstitutionName;
    }

    public BigDecimal getCrossCustodianTradeAmt() {
        return crossCustodianTradeAmt;
    }

    public void setCrossCustodianTradeAmt(BigDecimal crossCustodianTradeAmt) {
        this.crossCustodianTradeAmt = crossCustodianTradeAmt;
    }
}

