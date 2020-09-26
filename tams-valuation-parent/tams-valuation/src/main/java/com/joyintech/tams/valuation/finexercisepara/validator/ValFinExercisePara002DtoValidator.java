/*
 * 文件名：ValFinExercisePara002Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年12月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.finexercisepara.validator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.finexercisepara.dto.ValFinExercisePara002Dto;
import com.joyintech.tams.valuation.finexercisepara.service.ValFinExercisePara002Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.workflow.engine.WorkflowContext;

/**
 * 税务方案 check
 * @author zhangwei
 * @version 1.0 2018年12月3日
 * @see ValFinExercisePara002DtoValidator
 * @since 1.0
 */
@Component
public class ValFinExercisePara002DtoValidator implements BeanValidator{

    @Autowired
    private ValFinExercisePara002Service valFinExercisePara002Service;
    
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    private final static Logger logger = LoggerFactory.getLogger(ValFinExercisePara002DtoValidator.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        ValFinExercisePara002Dto dto = (ValFinExercisePara002Dto)value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();

        try {
            // 判断当前流程是否存在：数据主键
            WflValidInputParameter validParam1 = new WflValidInputParameter();
            ProcessMark processMark1 = WorkflowContext.getProcessMarkService().getParameterByAnnotation(dto);
            validParam1.setWorkflowId(dto.getWorkflowId());
            validParam1.setPageId(processMark1.getPageId());

            validParam1.setBusinessDataPk1(dto.getBooksetId());
            validParam1.setBusinessDataPk2(dto.getFinprodId());
            if (mstWorkflowCommonService.isInWflProcess(validParam1)) {
                // 流程存在情况
                errorList.add(new ValidMessage(getMessage("COMMON_E_0021", "标识", dto.getBooksetId() + "_" + dto.getFinprodId())));
                return errorList;
            }
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return errorList;
    }

}
