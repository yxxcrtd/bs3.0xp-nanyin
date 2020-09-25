package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbInfoReport;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbInfoReportMapper {
    int deleteByPrimaryKey(String reportNo);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbInfoReport record);

    int insertSelective(ChbInfoReport record);

    int insertBasicByBasicInfo(ChbInfoReport record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbInfoReport selectByPrimaryKey(String reportNo);

    int updateByPrimaryKeySelective(ChbInfoReport record);

    int updateByWorkflowIdSelective(ChbInfoReport record);

    int updateByWorkflowId(ChbInfoReport record);

    int updateByPrimaryKey(ChbInfoReport record);
}