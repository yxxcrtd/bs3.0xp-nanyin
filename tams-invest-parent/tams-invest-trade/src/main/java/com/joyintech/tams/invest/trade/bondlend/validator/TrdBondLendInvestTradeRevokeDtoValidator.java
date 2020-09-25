/*
 * 文件名：TrdBondInvestTradeRevokeDtoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年06月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.bondlend.validator;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.invest.trade.bondlend.dto.TrdBondLendInvestTradeRevokeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.trade.lend.service.TrdLendInvestTrade001Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author 裴家俊
 * @version 1.0 2019年06月25日
 * @see TrdBondLendInvestTradeRevokeDtoValidator
 * @since 1.0
 */
@Component
public class TrdBondLendInvestTradeRevokeDtoValidator implements BeanValidator {

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
        TrdBondLendInvestTradeRevokeDto inputDto = (TrdBondLendInvestTradeRevokeDto) value;
        List<ValidMessage> errorList = new ArrayList<>();

        // 存在存续期交易存在时不能撤销check增加 add by renping 20191017 start 
        if (trdLendInvestTrade001Service.selectDurationDataCount(inputDto.getTradeId())) {
            ValidMessage validMessage = new ValidMessage(
                getMessage("TRD_E_0020", inputDto.getTradeId()));
            errorList.add(validMessage);
        }
        // 存在存续期交易存在时不能撤销check增加 add by renping 20191017 end 

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

        return errorList;
    }
}
