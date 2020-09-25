/*
 * 文件名：TrdCommDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiaominghui
 * 修改时间：2019年7月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.dto.common;

import java.util.List;

import com.joyintech.tams.common.base.entity.*;

/**
 * 交易共同dto 用于调用jixu接口
 * 
 * @author qiaominghui
 * @version 1.0 2019年7月25日
 * @see TrdCommDto
 * @since 1.0
 */
public class TrdCommDto {

    // 操作类型
    private String operateType;

    // 交易汇总表
    private TrdFinProductDealTotal trdFinProductDealTotal;

    // 交易类金融产品
    private FinTradeProduct finTradeProduct;

    // 金融产品交易
    private TrdProductDeal trdProductDeal;

    // 金融产品交易附属表
    private TrdProductDealAdd trdProductDealAdd;

    // 金融产品交易附属表
    private List<StlPayOrders> stlPayOrdersList;

    // 金融产品账户表
    private List<StlFinProductAcct> stlFinProductAcctList;
    /**
     * 金融产品实物交易表
     */
    private TrdFinProductBarter trdFinProductBarter;
    // 几许消息头
    private String header;
    
    //是否转仓交易 add by zhangquan
    private String isSwapTransaction;

	public String getIsSwapTransaction() {
		return isSwapTransaction;
	}

	public void setIsSwapTransaction(String isSwapTransaction) {
		this.isSwapTransaction = isSwapTransaction;
	}

	// 费用明细
    private List<TrdTradeFeeDetail> tradeFeeDetaillist;

    // 标的金融产品信息
    private FinProduct finProduct;

    // 标的金融产品附属
    private FinProductAdd finProductAdd;

    /**
     * List<现金流信息Entity>
     */
    private List<FinCashInfo> finCashInfoList;

    /**
     * List<现金流支付规则表Entity>
     */
    private List<FinCashPayRule> finCashPayRuleList;

    /**
     * List<现金流计算规则表Entity>
     */
    private List<FinCashCalcRule> finCashCalcRuleList;

    /**
     * List<现金流利率信息表Entity>
     */
    private List<FinRateInfo> finRateInfoList;

    /**
     * List<现金流计划表Entity>
     */
    private List<FinCashPlan> finCashPlanList;

    /**
     * List<利率重置计划表Entity>
     */
    private List<FinCashResetPlan> finCashResetPlanList;
    
    /**
     * 组合交易表List
     */
    private List<PtlTrade> ptlTradeList;

    /**
     * @return the operateType
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * @param operateType the operateType to set
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 finTradeProduct
     * 
     * @return finTradeProduct.
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
     * @return the stlPayOrdersList
     */
    public List<StlPayOrders> getStlPayOrdersList() {
        return stlPayOrdersList;
    }

    /**
     * @param stlPayOrdersList the stlPayOrdersList to set
     */
    public void setStlPayOrdersList(List<StlPayOrders> stlPayOrdersList) {
        this.stlPayOrdersList = stlPayOrdersList;
    }

    /**
     * 获取 stlFinProductAcctList
     * 
     * @return stlFinProductAcctList.
     */
    public List<StlFinProductAcct> getStlFinProductAcctList() {
        return stlFinProductAcctList;
    }

    /**
     * 设置 stlFinProductAcctList
     * 
     * @param stlFinProductAcctList stlFinProductAcctList
     */
    public void setStlFinProductAcctList(List<StlFinProductAcct> stlFinProductAcctList) {
        this.stlFinProductAcctList = stlFinProductAcctList;
    }

    public TrdFinProductDealTotal getTrdFinProductDealTotal() {
        return trdFinProductDealTotal;
    }

    public void setTrdFinProductDealTotal(TrdFinProductDealTotal trdFinProductDealTotal) {
        this.trdFinProductDealTotal = trdFinProductDealTotal;
    }

    /**
     * @return the header
     */
    public String getHeader() {
        return header;
    }

    /**
     * @param header the header to set
     */
    public void setHeader(String header) {
        this.header = header;
    }

    public List<TrdTradeFeeDetail> getTradeFeeDetaillist() {
        return tradeFeeDetaillist;
    }

    public void setTradeFeeDetaillist(List<TrdTradeFeeDetail> tradeFeeDetaillist) {
        this.tradeFeeDetaillist = tradeFeeDetaillist;
    }

    public FinProduct getFinProduct() {
        return finProduct;
    }

    public void setFinProduct(FinProduct finProduct) {
        this.finProduct = finProduct;
    }

    public FinProductAdd getFinProductAdd() {
        return finProductAdd;
    }

    public void setFinProductAdd(FinProductAdd finProductAdd) {
        this.finProductAdd = finProductAdd;
    }

    /**
     * 获取 finCashInfoList
     * 
     * @return finCashInfoList.
     */
    public List<FinCashInfo> getFinCashInfoList() {
        return finCashInfoList;
    }

    /**
     * 设置 finCashInfoList
     * 
     * @param finCashInfoList finCashInfoList
     */
    public void setFinCashInfoList(List<FinCashInfo> finCashInfoList) {
        this.finCashInfoList = finCashInfoList;
    }

    /**
     * 获取 finCashPlanList
     * 
     * @return finCashPlanList.
     */
    public List<FinCashPlan> getFinCashPlanList() {
        return finCashPlanList;
    }

    /**
     * 设置 finCashPlanList
     * 
     * @param finCashPlanList finCashPlanList
     */
    public void setFinCashPlanList(List<FinCashPlan> finCashPlanList) {
        this.finCashPlanList = finCashPlanList;
    }

    /**
     * 获取 finCashPayRuleList
     * 
     * @return finCashPayRuleList.
     */
    public List<FinCashPayRule> getFinCashPayRuleList() {
        return finCashPayRuleList;
    }

    /**
     * 设置 finCashPayRuleList
     * 
     * @param finCashPayRuleList finCashPayRuleList
     */
    public void setFinCashPayRuleList(List<FinCashPayRule> finCashPayRuleList) {
        this.finCashPayRuleList = finCashPayRuleList;
    }

    /**
     * 获取 finCashCalcRuleList
     * 
     * @return finCashCalcRuleList.
     */
    public List<FinCashCalcRule> getFinCashCalcRuleList() {
        return finCashCalcRuleList;
    }

    /**
     * 设置 finCashCalcRuleList
     * 
     * @param finCashCalcRuleList finCashCalcRuleList
     */
    public void setFinCashCalcRuleList(List<FinCashCalcRule> finCashCalcRuleList) {
        this.finCashCalcRuleList = finCashCalcRuleList;
    }

    /**
     * 获取 finRateInfoList
     * 
     * @return finRateInfoList.
     */
    public List<FinRateInfo> getFinRateInfoList() {
        return finRateInfoList;
    }

    /**
     * 设置 finRateInfoList
     * 
     * @param finRateInfoList finRateInfoList
     */
    public void setFinRateInfoList(List<FinRateInfo> finRateInfoList) {
        this.finRateInfoList = finRateInfoList;
    }

    /**
     * 获取 finCashResetPlanList
     * 
     * @return finCashResetPlanList.
     */
    public List<FinCashResetPlan> getFinCashResetPlanList() {
        return finCashResetPlanList;
    }

    /**
     * 设置 finCashResetPlanList
     * 
     * @param finCashResetPlanList finCashResetPlanList
     */
    public void setFinCashResetPlanList(List<FinCashResetPlan> finCashResetPlanList) {
        this.finCashResetPlanList = finCashResetPlanList;
    }

	public List<PtlTrade> getPtlTradeList() {
		return ptlTradeList;
	}

	public void setPtlTradeList(List<PtlTrade> ptlTradeList) {
		this.ptlTradeList = ptlTradeList;
	}

    /**
     * 获取 金融产品实物交易表
     *
     * @return trdFinProductBarter 金融产品实物交易表
     */
    public TrdFinProductBarter getTrdFinProductBarter() {
        return this.trdFinProductBarter;
    }

    /**
     * 设置 金融产品实物交易表
     *
     * @param trdFinProductBarter 金融产品实物交易表
     */
    public void setTrdFinProductBarter(TrdFinProductBarter trdFinProductBarter) {
        this.trdFinProductBarter = trdFinProductBarter;
    }
}
