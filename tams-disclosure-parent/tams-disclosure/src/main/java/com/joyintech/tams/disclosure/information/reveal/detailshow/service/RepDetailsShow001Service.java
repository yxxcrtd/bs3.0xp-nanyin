package com.joyintech.tams.disclosure.information.reveal.detailshow.service;

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

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.reportDeal.service.ReportDealService;
import com.joyintech.tams.disclosure.busicommon.poireport.dto.DisReportType;
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

import com.joyintech.framework.base.intf.DataDict;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.RepJobRange;
import com.joyintech.tams.common.util.JacksonUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.util.RevealDeadLineService;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.dao.ReportInfoMapper;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.pojo.ReportInfo;
import com.joyintech.tams.disclosure.busicommon.poireport.service.PoiReportService;
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
        FileInputStream fis = null;
        try {
	        if ( params.get("elementIdForLog")==null || !params.get("elementIdForLog").equals("RepDetailsShow001_showDetail") ) {  //树单击：显示模板
	        	reportFile = poiReportService.genReport(params,PoiReportService.REPORT_TYPE_模板文件,"preview");
	        } else {  //预览按钮：显示指定产品的实际内容
	        	params.put("platformDate", commonPlatformDateService.getSysDate());
	            reportFile = poiReportService.genReport(params,PoiReportService.REPORT_TYPE_报表文件,"preview");
	        }
	
	        //将生成的文件作为输入，转换成html后，发送到response
	        fis = new FileInputStream(reportFile);
	        PoiUtils.docxToHtml(fis,response.getOutputStream(), imagePath);
        } catch (Exception e ) {
        	logger.error("查询数据错误。",e);
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

    /**
     * 生成报表：创建服务器文件，创建工作流进行报表确认
     * @param params
     * @throws Exception
     * @author chenxc
     */
    public void genReport(Map<String,Object> params) throws Exception {
    	poiReportService.genReport(params,PoiReportService.REPORT_TYPE_报表文件,"gen");
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
        	genDate = DateUtil.parse(params.get("reportDate").toString());
        if (params.get("endDate")!=null )
        	genDate = DateUtil.parse(params.get("endDate").toString());
 
        String prodId = "";
        if (params.get("prodId") != null )
            prodId = params.get("prodId").toString();
        String reportFile = poiReportService.buildReportFileName(PoiReportService.REPORT_TYPE_报表文件,reportInfo, genDate, prodId,PoiReportService.FILE_TYPE_DOCX);
        String localFileName = reportInfo.getReportName()+"_"+prodId+"_" + DateUtil.format(genDate)+PoiReportService.FILE_TYPE_DOCX;

        if ( "pdf".equals(fileType)) {  //如果导出pdf，则需要先讲word转换成pdf
            FileInputStream fis = new FileInputStream(reportFile);
            String fileNamePdf = poiReportService.buildReportFileName(PoiReportService.REPORT_TYPE_报表文件,reportInfo, genDate, prodId,PoiReportService.FILE_TYPE_PDF);
            FileOutputStream fos = new FileOutputStream(fileNamePdf);
            PoiUtils.docxToPdf(fis,fos);
            reportFile = fileNamePdf;
            localFileName = reportInfo.getReportName()+"_"+prodId+"_" + params.get("reportDate").toString()+PoiReportService.FILE_TYPE_PDF;
        }

        File remoteFile = new SafelyFile(reportFile);

        if(!remoteFile.exists()){
        	remoteFile.createNewFile();
        }

        return ExportUtil.export(localFileName, remoteFile, false);
        
    }

    public AjaxResult check(Map<String, Object> param){
        AjaxResult result = new AjaxResult();
        List<ValidMessage> messages = new ArrayList<>();
        RepReveal repReveal = poiReportService.getRepReveal(param);

        if(!reportDealService.selectConfirmStatus(repReveal)){
            //存在已确认的数据
            messages.add(new ValidMessage("报表已确认！"));
        }
        //判断数据是否在流程中
        if(reportDealService.ckeckWflStatus(repReveal)){
            messages.add(new ValidMessage("存在流程中的报表！"));
        }

        if(!StringUtil.isEmptyList(messages)) {
            result.setErrorList(messages);
        }
        return result;

    }

    public RepReveal getRepReveal(Map<String, Object> param) {
    	return poiReportService.getRepReveal(param);
    }
}
