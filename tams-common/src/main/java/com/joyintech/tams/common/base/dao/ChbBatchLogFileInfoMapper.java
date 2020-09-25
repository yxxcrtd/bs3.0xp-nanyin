package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbBatchLogFileInfo;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbBatchLogFileInfoMapper {
    int deleteByPrimaryKey(String fileId);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbBatchLogFileInfo record);

    int insertSelective(ChbBatchLogFileInfo record);

    int insertBasicByBasicInfo(ChbBatchLogFileInfo record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbBatchLogFileInfo selectByPrimaryKey(String fileId);

    int updateByPrimaryKeySelective(ChbBatchLogFileInfo record);

    int updateByWorkflowIdSelective(ChbBatchLogFileInfo record);

    int updateByWorkflowId(ChbBatchLogFileInfo record);

    int updateByPrimaryKey(ChbBatchLogFileInfo record);
}