package com.joyintech.tams.common.base.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.WflEtrEntrustWwDetail;
import com.joyintech.tams.common.base.entity.WflEtrEntrustWwDetailKey;

@Repository
public interface WflEtrEntrustWwDetailMapper {
    int deleteByPrimaryKey(WflEtrEntrustWwDetailKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflEtrEntrustWwDetail record);

    int updateByWorkflowId(WflEtrEntrustWwDetail record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflEtrEntrustWwDetail record);

    int insertSelective(WflEtrEntrustWwDetail record);

    int insertBasicByBasicInfo(WflEtrEntrustWwDetail record);

    WflEtrEntrustWwDetail selectByPrimaryKey(WflEtrEntrustWwDetailKey key);

    int updateByPrimaryKeySelective(WflEtrEntrustWwDetail record);

    int updateByPrimaryKey(WflEtrEntrustWwDetail record);
}