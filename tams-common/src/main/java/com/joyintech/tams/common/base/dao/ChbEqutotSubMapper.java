package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbEqutotSub;
import com.joyintech.tams.common.base.entity.ChbEqutotSubKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbEqutotSubMapper {
    int deleteByPrimaryKey(ChbEqutotSubKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbEqutotSub record);

    int insertSelective(ChbEqutotSub record);

    int insertBasicByBasicInfo(ChbEqutotSub record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbEqutotSub selectByPrimaryKey(ChbEqutotSubKey key);

    int updateByPrimaryKeySelective(ChbEqutotSub record);

    int updateByWorkflowIdSelective(ChbEqutotSub record);

    int updateByWorkflowId(ChbEqutotSub record);

    int updateByPrimaryKey(ChbEqutotSub record);
}