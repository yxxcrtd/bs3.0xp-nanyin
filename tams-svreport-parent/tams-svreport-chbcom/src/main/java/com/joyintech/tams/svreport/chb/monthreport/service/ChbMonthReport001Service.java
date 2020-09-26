/*
 * 文件名：InvestorsHoldInfoRegist001Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：zuoyinyi
 * 修改时间：2018年5月8日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.svreport.chb.monthreport.service;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.ZipFileUtil;
import com.joyintech.tams.common.svreport.chb.constant.CheckConfConstants;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.common.svreport.datacheck.dao.ChbCheckRuleoutMapper;
import com.joyintech.tams.common.svreport.datacheck.dto.ChbCheckRuleout;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.service.SvrCommonExportService;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.interf.common.util.TisUtil;
import com.joyintech.tams.report.submitmanagement.checkreport.dao.CheckReport001Mapper;
import com.joyintech.tams.report.submitmanagement.checkreport.dto.CheckReport001Dto;
import com.joyintech.tams.svreport.chb.datacheck.beforesub.service.BeforeSubCheck;
import com.joyintech.tams.svreport.chb.monthreport.dao.ChbMonthReportDetailMapper;
import com.joyintech.tams.svreport.chb.monthreport.dao.ChbMonthReportMapper;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReport;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReportDetail;
import com.joyintech.tools.collectors.JoyinCollectors;

import net.sf.jxls.transformer.XLSTransformer;
/**
 * 银行月度报表登记service
 *
 * @author yuluan
 * @version 1.0 2019年2月20日
 * @since 1.0
 */
@Service
public class ChbMonthReport001Service extends SvrCommonExportService<ChbMonthReport> {
//	@Autowired
//    private  MonthReportAssetService monthReportAssetService;
	@Autowired
    private ChbMonthReportMapper  chbMonthReportMapper;
	@Autowired
    private  ChbMonthReportDetailMapper chbMonthReportDetailMapper;
	@Autowired
	private CheckReport001Mapper checkReport001Mapper;
	@Autowired
	private ChbCheckRuleoutMapper chbCheckRuleoutMapper;
	@Autowired
	private BeforeSubCheck beforeSubCheck;
	@Autowired
    private MonthReportRegistBatchService monthReportRegistBatchService;


    /**
     * 短sql最大一次最大插入条数
     */
    @Value("${application.reportSelectOrDeleteCountForShenFen}")
    public  int reportSelectOrDeleteCountForShenFen;
    
    private Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 查询页面数据
	 *
	 * @param pageParam
	 *            查询条件
	 * @return 结果列表
	 * @see
	 * @since 1.0
	 */
	public Page<ChbMonthReport> searchPage(Map<String, Object> pageParam) {
		dealSearchParam(pageParam);
		//将发送状态改为空
		pageParam.put("sendStatus", "");
		int pagenum = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_PAGE).toString());
        int legth = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_LENGTH).toString());
		Page<ChbMonthReport> page = queryForPage(
				pagenum,legth,()->chbMonthReportMapper.searchPage(pageParam));
		return page;
	}

	/**
	 * 处理查询前的参数 <功能详细描述>
	 *
	 * @param pageParam
	 * @see
	 * @since 1.0
	 */
	public void dealSearchParam(Map<String, Object> pageParam) {
		// 选择行数据时
		if (pageParam.containsKey("keyList")) {
			String keyList = pageParam.get("keyList").toString();
			try {
				List<ChbMonthReport> astValueList = JsonUtils.json2List(keyList,
						ChbMonthReport.class);
				pageParam.put("reportList", astValueList);
			} catch (IOException e) {
//				e.printStackTrace();
	            logger.error(e.getMessage(),e);
			}
		} else {
			// 查询参数条件
			// 数据状态
			String status = (String) pageParam.get("status");
			// 报送状态
			String sendStatus = (String) pageParam.get("sendStatus");
			// 反馈状态
			String submitFeedbackStatus = (String) pageParam.get("submitFeedbackStatus");


			// 将数据状态当做多选下拉框处理
			if (StringUtil.isNotBlank(status)) {
				pageParam.put("statusList", StringUtil.splitToList(GlobalConstant.COMMA, status));
			}
			// 将报送状态当做多选下拉框处理
			if (StringUtil.isNotBlank(sendStatus)) {
				pageParam.put("sendStatusList", StringUtil.splitToList(GlobalConstant.COMMA, sendStatus));
			}
			// 将反馈状态当做多选下拉框处理
			if (StringUtil.isNotBlank(submitFeedbackStatus)) {
				pageParam.put("submitFeedbackStatusList",
						StringUtil.splitToList(GlobalConstant.COMMA, submitFeedbackStatus));
			}

		}
	}

	/**
	 * 导出功能查询
	 *
	 * @param pageParam
	 *            查询参数
	 * @return 结果集
	 * @see
	 * @since 1.0
	 */

	@SuppressWarnings("unchecked")
	public List<ChbMonthReport> selectAllList(Map<String, Object> pageParam) {
		dealSearchParam(pageParam);
		List<ChbMonthReport> dataList =  (List<ChbMonthReport>) pageParam.get("astValueList");// 查询参数列表

		List<ChbMonthReport> dtoList = new ArrayList<ChbMonthReport>();// 存放查询结果集
		if(CollectionUtils.isNotEmpty(dataList)){
//			selectAllDataBach(dataList,dtoList);
		}
		else{
			dtoList = chbMonthReportMapper.searchPage(pageParam);
		}


		return dtoList;
	}
	/**
     * 查询页面数据
     *
     * @param pageParam
     *            查询条件
     * @return 结果列表
     * @see
     * @since 1.0
     */
    public ChbMonthReport searchPageBysubmitDate(Map<String, Object> pageParam) {
        Map<String, Object> param = new HashMap<String, Object>();
//        String submitDateStart = (String) pageParam.get("submitDateStart");
//        if (!StringUtil.isEmpty(submitDateStart)
//                && submitDateStart.length() >= 7){
//            submitDateStart = submitDateStart.substring(0,4)+submitDateStart.substring(5,7);
//            param.put("termidDateStart", submitDateStart);
//        }
//        String submitDateEnd = (String) pageParam.get("submitDateEnd");
//        if (!StringUtil.isEmpty(submitDateEnd)
//                && submitDateEnd.length() >= 7){
//            submitDateEnd = submitDateEnd.substring(0,4)+submitDateEnd.substring(5,7);
//            param.put("termidDateEnd", submitDateEnd);
//        }
        if(pageParam.get("enddate")!=null){
        	String end= pageParam.get("enddate").toString().replace("-", "");
        	param.put("termid", end);
        }
        List<ChbMonthReport> pbcMonthReportList = chbMonthReportMapper.searchPage(param);
        ChbMonthReport repos = new ChbMonthReport();
        for (ChbMonthReport report : pbcMonthReportList){
            if (repos.getStartdate() == null){
                repos.setStartdate(report.getStartdate());
            } 
            // 取最小的开始日期
            if (repos.getStartdate().compareTo(report.getStartdate()) > 0){
                repos.setStartdate(report.getStartdate());
            }
            //有未报送
            if(report.getSendStatus().equals("01")){
            	repos.setSendStatus(report.getSendStatus());
            }else{//有已报送
            	repos.setStatus("02");
            }
        }
        return repos;
    }
	 @Transactional(rollbackFor = Exception.class)
	 public String recalReport(Map<String, Object> map){
		 String submitDateEnd = (String) map.get("enddate");
	        Date endDate = DateUtil.parse(submitDateEnd+"-01");
		  Calendar calendar = Calendar.getInstance();
	        calendar.setTime(endDate);
	        calendar.add(Calendar.MONTH, 1);
	        calendar.set(Calendar.DAY_OF_MONTH, 1);
		 monthReportRegistBatchService.genReport(calendar.getTime(), null, true);
		return "success";
		 
	 }
	
    /**
 	 * <一句话功能简述> <功能详细描述>
 	 *
 	 * @param upKey
 	 * @see
 	 * @since 1.0
 	 */
	@Transactional(rollbackFor = Exception.class)
 	@SuppressWarnings("unchecked")
 	public void updateSumStaus(Map<String, Object> map) {
 		dealSearchParam(map);
 		List<ChbMonthReport> chbBotAstPositionRegistList  = new ArrayList<ChbMonthReport>();
 		if(map.get("flag") != null && "01".equals(map.get("flag"))){
 			//按条件查询全部
 			chbBotAstPositionRegistList = chbMonthReportMapper.searchPage(map);
 		}else{
 			chbBotAstPositionRegistList = (List<ChbMonthReport>)map.get("reportList");
 		}
 		 Iterator<ChbMonthReport> it=chbBotAstPositionRegistList.iterator();
         while(it.hasNext()){
        	 ChbMonthReport m =  it.next();
 			if(!m.getSendStatus().equals("01")){
 				it.remove();
 			}
 		}
 		
// 		for (ChbMonthReport dto : chbBotAstPositionRegistList) {
//// 			// 报送状态表插入组装
//// 			if (StringUtil.isEmpty(dto.getSumitSeq())) {
//// 				dto.setSumitSeq(CommonUtil.getSequenceIndexData(DataIndexSequenceType.中债登记管理));
//// 			} 
// 			dto.setSubmitFeedbackStatus(DictCodeListExt2.报送反馈状态_中债通过.getValue());
// 		}
 		this.updateInterfSubmitAndSeq(DictCodeListExt2.报送状态_手工处理.getValue(), chbBotAstPositionRegistList);

 	}
 	/**
 	 * 分批次执行updateInterfSubmitAndSeq
 	 *
 	 * @param investList
 	 *            数据
 	 * @see
 	 * @since 1.0
 	 */
 	@Transactional(rollbackFor = Exception.class)
 	public void updateInterfSubmitAndSeq(String sendStatus, List<ChbMonthReport> investList) {
 		if (StringUtil.isEmptyList(investList)) {
 			return;
 		}
 		int maxDataCountOneTime = reportSelectOrDeleteCountForShenFen;
 		int dataCount = investList.size();
 		if (dataCount <= maxDataCountOneTime) {
 			//已报送数据进留痕表
 			chbMonthReportMapper.insertReportHis(investList);
 			//删除正式表已报送数据
 			chbMonthReportMapper.deleteLastSubmitRegist(investList);
 			chbMonthReportMapper.updateInterfSubmitAndSeqBatch(sendStatus, investList);
 			return;
 		}

 	

 		List<ChbMonthReport> newDataList = new ArrayList<ChbMonthReport>();
 		newDataList.addAll(investList);

 		// 分批处理
 		int part = dataCount / maxDataCountOneTime; // 分批数
 		for (int i = 0; i < part; i++) {
 			logger.info("单次更新登记表start===");
 			//已报送数据进留痕表
 			chbMonthReportMapper.insertReportHis(investList);
 			//删除正式表已报送数据
 			chbMonthReportMapper.deleteLastSubmitRegist(investList);
 			chbMonthReportMapper.updateInterfSubmitAndSeqBatch(sendStatus,
                 newDataList.subList(0, maxDataCountOneTime));
 			newDataList.subList(0, maxDataCountOneTime).clear();
 			logger.info("单次更新登记表end===");
 		}
 		// 最后剩下的数据
 		if (CollectionUtils.isNotEmpty(newDataList)) {
 			logger.info("单次更新登记表start===");
 			//已报送数据进留痕表
 			chbMonthReportMapper.insertReportHis(investList);
 			//删除正式表已报送数据
 			chbMonthReportMapper.deleteLastSubmitRegist(investList);
 			chbMonthReportMapper.updateInterfSubmitAndSeqBatch(sendStatus, newDataList);
 			logger.info("单次更新登记表end===");
 		}
 	}

 	
 	/**
 	 * 已手工处理的数据将报送状态改为未报送
 	 *
 	 * @param upKey
 	 *            传入主键
 	 * @see
 	 * @since 1.0
 	 */
 	@Transactional(rollbackFor = Exception.class)
 	@SuppressWarnings("unchecked")
 	public void cancelMannul(Map<String, Object> map) {
 		dealSearchParam(map);
 		List<ChbMonthReport> list = new ArrayList<ChbMonthReport>();
 		if(map.get("flag") != null && "01".equals(map.get("flag"))){
 			list = chbMonthReportMapper.searchPage(map);
 		}else{
 			list = (List<ChbMonthReport>) map.get("reportList");
 		}
 		chbMonthReportMapper.cancelMannultRegist(list);
 		// 将履历表中最后创建时间的数据挪回登记表
 		chbMonthReportMapper.insertReportDataFromHis(list);
 		//将挪回数据从履历表中删除
 		chbMonthReportMapper.deleteReportDataHis(list);
 	}
 	
    @Override
    public ResponseEntity<byte[]> export(String pageId, Map<String, Object> selectParam){
    	@SuppressWarnings("unchecked")
        Map<String,Object> data = restTemplate.postForObject(
    	                SvrServiceApplication.CHB_SERVICE_NAME + "/ChbMonthReport/createData",selectParam,
    	                Map.class);
    	String termname = data.get("termname").toString();
    	String exceltemplate = "Monthreport";
	    String fileName = "银行月度报表"+"_"+termname+".xls";

		try {
			if("G06".equals(selectParam.get("G06"))){
				 return exportG06MonthReportExcel(data,termname);
			}
            return exportMonthReportExcel(data,exceltemplate,fileName);
        } catch (IOException e) {   
            logger.error("导出银行月度报表错误:"+e.getMessage(),e);
            return null;
        }
    
    }
    
    public Map<Object, Object> createData(Map<String, Object> selectParam){
    	dealSearchParam(selectParam);
    	Map<Object,Object> data  = new HashMap<Object,Object>();
    	@SuppressWarnings("unchecked")
		List<ChbMonthReport> dataList =  (List<ChbMonthReport>) selectParam.get("reportList");// 查询参数列表
    	String reportuuid = "";			
    	if(dataList!=null&&dataList.size()>0){
    		reportuuid =dataList.get(0).getReportuuid();
    	}else{
    		reportuuid =selectParam.get("reportuuid").toString();
    	}
    	// 导出excel或预览时，值为0的单元格显示成0还是显示成空，读取系统参数
    	String isShowZero = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_IS_SHOW_ZERO);
    	ChbMonthReport monthreport = chbMonthReportMapper.selectByPrimaryKey(reportuuid);
//    	if(monthreport==null)
    	data.put("report", monthreport);
    	data.put("termname", monthreport.getTermname());
    	selectParam.put("termid", monthreport.getTermid());
    	selectParam.put("reportuuid", reportuuid);
		List<ChbMonthReportDetail> detailList  = chbMonthReportDetailMapper.selectByModel(selectParam);
		List<String> propertyCodeList=chbMonthReportDetailMapper.selectAllPropertyCode();
		Map<String,String>map =new HashMap<String,String>();
		for(String propertyCode:propertyCodeList){
			data.put(propertyCode, map);
		}
		for(ChbMonthReportDetail detail : detailList){
			dealProdData(detail,isShowZero);
			data.put(detail.getPropertycode(), divide10000(detail));
		}
    	return  data;
    }
 	
	public ResponseEntity<byte[]>  exportMonthReportExcel(Map<String,Object> data,String exceltemplate,String fileName) throws IOException {
	    //File depositfile = createFile(data,exceltemplate);
	    File depositfile = TisUtil.createFile(data, exceltemplate, fileName);
		return ExportUtil.export(fileName, depositfile, true);
	}
	
	public ResponseEntity<byte[]>  exportG06MonthReportExcel(Map<String,Object> data,String termname) throws IOException {
		List<File> sourceFiles = new ArrayList<File>();
		//产品端
		data.put("fileName", "《G0601产品分类情况（"+termname+"）》.xls");
		//File prodFile = createFile(data,"G06MonthreportProd");
		File prodFile = TisUtil.createFile(data, "G06MonthreportProd", "《G0601产品分类情况（"+termname+"）》.xls");
		sourceFiles.add(prodFile);
		//资产负债端
		data.put("fileName", "《G0602资产负债情况（"+termname+"）》.xls");
		//File assetFile = createFile(data,"G06MonthreportAsset");
		
		File assetFile = TisUtil.createFile(data, "G06MonthreportAsset", "《G0602资产负债情况（"+termname+"）》.xls");
		sourceFiles.add(assetFile);
		
		
		
		 // 如果生成excel文件个数大于1，生成压缩文件
        File outFile = null;
            String exportFilePath = SvrDataUtil.getSysParam(
                GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
//            String zipFileName = FileNameUtil.createFileName("zip");
            String zipFileName = "G06非保本理财业务月度统计表"+"_"+termname+".zip";
            outFile = new File(exportFilePath + File.separator + zipFileName);
            // 导出路径不存在则重新生成
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            try {
                ZipFileUtil.compressZip(outFile, true, sourceFiles.toArray(new File[] {}));
            }
            catch (Exception e) {
                // 压缩文件失败后，删除所有临时文件
                for (File file : sourceFiles) {
                    if (file.exists()) {
                        file.delete();
                    }
                }
                logger.error(e.getMessage(),e);
            }
            //删掉xls文件
            for(File single : sourceFiles){
            	single.delete();
            }
            return ExportUtil.export(zipFileName, outFile, true);

	}
	public String priview(String pageid,Map<String, Object> selectParam) {
		String exceltemplate =  "Monthreport";
		if("G06".equals(selectParam.get("G06"))){
			exceltemplate = "G06Monthreport";
		}
		@SuppressWarnings("unchecked")
        Map<String,Object> data = restTemplate.postForObject(
                SvrServiceApplication.CHB_SERVICE_NAME + "/ChbMonthReport/createData",selectParam,
                Map.class);
		
		data.put("termname",data.get("termname").toString());
//	    File depositfile = createFile(data,exceltemplate);
	    String termname = data.get("termname").toString();
	    String flieName = exceltemplate+"_"+termname+".xls";
		//G06需要将文件按命名规则生成并打包成zip
		if(null!=data.get("fileName")){
			flieName = data.get("fileName").toString();
		}
	    File depositfile = TisUtil.createFile(data, exceltemplate, flieName);
		return this.priView(depositfile, true);
	}
    /**
     * 导出文件
     * 
     * @param downloadFileName 导出的文件名
     * @param downloadFile 需要被下载的文件
     * @param deleteAfterExport 下载后是否删除当前原文件
     * @return 返回responseEntity
     * @throws IOException 异常
     * @see
     * @since 1.0
     */
    public String priView( File downloadFile, boolean deleteAfterExport){
    	  String rs =  XlsxToHtml.readExcelToHtml(downloadFile,true);  
        if (deleteAfterExport) {
            downloadFile.delete();
        }
        return rs;
    }
	
    public List<String> getCheckRepList(Map<String, Object> param) {
        List<String> keylist = new ArrayList<String>();
        dealSearchParam(param);
        List<ChbMonthReport> list = chbMonthReportMapper.searchPage(param);
        if(CollectionUtils.isNotEmpty(list)){
            for(ChbMonthReport dto : list){
                keylist.add(dto.getReportuuid());
            }
        }
        return keylist;
    }
	

	public File createFile(Map<String,Object> data,String exceltemplate){
		String termname = data.get("termname").toString();
	    String flieName = exceltemplate+"_"+termname+".xls";
		//G06需要将文件按命名规则生成并打包成zip
		if(null!=data.get("fileName")){
			flieName = data.get("fileName").toString();
		}
	    File depositfile = null;
	    FileOutputStream fos=null;
		InputStream fis = null;
		try{		
			
		// 关联模板
        XLSTransformer transformer = new XLSTransformer();
        
		String template = FileNameUtil.getTplFile(exceltemplate + ".xls");;
		//windows环境地址
//        if(File.separator.equals("\\")){
//        	template = getXmlPath(exceltemplate+".xls");
//        }
//        //linux环境地址
//        if(File.separator.equals("/")){
//        	template = getXmlPathLinux(exceltemplate+".xls");
//        }
		//传放地址
		String depositfilePath = SvrDataUtil.getSysParam(
                GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
		
		depositfile = new File(depositfilePath + File.separator + flieName);
		if (!depositfile.getParentFile().exists()) {
			depositfile.getParentFile().mkdirs();
		}
		
		// 缓存区，一次性读取1024字节
		byte[] buffer = new byte[1024];
		String temp = depositfilePath + "ptl.xls";
		// 原文件
		File srcFile = new File(template);
		// 目标文件
		File destFile = new File(temp);
		if (srcFile.exists()) {
			fis = new FileInputStream(srcFile);
		} else {
			fis = ChbMonthReport001Service.class.getResourceAsStream("/tpl/" + exceltemplate + ".xls");
		}
		 fos = new FileOutputStream(destFile);
		while (true) {
			// 实际读取的长度是 actuallyReaded,有可能小于1024
			int actuallyReaded = fis.read(buffer);
			// -1表示没有可读的内容了
			if (-1 == actuallyReaded)
				break;
			fos.write(buffer, 0, actuallyReaded);
			fos.flush();
		}
		transformer.transformXLS(temp, data, depositfilePath + File.separator +flieName);
	    }catch(Exception e){
	    	logger.info(exceltemplate+"生成失败！");
	    	logger.error(e.getMessage(),e);
	    	throw new RuntimeException(e);
	    }finally{
	    	if(fos !=null){
	    		try {
					fos.close();
				} catch (IOException e) {
					logger.error(e.getMessage(),e);
					e.printStackTrace();
				}
		    	if(fis !=null){
		    		try {
		    			fis.close();
					} catch (IOException e) {
						logger.error(e.getMessage(),e);
						e.printStackTrace();
					}
		    	}
	    	}
	    }
		 return depositfile;
	}
	public static String getXmlPath(String filename) {
		String path = ChbMonthReport001Service.class.getClassLoader()
				.getResource("").getPath();
		path = path.replace('/', '\\'); // 将/换成\
		path = path.replace("file:", ""); // 去掉file:
		path = path.substring(1); // 去掉第一个\,如 \D:\JavaWeb...
		path += "tpl\\" + filename;
		path = path.replace("report-batch", "report-parent\\report");
		return path;
	}

	public static String getXmlPathLinux(String filename) {
		try {
			String path = Thread.currentThread().getContextClassLoader()
					.getResource("").toURI().getPath();
			path += "tpl/" + filename;
			path = path.replace("file:", ""); // 去掉file:
			path = path.replace("report-batch", "report-parent/report");
			return path;
		} catch (URISyntaxException e) {
			return null;
		}
	}

	public ChbMonthReportDetail divide10000(ChbMonthReportDetail detail) {
		BigDecimal tenthousand = new BigDecimal(10000);
		if(detail.getBalance()!=null)//期末余额
			detail.setBalance(detail.getBalance().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
		if(detail.getBottombalance()!=null)//期末余额（穿透后）
			detail.setBottombalance(detail.getBottombalance().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
		if(detail.getBankprofit()!=null)//本期银行收益
			detail.setBankprofit(detail.getBankprofit().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
		if(detail.getCustomerprofit()!=null)//本期客户收益
			detail.setCustomerprofit(detail.getCustomerprofit().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
		if(detail.getProdamount()!=null)//本期募集金额
			detail.setProdamount(detail.getProdamount().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
		if(detail.getProdnetamount()!=null)//本期总募集金额
			detail.setProdnetamount(detail.getProdnetamount().divide(tenthousand, 9, BigDecimal.ROUND_HALF_UP));
		return detail;
	}
	
	public List<ExcelValidDto> checkData(List<String> checkKeys){
        Map<String,CheckReport001Dto> checkmap = new HashMap<String,CheckReport001Dto>();
        Map<String,ChbMonthReport> repmap = new HashMap<String,ChbMonthReport>();
        List<ChbMonthReport> issuelist = chbMonthReportMapper.searchByKeys(checkKeys);
        repmap = issuelist.stream().collect(Collectors.toMap(ChbMonthReport::getReportuuid,(p)->p,(key1,key2)->key1));
        List<ExcelValidDto> result = new ArrayList<ExcelValidDto>();
        List<CheckReport001Dto> resultlist  = new ArrayList<CheckReport001Dto>();
        // 检查登记表中小于今天所有未报送的数据
        if(CollectionUtils.isNotEmpty(checkKeys)){
            // 调用检查方法
            resultlist.addAll(check());
            checkmap = resultlist.stream().collect(Collectors.toMap(CheckReport001Dto::getRefseqno,(p)->p,(key1,key2)->key1));
            for(String key : checkKeys){
                if(checkmap.get(key)!=null){
                    ExcelValidDto exc=new ExcelValidDto();
                    exc.setCheckResult(checkmap.get(key).getCheckResult());
                    exc.setErrorInfo(checkmap.get(key).getErrorInfo());
                    exc.setWarnInfo(checkmap.get(key).getWarnInfo());
                    exc.setValidKey(checkmap.get(key).getValidKey());
                    result.add(exc);
                }else{
                    ExcelValidDto exc=new ExcelValidDto();
                    exc.setValidKey("月报期数:"+repmap.get(key).getTermid());
                    exc.setCheckResult("01");
                    result.add(exc);
                }
            }
        }
        return result;
    }
	/**
     * @Description 检查登记表中小于今天所有未报送的数据
     * @return
     * @return List<CheckReport001Dto>
     * @version 1.7 2019年6月24日
     */
    private List<CheckReport001Dto> check(){
        // 数据日期为系统时间
        Date checkdate = DateUtil.parse(DateUtil.getSysDate());
        // 删除今天所有已检查的数据
        checkReport001Mapper.delReportDatasByCodeAndDate(DictCodeListExt2.报表名称_银行月度统计表.getValue(),checkdate);
        //增加例外情况过滤
        List<ChbCheckRuleout> listRuleout=chbCheckRuleoutMapper.selectDataByCheckTypeAndReprotCode(CheckConfConstants.CHB_CHECK_TYPE_B, CheckConfConstants.CHB_PRD_ISSUE_SUB);
        Map<String, List<ChbCheckRuleout>> mapRuleout = listRuleout.stream().collect(
                 JoyinCollectors.groupingBy(x -> x.getProdId()));
        //检查数据
        List<CheckReport001Dto> resultList = beforeSubCheck.checkProdMonthReport(checkdate,mapRuleout);
        //报错检查异常数据
        int batchCount = Integer.parseInt( null == SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT)?"100" : SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT));// 每批commit的个数
        int batchLastIndex = batchCount;// 每批最后一个的下标
        for (int index = 0; index < resultList.size();) {
            if (batchLastIndex > resultList.size() - 1) {
                batchLastIndex = resultList.size();
                checkReport001Mapper.batchInsert(resultList.subList(index, batchLastIndex));
                break;// 数据删除完毕,退出循环
            } else {
                checkReport001Mapper.batchInsert(resultList.subList(index, batchLastIndex));
                index = batchLastIndex;// 设置下一批下标
                batchLastIndex = index + batchCount;
            }
        }
        return resultList;
    }
    /**
     * @Description 导出预览为零的数据显示空还是显示零
     * @param detail
     * @version 1.7 2019年7月4日
     * @param isShowZero 
     */
    private void dealProdData(ChbMonthReportDetail detail, String isShowZero) {
    	// 显示成零
		if("01".equals(isShowZero)){
			if(detail.getBalance()==null){
				detail.setBalance(BigDecimal.ZERO);
			}
			if(detail.getBankprofit()==null){
				detail.setBankprofit(BigDecimal.ZERO);
			}
			if(detail.getBottombalance()==null){
				detail.setBottombalance(BigDecimal.ZERO);
			}
			if(detail.getCustomerprofit()==null){
				detail.setCustomerprofit(BigDecimal.ZERO);
			}
			if(detail.getProdamount()==null){
				detail.setProdamount(BigDecimal.ZERO);
			}
			if(detail.getProdnetamount()==null){
				detail.setProdnetamount(BigDecimal.ZERO);
			}
			if(detail.getProdyield()==null){
				detail.setProdyield(BigDecimal.ZERO);
			}
			if(detail.getProdcounts()==null){
				detail.setProdcounts(0);
			}
			if(detail.getDurationprodcounts()==null){
				detail.setDurationprodcounts(0);
			}
		} else if("02".equals(isShowZero)){
			
			if(detail.getBalance()!=null&&(BigDecimal.ZERO).compareTo(detail.getBalance())==0){
				detail.setBalance(null);
			}
			if(detail.getBankprofit()!=null&&(BigDecimal.ZERO).compareTo(detail.getBankprofit())==0){
				detail.setBankprofit(null);
			}
			if(detail.getBottombalance()!=null&&(BigDecimal.ZERO).compareTo(detail.getBottombalance())==0){
				detail.setBottombalance(null);
			}
			if(detail.getCustomerprofit()!=null&&(BigDecimal.ZERO).compareTo(detail.getCustomerprofit())==0){
				detail.setCustomerprofit(null);
			}
			if(detail.getProdamount()!=null&&(BigDecimal.ZERO).compareTo(detail.getProdamount())==0){
				detail.setProdamount(null);
			}
			if(detail.getProdnetamount()!=null&&(BigDecimal.ZERO).compareTo(detail.getProdnetamount())==0){
				detail.setProdnetamount(null);
			}
			if(detail.getProdyield()!=null&&(BigDecimal.ZERO).compareTo(detail.getProdyield())==0){
				detail.setProdyield(null);
			}
			if(detail.getProdcounts()!=null&&detail.getProdcounts()==0){
				detail.setProdcounts(null);
			}
			if(detail.getDurationprodcounts()!=null&&detail.getDurationprodcounts()==0){
				detail.setDurationprodcounts(null);
			}
		}
	}
 	
}
