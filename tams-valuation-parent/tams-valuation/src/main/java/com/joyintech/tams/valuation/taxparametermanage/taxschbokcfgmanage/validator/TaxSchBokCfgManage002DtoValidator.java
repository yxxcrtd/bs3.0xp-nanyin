/*
 * 文件名：TaxSchBokCfgManage002DtoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年12月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.taxparametermanage.taxschbokcfgmanage.validator;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.workflow.engine.constant.OperationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.tams.valuation.taxparametermanage.taxschbokcfgmanage.dto.TaxSchBokCfgManage002Dto;
import com.joyintech.workflow.engine.WorkflowContext;

/**
 * 参数设置 check
 * @author zhangwei
 * @version 1.0 2018年12月3日
 * @see TaxSchBokCfgManage002DtoValidator
 * @since 1.0
 */
@Component("taxSchBokCfgManage002DtoValidator")
public class TaxSchBokCfgManage002DtoValidator implements BeanValidator{

    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    private final static Logger logger = LoggerFactory.getLogger(TaxSchBokCfgManage002DtoValidator.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        TaxSchBokCfgManage002Dto dto = (TaxSchBokCfgManage002Dto)value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();

        try {
            // 判断当前流程是否存在：数据主键
            WflValidInputParameter validParam1 = new WflValidInputParameter();
            ProcessMark processMark1 = WorkflowContext.getProcessMarkService().getParameterByAnnotation(dto);
            validParam1.setWorkflowId(dto.getWorkflowId());
            validParam1.setPageId(processMark1.getPageId());

            validParam1.setBusinessDataPk1(dto.getConfigId());
            if (mstWorkflowCommonService.isInWflProcess(validParam1)) {
                // 流程存在情况
                errorList.add(new ValidMessage(getMessage("COMMON_E_0021", "标识", dto.getConfigId())));
                return errorList;
            }

            if ("delete".equals(dto.getOperateType())) {
                // 判断当前流程是否存在：业务主键 (防止在税务方案中关联，进入流程中待审批， 此时进行了删除操作)
                WflValidInputParameter validParam2 = new WflValidInputParameter();
                validParam2.setWorkflowId(dto.getWorkflowId());
                validParam2.setPageId("TaxScheme007");

                validParam2.setBusinessDataPk3(dto.getConfigId());
                if (mstWorkflowCommonService.isInWflProcess(validParam2)) {
                    // 流程存在情况
                    errorList.add(new ValidMessage(getMessage("COMMON_E_0021", "标识", dto.getConfigId())));
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
