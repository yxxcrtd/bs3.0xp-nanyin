package com.joyintech.tams.invest.settle.balance.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.invest.common.service.MapperDelegateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.LqdPipelineMatch;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.settle.balance.PortfolioUnmatchedInfo;
import com.joyintech.tams.invest.settle.balance.dto.OutflowFundsSystemFlowDto;
import com.joyintech.tams.invest.settle.balance.dto.OutflowManualMatchDto;

/**
 * @program: tams-invest-parent
 * @description: 流出资金核对
 * @author: 强成西
 * @create: 2018-12-04 10:33
 **/
@Service
public class OutflowFundsCheckManagement001Service extends CommonExportService<OutflowFundsSystemFlowDto> {

    @Autowired
    private MapperDelegateService mapperDelegateService;

    /**
     * 基础表的增删改查
     */

    public Page<SelectOptionDto> findEscrowAcctNo() {
        return restTemplate.postForPage(ServiceApplication.SETTLE_SERVICE_NAME + "/OutflowFundsCheckManagement/findEscrowAcctNo",
            RequestUtil.getPageParam(), SelectOptionDto.class);
    }

    public Page<PortfolioUnmatchedInfo> findPortfolioUnmatched() {
        return new Page(restTemplate.postForList(
            ServiceApplication.SETTLE_SERVICE_NAME + "/OutflowFundsCheckManagement/findPortfolioUnmatched",
            RequestUtil.getPageParam(), PortfolioUnmatchedInfo.class));
    }

    public Page<SelectOptionDto> getPortfolioId() {
        return restTemplate.postForPage(ServiceApplication.SETTLE_SERVICE_NAME + "/OutflowFundsCheckManagement/getPortfolioId",
            RequestUtil.getPageParam(), SelectOptionDto.class);
    }

    public Page<OutflowFundsSystemFlowDto> findSysPage(Map<String, Object> selectParam) {
        return restTemplate.postForPage(ServiceApplication.SETTLE_SERVICE_NAME + "/OutflowFundsCheckManagement/findSysPage",
            selectParam, OutflowFundsSystemFlowDto.class);
    }

    public Page<OutflowFundsSystemFlowDto> findEnPage(Map<String, Object> selectParam) {
        return restTemplate.postForPage(ServiceApplication.SETTLE_SERVICE_NAME + "/OutflowFundsCheckManagement/findEnPage",
            selectParam, OutflowFundsSystemFlowDto.class);
    }

    public Long selectSysFlowCountByTradeId(List<String> seqNoList) {
        return restTemplate.postForObject(
            ServiceApplication.SETTLE_SERVICE_NAME + "/OutflowFundsCheckManagement/selectSysFlowCountByTradeId", seqNoList,
            Long.class);
    }

    public Long selectEnFlowCountByTradeId(List<String> seqNoList) {
        return restTemplate.postForObject(
            ServiceApplication.SETTLE_SERVICE_NAME + "/OutflowFundsCheckManagement/selectEnFlowCountByTradeId", seqNoList,
            Long.class);
    }

    public void manualMatch(OutflowManualMatchDto dto) {
        String groupSeqNo = IDGenerator.generate();
        List<OutflowFundsSystemFlowDto> sysFlow = dto.getSysFlow();
        List<OutflowFundsSystemFlowDto> enFlow = dto.getEnFlow();
        List<OutflowFundsSystemFlowDto> maxLengthList = sysFlow.size() > enFlow.size() ? sysFlow : enFlow;
        List<LqdPipelineMatch> lqdPipelineMatches = new ArrayList<>();
        for (int i = 0; i < maxLengthList.size(); i++ ) {
            LqdPipelineMatch match = new LqdPipelineMatch();
            match.setMatchSeqno(IDGenerator.generate());
            match.setGroupId(groupSeqNo);
            if (OutflowFundsSystemFlowDto.SYS.equals(maxLengthList.get(i).getSysOrEn())) {
                setSysFlow(maxLengthList, i, match);
                if (i < enFlow.size()) {
                    setEnFlow(enFlow, i, match, dto.getRemark());
                }
            }
            else {
                setEnFlow(maxLengthList, i, match, dto.getRemark());
                if (i < sysFlow.size()) {
                    setSysFlow(sysFlow, i, match);
                }
            }

            lqdPipelineMatches.add(match);
        }
        if (lqdPipelineMatches.size() > 0) {
            mapperDelegateService.insertAutoClassifyBatch(lqdPipelineMatches);
        }
    }

    private void setSysFlow(List<OutflowFundsSystemFlowDto> maxLengthList, int i, LqdPipelineMatch match) {
        match.setSysTradeId(maxLengthList.get(i).getSysSeqNo());
        match.setSysIsTotalTrade(maxLengthList.get(i).getIsTotalTrade());
        match.setSysCdate(DateUtil.parse(maxLengthList.get(i).getSettleDate()));
        match.setSysBussType(maxLengthList.get(i).getPaBizType());
        match.setSysPortfolioId(maxLengthList.get(i).getPortfolioId());
        match.setSysPortfolioName(maxLengthList.get(i).getPortfolioName());
        match.setSysFinprodId(maxLengthList.get(i).getFinprodMarketId());
        match.setSysFinprodName(maxLengthList.get(i).getFinprodName());
        match.setSysPrinamt(maxLengthList.get(i).getSettleAmt());
        match.setSysCcy(maxLengthList.get(i).getCcy());
    }

    private void setEnFlow(List<OutflowFundsSystemFlowDto> enFlow, int i, LqdPipelineMatch match, String remark) {
        match.setTruSeqNo(enFlow.get(i).getEnSeqNo());
        match.setTruAccount(enFlow.get(i).getAccount());
        match.setTruPs(enFlow.get(i).getPs());
        match.setTruPrinamt(enFlow.get(i).getSettleAmt());
        match.setTruSeller(enFlow.get(i).getCounterAcctNo());
        match.setTruSellername(enFlow.get(i).getCounterAcctName());
        match.setTruUsage(enFlow.get(i).getUsage());
        match.setRemark(remark);
    }

    public OutflowManualMatchDto selectMatchDtoForCancel(String seqNo, String sysOrEn) {
        Map<String, String> params = new HashMap<>();
        params.put("seqNo", seqNo);
        params.put("sysOrEn", sysOrEn);
        return restTemplate.postForObject(
            ServiceApplication.SETTLE_SERVICE_NAME + "/OutflowFundsCheckManagement/selectMatchDtoForCancel", params,
            OutflowManualMatchDto.class);
    }

    public void cancelMatch(OutflowManualMatchDto dto) {
        restTemplate.postForObject(ServiceApplication.SETTLE_SERVICE_NAME + "/OutflowFundsCheckManagement/cancelMatch",
            dto.getGroupId(), String.class);
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

    public void remarkSet(List<String> enSeqNos, String remark) {
        Map<String, Object> params = new HashMap<>();
        params.put("enSeqNos", enSeqNos);
        params.put("remark", remark);
        restTemplate.postForObject(ServiceApplication.SETTLE_SERVICE_NAME + "/OutflowFundsCheckManagement/remarkSet", params,
            String.class);
    }

    public Long selectSysFlowOnWorkflow(List<String> bizCodeList) {
        Map<String, Object> params = new HashMap<>();
        params.put("bizCodeList", bizCodeList);
        return restTemplate.postForObject(ServiceApplication.SETTLE_SERVICE_NAME + "/OutflowFundsCheckManagement/selectSysFlowOnWorkflow", params,
                Long.class);
    }
}
