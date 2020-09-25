package com.jixu.report.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.jixu.common.func.CommFuncService;
import com.jixu.common.func.InitReportService;
import com.jixu.common.types.ReportType;
import com.jixu.common.util.DateUtil;
import com.orm.obj.PortfolioHdr;

/**
 * @ClassName JasperCustomerReport
 * @author Pan Wei
 * @Date 2016年12月18日 上午11:04:39
 * @Description []
 */

@Service("jasperReportService")
public class JasperReportService {

	private static final Logger logger = LoggerFactory.getLogger(JasperReportService.class);

	@Resource(name = "initReportService")
	private InitReportService initReport;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private CommFuncService commfuncService;
	
	private String genFileName(String exportFolder, String sourceFile, String portnum, Date startDate,Date endDate) {
		//String sourceFilePath=sourceFile.getParent();
		//String appendPath=sourceFilePath.replace(initReport.getReportSourceFolder(), "");
		
		PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
		
		StringBuilder fileBuilder=new StringBuilder();
		fileBuilder.append(exportFolder);
		fileBuilder.append("/");
		fileBuilder.append(portfolio.getPortName());
		//fileBuilder.append("/");
		//fileBuilder.append(appendPath);
		fileBuilder.append("/");
		fileBuilder.append(sourceFile.substring(0, sourceFile.indexOf(".")));
		fileBuilder.append("_");
		fileBuilder.append(portfolio.getPortName());
		fileBuilder.append("_");
		fileBuilder.append(DateUtil.convertD2YYYYMMDD(endDate));
		
		return fileBuilder.toString();
	}
	
	public void generateReport(int reportID, String portnum, Date startDate,
			Date endDate, HttpServletRequest request,
			HttpServletResponse response, HashMap<String, Object> params) {

		String sourceJaspFile = initReport.getReportSourceHM().get(reportID);
		String exportReportFolder = initReport.getReportExportFolder();
		InputStream stream = getClass().getClassLoader().getResourceAsStream(sourceJaspFile);
		if (stream!=null) {
			try {
				String exportfile = genFileName(exportReportFolder,sourceJaspFile,portnum,startDate,endDate);
				
				File file = new File(exportfile);  
				File fileParent = file.getParentFile();  
				if(!fileParent.exists()){  
					fileParent.mkdirs();  
				}  
				
				Connection conn = jdbcTemplate.getDataSource().getConnection();
				JasperHelper.exportStream(0, stream, exportfile, request, response, params, conn);

				showHtml(exportfile+".html", response);

			} catch (SQLException sqle) {
				logger.error("Error", sqle);
			} catch (IOException ie) {
				logger.error("Error", ie);
			}
		}
	}

	private void showHtml(String reportfile, HttpServletResponse response) throws IOException {
		File tempFile = new File(reportfile);
		String filename = tempFile.getName().substring(0,tempFile.getName().indexOf("."));
		InputStream ins = new BufferedInputStream(new FileInputStream(reportfile));
		//response.setContentType("application/" + resolveContentType(filename));
		response.setContentType("application/pdf");
		response.setHeader("filename", filename);
		//HashMap<String, Object> result = new HashMap<String, Object>();
		//result.put("filepath", reportfile);
		//JsonUtil.writeObjToResponse(response, result);
		FileCopyUtils.copy(ins, response.getOutputStream());
	}

	private static String resolveContentType(String fileName) {
		try {
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		} catch (StringIndexOutOfBoundsException e) {
			logger.error("Error", e);
			return "";
		}
	}
	
	public void showHtml(HttpServletResponse response, int reportID, String portnum, Date startDate, Date endDate){
		String sourceJaspFile = initReport.getReportSourceHM().get(reportID);
		String exportReportFolder = initReport.getReportExportFolder();
		File sourceFile = new File(sourceJaspFile);
		if (sourceFile.exists()) {
			String htmlFile=genFileName(exportReportFolder,sourceJaspFile,portnum,startDate,endDate)+".html";
			File reportFile=new File(htmlFile);
			if(reportFile.exists()){
				try{
					showHtml(htmlFile, response);
				} catch (IOException ie) {
					logger.error("Error", ie);
				}
			}
		}
	}
	
	public boolean checkReportExist(int reportID, String portnum, Date startDate, Date endDate){
		String sourceJaspFile = initReport.getReportSourceHM().get(reportID);
		String exportReportFolder = initReport.getReportExportFolder();
		File sourceFile = new File(sourceJaspFile);
		if (sourceFile.exists()) {
			String htmlFile=genFileName(exportReportFolder,sourceJaspFile,portnum,startDate,endDate)+".html";
			File reportFile=new File(htmlFile);
			if(reportFile.exists()){
				return true;
			}
		}
		return false;
	}
	
	public boolean downLoadReport(HttpServletResponse response, int reportID, String portnum, 
			Date startDate, Date endDate, int reportType) throws IOException{
		
		
		String sourceJaspFile = initReport.getReportSourceHM().get(reportID);
		String exportReportFolder = initReport.getReportExportFolder();
		if (sourceJaspFile!=null && !sourceJaspFile.equals("")) {
			String appendType="";
			if(reportType==ReportType.HTML){
				appendType=".html";
			}else if(reportType==ReportType.WORD){
				appendType=".doc";
			}else if(reportType==ReportType.PDF){
				appendType=".pdf";
			}else if(reportType==ReportType.EXCEL){
				appendType=".xls";
			}else{
				return false;
			}
			
			String exportFile=genFileName(exportReportFolder,sourceJaspFile,portnum,startDate,endDate)+appendType;
			File file=new File(exportFile);
			String filename=file.getName();
			InputStream ins = new BufferedInputStream(new FileInputStream(exportFile));
			response.setContentType("application/" + resolveContentType(filename));
			response.setHeader("filename", URLEncoder.encode(filename, "UTF-8"));
			response.setHeader("filename", filename);
			FileCopyUtils.copy(ins, response.getOutputStream());
			ins.close();
		}
		return false;
	}
}
