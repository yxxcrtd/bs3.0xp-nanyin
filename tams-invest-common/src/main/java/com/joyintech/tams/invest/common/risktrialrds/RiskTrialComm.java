package com.joyintech.tams.invest.common.risktrialrds;

import com.joyintech.tams.invest.common.risktrialrds.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.common.risktrialrds.dto.Task;

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
     * @param Map<String,Object>,TaskQueue,String
     * @return List<RiskTrialRetDto>
     * @date 2018/9/28
     */
    RiskTrialRetDto afterRiskTrialDeal(Map<String, Object> mapIndivid, Task taskQueue, String SupFrequency);
    /**
     * 查询流程是撤销交易还是正常交易提交
     * @param seqNo
     * @return
     */
    String selectWorkflowActionType(String seqNo);
}
