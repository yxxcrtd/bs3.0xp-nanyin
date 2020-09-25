package com.joyintech.tams.invest.settle.balance.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.settle.balance.dto.OutflowFundsSystemFlowDto;
import com.joyintech.tams.invest.settle.balance.dto.OutflowManualMatchDto;
import com.joyintech.tams.invest.settle.balance.service.OutflowFundsCheckManagement001Service;

/**
 * @program: tams-invest-parent
 * @description:
 * @author: 强成西
 * @create: 2018-12-14 16:03
 **/
@Component
public class OutflowManualMatchDtoValidator implements BeanValidator {

    @Autowired
    private OutflowFundsCheckManagement001Service outflowFundsCheckManagement001Service;

    @Override
    public List<ValidMessage> valid(Object value) {
        List<ValidMessage> errorList = new ArrayList<>();
        OutflowManualMatchDto dto = (OutflowManualMatchDto)value;
        List<OutflowFundsSystemFlowDto> sysFlow = dto.getSysFlow();
        List<OutflowFundsSystemFlowDto> enFlow = dto.getEnFlow();
        List<String> seqNoList = new ArrayList<>();
        BigDecimal sysAmt = BigDecimal.ZERO;
        BigDecimal enAmt = BigDecimal.ZERO;
        List<String> bizCodeList = new ArrayList<>();
        for (OutflowFundsSystemFlowDto outflowFundsSystemFlowDto : sysFlow) {
            sysAmt = ArithmeticUtil.doAdd(sysAmt, outflowFundsSystemFlowDto.getSettleAmt());
            seqNoList.add(outflowFundsSystemFlowDto.getSysSeqNo());
            bizCodeList.add(outflowFundsSystemFlowDto.getBizCode());
            if (!DictCodeListExt1.画面数据状态_有效.getValue().equals(outflowFundsSystemFlowDto.getStatusValid())) {
                errorList.add(new ValidMessage("请选择有效数据进行匹配"));
                return errorList;
            }
        }

        Long isInflow = outflowFundsCheckManagement001Service.selectSysFlowOnWorkflow(bizCodeList);
        if (isInflow > 0) {
            errorList.add(new ValidMessage("交易在撤销流程中，不能匹配"));
            return errorList;
        }

        Long num1 = outflowFundsCheckManagement001Service.selectSysFlowCountByTradeId(seqNoList);
        seqNoList.clear();
        for (OutflowFundsSystemFlowDto outflowFundsSystemFlowDto : enFlow) {
            enAmt = ArithmeticUtil.doAdd(enAmt, outflowFundsSystemFlowDto.getSettleAmt());
            seqNoList.add(outflowFundsSystemFlowDto.getEnSeqNo());
        }
        Long num2 = outflowFundsCheckManagement001Service.selectEnFlowCountByTradeId(seqNoList);
        // 取消匹配时
        if (dto.isCancel()) {
            if (num1 == 0) {
                errorList.add(new ValidMessage(getMessage("settle.balance.outflow.error3")));
                return errorList;
            }

            if (num2 == 0) {
                errorList.add(new ValidMessage(getMessage("settle.balance.outflow.error4")));
                return errorList;
            }
            return errorList;
        }

        // 匹配时
        if (sysFlow == null || sysFlow.isEmpty() || enFlow == null || enFlow.isEmpty()) {
            errorList.add(new ValidMessage("请至少选择一条数据"));
            return errorList;
        }
        String settleDate = sysFlow.get(0).getSettleDate();
        for (OutflowFundsSystemFlowDto outflowFundsSystemFlowDto : sysFlow) {
            if (settleDate == null || !settleDate.equals(outflowFundsSystemFlowDto.getSettleDate())) {
                errorList.add(new ValidMessage(getMessage("settle.balance.outflow.error2")));
                return errorList;
            }
        }
        for (OutflowFundsSystemFlowDto outflowFundsSystemFlowDto : enFlow) {
            if (settleDate == null || !settleDate.equals(outflowFundsSystemFlowDto.getSettleDate())) {
                errorList.add(new ValidMessage(getMessage("settle.balance.outflow.error2")));
                return errorList;
            }
        }

        if (num1 > 0) {
            errorList.add(new ValidMessage(getMessage("settle.balance.outflow.error3")));
            return errorList;
        }

        if (num2 > 0) {
            errorList.add(new ValidMessage(getMessage("settle.balance.outflow.error4")));
            return errorList;
        }

        if (sysAmt.compareTo(enAmt) != 0) {
            errorList.add(new ValidMessage(getMessage("settle.balance.outflow.error1")));
        }

        return errorList;
    }
}
