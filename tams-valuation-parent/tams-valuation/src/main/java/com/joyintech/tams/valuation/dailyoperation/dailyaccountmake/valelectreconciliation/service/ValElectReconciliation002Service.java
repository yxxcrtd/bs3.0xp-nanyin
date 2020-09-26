package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valelectreconciliation.service;

import java.util.Map;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valelectreconciliation.dto.ValElectReconciliation002Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;

@Service
public class ValElectReconciliation002Service extends CommonExportService<ValElectReconciliation002Dto> {

    /**
     * 对账明细 <功能详细描述>
     * 
     * @param params
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<ValElectReconciliation002Dto> findPage(Map<String, Object> params)
        throws Exception {
        return restTemplate.postForPage(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValElectReconciliation002/findPage",
            params, ValElectReconciliation002Dto.class);
    }

}
