/*
 * 文件名：ValBokSubjectDtoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年5月17日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.accounadjustmanage.subjectset.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.service.valuation.accounadjustmanage.subjectset.dao.ValSubjectSet001Mapper;
import com.joyintech.service.valuation.accounadjustmanage.subjectset.dto.ValBokSubjectDto;

@Component(value="serviceValBokSubjectDtoValidator")
public class ValBokSubjectDtoValidator implements BeanValidator{

    @Autowired
    private ValSubjectSet001Mapper valSubjectSet001Mapper;
    @Override
    public List<ValidMessage> valid(Object value) {
        ValBokSubjectDto dto = (ValBokSubjectDto)value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            if (dto.getSelectedItem() == null || dto.getSelectedItem().size() == 0) {
                errorList.add(new ValidMessage("table", getMessage("VAL_COMMON_E_0002")));
            }
        }
        else{
            if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
                if(valSubjectSet001Mapper.checkExists(dto) == 0){
                    errorList.add(
                        new ValidMessage("subjectNo", getMessage("VAL_COMMON_E_0001.1")));
                }
            }
            else{
                if(valSubjectSet001Mapper.checkExists(dto) > 0){
                    errorList.add(
                        new ValidMessage("subjectNo", getMessage("VAL_COMMON_E_0011",getMessage("ValSubjectSet001.subjectNo"))));
                }
            }
        }
        return errorList;
    }

}
