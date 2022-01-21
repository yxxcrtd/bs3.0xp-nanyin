package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSalerRewPenaErrodataWithBLOBs;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSalerRewPenaErrodataMapper {
    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(ChbSalerRewPenaErrodataWithBLOBs record);

    int updateByWorkflowId(ChbSalerRewPenaErrodataWithBLOBs record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(ChbSalerRewPenaErrodataWithBLOBs record);

    int insertSelective(ChbSalerRewPenaErrodataWithBLOBs record);

    int insertBasicByBasicInfo(ChbSalerRewPenaErrodataWithBLOBs record);
}