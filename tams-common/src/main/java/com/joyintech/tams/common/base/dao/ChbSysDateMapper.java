package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSysDate;
import java.util.Date;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSysDateMapper {
    int deleteByPrimaryKey(Date curDate);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbSysDate record);

    int insertSelective(ChbSysDate record);

    int insertBasicByBasicInfo(ChbSysDate record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbSysDate selectByPrimaryKey(Date curDate);

    int updateByPrimaryKeySelective(ChbSysDate record);

    int updateByWorkflowIdSelective(ChbSysDate record);

    int updateByWorkflowId(ChbSysDate record);

    int updateByPrimaryKey(ChbSysDate record);
}