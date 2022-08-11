/**
 * 
 */
package com.joyintech.tams.disclosure.busicommon.poireport.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.joyintech.tams.common.oss.service.FileRemoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.dao.ReportInfoMapper;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.pojo.ReportInfo;
import com.joyintech.tams.systemtools.service.CommonPlatformDateService;

import javax.annotation.Resource;

/**
 * poi报表生成的文件命名服务<br>
 * 
 * @author chenxc
 * @version 1.0 2022年08月01日
 * @since 1.0
 */
@Service
public class ReportFileService {

	@Autowired
	private ReportInfoMapper reportInfoMapper;

	@Autowired
	private CommonPlatformDateService commonPlatformDateService;

	@Resource
	private FileRemoteService minioFileRemoteService;
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public final static String REPORT_TYPE_模板文件 = "REPORT_TYPE_M";
	public final static String REPORT_TYPE_临时文件 = "REPORT_TYPE_T";
	public final static String REPORT_TYPE_报表文件 = "REPORT_TYPE_E";

	public final static String FILE_TYPE_DOCX = ".docx";
	public final static String FILE_TYPE_PDF = ".pdf";
	//OSS专用文件夹分隔符
	public final static String SEPARATOR = "/";

	/**
	 * 获取报表的模板文件
	 * @param repId
	 * @param fileType
	 * @return
	 */
	public String getTemplateFile(String repId,String fileType) {
		String templateFile = "";
		ReportInfo reportInfo = null;
		try {
			reportInfo = reportInfoMapper.getReportInfoByID(repId);
			if (StringUtil.isNotEmpty(reportInfo)) {
				templateFile = reportInfo.getRepository() + reportInfo.getFolder() +"/" + reportInfo.getUri() +fileType;
				File f = new File(templateFile);
				if ( !f.exists() ) { 
					templateFile = reportInfo.getRepository() + reportInfo.getFolder() +"/" + "ERROR" +fileType;
				}
			}
		} catch (Exception e) {
			logger.error("生成报表失败!没有找到模板。");
		}
		return templateFile;
	}
	/**
	 * @Author ZY22350
	 * @Description //获取正式报表文件OSS文件名-单纯获取文件名，而非上传文件，切记
	 * @Date 10:51 2022/8/5
	 * @Param [repId, genDate, params, fileType]
	 * @return java.lang.String
	 **/
	public String getOSSReportFileName(String repId,Date genDate,Map<String,Object> params,String fileType) {
		return minioFileRemoteService.getRemoteFilePathName(getSimpleReportFileName(repId,genDate,params,fileType));
	}
	/**
	 * @Author Administrator
	 * @Description //获取报表文件OSS临时文件名-单纯获取文件名，而非上传文件，切记
	 * @Date 10:50 2022/8/5
	 * @Param [repId, genDate, params, fileType]
	 * @return java.lang.String
	 **/
	public String getTempOSSReportFileName(String repId,Date genDate,Map<String,Object> params,String fileType) {
		return minioFileRemoteService.getRemoteFilePathName(getTempSimpleReportFileName(repId,genDate,params,fileType));
	}
	/**
	 * @Author ZY22350
	 * @Description //获取临时文件相对路径
	 * @Date 10:48 2022/8/5
	 * @Param
	 * @return temp/2022-08-05/A2002xxxx.docx
	 **/
	public String getTempSimpleReportFileName(String repId,Date genDate,Map<String,Object> params,String fileType){
		String prefix = "temp";
		String tempFilePathName = "";
		String simpleFileName = getSimpleReportFileName(repId,genDate,params,fileType);
		if(StringUtil.isBlank(simpleFileName)){
			throw new NullPointerException("获取文件名为空");
		}
		tempFilePathName = simpleFileName.startsWith(SEPARATOR) ?prefix + simpleFileName:prefix + SEPARATOR + simpleFileName;
		if(logger.isDebugEnabled())
			logger.debug("生成临时文件相对路径为:{}",tempFilePathName);

		return tempFilePathName;
	}
	
	/**
	 * 获取报表文件名称
	 * @param repId
	 * @param genDate，生成日期，为空的话，取平台时间
	 * @param params，文件名称需要替换的参数
	 * @param fileType,.docx,.pdf
	 * @return eg:/2022-08-22/A001产品发行公告.docx
	 */
    public String getSimpleReportFileName(String repId,Date genDate,Map<String,Object> params,String fileType) {

		ReportInfo reportInfo = null;
		try {
			reportInfo = reportInfoMapper.getReportInfoByID(repId);
		} catch (Exception e) {
			logger.error("获取报表模板错误。",e);
			return "";
		}
		
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	if ( genDate==null )
    		genDate = commonPlatformDateService.getSysDate();
   		String dirname = sdf.format(genDate);
   		
   		//文件的名称，用params里面的值替换变量，如：{prodName}{prodid}{enddate}开放公告
    	String filename = replacePara(reportInfo.getFileName(),params);
    	
   		StringBuffer sb = new StringBuffer();
		sb.append(dirname);
		sb.append(SEPARATOR);
		sb.append(filename);
		sb.append(fileType);
		return sb.toString();
    }

	public String getCheckFileName(String repId,Date genDate,Map<String,Object> params,String fileType) {

		ReportInfo reportInfo = null;
		try {
			reportInfo = reportInfoMapper.getReportInfoByID(repId);
		} catch (Exception e) {
			logger.error("获取报表模板错误。",e);
			return "";
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if ( genDate==null )
			genDate = commonPlatformDateService.getSysDate();
		String dirname = sdf.format(genDate);

		//文件的名称，用params里面的值替换变量，如：{prodName}{prodid}{enddate}开放公告
		String filename = replacePara(reportInfo.getFileName(),params);

		StringBuffer sb = new StringBuffer();
		sb.append(dirname);
		sb.append(SEPARATOR);
		sb.append("check");
		sb.append(SEPARATOR);
		sb.append(filename);
		sb.append(fileType);
		return sb.toString();
	}
	
	/**
	 * 获取报表文件名称
	 * @param repId
	 * @param genDate，生成日期，为空的话，取平台时间
	 * @param params，文件名称需要替换的参数
	 * @param fileType,.docx,.pdf
	 * @return eg:/2022-08-22/A001产品发行公告.docx
	 */
    public String getReportFileName(String repId,Date genDate,Map<String,Object> params,String fileType) {
    	//TODO:wanghaimin:update root url
    	String reportPath = "";//  CacheUtil.getSysParam(ReportGlobalConstant.REPORT_FILE_PATH);

		ReportInfo reportInfo = null;
		try {
			reportInfo = reportInfoMapper.getReportInfoByID(repId);
		} catch (Exception e) {
			logger.error("获取报表模板错误。",e);
			return "";
		}
		
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	if ( genDate==null )
    		genDate = commonPlatformDateService.getSysDate();
   		String dirname = sdf.format(genDate);
   		
   		//文件的名称，用params里面的值替换变量，如：{prodName}{prodid}{enddate}开放公告
    	String filename = replacePara(reportInfo.getFileName(),params);
    	
   		StringBuffer sb = new StringBuffer();
		sb.append(reportPath);
		sb.append(dirname);
		sb.append("/");
		sb.append(filename);
		sb.append(fileType);
		//用日期创建路径
		File file = new File(sb.toString());
		if ( !file.getParentFile().exists() ) {
			file.getParentFile().mkdirs();
		}
		return sb.toString();
    }

	private String replacePara(String expressText, Map<String,Object>params) {
		List<String> listExp = new ArrayList<String>();
		String re = "\\{([^\\}]+)\\}";
	    Pattern p = Pattern.compile(re);
	    Matcher m = p.matcher(expressText);
	    while(m.find()){
	    	listExp.add(m.group(1));
	    }
		if ( listExp.size()>0 ) {
			//text = replaceText(text,listExp,params);
			for ( String key:listExp) {
				Object val = params.get(key);
				String src = "{"+key+"}";
				expressText = expressText.replace(src,val==null?"":val.toString());
			}
		}
		return expressText;
	}
	
}
