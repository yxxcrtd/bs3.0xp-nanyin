/*
 * 文件名：CmpStockNewService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年7月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.order.exchange.business.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.joyintech.background.component.order.exchange.business.dto.EtrStockNewDto;
import com.joyintech.background.component.order.exchange.common.constant.DictCodeList;
import com.joyintech.background.component.order.exchange.common.constant.LocalConstant;
import com.joyintech.background.component.order.exchange.common.dto.EtrParameterDto;
import com.joyintech.background.component.order.exchange.common.exception.EtrComponentException;
import com.joyintech.background.component.order.exchange.common.service.EtrSubComponentService;
import com.joyintech.background.component.order.exchange.common.util.EtrComponentUtil;


/**
 * 新股指令子组件-Service
 * 
 * @author zhangrui1
 * @version 1.0 2020年7月13日
 * @see CmpStockNewService
 * @since 1.0
 */
@Component
class CmpStockNewService extends EtrSubComponentService<EtrStockNewDto> {

    /**
     * {@inheritDoc}
     */
    @Override
    public BindingResult entrustCheck(EtrStockNewDto dto, BindingResult result) {
        String objectName = dto.getClass().getSimpleName();
        // 交易规则配置
        EtrParameterDto settings = settings();
        // 规则配置不存在或者最小买卖单位不存在或者最小差价不存在时报错
        if (settings == null //
            || EtrComponentUtil.isAmtEmpty(settings.getMinDealUnit(), false)) {
            throw new EtrComponentException("规则配置异常");
        }
        // 价格模式必须为指定价格
        if (!EtrComponentUtil.isType(dto.getPriceModel(), DictCodeList.价格模式_指定价格)) {
            result.addError(error(objectName, LocalConstant.PRICE_MODEL, "ETR_COMPONENT_ERR_009",
                getField(objectName, LocalConstant.PRICE_MODEL),
                DictCodeList.价格模式_指定价格.getCodeName()));
        }
        // 指令数量需要控制必须是最小买卖单位的整数倍
        if (!EtrComponentUtil.isNumberExactDivission(dto.getEntrustNum(),
            settings.getMinDealUnit())) {
            result.addError(
                error(objectName, LocalConstant.ENTRUST_NUM, "ETR_COMPONENT_ERR_002",
                    getField(objectName, LocalConstant.ENTRUST_NUM),
                    getField(settings.getClass().getSimpleName(), LocalConstant.MIN_DEAL_UNIT)));
        }
        return result;
    }

}
