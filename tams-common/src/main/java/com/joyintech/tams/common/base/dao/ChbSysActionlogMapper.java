package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSysActionlog;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSysActionlogMapper {
    int deleteByPrimaryKey(String logId);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbSysActionlog record);

    int insertSelective(ChbSysActionlog record);

    int insertBasicByBasicInfo(ChbSysActionlog record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbSysActionlog selectByPrimaryKey(String logId);

    int updateByPrimaryKeySelective(ChbSysActionlog record);

    int updateByWorkflowIdSelective(ChbSysActionlog record);

    int updateByWorkflowId(ChbSysActionlog record);

    int updateByPrimaryKeyWithBLOBs(ChbSysActionlog record);

    int updateByPrimaryKey(ChbSysActionlog record);
}