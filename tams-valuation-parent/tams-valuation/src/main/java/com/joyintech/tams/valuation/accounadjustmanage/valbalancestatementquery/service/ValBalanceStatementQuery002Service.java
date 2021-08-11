package com.joyintech.tams.valuation.accounadjustmanage.valbalancestatementquery.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.common.base.entity.BokBussDetsub;
import com.joyintech.tams.valuation.accounadjustmanage.valbalancestatementquery.dto.*;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ValBalanceStatementQuery002Service extends CommonExportService<ValBalanceStatementQuery001Dto> {

    public BokBussDetsub getSubnameTail(Map<String, Object> map) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValBalanceStatementQuery002/getSubnameTail", map, BokBussDetsub.class);
    }

    /**
     * 余额流水信息查询
     * @param parameters
     * @return
     */
    public Page<ValBalanceStatementQuery002Dto> selectForPage(Map<String, Object> parameters) {
        String jizhangDate=(String) parameters.get("jizhangDate");
        String startDate=jizhangDate.substring(0,10);
        String endDate=jizhangDate.substring(12);
        parameters.put("startDate",startDate);
        parameters.put("endDate",endDate);
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME+"/ValBalanceStatementQuery002/findPage", parameters, ValBalanceStatementQuery002Dto.class);
    }

    /**
     * 凭证信息查询
     * @param parameters
     * @return
     */
    public Page<ValBalanceStatementQuery003Dto> selectForVouchPage(Map<String, Object> parameters) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME+"/ValBalanceStatementQuery002/selectForVouchPage", parameters, ValBalanceStatementQuery003Dto.class);
    }

    /**
     * 交易信息查询
     * @param parameters
     * @return
     */
    public Page<ValBalanceStatementQuery004Dto> selectForTradePage(Map<String, Object> parameters) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME+"/ValBalanceStatementQuery002/selectForTradePage", parameters, ValBalanceStatementQuery004Dto.class);
    }

    public FinProductDto getFinprodByBoksetId(Map<String, Object> map) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValBalanceStatementQuery002/getFinprodByBoksetId", map, FinProductDto.class);
    }
}
