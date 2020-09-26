/*
 * 文件名：ValProfitQuery001DtoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wanghb
 * 修改时间：2018年9月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.accounadjustmanage.ownerequitychange.validator;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.accounadjustmanage.ownerequitychange.dto.OwnerEquityChange001Dto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 校验查询条件
 * @author weipeng
 * @version 1.0 2018年11月8日
 * @see OwnerEquityChange001DtoValidator
 * @since 1.0
 */
@Component
public class OwnerEquityChange001DtoValidator implements BeanValidator{

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        OwnerEquityChange001Dto dto = (OwnerEquityChange001Dto)value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        //校验当选择季报时如果未选择某季节，则提示
        if(StringUtil.equals(dto.getReportTermType(), DictCodeListExt1.报表期限类型_季报.getValue())&&!StringUtil.isNotEmpty(dto.getReportTerm())){
            errorList.add(new ValidMessage("reportTermType", getMessage("VALSTATEACCOUNT_001_001")));
        }
        //校验当选择半年报时，需选择是上半年或下半年
        if(StringUtil.equals(dto.getReportTermType(), DictCodeListExt1.报表期限类型_半年报.getValue())&&!StringUtil.isNotEmpty(dto.getReportTerm())){
            errorList.add(new ValidMessage("reportTermType", getMessage("VALSTATEACCOUNT_001_002")));
        }
        return errorList;
    }

}
