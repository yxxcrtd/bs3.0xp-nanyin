package com.joyintech.tams.settle.batch.datagather.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.common.base.entity.SettleTransDataPledge;

import java.util.List;

/**
 * @author 何阳阳
 * @date 2020/7/16
 * @see InvestTradeReaderDto
 * @since 1.0
 */
public class InvestTradeReaderDto extends NeedOperateEntity {
    /**
     * 成交数据明细数据
     */
    private List<SettleTransDataDto> settleTransDataList;

    /**
     * 除权交易数据
     */
    private List<SettleDelDealDetailDto> settleTransDataDelDtoList;
    
    public InvestTradeReaderDto() {
    }

    /**
     * 成交数据明细质押品信息
     */
    private List<SettleTransDataPledge> settleTransDataPledgeList;

    public InvestTradeReaderDto(List<SettleTransDataDto> settleTransDataList) {
        this.settleTransDataList = settleTransDataList;
    }

    public List<SettleTransDataDto> getSettleTransDataList() {
        return settleTransDataList;
    }

    public void setSettleTransDataList(List<SettleTransDataDto> settleTransDataList) {
        this.settleTransDataList = settleTransDataList;
    }

    public List<SettleDelDealDetailDto> getSettleTransDataDelDtoList() {
        return settleTransDataDelDtoList;
    }

    public void setSettleTransDataDelDtoList(List<SettleDelDealDetailDto> settleTransDataDelDtoList) {
        this.settleTransDataDelDtoList = settleTransDataDelDtoList;
    }

    public List<SettleTransDataPledge> getSettleTransDataPledgeList() {
        return settleTransDataPledgeList;
    }

    public void setSettleTransDataPledgeList(List<SettleTransDataPledge> settleTransDataPledgeList) {
        this.settleTransDataPledgeList = settleTransDataPledgeList;
    }
}
