package com.joyintech.tams.disclosure.information.reveal.detailshow.service;

import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.reportDeal.service.ReportDealService;
import com.joyintech.tams.disclosure.busicommon.poireport.dto.DisReportType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.intf.DataDict;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.RepJobRange;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.common.util.JacksonUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.reportDeal.service.ReportDealService;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.util.RevealDeadLineService;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.dao.ReportInfoMapper;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.pojo.ReportInfo;
import com.joyintech.tams.disclosure.busicommon.poireport.service.PoiReportService;
import com.joyintech.tams.disclosure.busicommon.poireport.service.ReportFileService;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.reveal.detailshow.dao.RepDetailsShow001Mapper;
import com.joyintech.tams.disclosure.information.reveal.detailshow.dto.ProdInfoDto;
import com.joyintech.tams.disclosure.information.reveal.detailshow.dto.RepDetailsShow001Dto;
import com.joyintech.tams.disclosure.interf.interf.common.constant.DateUtil;
import com.joyintech.tams.systemtools.service.CommonPlatformDateService;
import com.joyintech.tams.utils.poi.PoiUtils;

@Service
@Transactional(rollbackFor = Exception.class)
public class RepDetailsShow001Service extends CommonExportService<RepDetailsShow001Dto>{
	
	@Autowired
	private RepDetailsShow001Mapper repDetailsShow001Mapper;
	
	@Autowired
	private ReportInfoMapper reportInfoMapper;
	
    @Autowired
    RevealDeadLineService revealDeadLineService;
    
    @Autowired
    private CommonPlatformDateService commonPlatformDateService;
    
    @Autowired
    private PoiReportService poiReportService;

    @Autowired
    private ReportDealService reportDealService;

    @Autowired
    private ReportFileService reportFileService;

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
     * 获取错误信息
     * @param params
     * @return
     * @throws MalformedURLException
     */
    public List<ValidMessage> getErrorMessage(Map<String,Object> params) throws MalformedURLException{
    	return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/RepDetailsShow001/getErrorMessage",params,
    			ValidMessage.class);
    	
    }
    
    /**
     * 删除错误信息
     */
    public int deleteErrorMsg (String rep_id){
    	return restTemplate.getForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/RepDetailsShow001/deleteErrorMsg?repId="+rep_id,
    			Integer.class);
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

    /**
     * 获取产品信息
     * 
     * @return 产品信息
     */
    public List<SelectOptionDto> getProdInfo(){
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
    
    public void getRandgeRule(Map<String, Object> params){
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
                        ||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_产品期限类型.getValue())
                        ||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_开放频率.getValue())
                        ||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_是否是指数型产品.getValue())
                        ||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_是否是客户周期型产品.getValue())
                        ||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_信批产品类别.getValue())){
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
     * 显示报表：1.用于单击报表显示报表模板，2.指定报表查询条件后预览报表； 
     * @param params
     * @param response
     * @author chenxc
     */
    public void previewReport(Map<String,Object> params, HttpServletResponse response) {
        String imagePath = "";
        String reportFile = "";
//        FileInputStream fis = null;
        ByteArrayInputStream bis = null;
        try {
	        if ( params.get("elementIdForLog")==null || !params.get("elementIdForLog").equals("RepDetailsShow001_showDetail") ) {  
	        	//树单击：初始化报表，即报表模板中的变量全部用xxx代替
	    		String repId = params.get("repId").toString();
	        	reportFile = poiReportService.initReport(repId);
	        } else {  //预览按钮：显示指定产品的实际内容，生成了一个临时文件f1
	            reportFile = poiReportService.previewReport(params);
	        }
	        
	        //将生成的文件作为输入，转换成html后，发送到response
            byte[] bytes = poiReportService.getFileByteArray(reportFile);
            if(bytes != null) {
                bis = new ByteArrayInputStream(bytes);
	            PoiUtils.docxToHtml(bis,response.getOutputStream(),imagePath);
            }
        } catch (Exception e ) {
        	logger.error("查询数据错误。",e);
            e.printStackTrace();
        } finally {
            if(bis != null){
                try{
                    bis.close();
                }catch(IOException e){
                    logger.error("关闭输入字节流异常",e);
                }
            }
        }

    }

    /**
     * 生成报表：创建服务器文件，创建工作流进行报表确认
     * @param params
     * @throws Exception
     * @author chenxc
     */
    public void genReport(Map<String,Object> params) throws Exception {
    	poiReportService.genReport(params);
    }

    /**
     * 导出合并文件
     * @param params
     * @return
     * @throws IOException
     * @author chenxc
     */
    public ResponseEntity<byte[]> exportReport(Map<String,Object> params) throws IOException {
        
        if ( params==null || params.size()==0) {
            return null;
        }

        String reportId = params.get("repId").toString();   //报表编号
        ReportInfo reportInfo = reportInfoMapper.getReportInfoByID(reportId);

        String fileType = params.get("fileType").toString();
        
        Date genDate = commonPlatformDateService.getSysDate();
        if (params.get("reportDate")!=null )
        	genDate = (Date)(params.get("reportDate"));
        if (params.get("endDate")!=null )
        	genDate = DateUtil.parse(params.get("endDate").toString());
 
        String prodId = "";
        if (params.get("prodId") != null )
            prodId = params.get("prodId").toString();
        String reportFile = reportFileService.getTempOSSReportFileName(reportId, genDate, params, ReportFileService.FILE_TYPE_DOCX);
        File f = new File(reportFile);
        //String localFileName = reportInfo.getReportName()+"_"+prodId+"_" + DateUtil.format(genDate)+ReportFileService.FILE_TYPE_DOCX;
        String localFileName = f.getName();
        if ( "pdf".equals(fileType)) {  //如果导出pdf，则需要先讲word转换成pdf
            ByteArrayInputStream bis = null;
            byte[] bytes = poiReportService.getFileByteArray(reportFile);
            if(bytes!=null){
                bis = new ByteArrayInputStream(bytes);
            }
            String fileNamePdf = reportFileService.getReportFileName(reportId,genDate,params,ReportFileService.FILE_TYPE_PDF);

            FileOutputStream fos = new FileOutputStream(fileNamePdf);
            PoiUtils.docxToPdf(bis,fos);
            reportFile = fileNamePdf;
            localFileName = localFileName.replace(ReportFileService.FILE_TYPE_DOCX, ReportFileService.FILE_TYPE_PDF);
        }else{
        	String  localReportFile = reportFileService.getReportFileName(reportId,genDate,params,ReportFileService.FILE_TYPE_DOCX);
        	FileOutputStream fos = null;
           byte[] bytes = poiReportService.getFileByteArray(reportFile);
           if(bytes !=null){
               try{
                   fos = new FileOutputStream(localReportFile);
                   fos.write(bytes);
               }catch(Exception e){
                   logger.error("写本地副本发生异常",e);
                   throw new FileNotFoundException("本地文件写入失败");
               }finally{
                   if(fos!=null){
                       try {
                           fos.close();
                       }catch (IOException e){
                           logger.error("关闭输出文件流异常",e);
                       }
                   }
               }
               reportFile = localReportFile;
           }else{
               throw new FileNotFoundException("远程下载文件失败");
           }
        }

        File remoteFile = new SafelyFile(reportFile);

        if(!remoteFile.exists()){
        	remoteFile.createNewFile();
        }

        return ExportUtil.export(localFileName, remoteFile, true);
        
    }

    public AjaxResult check(Map<String, Object> param){
        AjaxResult result = new AjaxResult();
        List<ValidMessage> messages = new ArrayList<>();
        
        collateParams(param);
        RepReveal repReveal = poiReportService.getRepReveal(param);


        if(!reportDealService.selectConfirmStatus(repReveal)){
            //存在已确认的数据
            messages.add(new ValidMessage("当前信披单据已确认，不支持重新生成"));
            result.setErrorList(messages);
            return result;
        }
        //判断数据是否在流程中
        if(reportDealService.ckeckWflStatus(repReveal)){
            messages.add(new ValidMessage("存在流程中的报表！"));
            result.setErrorList(messages);
            return result;
        }

        if(StringUtil.equals("01",(String)param.get("flag"))){
            if(reportDealService.checkSameReport(repReveal)){
                result.setMessage("存在相同的报表,是否继续编辑?", MessageType.确认消息);
                return result;
            }
        }else if(StringUtil.equals("02",(String)param.get("flag"))){
            if(reportDealService.checkSameReport(repReveal)){
                result.setMessage("存在相同的报表,是否继续生成?", MessageType.确认消息);
                return result;
            }
        }

        return result;

    }

    public RepReveal getRepReveal(Map<String, Object> param) {
    	return poiReportService.getRepReveal(param);
    }

    /**
     * 一个特殊的方法，用于校验整理前台传入的参数，并进行标准化
     * 命名标准：采用小写开头每个单词大写开头发样式，如endDate
     * 类型标准：传入的参数全部是string类型，新增的变量按照实际需要进行类型转换
     * @param params
     */
	public void collateParams(Map<String,Object> params) {
		//如下传入参数标准化
		params.put("modelId", params.get("modelId")==null?params.get("modelid"):params.get("modelId"));
		params.put("prodid", params.get("prodid")==null?params.get("pprodid"):params.get("prodid"));  //单产品只传了pprodid
		params.put("prodname", params.get("prodname")==null?params.get("pprodname"):params.get("prodname"));  //单产品只传了pprodid
		params.put("pprodId", params.get("pprodId")==null?params.get("pprodid"):params.get("pprodId"));
		params.put("prodId", params.get("prodId")==null?params.get("prodid"):params.get("prodId"));
		params.put("prodName", params.get("prodName")==null?params.get("prodname"):params.get("prodName"));
		params.put("repId", params.get("repId")==null?params.get("repid"):params.get("repId"));
		params.put("reportId", params.get("reportId")==null?params.get("repid"):params.get("reportId"));
		params.put("endDate", params.get("endDate")==null?params.get("enddate"):params.get("endDate"));
		params.put("repName", params.get("repName")==null?params.get("repname"):params.get("repName"));
		params.put("uri", params.get("uri")==null?params.get("modelid"):params.get("uri"));

		//以下新增参数
		if ( params.get("endDate")!=null )
			params.put("reportDate", DateUtil.parse(params.get("endDate").toString()) );
		params.put("platformDate", commonPlatformDateService.getSysDate());
		logger.debug("传入参数---------------------------------------<<<");
		logger.debug(params.toString());
		logger.debug("传入参数--------------------------------------->>>");
	}

}
