/*
 * 文件名：ValVoucherQuery001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.assets.bondasset.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;

/**
 * 修改 账套日期
 * 
 * @author qujianzhong
 * @version 2.0 2018年11月01日
 * @see ValBondassetquery001Validator
 * @since 2.0
 */
@Component(value="serviceValBondassetquery001Validator")
public class ValBondassetquery001Validator implements BeanValidator {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        //错误信息
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        return errorList;
    }
}
