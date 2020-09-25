package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttGrpLayering;
import com.joyintech.tams.common.base.entity.SttGrpLayeringKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttGrpLayeringMapper {
    int deleteByPrimaryKey(SttGrpLayeringKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(SttGrpLayering record);

    int insertSelective(SttGrpLayering record);

    int insertBasicByBasicInfo(SttGrpLayering record);

    int insertBasicByWorkflowId(Map paraMap);

    SttGrpLayering selectByPrimaryKey(SttGrpLayeringKey key);

    int updateByPrimaryKeySelective(SttGrpLayering record);

    int updateByWorkflowIdSelective(SttGrpLayering record);

    int updateByWorkflowId(SttGrpLayering record);

    int updateByPrimaryKey(SttGrpLayering record);
}