/*
 * 文件名：CmpStockRightService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年7月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.order.exchange.business.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.joyintech.background.component.order.exchange.business.dto.EtrStockRightDto;
import com.joyintech.background.component.order.exchange.common.constant.DictCodeList;
import com.joyintech.background.component.order.exchange.common.constant.LocalConstant;
import com.joyintech.background.component.order.exchange.common.dto.EtrParameterDto;
import com.joyintech.background.component.order.exchange.common.exception.EtrComponentException;
import com.joyintech.background.component.order.exchange.common.service.EtrSubComponentService;
import com.joyintech.background.component.order.exchange.common.util.EtrComponentUtil;


/**
 * 配股指令子组件-Service
 * 
 * @author zhangrui1
 * @version 1.0 2020年7月9日
 * @see CmpStockRightService
 * @since 1.0
 */
@Component
class CmpStockRightService extends EtrSubComponentService<EtrStockRightDto> {

    /**
     * {@inheritDoc}
     */
    @Override
    public BindingResult entrustCheck(EtrStockRightDto dto, BindingResult result) {
        // 交易规则配置
        EtrParameterDto settings = settings();
        // 规则配置异常
        if (settings == null || EtrComponentUtil.isAmtEmpty(settings.getMinReportUnit(), false)) {
            throw new EtrComponentException("规则配置异常");
        }
        String objectName = dto.getClass().getSimpleName();
        // 价格模式必须为指定价格
        if (!EtrComponentUtil.isType(dto.getPriceModel(), DictCodeList.价格模式_指定价格)) {
            result.addError(error(objectName, LocalConstant.PRICE_MODEL, "ETR_COMPONENT_ERR_009",
                getField(objectName, LocalConstant.PRICE_MODEL),
                DictCodeList.价格模式_指定价格.getCodeName()));
        }
        // 数量精度不能小于最小申报单位
        if (!EtrComponentUtil.isNumberExactDivission(dto.getEntrustNum(),
            settings.getMinReportUnit())) {
            result.addError(
                error(objectName, LocalConstant.ENTRUST_NUM, "ETR_COMPONENT_ERR_008",
                    getField(objectName, LocalConstant.ENTRUST_NUM_SCALE),
                    getField(settings.getClass().getSimpleName(), LocalConstant.MIN_REPORT_UNIT)));
        }
        return result;
    }

}
