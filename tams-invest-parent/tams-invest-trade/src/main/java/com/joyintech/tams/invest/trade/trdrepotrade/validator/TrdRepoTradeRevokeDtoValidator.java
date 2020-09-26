/*
 * 文件名：TrdRepoTradeRevokeDtoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年12月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.trdrepotrade.validator;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.trade.trdrepotrade.dto.TrdRepoTradeRevokeDto;
import com.joyintech.tams.invest.trade.trdrepotrade.service.TrdRepoTrade001Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 撤销前check
 *
 * @author 裴家俊
 * @version 1.0 2018年11月1日
 * @see TrdRepoTradeRevokeDtoValidator
 * @since 1.0
 */
@Component
public class TrdRepoTradeRevokeDtoValidator implements BeanValidator {

    /**
     * TrdLendInvestTrade001Service
     */
    @Autowired
    private TrdRepoTrade001Service trdRepoTrade001Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        TrdRepoTradeRevokeDto inputDto = (TrdRepoTradeRevokeDto) value;
        List<ValidMessage> errorList = new ArrayList<>();

        // 存在流程中数据
        if (WorkFlowUtil.isNotFromToDoList()) {
            if (trdRepoTrade001Service.isInFlowDataExisted(inputDto.getTradeId())) {
                ValidMessage validMessage = new ValidMessage(
                        getMessage("COMMON_E_0012", inputDto.getTradeId()));
                errorList.add(validMessage);
            }
        }

        // 删除状态不允许撤回
        if (trdRepoTrade001Service.isRevokeDataExisted(inputDto.getTradeId())) {
            ValidMessage validMessage = new ValidMessage(getMessage("TRD_W_0001",
                    getMessage("pingan.common.revoke"), getMessage("pingan.common.revoke")));
            errorList.add(validMessage);
        }

        //由到期续做产生的首期交易产品不给撤销
        if (trdRepoTrade001Service.isDqTrdProductInfo(inputDto.getTradeId())) {
            ValidMessage validMessage = new ValidMessage(
                    getMessage("TrdRepoTrade001valid"));
            errorList.add(validMessage);
        }

        return errorList;
    }
}
