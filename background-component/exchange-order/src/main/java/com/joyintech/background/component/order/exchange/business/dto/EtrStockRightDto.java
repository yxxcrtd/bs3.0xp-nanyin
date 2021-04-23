/*
 * 文件名：EtrStockRightDto.java
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
 * 配股Dto
 * 
 * @author zhangrui1
 * @version 1.0 2020年7月8日
 * @see EtrStockRightDto
 * @since 1.0
 */
public class EtrStockRightDto extends EtrSinglePortfolioDto {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 8329607198239410921L;

    /**
     * Constructor
     */
    public EtrStockRightDto() {
        super(DictCodeList.指令业务类型_配股.getValue());
    }
}
