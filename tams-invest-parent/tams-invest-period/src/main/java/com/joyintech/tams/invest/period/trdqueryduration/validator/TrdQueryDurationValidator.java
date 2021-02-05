/*
 * 文件名：ListExport001Validator.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：qiangchengxi
 * 修改时间：2019年5月6日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.invest.period.trdqueryduration.validator;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.invest.period.trdqueryduration.dto.TrdQueryDurationDto;
import com.joyintech.tams.invest.period.trdqueryduration.service.TrdQueryDuration001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.BussinessConstant;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangyang1 
 * @version 1.0 2019年9月19日
 * @see TrdQueryDurationValidator
 * @since 1.0
 */
@Component
public class TrdQueryDurationValidator implements BeanValidator {
     
    @Autowired
    private TrdQueryDuration001Service service;
    
    @SuppressWarnings("unchecked")
    @Override
    public List<ValidMessage> valid(Object value) {
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        errorList.add(new ValidMessage(getMessage("资金到账日不允许为空！")));
        return errorList;
    }
}
