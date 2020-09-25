package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSubmitStatus;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSubmitStatusMapper {
    int deleteByPrimaryKey(String seqno);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbSubmitStatus record);

    int insertSelective(ChbSubmitStatus record);

    int insertBasicByBasicInfo(ChbSubmitStatus record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbSubmitStatus selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(ChbSubmitStatus record);

    int updateByWorkflowIdSelective(ChbSubmitStatus record);

    int updateByWorkflowId(ChbSubmitStatus record);

    int updateByPrimaryKey(ChbSubmitStatus record);
}