package com.joyintech.tams.invest.common.trade.dto;

import com.joyintech.tams.common.base.entity.EtrPledge;
import com.joyintech.tams.common.base.entity.FinDealPledge;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: 2.1.0
 * @description:
 * @author: 强成西
 * @create: 2019-09-17 16:20
 **/
public class PledgeInsertDto {
    private List<FinDealPledge> finDealPledgeList;
    private List<EtrPledge> etrPledgeList;

    /**
     * 获取 finDealPledgeList
     *
     * @return finDealPledgeList
     */
    public List<FinDealPledge> getFinDealPledgeList() {
        return finDealPledgeList;
    }

    /**
     * 设置 finDealPledgeList
     *
     * @param finDealPledgeList finDealPledgeList
     */
    public void setFinDealPledgeList(List<FinDealPledge> finDealPledgeList) {
        this.finDealPledgeList = finDealPledgeList;
    }

    /**
     * 获取 etrPledgeList
     *
     * @return etrPledgeList
     */
    public List<EtrPledge> getEtrPledgeList() {
        return etrPledgeList;
    }

    /**
     * 设置 etrPledgeList
     *
     * @param etrPledgeList etrPledgeList
     */
    public void setEtrPledgeList(List<EtrPledge> etrPledgeList) {
        this.etrPledgeList = etrPledgeList;
    }
}
