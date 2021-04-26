package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.CommissionCaclProcess;
import org.springframework.stereotype.Repository;

@Repository
public interface CommissionCaclProcessMapper {
    int deleteByPrimaryKey(String busiNo);

    int insert(CommissionCaclProcess record);

    int insertSelective(CommissionCaclProcess record);

    int insertBasicByBasicInfo(CommissionCaclProcess record);

    CommissionCaclProcess selectByPrimaryKey(String busiNo);

    int updateByPrimaryKeySelective(CommissionCaclProcess record);

    int updateByPrimaryKey(CommissionCaclProcess record);
}