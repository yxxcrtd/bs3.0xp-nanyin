package com.joyintech.service.settle.balance.service;

import static com.joyintech.tams.invest.common.util.SettleCommonUtil.setParams;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.settle.balance.dao.InflowFundsCheckManagementMapper;
import com.joyintech.service.settle.balance.dao.OutflowFundsCheckManagementMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.settle.balance.BaseOutflowFundsSystemFlowDto;
import com.joyintech.tams.invest.common.dto.settle.balance.PortfolioUnmatchedInfo;

/**
 * @program: tams-invest-parent
 * @description:
 * @author: 强成西
 * @create: 2018-12-03 13:59
 **/

@Service
public class InflowFundsCheckManagementService extends BaseService<BaseOutflowFundsSystemFlowDto> {

    @Autowired
    private InflowFundsCheckManagementMapper inflowFundsCheckManagementMapper;

    @Autowired
    private OutflowFundsCheckManagementMapper outflowFundsCheckManagementMapper;

    public List<PortfolioUnmatchedInfo> findPortfolioUnmatched(Map<String, Object> params) {
        setParams(params);
        if (StringUtil.isNotEmpty(params.get("acctNo"))) {
            params.put("acctNo", StringUtil.splitToList(",", (String)params.get("acctNo")));
        }
        return inflowFundsCheckManagementMapper.findPortfolioUnmatched(params);
    }

    public Page<BaseOutflowFundsSystemFlowDto> findSysPage(int page, int length, Map<String, Object> params) {
        setParams(params);
        if (StringUtil.isNotEmpty(params.get("portfolioId"))) {
            params.put("portfolioId", StringUtil.splitToList(",", (String)params.get("portfolioId")));
        }
        if (StringUtil.isNotEmpty(params.get("finprodId"))) {
            params.put("finprodId", StringUtil.splitToList(",", (String)params.get("finprodId")));
        }
        if (StringUtil.isNotEmpty(params.get("statusValid"))) {
            params.put("statusValid", StringUtil.splitToList(",", (String)params.get("statusValid")));
        }
        if (StringUtil.isNotEmpty(params.get("paBizType"))) {
            params.put("paBizType", StringUtil.splitToList(",", (String)params.get("paBizType")));
        }
        params.put("sysOrEn", BaseOutflowFundsSystemFlowDto.SYS);
        return queryForPageForSelectReturn(page, length, () -> inflowFundsCheckManagementMapper.findSysPage(params));
    }

    public Page<BaseOutflowFundsSystemFlowDto> findEnPage(int page, int length, Map<String, Object> params) {
        params.put("NO", DictCodeList.是否_否.getValue());
        params.put("YES", DictCodeList.是否_是.getValue());
        params.put("sysOrEn", BaseOutflowFundsSystemFlowDto.EN);
        params.put("CD_FLOW_FLAG_01", DictCodeList.托管流水借贷方向_贷.getValue());
        params.put("TRADE_PLACE_01", DictCodeListExt1.交易场所_银行间.getValue());
        return queryForPageForSelectReturn(page, length, () -> outflowFundsCheckManagementMapper.findEnPage(params));
    }

}
