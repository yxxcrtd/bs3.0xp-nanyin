/*
 * 文件名：PlannedAssetBaseInfoForInterfRecProcessorResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年6月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.BokDealAccounting;
import com.joyintech.tams.common.base.entity.BokDealAccountingDetail;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.common.base.entity.StlOrders;
import com.joyintech.tams.common.base.entity.StlOrdersExt;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.common.base.entity.ValInterflog;
import com.joyintech.framework.base.BaseEntity;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author ruanhongming
 * @version 1.0 2018年6月25日
 * @see PlannedAssetDealCommonResult
 * @since 1.0
 */
public class PlannedAssetDealCommonResult extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 金融产品交易表
     */
    private List<TrdProductDeal> listValFinProductDeal = new ArrayList<TrdProductDeal>();

    /**
     * 估值核算交易扩展表
     */
    private List<TrdProductDealAdd> listValFinProductDealExt = new ArrayList<TrdProductDealAdd>();

    /**
     * 入账信息表
     */
    private List<BokDealAccounting> listValDealAccounting = new ArrayList<BokDealAccounting>();

    /**
     * 账务明细表
     */
    private List<BokDealAccountingDetail> listValDealAccountingDetail = new ArrayList<BokDealAccountingDetail>();

    //账务明细代码表
    private List<BokDetailId> listValBokDetailId=new ArrayList<BokDetailId>();
    /**
     * 日志
     */
    private List<ValInterflog> valInterflog = new ArrayList<ValInterflog>();
    
    /**
     * 交易费用明细
     */
    private List<TrdTradeFeeDetail> valTradeFeeDetailList = new ArrayList<TrdTradeFeeDetail>();
    /**
     * 
     */
    private List<StlOrders> valOrdersList = new ArrayList<StlOrders>();
    /**
     * 
     */
    private List<StlOrdersExt> valOrdersExtList = new ArrayList<StlOrdersExt>();
    
    /**
     * 交易类金融产品
     */
    private List<FinTradeProduct> listValTradeFinProduct = new ArrayList<FinTradeProduct>();
    
    /**
     * 读取数据数量
     */
    private int dataNum;
    
    /**
     * 获取 listValFinProductDeal
     * @return listValFinProductDeal.
     */
    public List<TrdProductDeal> getListValFinProductDeal() {
        return listValFinProductDeal;
    }

    
    /**
     * 设置 listValFinProductDeal
     * @param listValFinProductDeal listValFinProductDeal
     */
    public void setListValFinProductDeal(List<TrdProductDeal> listValFinProductDeal) {
        this.listValFinProductDeal = listValFinProductDeal;
    }

    
    /**
     * 获取 listValFinProductDealExt
     * @return listValFinProductDealExt.
     */
    public List<TrdProductDealAdd> getListValFinProductDealExt() {
        return listValFinProductDealExt;
    }

    
    /**
     * 设置 listValFinProductDealExt
     * @param listValFinProductDealExt listValFinProductDealExt
     */
    public void setListValFinProductDealExt(List<TrdProductDealAdd> listValFinProductDealExt) {
        this.listValFinProductDealExt = listValFinProductDealExt;
    }

    
    /**
     * 获取 listValDealAccounting
     * @return listValDealAccounting.
     */
    public List<BokDealAccounting> getListValDealAccounting() {
        return listValDealAccounting;
    }

    
    /**
     * 设置 listValDealAccounting
     * @param listValDealAccounting listValDealAccounting
     */
    public void setListValDealAccounting(List<BokDealAccounting> listValDealAccounting) {
        this.listValDealAccounting = listValDealAccounting;
    }

    
    /**
     * 获取 listValDealAccountingDetail
     * @return listValDealAccountingDetail.
     */
    public List<BokDealAccountingDetail> getListValDealAccountingDetail() {
        return listValDealAccountingDetail;
    }

    
    /**
     * 设置 listValDealAccountingDetail
     * @param listValDealAccountingDetail listValDealAccountingDetail
     */
    public void setListValDealAccountingDetail(List<BokDealAccountingDetail> listValDealAccountingDetail) {
        this.listValDealAccountingDetail = listValDealAccountingDetail;
    }

    
    /**
     * 获取 listValBokDetailId
     * @return listValBokDetailId.
     */
    public List<BokDetailId> getListValBokDetailId() {
        return listValBokDetailId;
    }

    
    /**
     * 设置 listValBokDetailId
     * @param listValBokDetailId listValBokDetailId
     */
    public void setListValBokDetailId(List<BokDetailId> listValBokDetailId) {
        this.listValBokDetailId = listValBokDetailId;
    }

    
    /**
     * 获取 valInterflog
     * @return valInterflog.
     */
    public List<ValInterflog> getValInterflog() {
        return valInterflog;
    }

    
    /**
     * 设置 valInterflog
     * @param valInterflog valInterflog
     */
    public void setValInterflog(List<ValInterflog> valInterflog) {
        this.valInterflog = valInterflog;
    }

    
    /**
     * 获取 valTradeFeeDetailList
     * @return valTradeFeeDetailList.
     */
    public List<TrdTradeFeeDetail> getValTradeFeeDetailList() {
        return valTradeFeeDetailList;
    }

    
    /**
     * 设置 valTradeFeeDetailList
     * @param valTradeFeeDetailList valTradeFeeDetailList
     */
    public void setValTradeFeeDetailList(List<TrdTradeFeeDetail> valTradeFeeDetailList) {
        this.valTradeFeeDetailList = valTradeFeeDetailList;
    }

    
    /**
     * 获取 valOrdersList
     * @return valOrdersList.
     */
    public List<StlOrders> getValOrdersList() {
        return valOrdersList;
    }

    
    /**
     * 设置 valOrdersList
     * @param valOrdersList valOrdersList
     */
    public void setValOrdersList(List<StlOrders> valOrdersList) {
        this.valOrdersList = valOrdersList;
    }

    
    /**
     * 获取 valOrdersExtList
     * @return valOrdersExtList.
     */
    public List<StlOrdersExt> getValOrdersExtList() {
        return valOrdersExtList;
    }

    
    /**
     * 设置 valOrdersExtList
     * @param valOrdersExtList valOrdersExtList
     */
    public void setValOrdersExtList(List<StlOrdersExt> valOrdersExtList) {
        this.valOrdersExtList = valOrdersExtList;
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
     * 获取 listValTradeFinProduct
     * @return listValTradeFinProduct.
     */
    public List<FinTradeProduct> getListValTradeFinProduct() {
        return listValTradeFinProduct;
    }


    /**
     * 设置 listValTradeFinProduct
     * @param listValTradeFinProduct listValTradeFinProduct
     */
    public void setListValTradeFinProduct(List<FinTradeProduct> listValTradeFinProduct) {
        this.listValTradeFinProduct = listValTradeFinProduct;
    }


    /**
     * 获取 dataNum
     * @return dataNum.
     */
    public int getDataNum() {
        return dataNum;
    }


    /**
     * 设置 dataNum
     * @param dataNum dataNum
     */
    public void setDataNum(int dataNum) {
        this.dataNum = dataNum;
    }
    
}
