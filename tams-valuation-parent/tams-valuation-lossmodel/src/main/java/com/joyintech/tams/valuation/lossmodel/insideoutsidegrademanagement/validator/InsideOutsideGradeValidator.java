
package com.joyintech.tams.valuation.lossmodel.insideoutsidegrademanagement.validator;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.lossmodel.insideoutsidegrademanagement.dto.InsideOutsideGrade001Dto;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 
 * @author liuhuaming
 * @version 1.0 2021年7月3日
 * @see InsideOutsideGradeValidator
 * @since 1.0
 */
@Component
public class InsideOutsideGradeValidator implements BeanValidator {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        InsideOutsideGrade001Dto dto = (InsideOutsideGrade001Dto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();

//        if (dto.getIsSmooth().equals(DictCodeList.是否_是.getValue())) {
//            if (!StringUtil.isNotEmpty(dto.getSmoothSection())) {
//                resultList.add(new ValidMessage(getMessage("COMMON_E_0021", "标识", dto.getFinprodId() + ":" + dto.getFinprodName())));
//                return resultList;
//            }
//        }
        return resultList;
    }

}
