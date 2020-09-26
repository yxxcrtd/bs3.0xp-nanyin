package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValAgencyInfo;

@Repository
public interface ValAgencyInfoMapper {
    int deleteByPrimaryKey(String agencyId);

    int insert(ValAgencyInfo record);

    int insertSelective(ValAgencyInfo record);

    int insertBasicByBasicInfo(ValAgencyInfo record);

    ValAgencyInfo selectByPrimaryKey(String agencyId);

    int updateByPrimaryKeySelective(ValAgencyInfo record);

    int updateByPrimaryKey(ValAgencyInfo record);
}