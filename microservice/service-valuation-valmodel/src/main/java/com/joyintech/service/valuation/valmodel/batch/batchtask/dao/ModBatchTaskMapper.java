package com.joyintech.service.valuation.valmodel.batch.batchtask.dao;

import com.joyintech.framework.base.entity.SysBatchGrpPrdlink;
import com.joyintech.framework.base.entity.SysBatchJob;
import com.joyintech.service.valuation.valmodel.batch.batchtask.dto.BatchAssetInfoDto;
import com.joyintech.service.valuation.valmodel.batch.batchtask.dto.BatchExecutingStatusDto;
import com.joyintech.service.valuation.valmodel.batch.batchtask.dto.BatchTaskDto;
import com.joyintech.service.valuation.valmodel.batch.batchtask.dto.ModValBatchAssetInfoDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author 王波
 * @date 2021-07-13
 * @see ModBatchTaskMapper
 * @since 1.0
 */
@Repository
public interface ModBatchTaskMapper {
    List<BatchTaskDto> getBatchTask(Map<String, Object> params);

    void batchInsertBatchGroup(List<SysBatchJob> jobList);

    void batchInsertProdGroup(List<SysBatchGrpPrdlink> products);

    List<BatchExecutingStatusDto> findBatchExeDetail(Map<String, Object> params);

    int checkIsRunning(Map<String, Object> params);

    Integer cleanBatchParams();

    List<BatchAssetInfoDto> getAssetInfo(Map<String, Object> param);

    List<BatchAssetInfoDto> getAssetInfoImpairment(Map<String, Object> param);

    List<BatchAssetInfoDto> getLossKpmgAssetInfo(Map<String, Object> param);

    List<BatchAssetInfoDto> getLossPwcAssetInfo(Map<String, Object> param);

    List<BatchAssetInfoDto> getValAssetInfo(Map<String, Object> param);






}
