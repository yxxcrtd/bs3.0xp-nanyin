package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttStressTips;
import com.joyintech.tams.common.base.entity.SttStressTipsKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttStressTipsMapper {
    int deleteByPrimaryKey(SttStressTipsKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(SttStressTips record);

    int insertSelective(SttStressTips record);

    int insertBasicByBasicInfo(SttStressTips record);

    int insertBasicByWorkflowId(Map paraMap);

    SttStressTips selectByPrimaryKey(SttStressTipsKey key);

    int updateByPrimaryKeySelective(SttStressTips record);

    int updateByWorkflowIdSelective(SttStressTips record);

    int updateByWorkflowId(SttStressTips record);

    int updateByPrimaryKey(SttStressTips record);
}