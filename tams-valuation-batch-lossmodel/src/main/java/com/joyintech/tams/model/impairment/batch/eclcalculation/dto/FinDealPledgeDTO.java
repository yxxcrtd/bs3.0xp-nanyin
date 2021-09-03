package com.joyintech.tams.model.impairment.batch.eclcalculation.dto;

import java.math.BigDecimal;

public class FinDealPledgeDTO {

    /**
     * 质押金融产品代码
     */
    private String pledgeFinprodId;

    /**
     * 质押金融产品类型
     */
    private String pledgeFinprodType;

    /**
     * 公开市场代码
     */
    private String finprodMarketId;

    /**
     * 质押金额
     */
    private BigDecimal pledgeAmt;

    /**
     * 证券品种
     */
    private String secType;

    /**
     * 证券细分类
     */
    private String secDetailType;

    public String getPledgeFinprodId() {
        return pledgeFinprodId;
    }

    public void setPledgeFinprodId(String pledgeFinprodId) {
        this.pledgeFinprodId = pledgeFinprodId;
    }

    public String getPledgeFinprodType() {
        return pledgeFinprodType;
    }

    public void setPledgeFinprodType(String pledgeFinprodType) {
        this.pledgeFinprodType = pledgeFinprodType;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public BigDecimal getPledgeAmt() {
        return pledgeAmt;
    }

    public void setPledgeAmt(BigDecimal pledgeAmt) {
        this.pledgeAmt = pledgeAmt;
    }

    public String getSecType() {
        return secType;
    }

    public void setSecType(String secType) {
        this.secType = secType;
    }

    public String getSecDetailType() {
        return secDetailType;
    }

    public void setSecDetailType(String secDetailType) {
        this.secDetailType = secDetailType;
    }

}
