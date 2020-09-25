/*
 * 文件名：FinProductPayInt002TradeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2019年1月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.payint.dto;

import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.framework.base.BaseEntity;

/**
 * 金融产品交易相关表dto
 *
 * @author etliu
 * @version 1.0 2019年1月9日
 * @see FinProductPayInt002TradeDto
 * @since 1.0
 */
public class FinProductPayInt002TradeDto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 2441256717844438821L;

    /**
     * 金融产品交易Entity
     */
    private TrdProductDeal trdProductDeal;

    /**
     * 金融产品交易扩展表Entity
     */
    private PaTrdProductDeal paTrdProductDeal;

    /**
     * 金融产品交易附属表Entity
     */
    private TrdProductDealAdd trdProductDealAdd;

    /**
     * 组合交易表Entity
     */
    private PtlTrade ptlTrade;

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
}
