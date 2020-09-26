package com.joyintech.tams.valuation.common.interf.base.dao;

import java.util.Map;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.base.dto.Interflog;

@Repository
public interface InterflogMapper {
    int deleteByPrimaryKey(String fileType);

    int deleteByWorkflowId(String workflowId);

    int insert(Interflog record);

    int insertSelective(Interflog record);

    int insertBasicByBasicInfo(Interflog record);

    int insertBasicByWorkflowId(Map paraMap);

    Interflog selectByPrimaryKey(String fileType);

    int updateByPrimaryKeySelective(Interflog record);

    int updateByWorkflowIdSelective(Interflog record);

    int updateByWorkflowId(Interflog record);

    int updateByPrimaryKey(Interflog record);
}