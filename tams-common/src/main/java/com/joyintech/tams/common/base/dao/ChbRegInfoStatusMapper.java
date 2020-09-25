package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbRegInfoStatus;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbRegInfoStatusMapper {
    int deleteByPrimaryKey(String seqno);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbRegInfoStatus record);

    int insertSelective(ChbRegInfoStatus record);

    int insertBasicByBasicInfo(ChbRegInfoStatus record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbRegInfoStatus selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(ChbRegInfoStatus record);

    int updateByWorkflowIdSelective(ChbRegInfoStatus record);

    int updateByWorkflowId(ChbRegInfoStatus record);

    int updateByPrimaryKey(ChbRegInfoStatus record);
}