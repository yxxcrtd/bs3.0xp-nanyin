/*
 * 文件名：ValOptInfo001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：tanxuan
 * 修改时间：2020年02月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.basedata.marketdata.valoptinfo.validator;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.basedata.marketdata.valoptinfo.dto.ValOptInfo001Dto;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.workflow.engine.WorkflowContext;

/**
 * 场外期权标的 check
 * @author tanxuan
 * @version 1.0 2020年02月25日
 * @see ValOptInfo001Validator
 * @since 1.0
 */
@Component
public class ValOptInfo001Validator implements BeanValidator{
    
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    private final static Logger logger = LoggerFactory.getLogger(ValOptInfo001Validator.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
    	ValOptInfo001Dto dto = (ValOptInfo001Dto)value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        List<ValOptInfo001Dto> dtoList = new ArrayList<>();
        dtoList.add(dto);
        if(dto.getFinprodId() == null){
        	dtoList = dto.getListValOptInfo001Dto();
        }

        try {
        	for(ValOptInfo001Dto item : dtoList){
        		// 判断当前流程是否存在：数据主键
                WflValidInputParameter validParam1 = new WflValidInputParameter();
                ProcessMark processMark1 = WorkflowContext.getProcessMarkService().getParameterByAnnotation(item);
                validParam1.setWorkflowId(item.getWorkflowId());
                validParam1.setPageId(processMark1.getPageId());

                validParam1.setBusinessDataPk1(item.getFinprodId());
                if (mstWorkflowCommonService.isInWflProcess(validParam1)) {
                    // 流程存在情况
                    errorList.add(new ValidMessage(getMessage("COMMON_E_0021", "标识", item.getFinprodId())));
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
