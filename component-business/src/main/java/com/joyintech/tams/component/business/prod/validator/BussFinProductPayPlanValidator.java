/*
 * 文件名：BussFinProductPayPlanValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.prod.validator;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.component.business.prod.dto.BussFinProductPayPlanDto;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 付息计划验证逻辑
 * 
 * @author 何阳阳
 * @version 1.0 2018年9月20日
 * @see BussFinProductPayPlanValidator
 * @since 1.0
 */
@Component
public class BussFinProductPayPlanValidator implements BeanValidator {

    /**
     * 平台日基础组件
     */
    @Autowired(required = false)
    private IPlatformDate platformDate;

    @Override
    public List<ValidMessage> valid(Object value) {
        BussFinProductPayPlanDto dto = (BussFinProductPayPlanDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(dto.getPageId(), "FinProductBuild002")) {
            // 付息方法
            String payCycle = dto.getPayCycle();
            // 组件名称
            String namePrefix = dto.getEntityName();
            // 付息方法不能为空
            if (StringUtil.isEmpty(payCycle)) {
                resultList.add(new ValidMessage("payCycle",
                    getMessage("BUSS_COM_E_0038", getMessage("BussFinProductPayPlan.payCycle")),
                    namePrefix));
            }
            // 起息日不能为空
            if (null == dto.getVdate()) {
                resultList.add(new ValidMessage("vdate", getMessage("BUSS_COM_E_0038",
                    getMessage("BussFinProductPayPlan.other.msg.vdate"))));
            }
            // 到期日不能为空
            if (null == dto.getMdate()) {
                resultList.add(new ValidMessage("mdate", getMessage("BUSS_COM_E_0038",
                    getMessage("BussFinProductPayPlan.other.msg.mdate"))));
            }
            // 交易日历不能为空
            if (StringUtil.isEmpty(dto.getCalendarId())) {
                resultList.add(new ValidMessage("calendarId", getMessage("BUSS_COM_E_0038",
                    getMessage("BussFinProductPayPlan.other.calendarId"))));
            }
            // 分红方式不能为空
//            if (StringUtil.isEmpty(dto.getPayType())) {
//                resultList.add(new ValidMessage("payType",
//                    getMessage("BUSS_COM_E_0038", getMessage("BussFinProductPayPlan.payType")),
//                    namePrefix));
//            }
            // 当起息日、交易日历、付息方法都不为空时，验证其它属性
            if (null != dto.getVdate() && StringUtil.isNotEmpty(dto.getCalendarId())
                && StringUtil.isNotEmpty(payCycle)) {
                // 付息规则为到期时
                if (StringUtil.equals(payCycle, DictCodeListExt1.付息周期_到期支付.getValue())) {
                    // 付息营业日准则不能为空
                    if (StringUtil.isEmpty(dto.getPayTdayRule())) {
                        resultList.add(
                            new ValidMessage("payTdayRule", getMessage("BUSS_COM_E_0038",
                                getMessage("BussFinProductPayPlan.payTdayRule")), namePrefix));
                    }
                }
                // 付息规则选择为周期时
                else if (StringUtil.equals(payCycle, DictCodeListExt1.付息周期_周期支付.getValue())) {
                    // 付息周期不能为空
                    if (null == dto.getPayCycleVal() || dto.getPayCycleVal() == 0) {
                        resultList.add(
                            new ValidMessage("payCycleVal", getMessage("BUSS_COM_E_0038",
                                getMessage("BussFinProductPayPlan.payCycleVal")), namePrefix));
                    }
                    // 付息周期单位不能为空
                    if (StringUtil.isEmpty(dto.getPayCycleUnit())) {
                        resultList.add(
                            new ValidMessage("payCycleUnit",
                                getMessage("BUSS_COM_E_0038",
                                    getMessage("BussFinProductPayPlan.payCycleUnit")),
                                namePrefix));
                    }
                    // 推算方法不能为空
                    if (StringUtil.isEmpty(dto.getCalcRule())) {
                        resultList.add(new ValidMessage("calcRule", getMessage("BUSS_COM_E_0038",
                            getMessage("BussFinProductPayPlan.calcRule")), namePrefix));
                    }
                    // 计息营业日准则不能为空
                    if (StringUtil.isEmpty(dto.getEndTdayRule())) {
                        resultList.add(
                            new ValidMessage("endTdayRule", getMessage("BUSS_COM_E_0038",
                                getMessage("BussFinProductPayPlan.endTdayRule")), namePrefix));
                    }
                    // 付息营业日准则不能为空
                    if (StringUtil.isEmpty(dto.getPayTdayRule())) {
                        resultList.add(
                            new ValidMessage("payTdayRule", getMessage("BUSS_COM_E_0038",
                                getMessage("BussFinProductPayPlan.payTdayRule")), namePrefix));
                    }
                    // TODO 付息周期合法性check
                }
                // 推算方法选择为首次付息日时
             // 一体化对应etliu
//                if (StringUtil.equals(dto.getCalcRule(),
//                    DictCodeList.推算方法_首次付息日.getValue())) {
                if (StringUtil.equals(dto.getCalcRule(),
                        DictCodeList.推算方式_首次支付日.getValue())) {
                    // 首次付息日必须、合法性check
                    if (null == dto.getFirstPayDate()) {
                        resultList.add(
                            new ValidMessage("firstPayDate",
                                getMessage("BUSS_COM_E_0038",
                                    getMessage("BussFinProductPayPlan.firstPayDate")),
                                namePrefix));
                    }
                    else {
                        // 首次付息日<=起息日
                        if (dto.getVdate() != null
                            && DateUtil.compareDate(dto.getFirstPayDate(), dto.getVdate()) <= 0) {
                            resultList.add(new ValidMessage("firstPayDate",
                                getMessage("FIN_PROD_E_0015",
                                    getMessage("BussFinProductPayPlan.firstPayDate"),
                                    getMessage("BussFinProductPayPlan.other.msg.vdate")),
                                namePrefix));
                        }
                        // 到期日不为空时，首次付息日>到期日
                        if (null != dto.getMdate()
                            && DateUtil.compareDate(dto.getFirstPayDate(), dto.getMdate()) > 0) {
                            resultList.add(new ValidMessage("firstPayDate",
                                getMessage("FIN_PROD_E_0017",
                                    getMessage("BussFinProductPayPlan.firstPayDate"),
                                    getMessage("BussFinProductPayPlan.other.msg.mdate")),
                                namePrefix));
                        }
                        // 到期日为空时， 首次付息日>平台日期+n年
                        int year = StringUtil.toInt(
                            CacheUtil.getSysParam(BussinessConstant.MAX_AFTER_NOW_YEAR));
                        if (DateUtil.compareDate(dto.getFirstPayDate(),
                            DateUtil.addYear(platformDate.getSysDate(), year)) > 0) {
                            resultList.add(new ValidMessage("firstPayDate",
                                getMessage("FIN_PROD_E_0016",
                                    getMessage("BussFinProductPayPlan.firstPayDate"),
                                    year + getMessage("BussFinProductPayPlan.other.year")),
                                namePrefix));
                        }
                    }
                }
            }
        }

        return resultList;
    }

}
