package com.joyintech.tams.invest.period.trdbondduration.service;

import java.util.ArrayList;
import java.util.Map;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.period.trdbondduration.dto.TrdBondDuration002ProcessDto;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdbondduration.service
 * @Description: 债券存续期管理-查询
 * @date Date : 2018年09月21日 14:32
 */
@Service
public class TrdBondDuration001Service extends CommonExportService<TrdBondDuration002ProcessDto> {

    public Page<SelectOptionDto> getSecManageAcctId() {
        return restTemplate.postForPage(
            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdBondDuration/getSecManageAcctId",
            RequestUtil.getPageParam(), SelectOptionDto.class);
    }

    public Page<SelectOptionDto> getFinprodMarketId() {
        Map<String, Object> params = RequestUtil.getPageParam();
        params.put("finprodType2",
            DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        return restTemplate.postForPage(
            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdBondDuration/getFinprodMarketId",
                params, SelectOptionDto.class);
    }

    public Page<TrdBondDuration002ProcessDto> findPage(Map<String, Object> params) {
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
                ServiceApplication.PERIOD_SERVICE_NAME + "/TrdBondDuration/findPage", params,
                TrdBondDuration002ProcessDto.class);
    }

    protected void buildExportParam(ExportParam exportParam) {
        if(MultilevelDataAuthorityUtil.isEnabled()){
            exportParam.config(
                    "/com/joyintech/tams/invest/period/trdbondduration/config/export_conf_lianshe.xml").dataSource(
                    DataSourceType.MASERT);
        }
        else {
            exportParam.config(
                    "/com/joyintech/tams/invest/period/trdbondduration/config/export_conf.xml").dataSource(
                    DataSourceType.MASERT);
        }
    }

    /**
     * {@inheritDoc}
     */

    public Page<TrdBondDuration002ProcessDto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }

    public Page<SelectOptionDto> getBusiCode() {
        return restTemplate.postForPage(
                ServiceApplication.PERIOD_SERVICE_NAME + "/TrdBondDuration/getBusiCode",
                RequestUtil.getPageParam(), SelectOptionDto.class);
    }
}
