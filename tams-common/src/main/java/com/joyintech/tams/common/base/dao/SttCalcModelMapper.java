package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttCalcModel;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttCalcModelMapper {
    int deleteByPrimaryKey(String modelId);

    int deleteByWorkflowId(String workflowId);

    int insert(SttCalcModel record);

    int insertSelective(SttCalcModel record);

    int insertBasicByBasicInfo(SttCalcModel record);

    int insertBasicByWorkflowId(Map paraMap);

    SttCalcModel selectByPrimaryKey(String modelId);

    int updateByPrimaryKeySelective(SttCalcModel record);

    int updateByWorkflowIdSelective(SttCalcModel record);

    int updateByWorkflowId(SttCalcModel record);

    int updateByPrimaryKey(SttCalcModel record);
}