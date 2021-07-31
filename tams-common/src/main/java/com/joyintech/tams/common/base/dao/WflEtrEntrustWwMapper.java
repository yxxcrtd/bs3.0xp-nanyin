package com.joyintech.tams.common.base.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.WflEtrEntrustWw;
import com.joyintech.tams.common.base.entity.WflEtrEntrustWwKey;

@Repository
public interface WflEtrEntrustWwMapper {
    int deleteByPrimaryKey(WflEtrEntrustWwKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflEtrEntrustWw record);

    int updateByWorkflowId(WflEtrEntrustWw record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflEtrEntrustWw record);

    int insertSelective(WflEtrEntrustWw record);

    int insertBasicByBasicInfo(WflEtrEntrustWw record);

    WflEtrEntrustWw selectByPrimaryKey(WflEtrEntrustWwKey key);

    int updateByPrimaryKeySelective(WflEtrEntrustWw record);

    int updateByPrimaryKey(WflEtrEntrustWw record);
}