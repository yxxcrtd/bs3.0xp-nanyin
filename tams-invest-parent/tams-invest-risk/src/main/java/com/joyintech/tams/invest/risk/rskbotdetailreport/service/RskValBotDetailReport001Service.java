package com.joyintech.tams.invest.risk.rskbotdetailreport.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.rskbotdetailreport.dto.RskValBotDetailReport001Dto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RskValBotDetailReport001Service  extends CommonExportService<RskValBotDetailReport001Dto> {

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/risk/rskbotdetailreport/conf/export_conf.xml")
                .dataSource(DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<RskValBotDetailReport001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    /**
     * 导出
     * @param pageParam
     * @return
     */
    private Page<RskValBotDetailReport001Dto> selectForPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(
                ServiceApplication.RISK_SERVICE_NAME + "/RskValBotDetailReport/selectData",
                pageParam, RskValBotDetailReport001Dto.class);
    }

}
