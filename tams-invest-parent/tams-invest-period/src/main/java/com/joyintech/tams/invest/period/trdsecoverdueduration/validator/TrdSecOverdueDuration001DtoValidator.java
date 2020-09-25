package com.joyintech.tams.invest.period.trdsecoverdueduration.validator;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.period.common.validator.PeriodCommonValidator;
import com.joyintech.tams.invest.period.trdsecoverdueduration.dto.TrdSecOverdueDuration001Dto;
import com.joyintech.tams.invest.period.trdsecoverdueduration.dto.TrdSecOverdueDuration002Dto;
import com.joyintech.tams.invest.period.trdsecoverdueduration.service.TrdSecOverdueDuration002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TrdSecOverdueDuration001DtoValidator
 * @Description: TODO
 * @Author wangxuelei
 * @Date 2020/1/21 0021
 * @Version V1.0
 **/
@Component
public class TrdSecOverdueDuration001DtoValidator  implements PeriodCommonValidator {

    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    @Override
    public List<ValidMessage> bussValid(Object value) {
        List<ValidMessage> errorList = new ArrayList<>();
        TrdSecOverdueDuration001Dto dto = (TrdSecOverdueDuration001Dto)value;
        //校验其前置的现金流信息所对应的交易 是否在流程中
            WflValidInputParameter parameter = new WflValidInputParameter();
            parameter.setPageId("TrdSecOverdueDuration002");
            parameter.setBusinessDataPk2(dto.getFinprodId());
            parameter.setBusinessDataPk3(dto.getSecAcctId());
            if(StringUtil.isNotEmpty(dto.getBjBranch())&&StringUtil.isNotBlank(dto.getBjBranch().toString())){
                parameter.setBusinessDataPk4(dto.getBjBranch().toString());
            }else{
                parameter.setBusinessDataPk4(dto.getLiBranch().toString());
            }
            boolean isInWfl = mstWorkflowCommonService.isInWflProcess(parameter);
            if (isInWfl){
                 errorList.add(new ValidMessage(this.getMessage("TRD_SEC_OVER_001")));
            }
        return errorList;
    }
}
