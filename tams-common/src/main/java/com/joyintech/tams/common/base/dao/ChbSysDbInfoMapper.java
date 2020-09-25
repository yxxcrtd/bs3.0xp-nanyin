package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSysDbInfo;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSysDbInfoMapper {
    int deleteByPrimaryKey(String isLock);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbSysDbInfo record);

    int insertSelective(ChbSysDbInfo record);

    int insertBasicByBasicInfo(ChbSysDbInfo record);

    int insertBasicByWorkflowId(Map paraMap);

    int updateByWorkflowIdSelective(ChbSysDbInfo record);

    int updateByWorkflowId(ChbSysDbInfo record);
}