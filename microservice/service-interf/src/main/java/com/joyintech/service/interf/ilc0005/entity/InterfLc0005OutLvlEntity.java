/*
 * 文件名：InterfLc0005OutLvlEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ilc0005.entity;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Required;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.core.annotation.EsbNumber;

/**
 * 理财产品报价报价查询接口Lc0005接口分层输出
 * 
 * @author changwenbin
 * @version 1.0 2018年11月8日
 * @see InterfLc0005OutLvlEntity
 * @since 1.0
 */
public class InterfLc0005OutLvlEntity extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 4582834278563885119L;

    /**
     * 持有产品天数区间
     */
    private String holddaysinterval;

    /**
     * 购买金额区间
     */
    private String buyamtinterval;

    /**
     * 客户等级区间
     */
    private String clientlevelinterval;

    /**
     * 分层收益率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bedyield;

    /**
     * 年计息天数
     */
    private String yearinterestdays;

    /**
     * 获取持有产品天数区间
     * 
     * @return 持有产品天数区间
     * @see
     * @since 1.0
     */
    @EsbName(value = "HOLD_DAYS_INTERVAL")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 100)
    public String getHolddaysinterval() {
        return holddaysinterval;
    }

    /**
     * 设置持有产品天数区间
     * 
     * @param holddaysinterval 持有产品天数区间
     * @see
     * @since 1.0
     */
    public void setHolddaysinterval(String holddaysinterval) {
        this.holddaysinterval = holddaysinterval;
    }

    /**
     * 获取购买金额区间
     * 
     * @return 购买金额区间
     * @see
     * @since 1.0
     */
    @EsbName(value = "BUY_AMT_INTERVAL")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 100)
    public String getBuyamtinterval() {
        return buyamtinterval;
    }

    /**
     * 设置购买金额区间
     * 
     * @param buyamtinterval 购买金额区间
     * @see
     * @since 1.0
     */
    public void setBuyamtinterval(String buyamtinterval) {
        this.buyamtinterval = buyamtinterval;
    }

    /**
     * 获取客户等级区间
     * 
     * @return 客户等级区间
     * @see
     * @since 1.0
     */
    @EsbName(value = "CLIENT_LEVEL_INTERVAL")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getClientlevelinterval() {
        return clientlevelinterval;
    }

    /**
     * 设置客户等级区间
     * 
     * @param clientlevelinterval 客户等级区间
     * @see
     * @since 1.0
     */
    public void setClientlevelinterval(String clientlevelinterval) {
        this.clientlevelinterval = clientlevelinterval;
    }

    /**
     * 获取分层收益率
     * 
     * @return 分层收益率
     * @see
     * @since 1.0
     */
    @EsbName(value = "BED_YIELD")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 9)
    @Required
    public BigDecimal getBedyield() {
        return bedyield;
    }

    /**
     * 设置分层收益率
     * 
     * @param bedyield 分层收益率
     * @see
     * @since 1.0
     */
    public void setBedyield(BigDecimal bedyield) {
        this.bedyield = bedyield;
    }

    /**
     * 获取年计息天数
     * 
     * @return 年计息天数
     * @see
     * @since 1.0
     */
    @EsbName(value = "YEAR_INTEREST_DAYS")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    public String getYearinterestdays() {
        return yearinterestdays;
    }

    /**
     * 设置年计息天数
     * 
     * @param yearinterestdays 年计息天数
     * @see
     * @since 1.0
     */
    public void setYearinterestdays(String yearinterestdays) {
        this.yearinterestdays = yearinterestdays;
    }

}
