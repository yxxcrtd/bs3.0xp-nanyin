package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttStressSuit;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttStressSuitMapper {
    int deleteByPrimaryKey(String seqNo);

    int deleteByWorkflowId(String workflowId);

    int insert(SttStressSuit record);

    int insertSelective(SttStressSuit record);

    int insertBasicByBasicInfo(SttStressSuit record);

    int insertBasicByWorkflowId(Map paraMap);

    SttStressSuit selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(SttStressSuit record);

    int updateByWorkflowIdSelective(SttStressSuit record);

    int updateByWorkflowId(SttStressSuit record);

    int updateByPrimaryKey(SttStressSuit record);
}