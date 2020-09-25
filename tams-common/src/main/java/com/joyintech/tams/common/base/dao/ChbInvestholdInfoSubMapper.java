package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbInvestholdInfoSub;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbInvestholdInfoSubMapper {
    int deleteByPrimaryKey(String registSeqno);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbInvestholdInfoSub record);

    int insertSelective(ChbInvestholdInfoSub record);

    int insertBasicByBasicInfo(ChbInvestholdInfoSub record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbInvestholdInfoSub selectByPrimaryKey(String registSeqno);

    int updateByPrimaryKeySelective(ChbInvestholdInfoSub record);

    int updateByWorkflowIdSelective(ChbInvestholdInfoSub record);

    int updateByWorkflowId(ChbInvestholdInfoSub record);

    int updateByPrimaryKey(ChbInvestholdInfoSub record);
}