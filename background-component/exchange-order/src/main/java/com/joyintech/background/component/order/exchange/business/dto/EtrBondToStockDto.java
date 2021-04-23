/*
 * 文件名：EtrBondToStockDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年7月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.order.exchange.business.dto;

import java.math.BigDecimal;

import com.joyintech.background.component.order.exchange.common.constant.DictCodeList;
import com.joyintech.background.component.order.exchange.common.dto.EtrSinglePortfolioDto;


/**
 * 债转股子组件-Dto
 * 
 * @author zhangrui1
 * @version 1.0 2020年7月14日
 * @see EtrBondToStockDto
 * @since 1.0
 */
public class EtrBondToStockDto extends EtrSinglePortfolioDto {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 7747312064429874397L;

    /**
     * 计算方式
     */
    private String calMode;

    /**
     * 预计转换股数
     */
    private BigDecimal expConNum;

    public EtrBondToStockDto() {
        super(DictCodeList.指令业务类型_债转股.getValue());
    }

    /**
     * 获取 计算方式
     * 
     * @return 计算方式.
     */
    public String getCalMode() {
        return calMode;
    }

    /**
     * 设置 计算方式
     * 
     * @param calMode 计算方式
     */
    public void setCalMode(String calMode) {
        this.calMode = calMode;
    }

    /**
     * 获取 预计转换股数
     * 
     * @return 预计转换股数.
     */
    public BigDecimal getExpConNum() {
        return expConNum;
    }

    /**
     * 设置 预计转换股数
     * 
     * @param expConNum 预计转换股数
     */
    public void setExpConNum(BigDecimal expConNum) {
        this.expConNum = expConNum;
    }
}
