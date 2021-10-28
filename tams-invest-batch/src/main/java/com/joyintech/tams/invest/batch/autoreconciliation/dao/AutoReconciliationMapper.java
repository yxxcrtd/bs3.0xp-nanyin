package com.joyintech.tams.invest.batch.autoreconciliation.dao;

import com.joyintech.tams.invest.common.reconciliation.dto.DayEndAccountDaichuliDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AutoReconciliationMapper {
    /**
     * 获取待处理的对账数据
     * @param param param
     * @return
     */
    List<DayEndAccountDaichuliDto> queryAccountTodoList(Map<String, Object> param);
}
