package com.joyintech.tams.disclosure.busicommon.jixu.framework.report.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.pojo.ReportInfo;

@Repository
public interface ReportInfoDao {
	
	public ReportInfo getReportInfoByID(String reportID);
	public ReportInfo getReportInfoByURI(String uri);
	public List<ReportInfo> loadEntityAll(Class<ReportInfo> class1);

}
