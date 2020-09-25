package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbAssetSubStatus;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbAssetSubStatusMapper {
    int deleteByWorkflowId(String workflowId);

    int insert(ChbAssetSubStatus record);

    int insertSelective(ChbAssetSubStatus record);

    int insertBasicByBasicInfo(ChbAssetSubStatus record);

    int insertBasicByWorkflowId(Map paraMap);

    int updateByWorkflowIdSelective(ChbAssetSubStatus record);

    int updateByWorkflowId(ChbAssetSubStatus record);
}