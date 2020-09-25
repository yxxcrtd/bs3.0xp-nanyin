package com.joyintech.tams.invest.asset.interfaceDto;

import java.math.BigDecimal;

public class S012RecommenderInfoEntity {
    private  String RECOMMENDER;//资产推荐方
    private BigDecimal RECFEE_RATE;//资产推荐费率

    public String getRECOMMENDER() {
        return RECOMMENDER;
    }

    public void setRECOMMENDER(String RECOMMENDER) {
        this.RECOMMENDER = RECOMMENDER;
    }

    public BigDecimal getRECFEE_RATE() {
        return RECFEE_RATE;
    }

    public void setRECFEE_RATE(BigDecimal RECFEE_RATE) {
        this.RECFEE_RATE = RECFEE_RATE;
    }
}
