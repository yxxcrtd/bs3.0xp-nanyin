package com.joyintech.tams.common.svreport.datacheck.dao;

import java.util.List;

import com.joyintech.tams.common.svreport.datacheck.dto.CheckCondition;

public interface CheckConditionMapper {
    int insert(CheckCondition record);

    int insertSelective(CheckCondition record);
    
    List<CheckCondition> selectByRuleid(String checkruleid);
}