/*
 * 文件名：EtrBondRepurchaseDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年7月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.order.exchange.business.dto;

import com.joyintech.background.component.order.exchange.common.constant.DictCodeList;
import com.joyintech.background.component.order.exchange.common.dto.EtrSinglePortfolioDto;


/**
 * 债券回售子组件-Dto
 * 
 * @author zhangrui1
 * @version 1.0 2020年7月14日
 * @see EtrBondRepurchaseDto
 * @since 1.0
 */
public class EtrBondRepurchaseDto extends EtrSinglePortfolioDto {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -7902324578363984170L;

    /**
     * 计算方式
     */
    private String calMode;

    public EtrBondRepurchaseDto() {
        super(DictCodeList.指令业务类型_债券回售.getValue());
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
}
