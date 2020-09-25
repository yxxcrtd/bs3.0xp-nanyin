/*
 * 文件名：TrdDepoDuration001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dingzhipeng
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.period.trdfundobversion.validator;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.invest.period.trdfundobversion.dto.TrdFundObversionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdfundduration.service.TrdFundDuration001Service;

/**
 * 基金Validator
 * 〈功能详细描述〉
 * @author wangxuelei
 * @version 1.0 2019年10月16日
 * @see TrdFundObversion001Validator
 * @since 1.0
 */
@Component
public class TrdFundObversion001Validator implements BeanValidator{
    /**
     * service
     */
    @Autowired
    private PeriodCommonService periodCommonService;
    /**
     * service
     */
    @Autowired
    private TrdFundDuration001Service trdFundDuration001Service;
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
        TrdFundObversionDto dto = (TrdFundObversionDto)value;
        List<ValidMessage> messageList = new ArrayList<>();
        if(StringUtil.equals("revoke",dto.getMode())){
//            check流程中（防止重复撤销）
            WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
            wflValidInputParameter.setPageId("TrdFundObversion002");
//            设置原交易编号
            wflValidInputParameter.setBusinessDataPk4(dto.getTotleTradeId());
            if(StringUtil.isNotEmpty(mstWorkflowCommonServie)&&mstWorkflowCommonServie.isInWflProcess(wflValidInputParameter)){
//                存在流程中数据
                messageList.add(new ValidMessage(getMessage("BUSS_COM_E_0023")));
            }
            periodCommonService.periodCommonRevokeCheck(dto.getStatusValid(),dto.getTotleTradeId(),messageList);
        }
        else if(StringUtil.equals("edit",dto.getMode())){
            //check流程中（防止重复保存）
            WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
            wflValidInputParameter.setPageId("TrdFundObversion002");
//            设置前置交易编号
            wflValidInputParameter.setBusinessDataPk3(dto.getTotleTradeId());
            if(StringUtil.isNotEmpty(mstWorkflowCommonServie)&&mstWorkflowCommonServie.isInWflProcess(wflValidInputParameter)){
                messageList.add(new ValidMessage(getMessage("BUSS_COM_E_0023")));
            }
        }
        else if(StringUtil.equals("save",dto.getOperateType())||StringUtil.equals("submit",dto.getOperateType())){
        }
        return messageList;
    }
}
