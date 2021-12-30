package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.WflEtrEntrustDetail;
import com.joyintech.tams.common.base.entity.WflEtrEntrustDetailKey;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface WflEtrEntrustDetailMapper {
    int deleteByPrimaryKey(WflEtrEntrustDetailKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflEtrEntrustDetail record);

    int updateByWorkflowId(WflEtrEntrustDetail record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflEtrEntrustDetail record);

    int insertSelective(WflEtrEntrustDetail record);

    int insertBasicByBasicInfo(WflEtrEntrustDetail record);

    WflEtrEntrustDetail selectByPrimaryKey(WflEtrEntrustDetailKey key);

    int updateByPrimaryKeySelective(WflEtrEntrustDetail record);

    int updateByPrimaryKey(WflEtrEntrustDetail record);
}