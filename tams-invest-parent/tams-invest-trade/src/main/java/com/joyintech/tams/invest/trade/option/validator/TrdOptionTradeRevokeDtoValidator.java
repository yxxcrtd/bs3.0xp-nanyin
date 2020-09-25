/*
 * 文件名：TrdLendInvestTradeRevokeDtoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：刘二通
 * 修改时间：2018年11月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.option.validator;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.trade.lend.dto.TrdLendInvestTradeRevokeDto;
import com.joyintech.tams.invest.trade.lend.service.TrdLendInvestTrade001Service;
import com.joyintech.tams.invest.trade.option.dto.TrdOptionTradeRevokeDto;
import com.joyintech.tams.invest.trade.option.service.TrdOptionTrade001Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 期权交易撤销前check
 *
 * @author zuoyinyi
 * @version 1.0 2020年1月16日
 * @see TrdOptionTradeRevokeDtoValidator
 * @since 1.0
 */
@Component
public class TrdOptionTradeRevokeDtoValidator implements BeanValidator {

    /**
     * TrdOptionTrade001Service
     */
    @Autowired
    private TrdOptionTrade001Service trdOptionTrade001Service;

    /**
     * 平台日期
     */
    @Autowired(required = false)
    private IPlatformDate platformDate;

    /**
     * 业务流程校验service
     */
    @Autowired(required = false)
    private MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        TrdOptionTradeRevokeDto inputDto = (TrdOptionTradeRevokeDto) value;
        List<ValidMessage> errorList = new ArrayList<>();

        //平台日
        Date paltDate = platformDate != null ? platformDate.getSysDate(): DateUtil.now();
        if(inputDto.getTradeDate() != null && DateUtil.compareDate(inputDto.getTradeDate(), paltDate) != 0){
            ValidMessage validMessage = new ValidMessage(getMessage("TRD_OPTION_001"));
            errorList.add(validMessage);
        }

        // 存在流程中数据
        if (WorkFlowUtil.isNotFromToDoList()) {
            if (trdOptionTrade001Service.isInFlowDataExisted(inputDto.getTotleTradeId())) {
                ValidMessage validMessage = new ValidMessage(
                    getMessage("COMMON_E_0012", inputDto.getTotleTradeId()));
                errorList.add(validMessage);
            }
        }

        // 删除状态不允许撤回
        if (trdOptionTrade001Service.isRevokeDataExisted(inputDto.getTotleTradeId())) {
            ValidMessage validMessage = new ValidMessage(getMessage("TRD_W_0001",
                getMessage("pingan.common.revoke"), getMessage("pingan.common.revoke")));
            errorList.add(validMessage);
        }

        // 存在存续期交易不允许撤回
        if (trdOptionTrade001Service.selectDurationDataCount(inputDto.getTotleTradeId())) {
            ValidMessage validMessage = new ValidMessage(getMessage("TRD_E_0020"));
            errorList.add(validMessage);
        }

        // 普通拆出交易已进行流出资金核对，则不允许撤销
        if (trdOptionTrade001Service.isAlreadyLqdPipedTrade(inputDto.getTotleTradeId())) {
            ValidMessage validMessage = new ValidMessage(getMessage("TRD_W_0010",
                inputDto.getTotleTradeId(), getMessage("pingan.common.revoke")));
            errorList.add(validMessage);
        }
        getValidMessage(errorList, inputDto);
        return errorList;
    }

    private List<ValidMessage> getValidMessage(List<ValidMessage> errorList, TrdOptionTradeRevokeDto dto) {
        WflValidInputParameter inputParameter = new WflValidInputParameter();
        inputParameter.setPageId("TrdOptionDuration002");
        inputParameter.setWorkflowId(dto.getWorkflowId());
        inputParameter.setBusinessDataPk2(dto.getTotleTradeId());
        if(mstWorkflowCommonService.isInWflProcess(inputParameter)){
            errorList.add(new ValidMessage(getMessage("BUSS_COM_MDATE_0002")));
        }
        else{
            inputParameter.setPageId("TrdOptionDuration003");//放弃行权
            if(mstWorkflowCommonService.isInWflProcess(inputParameter)){
                errorList.add(new ValidMessage(getMessage("BUSS_COM_MDATE_0002")));
            }
        }
        return errorList;
    }
}
