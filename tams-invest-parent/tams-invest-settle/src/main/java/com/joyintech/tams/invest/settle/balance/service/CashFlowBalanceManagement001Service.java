package com.joyintech.tams.invest.settle.balance.service;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SuggestDto;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.settle.balance.dto.CashFlowBalanceManagement001Dto;
import com.joyintech.tams.invest.settle.fundback.dto.FundBackgroundManagement001Dto;

/**
 * @program: tams-invest-parent
 * @description:
 * @author: 强成西
 * @create: 2018-12-03 13:54
 **/
@Service
public class CashFlowBalanceManagement001Service extends CommonExportService<CashFlowBalanceManagement001Dto> {

    public Page<SelectOptionDto> findCounterAcctNo() {
        return restTemplate.postForPage(ServiceApplication.SETTLE_SERVICE_NAME + "/CashFlowBalanceManagement/findCounterAcctNo",
            RequestUtil.getPageParam(), SelectOptionDto.class);
    }

    public List<SuggestDto> findCounterAcctNoForSugg() {
        return restTemplate.getForList(ServiceApplication.SETTLE_SERVICE_NAME
                + "/CashFlowBalanceManagement/findCounterAcctNoForSugg?search=" +RequestUtil.getParam("param"), SuggestDto.class);
    }

    public List<SuggestDto> findAcctNoByTg() {
        return restTemplate.getForList(ServiceApplication.SETTLE_SERVICE_NAME
                + "/CashFlowBalanceManagement/findAcctNoByTg?search=" +RequestUtil.getParam("param"), SuggestDto.class);
    }

    public Page<CashFlowBalanceManagement001Dto> findPage(Map<String, Object> params) {
        return restTemplate.postForPage(ServiceApplication.SETTLE_SERVICE_NAME + "/CashFlowBalanceManagement/findPage", params,
            CashFlowBalanceManagement001Dto.class);
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/settle/balance/config/export_conf.xml").dataSource(DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<CashFlowBalanceManagement001Dto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }
}
