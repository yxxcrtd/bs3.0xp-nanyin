/*
 * 文件名：TaxScheme002DtoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年12月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.taxparametermanage.taxscheme.validator;

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
import com.joyintech.tams.valuation.taxparametermanage.taxscheme.dto.TaxScheme002Dto;
import com.joyintech.tams.valuation.taxparametermanage.taxscheme.service.TaxScheme002Service;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.workflow.engine.WorkflowContext;

/**
 * 税务方案 check
 * @author zhangwei
 * @version 1.0 2018年12月3日
 * @see TaxScheme002DtoValidator
 * @since 1.0
 */
@Component
public class TaxScheme002DtoValidator implements BeanValidator{

    @Autowired
    private TaxScheme002Service taxScheme002Service;
    
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    private final static Logger logger = LoggerFactory.getLogger(TaxScheme002DtoValidator.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        TaxScheme002Dto dto = (TaxScheme002Dto)value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();

        try {
            // 判断当前流程是否存在：数据主键
            WflValidInputParameter validParam1 = new WflValidInputParameter();
            ProcessMark processMark1 = WorkflowContext.getProcessMarkService().getParameterByAnnotation(dto);
            validParam1.setWorkflowId(dto.getWorkflowId());
            validParam1.setPageId(processMark1.getPageId());

            validParam1.setBusinessDataPk1(dto.getSchId());
            if (mstWorkflowCommonService.isInWflProcess(validParam1)) {
                // 流程存在情况
                errorList.add(new ValidMessage(getMessage("COMMON_E_0021", "标识", dto.getSchId())));
                return errorList;
            }

            if (!StringUtils.startsWith(dto.getOperateType(), "delete")) { // 非删除时
                // 判断当前流程是否存在：业务主键
                // 唯一性验证：方案代码
                WflValidInputParameter validParam2 = new WflValidInputParameter();
                ProcessMark processMark2 = WorkflowContext.getProcessMarkService().getParameterByAnnotation(dto);
                validParam2.setWorkflowId(dto.getWorkflowId());
                validParam2.setPageId(processMark2.getPageId());

                validParam2.setBusinessDataPk2(dto.getSchName());
                if (mstWorkflowCommonService.isInWflProcess(validParam2)) {
                    // 流程存在情况
                    errorList.add(new ValidMessage(getMessage("COMMON_E_0021", "标识", dto.getSchName())));
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
