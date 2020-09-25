/*
 * 文件名：TrdRepoTradeRevokeDtoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：修改人：王伟
 * 修改时间：2019年12月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.pretrdrepotrade.validator;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.trade.pretrdrepotrade.dto.PreTrdRepoTradeRevokeDto;
import com.joyintech.tams.invest.trade.trdrepotrade.dto.TrdRepoTradeRevokeDto;
import com.joyintech.tams.invest.trade.trdrepotrade.service.TrdRepoTrade001Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 质押式回购交易预录入撤销check
 * @author 王伟
 * @version 1.0 2019年12月30日
 * @see PreTrdRepoTradeRevokeDtoValidator
 * @since 1.0
 */
@Component
public class PreTrdRepoTradeRevokeDtoValidator implements BeanValidator {

    @Autowired
    private TrdRepoTrade001Service trdRepoTrade001Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        PreTrdRepoTradeRevokeDto inputDto = (PreTrdRepoTradeRevokeDto) value;
        List<ValidMessage> errorList = new ArrayList<>();

        // 存在流程中数据
        if (WorkFlowUtil.isNotFromToDoList()) {
            if (trdRepoTrade001Service.isInFlowDataExisted(inputDto.getTotleTradeId())) {
                ValidMessage validMessage = new ValidMessage(
                        getMessage("COMMON_E_0012", inputDto.getTotleTradeId()));
                errorList.add(validMessage);
            }
        }

        // 删除状态不允许撤回
        if (trdRepoTrade001Service.isRevokeDataExisted(inputDto.getTotleTradeId())) {
            ValidMessage validMessage = new ValidMessage(getMessage("TRD_W_0001",
                    getMessage("pingan.common.revoke"), getMessage("pingan.common.revoke")));
            errorList.add(validMessage);
        }

        //由到期续做产生的首期交易产品不给撤销
        if (trdRepoTrade001Service.isDqTrdProductInfo(inputDto.getTotleTradeId())) {
            ValidMessage validMessage = new ValidMessage(
                    getMessage("TrdRepoTrade001valid"));
            errorList.add(validMessage);
        }

        return errorList;
    }
}
