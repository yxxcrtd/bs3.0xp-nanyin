package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSalerInstErrodataWithBLOBs;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSalerInstErrodataMapper {
    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(ChbSalerInstErrodataWithBLOBs record);

    int updateByWorkflowId(ChbSalerInstErrodataWithBLOBs record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(ChbSalerInstErrodataWithBLOBs record);

    int insertSelective(ChbSalerInstErrodataWithBLOBs record);

    int insertBasicByBasicInfo(ChbSalerInstErrodataWithBLOBs record);
}