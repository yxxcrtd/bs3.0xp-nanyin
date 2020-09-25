/*
 * 文件名：ValProfitQuery001DtoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wanghb
 * 修改时间：2018年9月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.accounadjustmanage.valprofitquery.validator;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.valuation.accounadjustmanage.valprofitquery.dto.ValProfitQuery001Dto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 校验查询条件
 * @author wanghb
 * @version 1.0 2018年9月21日
 * @see ValProfitQuery001DtoValidator
 * @since 1.0
 */
@Component(value="serviceValProfitQuery001DtoValidator")
public class ValProfitQuery001DtoValidator implements BeanValidator{

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        ValProfitQuery001Dto dto = (ValProfitQuery001Dto)value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        // 当选择单账套时，账套代码不能为空
        if ("01".equals(dto.getReportDimen())) {
        	if (StringUtil.isEmpty(dto.getBooksetId())) {
        		errorList.add(new ValidMessage("reportDimen", "请输入账套代码。"));
        	}
        }
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
