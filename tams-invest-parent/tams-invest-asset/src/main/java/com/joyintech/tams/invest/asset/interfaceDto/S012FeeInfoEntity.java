package com.joyintech.tams.invest.asset.interfaceDto;

import java.math.BigDecimal;

public class S012FeeInfoEntity {
    private  String FEE_TYPE;//费用类型
    private BigDecimal FEE_RATE;//初始费率

    public String getFEE_TYPE() {
        return FEE_TYPE;
    }

    public void setFEE_TYPE(String FEE_TYPE) {
        this.FEE_TYPE = FEE_TYPE;
    }

    public BigDecimal getFEE_RATE() {
        return FEE_RATE;
    }

    public void setFEE_RATE(BigDecimal FEE_RATE) {
        this.FEE_RATE = FEE_RATE;
    }
}
