package com.joyintech.tams.invest.period.trdirsduration.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.common.validator.PeriodCommonValidator;
import com.joyintech.tams.invest.period.trdirsduration.dto.TrdIRSDuration003ProcessDto;

/**
 * 利率互换提前终止
 * 
 * @author : renping
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdirsduration.validator
 * @date Date : 2019年08月10日
 */
@Component
public class TrdIRSDuration003DtoValidator implements PeriodCommonValidator {

    @Autowired
    private PeriodCommonService periodCommonService;

    @Autowired
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    @Override
    public List<ValidMessage> bussValid(Object value) {
        List<ValidMessage> errorList = new ArrayList<>();
        TrdIRSDuration003ProcessDto dto = (TrdIRSDuration003ProcessDto)value;
        String mdate = DateUtil.format(dto.getBeforeEndDate());
        errorList.addAll(bussTrdTransferOrderService.check(dto.getBussTrdTransferOrder()));
        if (dto.getBeforeEndDate() != null
            && dto.getBeforeEndDate().compareTo(dto.getMdate()) > 0) {
            errorList.add(new ValidMessage("beforeEndDate",
                getMessage("BUSS_COM_E_0003", getMessage("TrdIRSDuration003.beforeEndDate"),
                    getMessage("TrdIRSDuration003.mdate"))));
        }
        if (dto.getBeforeEndPrin() != null
            && dto.getBeforeEndPrin().compareTo(dto.getRestPrin()) > 0) {
            errorList.add(new ValidMessage("beforeEndPrin",
                getMessage("BUSS_COM_E_0003", getMessage("TrdIRSDuration003.beforeEndPrin"),
                    getMessage("TrdIRSDuration003.restPrin"))));
        }
        // 校验其前置的现金流信息所对应的交易 是否在流程中
        Map<String, Object> map = new HashMap<>();
        map.put("finprodId", dto.getFinprodId());
        map.put("mdate", mdate);
        List<String> tradeList = periodCommonService.getFlowDeal(map);
        if (tradeList != null && tradeList.size() > 0) {
            errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0027",
                GlobalConstant.SPLIT + getMessage("TrdLendDuration001.busiCode"),
                StringUtil.joinString(tradeList, GlobalConstant.COMMA))));
        }
        return errorList;
    }
}
