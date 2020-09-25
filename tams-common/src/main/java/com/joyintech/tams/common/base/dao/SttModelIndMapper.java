package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttModelInd;
import com.joyintech.tams.common.base.entity.SttModelIndKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttModelIndMapper {
    int deleteByPrimaryKey(SttModelIndKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(SttModelInd record);

    int insertSelective(SttModelInd record);

    int insertBasicByBasicInfo(SttModelInd record);

    int insertBasicByWorkflowId(Map paraMap);

    SttModelInd selectByPrimaryKey(SttModelIndKey key);

    int updateByPrimaryKeySelective(SttModelInd record);

    int updateByWorkflowIdSelective(SttModelInd record);

    int updateByWorkflowId(SttModelInd record);

    int updateByPrimaryKey(SttModelInd record);
}