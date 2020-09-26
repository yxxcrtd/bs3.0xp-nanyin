/*
 * 文件名：CProdDealInfoProcessorResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chendijun
 * 修改时间：2018年8月13日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.cprod.dto;

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
import com.joyintech.tams.valuation.common.interf.base.dto.CProdDealInfoValCommonResult;

/**
 * 货币型产品交易信息Processor返回共通结果
 * 
 * @author chendijun
 * @version 1.0 2018年8月13日
 * @see CProdDealInfoProcessorResult
 * @since 1.0
 */
public class CProdDealInfoProcessorResult extends BaseEntity {
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
     * 交易费用明细
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
    private List<BokDetailId> listValBokDetailId=new ArrayList<BokDetailId>();
    
    /**
     * 结算指令
     */
    private List<StlOrders> listValOrders = new ArrayList<StlOrders>();
    
    /**
     * 结算指令扩展表
     */
    private List<StlOrdersExt> listValOrdersExt = new ArrayList<StlOrdersExt>();
    
    /**
     * 日志
     */
    private List<ValInterflog> valInterflog = new ArrayList<ValInterflog>();
    
    private Map<String,Object> paramMap;

    public CProdDealInfoProcessorResult(){
        
    };
    
    public CProdDealInfoProcessorResult (CProdDealInfoValCommonResult result) {
        this.setListValFinProductDeal(result.getListValFinProductDeal());
        this.setListValFinProductDealExt(result.getListValFinProductDealExt());
        this.setListValTradeFeeDetail(result.getValTradeFeeDetailList());
        this.setListValDealAccounting(result.getListValDealAccounting());
        this.setListValDealAccountingDetail(result.getListValDealAccountingDetail());
        this.setListValBokDetailId(result.getListValBokDetailId());
        this.setListValOrders(result.getListValOrders());
        this.setListValOrdersExt(result.getListValOrdersExt());
        this.setValInterflog(result.getValInterflog());
        this.paramMap = result.getParamMap();
        this.setDatanum(result.getDatanum());
    }
    
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
     * 获取 listValOrders
     * @return listValOrders.
     */
    public List<StlOrders> getListValOrders() {
        return listValOrders;
    }

    
    /**
     * 设置 listValOrders
     * @param listValOrders listValOrders
     */
    public void setListValOrders(List<StlOrders> listValOrders) {
        this.listValOrders = listValOrders;
    }

    
    /**
     * 获取 listValOrdersExt
     * @return listValOrdersExt.
     */
    public List<StlOrdersExt> getListValOrdersExt() {
        return listValOrdersExt;
    }

    
    /**
     * 设置 listValOrdersExt
     * @param listValOrdersExt listValOrdersExt
     */
    public void setListValOrdersExt(List<StlOrdersExt> listValOrdersExt) {
        this.listValOrdersExt = listValOrdersExt;
    }
    
    
    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
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

    private int datanum;//读取数据数



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
}
