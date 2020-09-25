package com.joyintech.tams.component.business.project;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.FinProductCredit;

import java.util.List;
import java.util.Map;

/**
 * 增信信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年10月11日
 * @see BussFinProductCreditService
 * @since 1.0
 */
public interface BussFinProductCreditService {

    /**
     * 默认entity名称
     */
    String DEFAULT_ENTITY_NAME = "bussFinProductCreditDto";

    List<FinProductCredit> getfinProductCreditByProductId(Map<String,Object> param);

    List<SelectOptionDto> getMarketInstitutionCustomerName(Map<String, Object> map);
}
