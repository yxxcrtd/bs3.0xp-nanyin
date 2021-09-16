package com.joyintech.service.valuation.lossmodel.batchtask.dao;

import com.joyintech.framework.base.entity.SysBatchGrpPrdlink;
import com.joyintech.framework.base.entity.SysBatchJob;
import com.joyintech.service.valuation.lossmodel.batchtask.dto.BatchTaskDto;
import com.joyintech.system.batch.batchmonitor.dto.BatchExecutingStatusDto;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModBatchLogDetailDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author 王波
 * @date 2021-07-27
 * @see ModImpairementBatchTaskMapper
 * @since 1.0
 */
@Repository
public interface ModImpairementBatchTaskMapper {

    List<BatchTaskDto> getBatchTask(Map<String, Object> params);

    void batchInsertBatchGroup(List<SysBatchJob> jobList);

    void batchInsertProdGroup(List<SysBatchGrpPrdlink> products);

    List<BatchExecutingStatusDto> findBatchExeDetail(Map<String, Object> params);

    int checkIsRunning(Map<String, Object> params);

    Integer cleanBatchParams(Map<String, Object> params);

    /**
     * 查询资产维度日志
     * @param params
     * @return
     */
    List<ModBatchLogDetailDTO> selectLogsDetail(Map<String, Object> params);
}
