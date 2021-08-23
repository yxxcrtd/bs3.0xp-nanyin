package com.joyintech.tams.invest.risk.outsvaltableimporthis.service;


import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.outsvaltableimporthis.dto.RskOutValtblImportHis001Dto;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RskOutValtblImportHis002Service extends CommonExportService<RskOutValtblImportHis001Dto> {

    /**
     * <功能详细描述>
     *
     * @param dto dto
     * @return
     * @see
     * @since 1.0
     */
    public List<RskOutValtblImportHis001Dto> selectTraceByKey(RskOutValtblImportHis001Dto dto) {
        return restTemplate.postForList(
                ServiceApplication.RISK_SERVICE_NAME + "/rskOutValtblImportHis/selectTraceByKey",
                dto, RskOutValtblImportHis001Dto.class);
    }
}
