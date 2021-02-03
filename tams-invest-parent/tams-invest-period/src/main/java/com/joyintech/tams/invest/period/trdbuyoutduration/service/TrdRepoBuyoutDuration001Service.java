package com.joyintech.tams.invest.period.trdbuyoutduration.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.period.trdbuyoutduration.dto.TrdRepoBuyoutDuration001Dto;

import com.joyintech.tams.valuation.common.constants.GlobalConstant;
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
public class TrdRepoBuyoutDuration001Service extends CommonExportService<TrdRepoBuyoutDuration001Dto> {

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

    public Page<TrdRepoBuyoutDuration001Dto> findPage(Map<String, Object> params) {
        if (params.get("statusValid") != null) {
            params.put("statusValid",
                    StringUtil.splitToList(",", (String) params.get("statusValid")));
        } else {
            params.put("statusValid", null);
        }

        //多选下拉框处理（合作资产）
        Object chlFinprodIdObj = params.get("chlFinprodId");
        if (chlFinprodIdObj != null){
            params.put("chlFinprodIdList",
                    StringUtil.splitToList(GlobalConstant.COMMA,(String) chlFinprodIdObj));
        }else {
            params.put("chlFinprodIdList",new ArrayList<>());
        }

        if (params.get("portfolioId") != null) {
            params.put("portfolioId",
                    StringUtil.splitToList(",", (String) params.get("portfolioId")));
        } else {
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
        return restTemplate.postForPage(
                ServiceApplication.PERIOD_SERVICE_NAME + "/TrdRepoBuyoutDuration/findPage", params,
                TrdRepoBuyoutDuration001Dto.class);
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/invest/period/trdbuyoutduration/config/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<TrdRepoBuyoutDuration001Dto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }


    public Page<SelectOptionDto> getBusiCode() {
        Map<String, Object> param = RequestUtil.getPageParam();
        param.put("BUSI_TYPE", DictCodeList.共通业务类型_买断式回购存续期.getValue());
        param.put("TRADE_STATUS03", DictCodeListExt1.交易状态_撤销.getValue());
        return restTemplate.postForPage(
                ServiceApplication.PERIOD_SERVICE_NAME + "/TrdRepoBuyoutDuration/getBusiCode",
                param, SelectOptionDto.class);
    }

}
