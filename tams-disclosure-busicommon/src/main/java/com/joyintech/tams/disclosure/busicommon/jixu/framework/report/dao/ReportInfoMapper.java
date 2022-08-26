package com.joyintech.tams.disclosure.busicommon.jixu.framework.report.dao;

import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.pojo.ReportInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author wj
 * @version 1.0.0
 * @ClassName ReportInfoMapper.java
 * @Description 批前检查
 * @createTime 2021年07月09日 09:29:00
 */
@Repository
public interface ReportInfoMapper {

    ReportInfo getReportInfoByID(String reportID);

    ReportInfo getReportInfoByURI(String uri);

    List<ReportInfo> loadEntityAll(Class<ReportInfo> class1);

    int getRepInfoStatus(Map<String, Object> params);

}
