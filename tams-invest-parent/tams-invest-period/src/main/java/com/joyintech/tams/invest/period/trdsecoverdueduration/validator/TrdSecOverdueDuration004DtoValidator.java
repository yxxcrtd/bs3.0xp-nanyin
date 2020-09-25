package com.joyintech.tams.invest.period.trdsecoverdueduration.validator;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.period.common.validator.PeriodCommonValidator;
import com.joyintech.tams.invest.period.trdsecoverdueduration.dto.TrdSecOverdueDuration001Dto;
import com.joyintech.tams.invest.period.trdsecoverdueduration.dto.TrdSecOverdueDuration004Dto;
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
public class TrdSecOverdueDuration004DtoValidator implements PeriodCommonValidator {

    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    @Override
    public List<ValidMessage> bussValid(Object value) {
        List<ValidMessage> errorList = new ArrayList<>();
        TrdSecOverdueDuration004Dto dto = (TrdSecOverdueDuration004Dto)value;
        //校验其前置的现金流信息所对应的交易 是否在流程中
            WflValidInputParameter inputParameter = new WflValidInputParameter();
            inputParameter.setPageId("TrdSecOverdueDuration003");
            inputParameter.setWorkflowId(dto.getWorkflowId());
//            inputParameter.setBusinessDataPk2(dto.getPortfolioId());
            inputParameter.setBusinessDataPk3(dto.getFinprodId());
            inputParameter.setBusinessDataPk4(dto.getSecAcctId());
            if(dto.getBjBranch()!=null){
                inputParameter.setBusinessDataPk5(dto.getBjBranch().toString());
            }
            if(dto.getLiBranch()!=null){
                inputParameter.setBusinessDataPk6(dto.getLiBranch().toString());
            }
            if(mstWorkflowCommonService.isInWflProcess(inputParameter)){
                errorList.add(new ValidMessage(getMessage("BUSS_COM_MDATE_0002")));
            }
        return errorList;
    }
}
