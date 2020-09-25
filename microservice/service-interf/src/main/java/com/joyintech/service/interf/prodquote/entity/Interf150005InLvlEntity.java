/*
 * 文件名：Interf150005InLvlEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.prodquote.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.core.annotation.EsbNumber;

/**
 * 报价接口150005-输入分层
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月9日
 * @see Interf150005InLvlEntity
 * @since 1.0
 */
public class Interf150005InLvlEntity implements Serializable {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -8122485467287209982L;

    /**
     * 持有产品天数区间
     */
    private String holdDaysInterval;

    /**
     * 购买金额区间
     */
    private String buyAmtInterval;

    /**
     * 客户等级区间
     */
    private String clientLvlInterval;

    /**
     * 分层收益率
     */
    private BigDecimal levelYield;

    /**
     * 年计息天数
     */
    private String yearInterestDay;

    /**
     * 取得 持有产品天数区间
     * 
     * @return 持有产品天数区间
     */
    @EsbName(value = "HOLD_DAYS_INTERVAL")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 100)
    public String getHoldDaysInterval() {
        return holdDaysInterval;
    }

    /**
     * 取得 购买金额区间
     * 
     * @return 购买金额区间
     */
    @EsbName(value = "BUY_AMT_INTERVAL")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 100)
    public String getBuyAmtInterval() {
        return buyAmtInterval;
    }

    /**
     * 取得 客户等级区间
     * 
     * @return 客户等级区间
     */
    @EsbName(value = "CLIENT_LEVEL_INTERVAL")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getClientLvlInterval() {
        return clientLvlInterval;
    }

    /**
     * 取得 分层收益率
     * 
     * @return 分层收益率
     */
    @EsbName(value = "LEVEL_YIELD")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 9)
    @Required
    public BigDecimal getLevelYield() {
        return levelYield;
    }

    /**
     * 取得 年计息天数
     * 
     * @return 年计息天数
     */
    @EsbName(value = "YEAR_INTEREST_DAYS")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    public String getYearInterestDay() {
        return yearInterestDay;
    }

    /**
     * 设置 持有产品天数区间
     * 
     * @param holdDaysInterval 持有产品天数区间
     */
    public void setHoldDaysInterval(String holdDaysInterval) {
        this.holdDaysInterval = holdDaysInterval;
    }

    /**
     * 设置 购买金额区间
     * 
     * @param buyAmtInterval 购买金额区间
     */
    public void setBuyAmtInterval(String buyAmtInterval) {
        this.buyAmtInterval = buyAmtInterval;
    }

    /**
     * 设置 客户等级区间
     * 
     * @param clientLvlInterval 客户等级区间
     */
    public void setClientLvlInterval(String clientLvlInterval) {
        this.clientLvlInterval = clientLvlInterval;
    }

    /**
     * 设置 分层收益率
     * 
     * @param levelYield 分层收益率
     */
    public void setLevelYield(BigDecimal levelYield) {
        this.levelYield = levelYield;
    }

    /**
     * 设置 年计息天数
     * 
     * @param yearInterestDay 年计息天数
     */
    public void setYearInterestDay(String yearInterestDay) {
        this.yearInterestDay = yearInterestDay;
    }
}
