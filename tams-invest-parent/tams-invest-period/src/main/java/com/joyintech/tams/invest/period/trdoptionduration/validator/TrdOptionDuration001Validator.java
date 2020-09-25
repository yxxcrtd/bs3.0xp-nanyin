package com.joyintech.tams.invest.period.trdoptionduration.validator;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.period.common.validator.PeriodCommonValidator;
import com.joyintech.tams.invest.period.trdoptionduration.dto.OptionDurationDetailDto;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.period.trdoptionduration.service.TrdOptionDuration001Service;
import com.joyintech.tams.invest.period.trdsecoverdueduration.dto.TrdSecOverdueDuration003Dto;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Component
public class TrdOptionDuration001Validator implements BeanValidator {

    /**
     * service
     */
    @Autowired(required = false)
    private TrdOptionDuration001Service trdOptionDuration001Service;

    /**
     * 业务流程校验service
     */
    @Autowired(required = false)
    private MstWorkflowCommonService mstWorkflowCommonService;

    @Override
    public List<ValidMessage> valid(Object value) {
        OptionDurationDetailDto dto = (OptionDurationDetailDto)value;
        List<ValidMessage> messageList = new ArrayList<>();
        //支付日不能小于行权日
        if (dto.getXqDate() != null && DateUtil.compareDate(dto.getZfDate(), dto.getXqDate()) < 0) {
            ValidMessage validMessage = new ValidMessage("zfDate",
                    getMessage("FW_E_0008", getMessage("TrdOptionTrade002.payDate"),
                            getMessage("TrdOptionDuration.xqDate")));
            messageList.add(validMessage);
        }

        // 存在流程中数据
        if (WorkFlowUtil.isNotFromToDoList()) {
            if (trdOptionDuration001Service.isInFlowDataExisted(dto.getFinprodId())) {
                ValidMessage validMessage = new ValidMessage(
                        getMessage("COMMON_E_0012", dto.getTotleTradeId()));
                messageList.add(validMessage);
            }
            getValidMessage(messageList, dto);
        }
        return messageList;
    }

    private List<ValidMessage> getValidMessage(List<ValidMessage> errorList, OptionDurationDetailDto dto) {
        WflValidInputParameter inputParameter = new WflValidInputParameter();
        inputParameter.setPageId("TrdOptionDuration002");
        inputParameter.setWorkflowId(dto.getWorkflowId());
        inputParameter.setBusinessDataPk2(dto.getFinprodId());
        if(mstWorkflowCommonService.isInWflProcess(inputParameter)){
            errorList.add(new ValidMessage(getMessage("BUSS_COM_MDATE_0002")));
        }else{
            inputParameter.setPageId("TrdOptionDuration003");
            if(mstWorkflowCommonService.isInWflProcess(inputParameter)){
                errorList.add(new ValidMessage(getMessage("BUSS_COM_MDATE_0002")));
            }
        }
        return errorList;
    }
}
