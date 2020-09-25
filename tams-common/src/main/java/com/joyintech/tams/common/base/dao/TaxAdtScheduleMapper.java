package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TaxAdtSchedule;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxAdtScheduleMapper {
    int deleteByPrimaryKey(String taxId);

    int insert(TaxAdtSchedule record);

    int insertSelective(TaxAdtSchedule record);

    int insertBasicByBasicInfo(TaxAdtSchedule record);

    TaxAdtSchedule selectByPrimaryKey(String taxId);

    int updateByPrimaryKeySelective(TaxAdtSchedule record);

    int updateByPrimaryKey(TaxAdtSchedule record);
}