package com.joyintech.tams.invest.period.trdrepoduration.validator;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdrepoduration.dto.TrdRepoDuration002Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdbondduration.validator
 * @Description: 保存校验
 * @date Date : 2018年09月27日 13:57
 */
@Component
public class TrdRepoDuration002DtoValidator implements BeanValidator {

    @Autowired
    private PeriodCommonService periodCommonService;
    @Autowired
    private BussTrdTransferOrderService bussTrdTransferOrderService;
    /**
     * 平台日期
     */
    @Autowired
    private IPlatformDate platformDateImpl;

    @Override
    public List<ValidMessage> valid(Object value) {
        TrdRepoDuration002Dto dto = (TrdRepoDuration002Dto)value;
        List<ValidMessage> errorList = new ArrayList<>();
        if (!"revoke".equals(dto.getOperateType())) {
            errorList.addAll(bussTrdTransferOrderService.check(dto.getBussTrdTransferOrder()));
//            提前终止时 提前支取利率不能为空
            if(StringUtil.equals(dto.getPs(), DictCodeListExt3.交易方向_投资端_提前终止.getValue())&&dto.getTerminateRate()==null){
                errorList.add(new ValidMessage("terminateRate",getMessage("FW_E_0006",getMessage("TrdRepoDuration002.terminateRate"))));
            }
            if(StringUtil.equals(dto.getPs(), DictCodeListExt3.交易方向_投资端_逾期返售.getValue())&&dto.getPenaltyIntamt()==null){
                errorList.add(new ValidMessage("penaltyIntamt",getMessage("FW_E_0006",getMessage("TrdRepoDuration002.penaltyIntamt"))));
            }
            if("T56".equals(dto.getPstype())){//新建时对续做页面做校验
                errorList.addAll(bussValid(dto));
            }
        }

        return errorList;
    }

    /**
     * 续做到期界面校验
     */
    public List<ValidMessage> bussValid(Object value) {
        TrdRepoDuration002Dto inputDto = (TrdRepoDuration002Dto)value;
        List<ValidMessage> errorList = new ArrayList<>();

        // 续做期限必填
            if (inputDto.getTermex()== null || inputDto.getTermex().equals("")) {
                ValidMessage validMessage = new ValidMessage("termex",
                        getMessage("FW_E_0006", getMessage("TrdRepoDuration002Dto.termex")));
                errorList.add(validMessage);
            }
        // 续做期限品种必填
        if(!inputDto.getTradeMarket().equals(DictCodeListExt1.交易场所_深圳证券交易所.getValue())) {
            if (inputDto.getTermTypeReback() == null || inputDto.getTermTypeReback().equals("")) {
                ValidMessage validMessage = new ValidMessage("termTypeReback",
                        getMessage("FW_E_0006", getMessage("TrdRepoDuration002Dto.termTypeReback"),
                                getMessage("TrdRepoDuration002Dto.termTypeReback")));
                errorList.add(validMessage);
            }
        }
        // 续做到期日必填
        if (inputDto.getmDatex() == null) {
                ValidMessage validMessage = new ValidMessage("mDatex",
                        getMessage("FW_E_0006", getMessage("TrdRepoDuration002Dto.mDatex"),
                                getMessage("TrdRepoDuration002Dto.mDatex")));
                errorList.add(validMessage);
        }
        // 续做本金必填
        if (inputDto.getTheoryPrinex() == null) {
            ValidMessage validMessage = new ValidMessage("theoryPrinex",
                    getMessage("FW_E_0006", getMessage("TrdRepoDuration002Dto.theoryPrinex"),
                            getMessage("TrdRepoDuration002Dto.theoryPrinex")));
            errorList.add(validMessage);
        }
        // 续做利率必填
        if (inputDto.getIntRatex() == null) {
            ValidMessage validMessage = new ValidMessage("intRatex",
                    getMessage("FW_E_0006", getMessage("TrdRepoDuration002Dto.intRatex"),
                            getMessage("TrdRepoDuration002Dto.intRatex")));
            errorList.add(validMessage);
        }
        // 续做利息必填
        if (inputDto.getTheoryIntex() == null) {
            ValidMessage validMessage = new ValidMessage("theoryIntex",
                    getMessage("FW_E_0006", getMessage("TrdRepoDuration002Dto.theoryIntex"),
                            getMessage("TrdRepoDuration002Dto.theoryIntex")));
            errorList.add(validMessage);
        }
        // 续做到期结算金额必填
        if (inputDto.getTradeAmtex() == null) {
            ValidMessage validMessage = new ValidMessage("tradeAmtex",
                    getMessage("FW_E_0006", getMessage("TrdRepoDuration002Dto.tradeAmtex"),
                            getMessage("TrdRepoDuration002Dto.tradeAmtex")));
            errorList.add(validMessage);
        }
        // 结算金额必填
        if (inputDto.getSetAmt() == null) {
            ValidMessage validMessage = new ValidMessage("setAmt",
                    getMessage("FW_E_0006", getMessage("TrdRepoDuration002Dto.setAmt"),
                            getMessage("TrdRepoDuration002Dto.setAmt")));
            errorList.add(validMessage);
        }
        // 续做对手必填
        if (inputDto.getCounterIdex() == null || inputDto.getCounterIdex().equals("")) {
            ValidMessage validMessage = new ValidMessage("counterIdex",
                    getMessage("FW_E_0006", getMessage("TrdRepoDuration002Dto.counterIdex"),
                            getMessage("TrdRepoDuration002Dto.counterIdex")));
            errorList.add(validMessage);
        }
        // 续做交易员必填
        if (inputDto.getTraderx() == null) {
            ValidMessage validMessage = new ValidMessage("traderx",
                    getMessage("FW_E_0006", getMessage("TrdRepoDuration002Dto.traderx"),
                            getMessage("TrdRepoDuration002Dto.traderx")));
            errorList.add(validMessage);
        }
        return errorList;
    }
}
