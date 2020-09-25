package com.joyintech.tams.invest.period.trdprojint.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.period.trdprojint.dto.TrdProjectDurationIntDto;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdbondduration.service
 * @Description: 债券存续期管理-查询
 * @date Date : 2018年09月21日 14:32
 */
@Service
public class TrdProjectDurationInterest001Service extends CommonExportService<TrdProjectDurationIntDto> {

    /**
     * 查询
     * 
     * @param selectParam 参数
     * @return
     */
    public Page<TrdProjectDurationIntDto> findPage(Map<String, Object> selectParam) {
        if (selectParam.get("assetType") != null) {
            selectParam.put("assetType", StringUtil.splitToList(",", (String)selectParam.get("assetType")));
        }
        return restTemplate.postForPage(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdProjectDurationInterest/findPage",
            selectParam, TrdProjectDurationIntDto.class);
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/period/trdprojint/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<TrdProjectDurationIntDto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }
}
