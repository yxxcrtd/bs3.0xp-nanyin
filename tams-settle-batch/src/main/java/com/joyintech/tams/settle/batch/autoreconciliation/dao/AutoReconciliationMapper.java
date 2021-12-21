package com.joyintech.tams.settle.batch.autoreconciliation.dao;

import com.joyintech.tams.invest.common.reconciliation.dto.DayEndAccountDaichuliDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AutoReconciliationMapper {
    /**
     * 获取待自动对账的对账数据
     * @param param param
     * @return
     */
    List<DayEndAccountDaichuliDto> queryAccountTodoList(Map<String, Object> param);

    /**
     * 获取待手工对账的对账数据
     * @param param param
     * @return
     */
    List<DayEndAccountDaichuliDto> queryManualAccountTodoList(Map<String, Object> param);
}
