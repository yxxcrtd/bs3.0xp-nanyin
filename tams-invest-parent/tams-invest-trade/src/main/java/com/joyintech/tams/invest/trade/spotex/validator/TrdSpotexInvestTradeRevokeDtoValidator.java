/*
 * 文件名：TrdSpotexInvestTradeRevokeDtoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangguoxian
 * 修改时间：2019年7月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.trade.spotex.validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.joyintech.tams.invest.trade.spotex.dto.TrdSpotexInvestTradeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.trade.spotex.dto.TrdSpotexInvestTradeRevokeDto;
import com.joyintech.tams.invest.trade.spotex.service.TrdSpotexInvestTrade001Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;


/**
 * 外汇即期交易撤销DtoValidator
 * @author wangguoxian
 * @version 1.0 2019年7月11日
 * @see TrdSpotexInvestTradeRevokeDtoValidator
 * @since 1.0
 */
@Component
public class TrdSpotexInvestTradeRevokeDtoValidator implements BeanValidator {

    /**
     * TrdSpotexInvestTrade001Service
     */
    @Autowired
    private TrdSpotexInvestTrade001Service trdSpotexInvestTrade001Service;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        TrdSpotexInvestTradeRevokeDto inputDto = (TrdSpotexInvestTradeRevokeDto)value;
        TrdSpotexInvestTradeDto selDto = inputDto.getSelectedItem().get(0);
        List<ValidMessage> errorList = new ArrayList<>();
        // 存在流程中数据
        if (WorkFlowUtil.isNotFromToDoList()) {
            if (trdSpotexInvestTrade001Service.isInFlowDataExisted(selDto.getTradeId())) {
                ValidMessage validMessage = new ValidMessage(
                    getMessage("COMMON_E_0012", selDto.getTradeId()));
                errorList.add(validMessage);
            }
        }

        // 删除状态不允许撤回
        if (trdSpotexInvestTrade001Service.isRevokeDataExisted(selDto.getTradeId())) {
            ValidMessage validMessage = new ValidMessage(getMessage("TRD_W_0001",
                getMessage("pingan.common.revoke"), getMessage("pingan.common.revoke")));
            errorList.add(validMessage);
        }
        
        // 不允许撤销交易日在平台日之前的交易
        Date sysDate = DateUtil.platDateFromCache();
        if(DateUtil.compareDate(selDto.getTradeDate(), sysDate)<0) {
        	ValidMessage validMessage = new ValidMessage(
                    getMessage("TRD_REVOKE_COMMON_001"));
                errorList.add(validMessage);
        }

        return errorList;
    }

}
