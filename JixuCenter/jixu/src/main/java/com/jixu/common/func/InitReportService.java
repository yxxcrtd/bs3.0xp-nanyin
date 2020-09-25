package com.jixu.common.func;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import com.jixu.common.tool.JixuUserUtil;

/** 
 * @ClassName 		ReportService
 * @author  		Pan Wei 
 * @Date 			2016年12月21日 上午11:08:20 	
 * @Description		[]  
 */

@Component("initReportService")
@ConfigurationProperties(prefix="jixu.report")
public class InitReportService implements InitializingBean{
	
	private static final Logger logger = LoggerFactory.getLogger(InitReportService.class);
	
	//从application.yml获取
	//---------------------
	private String reportExportFolder;
	private Map<String, String> commonReports=new HashMap<String, String>();
	//---------------------
	
	private int reportID=0;

	//jasper文件的地址
	private HashMap<Integer, String> reportSourceHM;
	
	//报表输出的地址
	private HashMap<Integer, String> reportExportHM;
	
	//报表的名字和ID匹配
	private HashMap<String, Integer> reportNameIDHM;
	
	//常用报表的映射
	private HashMap<String, String> commonReportHM;
	
	public HashMap<Integer, String> getReportExportHM() {
		return reportExportHM;
	}
	
	public HashMap<Integer, String> getReportSourceHM() {
		return reportSourceHM;
	}
	
	public HashMap<String, Integer> getReportNameIDHM() {
		return reportNameIDHM;
	}

	public HashMap<String, String> getCommonReportHM() {
		return commonReportHM;
	}

	/**
	 * 初始化报表配置。报表的执行文件地址，报表名字等信息放入map，执行报表时使用
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("开始初始化报表服务...");
		reportSourceHM=new HashMap<Integer, String>();
		reportNameIDHM=new HashMap<String, Integer>();
		commonReportHM=new HashMap<String, String>();
		
		Iterator<Entry<String,String>> iterList=commonReports.entrySet().iterator();
		while(iterList.hasNext()){
			Entry<String,String> entry=iterList.next();
			commonReportHM.put("Report.Common."+entry.getKey().toString(), entry.getValue().toString());//Report.Common.ValuationReport  财务估值表.jasper
		}

		if(commonReportHM.isEmpty()) {
			commonReportHM.put("Report.Common.ValuationReport", "财务估值表.jasper");
			commonReportHM.put("Report.Common.AcctBalanceReport", "科目余额表.jasper");
			commonReportHM.put("Report.Common.IncomeReport", "利润表.jasper");
			commonReportHM.put("Report.Common.AssetBalanceReport", "资产负债表.jasper");
			commonReportHM.put("Report.Common.FundCustodyFee", "基金托管费.jasper");
			commonReportHM.put("Report.Common.CustodyFeesChild", "托管费子表.jasper");
			commonReportHM.put("Report.Common.YGZTaiZhang", "营改增台账.jasper");
		}
		
		String sourcepath=System.getProperty("user.dir");
		if(StringUtils.isEmpty(reportExportFolder)) {
			//默认导出到项目运行所在的根目录
			reportExportFolder=sourcepath+"/outputs/";
		}
		logger.info("报表导出路径: " + reportExportFolder);
		
		//jar文件运行，需要直接读取jar里的文件流。不能用以前的File方式读取
		if(!JixuUserUtil.isJoyinTablesFlag())
			setJasperFilesMap();
		
		logger.info("报表服务初始化结束");
	}
	
	private void getDirectory(String filepath){//C:\\WorkSpace\\apache-tomcat-8.0.39\\wtpwebapps\\jixu\\reports
		File file = new File(filepath);
		if (file.isDirectory()) {
			String[] filelist = file.list();
			for (int i = 0; i < filelist.length; i++) {
				File readfile = new File(filepath + "/" + filelist[i]);//reports(Daily Source)
				if (readfile.isDirectory()) {
					getDirectory(filepath + "/" + filelist[i]);
				}else{
					reportID++;
					reportSourceHM.put(reportID, filepath+"/"+filelist[i]);
					reportNameIDHM.put(filelist[i], reportID);
				}
			}
		}
	}
	
	private void setJasperFilesMap() throws IOException{
		String jasperfilepath="reports/Daily/";
		try {
			Resource[] resources = new PathMatchingResourcePatternResolver().getResources(jasperfilepath+"*.jasper");
			for(Resource resource:resources) {
				reportID++;
				reportSourceHM.put(reportID, jasperfilepath+resource.getFilename());
				reportNameIDHM.put(resource.getFilename(), reportID);
	
			}
		}catch(Exception e) {
			logger.error("获取报表JASPER文件失败: " + jasperfilepath);
			logger.error("Error", e);
		}
	}

	public String getReportExportFolder() {
		return reportExportFolder;
	}

	public void setReportExportFolder(String reportExportFolder) {
		this.reportExportFolder = reportExportFolder;
	}

	public Map<String, String> getCommonReports() {
		return commonReports;
	}

	public void setCommonReports(Map<String, String> commonReports) {
		this.commonReports = commonReports;
	}
}
