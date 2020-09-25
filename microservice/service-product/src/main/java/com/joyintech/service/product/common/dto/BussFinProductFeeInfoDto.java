/*
 * 文件名：BussFinProductFeeInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.product.common.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.MstFeeinfo;

/**
 * 产品费用信息业务组件
 * 
 * @author zhangliang
 * @version 1.0 2018年10月11日
 * @see BussFinProductFeeInfoDto
 * @since 1.0
 */
public class BussFinProductFeeInfoDto extends MstFeeinfo {

    /**
     */
    private static final long serialVersionUID = -7293996269150791498L;
    
    /**
     * 现金流代码
     */
    private String cashId;

    /**
     * 产品款型
     */
    private String prodStyle;
    /**
     * 费率
     */
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
     * 首次计费结束日
     */
    private Date firstCalEndDate;
    
    /**
     * 计提结束日
     */
    private Date eAccrDate;

    /**
     * 付费计划列表
     */
    private List<FinCashPlan> finCashPlanList;

    /**
     * 费用列表
     */
    private List<BussFinProductFeeInfoDto> bussFinProductFeeInfoDtoList;
    /**
     * 产品代码
     */
    private String finprodId;
    /**
     * 首次计费基数
     */
    private String firstBaseType;
    
    public String getCashId() {
        return cashId;
    }

    public void setCashId(String cashId) {
        this.cashId = cashId;
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

    public List<BussFinProductFeeInfoDto> getBussFinProductFeeInfoDtoList() {
        return bussFinProductFeeInfoDtoList;
    }

    public void setBussFinProductFeeInfoDtoList(List<BussFinProductFeeInfoDto> bussFinProductFeeInfoDtoList) {
        this.bussFinProductFeeInfoDtoList = bussFinProductFeeInfoDtoList;
    }

	public Date getFirstCalEndDate() {
		return firstCalEndDate;
	}

	public void setFirstCalEndDate(Date firstCalEndDate) {
		this.firstCalEndDate = firstCalEndDate;
	}

	public Date geteAccrDate() {
		return eAccrDate;
	}

	public void seteAccrDate(Date eAccrDate) {
		this.eAccrDate = eAccrDate;
	}

    
    /**
     * 获取 prodStyle
     * @return prodStyle.
     */
    public String getProdStyle() {
        return prodStyle;
    }

    
    /**
     * 设置 prodStyle
     * @param prodStyle prodStyle
     */
    public void setProdStyle(String prodStyle) {
        this.prodStyle = prodStyle;
    }

	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	public String getFirstBaseType() {
		return firstBaseType;
	}

	public void setFirstBaseType(String firstBaseType) {
		this.firstBaseType = firstBaseType;
	}
    
    
}
