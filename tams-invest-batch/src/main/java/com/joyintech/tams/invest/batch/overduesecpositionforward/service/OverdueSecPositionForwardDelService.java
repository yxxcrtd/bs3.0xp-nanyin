package com.joyintech.tams.invest.batch.overduesecpositionforward.service;

import com.joyintech.tams.invest.batch.overduesecpositionforward.dao.OverdueSecPositionForwardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 逾期资产结转批处理 (具体删除逻辑处理service)
 */
@Service
public class OverdueSecPositionForwardDelService {
    /**
     * 逾期资产结转DAO
     */
    @Autowired
    private OverdueSecPositionForwardMapper overdueSecPositionForwardMapper;

    /**
     * 删除应收款持仓流水
     * @param preParams 参数
     */
    public void removeReceivablesPositionFlow(Map<String, Object> preParams) {
        overdueSecPositionForwardMapper.removeReceivablesPositionFlow(preParams);
    }
    /**
     * 删除组合持仓流水
     * @param preParams
     */
    public void removeSecPositionFlow(Map<String, Object> preParams) {
        overdueSecPositionForwardMapper.removeSecPositionFlow(preParams);
    }
    /**
     * 删除组合委托持仓流水
     * @param preParams
     */
    public void removeEtrSecPositionFlow(Map<String, Object> preParams) {
        overdueSecPositionForwardMapper.removeEtrSecPositionFlow(preParams);
    }
}
