/**
 * 
 */
package com.joyintech.tams.disclosure.busicommon.poireport.service;

import java.io.*;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.oss.service.FileRemoteService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.RepInstallLogDetailMapper;
import com.joyintech.tams.common.base.dao.RepProdInfoMapper;
import com.joyintech.tams.common.base.dao.RepRevealMapper;
import com.joyintech.tams.common.base.entity.RepInstallLogDetail;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.entity.RepRuleEntity;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.util.RevealDeadLineService;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.dao.ReportInfoMapper;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.pojo.ReportInfo;
import com.joyintech.tams.disclosure.busicommon.poireport.dao.PoiReportMapper;
import com.joyintech.tams.disclosure.busicommon.poireport.dto.DisReportType;
import com.joyintech.tams.disclosure.busicommon.rptdata.service.ReportDataService;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tams.disclosure.interf.interf.common.constant.DateUtil;
import com.joyintech.tams.systemtools.service.CommonPlatformDateService;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.utils.poi.PoiUtils;

import javax.annotation.Resource;

/**
 * poi报表生成服务<br>
 * 
 * @author chenxc
 * @version 1.0 2022年08月01日
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PoiReportService {

	@Autowired
	private ReportInfoMapper reportInfoMapper;

	@Autowired
	private CommonPlatformDateService commonPlatformDateService;

	@Autowired
	private ReportDataService reportDataService;

	@Autowired
	private RepProdInfoMapper repProdInfoMapper;

	@Autowired
	private RepRevealMapper repRevealMapper;

	@Autowired
	private PoiReportMapper poiReportMapper;
	
	@Autowired
	private RevealDeadLineService revealDeadLineService;
	
	/**
	 * 日志详情表mapper
	 */
	@Autowired
	private RepInstallLogDetailMapper repInstallLogDetailMapper;

	@Autowired
	private FileAttachmentProcess fileAttachmentProcess;
	@Resource
	private FileRemoteService minioFileRemoteService;
	
	@Autowired
	private ReportFileService reportFileService;

	
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 生成报表：创建服务器文件，创建工作流进行报表确认
	 * @param params
	 * @param reportFileType
	 * @param opType：报表生成类型：preview--预览，gen--生成
	 * @return
	 * @throws Exception
	 */
	public String initReport(String repId) throws Exception {
		String fileType = ".docx";
		String templateFile = reportFileService.getTemplateFile(repId, fileType);

		//初始化参数，用xxx替换参数变量，文件已静态编辑好，存在放开模板的.../blank/目录，直接换个文件名称
		File f = new File(templateFile);
		return f.getParent() + "/blank/"+f.getName();
	}
	
	/**
	 * 生成报表：创建服务器文件，创建工作流进行报表确认
	 * @param params
	 * @param reportFileType
	 * @param opType：报表生成类型：preview--预览，gen--生成
	 * @return
	 * @throws Exception
	 */
	public String previewReport(Map<String, Object> params) throws Exception {

			String reportFile = "";
			ReportInfo reportInfo = null;
			String prodId = "";
			
			//模板信息
			try {
				String repId = params.get("repid").toString();
				reportInfo = reportInfoMapper.getReportInfoByID(repId);
				if (!StringUtil.isNotEmpty(reportInfo)) {
					throw new Exception("生成报表失败!没有找到模板。");
				}
			} catch (Exception e) {
				logger.error("生成报表失败!没有找到模板。");
				throw new Exception("生成报表失败!没有找到模板。");
			}
	
			Date reportDate = null;
			try {
				if (params.get("reportDate") != null)
					reportDate = (Date)params.get("reportDate");
			} catch (Exception e) {
				logger.error("获取报表日期错误", e);
			}
			
			//查询数据
			Map<String, Object> dataMap = null;
				List<Map<String, Object>> listReports = reportDataService.queryDatas(params);
				if (listReports.size() > 0) {
					dataMap = listReports.get(0);
					dataMap.put("prodId",params.get("prodId"));
				} else {
					logger.error("生成报表失败!没有找到数据。");
					dataMap = new HashMap<>();
					dataMap.put("prodId", params.get("prodId"));
					//throw new Exception("生成报表失败!没有找到数据。");
				}

				if ( dataMap.get("reportDate")!=null ) 
					reportDate = (Date)dataMap.get("reportDate");
			
			reportFile = this.genOssReportFile(reportInfo, dataMap, params);
			logger.info("生成文件成功!" + reportFile);
			return reportFile;
		}

	/**
	 * 生成报表：创建服务器文件，创建工作流进行报表确认
	 * @param params
	 * @param reportFileType
	 * @param opType：报表生成类型：preview--预览，gen--生成
	 * @return
	 * @throws Exception
	 */
	public String genReport(Map<String, Object> params) throws Exception {

			String reportFile = "";
			ReportInfo reportInfo = null;
			String prodId = "";
			String repId = "";
			Date genDate = (Date)params.get("reportDate");
			try {
				repId = params.get("repid").toString();
				reportInfo = reportInfoMapper.getReportInfoByID(repId);
				if (!StringUtil.isNotEmpty(reportInfo)) {
					throw new Exception("生成报表失败!没有找到模板。");
				}
			} catch (Exception e) {
				logger.error("生成报表失败!没有找到模板。");
				throw new Exception("生成报表失败!没有找到模板。");
			}
	
//			Date reportDate = null;
//			try {
//				if (params.get("reportDate") != null)
//					reportDate = DateUtil.parse(params.get("reportDate").toString());
//			} catch (Exception e) {
//				logger.error("获取报表日期错误", e);
//			}

			reportFile = reportFileService.getSimpleReportFileName(repId, genDate, params, ReportFileService.FILE_TYPE_DOCX);
			String tempRemoteFileName = reportFileService.getTempOSSReportFileName(repId, genDate, params, ReportFileService.FILE_TYPE_DOCX);
			//getRomteFilePath(reportInfo, params, ReportFileService.FILE_TYPE_DOCX);
			
			File localFile = new SafelyFile(reportFile);
			String fileName = reportFile;
			UploadFile uploadFile = new UploadFile(fileName, IDGenerator.generate(), localFile);
			logger.info("UploadFile fileName= "  + reportFile);
//	        if(null != fileAttachmentProcess) {
//	            fileAttachmentProcess.upload(uploadFile);
//	        }
			minioFileRemoteService.tempFileCopy(tempRemoteFileName, uploadFile);
	        
			// 创建工作流
			boolean flag = insertRepReveal(params, uploadFile.getFileNo(), uploadFile.getSrcName(),
					reportInfo.getReportName(), reportInfo.getReportCategory());
			if (flag == false) {
				logger.error("生成信息主表错误!");
			}

		return reportFile;

	}
	/**
	 * 生成服务器上的word报表文件
	 * 
	 * @param repId：REP_INFO表的rep_id字段，报表类型编号，如OTHER_FHJSQD：分红计算清单
	 * @param dataMap，报表变量数据集合
	 * @param genDate,生成日期，如果为空则为平台日期
	 * @return 生成的报表文件路径
	 */
//	public String genReportFile(String reportType, ReportInfo reportInfo, Map<String, Object> dataMap,
//			Date reportDate) {
//		String imagePath = "";
//		String templateFile = "";
//		String reportFile = "";
//
//		templateFile = buildReportFileName(REPORT_TYPE_模板文件, reportInfo, dataMap, FILE_TYPE_DOCX);
//		if ( dataMap==null || dataMap.size()<1 ) {
//			//初始化参数，换个文件名称
//			File f = new File(templateFile);
//			return f.getParent() + "/blank/"+f.getName();
//			
//			//return templateFile;
//		}
//		
//		if (reportDate == null)
//			reportDate = commonPlatformDateService.getSysDate();
//		String prodId = (dataMap.get("prodId") == null ? "" : dataMap.get("prodId").toString());
//		reportFile = buildReportFileName(reportType, reportInfo, dataMap, FILE_TYPE_DOCX);
//
//		FileOutputStream fos = null;
//		InputStream is = null;
//		try {
//			// 替换参数
//			fos = new FileOutputStream(reportFile);
//			PoiUtils.mergeDocx(templateFile, fos, dataMap);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (fos != null) {
//					fos.flush();
//					fos.close();
//				}
//				if (is != null) {
//					is.close();
//				}
//			} catch (IOException e) {
//				logger.error("生成报表文件错误.", e);
//				e.printStackTrace();
//			}
//		}
//		return reportFile;
//	}

	/**
	 * 生成服务器上的word报表文件
	 * 
	 * @param repId：REP_INFO表的rep_id字段，报表类型编号，如OTHER_FHJSQD：分红计算清单
	 * @param dataMap，报表变量数据集合
	 * @param genDate,生成日期，如果为空则为平台日期
	 * @return 生成的报表文件路径
	 */
//	public String genReportFile(String reportType, ReportInfo reportInfo, Map<String, Object> dataMap,Map<String,Object>params) {
//		String imagePath = "";
//		String templateFile = "";
//		String reportFile = "";
//
//		templateFile = buildReportFileName(REPORT_TYPE_模板文件, reportInfo, dataMap, FILE_TYPE_DOCX);
//		if ( dataMap==null || dataMap.size()<1 ) {
//			//初始化参数，换个文件名称
//			File f = new File(templateFile);
//			return f.getParent() + "/blank/"+f.getName();
//			
//			//return templateFile;
//		}
//		
////		if (reportDate == null)
////			reportDate = commonPlatformDateService.getSysDate();
//		String prodId = (dataMap.get("prodId") == null ? "" : dataMap.get("prodId").toString());
//		reportFile = buildReportFileName(reportType, reportInfo, params, FILE_TYPE_DOCX);
//
//		FileOutputStream fos = null;
//		InputStream is = null;
//		try {
//			// 替换参数
//			fos = new FileOutputStream(reportFile);
//			PoiUtils.mergeDocx(templateFile, fos, dataMap);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (fos != null) {
//					fos.flush();
//					fos.close();
//				}
//				if (is != null) {
//					is.close();
//				}
//			} catch (IOException e) {
//				logger.error("生成报表文件错误.", e);
//				e.printStackTrace();
//			}
//		}
//		return reportFile;
//	}
	
	/**
	 * 
	 * 生成功能入库操作 <功能详细描述>
	 * 
	 * @param params
	 * @param fileKey
	 * @param fileName
	 * @return
	 * @see
	 * @since 1.0
	 */
	private boolean insertRepReveal(Map<String, Object> params, String fileKey, String fileName, String ReportName,
			String reportCategory) {
		RepReveal repReveal = reportDataService.getRepReveal(params);
		// 将待组装数据成日志信息
		RepInstallLogDetail repInstallLogDetail = new RepInstallLogDetail();

		repReveal.setRepId(params.get("repid").toString());
		repReveal.setSysFilepath(fileKey);
		repReveal.setSysFilename(fileName);
		repReveal.setReportName(ReportName);
		if (StringUtil.isNotEmpty(params.get("prodId"))) {
			repReveal.setProdid(params.get("prodId").toString());
		} else {
			if ( params.get("pprodId")!=null )
				repReveal.setProdid(params.get("pprodId").toString());
		}
		if ( params.get("prodName")!=null ) { 
			repReveal.setProdName(params.get("prodName").toString());
		} else {
			if ( params.get("pprodName")!=null )
				repReveal.setProdName(params.get("pprodName").toString());			
		}
		if (StringUtil.isNotEmpty(params.get("startDate"))) {
			repReveal.setStartDate(DateUtil.parse(params.get("startDate").toString()));
		}
		if (StringUtil.isNotEmpty(params.get("endDate"))) {
			repReveal.setEndDate(DateUtil.parse(params.get("endDate").toString()));
		} else {
			repReveal.setEndDate((Date)params.get("reportDate"));
		}
		// 获取产品信息
		String portnum = params.get("prodId") == null ? "" : params.get("prodId").toString();
		RepProdInfo prodInfo = repProdInfoMapper.selectByPrimaryKey(portnum);

		// TODO:规则信息，重新编写
		// 获取规则信息
       List<RepRuleEntity> repRule = poiReportMapper.getRuleInfo(params.get("repId").toString());
       if(null!=repRule&&repRule.size()==1){
           Date basiDate = getBasisDate(prodInfo, repRule.get(0));
           
           Date revealEndDate = null;
           if(StringUtil.isNotEmpty(basiDate)) {
               revealEndDate = revealDeadLineService.getRevDeadlineByMark(basiDate, repRule.get(0), new HashMap<String, Date>());
               // 设置披露截止日
               repReveal.setRevealEndDate(revealEndDate);
           }else{
               repReveal.setRevealEndDate(new Date());
           }
       }else{
           repReveal.setRevealEndDate(new Date());
       }

//       RepInfo repInfo = repInfoMapper.selectByPrimaryKey(repReveal.getRepId());

//       if(StringUtil.isNotEmpty(repInfo)&&StringUtil.isNotEmpty(repInfo.getTemplateId())){
//           String temllateId = repInfo.getTemplateId();
//           if(temllateId.equals("CLGG_zx")){
//               repReveal.setEndDate(prodInfo.getVdate());
//           }else if(temllateId.equals("DQGGHB_zx")||temllateId.equals("DQGGJZ_zx")||temllateId.equals("DQGGYQ_zx")){
//               repReveal.setEndDate(prodInfo.getMdate());
//           }
//       }

		repReveal.setEndDate(params.get("endDate") == null ? null : DateUtil.parse(params.get("endDate").toString()));

		// 获取披露渠道
		if (null != prodInfo) {
			repReveal.setRevealChannel(prodInfo.getRevealChannel());
		}

		repReveal.setId(IDGenerator.generate());
		repReveal.setConfirmStatus(DictCodeListExt1.披露确认状态_未确认.getValue());
		repReveal.setRevealStatus(DictCodeListExt2.披露状态_未披露.getValue());
		repReveal.setStatus(DisclosureDictCodeList.数据状态_有效.getValue());
		// 系统生成确认方式和披露方式设置为手动
		repReveal.setIsConfirm(DictCodeListExt1.确认类型_手动.getValue());
		repReveal.setIsReveal(DictCodeListExt2.披露方式_手动.getValue());
		repReveal.setReportCategory(reportCategory);
		repRevealMapper.insert(repReveal);

		// 插入日志
		// 主键 id
		repInstallLogDetail.setId(IDGenerator.generate());
		// 执行状态
		repInstallLogDetail.setExecStatus(DisclosureDictCodeList.日志状态_生成成功.getValue());
		// 报表编号
		repInstallLogDetail.setRepId(repReveal.getRepId());
		// 报表名称
		repInstallLogDetail.setRepName(repReveal.getReportName());
		// 产品编号
		repInstallLogDetail.setProdid(repReveal.getProdid());
		// 文件类型
		repInstallLogDetail
				.setFileType(repReveal.getSysFilename().substring(repReveal.getSysFilename().lastIndexOf(".") + 1));
		// 产品名称
		repInstallLogDetail.setProdName(repReveal.getProdName());
		// 开始日
		repInstallLogDetail.setStartDate(repReveal.getStartDate());
		// 结束日日
		repInstallLogDetail.setEndDate(repReveal.getEndDate());
		// 披露渠道
		repInstallLogDetail.setRevealChannel(repReveal.getRevealChannel());
		// 披露截止日
		repInstallLogDetail.setRevealEndDate(repReveal.getRevealEndDate());
		// 是否自动确认
		repInstallLogDetail.setIsConfirm(repReveal.getIsConfirm());
		// 是否自动披露
		repInstallLogDetail.setIsReveal(repReveal.getIsConfirm());
		// 设置数据状态
		repInstallLogDetail.setStatus(DisclosureDictCodeList.数据状态_有效.getValue());
		// 更新日志表
		repInstallLogDetailMapper.insert(repInstallLogDetail);
		return true;
	}
	
//	/**
//	 * 获取模板或者报表文件url
//	 * 
//	 * @param reportType：M-模板文件，T-临时文件，E-正式文件
//	 * @param reportInfo                      模板信息
//	 * @param reportDate                      生成日期
//	 * @param reportDate                      报表日期
//	 * @param portnum                         关键字
//	 * @param fileType
//	 * @return
//	 */
//	public String buildReportFileName(String reportType, ReportInfo reportInfo, Map<String,Object>params,String fileType) {
//		// String templatePath =
//		// CacheUtil.getSysParam(ReportGlobalConstant.TEMPLATE_FILE_PATH);
//		
//		
//		String reportPath = CacheUtil.getSysParam(ReportGlobalConstant.REPORT_FILE_PATH);
//		String tempPath = CacheUtil.getSysParam(ReportGlobalConstant.TEMP_FILE_PATH);
//		String filename = "";
//		if (REPORT_TYPE_模板文件.equals(reportType)) { // 模板文件
//			filename = reportInfo.getRepository() + reportInfo.getFolder() + "/" + reportInfo.getUri() + fileType;
//			File f = new File(filename);
//			if (!f.exists())
//				filename = reportInfo.getRepository() + reportInfo.getFolder() + "/" + "ERROR" + fileType;
//
//		} else {
//			RepReveal repReveal = reportDataService.getRepReveal(params);
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			Date genDate = commonPlatformDateService.getSysDate();
//			String dirname = sdf.format(genDate);
//			StringBuffer sb = new StringBuffer();
//			if (REPORT_TYPE_临时文件.equals(reportType)) {
//				sb.append(tempPath);
//			} else { // 正式文件
//				sb.append(reportPath);
//			}
//			sb.append(dirname);
//			sb.append("/");
//			sb.append(reportInfo.getUri());  // .getReportName()，避免linux中文
//			sb.append("_");
//			sb.append(repReveal.getProdid());
//			sb.append("_");
//			sb.append(sdf.format(repReveal.getEndDate()));
//			sb.append(fileType);
//			// 用日期创建路径
//			File file = new File(sb.toString());
//			if (!file.getParentFile().exists()) {
//				file.getParentFile().mkdirs();
//			}
//			filename = sb.toString();
//		}
//		return filename;
//	}
	
    /**
	 * 获取基准日
	 * @param prodInfoListEntity 产品相关
	 * @param repRule 规则表
	 * @return 返回基准日 
	 */
	private Date getBasisDate(RepProdInfo prodInfo, RepRuleEntity repRule){
		if(StringUtil.equals(DictCodeListExt2.基准日_募集起始日.getValue(), repRule.getBasisDateType())
				||StringUtil.equals(DictCodeListExt2.基准日_实际到期日.getValue(), repRule.getBasisDateType())){
			try {
				return (Date)CommonUtil.getValueByClassNameObject(prodInfo, CommonUtil.dealString(repRule.getBasisDateType(),true), RepProdInfo.class);
			} catch (Exception e) {
				logger.error("error:",e);
			}
		}
		return null;
	}
	
    public RepReveal getRepReveal( Map<String,Object>params) {
    	return reportDataService.getRepReveal(params);
    }
	/**
	 * @Author ZY22350
	 * @Description //获取远程文件地址
	 * @Date 15:08 2022/8/3
	 * @Param [reportType, reportInfo, reportDate, portnum, fileType]
	 * @return java.lang.String
	 **/
	public String getRomteFilePath(ReportInfo reportInfo, Map<String,Object> params,
								   String fileType){
		Date genDate = new Date();
		if ( params.get("reportDate")!=null )
			genDate = (Date)params.get("reportDte");
		String localFilePath = reportFileService.getReportFileName(reportInfo.getReportID(), genDate, params, fileType);
		return minioFileRemoteService.getRemoteFilePathName(localFilePath);
	}
	/**
	 * @Author ZY22350
	 * @Description //创建OSS文件名称，其中针对模板暂时不处理
	 * @Date 14:14 2022/8/3
	 * @Param [reportType, reportInfo, reportDate, portnum, fileType]
	 * @return java.lang.String
	 **/
//	public String buildOssReportFileName(String reportType, ReportInfo reportInfo, Map<String,Object> params,String fileType) {
//		String filename = "";
//		if (REPORT_TYPE_模板文件.equals(reportType)) { // 模板文件
//			filename = reportInfo.getRepository() + reportInfo.getFolder() + "/" + reportInfo.getUri() + fileType;
//			File f = new File(filename);
//			if (!f.exists())
//				filename = reportInfo.getRepository() + reportInfo.getFolder() + "/" + "ERROR" + fileType;
//
//		} else {
//			RepReveal repReveal = reportDataService.getRepReveal(params);
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			Date genDate = commonPlatformDateService.getSysDate();
//			String dirname = sdf.format(genDate);
//			StringBuffer sb = new StringBuffer();
//			sb.append(dirname);
//			sb.append("/");
//			sb.append(reportInfo.getUri());  // .getReportName()，避免linux中文
//			sb.append("_");
//			sb.append(repReveal.getProdid());
//			sb.append("_");
//			sb.append(sdf.format(repReveal.getEndDate()));
//			sb.append(fileType);
//			filename = sb.toString();
//		}
//		return filename;
//
//	}

	/**
	 * @Author ZY22350
	 * @Description //报表文件OSS生成
	 * @Date 14:26 2022/8/3
	 * @Param [reportType, reportInfo, dataMap, reportDate]
	 * @return java.lang.String
	 **/
	public String genOssReportFile(ReportInfo reportInfo, Map<String, Object> dataMap, Map<String,Object> params) {
		String repId = params.get("repId").toString();
		String templateFile = reportFileService.getTemplateFile(repId, ReportFileService.FILE_TYPE_DOCX);
		Date genDate = new Date(); 
		if ( params.get("reportDate")!=null ) {
			genDate = (Date)params.get("reportDate");
		}else if (params.get("endDate")!=null ){
			genDate = DateUtil.parse(params.get("enddate").toString());
		} else {
			genDate = (Date)params.get("platformDate");
		}
		String localPathFile = reportFileService.getTempSimpleReportFileName(repId, genDate, params, ReportFileService.FILE_TYPE_DOCX); 
		logger.debug("==================报表生成文件上传："+localPathFile);
		ByteArrayOutputStream bos = null;
		ByteArrayInputStream is = null;
		String realFilePath = "";
		try {
			bos = new ByteArrayOutputStream();
			// 替换参数
			PoiUtils.mergeDocx(templateFile, bos, dataMap);
			is = new ByteArrayInputStream(bos.toByteArray());
			//上传问题到minio服务器
			realFilePath = minioFileRemoteService.uploadFile(localPathFile, is);
		} catch (Exception e) {
			logger.error("生成报表文件异常", e);
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					logger.error("关闭字节输出流异常", e);
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error("关闭字节输入流异常", e);
				}
			}
		}
		return realFilePath;
	}
	/**
	 * @Author ZY22350
	 * @Description //读取文件为字节流，如果是http:开头，判断为远程调用，使用minio读取
	 * @Date 14:52 2022/8/3
	 * @Param [filePath]
	 * @return byte[]
	 **/
	public byte[] getFileByteArray(String filePath){

		if(logger.isDebugEnabled())
			logger.debug("读取文件字节流,文件为:{}",filePath);

		if(StringUtils.isBlank(filePath)){
			return null;
		}
		if(filePath.startsWith("http:")){
			return minioFileRemoteService.downloadFile(filePath);
		}else{
			FileInputStream fis = null;
			ByteArrayOutputStream bos = null;
			try{
				fis = new FileInputStream(filePath);
				bos = new ByteArrayOutputStream();
				byte[] buff = new byte[1024];
				int rc = 0;
				while (true) {
					try {
						if (!((rc = fis.read(buff, 0, 1024)) > 0))
							break;
					} catch (IOException e) {
						logger.error("读取文件流异常", e);
					}
					bos.write(buff, 0, rc);
				}
				return bos.toByteArray();
			}catch(Exception e){
				logger.error("读取本地文件报错",e);
			}finally {
				if(bos!=null){
					try {
						bos.close();
					}catch(IOException e){
						logger.error("关闭输出字节流异常",e);
					}
				}
				if(fis != null){
					try{
						fis.close();
					}catch(IOException e){
						logger.error("关闭文件输入流异常",e);
					}
				}
			}
		}
		return null;
	}
	/**
	 * @Author ZY22350
	 * @Description //批处理提交文件，此处需要注意，dataMap中必须要求包含：enddate,repId,prodName等生成文件头必须要的参数
	 * 发生异常会进行异常抛出，如果异常不影响后续流程处理，请捕获异常RuntimeException
	 * @Date 13:27 2022/8/6
	 * @Param [dataMap]
	 * @return com.joyintech.framework.base.dto.UploadFile
	 **/
	public UploadFile genReportFile2Batch(Map<String,Object> dataMap) throws RuntimeException{

		if(dataMap == null ){
			throw new IllegalArgumentException("业务数据为空");
		}

		String repId = dataMap.get("repId").toString();
		if(logger.isDebugEnabled())
			logger.debug("开始进行批处理报表生成,repId={}",repId);
		String templateFile = reportFileService.getTemplateFile(repId, ReportFileService.FILE_TYPE_DOCX);
		Date genDate = new Date();
		Object endDateObj = dataMap.get("enddate");
		if ( endDateObj!=null ) {
			if(endDateObj instanceof String){
				genDate = DateUtil.parse(endDateObj.toString());
			}else if(endDateObj instanceof Date){
				genDate = (Date) endDateObj;
			}else{
				throw new IllegalArgumentException("enddate 类型不正确");
			}
		} else {
			Object execDateObj = dataMap.get("execDate");
			if(execDateObj == null){
				genDate = commonPlatformDateService.getSysDate();
			}else {
				genDate = (Date) execDateObj;
			}
		}
		String localPathFile = reportFileService.getSimpleReportFileName(repId, genDate, dataMap, ReportFileService.FILE_TYPE_DOCX);
		ByteArrayOutputStream bos = null;
		ByteArrayInputStream is = null;
		try {
			bos = new ByteArrayOutputStream();
			// 替换参数
			PoiUtils.mergeDocx(templateFile, bos, dataMap);
			is = new ByteArrayInputStream(bos.toByteArray());
			File vFile = new SafelyFile(localPathFile);
			UploadFile uploadFile = new UploadFile(localPathFile,IDGenerator.generate(),vFile);
			uploadFile = minioFileRemoteService.uploadFile(uploadFile,is);
			return uploadFile;
		} catch (Exception e) {
			logger.error("生成报表文件异常", e);
			throw new RuntimeException("生成报表文件异常",e);
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					logger.error("关闭字节输出流异常", e);
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error("关闭字节输入流异常", e);
				}
			}
		}
	}
}
