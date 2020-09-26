/*
 * 文件名：PlanAssetCurrencyDealRecProcessorResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2019年1月23日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.pacd.dto;

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
import com.joyintech.tams.common.base.entity.ValInterflog;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.PlanAssetCurrencyDealCommonResult;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author HuangJian
 * @version 1.0 2019年1月23日
 * @see PlanAssetCurrencyDealRecProcessorResult
 * @since 1.0
 */
public class PlanAssetCurrencyDealRecProcessorResult extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public PlanAssetCurrencyDealRecProcessorResult(PlanAssetCurrencyDealCommonResult result) {
        this.datanum = result.getDatanum();
        this.listBokDealAccounting = result.getListBokDealAccounting();
        this.listBokDealAccountingDetail = result.getListBokDealAccountingDetail();
        this.listBokDetailId = result.getListBokDetailId();
        this.listStlOrders = result.getListStlOrders();
        this.listStlOrdersExt = result.getListStlOrdersExt();
        this.listTrdProductDeal = result.getListTrdProductDeal();
        this.listTrdProductDealExt = result.getListTrdProductDealExt();
        this.valInterflog = result.getValInterflog();
        this.paramMap = result.getParamMap();
    }

    /**
     * 
     */
    public PlanAssetCurrencyDealRecProcessorResult() {
        super();
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
    private Map<String, Object> paramMap;

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

}
