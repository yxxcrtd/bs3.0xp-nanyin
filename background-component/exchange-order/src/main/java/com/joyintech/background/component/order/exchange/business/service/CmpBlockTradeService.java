/*
 * 文件名：CmpBlockTradeService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年7月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.order.exchange.business.service;

import java.math.BigDecimal;

import org.springframework.validation.BindingResult;

import com.joyintech.background.component.common.pack.common.exception.ComponentException;
import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.background.component.order.exchange.common.constant.DictCode;
import com.joyintech.background.component.order.exchange.common.constant.DictCodeList;
import com.joyintech.background.component.order.exchange.common.constant.LocalConstant;
import com.joyintech.background.component.order.exchange.common.constant.UnitEnum;
import com.joyintech.background.component.order.exchange.common.dto.EtrParameterDto;
import com.joyintech.background.component.order.exchange.common.dto.EtrSinglePortfolioDto;
import com.joyintech.background.component.order.exchange.common.exception.EtrComponentException;
import com.joyintech.background.component.order.exchange.common.service.EtrSubComponentService;
import com.joyintech.background.component.order.exchange.common.util.EtrComponentUtil;


/**
 * 大宗交易指令组件-Service
 * 
 * @author zhangrui1
 * @version 1.0 2020年7月10日
 * @see CmpBlockTradeService
 * @since 1.0
 */
abstract class CmpBlockTradeService<V extends EtrSinglePortfolioDto> extends EtrSubComponentService<V> {

    /**
     * {@inheritDoc}
     */
    @Override
    public BindingResult entrustCheck(V dto, BindingResult result) {
        // 交易规则配置
        EtrParameterDto settings = settings();
        // 规则配置不存在或者最小买卖单位不存在或者最小差价不存在时报错
        if (settings == null //
            || EtrComponentUtil.isAmtEmpty(settings.getMinDealUnit(), false)
            || EtrComponentUtil.isAmtEmpty(settings.getMinDiffPrice(), false)) {
            throw new EtrComponentException("规则配置异常");
        }
        String objectName = dto.getClass().getSimpleName();
        // 指令数量需要控制必须是最小买卖单位的整数倍
        if (!EtrComponentUtil.isNumberExactDivission(dto.getEntrustNum(),
            settings.getMinDealUnit())) {
            result.addError(
                error(objectName, LocalConstant.ENTRUST_NUM, "ETR_COMPONENT_ERR_002",
                    getField(objectName, LocalConstant.ENTRUST_NUM),
                    getField(settings.getClass().getSimpleName(), LocalConstant.MIN_DEAL_UNIT)));
        }
        else {
            // 校验大宗交易平台指令数量限额
            checkEntrustNum(dto, settings, result);
        }
        // 指令价格需要控制必须是最小价差的整数倍
        if (!EtrComponentUtil.isNumberExactDivission(dto.getPrice(), settings.getMinDiffPrice())) {
            result.addError(error(objectName, LocalConstant.PRICE, "ETR_COMPONENT_ERR_002",
                getField(objectName, LocalConstant.PRICE),
                getField(settings.getClass().getSimpleName(), LocalConstant.MIN_DIFF_PRICE)));
        }
        // 股票买卖价格需要控制必须在涨跌停范围内
        // else if (!EtrComponentUtil.isNumberInRange(dto.getPrice(), settings.getLimitDnAmt(),
        // settings.getLimitUpAmt())) {
        // result.addError(error(objectName, LocalConstant.PRICE, "ETR_COMPONENT_ERR_004",
        // getField(objectName, LocalConstant.PRICE),
        // getField(settings.getClass().getSimpleName(), LocalConstant.LIMIT_UP_DN_AMT)));
        // }
        // 条件必填项校验
        checkMustWithCond(dto, settings, result);
        return result;
    }

    /**
     * 校验大宗交易平台指令数量限额
     * 
     * @param dto 指令信息
     * @param settings 配置信息
     * @param result 校验结果
     */
    protected void checkEntrustNum(V dto, EtrParameterDto settings, BindingResult result) {
        BigDecimal[] limitNum = settings.limitNum(dto.getTradeMarket());
        BigDecimal[] limitAmt = settings.limitAmt(dto.getTradeMarket());
        boolean isNumInRng = EtrComponentUtil.isNumberInRange(dto.getEntrustNum(), limitNum,
            EtrComponentUtil.RANGE_MODE_CL_CL);
        boolean isAmtInRng = EtrComponentUtil.isNumberInRange(dto.getEntrustAmt(), limitAmt,
            EtrComponentUtil.RANGE_MODE_CL_CL);
        String tradeMarket = dto.getTradeMarket();
        if (StringUtil.isNotEmpty(tradeMarket)
            && (!isNumInRng && !isAmtInRng)) {
            result.addError(
                error(dto.getClass().getSimpleName(),
                    LocalConstant.ENTRUST_NUM + "," + LocalConstant.ENTRUST_AMT,
                    "ETR_COMPONENT_ERR_005",
                    limitNumMsg(limitNum, settings.getUnitEnum(tradeMarket), 0) + "，或者"
                                             + getMessage("ETR_COMPONENT_ERR_011",
                                                 limitNumMsg(limitAmt, UnitEnum.元, 0))));
        }
    }

    /**
     * 条件必填项校验
     * 
     * @param dto 指令信息
     * @param settings 配置项
     * @param result 校验结果
     */
    protected void checkMustWithCond(V dto, EtrParameterDto settings, BindingResult result) {
        // override by child class
    }

    /**
     * 数量限额Message添加单位（例：不低于30万份）
     * 
     * @param arr 限额范围
     * @param unit 单位
     * @param scale 保留小数位
     * @return 带单位的Message
     */
    protected String limitNumMsg(BigDecimal[] arr, UnitEnum unit, int scale) {
        StringBuilder builder = new StringBuilder();
        if (arr == null || arr.length == 0) {
            return "";
        }
        if (arr[0] != null) {
            builder.append("不低于").append(
                unit != null ? unit.showMeWithUnit(arr[0], scale) : arr[0]);
        }
        if (arr.length == 2 && arr[1] != null) {
            if (arr[0] != null) {
                builder.append("且");
            }
            builder.append("不高于").append(
                unit != null ? unit.showMeWithUnit(arr[1], scale) : arr[1]);
        }
        return builder.toString();
    }

    /**
     * 价格模式是否为点击成交申报、成交申报
     * 
     * @return true：是
     */
    protected boolean isPriceModelPM0809(String priceModel) {
        return EtrComponentUtil.isType(priceModel, DictCodeList.价格模式_点击成交申报)
               || EtrComponentUtil.isType(priceModel, DictCodeList.价格模式_成交申报);
    }

    /**
     * 价格模式名称
     * 
     * @param priceModel 价格模式字典代码
     * @return 价格模式字典名称
     */
    protected String priceModelFieldName(String priceModel) {
        DictCodeList dictList = DictCodeList.from(DictCode.价格模式, priceModel);
        if (dictList == null) {
            throw new ComponentException("不存在的价格模式：" + priceModel);
        }
        return dictList.getCodeName();
    }
}
