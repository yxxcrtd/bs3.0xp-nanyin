
package com.joyintech.tams.valuation.valmodel.valgeneralfixedincomeassets.cashflowdiscountmanagement.validator;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.valmodel.valgeneralfixedincomeassets.cashflowdiscountmanagement.dto.CashFlowDiscount001Dto;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 
 * @author liuhuaming
 * @version 1.0 2021年7月3日
 * @see CashFlowDiscountValidator
 * @since 1.0
 */
@Component
public class CashFlowDiscountValidator implements BeanValidator {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        CashFlowDiscount001Dto dto = (CashFlowDiscount001Dto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();

        if (dto.getIsSmooth().equals(DictCodeList.是否_是.getValue())) {
            if (!StringUtil.isNotEmpty(dto.getSmoothSection())) {
                resultList.add(new ValidMessage(getMessage("COMMON_E_0021", "标识", dto.getFinprodId() + ":" + dto.getFinprodName())));
                return resultList;
            }
        }
        return resultList;
    }

}
