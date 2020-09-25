/**
 * 报表调用的门户类
 * 通过该类:1)查询报表仓库中的报表定义; 2)根据报表定义生成报表; 3)根据报表定义和导出格式导出报表
 * @author 小川(几许)
 * @version 1.0
*/

package com.joyintech.tams.disclosure.busicommon.jixu.framework.report;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosuregenandsend.service.InfoDisclosureGenService;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.generators.JasperServerGenerator;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.generators.JasperSourceGenerator;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.generators.ReportGenerator;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.pojo.ReportInfo;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.repository.ReportRepository;

import net.sf.jasperreports.engine.JRException;
@Component
public class ReportManager {
	//绑定在Report Manager的数据源
	private DataSource dataSource = null;
	
	/**
	 * 日志
	 */
	private Logger logger =  LoggerFactory.getLogger(ReportManager.class);

	
	//Report generators' list to generate and export all types of report
	private HashMap<String, ReportGenerator> generators = null;
	
	//Report repository to manage all report definition and search for report
	@Autowired
	private ReportRepository repository;
    
	/**
	 * ReportManager构造函数
	 * @param ds 报表引用的项目的默认数据源
	*/
	public ReportManager(DataSource ds) {
		super();
		this.dataSource = ds;
		this.generators = new HashMap<String, ReportGenerator>();
		
		//目前支持Jasper Server的report generator, 同时支持Jasper Library的API调用
		//可根据情况增加新的report generator
		this.generators.put("JasperSource", new JasperSourceGenerator(this.dataSource));
		this.generators.put("JasperServer", new JasperServerGenerator());
		
		//获得一个单例的报表仓库(报表定义的列表)
		//this.repository = new ReportRepository();
		 
		
	}
	
	/**
	 * 生成报表, 并返回HTML格式的报表内容
	 * @param report 报表定义类
	 * @param params 报表参数
	 * @return HTML格式的报表内容
	*/
	public String genReport(ReportInfo report, HashMap<String, Object> params){
		try {
			logger.info("Start to generate report: " + report.getUri());
			return this.generators.get(report.getGenerator()).genReport(report, params);
		} catch (JRException e) {
			logger.error("Fail to generate report: " + report.getUri());
			logger.error("error:", e);
			return null;
		}
	}
	
	/**
	 * 生成报表, 并将返回的HTML String写入PrintWriter
	 * @param report 报表定义类
	 * @param params 报表参数
	 * @param out 报表写入的目标输出
	 * @return true: 生成成功, false: 生成失败
	*/
	public Boolean genReport(ReportInfo report, HashMap<String, Object> params, PrintWriter out){
		try {
			logger.info("Start to generate report: " + report.getUri());
			(this.generators.get(report.getGenerator())).genReport(report, params, out);
			return true;
		} catch (JRException e) {
			logger.error("Fail to generate report: " + report.getUri());
			logger.error("error:", e);
			return false;
		}
	}
	
	/**
	 * 生成报表, 并将返回的HTML String写入servlet response
	 * @param report 报表定义类
	 * @param params 报表参数
	 * @param request servlet request
	 * @param response servlet response
	 * @return true: 生成成功, false: 生成失败
	*/
	public Boolean genReport(ReportInfo report, Map<String, Object> params, HttpServletRequest request, HttpServletResponse response){
		try {
			logger.info("Start to generate report: " + report.getUri());
			//TODO:
			this.generators.get(report.getGenerator()).genReport(report, params, request, response);
			return true;
		} catch (JRException e) {
			logger.error("Fail to generate report: " + report.getUri() + " casued by report generation error.");
			logger.error("error:", e);
			return false;
		} catch (IOException e) {
			logger.error("Fail to generate report: " + report.getUri() + " cased by Network IO error");
			logger.error("error:", e);
			return false;
		}
	}
	
	/**
	 * 根据报表的定义，导出报表到固定格式文件
	 * @param report 报表定义类
	 * @param params 报表参数
	 * @param type 导出文件类型, 目前支持: xlsx, docx, pdf
	 * @param dest 导出文件的目标, 应为所在操作系统的全路径
	 * @return true: 导出成功, false: 导出失败
	*/
	public Boolean exportReport(ReportInfo report, Map<String, Object>params ,String type, String dest){
		try {
			logger.info("Start to export report: " + report.getUri() + " to type: " + type);
			this.generators.get(report.getGenerator()).exportReport(report, params, type, dest);
			return true;
		} catch (JRException e) {
			logger.error("Fail to export report: " + report.getUri());
			logger.error("error:", e);
			return false;
		}
	}
	
	/**
	 * 列出所有被该Report Manager管理的所有报表定义
	 * @return Report Manager管理的报表定义列表
	*/
	public List<ReportInfo> getAllReports(){
		return this.repository.getAllReports();
	}
	
	/**
	 * 通过报表URI查找报表定义
	 * @param uri 报表URI
	 * @return 与给定URI匹配的报表定义，如果没有找到报表定义会返回null
	*/
	public ReportInfo getReportByURI(String uri){
		return this.repository.getReportByURI(uri);
	}
	
	/**
	 * 通过报表id查找报表定义
	 * @param id 报表id
	 * @return 与给定报表id匹配的报表定义，如果没有找到报表定义会返回null
	*/
	public ReportInfo getReportByID(String reportID){
		return this.repository.getReportByID(reportID);
	}
	
	/**
	 * 取得与当前Report Manager关联的数据源
	 * @return 与当前Report Manager关联的数据源
	*/
	public DataSource getDataSource() {
		return this.dataSource;
	}

	/**
	 * 设定Report Manager的数据源
	 * @param dataSource 数据源
	*/
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.generators.get("JasperSource").setDataSource(dataSource);
	}
}
