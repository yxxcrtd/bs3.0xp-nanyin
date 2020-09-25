/*
 * 文件名：ValuationMethodScheme003Validator.java
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
import com.joyintech.service.valuation.valuationparametermanage.valuationmethodscheme.dto.ValuationMethodScheme003Dto;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.workflow.engine.WorkflowContext;

/**
 * 估值方案 check
 * @author zhangwei
 * @version 1.0 2018年11月29日
 * @see ValuationMethodScheme003Validator
 * @since 1.0
 */
@Component(value="serviceValuationMethodScheme003Validator")
public class ValuationMethodScheme003Validator implements BeanValidator{
    
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    private final static Logger logger = LoggerFactory.getLogger(ValuationMethodScheme003Validator.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        ValuationMethodScheme003Dto dto = (ValuationMethodScheme003Dto)value;
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
                // 唯一性验证：证券品种、交易市场、证券细分类、投资目的和开始日五者相同就违背数据的唯一性(同一方案代码下)
                WflValidInputParameter validParam2 = new WflValidInputParameter();
                ProcessMark processMark2 = WorkflowContext.getProcessMarkService().getParameterByAnnotation(dto);
                validParam2.setWorkflowId(dto.getWorkflowId());
                validParam2.setPageId(processMark2.getPageId());

                validParam2.setBusinessDataPk2(dto.getSchemeId());
                validParam2.setBusinessDataPk3(dto.getSecType());
                validParam2.setBusinessDataPk4(dto.getTradeMarket());
                validParam2.setBusinessDataPk5(dto.getSecDetailType());
                validParam2.setBusinessDataPk6(dto.getInvAim());
                validParam2.setBusinessDataPk7(dto.getVdate());
                if (mstWorkflowCommonService.isInWflProcess(validParam2)) {
                    // 流程存在情况
                    errorList.add(new ValidMessage(getMessage("COMMON_E_0021", "标识", "该资产估值配置在同一开始日已经存在，不得重复配置")));
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
