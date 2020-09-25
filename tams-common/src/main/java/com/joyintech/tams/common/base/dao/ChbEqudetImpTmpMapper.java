package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbEqudetImpTmp;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbEqudetImpTmpMapper {
    int deleteByWorkflowId(String workflowId);

    int insert(ChbEqudetImpTmp record);

    int insertSelective(ChbEqudetImpTmp record);

    int insertBasicByBasicInfo(ChbEqudetImpTmp record);

    int insertBasicByWorkflowId(Map paraMap);

    int updateByWorkflowIdSelective(ChbEqudetImpTmp record);

    int updateByWorkflowId(ChbEqudetImpTmp record);
}