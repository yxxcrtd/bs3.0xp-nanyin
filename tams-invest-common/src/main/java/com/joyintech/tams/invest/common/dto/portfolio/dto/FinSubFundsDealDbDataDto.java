/*
 * 文件名：FinProductDurDealDbDataDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.dto.portfolio.dto;

import com.joyintech.tams.common.base.entity.PaBranchSaleDetail;
import com.joyintech.tams.common.base.entity.PaTrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;

/**
 * 同步db数据dto
 *
 * @author etliu
 * @version 1.0 2018年12月28日
 * @see FinSubFundsDealDbDataDto
 * @since 1.0
 */
public class FinSubFundsDealDbDataDto {

    /**
     * 金融产品交易汇总表Entity
     */
    private TrdFinProductDealTotal trdFinProductDealTotal;

    /**
     * 金融产品交易汇总扩展表Entity
     */
    private PaTrdFinProductDealTotal paTrdFinProductDealTotal;

    /**
     * 金融产品交易表Entity
     */
    private TrdProductDeal trdProductDeal;

    /**
     * 金融产品交易附属表Entity
     */
    private TrdProductDealAdd trdProductDealAdd;

    /**
     * 金融产品交易扩展表Entity
     */
    private PaTrdProductDeal paTrdProductDeal;

    /**
     * 组合交易Entity
     */
    private PtlTrade ptlTrade;

    /**
     * 理财分行销售明细Entity
     */
    private PaBranchSaleDetail paBranchSaleDetail;

    /**
     * 交易费用明细表Entity
     */
    private TrdTradeFeeDetail trdTradeFeeDetail;

    /**
     * 划款指令Entity
     */
    private BussTrdTransferOrderDto bussTrdTransferOrderDto;

    /**
     * 获取 trdFinProductDealTotal
     *
     * @return trdFinProductDealTotal.
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
     * 获取 paTrdFinProductDealTotal
     *
     * @return paTrdFinProductDealTotal.
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
     * 获取 trdProductDeal
     *
     * @return trdProductDeal.
     */
    public TrdProductDeal getTrdProductDeal() {
        return trdProductDeal;
    }

    /**
     * 设置 trdProductDeal
     *
     * @param trdProductDeal trdProductDeal
     */
    public void setTrdProductDeal(TrdProductDeal trdProductDeal) {
        this.trdProductDeal = trdProductDeal;
    }

    /**
     * 获取 trdProductDealAdd
     *
     * @return trdProductDealAdd.
     */
    public TrdProductDealAdd getTrdProductDealAdd() {
        return trdProductDealAdd;
    }

    /**
     * 设置 trdProductDealAdd
     *
     * @param trdProductDealAdd trdProductDealAdd
     */
    public void setTrdProductDealAdd(TrdProductDealAdd trdProductDealAdd) {
        this.trdProductDealAdd = trdProductDealAdd;
    }

    /**
     * 获取 paTrdProductDeal
     *
     * @return paTrdProductDeal.
     */
    public PaTrdProductDeal getPaTrdProductDeal() {
        return paTrdProductDeal;
    }

    /**
     * 设置 paTrdProductDeal
     *
     * @param paTrdProductDeal paTrdProductDeal
     */
    public void setPaTrdProductDeal(PaTrdProductDeal paTrdProductDeal) {
        this.paTrdProductDeal = paTrdProductDeal;
    }

    /**
     * 获取 ptlTrade
     *
     * @return ptlTrade.
     */
    public PtlTrade getPtlTrade() {
        return ptlTrade;
    }

    /**
     * 设置 ptlTrade
     *
     * @param ptlTrade ptlTrade
     */
    public void setPtlTrade(PtlTrade ptlTrade) {
        this.ptlTrade = ptlTrade;
    }

    /**
     * 获取 paBranchSaleDetail
     *
     * @return paBranchSaleDetail.
     */
    public PaBranchSaleDetail getPaBranchSaleDetail() {
        return paBranchSaleDetail;
    }

    /**
     * 设置 paBranchSaleDetail
     *
     * @param paBranchSaleDetail paBranchSaleDetail
     */
    public void setPaBranchSaleDetail(PaBranchSaleDetail paBranchSaleDetail) {
        this.paBranchSaleDetail = paBranchSaleDetail;
    }

    /**
     * 获取 trdTradeFeeDetail
     *
     * @return trdTradeFeeDetail.
     */
    public TrdTradeFeeDetail getTrdTradeFeeDetail() {
        return trdTradeFeeDetail;
    }

    /**
     * 设置 trdTradeFeeDetail
     *
     * @param trdTradeFeeDetail trdTradeFeeDetail
     */
    public void setTrdTradeFeeDetail(TrdTradeFeeDetail trdTradeFeeDetail) {
        this.trdTradeFeeDetail = trdTradeFeeDetail;
    }

    /**
     * 获取 bussTrdTransferOrderDto
     *
     * @return bussTrdTransferOrderDto.
     */
    public BussTrdTransferOrderDto getBussTrdTransferOrderDto() {
        return bussTrdTransferOrderDto;
    }

    /**
     * 设置 bussTrdTransferOrderDto
     *
     * @param bussTrdTransferOrderDto bussTrdTransferOrderDto
     */
    public void setBussTrdTransferOrderDto(BussTrdTransferOrderDto bussTrdTransferOrderDto) {
        this.bussTrdTransferOrderDto = bussTrdTransferOrderDto;
    }

}
