package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSalerStlDeaErrodataWithBLOBs;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSalerStlDeaErrodataMapper {
    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(ChbSalerStlDeaErrodataWithBLOBs record);

    int updateByWorkflowId(ChbSalerStlDeaErrodataWithBLOBs record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(ChbSalerStlDeaErrodataWithBLOBs record);

    int insertSelective(ChbSalerStlDeaErrodataWithBLOBs record);

    int insertBasicByBasicInfo(ChbSalerStlDeaErrodataWithBLOBs record);
}