package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttExtConfig;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttExtConfigMapper {
    int deleteByPrimaryKey(String configId);

    int deleteByWorkflowId(String workflowId);

    int insert(SttExtConfig record);

    int insertSelective(SttExtConfig record);

    int insertBasicByBasicInfo(SttExtConfig record);

    int insertBasicByWorkflowId(Map paraMap);

    SttExtConfig selectByPrimaryKey(String configId);

    int updateByPrimaryKeySelective(SttExtConfig record);

    int updateByWorkflowIdSelective(SttExtConfig record);

    int updateByWorkflowId(SttExtConfig record);

    int updateByPrimaryKey(SttExtConfig record);
}