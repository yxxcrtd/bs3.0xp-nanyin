package com.joyintech.tams.valuation.valuationparametermanage.valtrdaccountmfconfig.validator;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.tams.valuation.valuationparametermanage.valtrdaccountmfconfig.dto.ValTrdAccountmfConfig001Dto;
import com.joyintech.workflow.engine.WorkflowContext;

/**
 * 费用预提配置 check
 * 
 * @author tanxuan
 * @version 1.0 2021年3月18日
 * @see ValTrdAccountmfConfig001DtoValidator
 * @since 1.0
 */
@Component
public class ValTrdAccountmfConfig001DtoValidator implements BeanValidator {

    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    private final static Logger logger = LoggerFactory.getLogger(
        ValTrdAccountmfConfig001DtoValidator.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        ValTrdAccountmfConfig001Dto dto = (ValTrdAccountmfConfig001Dto)value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();

        try {
        	// 判断当前流程是否存在：数据主键
            WflValidInputParameter validParam1 = new WflValidInputParameter();
            ProcessMark processMark1 = WorkflowContext.getProcessMarkService().getParameterByAnnotation(
                dto);
            validParam1.setWorkflowId(dto.getWorkflowId());
            validParam1.setPageId(processMark1.getPageId());

            validParam1.setBusinessDataPk1(dto.getSeqNo());
            if (mstWorkflowCommonService.isInWflProcess(validParam1)) {
                // 流程存在情况
                errorList.add(new ValidMessage(
                    getMessage("COMMON_E_0021", dto.getSeqNo(), "已在流程中")));
                return errorList;
            }
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return errorList;
    }

}
