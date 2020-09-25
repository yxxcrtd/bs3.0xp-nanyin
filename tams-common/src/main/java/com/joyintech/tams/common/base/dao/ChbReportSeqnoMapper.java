package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbReportSeqno;
import com.joyintech.tams.common.base.entity.ChbReportSeqnoKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbReportSeqnoMapper {
    int deleteByPrimaryKey(ChbReportSeqnoKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbReportSeqno record);

    int insertSelective(ChbReportSeqno record);

    int insertBasicByBasicInfo(ChbReportSeqno record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbReportSeqno selectByPrimaryKey(ChbReportSeqnoKey key);

    int updateByPrimaryKeySelective(ChbReportSeqno record);

    int updateByWorkflowIdSelective(ChbReportSeqno record);

    int updateByWorkflowId(ChbReportSeqno record);

    int updateByPrimaryKey(ChbReportSeqno record);
}