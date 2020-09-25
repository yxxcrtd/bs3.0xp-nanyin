package com.joyintech.tams.invest.period.trdbondlendtargetduration.validator;

import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.common.validator.PeriodCommonValidator;
import com.joyintech.tams.invest.period.trdbondlendtargetduration.dto.TrdBondLendTargetDuration002ProcessDto;
import com.joyintech.tams.invest.period.trdbondlendtargetduration.service.TrdBondLendTargetDuration002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdbondduration.validator
 * @Description: TODO
 * @date Date : 2018年09月27日 13:57
 */
@Component
public class TrdBondLendTargetDuration002DtoValidator implements PeriodCommonValidator {

    @Autowired
    private PeriodCommonService periodCommonService;

    @Autowired
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    @Autowired
    private TrdBondLendTargetDuration002Service trdBondLendTargetDuration002Service;

    @Override
    public List<ValidMessage> bussValid(Object value) {
        List<ValidMessage> errorList = new ArrayList<>();
        TrdBondLendTargetDuration002ProcessDto dto = (TrdBondLendTargetDuration002ProcessDto)value;

        if (!BussinessConstant.REVOKE.equals(dto.getOperateType())) {
            errorList.addAll(bussTrdTransferOrderService.check(dto.getBussTrdTransferOrder()));
//            if (dto.getTradeDate().compareTo(dto.getMdate()) < 0) {
//                errorList.add(new ValidMessage(getMessage("FIN_ASSET_E_0022")));
//            }
        }
        else {
            periodCommonService.periodCommonRevokeCheck(dto.getStatusValid(), dto.getTotleTradeId(), errorList);
            if (errorList.isEmpty()) {
                Map<String, Object> params = new HashMap<>();
                params.put("portfolioId",  dto.getPortfolioId());
                params.put("bizType", DictCodeListExt1.金融产品类型_投管用_债券);
                params.put("finprodId", dto.getFinprodId());
                params.put("secManageAcctId", dto.getSecManageAcctId());
                params.put("mDate", dto.getMdate());
                params.put("chlId", null);
                if (!periodCommonService.checkIfMaxDate(params)) {
                    errorList.add(new ValidMessage(getMessage("PERIOD_E_0001")));
                }
            }
        }
        return errorList;
    }
}
