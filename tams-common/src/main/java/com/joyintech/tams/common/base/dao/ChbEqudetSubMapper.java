package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbEqudetSub;
import com.joyintech.tams.common.base.entity.ChbEqudetSubKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbEqudetSubMapper {
    int deleteByPrimaryKey(ChbEqudetSubKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbEqudetSub record);

    int insertSelective(ChbEqudetSub record);

    int insertBasicByBasicInfo(ChbEqudetSub record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbEqudetSub selectByPrimaryKey(ChbEqudetSubKey key);

    int updateByPrimaryKeySelective(ChbEqudetSub record);

    int updateByWorkflowIdSelective(ChbEqudetSub record);

    int updateByWorkflowId(ChbEqudetSub record);

    int updateByPrimaryKey(ChbEqudetSub record);
}