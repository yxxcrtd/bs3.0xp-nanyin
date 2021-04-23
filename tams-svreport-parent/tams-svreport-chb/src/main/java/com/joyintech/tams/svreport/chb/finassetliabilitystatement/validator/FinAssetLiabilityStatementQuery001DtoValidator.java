package com.joyintech.tams.svreport.chb.finassetliabilitystatement.validator;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.svreport.chb.finassetliabilitystatement.dto.FinAssetLiabilityStatementQuery001Dto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 校验查询条件
 *
 * @author qujianzhong
 * @version 1.0 2018年10月22日
 * @see FinAssetLiabilityStatementQuery001DtoValidator
 * @since 1.0
 */
@Component
public class FinAssetLiabilityStatementQuery001DtoValidator implements BeanValidator {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        FinAssetLiabilityStatementQuery001Dto dto = (FinAssetLiabilityStatementQuery001Dto) value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        return errorList;
    }

}
