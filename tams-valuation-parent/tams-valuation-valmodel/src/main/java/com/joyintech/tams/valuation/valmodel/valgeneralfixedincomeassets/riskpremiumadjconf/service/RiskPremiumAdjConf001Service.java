package com.joyintech.tams.valuation.valmodel.valgeneralfixedincomeassets.riskpremiumadjconf.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.valmodel.valgeneralfixedincomeassets.riskpremiumadjconf.dto.RiskPremiumAdjConf001Dto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 资产减值管理
 * @author xutong
 * @createTime 2021-06-24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RiskPremiumAdjConf001Service extends CommonExportService<RiskPremiumAdjConf001Dto> {

    public Page<RiskPremiumAdjConf001Dto> selectForPage(Map<String, Object> params)
            throws Exception {
        return restTemplate.postForPage(ServiceApplication.VAL_MODEL_SERVICE_NAME+"/RiskPremiumAdjConf001/findPage", params, RiskPremiumAdjConf001Dto.class);
    }

    /**
     * 资产代码（剔除同时不含赎回权和利率调整权的永续债、优先股、可转债、可交债）
     * @param params
     * @return
     * @throws Exception
     */
    public List<SelectOptionDto> getFinprodIdForRiskPremium(Map<String, Object> params){
        return restTemplate.postForList(ServiceApplication.CUSTOMER_INTERF_NAME + "/interf/ModelCallOnInvest/getFinprodIdForRiskPremium", params, SelectOptionDto.class);
    }

}
