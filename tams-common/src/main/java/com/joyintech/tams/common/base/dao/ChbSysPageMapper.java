package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSysPage;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSysPageMapper {
    int deleteByPrimaryKey(String pageId);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbSysPage record);

    int insertSelective(ChbSysPage record);

    int insertBasicByBasicInfo(ChbSysPage record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbSysPage selectByPrimaryKey(String pageId);

    int updateByPrimaryKeySelective(ChbSysPage record);

    int updateByWorkflowIdSelective(ChbSysPage record);

    int updateByWorkflowId(ChbSysPage record);

    int updateByPrimaryKey(ChbSysPage record);
}