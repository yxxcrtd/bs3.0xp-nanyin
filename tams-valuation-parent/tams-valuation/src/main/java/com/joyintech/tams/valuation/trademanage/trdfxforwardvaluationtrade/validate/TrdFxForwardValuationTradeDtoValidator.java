/*
 * 文件名：TrdFxSpotValuationTradeDtoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年11月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.trademanage.trdfxforwardvaluationtrade.validate;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.trademanage.trdfxforwardvaluationtrade.dto.TrdFxForwardValuationTradeDto;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:外汇远期交易
 * TrdFxForwardValuationTradeDtoValidator
 * @auther: yuzj
 * @date: 2022-1-18
 * @param:
 * @return:
 */
@Transactional
@Component
public class TrdFxForwardValuationTradeDtoValidator implements BeanValidator {

    public List<ValidMessage> valid(Object value) {
        List<ValidMessage> validMessageList = new ArrayList<ValidMessage>();
        TrdFxForwardValuationTradeDto dto = (TrdFxForwardValuationTradeDto)value;

        return validMessageList;
    }

}

