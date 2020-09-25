/*
 * 文件名：MstCustomerNatureValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwei
 * 修改时间：2018年8月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.mstcustomernature.validator;
import java.util.ArrayList;
import java.util.List;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;

/**
 * 校验
 * 〈一句话功能简述〉 
 * 〈功能详细描述〉
 * @author huwei
 * @version 1.0 2018年8月15日
 * @see MstCustomerNatureValidator
 * @since 1.0
 */
public class MstCustomerNatureValidator implements BeanValidator {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        List<ValidMessage> errorList = new ArrayList<>();
        return errorList;
    }

}
