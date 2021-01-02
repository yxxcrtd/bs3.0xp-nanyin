package com.joyintech.service.report.regulatoryreporting.common.base.dao;

import com.joyintech.service.report.regulatoryreporting.common.base.entity.ShareFileReport;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareFileReportMapper {
    int insert(ShareFileReport record);

    int insertSelective(ShareFileReport record);

    int insertBasicByBasicInfo(ShareFileReport record);
}