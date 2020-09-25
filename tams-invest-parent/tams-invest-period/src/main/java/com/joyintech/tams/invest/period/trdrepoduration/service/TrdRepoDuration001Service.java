package com.joyintech.tams.invest.period.trdrepoduration.service;

import java.util.ArrayList;
import java.util.Map;

import com.joyintech.framework.security.LoginUserUtil;
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
import com.joyintech.tams.invest.period.trdrepoduration.dto.TrdRepoDuration001Dto;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdbondduration.service
 * @Description: 债券存续期管理-查询
 * @date Date : 2018年09月21日 14:32
 */
@Service
public class TrdRepoDuration001Service extends CommonExportService<TrdRepoDuration001Dto> {

    /**
     * @return
     */
    public Page<SelectOptionDto> getPortfolioId() {
        return restTemplate.postForPage(
            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdBondDuration/getPortfolioId",
            RequestUtil.getPageParam(), SelectOptionDto.class);
    }

    public Page<SelectOptionDto> getSecManageAcctId() {
        return restTemplate.postForPage(
            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdBondDuration/getSecManageAcctId",
            RequestUtil.getPageParam(), SelectOptionDto.class);
    }

    public Page<TrdRepoDuration001Dto> findPage(Map<String, Object> params) {
        if (params.get("statusValid") != null) {
            params.put("statusValid",
                StringUtil.splitToList(",", (String)params.get("statusValid")));
        }
        else {
            params.put("statusValid", null);
        }

        if (params.get("portfolioId") != null) {
            params.put("portfolioId",
                StringUtil.splitToList(",", (String)params.get("portfolioId")));
        }
        else {
            params.put("portfolioId", null);
        }

        if (params.get("ps") != null) {
            params.put("ps",
                StringUtil.splitToList(",", (String)params.get("ps")));
        }
        else {
            params.put("ps", null);
        }
        if (params.get("psType") != null) {
            params.put("psType",
                    StringUtil.splitToList(",", (String)params.get("psType")));
        }
        else {
            params.put("psType", null);
        }
        params.put("userName", LoginUserUtil.getUserName());
        return restTemplate.postForPage(
            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdRepoDuration/findPage", params,
            TrdRepoDuration001Dto.class);
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/period/trdrepoduration/config/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<TrdRepoDuration001Dto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }


    public Page<SelectOptionDto> getBusiCode() {
        Map<String, Object> param = RequestUtil.getPageParam();
        param.put("BUSI_TYPE", DictCodeList.共通业务类型_质押式回购存续期.getValue());
        param.put("TRADE_STATUS03", DictCodeListExt1.交易状态_撤销.getValue());
        param.put("userName", LoginUserUtil.getUserName());
        return restTemplate.postForPage(
                ServiceApplication.PERIOD_SERVICE_NAME + "/TrdRepoDuration/getBusiCode",
                param, SelectOptionDto.class);
    }

}
