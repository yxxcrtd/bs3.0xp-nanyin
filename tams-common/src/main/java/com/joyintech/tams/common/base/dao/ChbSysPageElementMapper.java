package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSysPageElement;
import com.joyintech.tams.common.base.entity.ChbSysPageElementKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSysPageElementMapper {
    int deleteByPrimaryKey(ChbSysPageElementKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbSysPageElement record);

    int insertSelective(ChbSysPageElement record);

    int insertBasicByBasicInfo(ChbSysPageElement record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbSysPageElement selectByPrimaryKey(ChbSysPageElementKey key);

    int updateByPrimaryKeySelective(ChbSysPageElement record);

    int updateByWorkflowIdSelective(ChbSysPageElement record);

    int updateByWorkflowId(ChbSysPageElement record);

    int updateByPrimaryKey(ChbSysPageElement record);
}