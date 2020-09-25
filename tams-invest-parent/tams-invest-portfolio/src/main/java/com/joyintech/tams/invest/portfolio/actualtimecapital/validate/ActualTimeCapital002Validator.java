/*
 * 文件名：PtlAccountCurrentInterest001Validator.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：fanshuanghu
 * 修改时间：2018年10月11日 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.actualtimecapital.validate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.portfolio.actualtimecapital.dto.ActualTimeCapital002Dto;

/**
 * 现金余额调整
 * 
 * @author yanya
 * @version 1.0 2019年10月16日
 * @see ActualTimeCapital002Validator
 * @since 1.0
 */
@Transactional
@Component
public class ActualTimeCapital002Validator implements BeanValidator {

    @Override
    public List<ValidMessage> valid(Object value) {
        ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
        ActualTimeCapital002Dto dto = (ActualTimeCapital002Dto) value;
        // 校验数据是否为空
        if (dto.getAdjAmt() == null) {
            errorList.add(new ValidMessage("adjAmt",getMessage("ActualTimeAsset002.adjAmt")));
        }
        return errorList;
    }
}
