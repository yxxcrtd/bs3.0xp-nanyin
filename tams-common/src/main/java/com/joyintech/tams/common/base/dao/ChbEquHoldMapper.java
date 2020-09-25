package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbEquHold;
import com.joyintech.tams.common.base.entity.ChbEquHoldKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbEquHoldMapper {
    int deleteByPrimaryKey(ChbEquHoldKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbEquHold record);

    int insertSelective(ChbEquHold record);

    int insertBasicByBasicInfo(ChbEquHold record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbEquHold selectByPrimaryKey(ChbEquHoldKey key);

    int updateByPrimaryKeySelective(ChbEquHold record);

    int updateByWorkflowIdSelective(ChbEquHold record);

    int updateByWorkflowId(ChbEquHold record);

    int updateByPrimaryKey(ChbEquHold record);
}