package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbAssetInfoErrodata;
import com.joyintech.tams.common.base.entity.ChbAssetInfoErrodataWithBLOBs;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbAssetInfoErrodataMapper {
    int deleteByPrimaryKey(String datauuid);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(ChbAssetInfoErrodataWithBLOBs record);

    int updateByWorkflowId(ChbAssetInfoErrodataWithBLOBs record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(ChbAssetInfoErrodataWithBLOBs record);

    int insertSelective(ChbAssetInfoErrodataWithBLOBs record);

    int insertBasicByBasicInfo(ChbAssetInfoErrodataWithBLOBs record);

    ChbAssetInfoErrodataWithBLOBs selectByPrimaryKey(String datauuid);

    int updateByPrimaryKeySelective(ChbAssetInfoErrodataWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ChbAssetInfoErrodataWithBLOBs record);

    int updateByPrimaryKey(ChbAssetInfoErrodata record);
}