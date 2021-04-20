/*
 * 文件名：CmpBlockTradePledgeRepoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年7月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.order.exchange.business.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.background.component.order.exchange.business.dto.EtrBlockTradePledgeRepoDto;
import com.joyintech.background.component.order.exchange.common.constant.LocalConstant;
import com.joyintech.background.component.order.exchange.common.dto.EtrParameterDto;

/**
 * 大宗债券质押回购指令子组件-Service
 * 
 * @author zhangrui1
 * @version 1.0 2020年7月10日
 * @see CmpBlockTradePledgeRepoService
 * @since 1.0
 */
@Component
class CmpBlockTradePledgeRepoService extends CmpBlockTradeService<EtrBlockTradePledgeRepoDto> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void checkMustWithCond(EtrBlockTradePledgeRepoDto dto, EtrParameterDto settings,
                                     BindingResult result) {
        String objectName = dto.getClass().getSimpleName();
        String priceModel = dto.getPriceModel();
        // 对手席位条件必填校验
        String pmField = getField(objectName, LocalConstant.PRICE_MODEL);
        String pmSelect = priceModelFieldName(priceModel);
        if (StringUtil.isNotEmpty(priceModel) && isPriceModelPM0809(priceModel)
            && StringUtil.isEmpty(dto.getCounterSeat())) {
            result.addError(error(dto.getClass().getSimpleName(), LocalConstant.COUNTER_SEAT,
                "ETR_COMPONENT_ERR_007", pmField, pmSelect,
                getField(objectName, LocalConstant.COUNTER_SEAT)));
        }
        // 约定号条件必填校验
        if (StringUtil.isNotEmpty(priceModel) && isPriceModelPM0809(priceModel)
            && StringUtil.isEmpty(dto.getCounterConNo())) {
            result.addError(error(dto.getClass().getSimpleName(), LocalConstant.COUNTER_CON_NO,
                "ETR_COMPONENT_ERR_007", pmField, pmSelect,
                getField(objectName, LocalConstant.COUNTER_CON_NO)));
        }
    }

}
