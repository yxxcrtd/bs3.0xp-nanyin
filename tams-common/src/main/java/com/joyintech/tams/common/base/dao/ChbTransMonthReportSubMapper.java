package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbTransMonthReportSub;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbTransMonthReportSubMapper {
    int deleteByPrimaryKey(String reportuuid);

    int insert(ChbTransMonthReportSub record);

    int insertSelective(ChbTransMonthReportSub record);

    int insertBasicByBasicInfo(ChbTransMonthReportSub record);

    ChbTransMonthReportSub selectByPrimaryKey(String reportuuid);

    int updateByPrimaryKeySelective(ChbTransMonthReportSub record);

    int updateByPrimaryKey(ChbTransMonthReportSub record);
}