package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbEqudet;
import com.joyintech.tams.common.base.entity.ChbEqudetKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbEqudetMapper {
    int deleteByPrimaryKey(ChbEqudetKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbEqudet record);

    int insertSelective(ChbEqudet record);

    int insertBasicByBasicInfo(ChbEqudet record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbEqudet selectByPrimaryKey(ChbEqudetKey key);

    int updateByPrimaryKeySelective(ChbEqudet record);

    int updateByWorkflowIdSelective(ChbEqudet record);

    int updateByWorkflowId(ChbEqudet record);

    int updateByPrimaryKey(ChbEqudet record);
}