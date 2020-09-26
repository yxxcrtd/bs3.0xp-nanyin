/*
 * 文件名：ValVoucherQuery001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */
 
package com.joyintech.tams.valuation.accounadjustmanage.valuationtablegeneration.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;


/**
 * 模板示例<br>
 * 凭证查询
 * @author weipeng
 * @version 2.0 2018年5月3日
 * @see ValTableGenerate001Validator
 * @since 2.0
 */
@Component
public class ValTableGenerate001Validator implements BeanValidator{
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        List<ValidMessage> validMessageList = new ArrayList<ValidMessage>();
//        ValVoucherQuery001SearchDto dto = (ValVoucherQuery001SearchDto)value;
//        if (StringUtil.isNotEmpty(dto.getAccountDateStart()) && StringUtil.isNotEmpty(dto.getAccountDateEnd())) {
//            String st = dto.getAccountDateStart();
//            String et = dto.getAccountDateEnd();
//            if (DateUtil.compareDate(st, et) > 0) {
//                String msg = this.getMessage("VAL_COMMON_E_0001", 
//                    this.getMessage("ValVoucherQuery001.accountDateStart"),
//                    this.getMessage("ValVoucherQuery001.accountDateEnd"));
//                validMessageList.add(new ValidMessage("accountDateStart", msg));
//            }
//        }
        return validMessageList;
    }
    
}
