package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSysBatchInfo;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSysBatchInfoMapper {
    int deleteByPrimaryKey(String batchId);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbSysBatchInfo record);

    int insertSelective(ChbSysBatchInfo record);

    int insertBasicByBasicInfo(ChbSysBatchInfo record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbSysBatchInfo selectByPrimaryKey(String batchId);

    int updateByPrimaryKeySelective(ChbSysBatchInfo record);

    int updateByWorkflowIdSelective(ChbSysBatchInfo record);

    int updateByWorkflowId(ChbSysBatchInfo record);

    int updateByPrimaryKeyWithBLOBs(ChbSysBatchInfo record);

    int updateByPrimaryKey(ChbSysBatchInfo record);
}