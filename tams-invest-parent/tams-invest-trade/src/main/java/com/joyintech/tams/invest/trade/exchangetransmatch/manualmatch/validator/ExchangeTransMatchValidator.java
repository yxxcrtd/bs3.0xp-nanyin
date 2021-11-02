package com.joyintech.tams.invest.trade.exchangetransmatch.manualmatch.validator;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.trade.exchangetransmatch.manualmatch.dto.ExchangeTransMatch002Dto;
import com.joyintech.tams.invest.trade.exchangetransmatch.manualmatch.dto.ExchangeTransMatch003Dto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExchangeTransMatchValidator implements BeanValidator {
    @Override
    public List<ValidMessage> valid(Object entity) {
        ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
        ExchangeTransMatch002Dto dealDto = (ExchangeTransMatch002Dto) entity;
        if(StringUtil.equals(dealDto.getElementId(),"transaction_macth")) {
            ExchangeTransMatch003Dto entrustDto = dealDto.getEntrustList().get(0);
            //比较左右的资产类型
            if(!StringUtil.equals(dealDto.getSecType(),entrustDto.getSecType())) {
                errorList.add(new ValidMessage("组成交数据的资产类型和委托或交易的资产类型不一致!"));
            }

            //比较基本信息
            if(!StringUtil.equals(dealDto.getSecAcctId(),entrustDto.getSecAcctId())
                    || !StringUtil.equals(dealDto.getAcctNo(),entrustDto.getAcctNo())
                    || !StringUtil.equals(dealDto.getFinprodId(),entrustDto.getFinprodId())
                    || !StringUtil.equals(dealDto.getPs(),entrustDto.getPs())
                    || !StringUtil.equals(dealDto.getTradeDate(),entrustDto.getEntrustDate())
                    || !StringUtil.equals(dealDto.getTradePlat(),entrustDto.getTradePlat())) {
                errorList.add(new ValidMessage("成交数据的基本信息和委托或交易的基本信息不一致"));
            }
        }
        else if (StringUtil.equals(dealDto.getElementId(),"transaction_record")) {
            if(!StringUtil.equals(dealDto.getMatchStatus(), DictCodeListExt3.下行交易匹配状态_未匹配.getValue())
            && !StringUtil.equals(dealDto.getMatchStatus(), DictCodeListExt3.下行交易匹配状态_部分匹配.getValue())) {
                errorList.add(new ValidMessage("请选择未匹配或者部分匹配的数据"));
            }


        }

        return errorList;
    }
}
