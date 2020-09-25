/*
 * 文件名：ResultDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年7月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.basedata.marketdata.intratequote.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;

/**
 * 
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author HuangJian
 * @version 1.0 2018年7月23日
 * @see ResultDto
 * @since 1.0
 */
public class ResultDto  extends BaseEntity{
    /**
     * 利率代码
     */
    private String intRateId;
    /**
     * 利率
     */
    private String intRate;
    /**
     * 利率名称
     */
    private String rateName;
    /**
     * 利率类型
     */
    private String rateType;
    /**
     * 币种
     */
    private String quoCcy;
    /**
     * 数据来源
     */
    private String quoSrc;
    /**
     * 生效日
     */
    private String effDate;
    /**
     * 发布日
     */
    private String relaeseDate;
    
    
    
    /**
     * 获取 rateName
     * @return rateName.
     */
    public String getRateName() {
        return rateName;
    }

    
    /**
     * 设置 rateName
     * @param rateName rateName
     */
    public void setRateName(String rateName) {
        this.rateName = rateName;
    }

    
    /**
     * 获取 rateType
     * @return rateType.
     */
    public String getRateType() {
        return rateType;
    }

    
    /**
     * 设置 rateType
     * @param rateType rateType
     */
    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    
    /**
     * 获取 quoCcy
     * @return quoCcy.
     */
    public String getQuoCcy() {
        return quoCcy;
    }

    
    /**
     * 设置 quoCcy
     * @param quoCcy quoCcy
     */
    public void setQuoCcy(String quoCcy) {
        this.quoCcy = quoCcy;
    }

    
    /**
     * 获取 quoSrc
     * @return quoSrc.
     */
    public String getQuoSrc() {
        return quoSrc;
    }

    
    /**
     * 设置 quoSrc
     * @param quoSrc quoSrc
     */
    public void setQuoSrc(String quoSrc) {
        this.quoSrc = quoSrc;
    }

    /**
     * 获取 intRateId
     * @return intRateId.
     */
    public String getIntRateId() {
        return intRateId;
    }
    
    /**
     * 设置 intRateId
     * @param intRateId intRateId
     */
    public void setIntRateId(String intRateId) {
        this.intRateId = intRateId;
    }
    
    /**
     * 获取 intRate
     * @return intRate.
     */
    public String getIntRate() {
        return intRate;
    }
    
    /**
     * 设置 intRate
     * @param intRate intRate
     */
    public void setIntRate(String intRate) {
        this.intRate = intRate;
    }
    
    /**
     * 获取 effDate
     * @return effDate.
     */
    public String getEffDate() {
        return effDate;
    }
    
    /**
     * 设置 effDate
     * @param effDate effDate
     */
    public void setEffDate(String effDate) {
        this.effDate = effDate;
    }
    
    /**
     * 获取 relaeseDate
     * @return relaeseDate.
     */
    public String getRelaeseDate() {
        return relaeseDate;
    }
    
    /**
     * 设置 relaeseDate
     * @param relaeseDate relaeseDate
     */
    public void setRelaeseDate(String relaeseDate) {
        this.relaeseDate =relaeseDate;
            
    }
    
    
}
