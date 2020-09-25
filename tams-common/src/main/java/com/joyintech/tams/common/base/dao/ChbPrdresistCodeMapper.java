package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbPrdresistCode;
import com.joyintech.tams.common.base.entity.ChbPrdresistCodeKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbPrdresistCodeMapper {
    int deleteByPrimaryKey(ChbPrdresistCodeKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbPrdresistCode record);

    int insertSelective(ChbPrdresistCode record);

    int insertBasicByBasicInfo(ChbPrdresistCode record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbPrdresistCode selectByPrimaryKey(ChbPrdresistCodeKey key);

    int updateByPrimaryKeySelective(ChbPrdresistCode record);

    int updateByWorkflowIdSelective(ChbPrdresistCode record);

    int updateByWorkflowId(ChbPrdresistCode record);

    int updateByPrimaryKey(ChbPrdresistCode record);
}