package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSysPageDictlist;
import com.joyintech.tams.common.base.entity.ChbSysPageDictlistKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSysPageDictlistMapper {
    int deleteByPrimaryKey(ChbSysPageDictlistKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbSysPageDictlist record);

    int insertSelective(ChbSysPageDictlist record);

    int insertBasicByBasicInfo(ChbSysPageDictlist record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbSysPageDictlist selectByPrimaryKey(ChbSysPageDictlistKey key);

    int updateByPrimaryKeySelective(ChbSysPageDictlist record);

    int updateByWorkflowIdSelective(ChbSysPageDictlist record);

    int updateByWorkflowId(ChbSysPageDictlist record);

    int updateByPrimaryKey(ChbSysPageDictlist record);
}