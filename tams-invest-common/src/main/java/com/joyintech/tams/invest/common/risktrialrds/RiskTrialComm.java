package com.joyintech.tams.invest.common.risktrialrds;

import com.joyintech.tams.invest.common.risktrialrds.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.common.risktrialrds.dto.Task;

import java.util.List;
import java.util.Map;


/**
 * 监督指标入口接口
 * 〈功能详细描述〉
 * @author 裴家俊
 * @version 1.0 2018年11月28日
 * @see RiskTrialComm
 * @since 1.0
 */
public interface RiskTrialComm {

    /**
     * 事后监控处理
     * @param mapIndivid
     * @param taskQueue
     * @param SupFrequency
     * @return
     */
    RiskTrialRetDto afterRiskTrialDeal(Map<String, Object> mapIndivid, Task taskQueue, String SupFrequency);

    /**
     * 查询流程是撤销交易还是正常交易提交
     * @param seqNo
     * @return
     */
    String selectWorkflowActionType(String seqNo);


}
