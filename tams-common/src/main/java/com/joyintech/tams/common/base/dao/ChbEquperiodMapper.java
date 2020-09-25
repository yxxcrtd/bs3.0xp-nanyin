package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbEquperiod;
import com.joyintech.tams.common.base.entity.ChbEquperiodKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbEquperiodMapper {
    int deleteByPrimaryKey(ChbEquperiodKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbEquperiod record);

    int insertSelective(ChbEquperiod record);

    int insertBasicByBasicInfo(ChbEquperiod record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbEquperiod selectByPrimaryKey(ChbEquperiodKey key);

    int updateByPrimaryKeySelective(ChbEquperiod record);

    int updateByWorkflowIdSelective(ChbEquperiod record);

    int updateByWorkflowId(ChbEquperiod record);

    int updateByPrimaryKey(ChbEquperiod record);
}