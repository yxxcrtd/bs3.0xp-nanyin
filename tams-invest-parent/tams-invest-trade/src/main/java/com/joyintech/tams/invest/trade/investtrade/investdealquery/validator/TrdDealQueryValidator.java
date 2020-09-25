/*
 * 文件名：ListExport001Validator.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：qiangchengxi
 * 修改时间：2019年5月6日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.invest.trade.investtrade.investdealquery.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.trade.investtrade.investdealquery.dto.TrdDealQueryDto;
import com.joyintech.tams.invest.trade.investtrade.investdealquery.service.TrdInvestDealQuery001Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant; 

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangyang1 
 * @version 1.0 2019年9月19日
 * @see TrdDealQueryValidator
 * @since 1.0
 */
@Component
public class TrdDealQueryValidator implements BeanValidator {
     
    @Autowired
    private TrdInvestDealQuery001Service service;
    
    @SuppressWarnings("unchecked")
    @Override
    public List<ValidMessage> valid(Object value) {
        TrdDealQueryDto entity = (TrdDealQueryDto)value;
        List<ValidMessage> validList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(entity.getOperateType(), "singleExport") 
            || StringUtil.equals(entity.getOperateType(), "batchExport")){  
            if (DictCodeList.是否_是.getValue().equals(CacheUtil.getSysParam(BussinessConstant.IS_AUTO_GENERATE_NEGATE_DEAL))) { 
                if(StringUtil.isNotEmpty(entity.getSelectedItem())){
                    List<String> teadIds = new ArrayList<>();
                    for (TrdDealQueryDto trdDealQueryDto : entity.getSelectedItem()) {
                        teadIds.add(trdDealQueryDto.getTradeId());
                    }
                    int sumcheck = service.selectCheck(teadIds);
                    if (sumcheck != entity.getSelectedItem().size()) {
                        validList.add(new ValidMessage("选取数据中存在不是转仓交易的数据!"));
                    }
                }  
            }
        }
        return validList; 
    }
}
