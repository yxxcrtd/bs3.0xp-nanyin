package com.joyintech.tams.invest.period.trdirsduration.validator;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.common.validator.PeriodCommonValidator;
import com.joyintech.tams.invest.period.trdirsduration.dto.TrdIRSDuration002ProcessDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 利率互换存续期
 * 
 * @author : renping
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdirsduration.validator
 * @date Date : 2019年08月10日
 */
@Component
public class TrdIRSDuration002DtoValidator implements PeriodCommonValidator {

    @Autowired
    private PeriodCommonService periodCommonService;

    @Autowired
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    @Override
    public List<ValidMessage> bussValid(Object value) {
        List<ValidMessage> errorList = new ArrayList<>();
        TrdIRSDuration002ProcessDto dto = (TrdIRSDuration002ProcessDto)value;
        String mdate;
        if (dto.getFixMdate() != null) {
            mdate = DateUtil.format(dto.getFixMdate());
        }
        else {
            mdate = DateUtil.format(dto.getFloatMdate());
        }
        if (!BussinessConstant.REVOKE.equals(dto.getOperateType())) {
            if (dto.getFixMdate() != null) {
                if (dto.getFixIntAmt() == null) {
                    errorList.add(new ValidMessage("fixIntAmt",
                        getMessage("BUSS_COM_E_0038", getMessage("TrdIRSDuration002.fixIntAmt"))));
                }
                if (dto.getFixActPayDate() == null) {
                    errorList.add(new ValidMessage("fixActPayDate",
                        getMessage("BUSS_COM_E_0038", getMessage("TrdIRSDuration002.fixActPayDate"))));
                }
            }
            else {
                if (dto.getFloatIntAmt() == null) {
                    errorList.add(new ValidMessage("floatIntAm",
                        getMessage("BUSS_COM_E_0038", getMessage("TrdIRSDuration002.floatIntAm"))));
                }
                if (dto.getFloatActPayDate() == null) {
                    errorList.add(new ValidMessage("floatActPayDate",
                        getMessage("BUSS_COM_E_0038", getMessage("TrdIRSDuration002.floatActPayDate"))));
                }
            }
            errorList.addAll(bussTrdTransferOrderService.check(dto.getBussTrdTransferOrder()));
            if (dto.getFixActPayDate() != null
                && dto.getFixPayDate().compareTo(dto.getFixActPayDate()) > 0) {
                errorList.add(new ValidMessage("fixActPayDate",
                    getMessage("BUSS_COM_E_0004", getMessage("TrdIRSDuration002.fixActPayDate"),
                        getMessage("TrdIRSDuration002.fixPayDate"))));
            }
            if (dto.getFloatActPayDate() != null
                && dto.getFloatPayDate().compareTo(dto.getFloatActPayDate()) > 0) {
                errorList.add(new ValidMessage("floatActPayDate",
                    getMessage("BUSS_COM_E_0004", getMessage("TrdIRSDuration002.floatActPayDate"),
                        getMessage("TrdIRSDuration002.floatPayDate"))));
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
        }
        else {
            periodCommonService.periodCommonRevokeCheck(dto.getStatusValid(), dto.getBusiCode(),
                errorList);
            if (errorList.isEmpty()) {
                Map<String, Object> params = new HashMap<>();
                params.put("portfolioId",  dto.getPortfolioId());
                params.put("bizType", DictCodeListExt1.金融产品类型_投管用_利率互换);
                params.put("finprodId", dto.getFinprodId());
                params.put("secManageAcctId", null);
                params.put("mDate", DateUtil.format(dto.getMdate()));
                params.put("chlId", null);
                if (!periodCommonService.checkIfMaxDate(params)) {
                    errorList.add(new ValidMessage(getMessage("PERIOD_E_0001")));
                }
            }
        }
        return errorList;
    }
}
