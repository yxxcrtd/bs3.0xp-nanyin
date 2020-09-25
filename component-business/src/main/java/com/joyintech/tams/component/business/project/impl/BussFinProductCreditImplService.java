package com.joyintech.tams.component.business.project.impl;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.FinProductCredit;
import com.joyintech.tams.component.business.project.BussFinProductCreditService;
import com.joyintech.tams.component.business.project.dto.BussFinProductCreditDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BussFinProductCreditImplService extends BaseService<BussFinProductCreditDto> implements BussFinProductCreditService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FinProductCredit> getfinProductCreditByProductId(Map<String, Object> param) {
        return restTemplate.getForList(ServiceApplication.ASSET_SERVICE_NAME
                        + "/BussFinProductCredit/getFinProductCreditByProductId?finprodId="
                        + param.get("finprodId").toString(),
                FinProductCredit.class);
    }

    @Override
    public List<SelectOptionDto> getMarketInstitutionCustomerName(Map<String, Object> map) {
        return restTemplate.postForList(
                ServiceApplication.ASSET_SERVICE_NAME + "/AssetCommon/getMarketInstitutionCustomerName",
                map, SelectOptionDto.class);
    }

}
