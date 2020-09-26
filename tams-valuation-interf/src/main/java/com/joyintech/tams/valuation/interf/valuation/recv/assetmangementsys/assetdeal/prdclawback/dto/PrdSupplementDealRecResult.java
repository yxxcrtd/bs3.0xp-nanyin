/*
 * 文件名：PrdSupplementDealRecResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：dongjun
 * 修改时间：2019年2月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.prdclawback.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.BokDealAccounting;
import com.joyintech.tams.common.base.entity.BokDealAccountingDetail;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.common.base.entity.ValInterflog;
import com.joyintech.tams.valuation.common.interf.base.dto.InterfProductDealBookCommonDto;
import com.joyintech.tams.valuation.common.interf.base.dto.PrdSupplementDealRecCommonResult;

/**
 * 产品回补交易Processor返回共通结果 〈功能详细描述〉
 * 
 * @author dongjun
 * @version 1.0 2019年2月21日
 * @see PrdSupplementDealRecResult
 * @since 1.0
 */
public class PrdSupplementDealRecResult extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public PrdSupplementDealRecResult(PrdSupplementDealRecCommonResult result) {
        this.listValFinProductDeal = result.getListValFinProductDeal();
        this.listValFinProductDealExt = result.getListValFinProductDealExt();
        this.listValTradeFeeDetail = result.getListValTradeFeeDetail();
        this.listValInterflog = result.getListValInterflog();
        this.listInterfProductDealBookCommonDto = result.getListInterfProductDealBookCommonDto();
        this.bokDealAccountingList = result.getBokDealAccountingList();
        this.bokDealAccountingDetailList = result.getBokDealAccountingDetailList();
        this.bokDetailIdList = result.getBokDetailIdList();
        this.dataNum = result.getDataNum();
        this.paramMap = result.getParamMap();
    }

    /**
     * 金融产品交易表
     */
    private List<TrdProductDeal> listValFinProductDeal = new ArrayList<TrdProductDeal>();;

    /**
     * 估值核算交易扩展
     */
    private List<TrdProductDealAdd> listValFinProductDealExt = new ArrayList<TrdProductDealAdd>();

    /**
     * 交易费用明细表
     */
    private List<TrdTradeFeeDetail> listValTradeFeeDetail = new ArrayList<TrdTradeFeeDetail>();

    /**
     * 日志
     */
    private List<ValInterflog> listValInterflog = new ArrayList<ValInterflog>();

    /**
     * 交易入账
     */
    private List<InterfProductDealBookCommonDto> listInterfProductDealBookCommonDto = new ArrayList<InterfProductDealBookCommonDto>();

    /**
     * 入账信息List
     */
    private List<BokDealAccounting> bokDealAccountingList;

    /**
     * 入账账务明细List
     */
    private List<BokDealAccountingDetail> bokDealAccountingDetailList;

    /**
     * 账务明细代码表List
     */
    private List<BokDetailId> bokDetailIdList;

    /**
     * 读取数据数量
     */
    private int dataNum;

    /**
     * 用于存放去重数据筛选条件
     */
    private Map<String, Object> paramMap;

    public List<TrdProductDeal> getListValFinProductDeal() {
        return listValFinProductDeal;
    }

    public void setListValFinProductDeal(List<TrdProductDeal> listValFinProductDeal) {
        this.listValFinProductDeal = listValFinProductDeal;
    }

    public List<TrdProductDealAdd> getListValFinProductDealExt() {
        return listValFinProductDealExt;
    }

    public void setListValFinProductDealExt(List<TrdProductDealAdd> listValFinProductDealExt) {
        this.listValFinProductDealExt = listValFinProductDealExt;
    }

    public List<TrdTradeFeeDetail> getListValTradeFeeDetail() {
        return listValTradeFeeDetail;
    }

    public void setListValTradeFeeDetail(List<TrdTradeFeeDetail> listValTradeFeeDetail) {
        this.listValTradeFeeDetail = listValTradeFeeDetail;
    }

    public List<ValInterflog> getListValInterflog() {
        return listValInterflog;
    }

    public void setListValInterflog(List<ValInterflog> listValInterflog) {
        this.listValInterflog = listValInterflog;
    }

    public List<InterfProductDealBookCommonDto> getListInterfProductDealBookCommonDto() {
        return listInterfProductDealBookCommonDto;
    }

    public void setListInterfProductDealBookCommonDto(List<InterfProductDealBookCommonDto> listInterfProductDealBookCommonDto) {
        this.listInterfProductDealBookCommonDto = listInterfProductDealBookCommonDto;
    }

    public List<BokDealAccounting> getBokDealAccountingList() {
        return bokDealAccountingList;
    }

    public void setBokDealAccountingList(List<BokDealAccounting> bokDealAccountingList) {
        this.bokDealAccountingList = bokDealAccountingList;
    }

    public List<BokDealAccountingDetail> getBokDealAccountingDetailList() {
        return bokDealAccountingDetailList;
    }

    public void setBokDealAccountingDetailList(List<BokDealAccountingDetail> bokDealAccountingDetailList) {
        this.bokDealAccountingDetailList = bokDealAccountingDetailList;
    }

    public List<BokDetailId> getBokDetailIdList() {
        return bokDetailIdList;
    }

    public void setBokDetailIdList(List<BokDetailId> bokDetailIdList) {
        this.bokDetailIdList = bokDetailIdList;
    }

    public int getDataNum() {
        return dataNum;
    }

    public void setDataNum(int dataNum) {
        this.dataNum = dataNum;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

}
