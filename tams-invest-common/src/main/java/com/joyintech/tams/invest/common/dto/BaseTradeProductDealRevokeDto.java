/*
 * 文件名：BaseTradeProductDealRevokeDto.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights
 * Reserved. 描述：理财资管管理系统V2.0 修改人：huwenbin 修改时间：2018年9月4日 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.TrdFinProductBarter;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;

/**
 * 金融产品交易信息撤销实体 〈功能详细描述〉
 *
 * @author huwenbin
 * @version 1.0 2018年9月4日
 * @see BaseTradeProductDealRevokeDto
 * @since 1.0
 */
public class BaseTradeProductDealRevokeDto {

    /**
     * 原交易编号，撤销时存原来的交易编号
     */
    private String pTradeId;

    /**
     * 子组合代码
     */
    private String actPortfolioId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 撤销说明
     */
    private String cancelRemark;

    /**
     * 金融产品交易
     */
    private TrdProductDeal trdProductDeal;

    /**
     * 金融产品交易附属表
     */
    private TrdProductDealAdd trdProductDealAdd;

    /**
     * 划款指令表
     */
    private List<StlPayOrdersDto> stlPayOrdersList;

//    /**
//     * 交易资金指令表
//     */
//    private List<StlOrders> stlOrdersList;

    /**
     * 金融产品实物交易表
     */
    private List<TrdFinProductBarter> trdFinProductBarterList;

    /**
     * 交易费用明细表
     */
    private List<TrdTradeFeeDetail> trdTradeFeeDetailList;

    /**
     * 框架共通组件.附件组件
     */
    private MstFileInputParameter mstFileInputParameter;

    private MstFileOutputParameter mstFileOutputParameter;

    /**
     * 获取 pTradeId
     *
     * @return pTradeId.
     */
    public String getpTradeId() {
        return pTradeId;
    }

    /**
     * 设置 pTradeId
     *
     * @param pTradeId pTradeId
     */
    public void setpTradeId(String pTradeId) {
        this.pTradeId = pTradeId;
    }

    /**
     * 获取 remark
     *
     * @return remark.
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 remark
     *
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取 cancelRemark
     *
     * @return cancelRemark.
     */
    public String getCancelRemark() {
        return cancelRemark;
    }

    /**
     * 设置 cancelRemark
     *
     * @param cancelRemark cancelRemark
     */
    public void setCancelRemark(String cancelRemark) {
        this.cancelRemark = cancelRemark;
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
     * 获取 stlPayOrdersList
     *
     * @return stlPayOrdersList.
     */
    public List<StlPayOrdersDto> getStlPayOrdersList() {
        return stlPayOrdersList;
    }

    /**
     * 设置 stlPayOrdersList
     *
     * @param stlPayOrdersList stlPayOrdersList
     */
    public void setStlPayOrdersList(List<StlPayOrdersDto> stlPayOrdersList) {
        this.stlPayOrdersList = stlPayOrdersList;
    }

//    /**
//     * 获取 stlOrdersList
//     *
//     * @return stlOrdersList.
//     */
//    public List<StlOrders> getStlOrdersList() {
//        return stlOrdersList;
//    }
//
//    /**
//     * 设置 stlOrdersList
//     *
//     * @param stlOrdersList stlOrdersList
//     */
//    public void setStlOrdersList(List<StlOrders> stlOrdersList) {
//        this.stlOrdersList = stlOrdersList;
//    }

    /**
     * 获取 trdFinProductBarterList
     *
     * @return trdFinProductBarterList.
     */
    public List<TrdFinProductBarter> getTrdFinProductBarterList() {
        return trdFinProductBarterList;
    }

    /**
     * 设置 trdFinProductBarterList
     *
     * @param trdFinProductBarterList trdFinProductBarterList
     */
    public void setTrdFinProductBarterList(List<TrdFinProductBarter> trdFinProductBarterList) {
        this.trdFinProductBarterList = trdFinProductBarterList;
    }

    /**
     * 获取 trdTradeFeeDetailList
     *
     * @return trdTradeFeeDetailList.
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
     * 获取 mstFileInputParameter
     *
     * @return mstFileInputParameter.
     */
    public MstFileInputParameter getMstFileInputParameter() {
        return mstFileInputParameter;
    }

    /**
     * 设置 mstFileInputParameter
     *
     * @param mstFileInputParameter mstFileInputParameter
     */
    public void setMstFileInputParameter(MstFileInputParameter mstFileInputParameter) {
        this.mstFileInputParameter = mstFileInputParameter;
    }

    /**
     * 获取 mstFileOutputParameter
     *
     * @return mstFileOutputParameter.
     */
    public MstFileOutputParameter getMstFileOutputParameter() {
        return mstFileOutputParameter;
    }

    /**
     * 设置 mstFileOutputParameter
     *
     * @param mstFileOutputParameter mstFileOutputParameter
     */
    public void setMstFileOutputParameter(MstFileOutputParameter mstFileOutputParameter) {
        this.mstFileOutputParameter = mstFileOutputParameter;
    }

    /**
     * 获取 actPortfolioId
     *
     * @return actPortfolioId
     */
    public String getActPortfolioId() {
        return actPortfolioId;
    }

    /**
     * 设置 actPortfolioId
     *
     * @param actPortfolioId actPortfolioId
     */
    public void setActPortfolioId(String actPortfolioId) {
        this.actPortfolioId = actPortfolioId;
    }
}
