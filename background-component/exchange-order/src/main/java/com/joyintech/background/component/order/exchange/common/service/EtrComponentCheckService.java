/*
 * 文件名：EtrComponentCheckService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年7月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.order.exchange.common.service;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.joyintech.background.component.order.exchange.common.dto.EtrBaseDto;
import com.joyintech.background.component.order.exchange.common.util.EtrComponentUtil;

/**
 * 指令校验接口
 * 
 * @author zhangrui1
 * @version 1.0 2020年7月8日
 * @see EtrComponentCheckService
 * @since 1.0
 */
interface EtrComponentCheckService<V extends EtrBaseDto> {

    default FieldError error(String objectName, String field, String msgCode, String... args) {
        return new FieldError(objectName, field, getMessage(msgCode, args));
    }

    default String getField(String objectName, String componentField) {
        return EtrComponentUtil.getMessage(objectName + "." + componentField);
    }

    default String getMessage(String msgCode, String... args) {
        return EtrComponentUtil.getMessage(msgCode, args);
    }

    default String getFieldSelectText(String filed, String... selectCode) {
        if (selectCode == null || selectCode.length == 0) {
            return EtrComponentUtil.getMessage(filed);
        }
        StringBuilder builder = new StringBuilder();
        for (String code : selectCode) {
            builder.append(",").append(EtrComponentUtil.getMessage(builder + "." + code));
        }
        return builder.substring(1);
    }

    /**
     * 投资指令校验
     * 
     * @param dto 指令信息
     * @param result 校验结果信息
     * @return 校验结果
     */
    BindingResult entrustCheck(V dto, BindingResult result);
}
