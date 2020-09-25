package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbFileReadMonitorInfo;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbFileReadMonitorInfoMapper {
    int deleteByPrimaryKey(String handleSeqno);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbFileReadMonitorInfo record);

    int insertSelective(ChbFileReadMonitorInfo record);

    int insertBasicByBasicInfo(ChbFileReadMonitorInfo record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbFileReadMonitorInfo selectByPrimaryKey(String handleSeqno);

    int updateByPrimaryKeySelective(ChbFileReadMonitorInfo record);

    int updateByWorkflowIdSelective(ChbFileReadMonitorInfo record);

    int updateByWorkflowId(ChbFileReadMonitorInfo record);

    int updateByPrimaryKeyWithBLOBs(ChbFileReadMonitorInfo record);

    int updateByPrimaryKey(ChbFileReadMonitorInfo record);
}