package com.joyintech.tams.invest.prebatch.dao;

import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.invest.prebatch.dto.SysPrebatchResult001Dto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SysPrebatchResult001Mapper {

    List<SysPrebatchResult001Dto> findPage(Map<String, Object> pageParam);

    void deleteByDateAndInstCodeLs(Map<String, Object> pageParam);

    void deleteByDate(Map<String, Object> pageParam);

    void insertBatchExecList(@Param("insertDataList")List<SysBatchgrpExecInfo> infoList);

    void insertBatchPrebatchResultList(@Param("insertDataList")List<SysPrebatchResult001Dto> resultList);

    void deleteDetailList(Map<String, Object> param);
}
