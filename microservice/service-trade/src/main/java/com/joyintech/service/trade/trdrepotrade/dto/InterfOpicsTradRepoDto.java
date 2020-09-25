package com.joyintech.service.trade.trdrepotrade.dto;

import com.joyintech.tams.common.base.entity.InterfOpicsTradRepo;

import java.math.BigDecimal;

/**
 * @ClassName InterfOpicsTradRepoDto
 * @Description: TODO
 * @Author wangxuelei
 * @Date 2020/1/8 0008
 * @Version V1.0
 **/
public class InterfOpicsTradRepoDto extends InterfOpicsTradRepo {
    private Integer cashHoldingDays;//实际占款天数

    private BigDecimal repoamtcstp;

    public BigDecimal getRepoamtcstp() {
        return repoamtcstp;
    }

    public void setRepoamtcstp(BigDecimal repoamtcstp) {
        this.repoamtcstp = repoamtcstp;
    }

    public Integer getCashHoldingDays() {
        return cashHoldingDays;
    }

    public void setCashHoldingDays(Integer cashHoldingDays) {
        this.cashHoldingDays = cashHoldingDays;
    }
}
