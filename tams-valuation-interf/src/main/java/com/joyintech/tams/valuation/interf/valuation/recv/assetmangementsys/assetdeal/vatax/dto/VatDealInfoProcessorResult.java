/*
 * 文件名：VatDealInfoProcessorResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangqidong
 * 修改时间：2018年8月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.vatax.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.BokDealAccounting;
import com.joyintech.tams.common.base.entity.BokDealAccountingDetail;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.common.base.entity.StlOrders;
import com.joyintech.tams.common.base.entity.StlOrdersExt;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.common.base.entity.ValInterflog;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.AssetDealInfoValCommonResult;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangqidong
 * @version 1.0 2018年8月16日
 * @see VatDealInfoProcessorResult
 * @since 1.0
 */
public class VatDealInfoProcessorResult extends BaseEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 金融产品交易
     */
    private List<TrdProductDeal> listValFinProductDeal = new ArrayList<TrdProductDeal>();
    /**
     * 估值核算交易扩展表
     */
    private List<TrdProductDealAdd> listValFinProductDealExt = new ArrayList<TrdProductDealAdd>();
    /**
     * 交易费用明细表
     */
    private List<TrdTradeFeeDetail> listValTradeFeeDetail = new ArrayList<TrdTradeFeeDetail>();
    /**
     * 入账信息表
     */
    private List<BokDealAccounting> listValDealAccounting = new ArrayList<BokDealAccounting>();
    /**
     * 账务明细表
     */
    private List<BokDealAccountingDetail> listValDealAccountingDetail = new ArrayList<BokDealAccountingDetail>();
    /**
     * 账务明细代码表
     */
    private List<BokDetailId> listValBokDetailId = new ArrayList<BokDetailId>();
    /**
     * 结算指令表 
     */
    private List<StlOrders> valOrdersList = new ArrayList<StlOrders>();
    /**
     * 结算指令扩展表
     */
    private List<StlOrdersExt> valOrdersExtList = new ArrayList<StlOrdersExt>();
    /**
     * 日志
     */
    private List<ValInterflog> valInterflog = new ArrayList<ValInterflog>();
    
    /**
     * 读取数据数量
     */
    private int dataNum;
    
    public VatDealInfoProcessorResult(){
        
    }
    /**
     * 重载构造方法 从valuation-common中返回的result重新赋值
     * @param result 共通结果
     */
    public VatDealInfoProcessorResult(AssetDealInfoValCommonResult result){
        this.setListValDealAccounting(result.getListValDealAccounting());
        this.setListValDealAccountingDetail(result.getListValDealAccountingDetail());
        this.setListValTradeFeeDetail(result.getListValTradeFeeDetail());
        this.setListValFinProductDeal(result.getListValFinProductDeal());
        this.setListValFinProductDealExt(result.getListValFinProductDealExt());
        this.setValInterflog(result.getValInterflog());       
        this.setListValBokDetailId(result.getListValBokDetailId());       
        this.setValOrdersList(result.getListValOrders());
        this.setValOrdersExtList(result.getListValOrdersExt());
        this.setDataNum(result.getDataNum());
        this.paramMap = result.getParamMap();
    }
    
    
    /**
     * 获取 金融产品交易
     * @return 金融产品交易.
     */
    public List<TrdProductDeal> getListValFinProductDeal() {
        return listValFinProductDeal;
    }
    
    /**
     * 设置 金融产品交易
     * @param listValFinProductDeal 金融产品交易
     */
    public void setListValFinProductDeal(List<TrdProductDeal> listValFinProductDeal) {
        this.listValFinProductDeal = listValFinProductDeal;
    }
    
    /**
     * 获取 估值核算交易扩展表
     * @return 估值核算交易扩展表.
     */
    public List<TrdProductDealAdd> getListValFinProductDealExt() {
        return listValFinProductDealExt;
    }
    
    /**
     * 设置 估值核算交易扩展表
     * @param listValFinProductDealExt 估值核算交易扩展表
     */
    public void setListValFinProductDealExt(List<TrdProductDealAdd> listValFinProductDealExt) {
        this.listValFinProductDealExt = listValFinProductDealExt;
    }
    
    /**
     * 获取 交易费用明细表
     * @return 交易费用明细表.
     */
    public List<TrdTradeFeeDetail> getListValTradeFeeDetail() {
        return listValTradeFeeDetail;
    }
    
    /**
     * 设置 交易费用明细表
     * @param listValTradeFeeDetail listValTra交易费用明细表deFeeDetail
     */
    public void setListValTradeFeeDetail(List<TrdTradeFeeDetail> listValTradeFeeDetail) {
        this.listValTradeFeeDetail = listValTradeFeeDetail;
    }
    
    /**
     * 获取 入账信息表
     * @return 入账信息表.
     */
    public List<BokDealAccounting> getListValDealAccounting() {
        return listValDealAccounting;
    }
    
    /**
     * 设置 入账信息表
     * @param listValDealAccounting 入账信息表
     */
    public void setListValDealAccounting(List<BokDealAccounting> listValDealAccounting) {
        this.listValDealAccounting = listValDealAccounting;
    }
    
    /**
     * 获取 账务明细表
     * @return 账务明细表.
     */
    public List<BokDealAccountingDetail> getListValDealAccountingDetail() {
        return listValDealAccountingDetail;
    }
    
    /**
     * 设置 账务明细表
     * @param listValDealAccountingDetail 账务明细表
     */
    public void setListValDealAccountingDetail(List<BokDealAccountingDetail> listValDealAccountingDetail) {
        this.listValDealAccountingDetail = listValDealAccountingDetail;
    }
    
    /**
     * 获取 账务明细代码表
     * @return 账务明细代码表.
     */
    public List<BokDetailId> getListValBokDetailId() {
        return listValBokDetailId;
    }
    
    /**
     * 设置 账务明细代码表
     * @param listValBokDetailId 账务明细代码表
     */
    public void setListValBokDetailId(List<BokDetailId> listValBokDetailId) {
        this.listValBokDetailId = listValBokDetailId;
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
     * 获取 日志
     * @return 日志.
     */
    public List<ValInterflog> getValInterflog() {
        return valInterflog;
    }
    
    /**
     * 设置 日志
     * @param valInterflog 日志
     */
    public void setValInterflog(List<ValInterflog> valInterflog) {
        this.valInterflog = valInterflog;
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


