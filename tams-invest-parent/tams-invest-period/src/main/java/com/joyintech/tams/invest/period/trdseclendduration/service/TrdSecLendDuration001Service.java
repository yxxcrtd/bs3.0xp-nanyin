package com.joyintech.tams.invest.period.trdseclendduration.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.period.trdseclendduration.dto.TrdSecLendDuration001Dto;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author : 裴家俊
 * @version V1.0
 * @Project: tams-invest-parent
 * @Description: 债券借贷存续期管理-查询
 * @date 2019年07月01日
 */
@Service
public class TrdSecLendDuration001Service extends CommonExportService<TrdSecLendDuration001Dto> {

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

    public Page<TrdSecLendDuration001Dto> findPage(Map<String, Object> params) {
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

        params.put("userName", LoginUserUtil.getUserName());
        Page<TrdSecLendDuration001Dto> retPage= restTemplate.postForPage(
            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdSecLendDuration/findPage", params,
            TrdSecLendDuration001Dto.class);
        return retPage;
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/period/trdseclendduration/config/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<TrdSecLendDuration001Dto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }


    public Page<SelectOptionDto> getBusiCode() {
        Map<String, Object> param = RequestUtil.getPageParam();
        param.put("BUSI_TYPE", DictCodeListExt2.共通业务类型_债券借贷存续期.getValue());
        param.put("TRADE_STATUS03", DictCodeListExt1.交易状态_撤销.getValue());
        param.put("userName", LoginUserUtil.getUserName());
        return restTemplate.postForPage(
                ServiceApplication.PERIOD_SERVICE_NAME + "/TrdSecLendDuration/getBusiCode",
                param, SelectOptionDto.class);
    }

}
