package com.joyintech.tams.invest.period.trdbuyoutduration.validator;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdbuyoutduration.dto.TrdRepoBuyoutDuration002Dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdbondduration.validator
 * @Description: 撤销校验
 * @date Date : 2018年09月27日 13:57
 */
@Component
public class TrdRepoBuyoutDuration002DtoValidator implements BeanValidator {

    @Autowired
    private PeriodCommonService periodCommonService;
    @Autowired
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    @Override
    public List<ValidMessage> valid(Object value) {
        TrdRepoBuyoutDuration002Dto dto = (TrdRepoBuyoutDuration002Dto) value;
        List<ValidMessage> errorList = new ArrayList<>();
        if (!"revoke".equals(dto.getOperateType())) {
            errorList.addAll(bussTrdTransferOrderService.check(dto.getBussTrdTransferOrder()));
        }else {
            periodCommonService.periodCommonRevokeCheck(dto.getStatusValid(), dto.getBusiCode(), errorList);
        }
        
        return errorList;
    }
}
