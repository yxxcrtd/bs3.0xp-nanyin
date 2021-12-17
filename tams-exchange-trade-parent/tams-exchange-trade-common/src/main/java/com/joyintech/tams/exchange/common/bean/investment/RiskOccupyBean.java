package com.joyintech.tams.exchange.common.bean.investment;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 投管系统风控实占返回bean
 */
public class RiskOccupyBean {

    /**
     * 指标名称
     */
    private String rskStatus;

    /**
     * 指标名称
     */
    private String message;

    /**
     * 预计可投资金额
     */
    private BigDecimal preInvestAmt;
    @JsonProperty("RSK_LIST")
    private List<RiskResult> rskList;



    public String getRskStatus() {
        return rskStatus;
    }

    public void setRskStatus(String rskStatus) {
        this.rskStatus = rskStatus;
    }

    public BigDecimal getPreInvestAmt() {
        return preInvestAmt;
    }

    public void setPreInvestAmt(BigDecimal preInvestAmt) {
        this.preInvestAmt = preInvestAmt;
    }

    public List<RiskResult> getRskList() {
        return rskList;
    }

    public void setRskList(List<RiskResult> rskList) {
        this.rskList = rskList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
