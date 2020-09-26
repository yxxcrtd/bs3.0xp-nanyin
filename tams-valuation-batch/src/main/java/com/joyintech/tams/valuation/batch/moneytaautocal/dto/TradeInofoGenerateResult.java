/*
 * 文件名：TradeInofoGenerateResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：May 25, 2019
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.moneytaautocal.dto;

import java.util.List;

import com.google.common.collect.Lists;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.BokDealAccounting;
import com.joyintech.tams.common.base.entity.BokDealAccountingDetail;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;

/**
 * 货币型产品自动结转处理
 * 
 * @author zhangwei
 * @version 1.0 May 25, 2019
 * @see TradeInofoGenerateResult
 * @since 1.0
 */
public class TradeInofoGenerateResult extends NeedToWriteEntity {

    /**
     * 金融产品交易
     */
    private List<TrdProductDeal> listTrdProductDeal = Lists.newArrayList();

    /**
     * 估值核算交易扩展表
     */
    private List<TrdProductDealAdd> listTrdProductDealExt = Lists.newArrayList();

    /**
     * 入账信息表
     */
    private List<BokDealAccounting> listBokDealAccounting = Lists.newArrayList();

    /**
     * 估值核算交易扩展表
     */
    private List<BokDealAccountingDetail> listBokDealAccountingDetail = Lists.newArrayList();

    public List<TrdProductDeal> getListTrdProductDeal() {
        return listTrdProductDeal;
    }

    public void setListTrdProductDeal(List<TrdProductDeal> listTrdProductDeal) {
        this.listTrdProductDeal = listTrdProductDeal;
    }

    public List<TrdProductDealAdd> getListTrdProductDealExt() {
        return listTrdProductDealExt;
    }

    public void setListTrdProductDealExt(List<TrdProductDealAdd> listTrdProductDealExt) {
        this.listTrdProductDealExt = listTrdProductDealExt;
    }

    public List<BokDealAccounting> getListBokDealAccounting() {
        return listBokDealAccounting;
    }

    public void setListBokDealAccounting(List<BokDealAccounting> listBokDealAccounting) {
        this.listBokDealAccounting = listBokDealAccounting;
    }

    public List<BokDealAccountingDetail> getListBokDealAccountingDetail() {
        return listBokDealAccountingDetail;
    }

    public void setListBokDealAccountingDetail(List<BokDealAccountingDetail> listBokDealAccountingDetail) {
        this.listBokDealAccountingDetail = listBokDealAccountingDetail;
    }

}
