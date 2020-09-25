/*
 * 文件名：TrdForwexInvestTradeRevokeValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：admin
 * 修改时间：2019年7月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.trade.forwex.validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.trade.forwex.dto.TrdForwexInvestTradeRevokeDto;
import com.joyintech.tams.invest.trade.lend.service.TrdLendInvestTrade001Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 外汇远期投资交易撤销校验
 * 〈功能详细描述〉
 * @author qiaominghui
 * @version 1.0 2019年7月8日
 * @see TrdForwexInvestTradeRevokeValidator
 * @since 1.0
 */
@Component
public class TrdForwexInvestTradeRevokeValidator implements BeanValidator {

    /**
     * TrdLendInvestTrade001Service
     */
    @Autowired
    private TrdLendInvestTrade001Service trdLendInvestTrade001Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
    	TrdForwexInvestTradeRevokeDto inputDto = (TrdForwexInvestTradeRevokeDto) value;
        List<ValidMessage> errorList = new ArrayList<>();

        // 存在流程中数据
        if (WorkFlowUtil.isNotFromToDoList()) {
            if (trdLendInvestTrade001Service.isInFlowDataExisted(inputDto.getTradeId())) {
                ValidMessage validMessage = new ValidMessage(
                    getMessage("COMMON_E_0012", inputDto.getTradeId()));
                errorList.add(validMessage);
            }
        }

        // 删除状态不允许撤回
        if (trdLendInvestTrade001Service.isRevokeDataExisted(inputDto.getTradeId())) {
            ValidMessage validMessage = new ValidMessage(getMessage("TRD_W_0001",
                getMessage("pingan.common.revoke"), getMessage("pingan.common.revoke")));
            errorList.add(validMessage);
        }
        
        // 不允许撤销交易日在平台日之前的交易
        Date sysDate = DateUtil.platDateFromCache();
        if(DateUtil.compareDate(inputDto.getTradeDate(), DateUtil.format(sysDate))<0) {
        	ValidMessage validMessage = new ValidMessage(
                    getMessage("TRD_REVOKE_COMMON_001"));
                errorList.add(validMessage);
        }

        return errorList;
    }
}

