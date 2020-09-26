/*
 * 文件名：AssetIntdateCarryoverInfo.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chengzhong
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.assetintdatecarryover.dto;


import com.joyintech.tams.common.base.entity.BokDealAccounting;
import com.joyintech.tams.common.base.entity.BokDealAccountingDetail;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
//import com.joyintech.tams.common.base.entity.TrdProductDealExt;


/**
 * 资产理论结息日结转信息
 * 
 * @author chengzhong
 * @version 1.0 2018年6月7日
 * @see AssetIntdateCarryoverDeal
 * @since 1.0
 */
public class AssetIntdateCarryoverDeal {

    /**
     * 交易类金融产品
     */
    private FinTradeProduct valTradeFinProduct;


    /**
     * 金融产品交易
     */
    private TrdProductDeal valFinProductDeal;

    /**
     * 估值核算交易扩展表
     */
    //一体化问题12:表名变更(金融产品交易扩展表(TRD_PRODUCT_DEAL_EXT) -> 金融产品交易附属表(TRD_PRODUCT_DEAL_ADD))
    private TrdProductDealAdd valFinProductDealExt;

    /**
     * 入账信息表
     */
    private BokDealAccounting valDealAccounting;
    
    /**
     * 入账账务明细表
     */
    private BokDealAccountingDetail ValDealAccountingDetail;

    /**
     * 账务明细代码
     */
    private BokDetailId bokDetailId;

    /**
     * 获取 valTradeFinProduct
     * @return valTradeFinProduct.
     */
    public FinTradeProduct getValTradeFinProduct() {
        return valTradeFinProduct;
    }

    
    /**
     * 设置 valTradeFinProduct
     * @param valTradeFinProduct valTradeFinProduct
     */
    public void setValTradeFinProduct(FinTradeProduct valTradeFinProduct) {
        this.valTradeFinProduct = valTradeFinProduct;
    }

    
    /**
     * 获取 valFinProductDeal
     * @return valFinProductDeal.
     */
    public TrdProductDeal getValFinProductDeal() {
        return valFinProductDeal;
    }

    
    /**
     * 设置 valFinProductDeal
     * @param valFinProductDeal valFinProductDeal
     */
    public void setValFinProductDeal(TrdProductDeal valFinProductDeal) {
        this.valFinProductDeal = valFinProductDeal;
    }

    
    /**
     * 获取 valFinProductDealExt
     * @return valFinProductDealExt.
     */
    public TrdProductDealAdd getValFinProductDealExt() {
        return valFinProductDealExt;
    }

    
    /**
     * 设置 valFinProductDealExt
     * @param valFinProductDealExt valFinProductDealExt
     */
    public void setValFinProductDealExt(TrdProductDealAdd valFinProductDealExt) {
        this.valFinProductDealExt = valFinProductDealExt;
    }

    
    /**
     * 获取 valDealAccounting
     * @return valDealAccounting.
     */
    public BokDealAccounting getValDealAccounting() {
        return valDealAccounting;
    }

    
    /**
     * 设置 valDealAccounting
     * @param valDealAccounting valDealAccounting
     */
    public void setValDealAccounting(BokDealAccounting valDealAccounting) {
        this.valDealAccounting = valDealAccounting;
    }


    
    public BokDealAccountingDetail getValDealAccountingDetail() {
        return ValDealAccountingDetail;
    }


    
    public void setValDealAccountingDetail(BokDealAccountingDetail valDealAccountingDetail) {
        ValDealAccountingDetail = valDealAccountingDetail;
    }

    /**
     * 获取 账务明细代码
     * 
     * @return 账务明细代码
     */
    public BokDetailId getBokDetailId() {
        return bokDetailId;
    }

    /**
     * 设置 账务明细代码
     * 
     * @param bokDetailId 账务明细代码
     */
    public void setBokDetailId(BokDetailId bokDetailId) {
        this.bokDetailId = bokDetailId;
    }

}
