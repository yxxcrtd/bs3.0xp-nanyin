package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbReportCheck;
import com.joyintech.tams.common.base.entity.ChbReportCheckWithBLOBs;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbReportCheckMapper {
    int deleteByPrimaryKey(String checkSeqno);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbReportCheckWithBLOBs record);

    int insertSelective(ChbReportCheckWithBLOBs record);

    int insertBasicByBasicInfo(ChbReportCheckWithBLOBs record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbReportCheckWithBLOBs selectByPrimaryKey(String checkSeqno);

    int updateByPrimaryKeySelective(ChbReportCheckWithBLOBs record);

    int updateByWorkflowIdSelective(ChbReportCheckWithBLOBs record);

    int updateByWorkflowId(ChbReportCheckWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ChbReportCheckWithBLOBs record);

    int updateByPrimaryKey(ChbReportCheck record);
}