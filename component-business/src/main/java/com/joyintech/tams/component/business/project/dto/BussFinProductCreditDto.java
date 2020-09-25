package com.joyintech.tams.component.business.project.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.FinProductCredit;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.component.business.bond.validator.BusiFinBondRatingInfoValidator;
import com.joyintech.tams.component.business.project.validator.BussFinProductCreditValidator;

import java.util.List;

/**
 * 增信信息Dto
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年10月11日
 * @see BussFinProductCreditDto
 * @since 1.0
 */
@ValidLogic(targetClass = BussFinProductCreditValidator.class,groups = {ValidateGroupEdit.class})
public class BussFinProductCreditDto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    private List<FinProductCredit> finProductCreditList;

    public List<FinProductCredit> getFinProductCreditList() {
        return finProductCreditList;
    }

    public void setFinProductCreditList(List<FinProductCredit> finProductCreditList) {
        this.finProductCreditList = finProductCreditList;
    }
}
