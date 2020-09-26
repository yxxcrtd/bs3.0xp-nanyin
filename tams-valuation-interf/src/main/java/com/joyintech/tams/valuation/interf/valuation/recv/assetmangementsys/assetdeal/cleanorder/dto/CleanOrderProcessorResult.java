/*
 * 文件名：CleanOrderProcessorResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chendijun
 * 修改时间：2018年8月17日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.cleanorder.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.CleanOrderCommonResult;
import com.joyintech.tams.common.base.entity.BokDealAccounting;
import com.joyintech.tams.common.base.entity.BokDealAccountingDetail;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.common.base.entity.StlOrders;
import com.joyintech.tams.common.base.entity.StlOrdersExt;
import com.joyintech.tams.common.base.entity.ValInterflog;

/**
 * 
 * 〈功能详细描述〉
 * @author chendijun
 * @version 1.0 2018年8月17日
 * @see CleanOrderProcessorResult
 * @since 1.0
 */
public class CleanOrderProcessorResult extends BaseEntity{
    private static final long serialVersionUID = 1L;

    /**
     * 结算指令
     */
    private List<StlOrders> listValOrders = new ArrayList<StlOrders>();
    
    /**
     * 结算指令扩展表
     */
    private List<StlOrdersExt> listValOrdersExt = new ArrayList<StlOrdersExt>();
    
    
    /**
     * 账务明细代码表
     */
    private List<BokDetailId> listValBokDetailId=new ArrayList<BokDetailId>();
    
    /**
     * 入账信息表
     */
    private List<BokDealAccounting> listValDealAccounting = new ArrayList<BokDealAccounting>();
    
    /**
     * 账务明细表
     */
    private List<BokDealAccountingDetail> listValDealAccountingDetail = new ArrayList<BokDealAccountingDetail>();
    
    /**
     * 日志
     */
    private List<ValInterflog> valInterflog = new ArrayList<ValInterflog>();
    
    private Map<String,Object> paramMap;
    
    /**
     * 读取数据数量
     */
    private int dataNum;
    
    public CleanOrderProcessorResult() {
        
    }

    public CleanOrderProcessorResult(CleanOrderCommonResult result) {
        this.setListValOrders(result.getListValOrders());
        this.setListValOrdersExt(result.getListValOrdersExt());
        this.setListValDealAccounting(result.getListValDealAccounting());
        this.setListValDealAccountingDetail(result.getListValDealAccountingDetail());
        this.setListValBokDetailId(result.getListValBokDetailId());
        this.setValInterflog(result.getValInterflog());
        this.setParamMap(result.getParamMap());
        this.setDataNum(result.getDataNum());
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
     * 获取 paramMap
     * @return paramMap.
     */
    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    
    /**
     * 设置 paramMap
     * @param paramMap paramMap
     */
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
