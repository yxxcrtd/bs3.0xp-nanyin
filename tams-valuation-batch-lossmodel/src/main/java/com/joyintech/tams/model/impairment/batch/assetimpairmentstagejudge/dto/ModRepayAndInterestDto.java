package com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dto;

import java.util.Date;

/**
 * 还本付息dto
 */
public class ModRepayAndInterestDto {
    private Date planEndDate; // 计息截止日
    private String dealSide;// 交易方向
    private Date tradeDate; // 交易日期

    public Date getPlanEndDate() {
        return planEndDate;
    }

    public void setPlanEndDate(Date planEndDate) {
        this.planEndDate = planEndDate;
    }

    public String getDealSide() {
        return dealSide;
    }

    public void setDealSide(String dealSide) {
        this.dealSide = dealSide;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }
}
