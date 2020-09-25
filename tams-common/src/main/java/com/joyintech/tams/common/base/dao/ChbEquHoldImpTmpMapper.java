package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbEquHoldImpTmp;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbEquHoldImpTmpMapper {
    int deleteByWorkflowId(String workflowId);

    int insert(ChbEquHoldImpTmp record);

    int insertSelective(ChbEquHoldImpTmp record);

    int insertBasicByBasicInfo(ChbEquHoldImpTmp record);

    int insertBasicByWorkflowId(Map paraMap);

    int updateByWorkflowIdSelective(ChbEquHoldImpTmp record);

    int updateByWorkflowId(ChbEquHoldImpTmp record);
}