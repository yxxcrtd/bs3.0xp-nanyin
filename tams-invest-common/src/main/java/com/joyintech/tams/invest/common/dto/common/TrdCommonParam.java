/**
 *
 */
package com.joyintech.tams.invest.common.dto.common;

import com.joyintech.tams.common.base.entity.*;
import com.joyintech.workflow.engine.constant.OperationType;

import java.util.List;

/**
 * @author ex_kjkfzx_heyy
 *
 */
public class TrdCommonParam {
    /**
     * 交易类金融产品
     */
    private FinTradeProduct finTradeProduct;
    /**
     * 金融产品类型
     */
    private FinProductType finProductType;
    /**
     * 金融产品交易汇总业务表
     */
    private TrdFinProductDealTotal trdFinProductDealTotal;
    /**
     *
     */
    private FinTradeProductAdd finTradeProductAdd;
    /**
     * 划款指令业务表
     */
    private List<StlPayOrders> stlPayOrdersDtos;

    /**
     * 金融产品交易汇总扩展业务表
     */
    private PaTrdFinProductDealTotal paTrdFinProductDealTotal;

    private List<TrdProductDealAdd> dealAdds;

    private List<PaTrdProductDeal> paDeals;

    private List<TrdProductDeal> trdProductDeals;

    private List<PtlTrade> ptlTrades;

    private List<TrdTradeFeeDetail> trdTradeFeeDetails;

    private OperationType type;

    private List<StlFinProductAcct>stlFinProductAccts;

    public FinTradeProductAdd getFinTradeProductAdd() {
        return finTradeProductAdd;
    }

    public void setFinTradeProductAdd(FinTradeProductAdd finTradeProductAdd) {
        this.finTradeProductAdd = finTradeProductAdd;
    }

    public List<StlFinProductAcct> getStlFinProductAccts() {
        return stlFinProductAccts;
    }

    public void setStlFinProductAccts(List<StlFinProductAcct> stlFinProductAccts) {
        this.stlFinProductAccts = stlFinProductAccts;
    }

    public FinProductType getFinProductType() {
        return finProductType;
    }

    public void setFinProductType(FinProductType finProductType) {
        this.finProductType = finProductType;
    }

    public FinTradeProduct getFinTradeProduct() {
        return finTradeProduct;
    }

    public void setFinTradeProduct(FinTradeProduct finTradeProduct) {
        this.finTradeProduct = finTradeProduct;
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
     * 获取 stlPayOrdersDtos
     *
     * @return stlPayOrdersDtos
     */
    public List<StlPayOrders> getStlPayOrdersDtos() {
        return stlPayOrdersDtos;
    }

    /**
     * 设置 stlPayOrdersDtos
     *
     * @param stlPayOrdersDtos stlPayOrdersDtos
     */
    public void setStlPayOrdersDtos(List<StlPayOrders> stlPayOrdersDtos) {
        this.stlPayOrdersDtos = stlPayOrdersDtos;
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
     * 获取 dealAdds
     *
     * @return dealAdds
     */
    public List<TrdProductDealAdd> getDealAdds() {
        return dealAdds;
    }

    /**
     * 设置 dealAdds
     *
     * @param dealAdds dealAdds
     */
    public void setDealAdds(List<TrdProductDealAdd> dealAdds) {
        this.dealAdds = dealAdds;
    }

    /**
     * 获取 paDeals
     *
     * @return paDeals
     */
    public List<PaTrdProductDeal> getPaDeals() {
        return paDeals;
    }

    /**
     * 设置 paDeals
     *
     * @param paDeals paDeals
     */
    public void setPaDeals(List<PaTrdProductDeal> paDeals) {
        this.paDeals = paDeals;
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
     * 获取 type
     *
     * @return type
     */
    public OperationType getType() {
        return type;
    }

    /**
     * 设置 type
     *
     * @param type type
     */
    public void setType(OperationType type) {
        this.type = type;
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
     * 获取 trdTradeFeeDetails
     *
     * @return trdTradeFeeDetails
     */
    public List<TrdTradeFeeDetail> getTrdTradeFeeDetails() {
        return trdTradeFeeDetails;
    }

    /**
     * 设置 trdTradeFeeDetails
     *
     * @param trdTradeFeeDetails trdTradeFeeDetails
     */
    public void setTrdTradeFeeDetails(List<TrdTradeFeeDetail> trdTradeFeeDetails) {
        this.trdTradeFeeDetails = trdTradeFeeDetails;
    }
}
