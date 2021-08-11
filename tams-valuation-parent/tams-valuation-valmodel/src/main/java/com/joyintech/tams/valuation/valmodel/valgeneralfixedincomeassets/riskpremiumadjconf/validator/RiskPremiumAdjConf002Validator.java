/*
 * 文件名：RiskPremiumAdjConf002Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xutong
 * 修改时间：2021-06-28
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.valmodel.valgeneralfixedincomeassets.riskpremiumadjconf.validator;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.valmodel.valgeneralfixedincomeassets.riskpremiumadjconf.dto.RiskPremiumAdjConf002Dto;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 资产减值管理
 * @author xutong
 * @createTime 2021-07-01
 * @see RiskPremiumAdjConf002Validator
 * @since 1.0
 */
@Component
public class RiskPremiumAdjConf002Validator implements BeanValidator{

    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * 校验
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        RiskPremiumAdjConf002Dto dto = (RiskPremiumAdjConf002Dto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if (WorkFlowUtil.isNotFromToDoList()) {
            // 判断当前流程是否存在：数据主键
            WflValidInputParameter validParam1 = new WflValidInputParameter();
            ProcessMark processMark1 = WorkflowContext.getProcessMarkService().getParameterByAnnotation(dto);
            validParam1.setWorkflowId(dto.getWorkflowId());
            validParam1.setPageId(processMark1.getPageId());

            validParam1.setBusinessDataPk1(dto.getSeqno());
            if (mstWorkflowCommonService.isInWflProcess(validParam1)) {
                // 流程存在情况
                resultList.add(new ValidMessage(getMessage("COMMON_E_0021", "标识", dto.getFinprodId() + ":" + dto.getFinprodName())));
                return resultList;
            }

        }
        return resultList;
    }

}