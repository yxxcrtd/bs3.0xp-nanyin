package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttObjectGrp;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttObjectGrpMapper {
    int deleteByPrimaryKey(String objectGrpId);

    int deleteByWorkflowId(String workflowId);

    int insert(SttObjectGrp record);

    int insertSelective(SttObjectGrp record);

    int insertBasicByBasicInfo(SttObjectGrp record);

    int insertBasicByWorkflowId(Map paraMap);

    SttObjectGrp selectByPrimaryKey(String objectGrpId);

    int updateByPrimaryKeySelective(SttObjectGrp record);

    int updateByWorkflowIdSelective(SttObjectGrp record);

    int updateByWorkflowId(SttObjectGrp record);

    int updateByPrimaryKey(SttObjectGrp record);
}