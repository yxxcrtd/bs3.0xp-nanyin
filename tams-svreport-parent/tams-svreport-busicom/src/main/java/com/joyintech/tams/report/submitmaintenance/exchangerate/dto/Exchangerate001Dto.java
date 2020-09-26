/*
 * 文件名：EquiteBaseInfo001Dto.java
 * 版权：Copyright 2017-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中债报送子系统
 * 修改人：何阳阳
 * 修改时间：2017年9月14日
 * 修改内容：新建
 * 系统名称：中债报送子系统
 */

package com.joyintech.tams.report.submitmaintenance.exchangerate.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.tams.report.common.base.TableRow;


/**
 * 〈一句话功能简述〉
 * 
 * @author zyf
 * @version 1.0 2017年9月14日
 * @see Exchangerate001Dto
 * @since 1.0
 */
public class Exchangerate001Dto extends TableRow {

    /**
     * 数据状态
     */
    private String status;
    
    /**
     * 获取 agent
     * @return agent.
     */
    public String getAgent() {
        return agent;
    }


    
    /**
     * 设置 agent
     * @param agent agent
     */
    public void setAgent(String agent) {
        this.agent = agent;
    }


    /**
     * 数据状态Name
     */
    private String statusName;
    
    /**
     * 经办人
     */
    private String agent;
    
    /**
     * 获取 statusName
     * @return statusName.
     */
    public String getStatusName() {
        return statusName;
    }

    
    /**
     * 设置 statusName
     * @param statusName statusName
     */
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }


    /**
     * 原币种
     */
    private String ccyOriginal;

    /**
     * 原币种Name
     */
    private String ccyOriginalName;
    
    /**
     * 折算币种
     */
    private String ccyConvert;

    /**
     * 折算币种Name
     */
    private String ccyConvertName;
    
    /**
     * 公布日期
     */
    private Date publishDate;
    
    /**
     * 汇率
     */
    private BigDecimal exchangePrice;

    public String getCcyOriginalName() {
        return ccyOriginalName;
    }

    public void setCcyOriginalName(String ccyOriginalName) {
        this.ccyOriginalName = ccyOriginalName;
    }

    public String getCcyConvertName() {
        return ccyConvertName;
    }

    public void setCcyConvertName(String ccyConvertName) {
        this.ccyConvertName = ccyConvertName;
    }

    /**
     * 获取 status
     * @return status.
     */
    public String getStatus() {
        return status;
    }

    
    /**
     * 设置 status
     * @param status status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    
    /**
     * 获取 ccyOriginal
     * @return ccyOriginal.
     */
    public String getCcyOriginal() {
        return ccyOriginal;
    }

    
    /**
     * 设置 ccyOriginal
     * @param ccyOriginal ccyOriginal
     */
    public void setCcyOriginal(String ccyOriginal) {
        this.ccyOriginal = ccyOriginal;
    }

    
    /**
     * 获取 ccyConvert
     * @return ccyConvert.
     */
    public String getCcyConvert() {
        return ccyConvert;
    }

    
    /**
     * 设置 ccyConvert
     * @param ccyConvert ccyConvert
     */
    public void setCcyConvert(String ccyConvert) {
        this.ccyConvert = ccyConvert;
    }

    
    /**
     * 获取 publishDate
     * @return publishDate.
     */
    public Date getPublishDate() {
        return publishDate;
    }

    
    /**
     * 设置 publishDate
     * @param publishDate publishDate
     */
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    
    /**
     * 获取 exchangePrice
     * @return exchangePrice.
     */
    public BigDecimal getExchangePrice() {
        return exchangePrice;
    }

    
    /**
     * 设置 exchangePrice
     * @param exchangePrice exchangePrice
     */
    public void setExchangePrice(BigDecimal exchangePrice) {
        this.exchangePrice = exchangePrice;
    }



}
