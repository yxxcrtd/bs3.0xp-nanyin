package com.joyintech.tams.invest.batch.overduesecpositionforward.dao;

import com.joyintech.tams.invest.batch.overduesecpositionforward.dto.OverdueSecPositionForwardDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 逾期资产结转批处理DAO
 */
@Repository
public interface OverdueSecPositionForwardMapper {
    /**
     * 删除应收款持仓流水
     * @param preParams 参数
     */
    void removeReceivablesPositionFlow(Map<String, Object> preParams);

    /**
     * 查询需要处理的数据
     * @param params 查询参数
     * @return 返回结果
     */
    List<OverdueSecPositionForwardDto> findPendingData(Map<String, Object> params);

    Map<String, Object> findAdjustDate(Map<String, Object> param);

    void removeSecPositionFlow(Map<String, Object> preParams);

    void removeEtrSecPositionFlow(Map<String, Object> preParams);
}
