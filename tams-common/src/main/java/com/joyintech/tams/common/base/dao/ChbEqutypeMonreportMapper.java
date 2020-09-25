package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbEqutypeMonreport;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbEqutypeMonreportMapper {
    int deleteByPrimaryKey(String seqno);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbEqutypeMonreport record);

    int insertSelective(ChbEqutypeMonreport record);

    int insertBasicByBasicInfo(ChbEqutypeMonreport record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbEqutypeMonreport selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(ChbEqutypeMonreport record);

    int updateByWorkflowIdSelective(ChbEqutypeMonreport record);

    int updateByWorkflowId(ChbEqutypeMonreport record);

    int updateByPrimaryKey(ChbEqutypeMonreport record);
}