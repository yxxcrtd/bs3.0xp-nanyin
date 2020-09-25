package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbMktHoliday;
import com.joyintech.tams.common.base.entity.ChbMktHolidayKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbMktHolidayMapper {
    int deleteByPrimaryKey(ChbMktHolidayKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbMktHoliday record);

    int insertSelective(ChbMktHoliday record);

    int insertBasicByBasicInfo(ChbMktHoliday record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbMktHoliday selectByPrimaryKey(ChbMktHolidayKey key);

    int updateByPrimaryKeySelective(ChbMktHoliday record);

    int updateByWorkflowIdSelective(ChbMktHoliday record);

    int updateByWorkflowId(ChbMktHoliday record);

    int updateByPrimaryKey(ChbMktHoliday record);
}