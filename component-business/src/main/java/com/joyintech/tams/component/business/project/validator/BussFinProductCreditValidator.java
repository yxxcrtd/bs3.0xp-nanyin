package com.joyintech.tams.component.business.project.validator;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.FinProductCredit;
import com.joyintech.tams.component.business.bond.validator.BusiFinBondRatingInfoValidator;
import com.joyintech.tams.component.business.project.dto.BussFinProductCreditDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *  评级信息--业务验证
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月14日
 * @see BusiFinBondRatingInfoValidator
 * @since 1.0
 */
@Component
public class BussFinProductCreditValidator implements BeanValidator {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        BussFinProductCreditDto dto = (BussFinProductCreditDto)value;
        List<FinProductCredit> finProductCreditList = dto.getFinProductCreditList();
        //校验重复的增信主体
        Set<String> keySet = new HashSet<>();
        if(null!=finProductCreditList&&!finProductCreditList.isEmpty()) {
            for (FinProductCredit entity : finProductCreditList) {
                //评级类型不可为空
                if (StringUtil.isEmpty(entity.getCreditFlag())) {
                    resultList.add(new ValidMessage("",
                            getMessage("COMMON_E_0057", getMessage("BussFinProductCredit.creditFlag"))));
                    break;
                }
                //长期短期不可为空
                if (StringUtil.isEmpty(entity.getCreditType())) {
                    resultList.add(new ValidMessage("",
                            getMessage("COMMON_E_0057", getMessage("BussFinProductCredit.creditType"))));
                    break;
                }
                if (!keySet.add(entity.getCreditFlag())) {
                    resultList.add(new ValidMessage("",
                            getMessage("增信主体不能重复!")));
                    break;
                }
            }
        }
        return resultList;
    }
}
