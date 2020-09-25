/*
 * 文件名：FinProductExpireBaseTrdDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2019年1月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.product.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;

/**
 * 产品到期交易共通
 * 
 * @author zhangliang
 * @version 1.0 2019年1月13日
 * @see FinProductExpireBaseTrdDto
 * @since 1.0
 */
public class FinProductExpireBaseTrdDto {
    
    /**
     * {@linkplain}
     */
    private List<TrdProductDeal> trdProductDealList;
    
    /**
     * {@linkplain}
     */
    private List<TrdProductDealAdd> trdProductDealAddList;
    
    /**
     * {@linkplain}
     */
    private List<PaTrdProductDeal> paTrdProductDealList;
    
    /**
     * {@linkplain}
     */
    private List<PtlTrade> ptlTradeList;
    
    /**
     * {@linkplain}
     */
    private List<TrdTradeFeeDetail> trdTradeFeeDetailList;

    
    /**
     * 获取 trdProductDealList
     * @return trdProductDealList.
     */
    public List<TrdProductDeal> getTrdProductDealList() {
        return trdProductDealList;
    }

    
    /**
     * 设置 trdProductDealList
     * @param trdProductDealList trdProductDealList
     */
    public void setTrdProductDealList(List<TrdProductDeal> trdProductDealList) {
        this.trdProductDealList = trdProductDealList;
    }

    
    /**
     * 获取 trdProductDealAddList
     * @return trdProductDealAddList.
     */
    public List<TrdProductDealAdd> getTrdProductDealAddList() {
        return trdProductDealAddList;
    }

    
    /**
     * 设置 trdProductDealAddList
     * @param trdProductDealAddList trdProductDealAddList
     */
    public void setTrdProductDealAddList(List<TrdProductDealAdd> trdProductDealAddList) {
        this.trdProductDealAddList = trdProductDealAddList;
    }

    
    /**
     * 获取 paTrdProductDealList
     * @return paTrdProductDealList.
     */
    public List<PaTrdProductDeal> getPaTrdProductDealList() {
        return paTrdProductDealList;
    }

    
    /**
     * 设置 paTrdProductDealList
     * @param paTrdProductDealList paTrdProductDealList
     */
    public void setPaTrdProductDealList(List<PaTrdProductDeal> paTrdProductDealList) {
        this.paTrdProductDealList = paTrdProductDealList;
    }


    
    /**
     * 获取 ptlTradeList
     * @return ptlTradeList.
     */
    public List<PtlTrade> getPtlTradeList() {
        return ptlTradeList;
    }


    
    /**
     * 设置 ptlTradeList
     * @param ptlTradeList ptlTradeList
     */
    public void setPtlTradeList(List<PtlTrade> ptlTradeList) {
        this.ptlTradeList = ptlTradeList;
    }


    
    /**
     * 获取 trdTradeFeeDetailList
     * @return trdTradeFeeDetailList.
     */
    public List<TrdTradeFeeDetail> getTrdTradeFeeDetailList() {
        return trdTradeFeeDetailList;
    }


    
    /**
     * 设置 trdTradeFeeDetailList
     * @param trdTradeFeeDetailList trdTradeFeeDetailList
     */
    public void setTrdTradeFeeDetailList(List<TrdTradeFeeDetail> trdTradeFeeDetailList) {
        this.trdTradeFeeDetailList = trdTradeFeeDetailList;
    }

    

}
