package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSysBatchLog;
import com.joyintech.tams.common.base.entity.ChbSysBatchLogKey;
import com.joyintech.tams.common.base.entity.ChbSysBatchLogWithBLOBs;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSysBatchLogMapper {
    int deleteByPrimaryKey(ChbSysBatchLogKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbSysBatchLogWithBLOBs record);

    int insertSelective(ChbSysBatchLogWithBLOBs record);

    int insertBasicByBasicInfo(ChbSysBatchLogWithBLOBs record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbSysBatchLogWithBLOBs selectByPrimaryKey(ChbSysBatchLogKey key);

    int updateByPrimaryKeySelective(ChbSysBatchLogWithBLOBs record);

    int updateByWorkflowIdSelective(ChbSysBatchLogWithBLOBs record);

    int updateByWorkflowId(ChbSysBatchLogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ChbSysBatchLogWithBLOBs record);

    int updateByPrimaryKey(ChbSysBatchLog record);
}