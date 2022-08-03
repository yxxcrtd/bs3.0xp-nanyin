/**
 * 
 */
package com.joyintech.tams.disclosure.busicommon.poireport.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public final static String REPORT_TYPE_模板文件 = "REPORT_TYPE_M";
	public final static String REPORT_TYPE_临时文件 = "REPORT_TYPE_T";
	public final static String REPORT_TYPE_报表文件 = "REPORT_TYPE_E";

	public final static String FILE_TYPE_DOCX = ".docx";
	public final static String FILE_TYPE_PDF = ".pdf";

	/**
	 * 生成报表：创建服务器文件，创建工作流进行报表确认
	 * @param params
	 * @param reportFileType
	 * @param opType：报表生成类型：preview--预览，gen--生成
	 * @return
	 * @throws Exception
	 */
	public String genReport(Map<String, Object> params, String reportFileType,String opType) throws Exception {

			String reportFile = "";
			ReportInfo reportInfo = null;
			String prodId = "";
			
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
					reportDate = DateUtil.parse(params.get("reportDate").toString());
			} catch (Exception e) {
				logger.error("获取报表日期错误", e);
			}
			
		if ( "preview".equals(opType) ) {  //预览，同时生成报表文件
			//查询数据
			Map<String, Object> dataMap = null;
			if ( REPORT_TYPE_模板文件.equals(reportFileType) ) {  //模板文件
				dataMap = new HashMap<>();
			} else {		//生成报表文件：正式文件
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
			}
			if ( dataMap.get("reportDate")!=null ) 
				reportDate = (Date)dataMap.get("reportDate");
			
			reportFile = this.genReportFile(reportFileType, reportInfo, dataMap, reportDate);
			logger.info("生成文件成功!" + reportFile);
		} else if (opType.equals("gen")) {  //直接使用预览的文件
			prodId = params.get("prodId").toString();
			reportFile = buildReportFileName(REPORT_TYPE_报表文件, reportInfo, reportDate, prodId, FILE_TYPE_DOCX);
			File localFile = new SafelyFile(reportFile);
			String fileName = prodId
					+ GlobalConstant.UNDER_LINE +reportInfo.getReportName() 
					+ GlobalConstant.UNDER_LINE 
					+ DateUtil.getNewSysDate()
					+ FILE_TYPE_DOCX;
			UploadFile uploadFile = new UploadFile(fileName, IDGenerator.generate(), localFile);
			logger.info("UploadFile fileName= "  + reportFile);
			
	        if(null != fileAttachmentProcess) {
	            fileAttachmentProcess.upload(uploadFile);
	        }
			// 创建工作流
			boolean flag = insertRepReveal(params, uploadFile.getFileNo(), uploadFile.getSrcName(),
					reportInfo.getReportName(), reportInfo.getReportCategory());
			if (flag == false) {
				logger.error("生成信息主表错误!");
			}
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
	public String genReportFile(String reportType, ReportInfo reportInfo, Map<String, Object> dataMap,
			Date reportDate) {
		String imagePath = "";
		String templateFile = "";
		String reportFile = "";

		templateFile = buildReportFileName(REPORT_TYPE_模板文件, reportInfo, null, null, FILE_TYPE_DOCX);
		if ( dataMap==null || dataMap.size()<1 ) {
			//初始化参数，换个文件名称
			File f = new File(templateFile);
			return f.getParent() + "/blank/"+f.getName();
			
			//return templateFile;
		}
		
		if (reportDate == null)
			reportDate = commonPlatformDateService.getSysDate();
		String prodId = (dataMap.get("prodId") == null ? "" : dataMap.get("prodId").toString());
		reportFile = buildReportFileName(reportType, reportInfo, reportDate, prodId, FILE_TYPE_DOCX);

		FileOutputStream fos = null;
		InputStream is = null;
		try {
			// 替换参数
			fos = new FileOutputStream(reportFile);
			PoiUtils.mergeDocx(templateFile, fos, dataMap);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.flush();
					fos.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				logger.error("生成报表文件错误.", e);
				e.printStackTrace();
			}
		}
		return reportFile;
	}

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
			// 查询产品名称的全称
			// String prodName =
			// repDetailsShow001Mapper.getProdName(params.get("prodName").toString());
			String prodName = params.get("prodName").toString();
//           repReveal.setProdName(params.get("prodName").toString());
			repReveal.setProdName(prodName);
		}
		if (StringUtil.isNotEmpty(params.get("startDate"))) {
			repReveal.setStartDate(DateUtil.parse(params.get("startDate").toString()));
		}
		if (StringUtil.isNotEmpty(params.get("endDate"))) {
			repReveal.setEndDate(DateUtil.parse(params.get("endDate").toString()));
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

	/**
	 * 获取模板或者报表文件url
	 * 
	 * @param reportType：M-模板文件，T-临时文件，E-正式文件
	 * @param reportInfo                      模板信息
	 * @param reportDate                      生成日期
	 * @param reportDate                      报表日期
	 * @param portnum                         关键字
	 * @param fileType
	 * @return
	 */
	public String buildReportFileName(String reportType, ReportInfo reportInfo, Date reportDate, String portnum,
			String fileType) {
		// String templatePath =
		// CacheUtil.getSysParam(ReportGlobalConstant.TEMPLATE_FILE_PATH);
		String reportPath = CacheUtil.getSysParam(ReportGlobalConstant.REPORT_FILE_PATH);
		String tempPath = CacheUtil.getSysParam(ReportGlobalConstant.TEMP_FILE_PATH);
		String filename = "";
		if (REPORT_TYPE_模板文件.equals(reportType)) { // 模板文件
			filename = reportInfo.getRepository() + reportInfo.getFolder() + "/" + reportInfo.getUri() + fileType;
			File f = new File(filename);
			if (!f.exists())
				filename = reportInfo.getRepository() + reportInfo.getFolder() + "/" + "ERROR" + fileType;

		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date genDate = commonPlatformDateService.getSysDate();
			String dirname = sdf.format(genDate);
			StringBuffer sb = new StringBuffer();
			if (REPORT_TYPE_临时文件.equals(reportType)) {
				sb.append(tempPath);
			} else { // 正式文件
				sb.append(reportPath);
			}
			sb.append(dirname);
			sb.append("/");
			sb.append(reportInfo.getReportName());
			sb.append("_");
			sb.append(portnum);
			sb.append("_");
			sb.append(sdf.format(reportDate));
			sb.append(fileType);
			// 用日期创建路径
			File file = new File(sb.toString());
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			filename = sb.toString();
		}
		return filename;
	}
	
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
	
    public RepReveal getRepReveal( Map<String,Object>param) {
    	return reportDataService.getRepReveal(param);
    }
}
