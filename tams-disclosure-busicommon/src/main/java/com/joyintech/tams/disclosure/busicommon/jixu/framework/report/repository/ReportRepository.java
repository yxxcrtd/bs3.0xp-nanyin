package com.joyintech.tams.disclosure.busicommon.jixu.framework.report.repository;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.dao.ReportInfoDao;
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
	private ReportInfoDao reportInfoDao;
	
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
		  return reportInfoDao.loadEntityAll(ReportInfo.class);
	}
	
	public ReportInfo getReportByURI(String uri){
		return reportInfoDao.getReportInfoByURI(uri);
	}
	
	public ReportInfo getReportByID(String reportID){
		return reportInfoDao.getReportInfoByID(reportID);
	}
}
