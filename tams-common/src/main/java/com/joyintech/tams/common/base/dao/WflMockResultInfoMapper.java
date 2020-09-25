package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.WflMockResultInfo;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface WflMockResultInfoMapper {
    int deleteByPrimaryKey(String seqNo);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflMockResultInfo record);

    int updateByWorkflowId(WflMockResultInfo record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflMockResultInfo record);

    int insertSelective(WflMockResultInfo record);

    int insertBasicByBasicInfo(WflMockResultInfo record);

    WflMockResultInfo selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(WflMockResultInfo record);

    int updateByPrimaryKey(WflMockResultInfo record);
}