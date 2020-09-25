package com.joyintech.tams.invest.common.risktrial;

import java.util.Map;
import java.util.List;

import com.joyintech.tams.invest.common.risktrial.dto.InvestTradeTrialInput;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.common.risktrial.service.TaskQueue;


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
     * 实时监控处理
     * 
     * @param inputDto
     * @return InvestTradeTrialOutput
     */
    List<RiskTrialRetDto> actualRiskTrialDeal(InvestTradeTrialInput inputDto) throws InterruptedException;

    /**
     * 事后监控处理
     * @param taskQueue
     * @date 2018/9/28
     */
    void afterRiskTrialDeal(TaskQueue taskQueue);
    /**
     * 流程中的实体获取多笔交易是否超审批线
     * @param riskMap  key值存的是workflowId
     * @param <T>  从画面传过来的实体
     * @return
     */
    <T extends RiskTrialRetDto> Map<String,Object> getRiskExceedApproveByEntity(Map<String,List<T>> riskMap);

    /**
     * 按工作流代码获取多笔交易是否超审批线
     * @param workflowId
     * @return
     */
    Map<String,Object> getRiskExceedApproveByFlowId(String... workflowId);

    /**
     * 按交易代码获取多笔交易是否超审批线
     * @param t
     * @param tradeId
     * @param <T>
     * @return
     */
    <T extends RiskTrialRetDto> Map<String,Object> getRiskExceedApproveByTradeId(Class<T> t,String... tradeId);
}
