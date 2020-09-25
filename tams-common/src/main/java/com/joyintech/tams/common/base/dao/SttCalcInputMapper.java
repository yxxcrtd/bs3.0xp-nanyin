package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttCalcInput;
import com.joyintech.tams.common.base.entity.SttCalcInputKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttCalcInputMapper {
    int deleteByPrimaryKey(SttCalcInputKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(SttCalcInput record);

    int insertSelective(SttCalcInput record);

    int insertBasicByBasicInfo(SttCalcInput record);

    int insertBasicByWorkflowId(Map paraMap);

    SttCalcInput selectByPrimaryKey(SttCalcInputKey key);

    int updateByPrimaryKeySelective(SttCalcInput record);

    int updateByWorkflowIdSelective(SttCalcInput record);

    int updateByWorkflowId(SttCalcInput record);

    int updateByPrimaryKey(SttCalcInput record);
}