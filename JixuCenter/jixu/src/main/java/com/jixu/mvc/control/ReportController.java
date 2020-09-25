package com.jixu.mvc.control;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jixu.common.controller.ExceptionControl;
import com.jixu.common.func.InitReportService;
import com.jixu.common.types.ReportType;
import com.jixu.common.util.DataConvert;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.JsonUtil;
import com.jixu.report.service.JasperReportService;

@Controller
public class ReportController extends ExceptionControl{
	
	@Resource(name="jasperReportService")
	private JasperReportService reportService;
	
	@RequestMapping(value="/report.do", method = RequestMethod.GET, name="报表-页面显示")
	public String report() {
		return "report";
	}
	
	@Resource(name = "initReportService")
	private InitReportService initReport;
	
	@RequestMapping(value = "/report/reportTypes.do", method = RequestMethod.POST)
	public void reportTypes(HttpServletRequest request, HttpServletResponse response){
		HashMap<String, String> reports=new HashMap<String, String>();
		reports.put("ValuationReport", "财务估值表");
		reports.put("IncomeReport", "利润表");
		reports.put("AcctBalanceReport", "余额表");
		reports.put("AssetBalanceReport", "资产负债表");
		reports.put("FundCustodyFee", "基金托管费");
		reports.put("CustodyFeesChild", "托管费子表 ");
		reports.put("YGZTaiZhang", "营改增台账");
		JsonUtil.writeObjToResponse(response, reports);
	}
	
	@RequestMapping(value = "/report/genReport.do", method = RequestMethod.POST, name="报表-生成")
	public void genReport(@RequestParam(value = "id", required = true) String reportID,
								   @RequestParam(value = "portfolio", required = true) String portID,
								   @RequestParam(value = "start", required = true) String start,
								   @RequestParam(value = "end", required = true) String end,
								   @RequestParam(value = "params", required = false) String params,
								   HttpServletRequest request,
								   HttpServletResponse response) throws IOException, JRException, SQLException{
		
		Date startDate=null;
		Date endDate=null;
		if(start.equals("")){
			startDate=new Date();
		}else{
			startDate=DateUtil.convertStr2Date2(start);
		}
		if(end.equals("")){
			endDate=new Date();
		}else{
			endDate=DateUtil.convertStr2Date2(end);
		}
		
		int reportnum=getReportID(reportID);//reportID:ValuationReport
		
		HashMap<String, Object> reportparams=new HashMap<String, Object>();
		reportparams.put("Portnum", portID);
		reportparams.put("StartDate", DateUtil.convertD2YYYY_MM_DD(startDate));
		reportparams.put("EndDate", DateUtil.convertD2YYYY_MM_DD(endDate));
		
		reportService.generateReport(reportnum, portID, startDate, endDate, request, response, reportparams);
	}
	
	@RequestMapping(value = "/report/exportReport.do", method = RequestMethod.POST, name="报表-导出")
	public void exportReport(@RequestParam(value = "id", required = true) String reportID,
								   @RequestParam(value = "portfolio", required = true) String portID,
								   @RequestParam(value = "start", required = true) String start,
								   @RequestParam(value = "end", required = true) String end,
								   @RequestParam(value = "type", required = true) int type,
								   HttpServletRequest request,
								   HttpServletResponse response) throws IOException, JRException, SQLException{
		Date startDate=null;
		Date endDate=null;
		if(start.equals("")){
			startDate=new Date();
		}else{
			startDate=DateUtil.convertStr2Date2(start);
		}
		if(end.equals("")){
			endDate=new Date();
		}else{
			endDate=DateUtil.convertStr2Date2(end);
		}
		
		int reportnum=getReportID(reportID);
		reportService.downLoadReport(response, reportnum, portID, startDate, endDate, type);
	}
	
	@RequestMapping(value = "/report/viewReport.do", method = RequestMethod.POST, name="报表-查看")
	public void viewReport(@RequestParam(value = "id", required = true) String reportID,
								   @RequestParam(value = "portfolio", required = true) String portID,
								   @RequestParam(value = "start", required = true) String start,
								   @RequestParam(value = "end", required = true) String end,
								   HttpServletRequest request,
								   HttpServletResponse response) throws IOException, JRException, SQLException{
		Date startDate=null;
		Date endDate=null;
		if(start.equals("")){
			startDate=new Date();
		}else{
			startDate=DateUtil.convertStr2Date2(start);
		}
		if(end.equals("")){
			endDate=new Date();
		}else{
			endDate=DateUtil.convertStr2Date2(end);
		}
		
		int reportnum=getReportID(reportID);
		reportService.showHtml(response, reportnum, portID, startDate, endDate);
		
	}
	
	@RequestMapping(value="/report/reportFormats.do", method = RequestMethod.POST)  
    public void reportFormats(HttpServletResponse response){
		HashMap<Integer, String> result = ReportType.fileTypeHM;
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/report/isViewable.do", method = RequestMethod.POST)  
    public void isViewable(@RequestParam(value = "id", required = true) String reportID,
    					   @RequestParam(value = "portfolio", required = true) String portID,
    					   @RequestParam(value = "start", required = true) String start,
    					   @RequestParam(value = "end", required = true) String end,
    					   HttpServletResponse response){

		Date startDate=null;
		Date endDate=null;
		if(start.equals("")){
			startDate=new Date();
		}else{
			startDate=DateUtil.convertStr2Date2(start);
		}
		if(end.equals("")){
			endDate=new Date();
		}else{
			endDate=DateUtil.convertStr2Date2(end);
		}
		
		int reportnum=getReportID(reportID);
		boolean isViewable = reportService.checkReportExist(reportnum, portID, startDate, endDate);
		JsonUtil.writeObjToResponse(response, isViewable);
	}
	
	private int getReportID(String reportid){
		if(DataConvert.isNumeric(reportid)){
			return Integer.valueOf(reportid);
		}else{
			int reportnum=0;
			String reportJaspaerFile=initReport.getCommonReportHM().get("Report.Common."+reportid);
			if(reportJaspaerFile!=null){
				reportnum=initReport.getReportNameIDHM().get(reportJaspaerFile);
			}
			return reportnum;
		}
	}
}
