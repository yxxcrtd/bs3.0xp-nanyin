package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbTransMonthHis;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbTransMonthHisMapper {
    int deleteByPrimaryKey(String reportuuid);

    int insert(ChbTransMonthHis record);

    int insertSelective(ChbTransMonthHis record);

    int insertHisByBasicInfo(ChbTransMonthHis record);

    ChbTransMonthHis selectByPrimaryKey(String reportuuid);

    int updateByPrimaryKeySelective(ChbTransMonthHis record);

    int updateByPrimaryKey(ChbTransMonthHis record);
}