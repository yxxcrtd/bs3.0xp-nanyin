/*
 * 文件名：ValuationMethodScheme004Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年11月29日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.valuationparametermanage.valuationmethodscheme.validator;

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
import com.joyintech.service.valuation.valuationparametermanage.valuationmethodscheme.dto.ValuationMethodScheme004Dto;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.workflow.engine.WorkflowContext;

/**
 * 估值方案 check
 * @author zhangwei
 * @version 1.0 2018年11月29日
 * @see ValuationMethodScheme004Validator
 * @since 1.0
 */
@Component(value="serviceValuationMethodScheme004Validator")
public class ValuationMethodScheme004Validator implements BeanValidator{
    
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    private final static Logger logger = LoggerFactory.getLogger(ValuationMethodScheme004Validator.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        ValuationMethodScheme004Dto dto = (ValuationMethodScheme004Dto)value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();

        try {
            // 判断当前流程是否存在：数据主键
            WflValidInputParameter validParam1 = new WflValidInputParameter();
            ProcessMark processMark1 = WorkflowContext.getProcessMarkService().getParameterByAnnotation(dto);
            validParam1.setWorkflowId(dto.getWorkflowId());
            validParam1.setPageId(processMark1.getPageId());

            validParam1.setBusinessDataPk1(dto.getSchemeDetailId());
            if (mstWorkflowCommonService.isInWflProcess(validParam1)) {
                // 流程存在情况
                errorList.add(new ValidMessage(getMessage("COMMON_E_0021", "标识", dto.getSchemeDetailId())));
                return errorList;
            }

            if (!StringUtils.startsWith(dto.getOperateType(), "delete")) { // 非删除时
                // 判断当前流程是否存在：业务主键
                // 唯一性校验：资产代码、投资目的、开始日相同时，不可提交。(同一方案代码下)
                // 日期重叠校验 TODO
                WflValidInputParameter validParam2 = new WflValidInputParameter();
                ProcessMark processMark2 = WorkflowContext.getProcessMarkService().getParameterByAnnotation(dto);
                validParam2.setWorkflowId(dto.getWorkflowId());
                validParam2.setPageId(processMark2.getPageId());

                validParam2.setBusinessDataPk2(dto.getSchemeId());
                validParam2.setBusinessDataPk3(dto.getFinprodId());
                validParam2.setBusinessDataPk4(dto.getInvAim());
                validParam2.setBusinessDataPk5(dto.getVdate());
                if (mstWorkflowCommonService.isInWflProcess(validParam2)) {
                    // 流程存在情况
                    errorList.add(new ValidMessage(getMessage("COMMON_E_0021", "标识", "在此投资目的下，该资产存在未结束的估值配置")));
                    return errorList;
                } 
            }
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return errorList;
    }

}
