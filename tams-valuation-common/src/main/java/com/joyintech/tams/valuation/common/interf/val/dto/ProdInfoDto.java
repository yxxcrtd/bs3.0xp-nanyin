/*
 * 文件名：ProdInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2019年1月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.common.interf.val.dto;

import java.util.Date;
import java.util.Map;

import com.google.common.collect.Maps;
import com.joyintech.framework.base.BaseEntity;

/**
 * 产品信息
 * 
 * @author zhangwei
 * @version 1.0 2019年1月10日
 * @see ProdInfoDto
 * @since 1.0
 */
public class ProdInfoDto extends BaseEntity {

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 金融产品简称（无金融产品简称传值同金融产品名称）
     */
    private String finprodAbbr;

    /**
     * 金融产品全称
     */
    private String finprodName;

    /**
     * 母金融产品代码
     */
    private String pFinprodId;

    /**
     * 收益类型，净值、预期收益、货币、结构
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
     * 币种
     */
    private String ccy;

    /**
     * 交易日历
     */
    private String calendarId;

    /**
     * 托管活期利息计提
     */
    private String isCalc;

    /**
     * 是否分层核算
     */
    private String isLayerAccount;

    /**
     * 投资组合代码
     */
    private String portfolioId;

    /**
     * 是否核算
     */
    private String isAccount;

    /**
     * 是否计税
     */
    private String isTax;

    /**
     * 估值表模板
     */
    private String valuationTemplate;

    /**
     * 到期日
     */
    private Date actmdate;
    
    
    /**
     * 账套参数Map
     */
    private Map<String, String> mapBooksetParam = Maps.newHashMap();
    
    
    public Date getActmdate() {
		return actmdate;
	}


	public void setActmdate(Date actmdate) {
		this.actmdate = actmdate;
	}


	public Map<String, String> getMapBooksetParam() {
        return mapBooksetParam;
    }

    
    public void setMapBooksetParam(Map<String, String> mapBooksetParam) {
        this.mapBooksetParam = mapBooksetParam;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodType() {
        return finprodType;
    }

    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    public String getpFinprodId() {
        return pFinprodId;
    }

    public void setpFinprodId(String pFinprodId) {
        this.pFinprodId = pFinprodId;
    }

    public String getProfitType() {
        return profitType;
    }

    public void setProfitType(String profitType) {
        this.profitType = profitType;
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

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    public String getIsCalc() {
        return isCalc;
    }

    public void setIsCalc(String isCalc) {
        this.isCalc = isCalc;
    }

    public String getIsLayerAccount() {
        return isLayerAccount;
    }

    public void setIsLayerAccount(String isLayerAccount) {
        this.isLayerAccount = isLayerAccount;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getIsAccount() {
        return isAccount;
    }

    public void setIsAccount(String isAccount) {
        this.isAccount = isAccount;
    }

    public String getIsTax() {
        return isTax;
    }

    public void setIsTax(String isTax) {
        this.isTax = isTax;
    }

    public String getValuationTemplate() {
        return valuationTemplate;
    }

    public void setValuationTemplate(String valuationTemplate) {
        this.valuationTemplate = valuationTemplate;
    }

}
