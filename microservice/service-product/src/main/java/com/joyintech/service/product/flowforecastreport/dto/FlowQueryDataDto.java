/*
 * 文件名：FlowPredictReprotDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：weipeng
 * 修改时间：2019年8月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.flowforecastreport.dto;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.FlowForecastConfig;

/**
 * 查询数据用Dto
 * 
 * @author weipeng
 * @version 1.0 2019年8月12日
 * @see FlowQueryDataDto
 * @since 1.0
 */
public class FlowQueryDataDto extends FlowForecastConfig{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -2980598450877880948L;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 数量
     */
    private BigDecimal amount;

    /**
     * 类型
     */
    private String type;

    /**
     * 持仓类型
     */
    private String holdingType;

    /**
     * 日期区间
     */
    private String dateArea;

    /**
     * 日期区间位置
     */
    private String areaIndex;

    /**
     * 获取 金融产品代码
     * 
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * 
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 数量
     * 
     * @return 数量
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置 数量
     * 
     * @param amount 数量
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取 类型
     * 
     * @return 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置 类型
     * 
     * @param type 类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取 持仓类型
     * 
     * @return 持仓类型
     */
    public String getHoldingType() {
        return holdingType;
    }

    /**
     * 设置 持仓类型
     * 
     * @param holdingType 持仓类型
     */
    public void setHoldingType(String holdingType) {
        this.holdingType = holdingType;
    }

    /**
     * 获取 日期区间
     * 
     * @return 日期区间
     */
    public String getDateArea() {
        return dateArea;
    }

    /**
     * 设置 日期区间
     * 
     * @param dateArea 日期区间
     */
    public void setDateArea(String dateArea) {
        this.dateArea = dateArea;
    }

    /**
     * 获取 区间位置
     * 
     * @return 区间位置
     */
    public String getAreaIndex() {
        return areaIndex;
    }

    /**
     * 设置 区间位置
     * 
     * @param areaIndex 区间位置
     */
    public void setAreaIndex(String areaIndex) {
        this.areaIndex = areaIndex;
    }

}
