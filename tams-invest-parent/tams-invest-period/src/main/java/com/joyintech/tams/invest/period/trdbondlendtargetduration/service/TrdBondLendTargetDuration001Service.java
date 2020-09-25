package com.joyintech.tams.invest.period.trdbondlendtargetduration.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.period.trdbondlendtargetduration.dto.TrdBondLendTargetDuration002ProcessDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdbondduration.service
 * @Description: 债券存续期管理-查询
 * @date Date : 2018年09月21日 14:32
 */
@Service
public class TrdBondLendTargetDuration001Service extends CommonExportService<TrdBondLendTargetDuration002ProcessDto> {

    public Page<TrdBondLendTargetDuration002ProcessDto> findPage(Map<String, Object> params) {
        if (params.get("statusValid") != null) {
            params.put("statusValid",
                    StringUtil.splitToList(",", (String)params.get("statusValid")));
        }
        else {
            params.put("statusValid", new ArrayList<>());
        }
        if (params.get("portfolioId") != null) {
            params.put("portfolioId",
                    StringUtil.splitToList(",", (String)params.get("portfolioId")));
        }
        else {
            params.put("portfolioId", new ArrayList<>());
        }
        params.put("userName", LoginUserUtil.getUserName());
        return restTemplate.postForPage(
                ServiceApplication.PERIOD_SERVICE_NAME + "/TrdBondLendTargetDuration/findPage", params,
                TrdBondLendTargetDuration002ProcessDto.class);
    }

    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/invest/period/trdbondlendtargetduration/config/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */

    public Page<TrdBondLendTargetDuration002ProcessDto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }

    public Page<SelectOptionDto> getBusiCode() {
        return restTemplate.postForPage(
                ServiceApplication.PERIOD_SERVICE_NAME + "/TrdBondLendTargetDuration/getBusiCode",
                RequestUtil.getPageParam(), SelectOptionDto.class);
    }
}
