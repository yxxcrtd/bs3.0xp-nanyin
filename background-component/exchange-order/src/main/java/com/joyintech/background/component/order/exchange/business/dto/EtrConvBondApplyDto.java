/*
 * 文件名：EtrConvBondApplyDto.java
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
 * 可转债信用申购子组件Dto
 * 
 * @author zhangrui1
 * @version 1.0 2020年7月14日
 * @see EtrConvBondApplyDto
 * @since 1.0
 */
public class EtrConvBondApplyDto extends EtrSinglePortfolioDto {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -8029121060578211801L;

    /**
     * 计算方法
     */
    private String calMode;

    public EtrConvBondApplyDto() {
        super(DictCodeList.指令业务类型_转债申购.getValue());
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
