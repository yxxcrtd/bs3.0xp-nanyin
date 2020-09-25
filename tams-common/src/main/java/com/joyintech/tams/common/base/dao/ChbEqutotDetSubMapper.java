package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbEqutotDetSub;
import com.joyintech.tams.common.base.entity.ChbEqutotDetSubKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbEqutotDetSubMapper {
    int deleteByPrimaryKey(ChbEqutotDetSubKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbEqutotDetSub record);

    int insertSelective(ChbEqutotDetSub record);

    int insertBasicByBasicInfo(ChbEqutotDetSub record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbEqutotDetSub selectByPrimaryKey(ChbEqutotDetSubKey key);

    int updateByPrimaryKeySelective(ChbEqutotDetSub record);

    int updateByWorkflowIdSelective(ChbEqutotDetSub record);

    int updateByWorkflowId(ChbEqutotDetSub record);

    int updateByPrimaryKey(ChbEqutotDetSub record);
}