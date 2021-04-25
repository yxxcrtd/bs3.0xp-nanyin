package com.joyintech.tams.svreport.chbreport.hxassetliabilitystatement.dao;


import com.joyintech.tams.svreport.chbreport.hxassetliabilitystatement.dto.HxBalance;

public interface HxBalanceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HxBalance record);

    int insertSelective(HxBalance record);

    HxBalance selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HxBalance record);

    int updateByPrimaryKey(HxBalance record);
}