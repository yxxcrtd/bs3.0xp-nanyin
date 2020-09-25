package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.AccpoolAssetInfo;
import com.joyintech.tams.common.base.entity.AccpoolAssetInfoKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface AccpoolAssetInfoMapper {
    int deleteByPrimaryKey(AccpoolAssetInfoKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(AccpoolAssetInfo record);

    int updateByWorkflowId(AccpoolAssetInfo record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(AccpoolAssetInfo record);

    int insertSelective(AccpoolAssetInfo record);

    int insertBasicByBasicInfo(AccpoolAssetInfo record);

    AccpoolAssetInfo selectByPrimaryKey(AccpoolAssetInfoKey key);

    int updateByPrimaryKeySelective(AccpoolAssetInfo record);

    int updateByPrimaryKey(AccpoolAssetInfo record);
}