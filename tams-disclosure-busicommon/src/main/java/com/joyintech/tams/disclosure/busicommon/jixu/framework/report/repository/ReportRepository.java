package com.joyintech.tams.disclosure.busicommon.jixu.framework.report.repository;

import java.util.List;

import javax.annotation.Resource;

import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.dao.ReportInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.pojo.ReportInfo;

import net.sf.jasperreports.j2ee.servlets.ImageServlet;
@Configuration
@Component
public class ReportRepository {
	@Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new ImageServlet(), "/image");
    }
	
	@Autowired
	private ReportInfoMapper reportInfoMapper;
	
	@Transactional(value="jixuTXManager")
	public void createReport(ReportInfo report){
//		reportInfoDao.saveEntityBean(report);	
	}
	
	@Transactional(value="jixuTXManager")
	public void updateReport(String reportID){
		ReportInfo ri = getReportByID(reportID);
//		reportInfoDao.updateEntity(ri);
	}
	
	@Transactional(value="jixuTXManager")
	public void deleteReport(String reportID){
		ReportInfo ri = getReportByID(reportID);
//		reportInfoDao.deleteEntity(ri);
	}
	
	public List<ReportInfo> getAllReports(){
		  return reportInfoMapper.loadEntityAll(ReportInfo.class);
	}
	
	public ReportInfo getReportByURI(String uri){
		return reportInfoMapper.getReportInfoByURI(uri);
	}
	
	public ReportInfo getReportByID(String reportID){
		return reportInfoMapper.getReportInfoByID(reportID);
	}
}
