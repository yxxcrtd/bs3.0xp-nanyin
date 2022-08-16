/**
 * 
 */
package com.joyintech.tams.disclosure.busicommon.poireport.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.RepInstallLogDetailMapper;
import com.joyintech.tams.common.base.dao.RepProdInfoMapper;
import com.joyintech.tams.common.base.dao.RepReportCheckMapper;
import com.joyintech.tams.common.base.dao.RepRevealMapper;
import com.joyintech.tams.common.base.entity.RepDataCheck;
import com.joyintech.tams.common.base.entity.RepInstallLogDetail;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.common.base.entity.RepReportCheck;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.common.oss.service.FileRemoteService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.constant.DictCodeListExt5;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.entity.RepRuleEntity;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.util.RevealDeadLineService;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.dao.ReportInfoMapper;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.pojo.ReportInfo;
import com.joyintech.tams.disclosure.busicommon.poireport.dao.PoiReportMapper;
import com.joyintech.tams.disclosure.busicommon.poireport.dto.DisReportType;
import com.joyintech.tams.disclosure.busicommon.rptdata.service.HolidayService;
import com.joyintech.tams.disclosure.busicommon.rptdata.service.ReportDataService;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tams.disclosure.interf.interf.common.constant.DateUtil;
import com.joyintech.tams.systemtools.service.CommonPlatformDateService;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.utils.poi.PoiUtils;
import com.joyintech.tools.collectors.JoyinCollectors;

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

	@Resource
	private FileRemoteService minioFileRemoteService;
	
	@Autowired
	private ReportFileService reportFileService;

	@Autowired
	private PoiReportService poiReportService;

	@Autowired
	private RepReportCheckMapper repReportCheckMapper;
	
	@Autowired
	private ReportRevealEndDateService reportRevealEndDateService;

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
	
			//查询数据
			Map<String, Object> dataMap = null;
				List<Map<String, Object>> listReports = reportDataService.queryDatas(params);
				if ( listReports==null )
					return null;
				if (listReports.size() > 0) {
					dataMap = listReports.get(0);
					dataMap.put("prodId",params.get("prodId"));
				} else {
					logger.error("生成报表失败!没有找到数据。");
					dataMap = new HashMap<>();
					dataMap.put("prodId", params.get("prodId"));
					//throw new Exception("生成报表失败!没有找到数据。");
				}

//				if ( dataMap.get("reportDate")!=null ) 
//					reportDate = (Date)dataMap.get("reportDate");
			
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
			boolean flag = insertRepReveal(params, uploadFile.getFileNo(), uploadFile.getSrcName(),reportInfo);
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
	private boolean insertRepReveal(Map<String, Object> params, String fileKey, String fileName,ReportInfo reportInfo ) {
		//String ReportName,
		//String reportCategory
		RepReveal repReveal = reportDataService.getRepReveal(params);
		// 将待组装数据成日志信息
		RepInstallLogDetail repInstallLogDetail = new RepInstallLogDetail();

		repReveal.setRepId(params.get("repid").toString());
		repReveal.setSysFilepath(fileKey);
		repReveal.setSysFilename(fileName);
		repReveal.setReportName(reportInfo.getReportName());
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
		} else if (params.get("reportDate") != null){
			repReveal.setEndDate((Date)params.get("reportDate"));
		}else{//有一部分报表生成没有日期，需要用平台日处理
			repReveal.setEndDate(com.joyintech.framework.util.DateUtil.platDateFromCache());
		}
		// 获取产品信息
		String busiId = params.get("prodId") == null ? "" : params.get("prodId").toString();
		RepProdInfo prodInfo = repProdInfoMapper.selectByPrimaryKey(busiId);

		//规则信息，重新编写
		Date revealEndDate = reportRevealEndDateService.getDisEndDate(repReveal, busiId,reportInfo);
		if ( revealEndDate ==null )
			revealEndDate = new Date();
		repReveal.setRevealEndDate(revealEndDate);

		// 获取规则信息
//       List<RepRuleEntity> repRule = poiReportMapper.getRuleInfo(params.get("repId").toString());
//       if(null!=repRule&&repRule.size()==1){
//           Date basiDate = getBasisDate(prodInfo, repRule.get(0));
//           
//           Date revealEndDate = null;
//           if(StringUtil.isNotEmpty(basiDate)) {
//               revealEndDate = revealDeadLineService.getRevDeadlineByMark(basiDate, repRule.get(0), new HashMap<String, Date>());
//               // 设置披露截止日
//               repReveal.setRevealEndDate(revealEndDate);
//           }else{
//               repReveal.setRevealEndDate(new Date());
//           }
//       }else{
//           repReveal.setRevealEndDate(new Date());
//       }

//       RepInfo repInfo = repInfoMapper.selectByPrimaryKey(repReveal.getRepId());

//       if(StringUtil.isNotEmpty(repInfo)&&StringUtil.isNotEmpty(repInfo.getTemplateId())){
//           String temllateId = repInfo.getTemplateId();
//           if(temllateId.equals("CLGG_zx")){
//               repReveal.setEndDate(prodInfo.getVdate());
//           }else if(temllateId.equals("DQGGHB_zx")||temllateId.equals("DQGGJZ_zx")||temllateId.equals("DQGGYQ_zx")){
//               repReveal.setEndDate(prodInfo.getMdate());
//           }
//       }
		//上面给endDate赋值了，这里还要再赋值一次？ 代码注掉 by zhengbs 20220807 start
//		repReveal.setEndDate(params.get("endDate") == null ? null : DateUtil.parse(params.get("endDate").toString()));
		//上面给endDate赋值了，这里还要再赋值一次？ 代码注掉 by zhengbs 20220807 end

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
		repReveal.setReportCategory(reportInfo.getReportCategory());
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

    public UploadFile genReportFileForCheck(Map<String,Object> dataMap) throws RuntimeException{

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
        String localPathFile = reportFileService.getCheckFileName(repId, genDate, dataMap, ReportFileService.FILE_TYPE_DOCX);
		String[]ss=StringUtil.split(localPathFile,"/");
		String fileName=ss[ss.length-1];

		ByteArrayOutputStream bos = null;
        ByteArrayInputStream is = null;
        try {
            bos = new ByteArrayOutputStream();
            // 替换参数
            PoiUtils.mergeDocx(templateFile, bos, dataMap);
            is = new ByteArrayInputStream(bos.toByteArray());
            File vFile = new SafelyFile(localPathFile);
            UploadFile uploadFile = new UploadFile(fileName,IDGenerator.generate(),vFile);
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


	public String dataCheck(Map<String, Object> params){
		StringBuffer resultSb=new StringBuffer();

		String reportId = params.get("repid").toString();   //报表编号
		ReportInfo reportInfo = reportInfoMapper.getReportInfoByID(reportId);

		params.put("pprodId", params.get("pprodId")==null?params.get("pprodid"):params.get("pprodId"));
		params.put("prodid", params.get("prodid")==null?params.get("pprodid"):params.get("prodid"));  //单产品只传了pprodid
		params.put("prodId", params.get("prodId")==null?params.get("prodid"):params.get("prodId"));
		params.put("repId", params.get("repId")==null?params.get("repid"):params.get("repId"));

		RepReveal repReveal = poiReportService.getRepReveal(params);

		Map<String, Object> dataMap = null;
		List<Map<String, Object>> listReports = reportDataService.queryDatas(params);
		if(listReports==null){
			return "报告期末时点距离当前产品的生成日或者到期日不足90个自然日，无需生成报告<br>";
		}
		if (listReports.size() > 0) {
			dataMap = listReports.get(0);
			dataMap.put("prodId",repReveal.getProdid());
			dataMap.put("repId",params.get("repid"));
		} else{
			return resultSb.toString();
		}

		String rptType = params.get("uri").toString();
		String prodId = StringUtil.isNotEmpty(repReveal.getProdid())?repReveal.getProdid():params.get("prodid").toString();

		if (StringUtil.isNotEmpty(rptType)) {
			if (StringUtil.equals(rptType,DisReportType.FXGG)) {
				//发行公告
				//产品募集金额只能是整数，不能出现角和分；
				if(StringUtil.isNotEmpty(dataMap.get("raiseAmtVal"))){
					BigDecimal raiseAmt=(BigDecimal)dataMap.get("raiseAmtVal");
					if(!(new BigDecimal(raiseAmt.intValue()).compareTo(raiseAmt)==0)){
						resultSb.append("产品募集金额只能是整数"+"<br>");
					}
				}

			}else if (StringUtil.equals(rptType,DisReportType.KFJZJFHGG_BNYS)
					||StringUtil.equals(rptType,DisReportType.KFJZJFHGG_BNYN)){
				//	开放、净值及分红公告
				//分红权益登记日一定是对应产品某一开放周期的开放结束日（根据产品排期计划判断）或产品的到期日（根据产品分红到期计划判断）
				String baseDate=(String) dataMap.get("baseDate");
				if(poiReportMapper.checkBaseDate(prodId,baseDate)<1){
					resultSb.append("分红权益登记日必须对应产品某一开放周期的开放结束日或产品的到期日"+"<br>");
				}

			}else if (StringUtil.equals(rptType,DisReportType.JZJFHBG_SMKF)){
				//净值及分红报告
				//分红权益登记日一定是对应产品某一开放周期的开放结束日（根据产品排期计划判断）或产品的到期日（根据产品分红到期计划判断）
				String settDate=(String) dataMap.get("settDate");
				if(poiReportMapper.checkBaseDate(prodId,settDate)<1){
					resultSb.append("分红权益登记日必须对应产品某一开放周期的开放结束日或产品的到期日"+"<br>");
				}
			}
			else if(StringUtil.equals(rptType,DisReportType.JDBG_ZSX)
					||StringUtil.equals(rptType,DisReportType.JDBG_FZSX)
					||StringUtil.equals(rptType,DisReportType.BNDBG_ZSX)
					||StringUtil.equals(rptType,DisReportType.BNDBG_FZSX)
					||StringUtil.equals(rptType,DisReportType.NDBG_ZSX)
					||StringUtil.equals(rptType,DisReportType.NDBG_FZSX)){
				//先把规则查询出来
				List<RepDataCheck>checkList=poiReportMapper.getRepDataCheckList();
				Map<String,List<RepDataCheck>>checkMap=checkList.stream().collect(JoyinCollectors.groupingBy(x->x.getCheckId()));
				//季报、半年报、年报
				//获取产品信息
				RepProdInfo prodInfo=poiReportMapper.getProdInfo(prodId);

				//获取报告期末产品资产组合情况
				Map<String,Object> prodAssetComb=(Map<String,Object>)dataMap.get("prodAssetComb");

				if(StringUtil.isNotEmpty(prodInfo)){
					//	当产品的“投资性质”为“固定收益类”时，【报告期末产品资产组合情况】-固定受益类穿透前及穿透后占比必须大于等于80%； 100000001
					if(StringUtil.equals(prodInfo.getNatureInvest(), DictCodeListExt5.信披投资性质_固定收益类.getValue())){
						RepDataCheck repDataCheck=checkMap.get("100000001").get(0);
						String tips1="当产品的“投资性质”为“固定收益类”时，【报告期末产品资产组合情况】-固定受益类穿透前占比必须";
						//穿透前
						if(StringUtil.isNotEmpty(prodAssetComb.get("fixPreRatio"))){
							BigDecimal fixPreRatio=(BigDecimal)prodAssetComb.get("fixPreRatio");
							String s1=this.singleCheck(repDataCheck,fixPreRatio,tips1);
							if (StringUtil.isNotEmpty(s1)) {
								resultSb.append(s1+"<br>");
							}
						}else{
							resultSb.append("固定受益类穿透前占比未获取到"+"<br>");
						}
						//穿透后
						if(StringUtil.isNotEmpty(prodAssetComb.get("fixAfterRatio"))){
							String tips2="当产品的“投资性质”为“固定收益类”时，【报告期末产品资产组合情况】-固定受益类穿透后占比必须";
							BigDecimal fixAfterRatio=(BigDecimal)prodAssetComb.get("fixAfterRatio");
							String s2=this.singleCheck(repDataCheck,fixAfterRatio,tips2);
							if (StringUtil.isNotEmpty(s2)) {
								resultSb.append(s2+"<br>");
							}
						}else{
							resultSb.append("固定受益类穿透后占比未获取到"+"<br>");
						}
					}
					//	当产品的“投资性质”为“权益类”时，【报告期末产品资产组合情况】-权益类穿透前及穿透后占比必须大于等于80%； 100000002
					else if(StringUtil.equals(prodInfo.getNatureInvest(), DictCodeListExt5.信披投资性质_权益类.getValue())){
						RepDataCheck repDataCheck=checkMap.get("100000002").get(0);
						//穿透前
						if(StringUtil.isNotEmpty(prodAssetComb.get("equityPreRatio"))){
							BigDecimal equityPreRatio=(BigDecimal)prodAssetComb.get("equityPreRatio");
							String tips1="当产品的“投资性质”为“权益类”时，【报告期末产品资产组合情况】-权益类穿透前占比必须";
							String s1=this.singleCheck(repDataCheck,equityPreRatio,tips1);
							if (StringUtil.isNotEmpty(s1)) {
								resultSb.append(s1+"<br>");
							}
						}else{
							resultSb.append("权益类穿透前占比未获取到"+"<br>");
						}
						//穿透后
						if(StringUtil.isNotEmpty(prodAssetComb.get("equityAfterRatio"))){
							BigDecimal equityAfterRatio=(BigDecimal)prodAssetComb.get("equityAfterRatio");
							String tips2="当产品的“投资性质”为“权益类”时，【报告期末产品资产组合情况】-权益类穿透后占比必须";
							String s2=this.singleCheck(repDataCheck,equityAfterRatio,tips2);
							if (StringUtil.isNotEmpty(s2)) {
								resultSb.append(s2+"<br>");
							}
						}else{
							resultSb.append("权益类穿透后占比未获取到"+"<br>");
						}

					}
					//	当产品的“投资性质”为“商品及衍生品类”时，【报告期末产品资产组合情况】-商品及衍生品类穿透前及穿透后占比必须大于等于80%； 100000003
					else if(StringUtil.equals(prodInfo.getNatureInvest(), DictCodeListExt5.信披投资性质_商品及金融衍生品类.getValue())){
						RepDataCheck repDataCheck=checkMap.get("100000003").get(0);
						//穿透前
						if(StringUtil.isNotEmpty(prodAssetComb.get("fdPreRatio"))){
							BigDecimal fdPreRatio=(BigDecimal)prodAssetComb.get("fdPreRatio");
							String tips1="当产品的“投资性质”为“商品及衍生品类”时，【报告期末产品资产组合情况】-商品及衍生品类穿透前占比必须";
							String s1=this.singleCheck(repDataCheck,fdPreRatio,tips1);
							if (StringUtil.isNotEmpty(s1)) {
								resultSb.append(s1+"<br>");
							}
						}else{
							resultSb.append("商品及衍生品类穿透前占比未获取到"+"<br>");
						}

						//穿透后
						if(StringUtil.isNotEmpty(prodAssetComb.get("fdAfterRatio"))){
							BigDecimal fdAfterRatio=(BigDecimal)prodAssetComb.get("fdAfterRatio");
							String tips2="当产品的“投资性质”为“商品及衍生品类”时，【报告期末产品资产组合情况】-商品及衍生品类穿透后占比必须";
							String s2=this.singleCheck(repDataCheck,fdAfterRatio,tips2);
							if (StringUtil.isNotEmpty(s2)) {
								resultSb.append(s2+"<br>");
							}
						}else{
							resultSb.append("商品及衍生品类穿透后占比未获取到"+"<br>");
						}
					}
					//	当产品的“投资性质”为“混合类”时，【报告期末产品资产组合情况】-固定收益类、权益类、商品及衍生品类穿透前及穿透后占比必须小于80%； 100000004
					else if(StringUtil.equals(prodInfo.getNatureInvest(), DictCodeListExt5.信披投资性质_混合类.getValue())){
						RepDataCheck repDataCheck=checkMap.get("100000004").get(0);
						//穿透前
						if(StringUtil.isNotEmpty(prodAssetComb.get("mixPreRatio"))){
							BigDecimal mixPreRatio=(BigDecimal)prodAssetComb.get("mixPreRatio");
							String tips1="当产品的“投资性质”为“混合类”时，【报告期末产品资产组合情况】-固定收益类、权益类、商品及衍生品类穿透前占比必须";
							String s1=this.singleCheck(repDataCheck,mixPreRatio,tips1);
							if (StringUtil.isNotEmpty(s1)) {
								resultSb.append(s1+"<br>");
							}
						}else{
							resultSb.append("固定收益类、权益类、商品及衍生品类穿透前占比未获取到"+"<br>");
						}
						if(StringUtil.isNotEmpty(prodAssetComb.get("mixAfterRatio"))){
							//穿透后
							BigDecimal mixAfterRatio=(BigDecimal)prodAssetComb.get("mixAfterRatio");
							String tips2="当产品的“投资性质”为“混合类”时，【报告期末产品资产组合情况】-固定收益类、权益类、商品及衍生品类穿透后占比必须";
							String s2=this.singleCheck(repDataCheck,mixAfterRatio,tips2);
							if (StringUtil.isNotEmpty(s2)) {
								resultSb.append(s2+"<br>");
							}
						}else{
							resultSb.append("固定收益类、权益类、商品及衍生品类穿透后占比未获取到"+"<br>");
						}

					}
				}

					if(StringUtil.equals(rptType,DisReportType.BNDBG_ZSX)
						||StringUtil.equals(rptType,DisReportType.BNDBG_FZSX)
						||StringUtil.equals(rptType,DisReportType.NDBG_ZSX)
						||StringUtil.equals(rptType,DisReportType.NDBG_FZSX)){
						//半年报、年报：
						//TODO:
						//	当产品的募集方式为“公募”，发行方式为“开放式”时，“报告期末产品杠杆水平”小于等于140； 100000005
						if(StringUtil.equals(prodInfo.getRaiseType(),DictCodeListExt5.信披募集方式_公募.getValue())
						&&StringUtil.equals(prodInfo.getIssuingWay(),DictCodeListExt5.发行方式_开放式.getValue())){
							RepDataCheck repDataCheck=checkMap.get("100000005").get(0);
							if(StringUtil.isNotEmpty(dataMap.get("leverage"))){
								BigDecimal leverage=(BigDecimal)dataMap.get("leverage");
								String tips1="当产品的募集方式为“公募”，发行方式为“开放式”时，“报告期末产品杠杆水平”";
								String s1=this.singleCheck(repDataCheck,leverage,tips1);
								if (StringUtil.isNotEmpty(s1)) {
									resultSb.append(s1+"<br>");
								}
							}else {
								resultSb.append("报告期末产品杠杆水平未获取到"+"<br>");
							}

						}

						//	当“产品类型”为“现管类产品”时，“报告期末产品杠杆水平”小于等于120； 100000006
						//	当“产品类型”为“现管类”时，【报告期末产品资产组合情况】-权益类穿透前及穿透后占比必须等于0% 100000009
						if(StringUtil.equals(prodInfo.getProdType(),DictCodeListExt5.信披产品类型_现管类产品.getValue())){
							RepDataCheck repDataCheck=checkMap.get("100000006").get(0);
							if(StringUtil.isNotEmpty(dataMap.get("leverage"))){
								BigDecimal leverage=(BigDecimal)dataMap.get("leverage");
								String tips1="当“产品类型”为“现管类产品”时，“报告期末产品杠杆水平”";
								String s1=this.singleCheck(repDataCheck,leverage,tips1);
								if (StringUtil.isNotEmpty(s1)) {
									resultSb.append(s1+"<br>");
								}
							}else {
								resultSb.append("报告期末产品杠杆水平未获取到"+"<br>");
							}

							RepDataCheck repDataCheck2=checkMap.get("100000009").get(0);
							//穿透前
							if(StringUtil.isNotEmpty(prodAssetComb.get("equityPreRatio"))){
								BigDecimal equityPreRatio=(BigDecimal)prodAssetComb.get("equityPreRatio");
								String tips1="当“产品类型”为“现管类”时，【报告期末产品资产组合情况】-权益类穿透前占比必须";
								String s1=this.singleCheck(repDataCheck2,equityPreRatio,tips1);
								if (StringUtil.isNotEmpty(s1)) {
									resultSb.append(s1+"<br>");
								}
							}else{
								resultSb.append("权益类穿透前占比未获取到"+"<br>");
							}
							//穿透后
							if(StringUtil.isNotEmpty(prodAssetComb.get("equityAfterRatio"))){
								BigDecimal equityAfterRatio=(BigDecimal)prodAssetComb.get("equityAfterRatio");
								String tips2="当“产品类型”为“现管类”时，【报告期末产品资产组合情况】-权益类穿透后占比必须";
								String s2=this.singleCheck(repDataCheck2,equityAfterRatio,tips2);
								if (StringUtil.isNotEmpty(s2)) {
									resultSb.append(s2+"<br>");
								}
							}else{
								resultSb.append("权益类穿透后占比未获取到"+"<br>");
							}

						}

						//	当产品的募集方式为“私募”且“是否分级产品”为“否”或产品募集方式为“公募”且发行方式为“封闭式”时，“报告期末产品杠杆水平”小于等于200； 100000007
						if((StringUtil.equals(prodInfo.getRaiseType(),DictCodeListExt5.信披募集方式_公募.getValue())
								&&StringUtil.equals(prodInfo.getIssuingWay(),DictCodeListExt5.发行方式_封闭式.getValue())
						||(StringUtil.equals(prodInfo.getRaiseType(),DictCodeListExt5.信披募集方式_私募.getValue())
								&&StringUtil.equals(prodInfo.getIsGrade(), DictCodeList.是否_中债_否.getValue())))){
							RepDataCheck repDataCheck=checkMap.get("100000007").get(0);
							if(StringUtil.isNotEmpty(dataMap.get("leverage"))){
								BigDecimal leverage=(BigDecimal)dataMap.get("leverage");
								String tips1="当产品的募集方式为“私募”且“是否分级产品”为“否”或产品募集方式为“公募”且发行方式为“封闭式”时，“报告期末产品杠杆水平”";
								String s1=this.singleCheck(repDataCheck,leverage,tips1);
								if (StringUtil.isNotEmpty(s1)) {
									resultSb.append(s1+"<br>");
								}
							}else {
								resultSb.append("报告期末产品杠杆水平未获取到"+"<br>");
							}
						}

						//	当产品募集方式为“私募”且“是否是分级产品”为“是”的时候，“报告期末产品杠杆水平”小于等于140； 100000008
						if(StringUtil.equals(prodInfo.getRaiseType(),DictCodeListExt5.信披募集方式_私募.getValue())
								&&StringUtil.equals(prodInfo.getIsGrade(), DictCodeList.是否_中债_是.getValue())){
							RepDataCheck repDataCheck=checkMap.get("100000008").get(0);
							if(StringUtil.isNotEmpty(dataMap.get("leverage"))){
								BigDecimal leverage=(BigDecimal)dataMap.get("leverage");
								String tips1="当产品募集方式为“私募”且“是否是分级产品”为“是”的时候，“报告期末产品杠杆水平”";
								String s1=this.singleCheck(repDataCheck,leverage,tips1);
								if (StringUtil.isNotEmpty(s1)) {
									resultSb.append(s1+"<br>");
								}
							}else {
								resultSb.append("报告期末产品杠杆水平未获取到"+"<br>");
							}
						}
					}

					//对于定期报告中需要从【报表指标管理】或“合作机构信息维护”、“产品资产组合情况信息维护”、“非标债权类资产明细信息维护”“关联交易情况信息维护”中取值的统计项，
					// 若在生成报告时，上述内容取不到值或无有效数据（系统生成报告的日期在数据的生效期及有效截止日期之间），也需要在本界面生成一条对应数据项为空的提示；
					//TODO:

				//对于开放式产品，若在定期报告的统计期内产品业绩比较基准发生了变更（由非指数型产品变为指数型产品，或指数型产品挂钩的行情代码由A变为B）需要在本模块给出提示：
				if(StringUtil.equals(prodInfo.getIssuingWay(),DictCodeListExt5.发行方式_开放式.getValue())){
					//TODO:
				}
			}

			//对所有报告的通用校验
			//生成报告时，若存在某个统计项取不到值的情况，需要在本模块进行提示 提示“生成报告所需数据有缺失”
			for (String key:dataMap.keySet()){
				if(!key.contains("_xx")){
					if (!StringUtil.isNotEmpty(dataMap.get(key))) {
						if (!dataMap.containsKey(key+"_xx")) {
							if(dataMap.get(key) instanceof String){
								dataMap.put(key,"xxx");
							}
							resultSb.append("生成报告所需数据有缺失key="+key+"<br>");
						}
					}
				}

			}

			//校验完成之后判断有没有错误
			if(StringUtil.isNotEmpty(resultSb)){
				//生成文件
				UploadFile uploadFile=poiReportService.genReportFileForCheck(dataMap);

				RepReportCheck repReportCheck=new RepReportCheck();

				repReportCheck.setReportCheckId(IDGenerator.generate(32));
				repReportCheck.setRepId(reportId);
				repReportCheck.setReportType(reportInfo.getReportCategory());
				repReportCheck.setReportName(uploadFile.getFileName());
				repReportCheck.setProdId(prodId);
				repReportCheck.setCheckDate((Date)params.get("platformDate"));
				repReportCheck.setEndDate(repReveal.getEndDate());
				repReportCheck.setErrormsg(resultSb.toString().replace("<br>",""));
				repReportCheck.setFileNo(uploadFile.getFileNo());
				repReportCheck.setDataStatus("E");

				//入库REP_REPORT_CHECK
				//数据全删全插
				poiReportMapper.deleteCheckData(repReportCheck);

				repReportCheckMapper.insertSelective(repReportCheck);

				resultSb.append("生成信披报告所需数据有缺失，请到【报表数据校验】功能查阅当前报表详情。点击“确认”按钮，可通过在线编辑的形式对当前报表进行编辑");
			}
		}

		return resultSb.toString();
	}

	private String singleCheck(RepDataCheck repDataCheck,BigDecimal val,String tips){
		String result="";
		if(StringUtil.equals(repDataCheck.getCheckType(),DictCodeListExt5.信披数据校验比较符_小于)){
			BigDecimal compareVal=new BigDecimal(repDataCheck.getParamValue1());
			if(val.compareTo(compareVal)>=0){
				result=tips+"小于"+repDataCheck.getParamValue1();
			}
		} else if (StringUtil.equals(repDataCheck.getCheckType(),DictCodeListExt5.信披数据校验比较符_小于等于)) {
			BigDecimal compareVal=new BigDecimal(repDataCheck.getParamValue1());
			if(val.compareTo(compareVal)>0){
				result=tips+"小于等于"+repDataCheck.getParamValue1();
			}
		}else if (StringUtil.equals(repDataCheck.getCheckType(),DictCodeListExt5.信披数据校验比较符_大于)) {
			BigDecimal compareVal=new BigDecimal(repDataCheck.getParamValue1());
			if(val.compareTo(compareVal)<=0){
				result=tips+"大于"+repDataCheck.getParamValue1();
			}
		}else if (StringUtil.equals(repDataCheck.getCheckType(),DictCodeListExt5.信披数据校验比较符_大于等于)) {
			BigDecimal compareVal=new BigDecimal(repDataCheck.getParamValue1());
			if(val.compareTo(compareVal)<0){
				result=tips+"大于等于"+repDataCheck.getParamValue1();
			}
		}else if (StringUtil.equals(repDataCheck.getCheckType(),DictCodeListExt5.信披数据校验比较符_范围控制_不包含临界值)) {
			BigDecimal paramValue1=new BigDecimal(repDataCheck.getParamValue1());
			BigDecimal paramValue2=new BigDecimal(repDataCheck.getParamValue2());
			if(!(val.compareTo(paramValue1)>0&&val.compareTo(paramValue2)<0)){
				result=tips+"不在区间"+repDataCheck.getParamValue1()+","+repDataCheck.getParamValue2()+"内";
			}

		}else if (StringUtil.equals(repDataCheck.getCheckType(),DictCodeListExt5.信披数据校验比较符_范围控制_包含较小的临界值)) {
			BigDecimal paramValue1=new BigDecimal(repDataCheck.getParamValue1());
			BigDecimal paramValue2=new BigDecimal(repDataCheck.getParamValue2());
			if(!(val.compareTo(paramValue1)>=0&&val.compareTo(paramValue2)<0)){
				result=tips+"不在区间"+repDataCheck.getParamValue1()+"（含）,"+repDataCheck.getParamValue2()+"内";
			}
		}else if (StringUtil.equals(repDataCheck.getCheckType(),DictCodeListExt5.信披数据校验比较符_范围控制_包含较大的临界值)) {
			BigDecimal paramValue1=new BigDecimal(repDataCheck.getParamValue1());
			BigDecimal paramValue2=new BigDecimal(repDataCheck.getParamValue2());
			if(!(val.compareTo(paramValue1)>0&&val.compareTo(paramValue2)<=0)){
				result=tips+"不在区间"+repDataCheck.getParamValue1()+","+repDataCheck.getParamValue2()+"（含）内";
			}
		}else if (StringUtil.equals(repDataCheck.getCheckType(),DictCodeListExt5.信披数据校验比较符_等于)) {
			BigDecimal compareVal=new BigDecimal(repDataCheck.getParamValue1());
			if(val.compareTo(compareVal)!=0){
				result=tips+"等于"+repDataCheck.getParamValue1();
			}
		}

		return result;
	}


	
}
