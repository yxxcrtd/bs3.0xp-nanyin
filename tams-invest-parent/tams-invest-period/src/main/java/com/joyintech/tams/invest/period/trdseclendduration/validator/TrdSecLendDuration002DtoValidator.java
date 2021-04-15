package com.joyintech.tams.invest.period.trdseclendduration.validator;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.TrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdseclendduration.dto.TrdSecLendDuration002Dto;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.workflow.engine.WorkflowContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 债券借贷存续期管理
 *
 * @author 裴家俊
 * @version 1.0 2019年7月01日
 * @see TrdSecLendDuration002DtoValidator
 * @since 1.0
 */
@Component
public class TrdSecLendDuration002DtoValidator implements BeanValidator {

    @Autowired
    private PeriodCommonService periodCommonService;
    @Autowired
    private BussTrdTransferOrderService bussTrdTransferOrderService;
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    @Autowired
    private TrdFinProductDealTotalMapper trdFinProductDealTotalMapper;

    @Override
    public List<ValidMessage> valid(Object value) {
        TrdSecLendDuration002Dto dto = (TrdSecLendDuration002Dto) value;
        List<ValidMessage> errorList = new ArrayList<>();
        if (!"revoke".equals(dto.getOperateType())) {
            errorList.addAll(bussTrdTransferOrderService.check(dto.getBussTrdTransferOrder()));
        }
        //当操作类型为修改或撤销时，判断是否在流程中
        if (StringUtil.equals(dto.getOperateType(), "revoke")) {
            TrdFinProductDealTotal trdFinProductDealTotal = trdFinProductDealTotalMapper.selectByPrimaryKey(dto.getBusiCode());
            if(StringUtil.isNotEmpty(trdFinProductDealTotal)) {
                WflValidInputParameter validInputParameter = new WflValidInputParameter();
                ProcessMark processMark = WorkflowContext.getProcessMarkService().getParameterByAnnotation(dto);
                validInputParameter.setWorkflowId(dto.getWorkflowId());
                validInputParameter.setPageId(processMark.getPageId());
                validInputParameter.setBusinessDataPk1(trdFinProductDealTotal.getFinprodId());
                validInputParameter.setBusinessDataPk2(dto.getBusiType());
                if (mstWorkflowCommonService.isInWflProcess(validInputParameter)) {
                    errorList.add(new ValidMessage("已存在流程中数据，不允许撤销!"));
                    return errorList;
                }
            }
        }
        periodCommonService.periodCommonRevokeCheck(dto.getStatusValid(), dto.getBusiCode(), errorList);
        return errorList;
    }
}
