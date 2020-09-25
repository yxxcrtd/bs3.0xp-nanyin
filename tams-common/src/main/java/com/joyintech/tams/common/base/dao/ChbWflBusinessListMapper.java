package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbWflBusinessList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbWflBusinessListMapper {
    int deleteByPrimaryKey(String seqno);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbWflBusinessList record);

    int insertSelective(ChbWflBusinessList record);

    int insertBasicByBasicInfo(ChbWflBusinessList record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbWflBusinessList selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(ChbWflBusinessList record);

    int updateByWorkflowIdSelective(ChbWflBusinessList record);

    int updateByWorkflowId(ChbWflBusinessList record);

    int updateByPrimaryKey(ChbWflBusinessList record);
}