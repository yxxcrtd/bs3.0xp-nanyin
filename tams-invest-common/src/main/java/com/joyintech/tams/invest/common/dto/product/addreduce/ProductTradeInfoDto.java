package com.joyintech.tams.invest.common.dto.product.addreduce;

import java.util.List;

import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;

/**
 * @program: tams-invest-parent
 * @description: 产品存续交易dto
 * @author: 强成西
 * @create: 2019-01-08 11:30
 **/
public class ProductTradeInfoDto {

    private TrdFinProductDealTotal trdFinProductDealTotal;

    private PaTrdFinProductDealTotal paTrdFinProductDealTotal;

    private List<StlPayOrdersDto> stlPayOrders;

    private List<PtlCashPositionFlowDto> ptlCashPositionFlows;

    private List<PtlSecPositionFlowDto> ptlSecPositionFlows;

    private List<TrdProductDeal> trdProductDeals;

    private List<PaTrdProductDeal> paTrdProductDeals;

    private List<TrdProductDealAdd> trdProductDealAdds;

    private List<PtlTrade> ptlTrades;

    /**
     * 获取 ptlSecPositionFlows
     *
     * @return ptlSecPositionFlows
     */
    public List<PtlSecPositionFlowDto> getPtlSecPositionFlows() {
        return ptlSecPositionFlows;
    }

    /**
     * 设置 ptlSecPositionFlows
     *
     * @param ptlSecPositionFlows ptlSecPositionFlows
     */
    public void setPtlSecPositionFlows(List<PtlSecPositionFlowDto> ptlSecPositionFlows) {
        this.ptlSecPositionFlows = ptlSecPositionFlows;
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
     * 获取 ptlTrades
     *
     * @return ptlTrades
     */
    public List<PtlTrade> getPtlTrades() {
        return ptlTrades;
    }

    /**
     * 设置 ptlTrades
     *
     * @param ptlTrades ptlTrades
     */
    public void setPtlTrades(List<PtlTrade> ptlTrades) {
        this.ptlTrades = ptlTrades;
    }

    /**
     * 获取 trdProductDealAdds
     *
     * @return trdProductDealAdds
     */
    public List<TrdProductDealAdd> getTrdProductDealAdds() {
        return trdProductDealAdds;
    }

    /**
     * 设置 trdProductDealAdds
     *
     * @param trdProductDealAdds trdProductDealAdds
     */
    public void setTrdProductDealAdds(List<TrdProductDealAdd> trdProductDealAdds) {
        this.trdProductDealAdds = trdProductDealAdds;
    }

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
     * 获取 stlPayOrders
     *
     * @return stlPayOrders
     */
    public List<StlPayOrdersDto> getStlPayOrders() {
        return stlPayOrders;
    }

    /**
     * 设置 stlPayOrders
     *
     * @param stlPayOrders stlPayOrders
     */
    public void setStlPayOrders(List<StlPayOrdersDto> stlPayOrders) {
        this.stlPayOrders = stlPayOrders;
    }

    /**
     * 获取 ptlCashPositionFlows
     *
     * @return ptlCashPositionFlows
     */
    public List<PtlCashPositionFlowDto> getPtlCashPositionFlows() {
        return ptlCashPositionFlows;
    }

    /**
     * 设置 ptlCashPositionFlows
     *
     * @param ptlCashPositionFlows ptlCashPositionFlows
     */
    public void setPtlCashPositionFlows(List<PtlCashPositionFlowDto> ptlCashPositionFlows) {
        this.ptlCashPositionFlows = ptlCashPositionFlows;
    }

    /**
     * 获取 trdProductDeals
     *
     * @return trdProductDeals
     */
    public List<TrdProductDeal> getTrdProductDeals() {
        return trdProductDeals;
    }

    /**
     * 设置 trdProductDeals
     *
     * @param trdProductDeals trdProductDeals
     */
    public void setTrdProductDeals(List<TrdProductDeal> trdProductDeals) {
        this.trdProductDeals = trdProductDeals;
    }

    /**
     * 获取 paTrdProductDeals
     *
     * @return paTrdProductDeals
     */
    public List<PaTrdProductDeal> getPaTrdProductDeals() {
        return paTrdProductDeals;
    }

    /**
     * 设置 paTrdProductDeals
     *
     * @param paTrdProductDeals paTrdProductDeals
     */
    public void setPaTrdProductDeals(List<PaTrdProductDeal> paTrdProductDeals) {
        this.paTrdProductDeals = paTrdProductDeals;
    }
}
