/*
 * 文件名：PlanAssetCurrencyDealCommonResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2019年1月23日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.*;
import com.joyintech.framework.base.BaseEntity;

/**
 * 计划类资产货币型 交易返回集
 * 
 * @author HuangJian
 * @version 1.0 2019年1月23日
 * @see PlanAssetCurrencyDealCommonResult
 * @since 1.0
 */
public class PlanAssetCurrencyDealCommonResult extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 标的类金融产品
     */
    private List<FinProduct> listFinProduct = new ArrayList<FinProduct>();

    /**
     * 标的类金融产品扩展表
     */
    private List<FinProductAdd> listFinProductExt = new ArrayList<FinProductAdd>();

    /**
     * 金融产品分类表
     */
    private List<FinProductType> listFinProductType = new ArrayList<FinProductType>();

    /**
     * 金融产品估值参数表
     */
    private List<FinProductValuationPara> listFinProductValuationPara = new ArrayList<FinProductValuationPara>();

    /**
     * 现金流信息表
     */
    private List<FinCashInfo> listFinCashInfo = new ArrayList<FinCashInfo>();

    /**
     * 现金流支付规则表
     */
    private List<FinCashPayRule> listFinCashPayRule = new ArrayList<FinCashPayRule>();

    /**
     * 现金流计划表
     */
    private List<FinCashPlan> listFinCashPlan = new ArrayList<FinCashPlan>();
    /**
     * 交易费用明细表
     * ADD BY WUJIE ON 20210501
     */
    private List<TrdTradeFeeDetail> listValTradeFeeDetail = new ArrayList<TrdTradeFeeDetail>();


    /**
     * 获取 listFinProduct
     * 
     * @return listFinProduct.
     */
    public List<FinProduct> getListFinProduct() {
        return listFinProduct;
    }

    /**
     * 设置 listFinProduct
     * 
     * @param listFinProduct listFinProduct
     */
    public void setListFinProduct(List<FinProduct> listFinProduct) {
        this.listFinProduct = listFinProduct;
    }

    /**
     * 获取 listFinProductExt
     * 
     * @return listFinProductExt.
     */
    public List<FinProductAdd> getListFinProductExt() {
        return listFinProductExt;
    }

    /**
     * 设置 listFinProductExt
     * 
     * @param listFinProductExt listFinProductExt
     */
    public void setListFinProductExt(List<FinProductAdd> listFinProductExt) {
        this.listFinProductExt = listFinProductExt;
    }

    /**
     * 获取 listFinProductType
     * 
     * @return listFinProductType.
     */
    public List<FinProductType> getListFinProductType() {
        return listFinProductType;
    }

    /**
     * 设置 listFinProductType
     * 
     * @param listFinProductType listFinProductType
     */
    public void setListFinProductType(List<FinProductType> listFinProductType) {
        this.listFinProductType = listFinProductType;
    }

    /**
     * 获取 listFinProductValuationPara
     * 
     * @return listFinProductValuationPara.
     */
    public List<FinProductValuationPara> getListFinProductValuationPara() {
        return listFinProductValuationPara;
    }

    /**
     * 设置 listFinProductValuationPara
     * 
     * @param listFinProductValuationPara listFinProductValuationPara
     */
    public void setListFinProductValuationPara(List<FinProductValuationPara> listFinProductValuationPara) {
        this.listFinProductValuationPara = listFinProductValuationPara;
    }

    /**
     * 获取 listFinCashInfo
     * 
     * @return listFinCashInfo.
     */
    public List<FinCashInfo> getListFinCashInfo() {
        return listFinCashInfo;
    }

    /**
     * 设置 listFinCashInfo
     * 
     * @param listFinCashInfo listFinCashInfo
     */
    public void setListFinCashInfo(List<FinCashInfo> listFinCashInfo) {
        this.listFinCashInfo = listFinCashInfo;
    }

    /**
     * 获取 listFinCashPayRule
     * 
     * @return listFinCashPayRule.
     */
    public List<FinCashPayRule> getListFinCashPayRule() {
        return listFinCashPayRule;
    }

    /**
     * 设置 listFinCashPayRule
     * 
     * @param listFinCashPayRule listFinCashPayRule
     */
    public void setListFinCashPayRule(List<FinCashPayRule> listFinCashPayRule) {
        this.listFinCashPayRule = listFinCashPayRule;
    }

    /**
     * 获取 listFinCashPlan
     * 
     * @return listFinCashPlan.
     */
    public List<FinCashPlan> getListFinCashPlan() {
        return listFinCashPlan;
    }

    /**
     * 设置 listFinCashPlan
     * 
     * @param listFinCashPlan listFinCashPlan
     */
    public void setListFinCashPlan(List<FinCashPlan> listFinCashPlan) {
        this.listFinCashPlan = listFinCashPlan;
    }

    /**
     * 金融产品交易
     */
    private List<TrdProductDeal> listTrdProductDeal = new ArrayList<TrdProductDeal>();

    /**
     * 估值核算交易扩展表
     */
    private List<TrdProductDealAdd> listTrdProductDealExt = new ArrayList<TrdProductDealAdd>();

    /**
     * 入账信息表
     */
    private List<BokDealAccounting> listBokDealAccounting = new ArrayList<BokDealAccounting>();

    /**
     * 账务明细表
     */
    private List<BokDealAccountingDetail> listBokDealAccountingDetail = new ArrayList<BokDealAccountingDetail>();

    /**
     * 账务明细代码表
     */
    private List<BokDetailId> listBokDetailId = new ArrayList<BokDetailId>();

    /**
     * 结算指令表
     */
    private List<StlOrders> listStlOrders = new ArrayList<StlOrders>();

    /**
     * 结算指令扩展表
     */
    private List<StlOrdersExt> listStlOrdersExt = new ArrayList<StlOrdersExt>();

    /**
     * 获取 listTrdProductDeal
     * 
     * @return listTrdProductDeal.
     */
    public List<TrdProductDeal> getListTrdProductDeal() {
        return listTrdProductDeal;
    }

    /**
     * 设置 listTrdProductDeal
     * 
     * @param listTrdProductDeal listTrdProductDeal
     */
    public void setListTrdProductDeal(List<TrdProductDeal> listTrdProductDeal) {
        this.listTrdProductDeal = listTrdProductDeal;
    }

    /**
     * 获取 listTrdProductDealExt
     * 
     * @return listTrdProductDealExt.
     */
    public List<TrdProductDealAdd> getListTrdProductDealExt() {
        return listTrdProductDealExt;
    }

    /**
     * 设置 listTrdProductDealExt
     * 
     * @param listTrdProductDealExt listTrdProductDealExt
     */
    public void setListTrdProductDealExt(List<TrdProductDealAdd> listTrdProductDealExt) {
        this.listTrdProductDealExt = listTrdProductDealExt;
    }

    /**
     * 获取 listBokDealAccounting
     * 
     * @return listBokDealAccounting.
     */
    public List<BokDealAccounting> getListBokDealAccounting() {
        return listBokDealAccounting;
    }

    /**
     * 设置 listBokDealAccounting
     * 
     * @param listBokDealAccounting listBokDealAccounting
     */
    public void setListBokDealAccounting(List<BokDealAccounting> listBokDealAccounting) {
        this.listBokDealAccounting = listBokDealAccounting;
    }

    /**
     * 获取 listBokDealAccountingDetail
     * 
     * @return listBokDealAccountingDetail.
     */
    public List<BokDealAccountingDetail> getListBokDealAccountingDetail() {
        return listBokDealAccountingDetail;
    }

    /**
     * 设置 listBokDealAccountingDetail
     * 
     * @param listBokDealAccountingDetail listBokDealAccountingDetail
     */
    public void setListBokDealAccountingDetail(List<BokDealAccountingDetail> listBokDealAccountingDetail) {
        this.listBokDealAccountingDetail = listBokDealAccountingDetail;
    }

    /**
     * 获取 listBokDetailId
     * 
     * @return listBokDetailId.
     */
    public List<BokDetailId> getListBokDetailId() {
        return listBokDetailId;
    }

    /**
     * 设置 listBokDetailId
     * 
     * @param listBokDetailId listBokDetailId
     */
    public void setListBokDetailId(List<BokDetailId> listBokDetailId) {
        this.listBokDetailId = listBokDetailId;
    }

    /**
     * 获取 listStlOrders
     * 
     * @return listStlOrders.
     */
    public List<StlOrders> getListStlOrders() {
        return listStlOrders;
    }

    /**
     * 设置 listStlOrders
     * 
     * @param listStlOrders listStlOrders
     */
    public void setListStlOrders(List<StlOrders> listStlOrders) {
        this.listStlOrders = listStlOrders;
    }

    /**
     * 获取 listStlOrdersExt
     * 
     * @return listStlOrdersExt.
     */
    public List<StlOrdersExt> getListStlOrdersExt() {
        return listStlOrdersExt;
    }

    /**
     * 设置 listStlOrdersExt
     * 
     * @param listStlOrdersExt listStlOrdersExt
     */
    public void setListStlOrdersExt(List<StlOrdersExt> listStlOrdersExt) {
        this.listStlOrdersExt = listStlOrdersExt;
    }

    /**
     * 日志
     */
    private List<ValInterflog> valInterflog = new ArrayList<ValInterflog>();

    /**
     * 获取 valInterflog
     * 
     * @return valInterflog.
     */
    public List<ValInterflog> getValInterflog() {
        return valInterflog;
    }

    /**
     * 设置 valInterflog
     * 
     * @param valInterflog valInterflog
     */
    public void setValInterflog(List<ValInterflog> valInterflog) {
        this.valInterflog = valInterflog;
    }

    private int datanum;// 读取数据数

    /**
     * @return the datanum
     */
    public int getDatanum() {
        return datanum;
    }

    /**
     * @param datanum the datanum to set
     */
    public void setDatanum(int datanum) {
        this.datanum = datanum;
    }
    
    /**
     * 用于存放去重数据筛选条件
     */
    private Map<String,Object> paramMap;

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    /**
     * 获取 listValTradeFeeDetail
     * @return listValTradeFeeDetail.
     */
    public List<TrdTradeFeeDetail> getListValTradeFeeDetail() {
        return listValTradeFeeDetail;
    }


    /**
     * 设置 listValTradeFeeDetail
     * @param listValTradeFeeDetail listValTradeFeeDetail
     */
    public void setListValTradeFeeDetail(List<TrdTradeFeeDetail> listValTradeFeeDetail) {
        this.listValTradeFeeDetail = listValTradeFeeDetail;
    }

}
