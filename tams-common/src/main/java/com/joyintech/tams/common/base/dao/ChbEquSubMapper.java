package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbEquSub;
import com.joyintech.tams.common.base.entity.ChbEquSubKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbEquSubMapper {
    int deleteByPrimaryKey(ChbEquSubKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbEquSub record);

    int insertSelective(ChbEquSub record);

    int insertBasicByBasicInfo(ChbEquSub record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbEquSub selectByPrimaryKey(ChbEquSubKey key);

    int updateByPrimaryKeySelective(ChbEquSub record);

    int updateByWorkflowIdSelective(ChbEquSub record);

    int updateByWorkflowId(ChbEquSub record);

    int updateByPrimaryKey(ChbEquSub record);
}