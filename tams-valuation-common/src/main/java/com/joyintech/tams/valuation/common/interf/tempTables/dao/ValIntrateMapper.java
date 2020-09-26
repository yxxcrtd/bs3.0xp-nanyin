package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValIntrate;

@Repository
public interface ValIntrateMapper {
    int deleteByPrimaryKey(String intRateId);

    int insert(ValIntrate record);

    int insertSelective(ValIntrate record);

    int insertBasicByBasicInfo(ValIntrate record);

    ValIntrate selectByPrimaryKey(String intRateId);

    int updateByPrimaryKeySelective(ValIntrate record);

    int updateByPrimaryKey(ValIntrate record);
}