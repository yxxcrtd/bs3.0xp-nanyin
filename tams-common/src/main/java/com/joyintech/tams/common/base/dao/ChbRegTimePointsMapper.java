package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbRegTimePoints;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbRegTimePointsMapper {
    int deleteByPrimaryKey(String seqno);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbRegTimePoints record);

    int insertSelective(ChbRegTimePoints record);

    int insertBasicByBasicInfo(ChbRegTimePoints record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbRegTimePoints selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(ChbRegTimePoints record);

    int updateByWorkflowIdSelective(ChbRegTimePoints record);

    int updateByWorkflowId(ChbRegTimePoints record);

    int updateByPrimaryKey(ChbRegTimePoints record);
}