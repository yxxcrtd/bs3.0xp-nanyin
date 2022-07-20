package com.joyintech.tams.disclosure.information.reveal.detailshow.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.validator.internal.metadata.aggregated.rule.ParallelMethodsMustNotDefineParameterConstraints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONArray;
import com.ibm.icu.text.SimpleDateFormat;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.base.intf.DataDict;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.RepInfoMapper;
import com.joyintech.tams.common.base.dao.RepInstallLogDetailMapper;
import com.joyintech.tams.common.base.dao.RepRevealMapper;
import com.joyintech.tams.common.base.dao.RepTreeMapper;
import com.joyintech.tams.common.base.entity.RepInfo;
import com.joyintech.tams.common.base.entity.RepInstallLogDetail;
import com.joyintech.tams.common.base.entity.RepJobRange;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.common.util.JacksonUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosuregenandsend.dao.InfoDisclosureGenAndSendMapper;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosuregenandsend.service.InfoDisclosureGenService;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.entity.ProdInfoListEntity;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.entity.RepRuleEntity;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.util.JasparConnectUtilService;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.util.RevealDeadLineService;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.ReportManager;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.dao.ReportInfoMapper;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.pojo.ReportInfo;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.reveal.detailshow.dao.RepDetailsShow001Mapper;
import com.joyintech.tams.disclosure.information.reveal.detailshow.dto.ProdInfoDto;
import com.joyintech.tams.disclosure.information.reveal.detailshow.dto.RepDetailsShow001Dto;
import com.joyintech.tams.disclosure.interf.interf.common.constant.DateUtil;
import com.joyintech.tams.disclosure.rptdata.service.ReportDataService;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.utils.poi.PoiUtils;

@SuppressWarnings("unused")
@Service
@Transactional(rollbackFor = Exception.class)
public class RepDetailsShow001Service extends CommonExportService<RepDetailsShow001Dto>{
	
	@Autowired
	private RepDetailsShow001Mapper repDetailsShow001Mapper;
	
	@Autowired
	private RepRevealMapper repRevealMapper;
	
	@Autowired
	private RepInfoMapper repInfoMapper;
	
	@Autowired
	private ReportInfoMapper reportInfoMapper;
	
	@Autowired
    private RepTreeMapper repTreeMapper;
	
	@Autowired
	private FileAttachmentProcess fileAttachmentProcess;
	/**
     * 日志详情表mapper
     */
    @Autowired
    private RepInstallLogDetailMapper repInstallLogDetailMapper;
	
    @Autowired
    RevealDeadLineService revealDeadLineService;
    @Autowired
    private JasparConnectUtilService jasparConnectUtilService;
    
    /**
     * 生成文件及相关service
     */
    @Autowired
    private InfoDisclosureGenService infoDisclosureGenService;
    @Autowired
    private InfoDisclosureGenAndSendMapper infoDisclosureGenAndSendMapper;

    @Autowired
    private ReportManager reportManager;

    @Autowired
    private ReportDataService rptDataService;

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(RepDetailsShow001Service.class);
	/**
     * 
     * 查询对应模板所有的查询条件
     * @param repId
     * @return
     * @see
     * @since 1.0
     */
    public RepDetailsShow001Dto getRepScreenList(String repId){
    	
//    	return restTemplate.getForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/RepDetailsShow001/getRepsearch?repId="+repId,
//    			RepDetailsShow001Dto.class);
        
        RepDetailsShow001Dto info = repDetailsShow001Mapper.getInfoByRepId(repId);  //查询报表的种类及模板配置等信息
        if(null!=info){
            info.setRepScreenList(repDetailsShow001Mapper.getRepScreenList(repId)); //查询每个报表前台配置的查询参数信息

        }
        return info;
    }
	
	/**
	 * 
	 * 获取系统中的变量值
	 * <功能详细描述>
	 * @param DictCodes
	 * @return
	 * @see
	 * @since 1.0
	 */
	public Map<String,Object> getCatchDetail(String DictCodes){
	    Map<String,Object> resultMap = new HashMap<>();
	    if(StringUtil.isNotEmpty(DictCodes)){
	        String[] dict = DictCodes.split(",");
	        for (String code : dict) {
	            DataDict dictCode =  CacheUtil.getDictCode(code);
	            resultMap.put(dictCode.getDictCode(), dictCode.getItems());
            }
	    }
	    return resultMap;
	}
    
	/**
     * 
     * 查询对应模板所有的查询条件
     * @return
     * @throws MalformedURLException 
     * @throws IOException 
     * @see
     * @since 1.0
     */
//    public boolean downFileListAndSubmit(Map<String,Object> params) throws MalformedURLException{
//
//
//    	return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/RepDetailsShow001/downFileListAndSubmit",params,
//    			boolean.class);
//        boolean flag = false;
//        
//        RepTree reptree = repTreeMapper.selectByPrimaryKey(params.get("repId").toString());
//
//        String type = params.get("fileType").toString();
//        
//        if(!StringUtil.isNotEmpty(reptree)){
//            return flag;
//        }
//        List<String> fileList = downLoadFile(params.get("repId").toString(),params.get("fileType").toString()
//            ,params.get("portnum").toString(),params.get("startDate").toString(),params.get("endDate").toString());
//        
//        String fileName = reptree.getTreeName()+GlobalConstant.UNDER_LINE + DateUtil.getNewSysDate() +GlobalConstant.POINT+ type;
//        
//        try {
//            String localFilePath = FileNameUtil.createUploadFilePath("")+File.separator+fileName;
//
//            
//            File localFile = new File(localFilePath);
//
//            if(!localFile.exists()){
//                    localFile.createNewFile();
//            }
//
//            if(type.equals("docx")){
//                OfficeMergeUtil.mergeWord(localFilePath, fileList);
//
//            }else if(type.equals("xlsx")){
//                OfficeMergeUtil.mergeExcel(localFilePath, fileList);
//
//            }
//            
//            
//              String fileKey = IDGenerator.generate();
//              
//              UploadFile fileInfo = new UploadFile(fileName, fileKey,
//                  localFile);
//              if(null != fileAttachmentProcess) {
//                  fileAttachmentProcess.upload(fileInfo);
//              }
//              
//              flag = insertRepReveal(params, fileKey,fileName,reptree.getTreeName()+"集合",null);
//        } catch (Exception e) {
//        	logger.error("error:",e);
//        }
//        return flag;
//    }
	
	/**
	 * 
	 * 下载文件并返回路径
	 * <功能详细描述>
	 * @param pid
	 * @param Type
	 * @param protNum
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws MalformedURLException
	 * @see
	 * @since 1.0
	 */
//    public List<String> downLoadFile(String pid,String Type,String protNum,String startDate,String endDate) throws MalformedURLException{
//        List<String> fileList = new ArrayList<String>();
//
//
//        List<RepDetailsShow001Dto> repList = repDetailsShow001Mapper.getRepInfoListByPid(pid);
//
//        for (RepDetailsShow001Dto rep : repList) {
//
//            Map<String,String> map = new HashMap<>();
//
//          //系统中配置的文件服务器ip
//            String serverUrl = CacheUtil.getSysParam(ReportGlobalConstant.REPORT_SERVER_URL);
//            //用户名
//            String serverUser = CacheUtil.getSysParam(ReportGlobalConstant.REPORT_USERNAME);
//            //密码
//            String serverPwd = CacheUtil.getSysParam(ReportGlobalConstant.REPORT_PASSWORD);
//
//
//            InfoDisclosureGenAndSendEntity entity = new InfoDisclosureGenAndSendEntity();
//            entity.setProdid(protNum);
//            entity.setStartDate(StringUtil.isNotEmpty(DateUtil.parse(startDate))?DateUtil.parse(startDate):null);
//            entity.setEndDate(StringUtil.isNotEmpty(DateUtil.parse(endDate))?DateUtil.parse(endDate):null);
//            entity.setTemplatePath(rep.getTemplatePath());
//            entity.setFileType(Type);
//            entity.setTemplateId(rep.getTemplateId());
//
//            List<RepScreen> screenList = infoDisclosureGenAndSendMapper.getRepScreenList(rep.getRepId());
//            String strUrl = infoDisclosureGenService.getAllUrl(serverUrl, serverUser, serverPwd, entity, false, screenList,null);
//
////            String strUrl = "http://192.168.70.178:8085/jasperserver/rest_v2/reports"+rep.getTemplatePath()+
////                        //File.separator
////                            "/"+rep.getTemplateId()+GlobalConstant.POINT+Type
////                                +"?j_username=jasperadmin&j_password=jasperadmin&portnum="+protNum+"&startDate="+startDate+"&endDate="+endDate+"/report.docx";
//
////            URL url = new URL(strUrl);
//
////            String fileName = rep.getReportName() + GlobalConstant.POINT + Type;
//
//            try {
//            	UploadFile uploadFile = jasparConnectUtilService.connectJasparAndDownload(strUrl, rep.getReportName(), Type, rep.getTemplateId(), protNum,infoDisclosureGenService.getParams(entity,screenList));
////                URLConnection conn = url.openConnection();
////                // 防止屏蔽程序抓取而返回403错误
////                conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
////
////                InputStream inputStream = conn.getInputStream();
////
////                // 获取自己数组
////                byte[] getData = readInputStream(inputStream);
////
////
////
////                String path =FileNameUtil.createUploadFilePath("");
////
////                fileName = rep.getReportName()+GlobalConstant.UNDER_LINE + DateUtil.getNewSysDate() +GlobalConstant.POINT+ Type;
////
////                  File localFile = new File(path+ File.separator+fileName);
////                  File dir = localFile.getParentFile();
////                  if (!dir.exists()) {
////                      dir.mkdirs();
////                  }
////
////                  FileOutputStream fos = new FileOutputStream(localFile);
////                  fos.write(getData);
////                  if (fos != null) {
////                      fos.close();
////                  }
////                  if (inputStream != null) {
////                      inputStream.close();
////                  }
//
//                  fileList.add(uploadFile.getTargetFile().getPath());
//            } catch (Exception e) {
//            	logger.error("error:",e);
//            }
//        }
//
//        return fileList;
//    }
    
    
    /**
     * 导出合并文件
     *
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public ResponseEntity<byte[]> exportReport(Map<String,Object> params) throws IOException {
        
        if ( params==null || params.size()==0) {
            return null;
        }

        String reportId = params.get("repId").toString();   //报表编号
        ReportInfo reportInfo = reportInfoMapper.getReportInfoByID(reportId);

        String fileType = params.get("fileType").toString();
        Date genDate = DateUtil.parse(params.get("endDate").toString());
        String prodId = params.get("portnum").toString();
        String reportFile = ReportFileUtil.buildReportFileName(ReportFileUtil.REPORT_TYPE_报表文件,reportInfo, genDate, prodId,ReportFileUtil.FILE_TYPE_DOCX);
        String localFileName = reportInfo.getReportName()+"_"+prodId+"_" + params.get("endDate").toString()+ReportFileUtil.FILE_TYPE_DOCX;

        if ( "pdf".equals(fileType)) {  //如果导出pdf，则需要先讲word转换成pdf
            FileInputStream fis = new FileInputStream(reportFile);
            String fileNamePdf = ReportFileUtil.buildReportFileName(ReportFileUtil.REPORT_TYPE_报表文件,reportInfo, genDate, prodId,ReportFileUtil.FILE_TYPE_PDF);
            FileOutputStream fos = new FileOutputStream(fileNamePdf);
            PoiUtils.docxToPdf(fis,fos);
            reportFile = fileNamePdf;
            localFileName = reportInfo.getReportName()+"_"+prodId+"_" + params.get("endDate").toString()+ReportFileUtil.FILE_TYPE_PDF;
        }

        File remoteFile = new SafelyFile(reportFile);

        if(!remoteFile.exists()){
        	remoteFile.createNewFile();
        }

        return ExportUtil.export(localFileName, remoteFile, false);
        
    }
    
	
	/**
     * 
     * 查询对应模板所有的查询条件
     * @return
	 * @throws MalformedURLException 
	 * @throws IOException 
	 * @see
     * @since 1.0
     */
//    public boolean downFileAndSubmit111(Map<String,Object> params) throws MalformedURLException{
//
//    	return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/RepDetailsShow001/downFileAndSubmit",params,
//    			boolean.class);
    	
    	
//        boolean flag = false;
//        
//        String repId = params.get("repId").toString();
//        String fileType = params.get("fileType").toString();
//        
////        RepInfo repInfo = repInfoMapper.selectByPrimaryKey(repId);
//        
//        RepInfo repInfo = restTemplate.getForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/RepDetailsShow001/selectByPrimaryKey?repId="+repId,
//        		RepInfo.class);
//        
//        
//        
//        
//        
//        
//        //获取产品中文名全称
//        //中信不要
//        //String prodName = repDetailsShow001Mapper.getProdName(params.get("portnum").toString());
//        if(!StringUtil.isNotEmpty(repInfo)){
//            logger.info("查找报表失败!");
//            return flag;
//        }
//        
////        String fileName = repInfo.getReportName() + GlobalConstant.POINT + fileType;
//        
//        try {
////        	String fileName = params.get("portnum").toString()+GlobalConstant.UNDER_LINE+repInfo.getReportName();
//        	String portnum = params.get("portnum") == null?"":params.get("portnum").toString();
//        	String fileName = portnum+GlobalConstant.UNDER_LINE+repInfo.getReportName();
//        	logger.info("**************************** ");
//        	logger.info("fileName = " + fileName);
//        	logger.info("**************************** ");
//        	UploadFile uploadFile = jasparConnectUtilService.connectJasparAndDownload(params.get("url").toString(), fileName, fileType, repInfo.getTemplateId(), portnum,params);
//            logger.info("生成文件!");
//
////            URLConnection conn = url.openConnection();
////            // 防止屏蔽程序抓取而返回403错误
////            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
////
////            InputStream inputStream = conn.getInputStream();
////            
////            // 获取自己数组
////            byte[] getData = readInputStream(inputStream);
////            
////
////            
////            String path = FileNameUtil.createUploadFilePath("");
////             
////            fileName = repInfo.getReportName()+GlobalConstant.UNDER_LINE + DateUtil.getNewSysDate() +GlobalConstant.POINT+ fileType;
////          
////              File localFile = new File(path+ File.separator+fileName);
////              File dir = localFile.getParentFile();
////              if (!dir.exists()) {
////                  dir.mkdirs();
////              }
////                  
////              FileOutputStream fos = new FileOutputStream(localFile);
////              fos.write(getData);
////              if (fos != null) {
////                  fos.close();
////              }
////              if (inputStream != null) {
////                  inputStream.close();
////              }
////              String fileKey = IDGenerator.generate();
////              
////              UploadFile fileInfo = new UploadFile(fileName, fileKey,
////                  localFile);
////              if(null != fileAttachmentProcess) {
////                  fileAttachmentProcess.upload(fileInfo);
////              }
//              
//              flag = insertRepReveal(params, uploadFile.getFileNo(),uploadFile.getSrcName(),repInfo.getReportName(),repInfo.getReportCategory());
//              logger.info("生成信息主表!");
//        } catch (Exception e) {
//        	logger.error("error:",e);
//        }
//        return flag;
//    }
    
    /**
     * 获取错误信息
     * @param params
     * @return
     * @throws MalformedURLException
     */
    public List<ValidMessage> getErrorMessage(Map<String,Object> params) throws MalformedURLException{
    	
    	
    	
    	return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/RepDetailsShow001/getErrorMessage",params,
    			ValidMessage.class);
//    	List<ValidMessage> checkList = new ArrayList<ValidMessage>();
//        
//        String repId = params.get("repId").toString();
//        String rep_id = params.get("rep_id").toString();
//        String fileType = params.get("fileType").toString();
//        
//        RepInfo repInfo = repInfoMapper.selectByPrimaryKey(repId);
//        List<String> list = new ArrayList<>();
//        String[] arr;
//        try {
//        	String portnum = params.get("portnum") == null?"":params.get("portnum").toString();
//        	String fileName = portnum +GlobalConstant.UNDER_LINE+repInfo.getReportName();
//        	UploadFile uploadFile = jasparConnectUtilService.connectJasparAndDownload(params.get("url").toString(), fileName, fileType, repInfo.getTemplateId(), portnum,params);
//        	List<String> errorMsg = repDetailsShow001Mapper.getErrorMsg(rep_id);
//        	if(errorMsg.size() != 0){
//        		for (int i=0;i<errorMsg.size();i++) {
//                	arr = errorMsg.get(i).split(",");
//                	for(int j=0;j<arr.length;j++){
//                		list.add(arr[j]);
//                	}
//                }
//        	}
//        } catch (Exception e) {
//        	logger.error("error:",e);
//        }
//        for(int i=0;i<list.size();i++){
//    		checkList.add(new ValidMessage(list.get(i)));
//    	}
//		return checkList;
    	
    }
    
    /**
     * 删除错误信息
     */
    
    public int deleteErrorMsg (String rep_id){
//    	int result = repDetailsShow001Mapper.deleteErrorMsg(rep_id);
//    	 return result;
    	return restTemplate.getForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/RepDetailsShow001/deleteErrorMsg?repId="+rep_id,
    			Integer.class);
    }
    
    
    /**
     * 
     * 生成功能入库操作
     * <功能详细描述>
     * @param params
     * @param fileKey
     * @param fileName
     * @return
     * @see
     * @since 1.0
     */
    public boolean insertRepReveal(Map<String,Object> params,String fileKey,String fileName,String ReportName, String reportCategory){
        RepReveal repReveal = new RepReveal();
        //将待组装数据成日志信息
    	RepInstallLogDetail repInstallLogDetail = new RepInstallLogDetail();

        repReveal.setRepId(params.get("repId").toString());
        repReveal.setSysFilepath(fileKey);
        repReveal.setSysFilename(fileName);
        repReveal.setReportName(ReportName);
        if(StringUtil.isNotEmpty(params.get("portnum"))){
            repReveal.setProdid(params.get("portnum").toString());
            //查询产品名称的全称
            String prodName = repDetailsShow001Mapper.getProdName(params.get("portnum").toString());
//            repReveal.setProdName(params.get("prodName").toString());
            repReveal.setProdName(prodName);
        }
        if(StringUtil.isNotEmpty(params.get("startDate"))){
            repReveal.setStartDate(DateUtil.parse(params.get("startDate").toString()));
        }
        if(StringUtil.isNotEmpty(params.get("endDate"))){
            repReveal.setEndDate((Date)params.get("endDate"));
        }
        // 获取产品信息
        String portnum = params.get("portnum") == null?"":params.get("portnum").toString();
        ProdInfoListEntity prodInfo = repDetailsShow001Mapper.getProdList(portnum);
        // 获取规则信息
        List<RepRuleEntity> repRule = repDetailsShow001Mapper.getRuleInfo(params.get("repId").toString());
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
        
        RepInfo repInfo = repInfoMapper.selectByPrimaryKey(repReveal.getRepId());
        
        
        if(StringUtil.isNotEmpty(repInfo)&&StringUtil.isNotEmpty(repInfo.getTemplateId())){
            String temllateId = repInfo.getTemplateId();
            if(temllateId.equals("CLGG_zx")){
                repReveal.setEndDate(prodInfo.getVdate());
            }else if(temllateId.equals("DQGGHB_zx")||temllateId.equals("DQGGJZ_zx")||temllateId.equals("DQGGYQ_zx")){
                repReveal.setEndDate(prodInfo.getMdate());
            }
        }

        // 获取披露渠道
        if(null != prodInfo){
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
        //主键 id
    	repInstallLogDetail.setId(IDGenerator.generate());
    	//执行状态
    	repInstallLogDetail.setExecStatus(DisclosureDictCodeList.日志状态_生成成功.getValue());
    	//报表编号
    	repInstallLogDetail.setRepId(repReveal.getRepId());
    	//报表名称  
    	repInstallLogDetail.setRepName(repReveal.getReportName());
    	//产品编号
    	repInstallLogDetail.setProdid(repReveal.getProdid());
    	//文件类型
    	repInstallLogDetail.setFileType(repReveal.getSysFilename().substring(repReveal.getSysFilename().lastIndexOf(".") + 1));
    	//产品名称 
    	repInstallLogDetail.setProdName(repReveal.getProdName());
    	//开始日
    	repInstallLogDetail.setStartDate(repReveal.getStartDate());
    	//结束日日
    	repInstallLogDetail.setEndDate(repReveal.getEndDate());
    	//披露渠道 
    	repInstallLogDetail.setRevealChannel(repReveal.getRevealChannel());
    	//披露截止日
    	repInstallLogDetail.setRevealEndDate(repReveal.getRevealEndDate());
    	//是否自动确认
    	repInstallLogDetail.setIsConfirm(repReveal.getIsConfirm());
    	//是否自动披露
    	repInstallLogDetail.setIsReveal(repReveal.getIsConfirm());
    	//设置数据状态
    	repInstallLogDetail.setStatus(DisclosureDictCodeList.数据状态_有效.getValue());
    	//更新日志表
		repInstallLogDetailMapper.insert(repInstallLogDetail);
        return true;
    }
    
    
    /**
     * 从输入流中获取字节数组
     * 
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    // add by chendong in 2018-11-27 start
    /**
     * 获取产品信息
     * 
     * @return 产品信息
     */
    public List<SelectOptionDto> getProdInfo(){
    	
//    	return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportWorkManage002/getReportIdList",null,
//    			SelectOptionDto.class);
		List<ProdInfoDto>prodInfoList = repDetailsShow001Mapper.getProdInfo();
		List<SelectOptionDto> listSelectDto = new ArrayList<SelectOptionDto>();
        
        for(ProdInfoDto prodInfo : prodInfoList){
        	SelectOptionDto dto = new SelectOptionDto();
        	dto.setId(prodInfo.getProdId());
        	dto.setText(prodInfo.getProdName());
        	listSelectDto.add(dto);
        }
    	return listSelectDto;
    }
    /**
     * 查询相同报表数据
     * @return 产品信息
     */
    public int querySameCount(Map<String, Object>params){
    	
//    	return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/RepDetailsShow001/querySameCount",params,
//    			Integer.class);
    	String startDate = null;
    	String endDate = null;
    	Map<String, Object>paramList = new HashMap<String, Object>();
    	if(params.get("startDate")==null){
    		paramList.put("startDate", "0");
    	}
    	else {
    		startDate = params.get("startDate").toString();
    		paramList.put("startDate", startDate);
    	}
    	if(params.get("endDate")==null){
    		paramList.put("endDate", "0");
    	}
    	else {
    		endDate = params.get("endDate").toString();
    		paramList.put("endDate", endDate);
    	}
    	if(params.get("repId")!=null){
    		paramList.put("repId", params.get("repId").toString());
    	}
    	if(null!=params.get("portnum")){ 
    		List<String> prdList = new ArrayList<>();
    		if(params.get("portnum").toString().indexOf("[")==0){
    			//prdList = JSONArray.parseArray(params.get("portnum").toString(),String.class);
    			prdList =JacksonUtil.toJavaObjectList(params.get("portnum").toString(),String.class);
    		}else{
    			prdList.add(params.get("portnum").toString());
    		}
    		paramList.put("portnum", prdList);
    	}
    	int count = repDetailsShow001Mapper.querySameCount(paramList);
    	return count;
    }
    /**
     * 更新相同报表数据数据状态为D(删除)
     */
    public int updateDataStatus(Map<String, Object>params){
    	String startDate = null;
    	String endDate = null;
    	Map<String, Object>paramList = new HashMap<String, Object>();
    	if(params.get("startDate")==null){
    		paramList.put("startDate", "0");
    	}
    	else {
    		startDate = params.get("startDate").toString();
    		paramList.put("startDate", startDate);
    	}
    	if(params.get("endDate")==null){
    		paramList.put("endDate", "0");
    	}
    	else {
    		endDate = params.get("endDate").toString();
    		paramList.put("endDate", endDate);
    	}
    	paramList.put("repId", params.get("repId").toString());
    	paramList.put("portnum", params.get("portnum").toString());
    	return repDetailsShow001Mapper.updateDataStatus(paramList); 
    }
    
    /**
	 * 获取基准日
	 * @param prodInfoListEntity 产品相关
	 * @param repRule 规则表
	 * @return 返回基准日 
	 */
	private Date getBasisDate(ProdInfoListEntity prodInfoListEntity, RepRuleEntity repRule){
		if(StringUtil.equals(DictCodeListExt2.基准日_募集起始日.getValue(), repRule.getBasisDateType())
				||StringUtil.equals(DictCodeListExt2.基准日_实际到期日.getValue(), repRule.getBasisDateType())){
			try {
				return (Date)CommonUtil.getValueByClassNameObject(prodInfoListEntity, CommonUtil.dealString(repRule.getBasisDateType(),true), ProdInfoListEntity.class);
			} catch (Exception e) {
				logger.error("error:",e);
			}
		}
		return null;
	}
    // add by chendong in 2018-11-27 end
	/**
     * 查询相同报表数据
     * @return 产品信息
	 * @throws Exception 
     */
//    public String showDetailHtml(String uri,RepReveal repReveal) throws IOException {
//	    //后台批处理报表地址单独啦出来
//		String serverUrl = CacheUtil.getSysParam(ReportGlobalConstant.REPORT_SERVER_URL);
//		//用户名
//		String serverUser = CacheUtil.getSysParam(ReportGlobalConstant.REPORT_USERNAME);
//		//密码
//		String serverPwd = CacheUtil.getSysParam(ReportGlobalConstant.REPORT_PASSWORD);
//		//路径
//		String serverPath = CacheUtil.getSysParam(ReportGlobalConstant.REPORT_SERVER_PATH);
//
//
//		String url = jasparConnectUtilService.getAllHtmlUrl(serverUrl, serverUser, serverPwd, serverPath, uri, repReveal);
//
//		String result = jasparConnectUtilService.connectJasparAndGenHtml(url);
//    	return result;
//    }
    public void getRandgeRule(Map<String, Object> params){
    	
//    	restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportWorkManage002/getRandgeRule",params,
//    			Integer.class);
//    	List<RepJobRange> ruleList =restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/RepDetailsShow001/getRandgeRule",params,
//    			RepJobRange.class);
        String repId =(String)params.get("repId");
        //根据报表编码查询规则数据
        List<RepJobRange> ruleList = repDetailsShow001Mapper.getRepJobRangeInfo(repId);
//        //根据规则数据设置查询条件
        StringBuffer sb = new StringBuffer();
        if(!StringUtil.isEmptyList(ruleList)){
            for(RepJobRange dto:ruleList){
                if(StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_募集方式.getValue())
                        ||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_发行方式.getValue())
                        ||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_发行状态.getValue())
                        ||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_开放模式.getValue())
                        ||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_是否独立开户.getValue())
                        ||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_产品类型.getValue())
                        ||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_理财类型.getValue())
                        ||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_产品期限类型.getValue())){
                    sb.append(" and ").append(dto.getRuleType1()).append(dto.getRuleType2()).append("'")
                            .append(dto.getRuleType4()).append("'");
                }else if(StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_起息日.getValue())
                        ||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_到期日.getValue())
                        ||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_清盘日.getValue())){
                    String sysDate=DateUtil.getCurrentDate();
                    if(dto.getRuleType4()==null||StringUtil.isEmpty(dto.getRuleType4())){
                        sb.append(" and ").append("to_char(").append(dto.getRuleType1()).append(",'yyyy-mm-dd')").append(dto.getRuleType2()).append("'")
                        .append(sysDate).append("'");
                    }
                    else{
                        sb.append(" and ").append("to_char(").append(dto.getRuleType1()).append(",'yyyy-mm-dd')").append(dto.getRuleType2()).append("'")
                        .append(dto.getRuleType4()).append("'");
                    }
                }
            }
        }
        params.put("condition", sb.toString());
    }

    /**
     * 生成服务器上的word报表文件
     * @param repId：REP_INFO表的rep_id字段，报表类型编号，如OTHER_FHJSQD：分红计算清单
     * @param dataMap，报表变量数据集合
     * @param genDate,生成日期，如果为空则为平台日期
     * @return  生成的报表文件路径
     */
    private String genReportFile(String reportType,ReportInfo reportInfo,Map<String,Object> dataMap,Date genDate) {
        //TODO:如下通过系统参数配置获取
    	String imagePath = "E:\\poitest\\template";

        String templateFile = "E:/poitest/template";
        String reportFile = "E:/poitest/report";
        
        String prodId = (dataMap.get("prodId")==null?"":dataMap.get("prodId").toString());
        
        if (genDate==null )  genDate = new Date();
        
        templateFile = ReportFileUtil.buildReportFileName(ReportFileUtil.REPORT_TYPE_模板文件,reportInfo, null, null,ReportFileUtil.FILE_TYPE_DOCX);
        reportFile = ReportFileUtil.buildReportFileName(reportType,reportInfo, genDate, prodId,ReportFileUtil.FILE_TYPE_DOCX);

        FileOutputStream fos = null;
        InputStream is = null;
        try {
            //替换参数
            fos = new FileOutputStream(reportFile);
            PoiUtils.mergeDocx(templateFile,fos, dataMap);
        } catch (Exception e ) {
            e.printStackTrace();
        } finally {
            try {
                if ( fos!=null ) {
                    fos.flush();
                    fos.close();
                }
                if ( is!=null ) {
                    is.close();
                }
            } catch (IOException e ) {
                logger.error("生成报表文件错误.",e);
            	e.printStackTrace();
            }
        }
        return reportFile;
    }
    
    /**
     * 显示报表：1.用于单击报表显示报表模板，2.指定报表查询条件后预览报表；
     * @param request
     * @param response
     */
    public void previewReport(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> params = RequestUtil.getPageParam();
        
        //TODO:如下两项的配置获取
        String imagePath = "E:\\poitest\\template";
        String templateFile = "E:/poitest/template";
        
        String reportId = params.get("repId").toString();   //报表编号
        ReportInfo reportInfo = reportInfoMapper.getReportInfoByID(reportId);
        
        if ( params.get("elementIdForLog")==null || !params.get("elementIdForLog").equals("RepDetailsShow001_showDetail") ) {  //树单击：显示模板
        	if ( reportInfo!=null ) {
        		templateFile = ReportFileUtil.buildReportFileName(ReportFileUtil.REPORT_TYPE_模板文件,reportInfo,null,null,ReportFileUtil.FILE_TYPE_DOCX);
        	}
            //转换成显示内容流
            try {
                FileInputStream fis = new FileInputStream(templateFile);
                //转换成html流
                PoiUtils.docxToHtml(fis,response.getOutputStream(), imagePath);
            } catch (IOException e ) {
                logger.error("报表模板预览错误.",e);
                e.printStackTrace();
            }
        } else {  //预览按钮：显示指定产品的实际内容
            if (null != params.get("endDate")) {
                Date endDate = com.joyintech.framework.util.DateUtil.parse(params.get("endDate").toString());
                params.put("endDate", new java.sql.Date(endDate.getTime()));
            }
            if (null != params.get("startDate")) {
                Date endDate = com.joyintech.framework.util.DateUtil.parse(params.get("startDate").toString());
                params.put("startDate", new java.sql.Date(endDate.getTime()));
            }

            params.put("prodId", params.get("portnum"));
            params.put("genDate", params.get("endDate"));
    		
            //TODO：调试临时写死的参数
            if ( params.get("prodId")==null ) {
            	params.put("prodId", "LEO-001");
            }
            
            if ( params.get("genDate")==null ) {
            	Date genDate = DateUtil.parse("2022-06-30");
            	params.put("genDate", genDate);
            }

            //查询实际数据
            List<Map<String,Object>> listReports = rptDataService.queryDatas(params);
            //这里的报表是手工生成，一次就一张报表
            Map<String,Object> dataMap = null;
            if ( listReports.size()>0 )
            	dataMap = listReports.get(0);
            else
            	dataMap = new HashMap<>();

            //生成服务器报表文件，供编辑、导出功能使用
            String reportFile = genReportFile(ReportFileUtil.REPORT_TYPE_临时文件,reportInfo,dataMap,(Date)params.get("genDate"));
            
            FileInputStream fis = null;
            try {
                //将生成的文件作为输入，转换成html后，发送到response
                fis = new FileInputStream(reportFile);
                PoiUtils.docxToHtml(fis,response.getOutputStream(), imagePath);
            } catch (Exception e ) {
                e.printStackTrace();
            } finally {
                try {
                    if ( fis!=null ) {
                        fis.close();
                    }
                } catch (IOException e ) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     *
     * 生成报表：创建服务器文件，创建工作流进行报表确认
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @see
     * @since 1.0
     */
    public boolean genReport(Map<String,Object> params) throws MalformedURLException{

    	boolean ret = true;
        
        String repId = params.get("repId").toString();
        ReportInfo reportInfo = reportInfoMapper.getReportInfoByID(repId);
        Date genDate = (Date)params.get("genDate");

        if(!StringUtil.isNotEmpty(reportInfo)){
            logger.error("生成报表失败!");
            return false;
        }

        List<String> prdList = new ArrayList<>();
        try {
            if(params.get("portnum").toString().indexOf("[")==0){
                prdList = JSONArray.parseArray(params.get("portnum").toString(),String.class);
            }else{
                prdList.add(params.get("portnum").toString().replace("\"", ""));
            }

            for (String portnum : prdList) {
            	params.put("prodId", portnum);
                Map<String,Object> dataMap = rptDataService.queryDatas(params).get(0);  
                String reportFile = this.genReportFile(ReportFileUtil.REPORT_TYPE_报表文件,reportInfo, dataMap,genDate);

	            UploadFile uploadFile = new UploadFile();
	            uploadFile.setFileName(reportFile);
	            uploadFile.setSrcName(reportFile);
                logger.info("生成文件成功!");
                
                //创建工作流，TODO:报表分类
                boolean flag = insertRepReveal(params, uploadFile.getFileNo(),uploadFile.getSrcName(),reportInfo.getReportName(),reportInfo.getReportCategory());
                if ( flag == false ) {
                	ret = false;
                	logger.error("生成信息主表错误!"+repId);
                }
            }
        } catch (Exception e) {
            logger.error("error:报表生成错误.",e);
           ret = false;
        }
        return ret;
    }

    /**
     * 暂时保留，生成报表文件，不做提交流程等处理
     * @param fileType
     * @param params
     * @return
     */
    public boolean geneReportFileOld(String fileType,Map<String,Object> params){

        boolean flag = false;
        String repId = params.get("repId").toString();
        RepInfo repInfo = repInfoMapper.selectByPrimaryKey(repId);

        String reportPath = "E:/poitest/report/";
        String fileName = reportPath + 1 + GlobalConstant.UNDER_LINE+repInfo.getReportName();
        logger.info("**************************** ");
        logger.info("fileName = " + fileName);
        logger.info("**************************** ");

        try {

            //TODO:临时写死，不去配数据库
            String templateFile = repInfo.getTemplatePath()+ repInfo.getTemplateName();
            templateFile = "E:/poitest/template/产品说明书.docx";

            Map<String,Object> dataMap = rptDataService.queryDatas(params).get(0);  //TODO:repId
            if ( fileType.equals("docx")) {
                FileOutputStream fos = new FileOutputStream(new File(fileName+"."+fileType));
                PoiUtils.mergeDocx(templateFile, fos, dataMap);
            } else if ( fileType.equals("pdf")) {

                ByteArrayOutputStream os = null;
                InputStream is = null;
                FileOutputStream fos = new FileOutputStream(new File(fileName+"."+fileType));
                try {
                    //替换参数
                    os = new ByteArrayOutputStream();
                    PoiUtils.mergeDocx(templateFile, os, dataMap);

                    //将替换后的输出作为输入，转换成html后，发送到response
                    is = new ByteArrayInputStream(os.toByteArray());

                    PoiUtils.docxToPdf(is, fos);
                    flag = true;
                } catch ( IOException e) {
                    e.printStackTrace();
                    flag = false;
                } finally {
                    if ( os!=null ) {
                        os.flush();
                        os.close();
                    }
                    if ( is!=null ) {
                        is.close();
                    }
                    if ( fos!=null ) {
                        //fos.flush();
                        fos.close();
                    }
                }
            }
        } catch (Exception e ) {
            e.printStackTrace();
            flag = false;
        }

        UploadFile uploadFile = new UploadFile();
        uploadFile.setFileName(fileName);
        uploadFile.setSrcName(fileName);

        logger.info("导出文件!");
        return flag;
    }

    public int saveFile (HttpServletRequest request,String outputFilename ) throws IOException{
        int ret = 0;
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                // 取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    // 取得当前上传文件的文件名称
                    String myFileName = StringUtil.trim(file.getOriginalFilename());
                    //String filePath = System.getProperty("user.home");
                    // 如果名称不为空,说明该文件存在，否则说明该文件不存在
                    if (StringUtil.isNotEmpty(myFileName)) {
                        // 文件存储到服务器之前调用监听器

                        File localFile = new SafelyFile(outputFilename);
                        File dir = localFile.getParentFile();
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }
                        if (!localFile.exists()) {
                            localFile.createNewFile();
                        }
                        // file.transferTo(localFile);
                        ret = FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(localFile));
                        //PoiUtils.copy(file.getName(), localFile.getName());

//                        UploadFile fileInfo = new UploadFile(myFileName, IDGenerator.generate(),
//                                localFile);
//                        fileInfo.setFileName(localFile.getPath());
//                        if(null != fileAttachmentProcess) {
//                            fileAttachmentProcess.upload(fileInfo);
//                        }
                    }
                }
            }
        }
//        progressBarService.updatePercent(pageId, ProgressBarService.FILE_UPLOAD_ID, 0.5D, true);
        return  ret;
    }

    public int loadFile (HttpServletResponse response,String inputFilename) throws IOException {
        FileInputStream fis = new FileInputStream(inputFilename);
        //转换成html流
        //PoiUtils.docxToHtml(fis,response.getOutputStream(), imagePath);

        OutputStream os = response.getOutputStream();
        int len = 0;
        byte[] buffer = new byte[1024];
        while ( (len = fis.read(buffer))!=-1 ) {
            os.write(buffer,0,len);
        }
        fis.close();
        os.flush();

        return len;
    }

}
