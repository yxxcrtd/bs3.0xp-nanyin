package com.joyintech.tams.invest.trade.bondtostock.validator;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.trade.bondtostock.dto.TrdBondToStock001Dto;
import com.joyintech.tams.invest.trade.common.validator.TradeCommonValidator;
//import com.joyintech.tams.invest.trade.convertiblebonds.dto.TrdBondToStock001Dto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TrdBondToStock001Validator implements TradeCommonValidator {

    @Override
    public List<ValidMessage> bussValid(Object value) {
        List<ValidMessage> retList = new ArrayList<>();
        TrdBondToStock001Dto dto = (TrdBondToStock001Dto)value;
        if(!(StringUtil.equals(dto.getOperateType(),"delete")||StringUtil.equals(dto.getOperateType(),"revoke"))){
            //        4.	转股起始日大于交易日期时，交易禁止提交；
            if(DateUtil.compareDate(dto.getConvdate(),dto.getTradeDate())>0){
                retList.add(new ValidMessage("tradeDate",getMessage("BUSS_COM_E_0003",getMessage("TrdBondToStock001.vdate"),
                        getMessage("TrdBondToStock001.tradeDate"))));
            }
            if(StringUtil.isEmpty(dto.getEntrustId())&&StringUtil.isNotEmpty(dto.getConetrPriceTotal())&&StringUtil.isNotEmpty(dto.getShareAmt())&&dto.getConetrPriceTotal().compareTo(dto.getShareAmt())<0){
                retList.add(new ValidMessage("stocketrPriceTotal",getMessage("BUSS_COM_E_0003",getMessage("TrdBondToStock001.shareAmt"),
                        getMessage("TrdBondToStock001.conetrPriceTotal"))));
            }
        }
        return retList;
    }
}
