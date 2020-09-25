package com.joyintech.tams.invest.common.dto.period.trdbondlendtargetduration;

import com.joyintech.tams.common.base.entity.*;

import java.util.List;

/**
 * @program: 2.1.1
 * @description:
 * @author: 强成西
 * @create: 2019-11-08 11:28
 **/
public class InvestTradeInfoDto {
    /**
     * 汇总交易
     */
    private TrdFinProductDealTotal trdFinProductDealTotal;

    /**
     * 汇总扩展
     */
    private TrdDealTotalAdd trdDealTotalAdd;

    /**
     * 汇总扩展
     */
    private PaTrdFinProductDealTotal paTrdFinProductDealTotal;

    /**
     * 交易费用明细
     */
    private List<TrdTradeFeeDetail> trdTradeFeeDetailList;

    /**
     * 划款指令
     */
    private List<StlPayOrders> stlPayOrdersList;

    /**
     * 金融产品交易
     */
    private List<TrdProductDeal> trdProductDealList;

    /**
     * 金融产品交易附属
     */
    private List<TrdProductDealAdd> trdProductDealAddList;

    /**
     * 金融产品交易扩展
     */
    private List<PaTrdProductDeal> paTrdProductDealList;

    /**
     * 组合交易
     */
    private List<PtlTrade> ptlTradeList;

    /**
     * 获取 trdFinProductDealTotal
     *
     * @return trdFinProductDealTotal
     */
    public TrdFinProductDealTotal getTrdFinProductDealTotal() {
        return trdFinProductDealTotal;
    }

    /**
     * 设置 trdFinProductDealTotal
     *
     * @param trdFinProductDealTotal trdFinProductDealTotal
     */
    public void setTrdFinProductDealTotal(TrdFinProductDealTotal trdFinProductDealTotal) {
        this.trdFinProductDealTotal = trdFinProductDealTotal;
    }

    /**
     * 获取 trdDealTotalAdd
     *
     * @return trdDealTotalAdd
     */
    public TrdDealTotalAdd getTrdDealTotalAdd() {
        return trdDealTotalAdd;
    }

    /**
     * 设置 trdDealTotalAdd
     *
     * @param trdDealTotalAdd trdDealTotalAdd
     */
    public void setTrdDealTotalAdd(TrdDealTotalAdd trdDealTotalAdd) {
        this.trdDealTotalAdd = trdDealTotalAdd;
    }

    /**
     * 获取 paTrdFinProductDealTotal
     *
     * @return paTrdFinProductDealTotal
     */
    public PaTrdFinProductDealTotal getPaTrdFinProductDealTotal() {
        return paTrdFinProductDealTotal;
    }

    /**
     * 设置 paTrdFinProductDealTotal
     *
     * @param paTrdFinProductDealTotal paTrdFinProductDealTotal
     */
    public void setPaTrdFinProductDealTotal(PaTrdFinProductDealTotal paTrdFinProductDealTotal) {
        this.paTrdFinProductDealTotal = paTrdFinProductDealTotal;
    }

    /**
     * 获取 trdTradeFeeDetailList
     *
     * @return trdTradeFeeDetailList
     */
    public List<TrdTradeFeeDetail> getTrdTradeFeeDetailList() {
        return trdTradeFeeDetailList;
    }

    /**
     * 设置 trdTradeFeeDetailList
     *
     * @param trdTradeFeeDetailList trdTradeFeeDetailList
     */
    public void setTrdTradeFeeDetailList(List<TrdTradeFeeDetail> trdTradeFeeDetailList) {
        this.trdTradeFeeDetailList = trdTradeFeeDetailList;
    }

    /**
     * 获取 stlPayOrdersList
     *
     * @return stlPayOrdersList
     */
    public List<StlPayOrders> getStlPayOrdersList() {
        return stlPayOrdersList;
    }

    /**
     * 设置 stlPayOrdersList
     *
     * @param stlPayOrdersList stlPayOrdersList
     */
    public void setStlPayOrdersList(List<StlPayOrders> stlPayOrdersList) {
        this.stlPayOrdersList = stlPayOrdersList;
    }

    /**
     * 获取 trdProductDealList
     *
     * @return trdProductDealList
     */
    public List<TrdProductDeal> getTrdProductDealList() {
        return trdProductDealList;
    }

    /**
     * 设置 trdProductDealList
     *
     * @param trdProductDealList trdProductDealList
     */
    public void setTrdProductDealList(List<TrdProductDeal> trdProductDealList) {
        this.trdProductDealList = trdProductDealList;
    }

    /**
     * 获取 trdProductDealAddList
     *
     * @return trdProductDealAddList
     */
    public List<TrdProductDealAdd> getTrdProductDealAddList() {
        return trdProductDealAddList;
    }

    /**
     * 设置 trdProductDealAddList
     *
     * @param trdProductDealAddList trdProductDealAddList
     */
    public void setTrdProductDealAddList(List<TrdProductDealAdd> trdProductDealAddList) {
        this.trdProductDealAddList = trdProductDealAddList;
    }

    /**
     * 获取 paTrdProductDealList
     *
     * @return paTrdProductDealList
     */
    public List<PaTrdProductDeal> getPaTrdProductDealList() {
        return paTrdProductDealList;
    }

    /**
     * 设置 paTrdProductDealList
     *
     * @param paTrdProductDealList paTrdProductDealList
     */
    public void setPaTrdProductDealList(List<PaTrdProductDeal> paTrdProductDealList) {
        this.paTrdProductDealList = paTrdProductDealList;
    }

    /**
     * 获取 ptlTradeList
     *
     * @return ptlTradeList
     */
    public List<PtlTrade> getPtlTradeList() {
        return ptlTradeList;
    }

    /**
     * 设置 ptlTradeList
     *
     * @param ptlTradeList ptlTradeList
     */
    public void setPtlTradeList(List<PtlTrade> ptlTradeList) {
        this.ptlTradeList = ptlTradeList;
    }
}
