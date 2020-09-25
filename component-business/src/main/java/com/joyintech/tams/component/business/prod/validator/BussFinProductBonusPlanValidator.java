/*
 * 文件名：BussFinProductBonusPlanValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.prod.validator;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.prod.dto.BussFinProductBonusPlanDto;
import com.joyintech.tams.constant.DictCodeList;


/**
 * 分红计划业务组件验证类
 * 
 * @author zhangliang
 * @version 1.0 2018年10月17日
 * @see BussFinProductBonusPlanValidator
 * @since 1.0
 */
@Component
public class BussFinProductBonusPlanValidator implements BeanValidator {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        BussFinProductBonusPlanDto bussFinProductBonusPlanDto = (BussFinProductBonusPlanDto)value;
        List<ValidMessage> validList = new ArrayList<ValidMessage>();
        if (!StringUtil.isNotEmpty(bussFinProductBonusPlanDto.getPayType())) {
            validList.add(new ValidMessage("payType",
                getMessage("BUSS_COM_E_0038", getMessage("BussFinProductBonusPlan.payType"))));
        }
        //净值型产品 以下参数不显示 不校验
        if(StringUtil.equals(DictCodeList.收益类型_净值型.getValue(), bussFinProductBonusPlanDto.getProfitType())){
            return validList;
        }
        if (!StringUtil.isNotEmpty(bussFinProductBonusPlanDto.getPayCycle())) {
            validList.add(new ValidMessage("payCycle",
                getMessage("BUSS_COM_E_0038", getMessage("BussFinProductBonusPlan.payRule"))));
        }
        else {
            if (StringUtil.equals(bussFinProductBonusPlanDto.getPayCycle(),
                DictCodeList.分红规则_到期支付.getValue())) {
                if (!StringUtil.isNotEmpty(bussFinProductBonusPlanDto.getPayTdayRule())) {
                    validList.add(new ValidMessage("payTdayRule", getMessage("BUSS_COM_E_0038",
                        getMessage("BussFinProductBonusPlan.payTDayRule"))));
                }
            }
            else if (StringUtil.equals(bussFinProductBonusPlanDto.getPayCycle(),
                DictCodeList.分红规则_周期支付.getValue())) {
                if (!StringUtil.isNotEmpty(bussFinProductBonusPlanDto.getPayCycleUnit())) {
                    validList.add(new ValidMessage("payCycleUnit", getMessage("BUSS_COM_E_0038",
                        getMessage("BussFinProductBonusPlan.payCycleUnit"))));
                }
                if (!StringUtil.isNotEmpty(bussFinProductBonusPlanDto.getPayCycleVal())) {
                    validList.add(new ValidMessage("payCycleVal", getMessage("BUSS_COM_E_0038",
                        getMessage("BussFinProductBonusPlan.payCycle"))));
                } else if(bussFinProductBonusPlanDto.getPayCycleVal() <= 0){
                    validList.add(new ValidMessage("payCycleVal", getMessage("FIN_PROD_E_0127",
                        getMessage("BussFinProductBonusPlan.payCycle"), "0")));
                }
                if (!StringUtil.isNotEmpty(bussFinProductBonusPlanDto.getCalcRule())) {
                    validList.add(new ValidMessage("calcRule", getMessage("BUSS_COM_E_0038",
                        getMessage("BussFinProductBonusPlan.calcRule"))));
                }
                if (!StringUtil.isNotEmpty(bussFinProductBonusPlanDto.getEndTdayRule())) {
                    validList.add(new ValidMessage("endTdayRule", getMessage("BUSS_COM_E_0038",
                        getMessage("BussFinProductBonusPlan.endTDayRule"))));
                }
                if (!StringUtil.isNotEmpty(bussFinProductBonusPlanDto.getPayTdayRule())) {
                    validList.add(new ValidMessage("payTdayRule", getMessage("BUSS_COM_E_0038",
                        getMessage("BussFinProductBonusPlan.payTDayRule"))));
                }
            }
        }

        // 一体化对应etliu
        // if (StringUtil.isNotEmpty(bussFinProductBonusPlanDto.getCalcRule())
        // && StringUtil.equals(bussFinProductBonusPlanDto.getCalcRule(),
        // DictCodeList.推算方法_首次分红日.getValue())){
        if (StringUtil.isNotEmpty(bussFinProductBonusPlanDto.getCalcRule()) && StringUtil.equals(
            bussFinProductBonusPlanDto.getCalcRule(), DictCodeList.推算方式_首次支付日.getValue())) {
            if (!StringUtil.isNotEmpty(bussFinProductBonusPlanDto.getFirstPayDate())) {
                validList.add(new ValidMessage("firstPayDate", getMessage("BUSS_COM_E_0038",
                    getMessage("BussFinProductBonusPlan.firstPayDate"))));
            }
            else if (DateUtil.compareDate(bussFinProductBonusPlanDto.getVdate(),
                bussFinProductBonusPlanDto.getFirstPayDate()) > 0) {
                validList.add(new ValidMessage("firstPayDate",
                    getMessage("FIN_PROD_E_0127", getMessage("BussFinProductBonusPlan.firstPayDate"), getMessage("FinProductBuild001.vdate"))));
            }
        }

        return validList;
    }

}
