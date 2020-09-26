package com.joyintech.tams.invest.trade.project.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.trade.project.dto.TrdProjectInvestTrade001Dto;
import com.joyintech.tams.invest.trade.project.dto.TrdProjectInvestTrade002Dto;
import com.joyintech.tams.invest.trade.project.service.TrdProjectInvestTrade002Service;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: tams-invest-parent
 * @Description: 撤销校验
 * @date Date : 2018年09月27日 13:57
 */
@Component
public class TrdProjectInvestTradeDeleteDtoValidator implements BeanValidator {

    @Autowired
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    @Autowired
    private TrdProjectInvestTrade002Service trdProjectInvestTrade002Service;

    @Override
    public List<ValidMessage> valid(Object value) {
        TrdProjectInvestTrade001Dto dto = (TrdProjectInvestTrade001Dto)value;
        List<ValidMessage> errorList = new ArrayList<>();
        String operateType = dto.getOperateType();
        if (!DictCodeListExt1.交易流程状态_有效.getValue().equals(dto.getStatusValid())) {
            errorList.add(new ValidMessage(getMessage("FIN_ASSET_E_0006")));
        }
        int i = trdProjectInvestTrade002Service.findRevokeDeal(dto.getBizCode());
        if (i >= 1) {
            errorList.add(new ValidMessage(getMessage("TRD_X_0005")));
        }
//        Map<String, Object> map = new HashMap<>();
//        map.put("tradeId", dto.getBizCode());
//        map.put("ps", DictCodeList.交易方向_卖出到账.getValue());
//        map.put("yes_no", DictCodeList.是否_否.getValue());
//        map.put("trade_status", DictCodeListExt1.交易状态_初始.getValue());
//        if (trdProjectInvestTrade002Service.countFollowDeal(map) > 0) {
//            errorList.add(new ValidMessage(getMessage("FIN_ASSET_E_0031")));
//        }

        return errorList;
    }
}
