package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttInddataLmh;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttInddataLmhMapper {
    int deleteByPrimaryKey(String seqNo);

    int deleteByWorkflowId(String workflowId);

    int insert(SttInddataLmh record);

    int insertSelective(SttInddataLmh record);

    int insertBasicByBasicInfo(SttInddataLmh record);

    int insertBasicByWorkflowId(Map paraMap);

    SttInddataLmh selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(SttInddataLmh record);

    int updateByWorkflowIdSelective(SttInddataLmh record);

    int updateByWorkflowId(SttInddataLmh record);

    int updateByPrimaryKey(SttInddataLmh record);
}