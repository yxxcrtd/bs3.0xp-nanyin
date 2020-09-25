/*
 * 文件名：InvestFeePay001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：malinlin
 * 修改时间：2018年12月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.feepay.validator;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.portfolio.feepay.dto.InvestFeePay001Dto;
import com.joyintech.tams.invest.portfolio.feepay.service.InvestFeePay001Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 投资端费用支付
 * 
 * @author malinlin
 * @version 1.0 2019年12月9日
 * @see InvestFeePay001Validator
 * @since 1.0
 */
@Component
public class InvestFeePay001Validator implements BeanValidator {

    /**
     * {@linkplain}
     */
    @Autowired
    private InvestFeePay001Service investFeePay001Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        InvestFeePay001Dto finProductFeePay001Dto = (InvestFeePay001Dto)value;
        List<ValidMessage> validList = new ArrayList<ValidMessage>();

        if (StringUtil.equals(finProductFeePay001Dto.getOperateType(), "confirm")) {
            // 数据状态必须为待处理
            if (!StringUtil.equals(finProductFeePay001Dto.getEffectStatusStr(),
                DictCodeListExt1.有效状态_待处理.getCodeName())) {
                validList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0032",
                    DictCodeListExt1.有效状态_待处理.getCodeName())));
            }
            else{
                // 查询是否有小于当前日期的待处理的计划
                if (investFeePay001Service.checkUndoPlanExists(finProductFeePay001Dto.getCashId(),
                    finProductFeePay001Dto.getCashNum()) > 0) {
                    validList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0063")));
                }
            }
        }
        else if (StringUtil.equals(finProductFeePay001Dto.getOperateType(), "revoke")) {
        	if (DateUtil.compareDate(finProductFeePay001Dto.getPayDate(), DateUtil.platDateFromCache()) < 0) {
        		validList.add(new ValidMessage("不能撤销平台日大于支付日的费用交易！"));
        	}
            // 数据状态必须为有效
            if (!StringUtil.equals(finProductFeePay001Dto.getEffectStatusStr(),
                DictCodeListExt1.有效状态_有效.getCodeName())) {
                validList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0032",
                    DictCodeListExt1.有效状态_有效.getCodeName())));
            }

            // 存在流程中数据
            if (WorkFlowUtil.isNotFromToDoList()) {
                if (investFeePay001Service.isInFlowDataExisted(
                    finProductFeePay001Dto.getTradeId())) {
                    validList.add(new ValidMessage(
                        getMessage("COMMON_E_0012", finProductFeePay001Dto.getTradeId())));
                }
            }

            // 删除状态不允许撤回
            if (investFeePay001Service.isRevokeDataExisted(finProductFeePay001Dto.getTradeId())) {
                validList.add(new ValidMessage(getMessage("TRD_W_0001",
                    getMessage("pingan.common.revoke"), getMessage("pingan.common.revoke"))));
            }
        }

        return validList;
    }

}
