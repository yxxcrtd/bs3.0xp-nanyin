/*
 * 文件名：TrdDepoDuration001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dingzhipeng
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.period.trdoverduecalcupenalty.validator;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdoverduecalcupenalty.dto.TrdOverdueCalcuPenaltyDto;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 基金Validator
 * 〈功能详细描述〉
 * @author wangxuelei
 * @version 1.0 2019年10月16日
 * @see TrdOverdueCalcuPenaltyValidator
 * @since 1.0
 */
@Component
public class TrdOverdueCalcuPenaltyValidator implements BeanValidator {
    /**
     * service
     */
    @Autowired
    private PeriodCommonService periodCommonService;
    /**
     * service
     */
    @Autowired(required = false)
    private MstWorkflowCommonService mstWorkflowCommonServie;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        String pk = "";
        TrdOverdueCalcuPenaltyDto dto = (TrdOverdueCalcuPenaltyDto)value;
        if(StringUtil.isNotBlank(dto.getBjBranch())){
            pk = pk+dto.getBjBranch();
        }
        if(StringUtil.isNotBlank(dto.getLiBranch())){
            pk = pk+dto.getLiBranch();
        }
        List<ValidMessage> messageList = new ArrayList<>();
        if(StringUtil.equals("revoke",dto.getMode())){
//            check流程中（防止重复撤销）
            WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
            wflValidInputParameter.setPageId("TrdOverdueCalcuPenalty002");
//            设置原交易编号
            wflValidInputParameter.setBusinessDataPk1(dto.getFinprodId());
            wflValidInputParameter.setBusinessDataPk2(pk);
            if(StringUtil.isNotEmpty(mstWorkflowCommonServie)&&mstWorkflowCommonServie.isInWflProcess(wflValidInputParameter)){
//                存在流程中数据
                messageList.add(new ValidMessage(getMessage("BUSS_COM_E_0023")));
            }
            periodCommonService.periodCommonRevokeCheck(dto.getStatusValid(),dto.getTotleTradeId(),messageList);
        }
        else if(StringUtil.equals("edit",dto.getMode())){
            //check流程中（防止重复保存）
            WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
            wflValidInputParameter.setPageId("TrdOverdueCalcuPenalty002");
            wflValidInputParameter.setBusinessDataPk1(dto.getFinprodId());
            wflValidInputParameter.setBusinessDataPk2(pk);
//            设置前置交易编号
            wflValidInputParameter.setBusinessDataPk3(dto.getTotleTradeId());
            if(StringUtil.isNotEmpty(mstWorkflowCommonServie)&&mstWorkflowCommonServie.isInWflProcess(wflValidInputParameter)){
                messageList.add(new ValidMessage(getMessage("BUSS_COM_E_0023")));
            }
        }
        else if(StringUtil.equals("save",dto.getOperateType())|| StringUtil.equals("submit",dto.getOperateType())){
            if(WorkFlowUtil.isNotFromToDoList()){
                //check流程中（防止重复保存）
                WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
                wflValidInputParameter.setPageId("TrdOverdueCalcuPenalty002");
                wflValidInputParameter.setBusinessDataPk1(dto.getFinprodId());
                wflValidInputParameter.setBusinessDataPk2(pk);
                if(StringUtil.isNotEmpty(mstWorkflowCommonServie)&&mstWorkflowCommonServie.isInWflProcess(wflValidInputParameter)){
                    messageList.add(new ValidMessage(getMessage("BUSS_COM_E_0023")));
                }
            }
        }
        return messageList;
    }
}
