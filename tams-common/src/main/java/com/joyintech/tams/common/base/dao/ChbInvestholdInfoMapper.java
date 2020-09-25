package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbInvestholdInfo;
import com.joyintech.tams.common.base.entity.ChbInvestholdInfoKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbInvestholdInfoMapper {
    int deleteByPrimaryKey(ChbInvestholdInfoKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbInvestholdInfo record);

    int insertSelective(ChbInvestholdInfo record);

    int insertBasicByBasicInfo(ChbInvestholdInfo record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbInvestholdInfo selectByPrimaryKey(ChbInvestholdInfoKey key);

    int updateByPrimaryKeySelected(ChbInvestholdInfo record);

    int updateByWorkflowIdSelective(ChbInvestholdInfo record);

    int updateByWorkflowId(ChbInvestholdInfo record);

    int updateByPrimaryKey(ChbInvestholdInfo record);
}