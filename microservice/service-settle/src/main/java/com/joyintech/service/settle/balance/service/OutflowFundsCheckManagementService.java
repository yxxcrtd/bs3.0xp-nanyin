package com.joyintech.service.settle.balance.service;

import static com.joyintech.tams.invest.common.util.SettleCommonUtil.setParams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.settle.balance.dao.OutflowFundsCheckManagementMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.settle.balance.BaseOutflowFundsSystemFlowDto;
import com.joyintech.tams.invest.common.dto.settle.balance.OutflowManualMatchDto;
import com.joyintech.tams.invest.common.dto.settle.balance.PortfolioUnmatchedInfo;

/**
 * @program: tams-invest-parent
 * @description:
 * @author: 强成西
 * @create: 2018-12-03 13:59
 **/

@Service
public class OutflowFundsCheckManagementService extends BaseService<BaseOutflowFundsSystemFlowDto> {

    @Autowired
    private OutflowFundsCheckManagementMapper outflowFundsCheckManagementMapper;

    public Page<SelectOptionDto> findEscrowAcctNo(int page, int length, Map<String, Object> params) {
        params.put("A01", DictCodeListExt1.资金账户类型_产品托管户.getValue());
        params.put("NO", DictCodeList.是否_否.getValue());
        return queryForPageForSelectReturn(page, length, () -> outflowFundsCheckManagementMapper.findEscrowAcctNo(params));
    }

    public List<PortfolioUnmatchedInfo> findPortfolioUnmatched(Map<String, Object> params) {
        setParams(params);
        if (StringUtil.isNotEmpty(params.get("acctNo"))) {
            params.put("acctNo", StringUtil.splitToList(",", (String)params.get("acctNo")));
        }
        if (StringUtil.isNotEmpty(params.get("statusValid"))) {
            params.put("statusValid", StringUtil.splitToList(",", (String)params.get("statusValid")));
        }
        return outflowFundsCheckManagementMapper.findPortfolioUnmatched(params);
    }

    public Page<SelectOptionDto> getPortfolioId(int page, int length, Map<String, Object> param) {
        return queryForPageForSelectReturn(page, length, () -> outflowFundsCheckManagementMapper.getPortfolioId(param));
    }

    public Page<BaseOutflowFundsSystemFlowDto> findSysPage(int page, int length, Map<String, Object> params) {
        setParams(params);
        if (StringUtil.isNotEmpty(params.get("portfolioId"))) {
            params.put("portfolioId", StringUtil.splitToList(",", (String)params.get("portfolioId")));
        }
        if (StringUtil.isNotEmpty(params.get("finprodId"))) {
            params.put("finprodId", StringUtil.splitToList(",", (String)params.get("finprodId")));
        }
        if (StringUtil.isNotEmpty(params.get("paBizType"))) {
            params.put("paBizType", StringUtil.splitToList(",", (String)params.get("paBizType")));
        }
        params.put("sysOrEn", BaseOutflowFundsSystemFlowDto.SYS);
        return queryForPageForSelectReturn(page, length, () -> outflowFundsCheckManagementMapper.findSysPage(params));
    }

    public Page<BaseOutflowFundsSystemFlowDto> findEnPage(int page, int length, Map<String, Object> params) {
        params.put("NO", DictCodeList.是否_否.getValue());
        params.put("YES", DictCodeList.是否_是.getValue());
        params.put("sysOrEn", BaseOutflowFundsSystemFlowDto.EN);
        params.put("CD_FLOW_FLAG_01", DictCodeList.托管流水借贷方向_借.getValue());
        params.put("TRADE_PLACE_01", DictCodeListExt1.交易场所_银行间.getValue());
        return queryForPageForSelectReturn(page, length, () -> outflowFundsCheckManagementMapper.findEnPage(params));
    }

    public Long selectSysFlowCountByTradeId(List<String> tradeIdList) {
        return outflowFundsCheckManagementMapper.selectSysFlowCountByTradeId(tradeIdList);
    }

    public Long selectEnFlowCountByTradeId(List<String> tradeIdList) {
        return outflowFundsCheckManagementMapper.selectEnFlowCountByTradeId(tradeIdList);
    }

    public OutflowManualMatchDto selectMatchDtoForCancel(Map<String, Object> params) {
        params.put("SYS", BaseOutflowFundsSystemFlowDto.SYS);
        params.put("EN", BaseOutflowFundsSystemFlowDto.EN);
        OutflowManualMatchDto result = outflowFundsCheckManagementMapper.findSysFlowFromMatch(params);
        List<BaseOutflowFundsSystemFlowDto> sysFlow = result.getSysFlow();
        List<BaseOutflowFundsSystemFlowDto> enFlow = result.getEnFlow();
        List<BaseOutflowFundsSystemFlowDto> newSysFlow = new ArrayList<>();
        List<BaseOutflowFundsSystemFlowDto> newEnFlow = new ArrayList<>();
        if (sysFlow != null) {
            newSysFlow = sysFlow.stream().filter(
                baseOutflowFundsSystemFlowDto -> StringUtil.isNotEmpty(baseOutflowFundsSystemFlowDto.getSysSeqNo())).collect(
                    Collectors.toList());
        }
        if (enFlow != null) {
            newEnFlow = enFlow.stream().filter(
                baseOutflowFundsSystemFlowDto -> StringUtil.isNotEmpty(baseOutflowFundsSystemFlowDto.getEnSeqNo())).collect(
                    Collectors.toList());
        }
        result.setSysFlow(newSysFlow);
        result.setEnFlow(newEnFlow);
        return result;
    }

    public String cancelMatch(String groupId) {
        outflowFundsCheckManagementMapper.cancelMatch(groupId);
        return Boolean.TRUE.toString();
    }

    public String remarkSet(Map<String, Object> params) {
        outflowFundsCheckManagementMapper.remarkSet(params);
        return Boolean.TRUE.toString();
    }

    public long selectSysFlowOnWorkflow(Map<String, Object> params) {
        return outflowFundsCheckManagementMapper.selectSysFlowOnWorkflow(params);
    }
}
