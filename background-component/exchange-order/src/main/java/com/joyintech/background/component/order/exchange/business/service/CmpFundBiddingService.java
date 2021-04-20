/*
 * 文件名：CmpFundBiddingService.java
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
import org.springframework.validation.annotation.Validated;

import com.joyintech.background.component.order.exchange.business.dto.EtrFundBiddingDto;
import com.joyintech.background.component.order.exchange.common.constant.LocalConstant;
import com.joyintech.background.component.order.exchange.common.dto.EtrParameterDto;
import com.joyintech.background.component.order.exchange.common.exception.EtrComponentException;
import com.joyintech.background.component.order.exchange.common.service.EtrSubComponentService;
import com.joyintech.background.component.order.exchange.common.util.EtrComponentUtil;


/**
 * 基金竞价指令子组件-Service
 * 
 * @author zhangrui1
 * @version 1.0 2020年7月10日
 * @see CmpFundBiddingService
 * @since 1.0
 */
@Component
class CmpFundBiddingService extends EtrSubComponentService<EtrFundBiddingDto> {

    /**
     * {@inheritDoc}
     */
    @Override
    public BindingResult entrustCheck(@Validated EtrFundBiddingDto dto, BindingResult result) {
        // 交易规则配置
        EtrParameterDto settings = settings();
        // 规则配置不存在或者最小买卖单位不存在或者最小差价不存在时报错
        if (settings == null || EtrComponentUtil.isAmtEmpty(settings.getMinDealUnit(), false)
            || EtrComponentUtil.isAmtEmpty(settings.getMinDiffPrice(), false)) {
            throw new EtrComponentException("规则配置异常");
        }
        String objectName = dto.getClass().getSimpleName();
        // 基金买卖数量需要控制必须是最小买卖单位的整数倍
        if (!EtrComponentUtil.isNumberExactDivission(dto.getEntrustNum(),
            settings.getMinDealUnit())) {
            result.addError(
                error(objectName, LocalConstant.ENTRUST_NUM, "ETR_COMPONENT_ERR_002",
                    getField(objectName, LocalConstant.ENTRUST_NUM),
                    getField(settings.getClass().getSimpleName(), LocalConstant.MIN_DEAL_UNIT)));
        }
        // 基金买卖价格需要控制必须是最小价差的整数倍
        if (!EtrComponentUtil.isNumberExactDivission(dto.getPrice(), settings.getMinDiffPrice())) {
            result.addError(error(objectName, LocalConstant.PRICE, "ETR_COMPONENT_ERR_002",
                getField(objectName, LocalConstant.PRICE),
                getField(settings.getClass().getSimpleName(), LocalConstant.MIN_DIFF_PRICE)));
        }
        // 基金买卖价格需要控制必须在涨跌停范围内
        else if ((!EtrComponentUtil.isAmtEmpty(settings.getLimitUpAmt(), false)
                  || !EtrComponentUtil.isAmtEmpty(settings.getLimitDnAmt(), false))
                    && !EtrComponentUtil.isNumberInRange(dto.getPrice(), settings.getLimitDnAmt(),
            settings.getLimitUpAmt(), EtrComponentUtil.RANGE_MODE_CL_CL)) {
            result.addError(error(objectName, LocalConstant.PRICE, "ETR_COMPONENT_ERR_004",
                getField(objectName, LocalConstant.PRICE),
                getField(settings.getClass().getSimpleName(), LocalConstant.LIMIT_UP_DN_AMT)));
        }
        return result;
    }

}
