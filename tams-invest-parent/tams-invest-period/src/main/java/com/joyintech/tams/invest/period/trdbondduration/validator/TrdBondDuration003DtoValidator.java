package com.joyintech.tams.invest.period.trdbondduration.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.invest.period.trdbondduration.dto.TrdBondDuration003ProcessDto;
import com.joyintech.tams.invest.period.trdbondduration.service.TrdBondDuration003Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.common.validator.PeriodCommonValidator;
import com.joyintech.tams.invest.period.trdbondduration.dto.TrdBondDuration002ProcessDto;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdbondduration.validator
 * @Description: TODO
 * @date Date : 2018年09月27日 13:57
 */
@Component
public class TrdBondDuration003DtoValidator implements PeriodCommonValidator {

    @Autowired
    private PeriodCommonService periodCommonService;

    @Autowired
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    @Autowired
    private TrdBondDuration003Service trdBondDuration003Service;

    @Override
    public List<ValidMessage> bussValid(Object value) {
        List<ValidMessage> errorList = new ArrayList<>();
        TrdBondDuration003ProcessDto dto = (TrdBondDuration003ProcessDto)value;
        if (dto.getSettleDate()!= null && dto.getPayDate()!= null && dto.getSettleDate().compareTo(dto.getPayDate()) <0) {
        	errorList.add(
                    new ValidMessage("settleDate", "资金到账日不能小于支付日！"));
        }
        errorList.addAll(bussTrdTransferOrderService.check(dto.getBussTrdTransferOrder()));
        if (StringUtil.equals(dto.getOperateType(), "submit")) {
            if (StringUtil.isNotEmpty(dto.getFinprodMarketId()) && StringUtil.isNotEmpty(dto.getChlId())
                && StringUtil.isNotEmpty(dto.getSettleDate())){
                Map<String,Object> map = new HashMap<>();
                map.put("finprodMarketId",dto.getFinprodMarketId());
                map.put("chlId",dto.getChlId());
                map.put("tradeDate",DateUtil.format(dto.getSettleDate(),DateUtil.DEFAULT_DATE_FORMAT_EN));
               List<String> dtos = trdBondDuration003Service.findProcessDtos(map);
               if (dtos.size() > 0){
                   errorList.add(
                           new ValidMessage("chlId", "通道已经对资产做了行权赎回！"));
               }
            }
        }
        return errorList;
    }
}
