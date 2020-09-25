package com.joyintech.tams.disclosure.busicommon.jixu.framework.report.generators;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.pojo.ReportInfo;

import net.sf.jasperreports.engine.JRException;

public interface ReportGenerator {
	public void genReport(ReportInfo report, Map<String, Object> params, PrintWriter out) throws JRException;
	public void genReport(ReportInfo report, Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) throws JRException, IOException;
	public String genReport(ReportInfo report, Map<String, Object> params) throws JRException;
	public void exportReport(ReportInfo report, Map<String, Object> params, String type, String dest) throws JRException;
	public DataSource getDataSource();
	public void setDataSource(DataSource dataSource);
}
