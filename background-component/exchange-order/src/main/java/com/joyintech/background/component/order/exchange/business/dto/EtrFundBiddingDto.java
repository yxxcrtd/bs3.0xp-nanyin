/*
 * 文件名：EtrFundBiddingDto.java
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
 * 基金竞价
 * 
 * @author zhangrui1
 * @version 1.0 2020年7月8日
 * @see EtrFundBiddingDto
 * @since 1.0
 */
public class EtrFundBiddingDto extends EtrSinglePortfolioDto {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 5068367164278394754L;

    public EtrFundBiddingDto() {
        super(DictCodeList.指令业务类型_基金竞价.getValue());
    }
}
