package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbWflBusinessModule;
import com.joyintech.tams.common.base.entity.ChbWflBusinessModuleKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbWflBusinessModuleMapper {
    int deleteByPrimaryKey(ChbWflBusinessModuleKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbWflBusinessModule record);

    int insertSelective(ChbWflBusinessModule record);

    int insertBasicByBasicInfo(ChbWflBusinessModule record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbWflBusinessModule selectByPrimaryKey(ChbWflBusinessModuleKey key);

    int updateByPrimaryKeySelective(ChbWflBusinessModule record);

    int updateByWorkflowIdSelective(ChbWflBusinessModule record);

    int updateByWorkflowId(ChbWflBusinessModule record);

    int updateByPrimaryKey(ChbWflBusinessModule record);
}