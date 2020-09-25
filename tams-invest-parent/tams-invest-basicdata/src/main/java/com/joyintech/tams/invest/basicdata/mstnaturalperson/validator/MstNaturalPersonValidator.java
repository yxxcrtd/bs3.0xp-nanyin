/*
 * 文件名：MstNaturalPersonValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年8月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.mstnaturalperson.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.basicdata.mstnaturalperson.dto.MstNaturalPerson001Dto;
import com.joyintech.tams.invest.basicdata.mstnaturalperson.service.MstNaturalPerson001Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 自然人管理校验 〈功能详细描述〉
 * 
 * @author xiexinting
 * @version 1.0 2018年8月15日
 * @see MstNaturalPersonValidator
 * @since 1.0
 */
@Component
public class MstNaturalPersonValidator implements BeanValidator {

    /**
     * 获取service对象
     */
    @Autowired
    private MstNaturalPerson001Service mstNaturalPerson001Service;

//    /**
//     * 获取mapper对象
//     */
//    @Autowired
//    private MstNaturalPerson001Mapper mstNaturalPerson001Mapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        MstNaturalPerson001Dto dto = (MstNaturalPerson001Dto)value;
        List<ValidMessage> errorList = new ArrayList<>();
        if (WorkFlowUtil.isNotFromToDoList()) {
            if (StringUtil.equals(dto.getOperateType(), "delete")) {
                // 行删除按钮与删除按钮校验
                List<MstNaturalPerson001Dto> listDto = dto.getSelectedItem();
                if (listDto.size() > 1) {
                    if (mstNaturalPerson001Service.checkAllCodeIsUse(listDto) > 0) {
                        errorList.add(new ValidMessage(getMessage("COMMON_E_0029")));
                    }
                }
                else {
                    if (mstNaturalPerson001Service.checkCodeIsUse(listDto.get(0)) > 0) {
                        errorList.add(new ValidMessage(getMessage("COMMON_E_0029")));
                    }
                }
            }
            else {
                // 新建校验
                if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
                    int count = mstNaturalPerson001Service.selectByPersonId(dto);
                    if (count > 0) {
                        errorList.add(
                            new ValidMessage("personId", this.getMessage("BUSS_COM_I_0017", this.getMessage("MstNaturalPerson001.personId"))));
                    }
                }
                else if (StringUtil.equals(dto.getPageModel(),
                    GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
                    // 修改校验
                    int count = mstNaturalPerson001Service.selectByPidandCid(dto);
                    if (count > 0) {
                        errorList.add(
                            new ValidMessage("personId", this.getMessage("BUSS_COM_I_0017", this.getMessage("MstNaturalPerson001.personId"))));
                    }
                }
            }
        }
        return errorList;
    }
}
