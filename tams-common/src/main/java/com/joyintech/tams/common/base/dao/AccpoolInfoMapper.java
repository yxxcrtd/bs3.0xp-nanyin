package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.AccpoolInfo;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface AccpoolInfoMapper {
    int deleteByPrimaryKey(String accpoolId);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(AccpoolInfo record);

    int updateByWorkflowId(AccpoolInfo record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(AccpoolInfo record);

    int insertSelective(AccpoolInfo record);

    int insertBasicByBasicInfo(AccpoolInfo record);

    AccpoolInfo selectByPrimaryKey(String accpoolId);

    int updateByPrimaryKeySelective(AccpoolInfo record);

    int updateByPrimaryKey(AccpoolInfo record);
}