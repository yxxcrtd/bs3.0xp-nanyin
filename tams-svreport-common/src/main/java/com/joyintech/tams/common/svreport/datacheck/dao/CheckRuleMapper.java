package com.joyintech.tams.common.svreport.datacheck.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.joyintech.tams.common.svreport.datacheck.dto.CheckRule;

public interface CheckRuleMapper {
    int deleteByPrimaryKey(String checkruleid);

    int insert(CheckRule record);

    int insertSelective(CheckRule record);

    CheckRule selectByPrimaryKey(String checkruleid);

    int updateByPrimaryKeySelective(CheckRule record);

    int updateByPrimaryKey(CheckRule record);
    
    List<CheckRule> selectByDatatype(String datatype);
    
    List<CheckRule> selectByDatatypeByTypeAndColumns(@Param("datatype") String datatype, @Param("columnList") List<String> columnList);

}