/*
 * 文件名：EtrBondRightDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年7月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.order.exchange.business.dto;

import com.joyintech.background.component.order.exchange.common.constant.DictCodeList;
import com.joyintech.background.component.order.exchange.common.dto.EtrSinglePortfolioDto;


/**
 * 配债Dto
 * 
 * @author zhangrui1
 * @version 1.0 2020年7月8日
 * @see EtrBondRightDto
 * @since 1.0
 */
public class EtrBondRightDto extends EtrSinglePortfolioDto {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 8329607198239410921L;

    /**
     * 计算方式
     */
    private String calMode;

    /**
     * Constructor
     */
    public EtrBondRightDto() {
        super(DictCodeList.指令业务类型_配债.getValue());
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
