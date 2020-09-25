package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttFactorExt;
import com.joyintech.tams.common.base.entity.SttFactorExtKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttFactorExtMapper {
    int deleteByPrimaryKey(SttFactorExtKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(SttFactorExt record);

    int insertSelective(SttFactorExt record);

    int insertBasicByBasicInfo(SttFactorExt record);

    int insertBasicByWorkflowId(Map paraMap);

    SttFactorExt selectByPrimaryKey(SttFactorExtKey key);

    int updateByPrimaryKeySelective(SttFactorExt record);

    int updateByWorkflowIdSelective(SttFactorExt record);

    int updateByWorkflowId(SttFactorExt record);

    int updateByPrimaryKey(SttFactorExt record);
}