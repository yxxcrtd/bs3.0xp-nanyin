package com.joyintech.tams.settle.batch.transdatagather.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.common.base.entity.SettleTransDataTotal;
import com.joyintech.tams.common.base.entity.SettleTransDataTotalAdd;

import java.util.Date;
import java.util.List;

/**
 * 汇总成交数据结果
 * @author meifaxin
 * @date 2021/10/29
 * @see StlSettleTransDataGatherParamDto
 * @since 1.0
 */
public class StlSettleTransDataGatherParamDto extends NeedOperateEntity {


    /**
     * 汇总成交数据结果
     */
    private List<SettleTransDataTotal> settleTransDataTotalList;

    /**
     * 汇总成交数据附属结果
     */
    private List<SettleTransDataTotalAdd> settleTransDataTotalAddList;


    /**
     * 交易日期
     */
    private Date tradeDate;

    public StlSettleTransDataGatherParamDto() {
    }

    public StlSettleTransDataGatherParamDto(List<SettleTransDataTotal> settleTransDataTotalList, List<SettleTransDataTotalAdd> settleTransDataTotalAddList) {
        this.settleTransDataTotalList = settleTransDataTotalList;
        this.settleTransDataTotalAddList = settleTransDataTotalAddList;
    }

    public List<SettleTransDataTotal> getSettleTransDataTotalList() {
        return settleTransDataTotalList;
    }

    public void setSettleTransDataTotalList(List<SettleTransDataTotal> settleTransDataTotalList) {
        this.settleTransDataTotalList = settleTransDataTotalList;
    }

    public List<SettleTransDataTotalAdd> getSettleTransDataTotalAddList() {
        return settleTransDataTotalAddList;
    }

    public void setSettleTransDataTotalAddList(List<SettleTransDataTotalAdd> settleTransDataTotalAddList) {
        this.settleTransDataTotalAddList = settleTransDataTotalAddList;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }
}
