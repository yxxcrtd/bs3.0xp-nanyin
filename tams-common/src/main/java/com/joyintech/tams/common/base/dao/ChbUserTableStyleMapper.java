package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbUserTableStyle;
import com.joyintech.tams.common.base.entity.ChbUserTableStyleKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbUserTableStyleMapper {
    int deleteByPrimaryKey(ChbUserTableStyleKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbUserTableStyle record);

    int insertSelective(ChbUserTableStyle record);

    int insertBasicByBasicInfo(ChbUserTableStyle record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbUserTableStyle selectByPrimaryKey(ChbUserTableStyleKey key);

    int updateByPrimaryKeySelective(ChbUserTableStyle record);

    int updateByWorkflowIdSelective(ChbUserTableStyle record);

    int updateByWorkflowId(ChbUserTableStyle record);

    int updateByPrimaryKeyWithBLOBs(ChbUserTableStyle record);

    int updateByPrimaryKey(ChbUserTableStyle record);
}