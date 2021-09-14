package com.joyintech.tams.report.system.reportstatusinfo.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.report.system.reportstatusinfo.dto.ReportStatusInfoDto;

@Repository
public interface ReportStatusInfoMapper {

    /**
     * 查询发送状态列表
     * @param params
     * @return
     */
    List<ReportStatusInfoDto> queryReportStatusInfoList(Map<String, Object> params);

}
