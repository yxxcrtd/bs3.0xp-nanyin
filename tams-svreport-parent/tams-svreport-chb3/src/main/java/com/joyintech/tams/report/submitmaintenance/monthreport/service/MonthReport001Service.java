package com.joyintech.tams.report.submitmaintenance.monthreport.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.report.monthreport.dao.MonthReport001Mapper;
import com.joyintech.tams.report.monthreport.dto.MonthReport001Dto;

import net.sf.jxls.transformer.XLSTransformer;

@Service
@Transactional(rollbackFor = Exception.class)
public class MonthReport001Service extends CommonExportService<MonthReport001Dto>  {

	@Autowired
    private MonthReport001Mapper monthReport001Mapper;
	
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
	
	
//	public ResponseEntity<byte[]> exportMonthReportFBB(String pageId,Map<String, Object> pageParam) {
//		List<MonthReportFBB001Dto> report = monthReportFBB001Mapper.getMonthReportFBB(pageParam);
//		this.beforeExprot();
//		
//		Map<Object,Object> data = new HashMap<Object,Object>();
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		Calendar c = Calendar.getInstance();
//		
//		FileInputStream is = null;
//		FileOutputStream out = null;
//		try{
//		// 关联模板
//        XLSTransformer transformer = new XLSTransformer();
//        
//		String template  = null;
//		//windows环境地址
//        if(File.separator.equals("\\")){
//        	template = getXmlPath("MonthReportFBB001.xls");
//        }
//        //linux环境地址
//        if(File.separator.equals("/")){
//        	
//        }
//        
//        String reportdate = pageParam.get("exportdate")==null?"":pageParam.get("exportdate").toString();//文件夹名称
//        String reportMonth = "";
//        if(reportdate.length()==10){
//        	reportMonth = reportdate.replace("-", "").substring(0, 6);
//        }
//		//传放地址
//		String depositfilePath = SvrDataUtil.getSysParam("access_monthfilepath");
//		
//		
//		File depositfile = new File(depositfilePath + File.separator + "MonthReportFBB001_"+reportMonth+".xls");
//		if (!depositfile.getParentFile().exists()) {
//			depositfile.getParentFile().mkdirs();
//		}
//
//		data.put("report", report);
//		transformer.transformXLS(template, data, depositfilePath + File.separator + "MonthReportFBB001_"+reportMonth+".xls");
//		
//
//		//下载已有文件
//		
//        String outFileName = FileNameUtil.createFileName("xls");
//		String exportFilePath = SvrDataUtil.getSysParam("access_exportfilepath");
//		File exportFile = new File(exportFilePath + File.separator + outFileName);
//		
//		if (!exportFile.getParentFile().exists()) {
//			exportFile.getParentFile().mkdirs();
//		}
//
//    	is = new FileInputStream(depositfile);
//		HSSFWorkbook wbs = new HSSFWorkbook(is);
//		wbs.write(new FileOutputStream(exportFile));
//		is.close();
//		wbs.close();
//		
//		String exportFileName = "非保本银行月度统计表(" + reportdate + ")"
//				+ "." + FileNameUtil.getFileExtName(exportFile);
//		return ExportUtil.export(exportFileName, exportFile, true);
//		
//	    }catch(Exception e){
//	    	System.out.println("非保本银行月度统计表生成失败！");
//	    	throw new RuntimeException(e);
//	    }
//		finally{
//			try {
//				if(null!=is){
//					is.close();
//				}
//				if(null!=out){
//					out.close();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	
	public static String getXmlPath(String filename) {
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("").toString();
		path = path.replace('/', '\\'); // 将/换成\
		path = path.replace("file:", ""); // 去掉file:
		path = path.substring(1); // 去掉第一个\,如 \D:\JavaWeb...
		path += "tpl\\" + filename;
		return path;
	}

	public static String getXmlPathLinux(String filename) {
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("").toString();
		path = path.replace("file:", ""); // 去掉file:
		path += "tpl/" + filename;
		return path;
	}

	public ResponseEntity<byte[]> exportMonthReportBB(String pageId, Map<String, Object> pageParam) {
//				List<MonthReportFBB001Dto> report = monthReportFBB001Mapper.getMonthReportBB(pageParam);
//				this.beforeExprot();
//				
//				Map<Object,Object> data = new HashMap<Object,Object>();
//				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//				Calendar c = Calendar.getInstance();
//				
				FileInputStream is = null;
				FileOutputStream out = null;
				HSSFWorkbook wbs = null;
				try{
//				// 关联模板
//		        XLSTransformer transformer = new XLSTransformer();
//		        
//				String template  = null;
//				//windows环境地址
//		        if(File.separator.equals("\\")){
//		        	template = getXmlPath("MonthReportBB001.xls");
//		        }
//		        //linux环境地址
//		        if(File.separator.equals("/")){
//		        	
//		        }
		        String reportdate = pageParam.get("exportdate")==null?"":pageParam.get("exportdate").toString();//文件夹名称
		        String reportMonth = "";
		        if(reportdate.length()==7){
		        	reportMonth = reportdate.replace("-", "");
		        }
				//传放地址
				String depositfilePath = SvrDataUtil.getSysParam("access_monthfilepath");
				
				
				File depositfile = new File(depositfilePath + File.separator + "MonthReportBB001_"+reportMonth+".xls");
				if (!depositfile.getParentFile().exists()) {
					depositfile.getParentFile().mkdirs();
				}

				//下载已有文件
				
		        String outFileName = FileNameUtil.createFileName("xls");
				String exportFilePath = SvrDataUtil.getSysParam("access_exportfilepath");
				File exportFile = new File(exportFilePath + File.separator + outFileName);
				
				if (!exportFile.getParentFile().exists()) {
					exportFile.getParentFile().mkdirs();
				}

		    	is = new FileInputStream(depositfile);
				wbs = new HSSFWorkbook(is);
				wbs.write(new FileOutputStream(exportFile));
				is.close();
				wbs.close();
				
				String exportFileName = "保本银行月度统计表(" + reportMonth + ")"
						+ "." + FileNameUtil.getFileExtName(exportFile);
				return ExportUtil.export(exportFileName, exportFile, true);
				
			    }catch(Exception e){
//			    	System.out.println("保本银行月度统计表生成失败！");
			        logger.error("保本银行月度统计表生成失败！");
			        logger.error(e.getMessage(),e);
	                return null;
//			    	throw new RuntimeException(e);
			    }
				finally{
					try {
						if(null!=is){
							is.close();
						}
						if(null!=out){
							out.close();
						}
						if(null!=wbs){
							wbs.close();
						}
					} catch (IOException e) {
//						e.printStackTrace();
			            logger.error(e.getMessage(),e);
					}
				}
	}
	
	
	public ResponseEntity<byte[]> exportMonthReport(String pageId, Map<String, Object> pageParam,String excelcnname,String excelenname) {
			FileInputStream is = null;
			FileOutputStream out = null;
			HSSFWorkbook wbs = null;
			try{
	        String reportdate = pageParam.get("exportdate")==null?"":pageParam.get("exportdate").toString();
	        String reportMonth = "";
	        if(reportdate.length()==7){
	        	reportMonth = reportdate.replace("-", "");
	        }
			//传放地址
			String depositfilePath = SvrDataUtil.getSysParam("access_monthfilepath");
			
			
			File depositfile = new File(depositfilePath + File.separator +excelenname+"_"+reportMonth+".xls");
			if (!depositfile.getParentFile().exists()) {
				depositfile.getParentFile().mkdirs();
			}
	
			//下载已有文件
	        String outFileName = FileNameUtil.createFileName("xls");
			String exportFilePath = SvrDataUtil.getSysParam("access_exportfilepath");
			File exportFile = new File(exportFilePath + File.separator + outFileName);
			
			if (!exportFile.getParentFile().exists()) {
				exportFile.getParentFile().mkdirs();
			}
	
	    	is = new FileInputStream(depositfile);
			wbs = new HSSFWorkbook(is);
			wbs.write(new FileOutputStream(exportFile));
			//is.close();
			//wbs.close();
			
			String exportFileName = excelcnname+"(" + reportMonth + ")"
					+ "." + FileNameUtil.getFileExtName(exportFile);
			return ExportUtil.export(exportFileName, exportFile, true);
			
		    }catch(Exception e){
//		    	System.out.println(excelcnname+"生成失败！");
                logger.error("{}生成失败！",excelcnname);
                logger.error(e.getMessage(),e);
                return null;
//		    	throw new RuntimeException(e);
		    }
			finally{
				try {
					if(null!=is){
						is.close();
					}
					if(null!=out){
						out.close();
					}
					if(null!=wbs){
						wbs.close();
					}
				} catch (IOException e) {
//					e.printStackTrace();
		            logger.error(e.getMessage(),e);
				}
			}
	}
	
	public void recalMonthReport(String pageId, Map<String, Object> pageParam,String excelcnname,String excelenname) {
			List<MonthReport001Dto> report  = new ArrayList<MonthReport001Dto>();
			FileInputStream is = null;
			FileOutputStream out = null;
			try {
			String reportdate = pageParam.get("exportdate")==null?"":pageParam.get("exportdate").toString();//文件夹名称
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
			Date date = dateFormat.parse(reportdate);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.set(Calendar.DAY_OF_MONTH, 0);
			pageParam.put("exportdate", c.getTime());
			
	        String reportMonth = "";
	        if(reportdate.length()==7){
	        	reportMonth = reportdate.replace("-", "");
	        }
	        
			if("MonthReportFBB001".equals(excelenname)){
				report = monthReport001Mapper.getMonthReportFBB(pageParam);
			}else{
				report = monthReport001Mapper.getMonthReportBB(pageParam);
			}
			this.beforeExprot();
			
			Map<Object,Object> data = new HashMap<Object,Object>();
			// 关联模板
	        XLSTransformer transformer = new XLSTransformer();
	        
			
			String template  = null;
			//windows环境地址
	        if(File.separator.equals("\\")){
	        	template = getXmlPath(excelenname+".xls");
	        }
	        //linux环境地址
	        if(File.separator.equals("/")){
	        	template = getXmlPathLinux(excelenname+".xls");
	        }
	        
	       
			//传放地址
			String depositfilePath = SvrDataUtil.getSysParam("access_monthfilepath");
			
			
			File depositfile = new File(depositfilePath + File.separator + excelenname+"_"+reportMonth+".xls");
			if (!depositfile.getParentFile().exists()) {
				depositfile.getParentFile().mkdirs();
			}
	
			data.put("report", report);
			transformer.transformXLS(template, data, depositfilePath + File.separator + excelenname+"_"+reportMonth+".xls");
			
		    }catch(Exception e){
//		    	System.out.println(excelcnname+"生成失败！");
                logger.error("{}生成失败！",excelcnname);
                logger.error(e.getMessage(),e);
                return;
//		    	throw new RuntimeException(e);
		    }
			finally{
				try {
					if(null!=is){
						is.close();
					}
					if(null!=out){
						out.close();
					}
				} catch (IOException e) {
//					e.printStackTrace();
		            logger.error(e.getMessage(),e);
				}
			}
	}


	public Map<String, Object> getSubmitstatus(Map<String, Object> pageParam) {
		String submitstatus = monthReport001Mapper.getSubmitstatus(pageParam);
		Map<String, Object> map = new HashMap<String, Object>();
		if(submitstatus !=null && "E".equals(submitstatus)){
			submitstatus = "已处理";
		}else{
			submitstatus = "未处理";
		}
		map.put("submitstatus", submitstatus);
		return map;
	}


	public Map<String, Object> manualSubmitstatus(Map<String, Object> pageParam) {
		Map<String, Object> result = new HashMap<String, Object>();
		String depositfilePath = SvrDataUtil.getSysParam("access_monthfilepath");
		String excelenname = pageParam.get("excelenname")==null?"":pageParam.get("excelenname").toString();
		String reportMonth = pageParam.get("reportmonth")==null?"":pageParam.get("reportmonth").toString();
		reportMonth = reportMonth.replace("-", "");
		File depositfile = new File(depositfilePath + File.separator + excelenname+"_"+reportMonth+".xls");
		if (!depositfile.exists()){
			result.put("reportexists", "N");
			result.put("submitstatus", "N");
		}else{
			int i = monthReport001Mapper.manualSubmitstatus(pageParam);
			if(i == 1){
				result.put("reportexists", "Y");
				result.put("submitstatus", "E");
			}
		}
		return result;
	}


	public Map<String, Object> cancelSubmitstatus(Map<String, Object> pageParam) {
		Map<String, Object> result = new HashMap<String, Object>();
		int i = monthReport001Mapper.cancelSubmitstatus(pageParam);
		if(i>0){
			result.put("cancelstatus", "E");
		}
		return result;
	}
	
	public List<Map<String, Object>> searchReportData(Date reportDay)
	        throws InterruptedException, ExecutionException {

	        List<Map<String, Object>> allDatas = new ArrayList<>(1);
	        return allDatas;
	}

	
}
