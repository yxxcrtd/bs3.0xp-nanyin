package com.joyintech.tams.invest.common.transmatch.dto;

import com.joyintech.background.component.common.pack.common.base.BaseEntity;
import com.joyintech.tams.common.base.entity.SettleTransDataTotal;

import java.math.BigDecimal;
import java.util.List;

public class ExchangeTransMatchCommonDto extends SettleTransDataTotal {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 8844629119926341373L;

    /**
     * 匹配类型
     */
    private String matchType;


    /**
     * 剩余成交金额
     */
    private BigDecimal remainAmount;

    /**
     * 剩余成交数量
     */
    private BigDecimal remainNum;

    /**
     * 关联的指令编号list
     */
    private List<String> entrustIdList;

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public List<String> getEntrustIdList() {
        return entrustIdList;
    }

    public void setEntrustIdList(List<String> entrustIdList) {
        this.entrustIdList = entrustIdList;
    }

    public BigDecimal getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(BigDecimal remainAmount) {
        this.remainAmount = remainAmount;
    }

    public BigDecimal getRemainNum() {
        return remainNum;
    }

    public void setRemainNum(BigDecimal remainNum) {
        this.remainNum = remainNum;
    }
}
