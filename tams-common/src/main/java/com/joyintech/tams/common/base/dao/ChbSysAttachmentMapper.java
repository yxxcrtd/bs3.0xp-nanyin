package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSysAttachment;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSysAttachmentMapper {
    int deleteByPrimaryKey(String seqno);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbSysAttachment record);

    int insertSelective(ChbSysAttachment record);

    int insertBasicByBasicInfo(ChbSysAttachment record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbSysAttachment selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(ChbSysAttachment record);

    int updateByWorkflowIdSelective(ChbSysAttachment record);

    int updateByWorkflowId(ChbSysAttachment record);

    int updateByPrimaryKey(ChbSysAttachment record);
}