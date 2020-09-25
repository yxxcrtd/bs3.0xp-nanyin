package com.joyintech.tams.disclosure.busicommon.infodisclosure.batchUtil.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.base.entity.SysBatchGrpPrdlink;
import com.joyintech.framework.base.entity.SysBatchJob;
import com.joyintech.framework.base.entity.SysBatchTask;

@Repository
public interface SysBatchStartMapper {
    List<SysBatchTask> findAllTask(Map<String,Object> pageParam);

    void batchInsertBatchGroup(List<SysBatchJob> jobList);

    void batchInsertProdGroup(List<SysBatchGrpPrdlink> products);

    void updateExeDetailToUnr(@Param("detailSeqNo") String detailSeqNo, @Param("userName")  String userName);

    void deleteAllBatchInfo(String detailSeqNo);
}
