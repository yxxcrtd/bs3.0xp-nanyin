package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.WflEtrEntrustAdd;
import com.joyintech.tams.common.base.entity.WflEtrEntrustAddKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface WflEtrEntrustAddMapper {
    int deleteByPrimaryKey(WflEtrEntrustAddKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflEtrEntrustAdd record);

    int updateByWorkflowId(WflEtrEntrustAdd record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflEtrEntrustAdd record);

    int insertSelective(WflEtrEntrustAdd record);

    int insertBasicByBasicInfo(WflEtrEntrustAdd record);

    WflEtrEntrustAdd selectByPrimaryKey(WflEtrEntrustAddKey key);

    int updateByPrimaryKeySelective(WflEtrEntrustAdd record);

    int updateByPrimaryKey(WflEtrEntrustAdd record);
}