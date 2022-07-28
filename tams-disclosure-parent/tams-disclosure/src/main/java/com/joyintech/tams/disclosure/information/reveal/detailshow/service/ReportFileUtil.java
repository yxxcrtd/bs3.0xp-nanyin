/**
 * 
 */
package com.joyintech.tams.disclosure.information.reveal.detailshow.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.pojo.ReportInfo;

/**
 * @author Administrator
 *
 */
public class ReportFileUtil {

	public final static String REPORT_TYPE_模板文件="REPORT_TYPE_M";
	public final static String REPORT_TYPE_临时文件="REPORT_TYPE_T";
	public final static String REPORT_TYPE_报表文件="REPORT_TYPE_E";
	
	public final static String FILE_TYPE_DOCX=".docx";
	public final static String FILE_TYPE_PDF=".pdf";
	
    /**
     * 获取模板或者报表文件url
     * @param reportType：M-模板文件，T-临时文件，E-正式文件
     * @param reportInfo
     * @param genDate
     * @param portnum
     * @param fileType
     * @return
     */
    public static String buildReportFileName(String reportType,ReportInfo reportInfo,Date genDate,String portnum,String fileType) {
    	//String templatePath = CacheUtil.getSysParam(ReportGlobalConstant.TEMPLATE_FILE_PATH);
    	String reportPath = CacheUtil.getSysParam(ReportGlobalConstant.REPORT_FILE_PATH);
    	String tempPath = CacheUtil.getSysParam(ReportGlobalConstant.TEMP_FILE_PATH);
    	String filename = "";
    	if ( REPORT_TYPE_模板文件.equals(reportType) )  { //模板文件
    		filename = reportInfo.getRepository() + reportInfo.getFolder() +"/" + reportInfo.getUri() +fileType;
        	File f = new File(filename);
        	if ( !f.exists() ) 
        		filename = reportInfo.getRepository() + reportInfo.getFolder() +"/" + "ERROR" +fileType;

    	} else {
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		String dirname = sdf.format(genDate);
    		StringBuffer sb = new StringBuffer();
    		if (REPORT_TYPE_临时文件.equals(reportType)) {
    			sb.append(tempPath);
    		} else {   //正式文件
    			sb.append(reportPath);
    		}
    		sb.append(dirname);
    		sb.append("/");
    		sb.append(reportInfo.getReportName());
    		sb.append("_");
    		sb.append(portnum);
    		sb.append(fileType);
    		//用日期创建路径
    		File file = new File(sb.toString());
    		if ( !file.getParentFile().exists() ) {
    			file.getParentFile().mkdirs();
    		}
    		filename = sb.toString();
    	}
    	return filename;
    }
}
