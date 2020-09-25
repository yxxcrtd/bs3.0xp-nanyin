/*
 * 文件名：BussFinProductFeeInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.tcms002.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.MstFeeinfo;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 费用基本信息
 * 
 * @author changwenbin
 * @version 1.0 2018年11月14日
 * @see BussFinProductFeeInfoDto
 * @since 1.0
 */
public class BussFinProductFeeInfoDto extends MstFeeinfo {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 3029981712607501777L;

    /**
     * 主画面传入的pageId
     */
    private String pageId;

    /**
     * 现金流代码
     */
    private String cashId;

    /**
     * 交易日历
     */
    private String dealCalId;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 收益类型
     */
    private String profitType;

    /**
     * 费率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal feeRate;

    /**
     * 资产净值日
     */
    private String netAssetValueDay;

    /**
     * 首次计提日
     */
    private Date provisionFirstDay;

    /**
     * 付费规则
     */
    private String payFeeRule;

    /**
     * 计费营业准则
     */
    private String calcFeeTradeRule;

    /**
     * 付费营业日准则
     */
    private String payFeeTradeRule;

    /**
     * 付费计划列表
     */
    private List<FinCashPlan> finCashPlanList;

    /**
     * 费用列表
     */
    private List<BussFinProductFeeInfoDto> bussFinProductFeeInfoDtoList;

    public String getDealCalId() {
        return dealCalId;
    }

    public void setDealCalId(String dealCalId) {
        this.dealCalId = dealCalId;
    }

    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public String getProfitType() {
        return profitType;
    }

    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    public BigDecimal getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }

    public String getNetAssetValueDay() {
        return netAssetValueDay;
    }

    public void setNetAssetValueDay(String netAssetValueDay) {
        this.netAssetValueDay = netAssetValueDay;
    }

    public Date getProvisionFirstDay() {
        return provisionFirstDay;
    }

    public void setProvisionFirstDay(Date provisionFirstDay) {
        this.provisionFirstDay = provisionFirstDay;
    }

    public String getPayFeeRule() {
        return payFeeRule;
    }

    public void setPayFeeRule(String payFeeRule) {
        this.payFeeRule = payFeeRule;
    }

    public String getCalcFeeTradeRule() {
        return calcFeeTradeRule;
    }

    public void setCalcFeeTradeRule(String calcFeeTradeRule) {
        this.calcFeeTradeRule = calcFeeTradeRule;
    }

    public String getPayFeeTradeRule() {
        return payFeeTradeRule;
    }

    public void setPayFeeTradeRule(String payFeeTradeRule) {
        this.payFeeTradeRule = payFeeTradeRule;
    }

    public List<FinCashPlan> getFinCashPlanList() {
        return finCashPlanList;
    }

    public void setFinCashPlanList(List<FinCashPlan> finCashPlanList) {
        this.finCashPlanList = finCashPlanList;
    }

    /**
     * 获取 bussFinProductFeeInfoDtoList
     * 
     * @return bussFinProductFeeInfoDtoList.
     */
    public List<BussFinProductFeeInfoDto> getBussFinProductFeeInfoDtoList() {
        return bussFinProductFeeInfoDtoList;
    }

    /**
     * 设置 bussFinProductFeeInfoDtoList
     * 
     * @param bussFinProductFeeInfoDtoList bussFinProductFeeInfoDtoList
     */
    public void setBussFinProductFeeInfoDtoList(List<BussFinProductFeeInfoDto> bussFinProductFeeInfoDtoList) {
        this.bussFinProductFeeInfoDtoList = bussFinProductFeeInfoDtoList;
    }

    /**
     * 获取 cashId
     * 
     * @return cashId.
     */
    public String getCashId() {
        return cashId;
    }

    /**
     * 设置 cashId
     * 
     * @param cashId cashId
     */
    public void setCashId(String cashId) {
        this.cashId = cashId;
    }

    /**
     * 获取 pageId
     * 
     * @return pageId.
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * 设置 pageId
     * 
     * @param pageId pageId
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }
}
