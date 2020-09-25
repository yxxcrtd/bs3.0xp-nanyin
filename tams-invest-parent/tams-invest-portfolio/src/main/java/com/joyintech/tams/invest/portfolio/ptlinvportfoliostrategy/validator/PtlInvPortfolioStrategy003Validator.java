/*
 * 文件名：PtlInvPortfolioStrategy001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：niuqianyu
 * 修改时间：2019年7月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlinvportfoliostrategy.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.portfolio.ptlinvportfoliostrategy.dto.PtlIncomeIndexDto;

@Component
public class PtlInvPortfolioStrategy003Validator implements BeanValidator {

    @Override
    public List<ValidMessage> valid(Object value) {
        PtlIncomeIndexDto dto = (PtlIncomeIndexDto)value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();

        if (!StringUtil.isEmpty(dto.getIncomeType())
            && StringUtil.equals(dto.getIncomeType(), "02")) {
            if (!StringUtil.isNotEmpty(dto.getSpreadRate())) {
                errorList.add(new ValidMessage(
                    getMessage("FW_E_0006", getMessage("PtlInvPortfolioStrategy001.spreadRate"))));
            }
            if (StringUtil.isEmptyList(dto.getFloatList())) {
                errorList.add(new ValidMessage("基准信息不能为空！"));
            }
        }
        else if (!StringUtil.isEmpty(dto.getIncomeType())
                 && StringUtil.equals(dto.getIncomeType(), "01")) {
            if (!StringUtil.isNotEmpty(dto.getIncomeTarget())) {
                errorList.add(new ValidMessage(
                    getMessage("FW_E_0006", getMessage("PtlInvPortfolioStrategy001.incomeTarget"))));
            }
            if (!StringUtil.isNotEmpty(dto.getMaxIncomeTarget())) {
                errorList.add(new ValidMessage(
                    getMessage("FW_E_0006", getMessage("PtlInvPortfolioStrategy001.minIncomeTarget"))));
            }
            if (!StringUtil.isNotEmpty(dto.getMinIncomeTarget())) {
                errorList.add(new ValidMessage(
                    getMessage("FW_E_0006", getMessage("PtlInvPortfolioStrategy001.maxIncomeTarget"))));
            }

        }
        else {
            errorList.add(new ValidMessage(
                getMessage("FW_E_0006", getMessage("PtlInvPortfolioStrategy001.incomeType"))));
        }

        return errorList;
    }

}
