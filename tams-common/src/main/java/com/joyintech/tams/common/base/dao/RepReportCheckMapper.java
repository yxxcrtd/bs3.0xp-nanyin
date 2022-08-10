package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepReportCheck;
import org.springframework.stereotype.Repository;

@Repository
public interface RepReportCheckMapper {
    int deleteByPrimaryKey(String reportCheckId);

    int insert(RepReportCheck record);

    int insertSelective(RepReportCheck record);

    int insertBasicByBasicInfo(RepReportCheck record);

    RepReportCheck selectByPrimaryKey(String reportCheckId);

    int updateByPrimaryKeySelective(RepReportCheck record);

    int updateByPrimaryKey(RepReportCheck record);
}