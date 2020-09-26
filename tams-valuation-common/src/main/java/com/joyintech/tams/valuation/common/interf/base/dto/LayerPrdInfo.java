/*
 * 文件名：LayerPrdInfo.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：dongjun
 * 修改时间：2018年10月15日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.dto;

import java.util.Date;

/**
 * 分层产品信息
 * 
 * @author dongjun
 * @version 1.0 2018年10月15日
 * @see LayerPrdInfo
 * @since 1.0
 */
public class LayerPrdInfo {

	/**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型，债券、理财产品、基金
     */
    private String finprodType;

    /**
     * 金融产品全称
     */
    private String finprodName;

    /**
     * 母金融产品代码
     */
    private String pFinprodId;

    /**
     * 收益类型，净值、预期收益、货币
     */
    private String profitType;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;
    
    /**
     * 实际到日期 add by wujie on 20190114
     */
    private Date actmdate;
    
    /**
     * 交易日历
     */
    private String calendarId;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 托管活期利息计提
     */
    private String isCalc;

    /**
     * 投资组合代码
     */
    private String portfolioId;

    /**
     * 是否核算
     */
    private String isAccount;

    /**
     * 是否分层核算
     */
    private String isLayerAccount;

    /**
     * 分支序号
     */
    private Long branch;
    
    public Long getBranch() {
		return branch;
	}

	public void setBranch(Long branch) {
		this.branch = branch;
	}

	/**
     * 获取 finprodId
     * 
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 finprodId
     * 
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 finprodType
     * 
     * @return finprodType.
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 finprodType
     * 
     * @param finprodType finprodType
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    /**
     * 获取 finprodName
     * 
     * @return finprodName.
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 finprodName
     * 
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    /**
     * 获取 pFinprodId
     * 
     * @return pFinprodId.
     */
    public String getpFinprodId() {
        return pFinprodId;
    }

    /**
     * 设置 pFinprodId
     * 
     * @param pFinprodId pFinprodId
     */
    public void setpFinprodId(String pFinprodId) {
        this.pFinprodId = pFinprodId;
    }

    /**
     * 获取 profitType
     * 
     * @return profitType.
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 profitType
     * 
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    /**
     * 获取 vdate
     * 
     * @return vdate.
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 vdate
     * 
     * @param vdate vdate
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 获取 mdate
     * 
     * @return mdate.
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 mdate
     * 
     * @param mdate mdate
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 获取 ccy
     * 
     * @return ccy.
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 ccy
     * 
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 isCalc
     * 
     * @return isCalc.
     */
    public String getIsCalc() {
        return isCalc;
    }

    /**
     * 设置 isCalc
     * 
     * @param isCalc isCalc
     */
    public void setIsCalc(String isCalc) {
        this.isCalc = isCalc;
    }

    /**
     * 获取 portfolioId
     * 
     * @return portfolioId.
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 portfolioId
     * 
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    /**
     * 获取 isAccount
     * 
     * @return isAccount.
     */
    public String getIsAccount() {
        return isAccount;
    }

    /**
     * 设置 isAccount
     * 
     * @param isAccount isAccount
     */
    public void setIsAccount(String isAccount) {
        this.isAccount = isAccount;
    }

    /**
     * 获取 isLayerAccount
     * 
     * @return isLayerAccount.
     */
    public String getIsLayerAccount() {
        return isLayerAccount;
    }

    /**
     * 设置 isLayerAccount
     * 
     * @param isLayerAccount isLayerAccount
     */
    public void setIsLayerAccount(String isLayerAccount) {
        this.isLayerAccount = isLayerAccount;
    }

    /**
     * 获取 calendarId
     * @return calendarId.
     */
    public String getCalendarId() {
        return calendarId;
    }

    /**
     * 设置 calendarId
     * @param calendarId calendarId
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    private String layeringId;//分层代码
    
    private String fLayeringType;//上层代码类型

	/**
	 * @return the layeringId
	 */
	public String getLayeringId() {
		return layeringId;
	}

	/**
	 * @param layeringId the layeringId to set
	 */
	public void setLayeringId(String layeringId) {
		this.layeringId = layeringId;
	}

	/**
	 * @return the fLayeringType
	 */
	public String getfLayeringType() {
		return fLayeringType;
	}

	/**
	 * @param fLayeringType the fLayeringType to set
	 */
	public void setfLayeringType(String fLayeringType) {
		this.fLayeringType = fLayeringType;
	}

	/**
	 * @return the actmdate
	 */
	public Date getActmdate() {
		return actmdate;
	}

	/**
	 * @param actmdate the actmdate to set
	 */
	public void setActmdate(Date actmdate) {
		this.actmdate = actmdate;
	}
    
}
