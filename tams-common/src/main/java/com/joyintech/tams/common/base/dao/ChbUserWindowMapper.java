package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbUserWindow;
import com.joyintech.tams.common.base.entity.ChbUserWindowKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbUserWindowMapper {
    int deleteByPrimaryKey(ChbUserWindowKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbUserWindow record);

    int insertSelective(ChbUserWindow record);

    int insertBasicByBasicInfo(ChbUserWindow record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbUserWindow selectByPrimaryKey(ChbUserWindowKey key);

    int updateByPrimaryKeySelective(ChbUserWindow record);

    int updateByWorkflowIdSelective(ChbUserWindow record);

    int updateByWorkflowId(ChbUserWindow record);

    int updateByPrimaryKey(ChbUserWindow record);
}