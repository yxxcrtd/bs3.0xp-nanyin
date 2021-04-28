package com.joyintech.tams.invest.batch.prebatchcheck.common.dao;

import com.joyintech.tams.invest.batch.prebatchcheck.common.dto.SysPrebatchResultDto;
import com.joyintech.tams.invest.batch.prebatchcheck.common.dto.SysPrebatchTaskAddDto;
import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PrebatchCheckCommonMapper {

    void updateSysPrebatchResult(SysPrebatchResultDto dto);

    SysPrebatchResultDto selectSysPrebatchResult(SysPrebatchResultDto paramdto);

    void insertBatchResultDetail(@Param("insertDataList")List<SysPrebatchResultDetail> details);

    List<String> selectAllInst();

    SysPrebatchTaskAddDto selectTaskList(@Param("taskId") String taskId);

    void insertBatchPrebatchResultList(@Param("insertDataList")List<SysPrebatchResultDto> resultList);

    void deleteResultDetailList(Map<String, Object> param);

    void deleteResultList(Map<String, Object> param);
}
