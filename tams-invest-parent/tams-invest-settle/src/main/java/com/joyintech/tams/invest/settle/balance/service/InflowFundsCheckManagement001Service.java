package com.joyintech.tams.invest.settle.balance.service;

import java.util.Map;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.settle.balance.PortfolioUnmatchedInfo;
import com.joyintech.tams.invest.settle.balance.dto.OutflowFundsSystemFlowDto;

/**
 * @program: tams-invest-parent
 * @description: 流入资金核对
 * @author: 强成西
 * @create: 2018-12-18 11:13
 **/
@Service
public class InflowFundsCheckManagement001Service extends CommonExportService<OutflowFundsSystemFlowDto> {

    public Page<PortfolioUnmatchedInfo> findPortfolioUnmatched() {
        return new Page(restTemplate.postForList(
            ServiceApplication.SETTLE_SERVICE_NAME + "/InflowFundsCheckManagement/findPortfolioUnmatched",
            RequestUtil.getPageParam(), PortfolioUnmatchedInfo.class));
    }

    public Page<OutflowFundsSystemFlowDto> findSysPage(Map<String, Object> params) {
        if (DictCodeList.是否_是.getValue().equals(params.get("isMatched"))
            && StringUtil.isNotEmpty((String)params.get("statusValid"))
            && !DictCodeListExt1.画面数据状态_有效.getValue().equals(params.get("statusValid"))) {
            return new Page<>();
        }

        params.put("userName", SecurityUtil.getLoginUser().getUserName());
        return restTemplate.postForPage(ServiceApplication.SETTLE_SERVICE_NAME + "/InflowFundsCheckManagement/findSysPage",
            params, OutflowFundsSystemFlowDto.class);
    }

    public Page<OutflowFundsSystemFlowDto> findEnPage(Map<String, Object> params) {
        return restTemplate.postForPage(ServiceApplication.SETTLE_SERVICE_NAME + "/InflowFundsCheckManagement/findEnPage", params,
            OutflowFundsSystemFlowDto.class);
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        if (exportParam.getSelectParam().get("isSys") != null
            && Boolean.parseBoolean((String)exportParam.getSelectParam().get("isSys"))) {
            exportParam.config("/com/joyintech/tams/invest/settle/balance/config/export_confSys.xml").dataSource(DataSourceType.MASERT);
        }
        else {
            exportParam.config("/com/joyintech/tams/invest/settle/balance/config/export_confEn.xml").dataSource(DataSourceType.MASERT);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<OutflowFundsSystemFlowDto> selectPageData(Map<String, Object> selectParam) {
        if (selectParam.get("isSys") != null && Boolean.parseBoolean((String)selectParam.get("isSys"))) {
            return findSysPage(selectParam);
        }
        else {
            return findEnPage(selectParam);
        }
    }

    /**
     * 流水更新
     *
     * @return check结果
     * @see
     * @since 1.0
     */
    public AjaxResult cashInfSel(Map<String, Object> pageParam){
        AjaxResult result = new AjaxResult();
        String code = restTemplate.postForObject(
                ServiceApplication.CUSTOMER_INTERF_NAME + "/interf/custodian/getAccountFlow",
                pageParam, String.class);
        if (com.joyintech.framework.util.StringUtil.equals(code, MessageType.提示消息.getCode())) {
            result.setMessage("申请成功！",MessageType.提示消息);
            result.setStatuCode(HttpStatus.OK.value());
        }
        else if (com.joyintech.framework.util.StringUtil.equals(code, MessageType.警告消息.getCode())) {
            result.setMessage("当日此托管账号没有流水！", MessageType.警告消息);
            result.setStatuCode(HttpStatus.OK.value());
        }
        else if (com.joyintech.framework.util.StringUtil.equals(code, MessageType.错误消息.getCode())) {
            result.setMessage("申请失败！",MessageType.错误消息);
            result.setStatuCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return result;
    }
}
