package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttSceneCurve;
import com.joyintech.tams.common.base.entity.SttSceneCurveKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttSceneCurveMapper {
    int deleteByPrimaryKey(SttSceneCurveKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(SttSceneCurve record);

    int insertSelective(SttSceneCurve record);

    int insertBasicByBasicInfo(SttSceneCurve record);

    int insertBasicByWorkflowId(Map paraMap);

    SttSceneCurve selectByPrimaryKey(SttSceneCurveKey key);

    int updateByPrimaryKeySelective(SttSceneCurve record);

    int updateByWorkflowIdSelective(SttSceneCurve record);

    int updateByWorkflowId(SttSceneCurve record);

    int updateByPrimaryKey(SttSceneCurve record);
}