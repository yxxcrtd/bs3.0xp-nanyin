package com.joyintech.tams.invest.common.dto.trade.bondlend;

import com.joyintech.tams.common.base.entity.*;

import java.util.List;

/**
 * @program: 2.1.0
 * @description:
 * @author: 强成西
 * @create: 2019-09-17 16:49
 **/
public class TrdBondLendInsertDto {

    private FinProductType finProductType;
    private FinTradeProduct finTradeProduct;
    private FinTradeProductAdd finTradeProductAdd;
    private List<PaTrdProductDeal> paTrdProductDeal;
    private List<PtlTrade> ptlTrade;
    private StlFinProductAcct stlFinProductAcct;
    private FinDealTarget finDealTarget;
    private RepAssetDeri repAssetDeri;

    /**
     * 获取 finProductType
     *
     * @return finProductType
     */
    public FinProductType getFinProductType() {
        return finProductType;
    }

    /**
     * 设置 finProductType
     *
     * @param finProductType finProductType
     */
    public void setFinProductType(FinProductType finProductType) {
        this.finProductType = finProductType;
    }

    /**
     * 获取 finTradeProduct
     *
     * @return finTradeProduct
     */
    public FinTradeProduct getFinTradeProduct() {
        return finTradeProduct;
    }

    /**
     * 设置 finTradeProduct
     *
     * @param finTradeProduct finTradeProduct
     */
    public void setFinTradeProduct(FinTradeProduct finTradeProduct) {
        this.finTradeProduct = finTradeProduct;
    }

    /**
     * 获取 paTrdProductDeal
     *
     * @return paTrdProductDeal
     */
    public List<PaTrdProductDeal> getPaTrdProductDeal() {
        return paTrdProductDeal;
    }

    /**
     * 设置 paTrdProductDeal
     *
     * @param paTrdProductDeal paTrdProductDeal
     */
    public void setPaTrdProductDeal(List<PaTrdProductDeal> paTrdProductDeal) {
        this.paTrdProductDeal = paTrdProductDeal;
    }

    /**
     * 获取 ptlTrade
     *
     * @return ptlTrade
     */
    public List<PtlTrade> getPtlTrade() {
        return ptlTrade;
    }

    /**
     * 设置 ptlTrade
     *
     * @param ptlTrade ptlTrade
     */
    public void setPtlTrade(List<PtlTrade> ptlTrade) {
        this.ptlTrade = ptlTrade;
    }

    /**
     * 获取 stlFinProductAcct
     *
     * @return stlFinProductAcct
     */
    public StlFinProductAcct getStlFinProductAcct() {
        return stlFinProductAcct;
    }

    /**
     * 设置 stlFinProductAcct
     *
     * @param stlFinProductAcct stlFinProductAcct
     */
    public void setStlFinProductAcct(StlFinProductAcct stlFinProductAcct) {
        this.stlFinProductAcct = stlFinProductAcct;
    }

    /**
     * 获取 finDealTarget
     *
     * @return finDealTarget
     */
    public FinDealTarget getFinDealTarget() {
        return finDealTarget;
    }

    /**
     * 设置 finDealTarget
     *
     * @param finDealTarget finDealTarget
     */
    public void setFinDealTarget(FinDealTarget finDealTarget) {
        this.finDealTarget = finDealTarget;
    }

    /**
     * 获取 repAssetDeri
     *
     * @return repAssetDeri
     */
    public RepAssetDeri getRepAssetDeri() {
        return repAssetDeri;
    }

    /**
     * 设置 repAssetDeri
     *
     * @param repAssetDeri repAssetDeri
     */
    public void setRepAssetDeri(RepAssetDeri repAssetDeri) {
        this.repAssetDeri = repAssetDeri;
    }

    public FinTradeProductAdd getFinTradeProductAdd() {
        return finTradeProductAdd;
    }

    public void setFinTradeProductAdd(FinTradeProductAdd finTradeProductAdd) {
        this.finTradeProductAdd = finTradeProductAdd;
    }
}
