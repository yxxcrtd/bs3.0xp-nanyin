/*
 * 文件名：CmpBondToStockService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年7月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.order.exchange.business.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.joyintech.background.component.order.exchange.business.dto.EtrBondToStockDto;
import com.joyintech.background.component.order.exchange.common.constant.DictCodeList;
import com.joyintech.background.component.order.exchange.common.constant.LocalConstant;
import com.joyintech.background.component.order.exchange.common.service.EtrSubComponentService;
import com.joyintech.background.component.order.exchange.common.util.EtrComponentUtil;


/**
 * 债转股子组件-Dto
 * 
 * @author zhangrui1
 * @version 1.0 2020年7月14日
 * @see CmpBondToStockService
 * @since 1.0
 */
@Component
class CmpBondToStockService extends EtrSubComponentService<EtrBondToStockDto> {

    /**
     * {@inheritDoc}
     */
    @Override
    public BindingResult entrustCheck(EtrBondToStockDto dto, BindingResult result) {
        String objectName = dto.getClass().getSimpleName();
        // 价格模式必须为指定价格
        if (!EtrComponentUtil.isType(dto.getPriceModel(), DictCodeList.价格模式_指定价格)) {
            result.addError(error(objectName, LocalConstant.PRICE_MODEL, "ETR_COMPONENT_ERR_009",
                getField(objectName, LocalConstant.PRICE_MODEL),
                DictCodeList.价格模式_指定价格.getCodeName()));
        }
        return result;
    }

}
