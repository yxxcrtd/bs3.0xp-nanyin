package com.joyintech.service.asset.common.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.FinProductCredit;

import java.util.List;

/**
 * 增信信息Dto
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年10月11日
 * @see BussFinProductCreditDto
 * @since 1.0
 */
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
