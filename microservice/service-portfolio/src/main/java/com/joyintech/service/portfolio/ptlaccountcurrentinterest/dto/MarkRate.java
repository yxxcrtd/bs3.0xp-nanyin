/*
 * 文件名：MarkRate.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年10月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.portfolio.ptlaccountcurrentinterest.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 基准类型
 * @author fanshuanghu
 * @version 1.0 2018年10月22日
 * @see MarkRate
 * @since 1.0
 */
public class MarkRate {
    /**
     * 利率代码
     */
    private String componentId;
    /**
     * 权重
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal weight;
    /**
     * 行情类型
     */
    private String componentType;
    /**
     * 
     */
    public MarkRate() {
        super();
    }
    
    /**
     * 获取 componentId
     * @return componentId.
     */
    public String getComponentId() {
        return componentId;
    }
    
    /**
     * 设置 componentId
     * @param componentId componentId
     */
    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }


    /**
     * 获取 componentType
     * @return componentType.
     */
    public String getComponentType() {
        return componentType;
    }

    
    /**
     * 设置 componentType
     * @param componentType componentType
     */
    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public BigDecimal getWeight() {
        return weight;
    }
    
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
}
