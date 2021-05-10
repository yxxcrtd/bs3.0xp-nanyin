/*
 * 文件名：ValAssetBookParameter002Validator.java
 * 描述：账套参数管理
 * 修改人：tanxuan
 * 修改时间：2019年11月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.assetbookparameter.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.accounadjustmanage.assetbookparameter.dto.ValAssetBookParameter002Dto;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.workflow.engine.WorkflowContext;

@Component
public class ValAssetBookParameter002Validator implements BeanValidator {
	
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        List<ValidMessage> validMessageList = new ArrayList<ValidMessage>();
        List<String> booksetIds = new ArrayList<String>();
        ValAssetBookParameter002Dto dto = (ValAssetBookParameter002Dto)value;
        // 判断当前流程是否存在：数据主键
        WflValidInputParameter validParam1 = new WflValidInputParameter();
        ProcessMark processMark1 = WorkflowContext.getProcessMarkService().getParameterByAnnotation(dto);
        validParam1.setWorkflowId(dto.getWorkflowId());
        validParam1.setPageId(processMark1.getPageId());

        validParam1.setBusinessDataPk1(dto.getBooksetId());
        validParam1.setBusinessDataPk2(dto.getParamCode());

        if (StringUtil.isNotEmpty(dto.getWorkflowId()) && mstWorkflowCommonService.isInWflProcess(validParam1)) {
            booksetIds.add(dto.getBooksetId());
          validMessageList.add(new ValidMessage(getMessage("COMMON_E_0021",
          StringUtil.join(booksetIds,","),"账套当前参数已在流程中")));
        }
        return validMessageList;
    }

}
