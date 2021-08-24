
package com.joyintech.tams.valuation.valmodel.valgeneralfixedincomeassets.cashflowdiscountmanagement.validator;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.valmodel.valgeneralfixedincomeassets.cashflowdiscountmanagement.dto.CashFlowDiscount001Dto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
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
                //modify by yanya on 20210823 修正提示语
                //resultList.add(new ValidMessage(getMessage("COMMON_E_0021", "标识", dto.getFinprodId() + ":" + dto.getFinprodName())));
                //return resultList;
                resultList.add(new ValidMessage(getMessage("请输入平滑区间！")));
            }
        }
        //modify by yanya on 20210823 增加兜底评级的提示
        if (StringUtil.equals(DictCodeList.是否_是.getValue(), dto.getIsBottomGrade())) {
            if (!StringUtil.isNotEmpty(dto.getBottomGrade())) {
                resultList.add(new ValidMessage(getMessage("请输入兜底评级！")));
            }
        }
        //modify by yanya on 20210823 增加生效日期的判定
        if (!StringUtil.isNotEmpty(dto.getEffDate())) {
            resultList.add(new ValidMessage(getMessage("请输入生效日期！")));
        }
        else {
            //平台日
            Date platDate = DateUtil.platDateFromCache();
            if(DateUtil.compareDate(dto.getEffDate(), platDate) < 0) {
                resultList.add(new ValidMessage(getMessage("生效日期必须大于等于平台日期！")));
            }
        }
        return resultList;
    }

}
