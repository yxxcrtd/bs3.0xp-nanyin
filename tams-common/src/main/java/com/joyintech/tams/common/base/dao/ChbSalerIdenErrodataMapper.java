package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSalerIdenErrodataWithBLOBs;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSalerIdenErrodataMapper {
    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(ChbSalerIdenErrodataWithBLOBs record);

    int updateByWorkflowId(ChbSalerIdenErrodataWithBLOBs record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(ChbSalerIdenErrodataWithBLOBs record);

    int insertSelective(ChbSalerIdenErrodataWithBLOBs record);

    int insertBasicByBasicInfo(ChbSalerIdenErrodataWithBLOBs record);
}