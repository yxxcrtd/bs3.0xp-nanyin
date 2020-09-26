package com.joyintech.tams.common.svreport.datacheck.dao;

import java.util.List;

import com.joyintech.tams.common.svreport.datacheck.dto.ChbCheckRuleout;

import org.apache.ibatis.annotations.Param;
public interface ChbCheckRuleoutMapper {
    int deleteByPrimaryKey(String ruleoutid);

    int insert(ChbCheckRuleout record);

    int insertSelective(ChbCheckRuleout record);

    ChbCheckRuleout selectByPrimaryKey(String ruleoutid);

    int updateByPrimaryKeySelective(ChbCheckRuleout record);

    int updateByPrimaryKey(ChbCheckRuleout record);
    
    List<ChbCheckRuleout> selectAllData();
    
    List<ChbCheckRuleout> selectDataByCheckTypeAndReprotCode(@Param("checkType")String checkType,@Param("reprotCode")String reprotCode);
    
}