/*
 * 文件名：PtlSubFundsAllocate002Validator.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：adrian.zhou
 * 修改时间：2019年9月1日 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlsubfundsmange.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.portfolio.ptlsubfundsmange.dto.PtlSubFundsAllocate002Dto;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

@Component
public class PtlSubFundsAllocate002Validator implements BeanValidator {

    @Autowired(required = false)
    private MstWorkflowCommonService mstWorkflowCommonServie;

    @Override
    public List<ValidMessage> valid(Object value) {
        List<ValidMessage> messageList = new ArrayList<>();

        if (WorkFlowUtil.isNotFromToDoList()) {
            PtlSubFundsAllocate002Dto dto = (PtlSubFundsAllocate002Dto)value;
            WflValidInputParameter param = new WflValidInputParameter();
            param.setPageId("PtlSubFundsAllocate002");
            if (StringUtil.isNotBlank(dto.getTradeId()))
                param.setBusinessDataPk1(dto.getTradeId());
            param.setBusinessDataPk2(DictCodeList.共通业务类型_子组合资金调配.getValue());
            param.setBusinessDataPk3(dto.getPortfolioId());
            if (mstWorkflowCommonServie.isInWflProcess(param)) {
                messageList.add(new ValidMessage(getMessage("BUSS_COM_E_0023")));
            }

            if (dto.getSelectedItem() == null || dto.getSelectedItem().isEmpty()) {
                messageList.add(new ValidMessage("子组合资金调配列表为空。"));
            }
            else {
                dto.getSelectedItem().forEach(ptlSubFundsAllocate002Dto -> {
                    if (StringUtil.isEmpty(ptlSubFundsAllocate002Dto.getCcy())) {
                        messageList.add(new ValidMessage("子组合资金调配列表币种不能为空。"));
                    }
                    if (ptlSubFundsAllocate002Dto.getTradeAmt() != null
                        && ptlSubFundsAllocate002Dto.getTradeAmt().compareTo(BigDecimal.ZERO) == 0) {
                        messageList.add(new ValidMessage("子组合资金调配列表调配金额不能为0。"));
                    }
                });
            }
        }
        return messageList;
    }

}
