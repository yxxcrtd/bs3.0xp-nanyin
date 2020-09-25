

package com.joyintech.tams.invest.product.riskLevelRating.validator;


import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.product.riskLevelRating.service.RiskLevelRating002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author jyl
 * @version 1.0 2020-01-04
 * @see RiskLevelRatingValidator
 * @since 1.0
 */
@Component
public class RiskLevelRatingValidator implements BeanValidator {

    /**
     * 产品风险等级评定新建Service
     */
    @Autowired
    private RiskLevelRating002Service riskLevelRating002Service;

    /**
     * 产品风险等级评定
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        List<ValidMessage> erroList = new ArrayList<ValidMessage>();

        return erroList;
    }

}
