package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TaxVatSchedule;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxVatScheduleMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(TaxVatSchedule record);

    int insertSelective(TaxVatSchedule record);

    int insertBasicByBasicInfo(TaxVatSchedule record);

    TaxVatSchedule selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(TaxVatSchedule record);

    int updateByPrimaryKey(TaxVatSchedule record);
}