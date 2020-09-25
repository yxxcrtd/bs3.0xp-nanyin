package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttStatElement;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttStatElementMapper {
    int deleteByPrimaryKey(String seqNo);

    int deleteByWorkflowId(String workflowId);

    int insert(SttStatElement record);

    int insertSelective(SttStatElement record);

    int insertBasicByBasicInfo(SttStatElement record);

    int insertBasicByWorkflowId(Map paraMap);

    SttStatElement selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(SttStatElement record);

    int updateByWorkflowIdSelective(SttStatElement record);

    int updateByWorkflowId(SttStatElement record);

    int updateByPrimaryKey(SttStatElement record);
}