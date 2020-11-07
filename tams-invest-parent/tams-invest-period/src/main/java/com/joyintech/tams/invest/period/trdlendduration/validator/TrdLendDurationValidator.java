/*
 * 文件名：TrdLendDurationValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dingzhipeng
 * 修改时间：2018年9月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.period.trdlendduration.validator;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.constant.DictCodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.invest.period.common.validator.PeriodCommonValidator;
import com.joyintech.tams.invest.period.trdlendduration.dto.TrdLendDuration001Dto;

/**
 * 拆借校验类
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2018年9月28日
 * @see TrdLendDurationValidator
 * @since 1.0
 */
@Component
public class TrdLendDurationValidator implements PeriodCommonValidator{
    /**
     * 
     */
    @Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> bussValid(Object value) {
        List<ValidMessage> messageList = new ArrayList<>();
        TrdLendDuration001Dto dto = (TrdLendDuration001Dto)value;
        if((StringUtil.equals(dto.getOperateType(),"submit")||StringUtil.equals(dto.getOperateType(),"save"))&&!StringUtil.equals("revoke", dto.getMode())) {
            messageList.addAll(bussTrdTransferOrderService.check(dto.getBussTrdTransferOrder()));
            if (DateUtil.compareDate(dto.getSettleDate(), dto.getMdate())==-1) {
                messageList.add(new ValidMessage("资金到账日需要大于等于到期日"));
            }


            // 校验是否多次提交    1.保存进入待办提交  2.直接提交。
            WflValidInputParameter parameter = new WflValidInputParameter();
            parameter.setPageId("TrdLendDuration002");
            parameter.setWorkflowId(dto.getWorkflowId());
            //parameter.setBusinessDataPk1(dto.getCurveCode());
            parameter.setBusinessDataPk2(dto.getLoanMaturityTransaction());
            parameter.setBusinessDataPk3(dto.getFinprodId());

            if(mstWorkflowCommonService.isInWflProcess(parameter)){
                messageList.add(new ValidMessage("此拆借到期确认交易已在流程中，不可重复提交"));
            }


        }
        //撤销check
        if(StringUtil.equals(dto.getOperateType(), "revoke")){
            
        }
        return messageList;
    }

}
